package dazz6_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBtest04 {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public DBtest04() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			stmt = conn.createStatement();
			
			boolean b = false;
			
			// execute() : executeQuery(), executeUpdate()를 하나로 처리
			
			// UPDATE
			b = stmt.execute("UPDATE sangdata SET sang = '마우스' WHERE code = 4"); // SELECT가 아니면 FALSE
			System.out.println("SELECT b : " + b);
			int result = stmt.getUpdateCount();
			System.out.println("result : " + result);
			if (result >= 1) {
				System.out.println("SUCCESS");
			} else {
				System.out.println("FAIL");
			}
			
			// SELECT
			b = stmt.execute("SELECT * FROM sangdata"); // SELECT는 TRUE
//			System.out.println("SELECT b : " + b);
			rs = stmt.getResultSet(); // execute를 사용하면 getResultSet으로 받아 준 뒤 사용
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getString(4));
			}
			
		} catch (Exception e) {
			System.out.println("DBtest04 실패 : " + e);
			return;
		}
	}

	public static void main(String[] args) {
		new DBtest04();

	}

}
