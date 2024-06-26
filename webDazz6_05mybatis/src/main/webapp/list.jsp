<%@page import="pack.business.DataDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="pd" class="pack.business.ProcessDAO"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
<h2>상품 목록 (MyBatis)</h2>
<% 
ArrayList<DataDTO> list = (ArrayList<DataDTO>)pd.selectDataAll(); 
request.setAttribute("list", list); // 아래 forEach구문에서 items를 굳이 %{}로 호출하고 싶을 경우 
%>
<a href="ins.html">상품 추가</a><br/>
코드를 클릭하면 삭제, 품명을 클릭하면 수정됩니다.
<table border="1">
	<tr>
		<th>코드</th><th>품명</th><th>수량</th><th>단가</th>
	</tr>
	<c:forEach var="s" items="<%= list %>"> <!-- ${list}는 request에서 받아와야 하므로 jsp 태그 사용 -->
		<tr>
			<td><a href="delete.jsp?code=${s.code}">${s.code}</a></td>
			<td><a href="update.jsp?code=${s.code}">${s.sang}</a></td>
			<td>${s.su}</td>
			<td>${s.dan}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>