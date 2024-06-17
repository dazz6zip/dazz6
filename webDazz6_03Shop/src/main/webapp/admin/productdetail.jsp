<%@page import="pack.product.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>
<%
String no = request.getParameter("no");
ProductDto dto = productMgr.getProduct(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2>상품 상세 보기</h2>
<%@ include file="admin_top.jsp" %>
<table>
	<tr>
		<th></th><th style="background-color: gray; color: white;">정보</th><th style="background-color: gray; color: white;">설명</th>
	</tr>
	<tr>
		<td style="width: 20%">
			<img src="../upload/<%= dto.getImage() %>" width="150">
		</td>
		<td style="vertical-align: top;">
			<table style="width: 100%">
				<tr>
					<td style="width: 50%">번호 : </td><td><%= dto.getNo() %></td>
				</tr>
				<tr>
					<td>상품명 : </td><td><%= dto.getName() %></td>
				</tr>
				<tr>
					<td>가격 : </td><td><%= dto.getPrice() %></td>
				</tr>
				<tr>
					<td>등록일 : </td><td><%= dto.getSdate() %></td>
				</tr>
				<tr>
					<td>재고량 : </td><td><%= dto.getStock() %></td>
				</tr>
			</table>
		</td>
		<td style="width: 30%">
			<b>상품 설명</b><br/>
			<%= dto.getDetail() %>
		</td>
	</tr>
	<tr>
		<td colspan="3" style="text-align: center;">
			<a href="javascript:productUpdate()">수정</a>&nbsp;|
			<a href="javascript:productDelete()">삭제</a>
		</td>
	</tr>
</table>
<%@ include file="admin_bottom.jsp" %>

</body>
</html>