<%@page import="pack.product.ProductDto"%>
<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="orderMgr" class="pack.order.OrderMgr"></jsp:useBean>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 목록</title>
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
<h2>주문 상품 정보</h2>
<%@ include file="guest_top.jsp" %>
<table>
	<tr style="background-color: #ffdddd; color: gray;">
		<th class="thst">주문번호</th><th class="thst">상품</th><th class="thst">수량</th><th class="thst">주문일</th><th class="thst">주문상태</th>
	</tr>
	<%
	String id = (String)session.getAttribute("idKey");
	ArrayList<OrderBean> list = orderMgr.getOrder(id);
	
	if(list.isEmpty()) {
		%>
		<tr>
			<td colspan="5"><b><%= id  %></b> 님의 주문 내역이 없습니다.</td>
		</tr>
		<%
	} else {
		for (OrderBean ord : list) {
			ProductDto product = productMgr.getProduct(ord.getProduct_no());			
			%>
			<tr>
				<td><%= ord.getNo() %></td>
				<td><%= product.getName() %></td>
				<td><%= ord.getQuantity() %></td>
				<td><%= ord.getSdate() %></td>
<%-- 			<td><%= ord.getState() %></td> --%>
				<td>
				<%
				switch (ord.getState()) {
					case "1" : {
						out.println("접수 완료"); 
						break;
					}
					case "2" : {
						out.println("입금 확인"); 
						break;
					}
					case "3" : {
						out.println("배송 준비"); 
						break;
					}
					case "4" : {
						out.println("배송 중"); 
						break;
					}
					case "5" : {
						out.println("처리 완료"); 
						break;
					}
					default : {
						out.println("접수 확인 중");
					}
				}
				%>
				</td>
			</tr>
			<%
		}
	}
	%>
	</table>
<%@ include file="guest_bottom.jsp" %>
</body>
</html>