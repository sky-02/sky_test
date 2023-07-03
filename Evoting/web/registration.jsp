<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%try{
   boolean result=(boolean)request.getAttribute("result");
   boolean userfound=(boolean)request.getAttribute("userfound");
   if(result==true)
     out.println("success");
   else if(userfound==true)
       out.println("uap");
   else
       out.println("fail");
    }catch(Exception ex){
        ex.printStackTrace();
   }%>