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
        <title>show voitng</title>
         <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">   
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <script src="jsscript/jquery.js"></script> 
         <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
        <script src="jsscript/vote.js"> </script>
    </head>
    <body>
        <%  
         ArrayList<CandidateInfo>list=(ArrayList<CandidateInfo>)request.getAttribute("candidate");
          out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div></div><br>");
           StringBuffer sb=new StringBuffer();
           for(CandidateInfo candidate:list){
                     String id=candidate.getCandidate_id();
                     String party=candidate.getParty();
                     String username=candidate.getUsername();
                     String image="<img src='data:image/jpg;base64,"+candidate.getSymbol()+"'style='width:300px;height:200px;'/>";                
                    sb.append("<input type='radio' id='"+id+"' name='vote' value='"+candidate.getCandidate_id()+"' onclick='addVote()'>"
                     + "<label for='"+id+"'>"+image+ "</label><br>");
                     sb.append("<div align='center' class='candidateprofile'>Username: "+username+"<br>");
                     sb.append("candidate id:"+id+"<br>");    
                     sb.append("party:"+party+"<br></div>");
                    }
           out.println(sb); 
    %>
    </body>
</html>
