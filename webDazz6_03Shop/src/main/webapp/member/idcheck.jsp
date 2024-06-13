<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.memberMgr"></jsp:useBean>
<%
String id = request.getParameter("id");

// id 중복을 확인하는 작업
boolean b = memberMgr.idCheckProcess(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 검색</title>
<link href="../css/board.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
</head>
<body style="text-align: center; margin-top: 30px;">
	<b><%= id %></b>
	<%
	if (b) { // b == true : 이미 DB에 해당 아이디가 있음 (사용 불가(중복) 아이디)
		%>
		은(는) 이미 사용 중인 아이디입니다.<p/>
		<a href="#" onclick="opener.document.regForm.id.focus(); window.close();">닫기</a>
		<%
	} else { // b == false : DB에 해당 아이디가 없음 (사용 가능 아이디)
		%>
		은(는) 사용 가능한 아이디입니다.<p/>
		<a href="#" onclick="opener.document.regForm.passwd.focus(); window.close();">닫기</a>
		<%
	}
	%>
</body>
</html>