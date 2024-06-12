<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
function check() {
	if (frm.id.value === "" || frm.pwd.value === "") {
		alert("로그인 자료를 정확히 입력해 주세요.");
		return;
	} 
	frm.submit();
}
</script>
</head>
<body>
<form action="adminlogin.jsp" name="frm" method="post">
<table>
	<tr>
		<td>
		<% // 세션 없으면 로그인 창 띄우기
			String sessionValue = (String)session.getAttribute("adminOk");
			if (sessionValue != null) {
			%>
				<b>로그인이 완료된 상태입니다.</b><br/>
				<a href="adminlogout.jsp" onclick="check()">로그아웃</a>&nbsp;|
				<a href="javascript:window.close()">종료</a>
			<%
			} else {
			%>
				<table style="width: 100%">
					<tr>
						<td>ID : </td><td><input type="text" name="id"></td>
					</tr>
					<tr>
						<td>PWD : </td><td><input type="text" name="pwd"></td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="#" onclick="check()">로그인</a>&nbsp;|
							<a href="javascript:window.close()">종료</a>
						</td>
					</tr>
				</table>	
			<%
			}
		%>
		</td>
	</tr>
</table>
</form>
</body>
</html>