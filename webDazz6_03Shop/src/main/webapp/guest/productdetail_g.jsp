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
<title>상세보기</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2>상품 상세보기</h2>
<%@ include file="guest_top.jsp" %>
<form action="cartproc.jsp">
<table>
	<tr>
		<th></th><th style="background-color: #ffdddd; color: gray; border-radius: 10px; padding: 3px;" >정보</th><th style="background-color: #ffdddd; color: gray; border-radius: 10px; padding: 3px;">설명</th>
	</tr>
	<tr>
		<td style="width: 30%">
			<img src="../upload/<%= dto.getImage() %>" width="100%">
		</td>
		<td style="width: 40%; vertical-align: top;">
			<table>
				<tr><td>번호</td><td><%= dto.getNo() %></td></tr>
				<tr><td>상품명</td><td><%= dto.getName() %></td></tr>
				<tr><td>가격</td><td><%= dto.getPrice() %></td></tr>
				<tr><td>등록일</td><td><%= dto.getSdate() %></td></tr>
				<tr><td>재고</td><td><%= dto.getStock() %></td></tr>
				<tr>
					<td>주문수</td>
					<td>
						<input type="number" min="1" value="1" name="quantity" style="text-align: center; width: 3cm;">
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;">
			<%= dto.getDetail() %>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<input type="hidden" name="product_no" value="<%= dto.getNo() %>">
			<input type="submit" value="장바구니 담기">
			<input type="button" value="상품 목록 보기" onclick="history.back()">
		</td>
	</tr>
</table>
</form>
<%@ include file="guest_bottom.jsp" %>
</body>
</html>