<%@page import="pack.SangpumDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// Connection conn = null; // 기존 방법
%>
<jsp:useBean id="dbc" class="pack.DBConnection"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp14DB(jsp)</title>
</head>
<body>
<h2>상품 정보 (jsp beans 사용)</h2>
<table border="1">
	<tr>
		<th>코드</th>
		<th>상품</th>
		<th>수량</th>
		<th>단가</th>
	</tr>
	<%
	ArrayList<SangpumDTO> list = dbc.getDataAll();
	// for (SangpumDTO s : list) {
	list.forEach((s) -> {
	%>
	<tr>
		<td><%= s.getCode() %></td>
		<td><%= s.getSang() %></td>
		<td><%= s.getSu() %></td>
		<td><%= s.getDan() %></td>
	</tr>
	<%
	});
	%>
</table>
</body>
</html>