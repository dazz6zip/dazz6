<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>


[
<%
// jikwon 테이블을 읽어 JSON 형식으로 출력
Connection conn = null;
PreparedStatement pstmt = null;
PreparedStatement pstmt2 = null;
ResultSet rs = null;
ResultSet rs2 = null;

String bname = request.getParameter("bname");

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url = "jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "123");
	pstmt = conn.prepareStatement("SELECT * FROM jikwon INNER JOIN buser ON buser_num = buser_no WHERE buser_name LIKE ?");
	pstmt.setString(1, bname + "%");
	rs = pstmt.executeQuery();

	String result = "";

	while (rs.next()) {
		result += "{";
		result += "\"jikwon_no\":\"" + rs.getString("jikwon_no") + "\",";
		result += "\"jikwon_name\":\"" + rs.getString("jikwon_name") + "\",";
		result += "\"jikwon_jik\":\"" + rs.getString("jikwon_jik") + "\",";

		// 담당 고객 수 구하기
		String sql = "SELECT COUNT(*) AS cou FROM jikwon INNER JOIN gogek ON jikwon_no = gogek_damsano WHERE jikwon_no = ?";
		pstmt2 = conn.prepareStatement(sql);
		pstmt2.setString(1, rs.getString("jikwon_no"));
		rs2 = pstmt2.executeQuery();
		rs2.next();
		result += "\"jikwon_gogek\":\"" + rs2.getString("cou") + "\"";
		result += "},";
	}

	if (result.length() > 0) {
		result = result.substring(0, result.length() - 1);
	}
	out.print(result);
} catch (Exception e) {
	System.out.println("ERROR : " + e);
} finally {
	try {
		rs.close();
		rs2.close();
		pstmt.close();
		pstmt2.close();
		conn.close();
	} catch (Exception e2) {

	}
}
%>
]
