<%-- 
    Document   : Voting
    Created on : Jun 1, 2021, 12:05:49 PM
    Author     : RC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='java.util.ArrayList' %>
<%@page import='Evote.Dto.CandidateInfo' %>
<html>
    <head>
        <title>show voitng Information</title>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">   
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/jquery.js"></script>    
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
       
    </head>
    <body>
        <%
            
        CandidateInfo candidate=(CandidateInfo)request.getAttribute("candidate");
          out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
            "<div class='subcandidate'>vote already done</div><br><br>");
           StringBuffer sb=new StringBuffer();
       if(candidate!=null){
                     String id=candidate.getCandidate_id();
                     String party=candidate.getParty();
                     String username=candidate.getUsername();
                     String image="<img src='data:image/jpg;base64,"+candidate.getSymbol()+"'style='width:300px;height:200px;'/>";                 
                     sb.append(image);
                     sb.append("<br>candidate id :"+id);
                     sb.append("<br>candidate name :"+username);
                     sb.append("<br>party :"+party);         
           out.println(sb); 
       }
    %>
    </body>
</html>
