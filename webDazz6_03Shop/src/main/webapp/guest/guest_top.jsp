<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String memid = (String)session.getAttribute("idKey");
String log = "";

if (memid == null) {
	// session을 읽어오는 데 실패 -> 로그인 상태가 아님
	log = "<a href='../member/login.jsp'>로그인</a>";
} else {
	// session을 읽어오는 데 성공 -> 로그인 상태
	log = "<a href='../member/logout.jsp'>로그아웃</a>";	
}

String mem = "";

if (memid == null) {
	// session을 읽어오는 데 실패 -> 로그인 상태가 아님
	mem = "<a href='../member/register.jsp'>회원가입</a>";
} else {
	// session을 읽어오는 데 성공 -> 로그인 상태
	mem = "<a href='../member/memberupdate.jsp'>회원수정</a>";	
}
%>

<table>
	<tr style="background-color: #ffbbcc; text-align: center;">
		<td class="menu"><%= log %></td>
		<td class="menu"><%= mem %></td>
		<td class="menu"><a href="productlist.jsp">상품목록</a></td>
		<td class="menu"><a href="cartlist.jsp">장바구니</a></td>
		<td class="menu"><a href="orderlist.jsp">구매목록</a></td>
		<td class="menu"><a href="../board/boardlist.jsp">게시판</a></td>
	</tr>
</table>