<%@page import="pack.product.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2>쇼핑몰</h2>
<%@ include file="guest_top.jsp" %>
<table>
	<tr>
		<th>상품명</th><th>가격</th><th>재고</th><th>상세보기</th>
	</tr>
	<%
	ArrayList<ProductDto> plist = productMgr.getProductAll();
	for (ProductDto p : plist) {
		%>
		<tr>
			<td>
				<img src="../upload/<%= p.getImage() %>" width="120px">
				<br/><i style="color: gray;"><%= p.getName() %></i>
			</td>
			<td><%= p.getPrice() %></td>
			<td><%= p.getStock() %></td>
			<td>
				<a href="javascript:productDetail_guest('<%= p.getNo() %>')">보기</a>
			</td>
		</tr>
		<%
	}
	%>
</table>
<%@ include file="guest_bottom.jsp" %>
<form action="productdetail_g.jsp" name="detailFrm" method="post">
	<input type="hidden" name="no">
</form>
</body>
</html>