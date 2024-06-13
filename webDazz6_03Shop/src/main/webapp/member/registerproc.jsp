<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8"); 
%>
<jsp:useBean id="mbean" class="pack.member.MemberBean"></jsp:useBean>
<jsp:setProperty property="*" name="mbean"/> <%-- 받아온 값을 MemberBean의 모든 해당 멤버필드에 치환 (setter) --%>
<jsp:useBean id="memberMgr" class="pack.member.memberMgr"></jsp:useBean>
<%
boolean b = memberMgr.memberInsert(mbean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(b) { // b == true : INSERT 작업에 성공
	out.println("<b>회원가입을 축하합니다.</b><br/>");
	out.println("<a href='login.jsp'>로그인</a>");
} else {
	out.println("<b>회원가입 실패</b><br/>");
	out.println("<a href='register.jsp'>가입 재시도</a>");
}
%>
</body>
</html>