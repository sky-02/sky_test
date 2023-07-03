/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evote.Controller;

import Evote.Dao.VotingDao;
import Evote.Dto.CandidateInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class VoterServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             HttpSession session=request.getSession();
           // out.println("add candidate servlet");
            RequestDispatcher rd=null;
            String userid=(String)session.getAttribute("userid");
            out.println("userid is"+userid);
            try{
                String cid=VotingDao.getCandidateId(userid);
                out.println(cid);
                if(cid==null){
                    out.println("inside if...");
                    ArrayList<CandidateInfo>list=VotingDao.getDetails(userid);
                    request.setAttribute("candidate",list);
                    rd=request.getRequestDispatcher("Voting.jsp");
                    rd.forward(request, response);
                }
                else{
                    CandidateInfo c=VotingDao.getVote(cid);
                    request.setAttribute("candidate",c);
                    rd=request.getRequestDispatcher("VoteDenied.jsp");
                    rd.forward(request, response);
                }
            }
            catch(Exception ex){
               rd=request.getRequestDispatcher("Error.jsp");
               request.setAttribute("exception", ex);
               rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
