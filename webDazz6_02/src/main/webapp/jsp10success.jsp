<%@page import="java.util.Base64"%>
<%@page import="io.jsonwebtoken.Claims"%>
<%@page import="io.jsonwebtoken.Jws"%>
<%@page import="io.jsonwebtoken.Jwts"%>
<%@page import="io.jsonwebtoken.security.Keys"%>
<%@page import="java.security.Key"%>
<%@page import="io.jsonwebtoken.JwtException"%>
<%@page import="javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// jsp09success.jsp copy
// 쿠키에서 jwt 가져와서 유효성 검사

// 세션 읽기
HttpSession ses = request.getSession(false);
// false : 세션이 있으면 읽고, 없어도 만들지 않음

Cookie[] cookies = request.getCookies();
String jwt = null;
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("jwt")) {
			jwt = cookie.getValue();
			// 쿠키에서 jwt 가져오기
			break;
		}
	}
}

if (jwt != null) {
	try {
		// 고정된 비밀 키 사용 (예제용) - 최소 256비트 길이의 비밀 키
/*		String secretKeyString = "mySuperSecretKey12345678901234567890123456789012";
		Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes()); */
		
		// 서블릿 컨텍스트에서 Base64로 인코딩된 비밀 키 가져오기  java.util.Base64
		// InitServlet.java 파일과 함께 보기
		String encodedKey = (String) getServletContext().getAttribute("secretKey");
	    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	    Key secretKey = Keys.hmacShaKeyFor(decodedKey);
			
		// jwt 유효성 검사 (이미 발급된 jwt를 검증, 검증 내용 파악)
		Jws<Claims> claims = Jwts.parserBuilder()
								.setSigningKey(secretKey) // 서명키 설정. jwt 생성시 사용된 secretKey와 일치해야 함
								.build() // parser 객체 생성
								.parseClaimsJws(jwt); // 파싱된 jwt 읽어오기
		String userid = claims.getBody().getSubject(); // Subject 클레임 반환
		// claims.getBody() : jwt의 payload를 반환
		// 유효한 경우 환영 메시지 출력
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp10success(jsp)</title>
</head>
<body>
<h2>로그인(jwt) 성공 페이지</h2>
<p><%= userid %> 님 환영합니다.</p><hr/>
<i style="font-size: 80%; opacity: 70%">인증에 성공한 경우 처리할 작업<br/>
Authorization(인가) 작업<br/>
쇼핑, 게시판, 방명록, 회의 참여...</i><hr/>
<a href="jsp10logout.jsp">로그아웃</a>
</body>
</html>
<%
	} catch (JwtException e) {
		// jwt가 유효하지 않을 경우
		response.sendRedirect("jsp10jwtLogin.html");
	}
} else {
	// jwt 없이 호출된 경우
	response.sendRedirect("jsp10jwtLogin.html");
}
%>