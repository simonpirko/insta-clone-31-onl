<%@ page import="by.tms.instaclone31onl.core.constants.AttributeConstants" %>
<%@ page import="java.util.List" %><%
    List<String> errors = (List<String>)request.getAttribute(AttributeConstants.ERRORS);

    if(errors != null && !errors.isEmpty()){
        out.println("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"> <ul>");
        for(String error : errors){
            out.println("<li>"+error+"</li>");
        }
        out.println(" </ul><button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
    }
%>