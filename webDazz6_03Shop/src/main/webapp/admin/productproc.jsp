<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="pack.product.ProductMgr"></jsp:useBean>

<%
request.setCharacterEncoding("utf-8");

// controller 역할
String flag = request.getParameter("flag");

boolean result = false;

if (flag.equals("insert")) {
 	result = productMgr.insertProduct(request);
	
}else if (flag.equals("update")) {
//	result = productMgr.updateProduct(request);
} else if (flag.equals("delete")) {
//	result = productMgr.deleteProduct(request);
} else {
	response.sendRedirect("productmanager.jsp");
}

if (result) { // INSERT에 성공한 경우
	%>
	<script>
		alert("정상적으로 처리되었습니다.");
		location.href="productmanager.jsp";
	</script>
	<%
} else { // INSERT에 실패한 경우
	%>
	<script>
		alert("오류 발생\n처리에 실패하였습니다.");
		location.href="productmanager.jsp";
	</script>
	<%
}
%>