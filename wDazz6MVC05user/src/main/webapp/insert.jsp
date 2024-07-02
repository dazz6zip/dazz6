<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>사용자 추가</h2>
<form action="insert.m2" method="post">
아이디 : <input type="text" name="userid"><br/>
비밀번호 : <input type="text" name="password"><br/>
작성자 : <input type="text" name="name"><br/>
이메일 : <input type="text" name="email"><br/>
<input type="submit" value="추가">
<input type="button" value="목록" onclick="location.href='list.m2'">
</form>
</body>
</html>