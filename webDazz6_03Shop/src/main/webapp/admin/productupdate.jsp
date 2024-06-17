<%@page import="pack.product.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>
<%
ProductDto dto = productMgr.getProduct(request.getParameter("no"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2>상품 수정</h2>
<%@ include file="admin_top.jsp" %>
<form action="productproc.jsp?flag=update" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td class="proinsertmenu">상품명</td>
			<td style="text-align: left;"><input type="text" name="name" value="<%= dto.getName() %>"></td>
		</tr>
		<tr>
			<td class="proinsertmenu">가격</td>
			<td style="text-align: left;"><input type="text" name="price" value="<%= dto.getPrice() %>"></td>
		</tr>
		<tr>
			<td class="proinsertmenu">설명</td>
			<td style="text-align: left;">
				<textarea rows="5" style="width: 99%" name="detail"><%= dto.getDetail() %></textarea>
			</td>
		</tr>
		<tr>
			<td class="proinsertmenu">재고량</td>
			<td style="text-align: left;"><input type="text" name="stock" value="<%= dto.getStock() %>"></td>
		</tr>
		<tr>
			<td class="proinsertmenu">이미지</td>
			<td style="text-align: left;">
				<img src="../upload/<%= dto.getImage() %>" width="50px">
				<input type="file" name="image" size="30">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br/>
				<input type="hidden" name="no" value="<%= dto.getNo() %>">
				<input type="submit" value="등록">
				<input type="reset" value="초기화" onclick="history.back()">
			</td>
		</tr>
	</table>
</form>
<%@ include file="admin_bottom.jsp" %>
</body>
</html>