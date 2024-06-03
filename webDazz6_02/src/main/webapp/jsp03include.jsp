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
<h1>include 연습</h1>
<b><i>include 지시어</i></b><br/>여러 jsp 파일에서 공통으로 사용할 부분을 별도 파일로 작성한 뒤 필요할 때 포함하여 사용
<pre>
본
문
</pre>
<%@ include file="jsp03bottom.jsp" %>
</body>
</html>