<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>

{"gogek":
[

<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

request.setCharacterEncoding("UTF-8");
String gname = request.getParameter("name");
String gno = request.getParameter("no");

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url = "jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "123");
	pstmt = conn.prepareStatement("SELECT gogek_name AS name, gogek_tel AS tel, CASE SUBSTR(gogek_jumin, 8, 1)WHEN 1 THEN '남' WHEN 2 THEN '여' END AS gen FROM jikwon INNER JOIN gogek ON jikwon_no = gogek_damsano WHERE jikwon_no = ? AND jikwon_name = ?");
	pstmt.setString(1, gno);
	pstmt.setString(2, gname);
	rs = pstmt.executeQuery();

	String result = "";
	
	while (rs.next()) {
		if (result != "") {
			result += ",";
		}
		result += "{";
		result += "\"name\":\"" + rs.getString("name") + "\",";
		result += "\"tel\":\"" + rs.getString("tel") + "\",";
		result += "\"gen\":\"" + rs.getString("gen") + "\"";
		result += "}";
	}

	out.print(result);

} catch (Exception e) {
	System.out.println("ERROR : " + e);
} finally {
	try {
			rs.close();
			pstmt.close();
			conn.close();
		
	} catch (Exception e2) {
		
	}	
}

%>
]
}
