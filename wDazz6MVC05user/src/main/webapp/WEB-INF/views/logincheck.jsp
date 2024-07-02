<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
session = request.getSession(false);

if(session == null || session.getAttribute("userid") == null) {
//	response.sendRedirect("login.jsp");
	out.println("<script>");
	out.println("alert('로그인 후 이용해 주세요.')");
	out.println("location.href='login.html'");
	out.println("</script>");
	
	return;
}
%>