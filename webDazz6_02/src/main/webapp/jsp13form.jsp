<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
// String name = request.getParameter("name"); // 기존 방법
%>
<jsp:useBean id="bean" class="pack.ExamBean"></jsp:useBean>
<!-- <jsp:setProperty property="name" name="bean"/> // 수신 값 갯수가 적을 때 사용
... // 갯수가 많을 때는 property에 * 사용 -->
<jsp:setProperty property="*" name="bean"/>
<!-- 수신된 값이 자동으로 ExamBean의 멤버필드로 들어감 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp13form(jsp)</title>
</head>
<body>
FormBean에 등록된 자료 출력하기<br/>
이름 : <jsp:getProperty property="name" name="bean"/><br/>
국어 : <jsp:getProperty property="kor" name="bean"/>점, 
영어 : <jsp:getProperty property="eng" name="bean"/>점, 
수학 : <jsp:getProperty property="mat" name="bean"/>점<br/>
<jsp:useBean id="process" class="pack.ExamProcess"></jsp:useBean>
<jsp:setProperty property="bean" name="process" value="<%= bean %>"/>
총점 : <jsp:getProperty property="tot" name="process"/>
평균 : <jsp:getProperty property="avg" name="process"/>
</body>
</html>