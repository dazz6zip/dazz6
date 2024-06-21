<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	isELIgnored="false" %>
	<%-- isELIgnored : 기본값 false, true면 EL을 무시함 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el_2.jsp</title>
</head>
<body>
<h2>EL 연산자 / 내장객체 경험</h2>
<table>
<tr><th colspan="3" style="background-color: gray; color: white; padding: 3px;">1. 연산자</th></tr>
<tr><th colspan="3" style="background-color: #aaaaaa; padding: 3px;">1-1. 일반연산자</th></tr>
<tr><td>\${3 + 4}</td><td>:</td><td>${3 + 4}</td></tr>
<tr><td>\${5 / 4}</td><td>:</td><td>${5 / 4}</td></tr><%--, ${5 div 4} --%>
<tr><td>\${5 % 4}</td><td>:</td><td>${5 % 4}, ${5 mod 4}</td></tr>
<tr><th colspan="3" style="background-color: #aaaaaa; padding: 3px;">1-2. 비교연산자</th></tr>
<tr><td>\${5 > 4}</td><td>:</td><td>${5 > 4}, ${5 gt 4}, ${5 lt 4}</td></tr>
<tr><td>\${5 >= 4}</td><td>:</td><td>${5 >= 4}, ${5 ge 4}, ${5 le 4}</td></tr>
<tr><th colspan="3" style="background-color: #aaaaaa; padding: 3px;">1-3. 논리연산자</th></tr>
<tr><td>\${5 > 4 and 3 > 2}</td><td>:</td><td>${5 > 4 and 3 > 2}, ${5 > 4 or 3 > 2}</td></tr>
<tr><td>\${5 > 4 ? 10 : 10 + 5}</td><td>:</td><td>${5 > 4 ? 10 : 10 + 5}</td></tr>
<tr><th colspan="3" style="background-color: gray; color: white; padding: 3px;">2. 내장 객체</th></tr>
<%
request.setAttribute("aa", "air");
session.setAttribute("bb", "burger");
application.setAttribute("cc", "cat");
%>
<tr><th colspan="3" style="background-color: #aaaaaa; padding: 3px;">2-1. 생존 범위 관련 내장객체 출력</th></tr>
<tr><td>\${requestScope.aa}, \${aa}</td><td>:</td><td>${requestScope.aa}, ${aa}</td></tr>
<tr><td>\${sessionScope.bb}, \${bb}</td><td>:</td><td>${sessionScope.bb}, ${bb}</td></tr>
<tr><td>\${applicationScope.cc}, \${cc}</td><td>:</td><td>${applicationScope.cc}, ${cc}</td></tr>
<tr><td>jsp의 header</td><td>:</td><td><%= request.getHeader("host") %></td></tr>
<tr><td>EL의 header</td><td>:</td><td>${header.host}, ${header["host"]}</td></tr>
<tr><th colspan="3" style="background-color: #aaaaaa; padding: 3px;">2-2. 컬렉션 객체 값 출력</th></tr>
<%
ArrayList<String> list = new ArrayList<String>();
list.add("치킨버거");
list.add("새우버거");
list.add("불고기버거");
request.setAttribute("list", list);

ArrayList<String> list2 = new ArrayList<String>();
list2 = (ArrayList<String>)request.getAttribute("list");
%>
<tr><td>jsp로 출력</td><td>:</td><td><%= list2.get(0) %>, <%= list2.get(1) %></td></tr>
<tr><td>EL로 출력</td><td>:</td><td>${list[0]}, ${list[1]}</td></tr>
<tr><th colspan="3" style="background-color: #aaaaaa; padding: 3px;">2-3. HTML 문서 자료 받기</th></tr>
<tr><td><a href="el_2.html">el_2.html 호출</a></td><td>:</td><td>${param.irum} (${param["irum"]}) - ${paramValues.sung[0]}, ${paramValues.sung["1"]}</td></tr>

</table>
</body>
</html>