<%@page import="pack.SangpumDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dbc3" class="pack.DBConnection03"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp16paging(jsp)</title>
</head>
<body>
<h2>상품 정보 (paging)</h2>
<a href="jsp16insert.html">상품 추가</a><br/>
<table border="1">
	<tr>
		<td>코드</td><td>상품명</td><td>수량</td><td>단가</td>
	</tr>
	<%
	// 페이징 처리를 위한 page 번호 받기
	String pa = request.getParameter("pa");
	if (pa == null) { // 페이지를 지정하지 않으면  
		pa = "1"; // 기본적으로 1 페이지
	}
	
	ArrayList<SangpumDTO> list = dbc3.getDataAll(pa);
	for (int i = 0; i < list.size(); i++) {
		SangpumDTO dto = (SangpumDTO)list.get(i);
		// 가독성을 위한 casting 구문 (SangpumDTO) 기재
		%>
		<tr>
			<td><%= dto.getCode() %></td>
			<td><%= dto.getSang() %></td>
			<td><%= dto.getSu() %></td>
			<td><%= dto.getDan() %></td>
		</tr>
		<%
	}
	int tpc = dbc3.prepareTotalPage(); // 전체 페이지 수 계산
	%>
	<tr>
		<td colspan="4" style="text-align: center">
			<%
			if (tpc > 0) {
				for (int pageNo = 1; pageNo <= tpc; pageNo++) {
					%>
					<a href="jsp16paging.jsp?pa=<%= pageNo %>"><%= pageNo %></a>&emsp;
					<%
				}
			}
			%>
		</td>
	</tr>
</table>
</body>
</html>