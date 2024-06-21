<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el_1.jsp</title>
</head>
<body>
<h2>EL(Expression Language)</h2>
<b><i>JSP 표현식을 대신해 속성값을 쉽게 출력하기 위한 언어</i></b><br/>
출력, 반복 처리를 <b>태그 기반</b>으로 제공<br/>
웹 디자인과 프로그래머의 <b>업무 분리</b>가 가능<br/>

<%
if (request.getParameter("userName") == null) {
%>
	<jsp:forward page="el_1.html"></jsp:forward>
<%
}
%>
<hr/>
<table style="width: 100%">
<tr><th colspan="3">사용자가 전송한 userName 출력</th></tr>
<tr><td>1-1</td><td>jsp scriptlet 사용</td><td><% out.println(request.getParameter("userName")); %></td></tr>
<tr><td>1-2</td><td>jsp 표현식 사용</td><td><%= request.getParameter("userName") %></td></tr>
<tr><td>2</td><td>EL 내장객체 <i>param</i> 사용</td><td>${param.userName}</td></tr>
</table>
</body>
</html>