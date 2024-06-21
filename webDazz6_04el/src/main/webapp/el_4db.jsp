<%@page import="pack.SangpumDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dbc" class="pack.DBConnection"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el_4db.jsp</title>
</head>
<body>
<h2>상품 정보</h2>
<h4>Beans : 전통적 방법으로 출력</h4>
<table border="1">
	<tr>
		<th>코드</th><th>상품</th><th>수량</th><th>단가</th>
	</tr>
	<%
	ArrayList<SangpumDTO> list = dbc.getDataAll();
	for(SangpumDTO s : list) {
		%>
		<tr>
			<td><%= s.getCode() %></td>
			<td><%= s.getSang() %></td>
			<td><%= s.getSu() %></td>
			<td><%= s.getDan() %></td>
		</tr>
		<%
	}
	%>
</table>
<hr/>
<h4>Beans : EL, JSTL로 출력</h4>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ArrayList<SangpumDTO> list2 = dbc.getDataAll(); %>
<table border="1">
	<tr>
		<th>코드</th><th>상품</th><th>수량</th><th>단가</th>
	</tr>
	<c:forEach var="s" items="<%= list2 %>">
	<tr>
		<td>${s.code}</td> <%-- 여기서 code : getCode 부르는 것 --%>
		<td>${s.sang}</td>
		<td>${s.su}</td>
		<td>${s.dan}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>