<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />

<%
String spage = request.getParameter("page");
String num = request.getParameter("num");
String pass = request.getParameter("pass");

boolean b = boardMgr.checkPass(Integer.parseInt(num), pass); 
// 비밀번호 비교
// 같으면 삭제 가능, 같지 않으면 삭제 불가

if (b) {
	boardMgr.delData(num);
	response.sendRedirect("boardlist.jsp?page=" + spage);
} else {
	%>
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.back();
	</script>
	<%
}
%>