<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String msg = request.getParameter("msg");
// request : 내장 객체

out.println("msg : " + msg);
%>