<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="pd" class="pack.business.ProcessDAO"></jsp:useBean>

<%
String code = request.getParameter("code");

boolean b = pd.delData(Integer.parseInt(code));
if (b) {
	response.sendRedirect("list.jsp");
} else {
	%>
	<script>
	alert("삭제에 실패했습니다.");
	location.href = "list.jsp";
	</script>
	<%
}
%>