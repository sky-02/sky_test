<%@page import="Evote.Dao.CandidateDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='Evote.Dto.CandidateDetails' %>
<%@page import='java.util.*'%>
<%
  LinkedHashMap<String,Integer>result=(LinkedHashMap)request.getAttribute("result");
  //LinkedHashMap<String,Integer>symbol=(LinkedHashMap)request.getAttribute("vote");
  float vote=(int)request.getAttribute("vote");
   Set s=result.entrySet();
   StringBuffer sb=new StringBuffer();
               Iterator it=s.iterator();
               sb.append("<table>");
   sb.append("<th>party</th>");
   sb.append("<th>symbol</th>");
   sb.append("<th>Voting Count</th>");
   sb.append("<th>vote percentage</th></tr>");
               while(it.hasNext()){
                 Map.Entry<String,Integer>e=(Map.Entry)it.next();
                 String party=e.getKey();
                 float voteCount=e.getValue();
              float votePer=(voteCount/vote)*100.0f;  
            String image="<img src='data:image/jpg;base64,"+CandidateDao.getSymbol(e.getKey()) +"'style='width:300px;height:200px;'/>";
                sb.append("<td>"+party+"</td>");
                sb.append("<td>"+image+"</td>");
                sb.append("<td>"+(int)voteCount+"</td>");
                sb.append("<td>"+votePer+"</td></tr>");
               }
             sb.append("</table>");
             out.println(sb);
    %>
  