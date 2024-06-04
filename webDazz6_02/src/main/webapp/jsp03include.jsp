<%@ page 
language="java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="java.time.format.DateTimeFormatter"
import="java.time.LocalTime"
%>
<!-- 아래 import 두 개 : 현재 jsp 에서 사용하지 않지만 jsp03bottom에서 사용하기 때문에 기재 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
<%@ include file="jsp03top.jsp" %>
<!-- include : 소스를 이 위치에 넣어서 실행 -->
<h1>include 연습</h1>
<b><i>include 지시어</i></b>
<br/>여러 jsp 파일에서 공통으로 사용할 부분을 별도 파일로 작성한 뒤 필요할 때 포함하여 사용
<pre>
본
문
</pre>
<hr style="opacity: 20%" />
<h2>include action tag로 파일 포함 결과 출력</h2>
<jsp:include page="jsp03Tag_01.jsp"></jsp:include>
<!-- include action : 실행 결과를 이 위치에 넣어서 실행 -->
<br/>작업 중...
<br/>
<div style="color: red; font-size: 30px">
	<jsp:include page="jsp03Tag_02.jsp">
		<jsp:param value="good" name="msg"/>
	</jsp:include>
<!-- include 자체는 값을 넘길 수 없음 -->
<!-- param은 include 태그에 종속적 -->
</div>
<%@ include file="jsp03bottom.jsp" %>
</body>
</html>