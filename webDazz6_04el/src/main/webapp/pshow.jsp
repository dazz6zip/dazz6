<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.expensive {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
<h2>제품 목록</h2>
<c:choose>
	<c:when test="${empty products}">
		<p>제품이 없습니다.</p>
	</c:when>
	<c:otherwise>
		<table border="1">
			<thead>
				<tr>
					<th>제품명</th><th>가격</th><th>설명</th><th>출시일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pro" items="${products}">
					<tr>
						<td>${pro.name}</td>
						<td class='<c:if test="${pro.price >= 5000.0}">expensive</c:if>'>
							${pro.price}
						</td>
						<td>${pro.description}</td>
						<td><fmt:formatDate value="${pro.releaseDate}" pattern="yyyy년 MM월 dd일"/></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<h2>제품 통계</h2>
<c:set var="totalProducts" value="${fn:length(products)}"></c:set>
<c:set var="totalPrice" value="0"></c:set>
<c:forEach var="p" items="${products}" varStatus="status">
	<c:set var="totalPrice" value="${totalPrice + p.price}"></c:set>
</c:forEach>
<p>전체 건수 : ${totalProducts}</p>
<p>가격 평균 : <fmt:formatNumber value="${totalPrice / totalProducts}" type="currency"></fmt:formatNumber></p>
<h3>제품 설명</h3>
<ul>
	<c:forEach var="p" items="${products}">
		<li><c:out value="${fn:substring(p.description, 0, 2)}..."></c:out></li>
	</c:forEach>
</ul>
</body>
</html>

