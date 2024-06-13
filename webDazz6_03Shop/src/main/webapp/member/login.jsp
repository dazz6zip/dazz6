<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String id = (String)session.getAttribute("idKey");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnLogin").addEventListener("click", funcLogin);
	document.querySelector("#btnNewmember").addEventListener("click", funcNewMember);
}
</script>
</head>
<body>
<%
if (id != null) { // id를 읽었을 경우(세션이 있는 경우) : 로그인 O
	%>
<%--
	<b><%= id %></b> 님 환영합니다.<p/>
	회원 기능 사용 가능합니다.<br/> 
	<a href="logout.jsp">로그아웃</a>
--%>
	<script type="text/javascript">
	location.href="../guest/guest_index.jsp";
	</script>
	<%
} else { // id를 읽지 못했을 경우(세션이 없는 경우) : 로그인 X
	 %>
	 <form name="loginForm">
	 	<table>
	 		<tr>
	 			<td colspan="2"><b>회원 로그인</b></td>
	 		</tr>
	 		<tr>
	 			<td>아이디 : </td>
	 			<td><input type="text" name="id"></td>
	 		</tr>
	 		<tr>
	 			<td>비밀번호 : </td>
	 			<td><input type="text" name="passwd"></td>
	 		</tr>
	 		<tr>
	 			<td colspan="2">
	 				<input type="button" value="로그인" id="btnLogin">
	 				<input type="button" value="회원가입" id="btnNewmember">
	 			</td>
	 		</tr>
	 	</table>
	 </form>
	 <%
}
%>
</body>
</html>