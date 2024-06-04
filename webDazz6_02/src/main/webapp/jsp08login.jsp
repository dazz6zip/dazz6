<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%
// 로그인 성공시 loginOk, 실패하면 loginFail로 이동하는 로직 처리

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

if ((id.equals("admin") && pwd.equals("111")) || (id.equals("user") && pwd.equals("222"))) {
	session.setAttribute("id", id); // 세션 생성 // 클라이언트마다 다른 값 가능
	response.sendRedirect("jsp08loginOk.jsp");
	
/*	세션이 아닌 request를 사용할 때
	request.setAttribute("id", id); // 클라이언트마다 다른 값 불가능
	request.getRequestDispatcher("jsp08loginOk.jsp").forward(request, response); */
	
} else {
	response.sendRedirect("jsp08loginFail.html");
}
%>