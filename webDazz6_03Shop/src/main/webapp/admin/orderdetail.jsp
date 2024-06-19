<%@page import="pack.product.ProductDto"%>
<%@page import="pack.order.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr"></jsp:useBean>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>
<%
OrderBean order = orderMgr.getOrderDetail(request.getParameter("no"));
ProductDto product = productMgr.getProduct(order.getProduct_no());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리 (관리자)</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
<style>
.tdmenu {
	background-color: gray;
	color: white;
	border-radius: 0;
	padding: 1px;
}
</style>
</head>
<body>
<h2>주문 관리 - 세부내역</h2>
<%@ include file="admin_top.jsp" %>
<form action="orderproc_admin.jsp" name="detailFrm" method="post">
<table>
	<tr>
		<td class="tdmenu">고객 아이디</td><td><%= order.getId() %></td>
		<td class="tdmenu">주문 번호</td><td><%= order.getNo() %></td>
		<td class="tdmenu">상품 번호</td><td><%= product.getNo() %></td>
		<td class="tdmenu">상품명</td><td><%= product.getName() %></td>
	</tr>
	<tr>
		<td class="tdmenu">상품 가격</td><td><%= product.getPrice() %></td>
		<td class="tdmenu">주문 수량</td><td><%= order.getQuantity() %></td>
		<td class="tdmenu">재고 수량</td><td><%= product.getStock()%></td>
		<td class="tdmenu">주문일</td><td><%= order.getSdate() %></td>
	</tr>
	<tr>
		<td colspan="4">
			총 결제 금액 : <%= Integer.parseInt(order.getQuantity()) * Integer.parseInt(product.getPrice()) %>
		</td>
		<td colspan="4">
			주문 상태 :
			<select name="state">
				<option value="1">접수 완료</option>
				<option value="2">입금 확인</option>
				<option value="3">배송 준비</option>
				<option value="4">배송 중</option>
				<option value="5">처리 완료</option>
			</select>
			<script type="text/javascript">
				document.detailFrm.state.value = <%= order.getState() %>;
				
			</script>
		</td>
	</tr>
	<tr>
		<td colspan="8">
			<input type="hidden" name="no" value="<%= order.getNo() %>">
			<input type="hidden" name="flag">
			<input style="background-color: #dddddd; border-style: none;" type="button" value="수정" onclick="orderUpdate(this.form)">
			<input style="background-color: #dddddd; border-style: none;" type="button" value="삭제" onclick="orderDelete(this.form)">
		</td>
	</tr>
</table>
</form>
<%@ include file="admin_bottom.jsp" %>
</body>
</html>