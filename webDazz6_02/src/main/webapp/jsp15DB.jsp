<%@page import="pack.JikwonDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dbc2" class="pack.DBConnection02"></jsp:useBean>
<%
String buser = request.getParameter("buser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp15DB(jsp)</title>
</head>
<body>
[<b><%= buser %></b> 내 근무 직원]<br/>
<table border="1">
	<tr>
		<th>번호</th><th>이름</th><th>직급</th><th>성별</th>
	</tr>
	<%
	ArrayList<JikwonDTO> jlist = dbc2.getData(buser);
	
	for (JikwonDTO jik : jlist) {
		%>
		<tr>
			<td><%= jik.getNo() %></td>
			<td><%= jik.getName() %></td>
			<td><%= jik.getJik() %></td>
			<td><%= jik.getGen() %></td>
		</tr>
		<%
	}
	%>
</table>
<b>총 <%= jlist.size() %>명</b><br/>
최대 연봉 : <%= dbc2.MAX %>만 원<br/>최소 연봉 : <%= dbc2.MIN %>만 원
</body>
</html>