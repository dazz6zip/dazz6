<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String code = request.getParameter("code"); %>
<jsp:useBean id="cpg" class="pack.ConnPooling" scope="page"></jsp:useBean>

<%
if (cpg.deleteData(code)) {
	response.sendRedirect("jsp17dbcp.jsp"); // 삭제 후 상품 목록 보기
} else {
	response.sendRedirect("jsp17fail.html");
}
%>