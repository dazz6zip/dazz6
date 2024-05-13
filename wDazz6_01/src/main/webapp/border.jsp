<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 자바 영역 클라이언트에게 받을 때 request, 줄 때 response
String irum = request.getParameter("writer");
String jemok = request.getParameter("subject");
String nai = request.getParameter("age");
String content = request.getParameter("content");

System.out.println(irum + " " + jemok + " " + nai + " " + content);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- html 영역 -->
작성자 : <%=irum %><br />
제목 : <%=jemok %><br />
</body>
</html>