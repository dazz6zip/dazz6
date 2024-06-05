<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% // 로직용 jsp 파일
// 로그인 결과에 따라 지정된 파일로 이동하도록 함

String id = request.getParameter("id");
String pwd = request.getParameter("password");

// Authentication(인증)
// 실제로는 DB 정보를 읽어 확인해야 하지만 실습에서는 임의로 아이디와 비밀번호 설정
String validId = "ok";
String validPwd = "123";

if (id != null && pwd != null && id.equalsIgnoreCase(validId) && pwd.equals(validPwd)) {
	// 인증에 성공하면 들어오는 if문
	HttpSession ses = request.getSession(); // 세션 생성
	ses.setAttribute("userId", id); // session id를 클라이언트 컴 쿠키에 저장
	response.sendRedirect("jsp09success.jsp"); // jsp09success.jsp로 이동
/*	이동할 때 값을 같이 넘기고 싶을 경우 String이면 Redirect도 가능
	String이 아니라면 (ex. ArrayList) Forward */
} else {
	// 인증에 실패하면 들어오는 else문
	out.println("<html><body>");
	out.println("<h3>로그인 실패</h3>");
	out.println("<a href='jsp09sessionLogin.html'>다시 시도</a>");
	out.println("</body></html>");
}
%>