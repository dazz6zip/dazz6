package dazz6_02;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBtest02CRUD {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private Properties prop = new Properties();
	
	public DBtest02CRUD() { // secure coding의 일종으로 연결 정보 별도 저장 후 읽기
		try {
			prop.load(new FileInputStream("C:\\work\\JAVA\\dazz6_02\\src\\dazz6_02\\DBtest02.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("passwd"));
			stmt = conn.createStatement();
			
			String sql = "";
			
			// 자료 추가하기
			// DEFAULT : AUTO COMMIT
//			sql = "INSERT INTO sangdata VALUES (5, '새우깡', 55, 3000)";
//			stmt.executeUpdate(sql); // SELECT는 executeQuery, 나머지는 executeUpdate (ResultSet 안 써도 됨)
			
//			// AUTO COMMIT OFF : Transaction (SELECT 해당 안 됨)
//			conn.setAutoCommit(false); // 수동
//			sql = "INSERT INTO sangdata VALUES (6, '감자깡', 7, 3000)";
//			stmt.executeQuery(sql); // Transaction 시작
//			sql = "INSERT INTO sangdata VALUES (7, '고구마깡', 17, 2000)";
//			stmt.executeQuery(sql);
////			conn.rollback(); // Transaction 종료, 입력한 내용 취소 (마지막으로 commit한 상태로 돌아감)
//			conn.commit(); // Transaction 종료, 입력한 내용 원격 DB 저장
//			conn.setAutoCommit(true);
			
//			// 자료 수정
//			sql = "UPDATE sangdata SET sang = '데일리콤부차', su = 12, dan = 8000 WHERE code = 5";
//			stmt.executeQuery(sql);
			
			// 자료 삭제
			sql = "DELETE FROM sangdata WHERE code >= 5";
//			stmt.executeQuery(sql);
			// INSERT(0 or 1), UPDATE, DELETE는 수행 후 처리 수 만큼 행의 갯수 반환 (RETURN 값)
			int result = stmt.executeUpdate(sql);
			System.out.println("result : " + result);
			if (result == 0) {
				System.out.println("FAIL(DELETE)");
			}
			
			// 모든 자료 읽기
			sql = "SELECT * FROM sangdata ORDER BY code DESC";
			rs = stmt.executeQuery(sql);
			int cou = 0;
			while (rs.next()) {
				System.out.println(rs.getString("code") + "\t" + rs.getString("sang") + "\t\t" + rs.getString("su") + "\t" + rs.getString("dan"));
				cou++;
			}
			System.out.println("전체 자료 수 : " + cou);
		
			System.out.println("\n----\n");
			
			// 부분 자료 읽기
			sql = "SELECT * FROM sangdata WHERE code = 1";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println(rs.getString("code") + "\t" + rs.getString("sang") + "\t\t" + rs.getString("su") + "\t" + rs.getString("dan"));
			} else {
				System.out.println("NOT FOUND");
			}
			
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				
			}
		}
		
	}

	public static void main(String[] args) {
		new DBtest02CRUD();

	}

}
