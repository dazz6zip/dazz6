<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 입력 장소를 전송받아 INSERT(SQL) 하는 비지니스 로직용 jsp
// 출력용이 아니기 때문에 서블릿 사용 가능

request.setCharacterEncoding("utf-8");
// String sang = request.getParameter("sang"); // 기존 방식
%>
<jsp:useBean id="spb" class="pack.SangpumBean"></jsp:useBean>
<jsp:setProperty property="*" name="spb"/>

<jsp:useBean id="dbc3" class="pack.DBConnection03"></jsp:useBean> <%-- DB 연결용 /
jsp16insert.html에서 script로 입력자료검사 완료된 값이 넘어오지만,
단독으로 해당 jsp 호출할 수 있으므로 수신된 자료가 있는지 검증 필요 (생략) --%>
<%
// INSERT 작업
dbc3.insertData(spb); 

// 상품 목록 보기로 이동
response.sendRedirect("jsp16paging.jsp");

// 주의 : 추가, 수정, 삭제 후 목록 보기 할 때에는 forwarding 금지
// request.getRequestDispatcher("jsp16paging.jsp").forward(request, response); // 사용 금지!
// forward 사용할 경우 주소가 바뀌지 않기 때문에 (jsp16insert.jsp 유지) 새로고침 할 때마다 INSERT 실행됨
%>