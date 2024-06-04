<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 비지니스 로직 처리용 jsp 파일로 출력에는 참여하지 않음

String data = request.getParameter("data");
String msg = "Mr. " + data;

// 1. redirect 방식으로 파일 호출
// response.sendRedirect("jsp06invoked.jsp?data=" + msg);
// response.sendRedirect("jsp06invoked.jsp?data=" + msg + "&data2=" + msg2);

// 2. forward 방식으로 파일 호출
request.setAttribute("datas", msg);

ArrayList<String> list = new ArrayList<String>();
list.add("mouse");
list.add("pen");
list.add("book");

request.setAttribute("product", list);
// request는 컨테이너 기능을 가지고 있음

// request.getRequestDispatcher("jsp06invoked.jsp").forward(request, response);
// 위 구문을 jsp:forward 태그로 대체할 수 있음
%>
<jsp:forward page="jsp06invoked.jsp"></jsp:forward>