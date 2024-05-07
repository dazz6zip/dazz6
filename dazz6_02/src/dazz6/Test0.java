package dazz6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test0 extends JFrame implements ActionListener {

	JLabel no, name;
	JTextField nof, namef;
	JButton btn;
	JTextArea result = new JTextArea("");
	JTextArea result2 = new JTextArea("직급별 연봉평균");

	int noi, no2;
	String nai, sql, na2, pa2, ji2, ra2;
	double aa, bb, cc, dd, ee;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs2;

	public Test0() {

		super("직원자료");

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			result.setText("FRONT" + e);
		}

		inputdata();

		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {

			try {
				noi = Integer.parseInt(nof.getText());
				nai = namef.getText();
			} catch (Exception e2) {
				result.setText("입력값 오류" + e2);
			}

			sql = "SELECT jikwon_no, jikwon_name, jikwon_pay, jikwon_jik, jikwon_rating FROM jikwon WHERE jikwon_no = ? AND jikwon_name = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, noi);
				pstmt.setString(2, nai);
				rs = pstmt.executeQuery();

				result.append("사번\t이름\t연봉\t직급\t평점\n");
				while (rs.next()) {
					no2 = rs.getInt("jikwon_no");
					na2 = rs.getString("jikwon_name");
					pa2 = rs.getString("jikwon_pay");
					ji2 = rs.getString("jikwon_jik");
					ra2 = rs.getString("jikwon_rating");

					result.append(no2 + "\t" + na2 + "\t" + pa2 + "\t" + ji2 + "\t" + ra2 + "\n");
				}

			} catch (Exception e2) {
				result.setText("ERROR");
			}

			sql = "SELECT AVG(jikwon_pay) FROM jikwon GROUP BY jikwon_jik ORDER BY AVG(jikwon_pay) DESC ";

			try {
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery();

				result2.append("이사 : \t부장 : \t과장 : \t대리 : \t사원 : \n");
				double[] avg = { aa, bb, cc, dd, ee };
				int i = 0;
				while (rs2.next()) {
					double imsi = rs.getDouble(1);
					avg[i] = imsi;
					i = i + 1;
					result2.append("이사 : " + aa + "부장 : " + bb + "과장 : " + cc + "대리 : " + dd + "사원 : " + ee);
				}

			} catch (Exception e2) {
				result.setText("ERROR");
			}
		}
	}

	private void inputdata() {
		setLayout(new GridLayout(3, 3));

		JPanel panel1 = new JPanel();
		no = new JLabel("직원번호 :");
		name = new JLabel("이름 : ");
		nof = new JTextField("", 5);
		namef = new JTextField("", 5);
		panel1.add(no);
		panel1.add(nof);
		panel1.add(name);
		panel1.add(namef);
		panel1.add(btn);
		add(panel1);
	


	}

	public static void main(String[] args) {
		new Test0();

	}

}
