<%@page import="java.util.Map"%>
<%@page import="pack.product.ProductDto"%>
<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session"></jsp:useBean>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 주문</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
<style type="text/css">
.thst {
	border-radius: 10px;
	padding: 3px;
}
</style>
</head>
<body>
<h2>장바구니 목록</h2>
<%@ include file="guest_top.jsp" %>
<table>
	<tr style="background-color: #ffdddd; color: gray;">
		<th class="thst">주문상품</th><th class="thst">가격(소계)</th><th class="thst">수량</th><th class="thst">수정/삭제</th><th class="thst">조회</th>
	</tr>
	<%
	int totalPrice = 0;
//	Hashtable hCart = cartMgr.getCartList();
	Hashtable<String, OrderBean> hCart = (Hashtable<String, OrderBean>)cartMgr.getCartList();
	
	if (hCart.isEmpty()) {
		%>
		<tr>
			<td colspan="5">상품이 없습니다.</td>
		</tr>
		<%
	} else{
	/*	Enumeration enu = hCart.keys();  // Map 타입의 컬렉션 읽어서 반복처리
		while(enu.hasMoreElements()){
			OrderBean orderbean = (OrderBean)hCart.get(enu.nextElement());
			ProductDto product = productMgr.getProduct(orderbean.getProduct_no());
			int price = Integer.parseInt(product.getPrice());
			int quantity = Integer.parseInt(orderbean.getQuantity());
			int subTotal = price * quantity;  //  소계
			totalPrice += subTotal;  //  총계 */
			
		// Map.Entry를 이용하여 Map에 저장된 모든 key-value(쌍)를 각각의 key-value를 갖고 있는 하나의 객체로 얻을 수 있다.
		// getKey()와 getValue()
		for (Map.Entry<String, OrderBean> entry : hCart.entrySet()) {
			OrderBean orderBean = entry.getValue();
			ProductDto product = productMgr.getProduct(orderBean.getProduct_no());
			int price = Integer.parseInt(product.getPrice());
			int quantity = Integer.parseInt(orderBean.getQuantity());
			int subTotal = price * quantity;
			totalPrice += subTotal;
			
	%>
	<form action="cartproc.jsp" method="get">
		<tr>
			<td><%=product.getName() %></td>
			<td><%=subTotal %></td>
			<td>
				<input type="text" name="quantity" style="text-align: center;" size="5px" value="<%=quantity %>">
			</td>
			<td>
				<input style="background-color: #ffbbcc; border-style: none;" type="button" value="수정" onclick="javascript:cartUpdate(this.form)">
				<input style="background-color: #ffbbcc; border-style: none;" type="button" value="삭제" onclick="javascript:cartDelete(this.form)">
				<input type="hidden" name="flag">
				<input type="hidden" name="product_no" value="<%= product.getNo() %>">
			</td>
			<td>
				<a href="javascript:productDetail_guest('<%= product.getNo() %>')">상세보기</a>
			</td>
		</tr>
	</form>
	<%
		}
	%>
		<tr>
			<td colspan="3">
				<b>총 결제 금액 : <%=totalPrice %>원</b>
			</td>
			<td colspan="2">
				<a href="orderproc.jsp" style="color: #ffbbcc; background-color: gray; padding: 5px;"><b>주문하기</b></a>
			</td>
		</tr>
	
	<%		
	}
	%>	
	</table>
<%@ include file="guest_bottom.jsp" %>

<form action="productdetail_g.jsp" name="detailFrm">
	<input type="hidden" name="no">
</form>
</body>
</html>