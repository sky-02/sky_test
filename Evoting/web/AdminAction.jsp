<%-- 
    Document   : AdminAction
    Created on : May 11, 2021, 4:45:30 PM
    Author     : RC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Admin Action</title>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">   
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/result.css" rel="stylesheet">
        <script src="jsscript/jquery.js"></script>    
        <script src="jsscript/AdminAction.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js""></script>      
    </head>
    <body>
        <%
             HttpSession sess=request.getSession();
            String userid=(String)sess.getAttribute("userid");
            if(userid==null)
            {
                       response.sendRedirect("AccessDenied.html");
                        return;
            }
            sess.setAttribute("userid",userid);
           StringBuffer displayBlock=new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Admin Actions Page</div><br><br>"+
                    "<div class='logout'><a href='login.html'>logout</a></div></div>"
                   + "<div class='container'>"
                   +            "<div id='dv1' onclick='manageuser()'>"
                   + "<img src='images/muser.png' height='255px' width='250px'><br>"
                   + "<h3>Manage User</h3></div>"+
                   "<div id='dv2' onclick='managecandidate()'>"
                   + "<img src='images/ManageCandLists.jpg' height='250px' width='250px'><br>"
                   + "<h3>Manage Candidate</h3></div>"+
                   "<div id='dv3' onclick='electionresult()'>"
                   + "<img src='images/resultgraph.jpg' height='250px' width='250px'><br>"
                   + "<h3>Show Result</h3></div>"+
          "<br><br><div align='center' id='result'></div>"+
        "</div>");
           
             displayBlock.append("<br><br><div align='center' id='data'></div>");
           out.println(displayBlock);
            %>
    </body>
</html>
