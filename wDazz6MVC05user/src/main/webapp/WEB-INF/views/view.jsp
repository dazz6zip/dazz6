<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="logincheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnUpdate").onclick = function() {
		if (confirm("정말 수정할까요?")) {
			frm.action = "updateform.m2";
			frm.submit();
		}
	}
	
	document.querySelector("#btnDelete").onclick = function() {
		if (confirm("정말 삭제할까요?")) {
			frm.action = "delete.m2";
			frm.submit();
		}
	}
	
	document.querySelector("#btnList").onclick = function() {
		frm.action = "list.m2";
		frm.submit();
	}
}
</script>
</head>
<body>

<h2>사용자 상세 보기</h2>
<table border="1">
	<tr>
		<td>아이디</td><td>${user.userid}</td>
		<td>비밀번호</td><td>${user.password}</td>
	</tr>
	<tr>
		<td>사용자</td><td>${user.name}</td>
		<td>이메일</td><td>${user.email}</td>
	</tr>
	<tr>
		<td colspan="4">
			<button id="btnUpdate">수정</button>
			<button id="btnDelete">삭제</button>
			<button id="btnList">목록</button>
		</td>
	</tr>
</table>

<form name="frm" method="post">
	<input type="hidden" name="userid" value="${user.userid}">
</form>
</body>
</html>