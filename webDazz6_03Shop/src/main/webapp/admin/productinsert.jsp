<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
<style>
.proinsertmenu {
	padding: 5px;
	background-color: gray;
	color: white;
}
</style>
</head>
<body>
<h2>상품 등록</h2>
<%@ include file="admin_top.jsp" %>
<form action="productproc.jsp?flag=insert" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td class="proinsertmenu">상품명</td>
			<td style="text-align: left;"><input type="text" name="name"></td>
		</tr>
		<tr>
			<td class="proinsertmenu">가격</td>
			<td style="text-align: left;"><input type="text" name="price"></td>
		</tr>
		<tr>
			<td class="proinsertmenu">설명</td>
			<td style="text-align: left;">
				<textarea rows="5" style="width: 99%" name="detail"></textarea>
			</td>
		</tr>
		<tr>
			<td class="proinsertmenu">재고량</td>
			<td style="text-align: left;"><input type="text" name="stock"></td>
		</tr>
		<tr>
			<td class="proinsertmenu">이미지</td>
			<td style="text-align: left;"><input type="file" name="image" size="30"></td>
		</tr>
		<tr>
			<td colspan="2">
				<br/>
				<input type="submit" value="등록">
				<input type="reset" value="초기화">
			</td>
		</tr>
	</table>
</form>
<%@ include file="admin_bottom.jsp" %>
</body>
</html>