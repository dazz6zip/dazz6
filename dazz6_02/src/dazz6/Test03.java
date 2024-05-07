package dazz6;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

// 키보드로 부서 번호를 입력받아 해당 부서에 근하는 직원 자료 출력
// 사번 이름   부서 직급  연봉
// 1  홍길동 총무부 부장 8900
// 건수: *

public class Test03 {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public Test03() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "123");
			stmt = conn.createStatement();

			// 키보드로 부서 이름 받기
			System.out.print("부서번호 입력 : ");
			Scanner sc = new Scanner(System.in);
			int buser = sc.nextInt();

			// 건수 카운트를 위한 변수 선언
			int cou = 0;

			// 원하는 결과를 위한 SQL 명령
			String sql = "SELECT jikwon_no AS 사번, jikwon_name AS 이름, buser_name AS 부서, jikwon_jik AS 직급, jikwon_pay AS 연봉 FROM jikwon INNER JOIN buser ON buser_num = buser_no WHERE buser_num = "
					+ buser;

			// 작성한 SQL 명령 호출
			rs = stmt.executeQuery(sql);
			System.out.println("사번\t이름\t부서\t직급\t연봉");

			// 반복문을 사용하여 결과 전부 출력
			while (rs.next()) {
				String s = rs.getString("사번");
				String n = rs.getString("이름");
				String b = rs.getString("부서");
				String j = rs.getString("직급");
				String y = rs.getString("연봉");
				System.out.println(s + "\t" + n + "\t" + b + "\t" + j + "\t" + y);
				cou++;
			}

			// 건수 출력
			System.out.print("건수 : " + cou);

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		} finally { // rs, stmt, conn이 열려 있을 경우 close할 수 있도록 if문
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
		new Test03();

	}

}
