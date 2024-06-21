<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el_3jstl.jsp</title>
</head>
<body>
<h2>JSTP(JavaServer Pages Standard Tag Library)</h2>
<b><i>Java 코드를 바로 사용하지 않고 HTML 태그 형태로 직관적인 코딩을 지원하는 라이브러리</i></b>
<ol>
	<li>Java EE 기반의 웹 애플리케이션 개발 플랫폼을 위한 컴포넌트 모음
	<li>XML 데이터 처리와 조건문, 반복문, 국제화와 지역화와 같은 일을 처리하기 위한 JSP 태그 라이브러리
	<li>자신만의 태그를 추가할 수 있는 기능을 제공
	<li>변수, 제어문 사용 가능
	<li>일반적으로 EL과 함께 사용
</ol>
<hr/>
<b>변수 처리</b><br/>
<c:set var="irum" value="이기자" scope="page"></c:set>
<%-- set : 변수 선언시 사용
scope 옵션 종류 : page, request, session, application--%>
이름 : <c:out value="${irum}"></c:out><br/>
<c:set var="ir" scope="session">공기밥</c:set><%-- value="공기밥" 과 같은 의미 --%>
이름 : <c:out value="${ir}"></c:out><br/>
<c:remove var="irum"/>
이름 : <c:out value="${irum}"></c:out><br/>
<c:remove var="ir" scope="session"/>
이름 : <c:out value="${ir}"></c:out><br/>
<%-- 
out : 출력용 (jsp의 out과 비슷한 역할)
remove : 변수 삭제, session 옵션 작성을 권장하지만 필수는 아님 --%>
<c:set var="abc" value="${header['User-Agent']}" scope="page"></c:set>
abc <i style="font-size: 70%">(header['User-Agent'] : 현재 사용 중인 브라우저 정보)</i> : <c:out value="${abc}"></c:out><br/>
<c:set var="su1" value="10"></c:set>
<c:set var="su2">20</c:set>
두 수의 합 : ${su1 + su2}<hr/>
<b>조건 판단문 if</b><br/>
<c:set var="nice" value="star"></c:set>
<c:if test="${nice == 'star'}"> <%-- ${nace eq 'star'} --%>
	nice : <c:out value="${nice}"></c:out>
</c:if><br/>
<b>조건 판단문 choose</b><br/>
<c:choose>
	<c:when test="${nice == 'moon'}">
		달 <c:out value="${nice}"></c:out>
	</c:when>
	<c:when test="${nice == 'star'}">
		별 <c:out value="${nice}"></c:out>
	</c:when>
	<c:otherwise>
		어떠한 조건도 만족하지 않은 경우
	</c:otherwise>
</c:choose><br/>
<c:choose>
	<c:when test="${empty param.myid}">
		<form>
			아이디 : <input type="text" name="myid">
			<input type="submit">
		</form>
	</c:when>
	<c:when test="${param.myid == 'admin' }">
		관리자	
	</c:when>
	<c:otherwise>
		<b><c:out value="${param.myid}"></c:out></b> 님 환영합니다.
	</c:otherwise>
</c:choose>
<hr/>
<b>반복문 forEach</b><br/>
연습 1 :
<c:forEach var="i" begin="1" end="10" step="2"> <%-- i라는 변수 : 1로 시작하고 10이 되면 멈춤, 2씩 증가 --%>
	${i}&emsp;
</c:forEach><br/>
연습 2 (구구단)<br/>
<c:forEach var="i" begin="1" end="9"> <%-- step 기본값 1 --%>
	3 * ${i} = ${3 * i}<br/>
</c:forEach><br/>
<%
HashMap<String, Object> map = new HashMap<String, Object>();
// HashMap은 추가한 순서와 상관없이 저장됨
map.put("name", "한국인");
map.put("today", new Date());
%>
<c:set var="m" value="<%= map %>"></c:set>
<c:forEach var="i" items="${m}">
	${i.key} : ${i.value}
</c:forEach><br/>
배열 생성 후 출력 : 
<c:set var="arr" value="<%= new int[]{1, 2, 3, 4, 5} %>"></c:set>
<c:forEach var="a" items="${arr}" begin="2" end="4" step="1">
	${a}&emsp;
</c:forEach><br/>
문자열 분할 후 출력 : 
<c:forTokens var="animal" items="horse,dog,cat*lion,tiger,pig" delims=",*">
	${animal}&emsp;
</c:forTokens><hr/>
<b>숫자 및 날짜 서식</b><br/>
숫자 1 : <fmt:formatNumber value="12345.678" type="number"></fmt:formatNumber><br/>
숫자 2 : <fmt:formatNumber value="12345.678" type="currency"></fmt:formatNumber><br/>
숫자 3 : <fmt:formatNumber value="0.123" type="percent"></fmt:formatNumber><br/>
숫자 4 : <fmt:formatNumber value="12345.678" type="number" pattern="#,##0"></fmt:formatNumber><br/>
숫자 5 : <fmt:formatNumber value="12345.678" pattern="#,##0.0"></fmt:formatNumber><br/>
숫자 6 : <fmt:formatNumber value="12345.678" pattern="0,000.0"></fmt:formatNumber><br/>
숫자 7 : <fmt:formatNumber value="12" pattern="0,000.0"></fmt:formatNumber><br/><br/>
<c:set var="now" value="<%= new Date() %>"></c:set>
날짜: <fmt:formatDate value="${now}" type="date"/><br/>
시간: <fmt:formatDate value="${now}" type="time"/><br/>
모두 1: <fmt:formatDate value="${now}" type="both"/><br/>
모두 2: <fmt:formatDate value="${now}" type="both" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/><br/>
</body>
</html>