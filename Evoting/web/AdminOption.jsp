<%-- 
    Document   : AdminOption
    Created on : May 11, 2021, 4:48:05 PM
    Author     : RC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">   
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/jquery.js"></script>    
        <script src="jsscript/AdminOption.js"></script>
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
                    "<div class='logout'><a href='Login.html'>logout</a></div></div>");
       displayBlock.append("<div class='container'>");
       displayBlock.append("<div id='dv1' onclick='redirectadministratorpage()'>"
       + "<img src='images/administrator.png' height='300px' width='300px'><br>"
       + "<h3>Admin Options</h3></div>");
       displayBlock.append("<div id='dv2' onclick='redirectvotingpage()'>"
       + "<img src='images/voteadmin.jpg' height='300px' width='300px'><br>"
       + "<h3>Voting Page</h3></div>");
       displayBlock.append("</div>");
       displayBlock.append("<br><br><div align='center' id='result'></div>");
       out.println(displayBlock);
%>

    </body>
</html>
