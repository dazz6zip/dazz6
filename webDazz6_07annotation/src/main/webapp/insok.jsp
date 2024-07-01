<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="dfb" class="pack.business.DataFormBean" />
<jsp:setProperty property="*" name="dfb" />
<jsp:useBean id="pd" class="pack.business.ProcessDAO" />

<%
boolean b = pd.insertData(dfb);

if(b) {
   response.sendRedirect("list.jsp");	
}else {
   response.sendRedirect("fail.jsp");	
}
%>