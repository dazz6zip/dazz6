<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session"></jsp:useBean>
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr"></jsp:useBean>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>
<%
Hashtable hCart = cartMgr.getCartList();

Enumeration enu = hCart.keys();
if (hCart.isEmpty()) {
	%>
	<script>
		alert("주문 내역이 없습니다.");
		location.href = "orderlist.jsp";
	</script>	
	<%
} else {
	while (enu.hasMoreElements()) {
		OrderBean orderBean = (OrderBean)hCart.get(enu.nextElement());
		orderMgr.insertOrder(orderBean); // 주문 정보 DB 저장
		productMgr.reduceProduct(orderBean); // 주문 처리 후 재고량 계산
		cartMgr.deleteCart(orderBean);
	}
	%>
	<script>
		alert("주문이 완료되었습니다.\n감사합니다.");
		location.href = "orderlist.jsp";
	</script>	
	<%
}
%>