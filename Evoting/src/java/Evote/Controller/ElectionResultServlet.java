/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evote.Controller;

import Evote.Dao.CandidateDao;
import Evote.Dao.VotingDao;
import Evote.Dto.CandidateDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class ElectionResultServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd=null;
            HttpSession session=request.getSession();
            String userid=(String)session.getAttribute("userid");
        if(userid==null)
        {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
        }
            try{
                LinkedHashMap<CandidateDetails,Integer>vote=new LinkedHashMap<>();
                Map<String,Integer>result=VotingDao.getResult();
               Set s=result.entrySet();
               CandidateDetails c=null;
               Iterator it=s.iterator();
               while(it.hasNext()){
                 Map.Entry<String,Integer>e=(Map.Entry)it.next();
                 c=CandidateDao.getCandidateDetails(e.getKey());
                 vote.put(c,e.getValue());
               }
               int voteCount=VotingDao.getVoteCount();
               request.setAttribute("result",vote);
               request.setAttribute("vote",voteCount);
               rd=request.getRequestDispatcher("ShowResult.jsp");
               rd.forward(request, response);
            }
            catch(Exception ex){
                out.println(ex);
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
