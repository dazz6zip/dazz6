<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session.removeAttribute("adminOk");
%>
<b>로그아웃 완료</b><br/>
<a href="javascript:window.close()">종료</a>
</body>
</html>