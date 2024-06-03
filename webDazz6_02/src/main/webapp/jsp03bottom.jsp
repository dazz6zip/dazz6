<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalTime"%>
<!-- 위 import는 호출할 파일에 넣어 주고 해당 파일에서 생략 권장 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 독립으로 실행할 파일이 아니기 때문에 호출할 파일에서 겹치는 지시문 생략 가능 -->
<hr/>
바닥글<br/>
<%
LocalTime sigan = LocalTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
// 포맷 준비

out.println(sigan.format(formatter));
%>