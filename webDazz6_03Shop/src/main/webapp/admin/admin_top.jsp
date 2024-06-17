<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String adminId = (String)session.getAttribute("adminOk");
// adminOk 이름 부여 : boardcontent.jsp 에서 함

if(adminId == null) { // 세션을 읽어오지 못함 : 로그인하지 않음
	response.sendRedirect("adminlogin.jsp");
//	return; // response.sendRedirect() 구문에서 이미 이동하기 때문에 가독성을 위해 작성, 생략 가능
}
%>
<style>
td {
	border-radius: 10px;
	padding: 3px;
}
</style>
<table>
	<tr style="background-color: #dddddd; text-align: center;">
		<td><a href="../guest/guest_index.jsp">홈페이지</a></td>
		<td><a href="adminlogout.jsp">로그아웃</a></td>
		<td><a href="membermanager.jsp">회원관리</a></td>
		<td><a href="productmanager.jsp">상품관리</a></td>
		<td><a href="ordermanager.jsp">주문관리</a></td>
	</tr>
</table>