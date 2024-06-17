<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>

</head>
<body>
<table>
	<tr>
		<td style="font-size: 24px;">특별 상품 전문 쇼핑몰</td>
	</tr>
</table>
<%@ include file="guest_top.jsp" %>
<table>
<%
if (memid != null) {
	%>
	<tr style="text-align: center;">
		<td>
			<img src="../images/pic2.gif"><br/>
			<b><%= memid %> 님</b> 방문을 환영합니다.
		</td>
	</tr>
	<%
} else {
	%>
	<tr>
		<td style="font-size: 24px; background-image: url(../images/pic.jpg); background-size: 100%; background-repeat: no-repeat;">
			<br/><br/><br/><br/><br/>고객님 반갑습니다.<br/>
			로그인 후 이용해 주세요.
		</td>
	</tr>
	<%
}
%>
</table>

<%@ include file="guest_bottom.jsp" %>
</body>
</html>