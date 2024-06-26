<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>

{"jikwon":
[

<%

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String value = request.getParameter("hidden");
String sql = "SELECT jikwon_no AS no, jikwon_name AS name, jikwon_jik AS jik, YEAR(jikwon_ibsail) AS ibsa FROM jikwon";
String ft = "";
if (value.equals("ma")) {
    sql += " WHERE jikwon_gen = '남'";
} else if (value.equals("fma")) {
    sql += " WHERE jikwon_gen = '여'";
} else {
    sql += " ";
}

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url = "jdbc:mariadb://localhost:3306/test";
	conn = DriverManager.getConnection(url, "root", "123");
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, ft);
	rs = pstmt.executeQuery();

	String result = "";
	
	while (rs.next()) {
		if (result != "") {
			result += ",";
		}
		result += "{";
		result += "\"no\":\"" + rs.getString("no") + "\",";
		result += "\"name\":\"" + rs.getString("name") + "\",";
		result += "\"jik\":\"" + rs.getString("jik") + "\",";
		result += "\"ibsa\":\"" + rs.getString("ibsa") + "\"";
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
