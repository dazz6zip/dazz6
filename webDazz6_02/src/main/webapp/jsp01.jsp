<!-- protected void service(...) {
	아래 내용은 소스 코드를 열어 보면 이 영역 내에 기술됨
} -->
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
<!-- 출력용 자바 : jsp, 내부 처리용 자바 : Servlet -->
<h1>JSP의 이해</h1>

<% // scriptlet(스크립트릿) : 자바 코드를 입력할 수 있는 영역
String irum = "한국인";
// service 메소드 내에 선언된 지역변수

System.out.println(irum); 
// 서버 컴의 표준출력장치(console)로 출력
// 개발자가 보기 위한 무언가를 출력하려고 할 때 사용

out.println(irum + " 님의 홈페이지");
// out -> PrintWriter 객체 타입의 내장객체 중 하나
// 클라이언트 브라우저(html)로 출력
%>
<br/>
<!-- expression (표현식), 출력문 한 개만 적음, 출력문 뒤 세미콜론(;) 사용하지 않음  -->
<%= irum + " 님" %>
<hr/>
<h1>자바</h1>
<h2>자바</h2>
<h3>자바</h3>
<h4>자바</h4>
<h5>자바</h5>
<h6>자바</h6>

<%
for (int i = 1; i < 7; i++) {
	out.print("<h" + i + ">");
	out.print("자바 파이팅");
	out.println("</h" + i + ">");
}
%>
현재 날짜 및 시간 <%= new Date() %>
<br/>
<%
int a = 0;
int sum = 0;

do {
	a++;
	sum += a;
} while (a < 10);
out.println("10까지의 합 : " + sum);
%>
<br/>
<%= "10까지의 합 : " + sum %>

<hr/>
<%= irum + " 님 전화번호 : " + junhwa %>

<%! // 선언
// 전역변수 (멤버필드)
String junhwa = "111-1111"; 

// 클래스 멤버 메소드
public int dataAdd(int su1, int su2) {
	return su1 + su2;
}
%>

<%
// 에러 : 메소드 내에서 메소드 쓰기 불가능
/*
public int dataAdd(int su1, int su2) {
	return su1 + su2;
}
*/
%>
<br/>
<%= dataAdd(10, 20) %>
</body>
</html>