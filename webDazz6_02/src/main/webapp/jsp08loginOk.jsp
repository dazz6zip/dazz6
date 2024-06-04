<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp08loginOk(jsp)</title>
</head>
<body>
로그인 성공했을 때 <p>
<table border="1">
	<tr>
		<td colspan="2">해당 페이지로 이동</td>
	</tr>
	<%
	String id = (String)session.getAttribute("id");
	// jsp08login.jsp 를 거치지 않고 해당 jsp 단독 실행도 가능하기 때문에 아이디 확인 if문 생성
	if(id.equals("admin")) {
	%>
		<tr style="background-color: gray; color: white;">
			<td><%= id %></td>
			<td><a href="https://daum.net">관리자 화면</a></td>
		</tr>
	<%
	} else if (id.equals("user")) {
	%>
	<tr style="color: gray;">
		<td><%= id %></td>
		<td><a href="https://naver.com">사용자 화면</a></td>
	</tr>
	<%
	}
	String pwd = (String)session.getAttribute("pwd");
	%>
</table>
</body>
</html>