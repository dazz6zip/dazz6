<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
import="java.sql.*, java.util.*"
session="true"
buffer="8kb"
autoFlush="true"
isThreadSafe="true"
info="jsp 문서 정보 기술"
errorPage="jsp02err.jsp"
%>
<!-- session 기본값 true, 생략 가능 / false일 경우 해당 jsp에서 session 사용 불가능 -->
<!-- buffer 기본값 8kb, 생략 가능 / 버퍼 사이즈 조정 가능 -->
<!-- autoFlush, isThreadSafe 기본값 true, 생략 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
<h2>page 지시어 관련</h2>
페이지 지시어 : jsp 문서 맨앞에 꼭 필요한 것만 적는다
<hr/>
날짜 출력<br/>
<%
LocalDate localdate = LocalDate.now(ZoneId.of("Asia/Seoul"));
int year = localdate.getYear();
int month = localdate.getMonthValue();
int day = localdate.getDayOfMonth();

out.print("오늘은 " + year + "년 " + month + "월 " + day + "일입니다.");
%>
<br/>
<%= this.getServletInfo() %> <!-- 위 지시어에서 기술한 정보 가져오기 -->
<br/>
<%
int num1 = 20; // 외부에서 주어지는 값이라고 가정함
int num2 = 5;
out.print("나누기 결과 : " + (num1 / num2));

%>
</body>
</html>