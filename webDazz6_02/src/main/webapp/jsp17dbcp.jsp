<%@page import="pack.SangpumDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cpg" class="pack.ConnPooling" scope="page"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp17dbcp(jsp)</title>
<style type="text/css">
b{
	background-color:yellow;
}
</style>
<script type="text/javascript">
function funcUp() {
	let code = prompt("수정할 코드 입력", "");
	if (code !== "" && code !== null) {
		location.href = "jsp17up.jsp?code=" + code;
	}
}
function funcDel() {
	let code = prompt("삭제할 데이터 코드 입력", "");
	if (code !== "" && code !== null) {
		if (confirm("삭제하시겠습니까?")) {
		location.href = "jsp17del.jsp?code=" + code;
		}
	}
}
</script>
</head>
<body>
<h2>상품 정보(DBCP 사용)</h2>
클라이언트와 서버 사이드인 웹 어플리케이션에 사용자의 요청에 따라 Connection이 생성된다면 수 많은 사용자가 요청을 했을 때 서버에 과부하가 걸리게 됩니다.<br/>
이러한 상황을 예방하기 위해 <b>미리 일정 갯수의 Connection을 만들어 Pool에 저장</b>하고,
사용자의 <b>요청이 발생하면 Connection을 제공</b>하고 사용자와의 <b>연결이 종료된다면 Pool에 다시 반환</b>하여 보관하는 것을 의미합니다.
<hr/>
<a href="jsp17insert.html">추가</a>&emsp;
<a href="javascript:funcUp()">수정</a>&emsp;
<a href="javascript:funcDel()">삭제</a><br/><br/>
<table border="1">
	<tr>
		<th>코드</th><th>상품명</th><th>수량</th><th>단가</th>
	</tr>
	<%
	ArrayList<SangpumDTO> slist = (ArrayList)cpg.getDataAll();
//	out.print(slist.size());
	for (SangpumDTO s : slist) {
		%>
		<tr>
			<td><%= s.getCode() %></td>
			<td><%= s.getSang() %></td>
			<td><%= s.getSu() %></td>
			<td><%= s.getDan() %></td>
		</tr>
		<%
	}
	%>
</table>
</body>
</html>
