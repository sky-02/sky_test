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
   JSONObject json=new JSONObject();
   json.put("name",name);
   json.put("city",city);
   json.put("party",party);
   json.put("src",image);
   out.println(json);
   }
   else{
       out.println("null object");
   }
    %>
    
    
    
    
