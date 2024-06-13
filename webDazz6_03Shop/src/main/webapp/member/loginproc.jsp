<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.memberMgr"></jsp:useBean>
<%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

boolean b = memberMgr.loginCheck(id, passwd);

if (b) { // b == true : 로그인에 성공
	session.setAttribute("idKey", id);
	response.sendRedirect("login.jsp");
} else { // b == false : 로그인에 실패
	response.sendRedirect("logfail.html");
}
%>