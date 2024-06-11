<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 자료 수정을 위한 로직 jsp --%>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean" class="pack.SangpumBean"></jsp:useBean>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="cpg" class="pack.ConnPooling" scope="page"></jsp:useBean>

<%
if (cpg.updateDataOk(bean)) {
	response.sendRedirect("jsp17dbcp.jsp"); // 수정 후 상품 목록 보기
} else {
	response.sendRedirect("jsp17fail.html");
}
%>

