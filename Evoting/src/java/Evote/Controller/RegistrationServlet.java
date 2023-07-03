
package Evote.Controller;
import Evote.Dao.UserDetailsDao;
import Evote.Dto.UserPojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RegistrationServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          String userid,pwd,username,email,address,city,mno,gender;
          RequestDispatcher rd=null;
          userid=request.getParameter("id");
          pwd=request.getParameter("pwd");
          username=request.getParameter("username");
          email=request.getParameter("email");
          address=request.getParameter("address");
          city=request.getParameter("city");
          mno=request.getParameter("mobile");
          gender=request.getParameter("gender");
          UserPojo u=new UserPojo();
         u.setAdhar_no(userid);
         u.setPassword(pwd);
         u.setUsername(username);
         u.setCity(city);
         u.setAddress(address);
         u.setEmail(email);
         u.setMno(mno);
         u.setGender(gender);
          boolean userfound=true,result=false;
          try{
             userfound=UserDetailsDao.search(userid);
             if(userfound==false){
                 result=UserDetailsDao.register(u);
             }
             rd=request.getRequestDispatcher("registration.jsp");
             request.setAttribute("userfound",userfound);
             request.setAttribute("result",result); 
             rd.forward(request, response);
          }
          catch(SQLException ex){
              request.setAttribute("exception",ex);
              rd=request.getRequestDispatcher("Error.jsp");
              rd.forward(request, response);
          }    
        }
    }   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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