<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp05called</title>
</head>
<body>
서버(Servlet)에 의해 호출된 파일<br/>
<%
// redirect 방식
String mydata = request.getParameter("data");
out.println("전송된 data(redirect) : " + mydata);

// forward 방식
String ourdata = (String)request.getAttribute("datas");
// request는 object 타입으로 받기 때문에 String으로 casting
out.println("<br/>전송된 data(forward) : " + ourdata);
%>
</body>
</html>