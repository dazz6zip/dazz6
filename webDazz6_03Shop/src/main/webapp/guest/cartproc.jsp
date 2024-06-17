<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session"></jsp:useBean>
<%-- 장바구니 데이터는 session에 저장해 두기 때문에 session이 살아 있는 동안 유효해야 함. 그로 인해 scope 값은 session으로 지정 --%>
<jsp:useBean id="order" class="pack.order.OrderBean"></jsp:useBean>
<jsp:setProperty property="*" name="order"/>

<%
String orderFlag = request.getParameter("flag"); // 구매 목록 보기, 수정, 삭제 판단하기 위한 용도
String id = (String)session.getAttribute("idKey");
out.print(order.getProduct_no() + ", 주문 수량 : " + order.getQuantity());
%>