
package Evote.Controller;

import Evote.Dao.CandidateDao;
import Evote.Dto.CandidatePojo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author RC
 */
public class AddNewCandidateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd=null;
            try{
                DiskFileItemFactory df=new DiskFileItemFactory();
                ServletFileUpload sfu=new ServletFileUpload(df);
                ServletRequestContext src=new ServletRequestContext(request);
                List<FileItem>multilist=sfu.parseRequest(src);
                InputStream inp=null;
                ArrayList <String>objValues=new ArrayList<>();
                for(FileItem fit:multilist){
                if(fit.isFormField())
                {
                    String fname=fit.getFieldName();
                    String value=fit.getString();
                    //System.out.println("Inside if");
                    //System.out.println(fname+":"+value);
                    objValues.add(value);
                   
                }
                else
                {
                    inp=fit.getInputStream();
                    String key=fit.getFieldName();
                    String fileName=fit.getName();
                    System.out.println("Inside else");
                    System.out.println(key+":"+fileName);
                }
                }
                String Candidate_ID=CandidateDao.checkParty(objValues.get(4),objValues.get(3));
                if(Candidate_ID!=null){
                    out.println("candidate alreay  of this party in this city");
                    return;
                }
                //System.out.println(inp);
                //out.println("object created");
                CandidatePojo c=new CandidatePojo();
                c.setSymbol(inp);
                c.setCandidate_id(objValues.get(0));
                c.setUserid(objValues.get(1));
                c.setParty(objValues.get(3));
                c.setCity(objValues.get(4));
                //out.println("data set ");
                System.out.println("data set");
                if(CandidateDao.addCandidate(c))
                    rd=request.getRequestDispatcher("Success.jsp");
                else
                    rd=request.getRequestDispatcher("Failer.jsp");
                 rd.forward(request, response);
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



