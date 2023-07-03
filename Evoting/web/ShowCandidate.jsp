<%-- 
    Document   : ShowCandidate
    Created on : May 22, 2021, 11:02:00 PM
    Author     : RC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='Evote.Dto.CandidateDetails' %>
<%@page import="org.json.JSONObject"%>
<%
   CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
   if(cd!=null){
   String name=cd.getUsername();
   String city=cd.getCity();
   String userid=cd.getUserID();
   String party=cd.getParty();
   String cid=cd.getCandidateId();
    String image="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px;'/>";
   StringBuffer sb=new StringBuffer();
   sb.append("<table>");
   sb.append("<tr><th>userid</th><td>"+userid+"</td>/<th></tr>");
   sb.append("<tr><th>username</th><td>"+name+"</td>/<th></tr>");
   sb.append("<tr><th>candidate id</th><td>"+cid+"</td>/<th></tr>");
   sb.append("<tr><th>city</th><td>"+city+"</td>/<th></tr>");
   sb.append("<tr><th>party</th><td>"+party+"</td>/<th></tr>");
   sb.append("<tr><th>image</th><td>"+image+"</td>/<th>");
   sb.append("</table>");
   JSONObject json=new JSONObject();
   json.put("table",sb.toString());
   out.println(json);
   }
   else{
       out.println("null object");
   }
    %>
    
    
    
    