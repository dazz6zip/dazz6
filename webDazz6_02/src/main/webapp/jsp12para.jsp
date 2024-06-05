<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// String mm = request.getParameter("message");
// action tag(setProperty, getProperty)를 사용할 경우 값 받아 주는 구문 필요없음 (태그 내부 자동처리)
%>
<jsp:useBean id="my" class="pack.Para01Class"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp12para(jsp)</title>
</head>
<body>
<i><b style="font-size: 120%">Beans 연습</b><br/>
빈즈에 값 전달</i>
<%
// 기존 방법 사용
// my.setMsg(mm);
// out.println("<br/><br/><b>메시지 출력</b> : " + my.getMsg());
%>
<br/>
<%-- 추천 방법 (Bean, setProperty, getProperty --%>
<jsp:setProperty property="msg" name="my"/>
<!-- Para01Class의 msg의 setter(setMsg)를 부르는 것 
property = html에서 호출한 키 이름(파라미터 변수명) = class의 멤버필드 변수명 -->
<br/><b>메시지 출력(action tag)</b> : <jsp:getProperty property="msg" name="my"/>
</body>
</html>