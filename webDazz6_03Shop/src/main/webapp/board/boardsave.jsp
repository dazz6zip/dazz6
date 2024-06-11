<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />

<%
System.out.println(bean.getName());
System.out.println(bean.getMail());

bean.setBip(request.getRemoteAddr());
bean.setBdate();

int newNum = boardMgr.currentMaxNum() + 1;
bean.setNum(newNum);
bean.setGnum(newNum);
boardMgr.saveData(bean);

response.sendRedirect("boardlist.jsp?page=1"); // 추후 페이징 처리를 위해 미리 값 넣어 줌 (page=1)
%>