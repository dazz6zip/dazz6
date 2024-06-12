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
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

if (id.equals("admin") && pwd.equals("111")) { // id, pwd DB 자료가 없으므로 임의 설정
	// 로그인에 성공했으므로 세션 생성
	session.setAttribute("adminOk", id);
	out.println("로그인 성공<br/>");
} else {
	out.println("로그인 실패<br/>");	
}
%>
<a href="javascript:window.close()">종료</a>
</body>
</html>