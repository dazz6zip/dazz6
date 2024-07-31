<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
{"jikwon":
[

<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url = "jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "123");
	pstmt = conn.prepareStatement("SELECT jikwon_no, jikwon_name, buser_name, jikwon_jik FROM jikwon INNER JOIN buser ON jikwon.buser_num = buser.buser_no");
	rs = pstmt.executeQuery();
	
	String result = "";
	
	while (rs.next()) {
		if (result != "") {
			result += ",";
		}
		result += "{";
		result += "\"no\":\"" + rs.getString("jikwon_no") + "\",";
		result += "\"name\":\"" + rs.getString("jikwon_name") + "\",";
		result += "\"bname\":\"" + rs.getString("buser_name") + "\",";
		result += "\"jik\":\"" + rs.getString("jikwon_jik") + "\"";
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