<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr"></jsp:useBean>
<jsp:useBean id="dto" class="pack.board.BoardDTO"></jsp:useBean>

<%
String num = request.getParameter("num");
String spage = request.getParameter("page");

// 상세보기 화면 조회수 증가
boardMgr.updateReadCnt(num);
dto = boardMgr.getData(num);

String name = dto.getName();
String pass = dto.getPass();
String mail = dto.getMail();
String title = dto.getTitle();
String cont = dto.getCont();
String bip = dto.getBip();
String bdate = dto.getBdate();
int readcnt = dto.getReadcnt();
// out.print(name + " " + title);

String adminPass = "*****"; // 로그인하지 않으면 비밀번호 숨기기

// 회원가입 후 로그인에 성공하면 세션 생성한다고 가정하고 세션 읽기 (adminOk의 Attribute 읽음)
String adminOk = (String)session.getAttribute("adminOk");
if (adminOk != null) {
	if (adminOk.equalsIgnoreCase("admin")) {
		adminPass = pass;
		// adminPass : 세션이 있으면 비밀번호를 가짐 (없으면 *****)
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
</head>
<body style="font-family: 맑은고딕;">
<table>
	<tr>
		<td style="font-size: 80%"><b>비밀번호</b> : <%= adminPass %></td>
		<%-- 로그인하면 비밀번호가 보이고 로그인하지 않으면 ***** 이 보임 --%>
		<td colspan="2" style="text-align: right;">
			<a style="color: #0489B1" href="reply.jsp?num=<%= num %>&page=<%= spage %>">
				<img src="../images/reply.gif">
			</a>
			<a href="edit.jsp?num=<%= num %>&page=<%= spage %>">
				<img src="../images/edit.gif">
			</a>
			<a href="delete.jsp?num=<%= num %>&page=<%= spage %>">
				<img src="../images/del.gif">
			</a>
			<a href="boardlist.jsp?page=<%= spage %>">
				<img src="../images/list.gif">
			</a>
			<%-- 게시글 상세보기에서 list를 누르면 해당 게시글이 있는 페이지로 돌아갈 수 있도록 spage 값을 가지고 감 --%>
		</td>
	</tr>
	<tr>
		<td style="font-size: 80%">
			<b>작성자 : <a href="mailto:<%= mail %>"><%= name %></a></b><br/>
			<i style="color: #0489B1; font-size: 80%">ip : <%= bip %></i>
		</td>
		<td style="font-size: 80%; text-align: center;">작성일 : <%= bdate %></td>
		<td style="font-size: 80%; text-align: right;">조회수 : <%= readcnt %></td>
	</tr>
	<tr>
		<td style="width: 25%"></td>
		<td style="width: 53%; background-color: #0489B1; border-radius: 30px; padding-top 5px; padding-bottom: 5px; text-align: center;">
			<b><%= title %></b>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<textarea rows="10" style="width: 96%; border-radius: 30px; padding-left: 10px; padding-top 10px; padding-bottom: 10px; border: 2px; border-style: solid; border-color: #0489B1; text-align: center;" readonly><%= cont %></textarea>
		</td>
	</tr>
</table>
</body>
</html>