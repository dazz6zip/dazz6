<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
String buser = request.getParameter("buser");

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
// System.out.println(buser);

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url = "jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "123");
	
	String sql = "SELECT jikwon_no, jikwon_name FROM jikwon INNER JOIN buser ON buser_num = buser_no WHERE buser_name = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, buser);
	rs = pstmt.executeQuery();

	String result = "";

	while (rs.next()) {
		result += "(" + rs.getString("jikwon_no") + " " + rs.getString("jikwon_name") + ")";
	}
	/*
	if (result.length() > 0) {
		result = result.substring(0, result.length() - 1);
	}
	*/
	out.print("<ele>");
	out.print("<make>" + result + "</make>");
	out.print("</ele>");
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
