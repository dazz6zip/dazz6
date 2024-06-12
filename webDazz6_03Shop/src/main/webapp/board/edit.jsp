<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr"></jsp:useBean>
<jsp:useBean id="dto" class="pack.board.BoardDTO"></jsp:useBean>

<%
String num = request.getParameter("num");
String spage = request.getParameter("page");

dto = boardMgr.getData(num); // 수정할 자료 읽기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnUpOk").onclick = () => {
//		alert("a");
		if (frm.pass.value === "") {
			frm.pass.focus();
			alert("비밀번호를 입력해 주세요.");
			return;
		}
		
		if (confirm("수정하시겠습니까?")) {
			frm.submit();
		}
	}
}
</script>
</head>
<body>
<h2 style="text-align: center;">글 수정</h2>
<form action="editsave.jsp" method="post" name="frm">
<input type="hidden" name="num" value="<%= num %>">
<input type="hidden" name="page" value="<%= spage %>">
	<table border="1">
		<tr>
			<td width="100">이름</td>
			<td>
				<input type="text" name="name" value="<%= dto.getName() %>">
			</td>
		</tr>
		<tr>
			<td>암호</td>
			<td>
				<input type="text" name="pass">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
				<input type="text" name="mail" value="<%= dto.getMail() %>">
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input type="text" name="title" value="<%= dto.getTitle() %>">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="10" name="cont" style="width: 100%"><%= dto.getCont() %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="등록" id="btnUpOk">&emsp;
				<input type="button" value="목록" onclick="location.href='boardlist.jsp?page=<%= spage %>'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>