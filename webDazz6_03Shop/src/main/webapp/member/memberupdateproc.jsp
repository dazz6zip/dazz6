<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="memberBean" class="pack.member.MemberBean" scope="page" />
<jsp:setProperty property="*" name="memberBean" />
<jsp:useBean id="memberMgr" class="pack.member.memberMgr" />

<%
String id = (String)session.getAttribute("idKey");
boolean b = memberMgr.memberUpdate(memberBean, id); 

if(b){ // b == true : 업데이트 성공
%>
	<script>
	alert("수정 성공");
	location.href="../guest/guest_index.jsp";
	</script>
<%}else{ // b == true : 업데이트 실패 %>
	<script>
	alert("수정 실패\n관리자에게 문의 바람");
	history.back();
	</script>
<%	
}
%>






