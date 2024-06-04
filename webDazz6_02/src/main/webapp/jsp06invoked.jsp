<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp06invoked(jsp)</title>
</head>
<body>
<h2>서버가 넘겨 준 자료 출력</h2>
<%
// redirect
String mydata = request.getParameter("data");
out.println("전송된 data(redirect) : " + mydata);

// forward
String ourdata = (String)request.getAttribute("datas");
out.println("<br/>전송된 data(forward) : " + ourdata);

ArrayList<String> plist = (ArrayList<String>)request.getAttribute("product");
out.println("<br/>전송된 list(forward) : ");
for (String p : plist) {
	out.println(p);
}
%>
</body>
</html>