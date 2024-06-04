<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8"); // 내장객체 - request : 클라이언트로부터의 요청 처리

String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
// 지역변수

if (!(id.equals("aa") && pwd.equals("11"))) {
	// id가 aa, pwd가 11이 아니면 jsp04member.html 로 돌아감
	response.sendRedirect("jsp04member.html"); // 내장객체 - response : 클라이언트로 결과 출력
}

String[] names = request.getParameterValues("name");
String job = request.getParameter("job");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>object</title>
</head>
<body>
<h4>전송된 입력 자료 확인</h4>
아이디 : <%= id %><br/>
이름 : <%= names[0] + ", 닉네임 : " + names[1] %><br/>
직업 : <% // 다른 비지니스 로직 작업 중...
out.println(job); // 내장객체 - out : 출력 스트림 객체 
// 계속해서 여러 실행문 작성 가능
%><hr/>
<div style="font-size: 80%; opacity: 70%">
<b>기타 정보</b><br/>
<!-- request -->
client ip : <%= request.getRemoteAddr() %><br/>
client domain : <%= request.getRemoteHost() %><br/>
protocol : <%= request.getProtocol() %><br/>
method : <%= request.getMethod() %><br/>
Server domain : <%= request.getServerName() %><br/>
<br/><!-- response -->
Server BufferSize : <%= response.getBufferSize() %><br/>
Server CharacterEncoding = <%= response.getCharacterEncoding() %><br/>
<br/><!-- etc -->
context path : <%= application.getContextPath() %><br/>
<!-- 현재 실행 중인 페이지의 외부 환경 정보 관련 객체 -->
<!-- 내장 객체 application -->
session : <%= pageContext.getSession() %><br/>
<!-- 내장 객체 pageContext -->
<br/>
+) session, page, config, exception
</div>
</body>
</html>







