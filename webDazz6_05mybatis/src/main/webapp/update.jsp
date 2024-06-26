<%@page import="pack.business.DataDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="pd" class="pack.business.ProcessDAO"></jsp:useBean>

<%
String code = request.getParameter("code");

DataDTO dto = pd.selectPart(code);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>상품 수정 (MyBatis)</h2>
<form action="updateok.jsp" method="post">
	<input type="hidden" name="code" value="<%= dto.getCode() %>">
	코드 : <%= dto.getCode() %><br/>
	품명 : <input type="text" name="sang" value="<%= dto.getSang() %>"><br/>
	수량 : <input type="text" name="su" value="<%= dto.getSu() %>"><br/>
	단가 : <input type="text" name="dan" value="<%= dto.getDan() %>"><br/>
	<input type="submit" value="수정">
	<input type="button" onclick="history.back()" value="이전">
</form>
</body>
</html>