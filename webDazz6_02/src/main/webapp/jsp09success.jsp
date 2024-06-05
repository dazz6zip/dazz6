<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 세션 읽기
HttpSession ses = request.getSession(false);
// false : 세션이 있으면 읽고, 없어도 만들지 않음

if (ses != null && ses.getAttribute("userId") != null) {
	String userid = (String)ses.getAttribute("userId");
	// jsp09login.jsp에서 세션 안에 넣어 준 값 받기
	// 주의: 여기서 if문을 닫으면 html p 태그 안에서 userid 변수를 호출할 수 없음
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp09success(jsp)</title>
</head>
<body>
<h2>로그인 성공 페이지</h2>
<p><%= userid + " 님 환영합니다." %></p><hr/>
<i style="font-size: 80%; opacity: 70%">인증에 성공한 경우 처리할 작업<br/>
Authorization(인가) 작업<br/>
쇼핑, 게시판, 방명록, 회의 참여...</i><hr/>
<a href="jsp09logout.jsp">로그아웃</a>
</body>
</html>
<%
} else {
	// 로그인 없이 호출된 경우
	response.sendRedirect("jsp09sessionLogin.html");
}
%>