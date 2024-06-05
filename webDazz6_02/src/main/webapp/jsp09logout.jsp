<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
HttpSession ses = request.getSession(false);
// false : 세션이 있으면 읽고, 없어도 만들지 않음

ses.removeAttribute("userId");
// 세션 삭제

response.sendRedirect("jsp09sessionLogin.html");
// jsp09sessionLogin.html로 이동
%>