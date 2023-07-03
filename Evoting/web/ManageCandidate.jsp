<%-- 
    Document   : ManageCandidate
    Created on : May 11, 2021, 5:24:57 PM
    Author     : RC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title> manage candidate</title>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">   
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/jquery.js"></script>    
        <script src="jsscript/ManageCandidate.js"></script>
         <script src="jsscript/showCandidate.js"></script>
         <script src="jsscript/Update.js"></script>
          <script src="jsscript/delete.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js""></script> 
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                //response.sendRedirect("AccessDenied.html");
                //return;
            }
            session.setAttribute("userid",userid);
            StringBuffer displayBlock=new StringBuffer("");
            displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
            "<div class='subcandidate'>Admin Actions Page</div><br><br>");
            displayBlock.append("<div class='logout'><a href='Login.html'>logout</a></div></div>");
            displayBlock.append("<div class='container'>");
            displayBlock.append("<div id='dv1' onclick='showaddcandidateform()'>"
                    + "<img src='images/addcandidate.png' height='300px' width='300px'>"
                    + "<br><h3>Add Candidate</h3></div>");
            displayBlock.append("<div id='dv2' onclick='showupdatecandidateform()'>"
                    + "<img src='images/update1.jpg' height='300px' width='300px'>"
                    + "<br><h3>Update Candidate</h3></div>");
            displayBlock.append("<div id='dv3' onclick='showcandidate()'>"
                    + "<img src='images/candidate.jpg' height='300px' width='300px'>"
                    + "<br><h3>Show Candidate</h3></div>");
             displayBlock.append("<div id='dv4' onclick='deletecandidate()'>"
                    + "<img src='images/update3.jpg' height='300px' width='300px'>"
                    + "<br><h3>Delete Candidate</h3></div>");
             displayBlock.append("</div>");
             displayBlock.append("<br><br><div align='center' id='result'></div>");
             displayBlock.append("<br><br><div align='center' id='data'></div>");
             out.println(displayBlock);


            %>
    </body>
</html>
