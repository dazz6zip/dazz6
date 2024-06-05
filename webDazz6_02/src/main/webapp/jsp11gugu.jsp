<%@page import="pack.Gugudan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp11gugu(jsp)</title>
</head>
<body>
기존 방법 사용<br/>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
out.println(dan + "단 출력<br/>");

// Gugudan gugudan = new Gugudan(); // 클래스의 포함관계 // 객체가 요청 수만큼 생성됨
Gugudan gugudan = Gugudan.getInstance(); // 싱글톤 패턴

int[] re = gugudan.computeGugu(dan);

for (int a = 0; a < 9; a++) {
	out.println(dan + " * " + (a + 1) + " = " + re[a] + "<br/>");
}
%>
<hr/>
jsp 액션 태그 중 useBean 사용<br/>
<jsp:useBean id="gugu" class="pack.Gugudan" scope="page"></jsp:useBean> 
<!-- 빈즈 사용, Gugudan gugu = new Gugudan(); 과 같은 의미 -->
<%-- scope =
page(기본값) : 페이지 내에서 지역변수처럼 사용 (매번 객체 생성)
Request : http 요청을 WAS가 받아서 웹 브라우저에게 응답할 때까지 변수가 유지되는 경우 사용 (매번 객체 생성)
Session : 웹 브라우저 별로 변수가 관리되는 경우 사용 (객체 1회 생성)
Application : 웹 어플리케이션이 시작되고 종료될 때까지 변수가 유지되는 경우 사용 (객체 1회 생성)
--%>
<%
int[] re2 = gugudan.computeGugu(dan);

for (int a = 0; a < 9; a++) {
	out.println(dan + " * " + (a + 1) + " = " + re2[a] + "<br/>");
}
%>
</body>
</html>