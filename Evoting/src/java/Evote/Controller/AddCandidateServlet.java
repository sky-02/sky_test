
package Evote.Controller;

import Evote.Dao.CandidateDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
public class AddCandidateServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session=request.getSession();
           // out.println("add candidate servlet");
            RequestDispatcher rd=null;
            String userid=(String)session.getAttribute("userid");
            String candidateId=(String)request.getParameter("id");
            String usid=(String)request.getParameter("uid");
             //out.println("usid is  "+usid);
              //out.println("candidate is  "+candidateId);
             System.out.println("user id is outside "+usid);
        if(userid==null)
        {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
        }
        
        if(usid!=null){
            try{
               //out.println("information about candidate "+usid);
               ///out.println("user id is  "+usid);
                String username=CandidateDao.getUsernameById(usid);
                 ArrayList<String> city=CandidateDao.getCity();
                 //out.println("username is "+username);
                 JSONObject obj=new JSONObject();
                 if(username==null)
                     username="wrong";
                 StringBuffer sb=new StringBuffer();
                 for(String c:city){
                     sb.append("<option value='"+c+"'>"+c+"</option>");
                 }
                 obj.put("username",username);
                 obj.put("city",sb.toString()); 
                 out.println(obj);
            }
            catch(SQLException sql){
                rd=request.getRequestDispatcher("Error.jsp");
                request.setAttribute("exception",sql);
                rd.forward(request, response);
            }
          }
        else if(candidateId.equalsIgnoreCase("getid")&&candidateId!=null){
            try{
               // out.println("candidate id generating");
                String id=CandidateDao.getId();
                System.out.println("candidate id is "+CandidateDao.getId());
                 ArrayList<String>user=CandidateDao.getUserID();
            JSONObject obj=new JSONObject();
                 
                 StringBuffer sb=new StringBuffer();
                 sb.append("<option value='select id'>select id</option>");
                 for(String c:user){
                     sb.append("<option value='"+c+"'>"+c+"</option>");
                 }
                 obj.put("id",sb.toString()); 
                 obj.put("cid",id);
                 out.println(obj);
                return;
            }
            catch(SQLException sql){
                rd=request.getRequestDispatcher("Error.jsp");
                request.setAttribute("exception",sql);
                rd.forward(request, response);
            }
        }
        
       }        
    }
    
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
