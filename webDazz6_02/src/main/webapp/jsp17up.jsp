<%@page import="pack.SangpumDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String code = request.getParameter("code"); %>
<jsp:useBean id="cpg" class="pack.ConnPooling"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp17up</title>
</head>
<body>
<%
SangpumDTO dto = cpg.updateData(code);

if (dto == null) {
%>
	<script type="text/javascript">
		alert("상품 코드 검색 오류\n수정이 불가능합니다.");
	//	history.back(); // 이전 페이지로
		location.href="jsp17dbcp.jsp"; // 명시적으로 호출해 주는 것이 좋음
	</script>
<%
	return;
}
%>
<h2>상품 수정</h2>
<form action="jsp17upok.jsp" method="post">
코드 : <%= dto.getCode() %><br/>
<input type="hidden" name="code" value="<%= dto.getCode() %>">
품명 : <input type="text" name="sang" value="<%= dto.getSang() %>"><br/>
수량 : <input type="text" name="su" value="<%= dto.getSu() %>"><br/>
단가 : <input type="text" name="dan" value="<%= dto.getDan() %>"><br/>
<br/><input type="submit" value="자료 수정">&nbsp;
<input type="button" value="수정 취소" onclick="javasript:location.href='jsp17dbcp.jsp'">
<%-- 명시적인 표현을 위해 javasript: 뒤에 경로를 기재함. 없어도 무관 --%>
</form>
</body>
</html>