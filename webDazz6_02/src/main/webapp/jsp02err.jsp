<%@ page 
language="java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isErrorPage="true"
%>
<!-- isErrorPage 기본값 false / 오류시에 호출되는 jsp 파일임을 명시 -> true -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp 오류시</title>
</head>
<body>
현재 jsp 문서는 예기치 않은 에러가 발생한 경우 대처용<br/>
오류<br/>
<%= "원인 : " + exception.getMessage() %> <!-- exception : 내장 객체 -->
</body>
</html>