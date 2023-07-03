

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='Evote.Dto.UserPojo' %>
<%@page import='java.util.ArrayList' %>
<%
    ArrayList<UserPojo>list=(ArrayList)request.getAttribute("list");
    StringBuffer sb=new StringBuffer();
   sb.append("<table style='border:solid 2px black' >");
   sb.append("<tr><th>User id</th>");
   sb.append("<<th>user name</th>");
   sb.append("<th>Address</th>");
   sb.append("<th>city</th>");
   sb.append("<th>Email</th>");
   sb.append("<th>gender</th>");
   sb.append("<th>Mobile No</th></tr>"); 
   for(UserPojo u:list){
   sb.append("<tr><td>"+u.getAdhar_no()+"</td>");
   sb.append("<td>"+u.getUsername()+"</td>");
   sb.append("<td>"+u.getAddress()+"</td>");
   sb.append("<td>"+u.getCity()+"</td>");
   sb.append("<td>"+u.getEmail()+"</td>");
   sb.append("<td>"+u.getGender()+"</td>");
   sb.append("<td>"+u.getMno()+"</td></tr>");
}
   sb.append("</table>");
   out.println(sb);
    %>