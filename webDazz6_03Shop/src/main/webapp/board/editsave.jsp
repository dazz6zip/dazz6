<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />

<%
String spage = request.getParameter("page");

boolean b = boardMgr.checkPass(bean.getNum(), bean.getPass()); 
// 비밀번호 비교
// 같으면 수정 가능, 같지 않으면 수정 불가

if (b) {
	boardMgr.saveEdit(bean);
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