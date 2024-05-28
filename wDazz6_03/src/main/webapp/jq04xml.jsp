<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>

<sangpums>
<%
// sangdata 테이블을 읽어 XML 형식으로 출력
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url = "jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "123");
	pstmt = conn.prepareStatement("SELECT * FROM sangdata");
	rs = pstmt.executeQuery();
	
//	rs.next();
//	out.println(rs.getString("sang"));
	while (rs.next()) {
%>
		<sangpum>
			<code><%= rs.getString("code") %></code>
			<sang><%= rs.getString("sang") %></sang>
			<su><%= rs.getString("su") %></su>
			<dan><%= rs.getString("dan") %></dan>
		</sangpum>
<%
	}

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
</sangpums>