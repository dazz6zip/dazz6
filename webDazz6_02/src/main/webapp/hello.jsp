<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println("클라이언트에 의해 호출");
System.out.println("서버에서 WEB-INF 영역 내 hi.jsp 호출 시도");
// 주의 : redirect 불가능, forwarding만 가능함
%>
<jsp:forward page="WEB-INF/hi.jsp"></jsp:forward>