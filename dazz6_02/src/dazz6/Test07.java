package dazz6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Test07 {

	int no, mc, wc, pay, mp, wp;
	String name, jik, gen, sql, insertBu, tel;
	double avgPay;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public Test07() {

		defaultload();
		inputData();
		process();
	}

	private void defaultload() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			System.out.println("defaultload ERROR : " + e);
		}
	}

	private void inputData() {
		Scanner sc = new Scanner(System.in);
		System.out.print("부서명을 입력하세요 : ");
		insertBu = sc.next();
	}
	
	private void process() {
		try {
			sql = "SELECT buser_tel FROM buser WHERE buser_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertBu);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				tel = rs.getString("buser_tel");				
			}
			System.out.println(insertBu + " 전화번호는 " + tel);

		} catch (Exception e2) {
			System.out.println("proc ERROR : " + e2);
		}
		
		process1();
		
	}

	private void process1() {
		try {
			sql = "SELECT jikwon_no, jikwon_name, jikwon_jik, jikwon_gen FROM jikwon INNER JOIN buser ON buser_num = buser_no WHERE buser_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertBu);
			rs = pstmt.executeQuery();

			System.out.println("사번\t이름\t직급\t성별");

			while (rs.next()) {
				no = rs.getInt("jikwon_no");
				name = rs.getString("jikwon_name");
				jik = rs.getString("jikwon_jik");
				gen = rs.getString("jikwon_gen");
				if (gen.equals("남")) {
					mc += 1;
				} else if (gen.equals("여")) {
					wc += 1;
				}
				System.out.println(no + "\t" + name + "\t" + jik + "\t" + gen);
			}
			System.out.println("인원수는 남직원 : " + mc + "명, 여직원 : " + wc + "명");

		} catch (Exception e2) {
			System.out.println("proc1 ERROR : " + e2);
		}
		
		process2();

	}

	private void process2() {
		try {
			sql = "SELECT jikwon_gen, AVG(jikwon_pay) FROM jikwon INNER JOIN buser ON buser_num = buser_no WHERE buser_name = ? GROUP BY jikwon_gen";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertBu);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("jikwon_gen").equals("남")) {
					mp = (int) rs.getDouble("AVG(jikwon_pay)");
				} else if (rs.getString("jikwon_gen").equals("여")) {
					wp = (int) rs.getDouble("AVG(jikwon_pay)");
				}
			}
			System.out.println("연봉평균은 남직원 : " + mp + ", 여직원 : " + wp);
		} catch (Exception e2) {
			System.out.println("proc2 ERROR : " + e2);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	public static void main(String[] args) {
		new Test07();
	}
}
