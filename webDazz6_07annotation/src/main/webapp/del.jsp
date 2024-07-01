<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="pd" class="pack.business.ProcessDAO" />

<%
String id = request.getParameter("id");
boolean b = pd.deleteData(id);

if(b) {
   response.sendRedirect("list.jsp");	
}else {
   response.sendRedirect("fail.jsp");	
}
%>