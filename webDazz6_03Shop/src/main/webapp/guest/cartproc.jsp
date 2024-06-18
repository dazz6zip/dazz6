<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session"></jsp:useBean>
<%-- 장바구니 데이터는 session에 저장해 두기 때문에 session이 살아 있는 동안 유효해야 함. 그로 인해 scope 값은 session으로 지정 --%>
<jsp:useBean id="order" class="pack.order.OrderBean"></jsp:useBean>
<jsp:setProperty property="*" name="order"/>

<%
String orderFlag = request.getParameter("flag"); // 구매 목록 보기, 수정, 삭제 판단하기 위한 용도
String id = (String)session.getAttribute("idKey");
// out.print(order.getProduct_no() + ", 주문 수량 : " + order.getQuantity());

if (id == null) {
	response.sendRedirect("../member/login.jsp"); // 회원 로그인 안 한 경우
} else {
	if (orderFlag == null) {
		order.setId(id); // order : id, quantity, product_no
		cartMgr.addCart(order); // 카트에 주문 상품 담기
		%>
		<script>
			alert("상품을 장바구니에 담았습니다.");
			location.href = "cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기
		</script>
		<%
		
	} else if (orderFlag.equals("update")) {
		order.setId(id);
		cartMgr.updateCart(order);
		%>
		<script>
			alert("장바구니 내 수량을 수정했습니다.");
			location.href = "cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기
		</script>
		<%
	} else if (orderFlag.equals("del")) {
		cartMgr.deleteCart(order);
		%>
		<script>
			alert("상품을 삭제했습니다.");
			location.href = "cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기
		</script>
		<%
	}
}
%>