<%@page import="java.util.Base64"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.*"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.security.Keys"%>
<%@page import="java.security.Key"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
// jsp09login.jsp copy
// jwt_lib.zip jar 파일 WEB-INF/lib 폴더에 넣어 주기
// jwt_lib.zip 파일 : https://cafe.daum.net/flowlife/HqLp/36

// 로직용 jsp 파일
// 로그인 결과에 따라 지정된 파일로 이동하도록 함

String id = request.getParameter("id");
String pwd = request.getParameter("password");

// Authentication(인증)
// 실제로는 DB 정보를 읽어 확인해야 하지만 실습에서는 임의로 아이디와 비밀번호 설정
String validId = "ok";
String validPwd = "123";

if (id != null && pwd != null && id.equalsIgnoreCase(validId) && pwd.equals(validPwd)) {
	// 인증에 성공하면 들어오는 if문
	
/*	1. jwt 생성하기(비밀키와 서명, 만료시간 등 설정)
	2. 생성된 jwt를 클라이언트 스토리지(storage) 또는 쿠키(cookie)에 저장
		- 스토리지에 저장할 경우 XSS 공격에 취약함 */
	
	// 고정된 비밀 키 사용 (예제용) - 최소 256비트 길이의 비밀 키
/*	String secretKeyString = "mySuperSecretKey12345678901234567890123456789012";
	Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes()); */
	// hmacShaKeyFor : key byte array를 기반으로 적절한 HMAC 알고리즘을 적용한 Key 객체 생성
	
	// 서블릿 컨텍스트에서 Base64로 인코딩된 비밀 키 가져오기  java.util.Base64
	// InitServlet.java 파일과 함께 보기
    String encodedKey = (String) getServletContext().getAttribute("secretKey");
    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    Key secretKey = Keys.hmacShaKeyFor(decodedKey);

	
	long expirationTime = 3600000; // 3600000 : 1시간 (밀리초 단위)
	// jwt 생성 : 문자열 반환, 인증 및 권한 부여 매커니즘에서 사용
	String jwt = Jwts.builder()
				.setSubject(id) // id, 사용자 식별자, 주제 등이 포함된 클레임 설정
				.setIssuedAt(new Date()) // 클래임 내용 중 lat : 발행시간 설정
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 클래임 내용 중 exp : 만료시간 설정
				.signWith(secretKey) // 서명 알고리즘, 비밀키 설정(무결성 보장이 목적)
				.compact(); // jwt 생성
	
	// 쿠키에 jwt 저장
	Cookie jwtCookie = new Cookie("jwt", jwt); // 쿠키 생성
	jwtCookie.setHttpOnly(true);
	jwtCookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능
	response.addCookie(jwtCookie);

	response.sendRedirect("jsp10success.jsp"); // jsp09success.jsp로 이동
/*	이동할 때 값을 같이 넘기고 싶을 경우 String이면 Redirect도 가능
	String이 아니라면 (ex. ArrayList) Forward */
} else {
	// 인증에 실패하면 들어오는 else문
	out.println("<html><body>");
	out.println("<h3>로그인 실패</h3>");
	out.println("<a href='jsp10jwtLogin.html'>다시 시도</a>");
	out.println("</body></html>");
}
%>