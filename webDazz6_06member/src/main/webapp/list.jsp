<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fu" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="pd" class="pack.business.ProcessDAO"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원 정보 (MyBatis)</h2>
<a href="ins.jsp">회원 추가</a>
<table border="1">
	<tr>
		<th>id</th><th>name</th><th>passwd</th><th>date</th>
	</tr>
	<% ArrayList<DataDto> list = (ArrayList<DataDto>)pd.selectDataAll(); %>
	<c:set var="list" value="<%= list %>"></c:set>
	<c:if test="${empty list}">
		<tr>
			<td colspan="4">회원 정보 없음</td>
		</tr>
	</c:if>
	
	<c:forEach var="m" items="<%= list %>">
		<tr>
			<td>${m.id}</td>
			<td>${m.name}</td>
			<td>${m.passwd}</td>
			<td>${m.reg_date}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="4" style="color: red; text-align: center;">id 클릭은 삭제, name 클릭은 수정</td>
	</tr>
</table>
</body>
</html>