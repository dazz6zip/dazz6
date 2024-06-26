<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="df" class="pack.business.DataForm"></jsp:useBean>
<jsp:setProperty property="*" name="df"/>
<jsp:useBean id="pd" class="pack.business.ProcessDAO"></jsp:useBean>

<% 
pd.insData(df);
response.sendRedirect("list.jsp");
// forwarding 금지!!
%>