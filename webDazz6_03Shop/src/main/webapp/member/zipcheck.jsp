<%@page import="pack.member.ZipcodeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="pack.member.memberMgr"></jsp:useBean>
<%
request.setCharacterEncoding("utf-8");
String check = request.getParameter("check");
// y or n (여부 확인)
// y 일 경우 : 동, 이름 입력 화면 출력
String dongName = request.getParameter("dongName");

// 자료가 있는지 확인하는 작업
ArrayList<ZipcodeDTO> zlist = memberMgr.zipcodeRead(dongName); // dongName을 검색한 값을 ArrayList 형식으로 담아 반환받음
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
window.onload = () => {
	document.querySelector("#btnZipFind").onclick = dongCheck;
	document.querySelector("#btnZipClose").onclick = () => {
		window.close(); // open으로 열었기 때문에 close로 닫음
	};
}

function dongCheck() {
	if (zipForm.dongName.value === "") { // 입력 자료 검사
		alert("검색할 동 이름을 입력해 주세요.");
		zipForm.dongName.focus();
		return;
	}
	zipForm.submit();
}

function sendFunc(zipcode, a1, a2, a3, a4) {
//	alert(zipcode + a1 + a2 + a3 + a4);
	// 가지고 온 값들을 register.jsp의 zipcode와 address에 넣어 주기
	// register에서 window.open으로 열었기 때문에 register가 zipcheck의 opener
	opener.document.regForm.zipcode.value = zipcode;
	
	const addr = a1 + " " + a2 + " " + a3 + " " + a4;
	opener.document.regForm.address.value = addr;
	window.close();
}
</script>
</head>
<body>
<h2 align="center">우편 자료 찾기</h2>
<form action="zipcheck.jsp" name="zipForm" method="post">
	<table>
		<tr align="center">
			<td>
			동 이름 입력 : <input type="text" name="dongName">
			<input type="button" value="검색" id="btnZipFind">
			<input type="button" value="닫기" id="btnZipClose">
			<input type="hidden" name="check" value="n">
			<%-- check 초기값은 y, 호출 뒤 n 으로 바뀌는 것 --%>
			</td>
		</tr>
	</table>
</form>
<%
if (check.equals("n")) { // check가 n 이면 이미 검색을 했다는 의미
	if (zlist.isEmpty()) { // 읽어온 zlist 값이 비어 있으면 = 해당 자료가 없으면
		%>
		<b>검색 결과가 없습니다.</b>
		<%
	} else { // 읽어온 zlist 값이 비어 있지 않으면 = 해당 자료가 있으면
		%>
		<table>
			<tr>
				<td style="text-align: center;">
					<i style="font-size: 80%; color: gray;">우편번호를 클릭하면 자동으로 주소가 입력됩니다.</i>
				</td>
			</tr>
			<tr>
				<td>
				<%
					for (int i = 0; i < zlist.size(); i++) {
						ZipcodeDTO dto = (ZipcodeDTO)zlist.get(i);
						String zipcode = dto.getZipcode();
						String area1 = dto.getArea1();
						String area2 = dto.getArea2();
						String area3 = dto.getArea3();
						String area4 = dto.getArea4();
						if(area4 == null) {
							area4 = "";
						}
						%>
						<a href="javascript:sendFunc('<%= zipcode %>', '<%= area1 %>', '<%= area2 %>', '<%= area3 %>', '<%= area4 %>')">
						<%-- 검색 결과에서 우편번호를 선택하면 그 값을 가지고 js sendFunc 호출 --%>
						<b><%= zipcode %></b></a> : <%= area1 %> <%= area2 %> <%= area3 %> <%= area4 %>
						<br/>
						<%
					}
				%>
				</td>
			</tr>
		</table>
		<%
	}
}
%>
</body>
</html>