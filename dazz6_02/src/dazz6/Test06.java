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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test06 extends JFrame implements ActionListener {

	JLabel no, name;
	JTextField nof, namef;
	JButton btn;
	JTextArea result = new JTextArea("");
	JTextArea result2 = new JTextArea("직급별 연봉평균\n");

	int noi, no2;
	String nai, sql, na2, pa2, ji2, ra2, pas, pai;
	double aa, bb, cc, dd, ee;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs, rs2;

	public Test06() {

		super("직원");

		defaultload();
		inputdata();

		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void defaultload() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			result.setText("FRONT" + e);
		}
	}

	private void inputdata() {

		JPanel panel1 = new JPanel();
		no = new JLabel("직원번호 :");
		name = new JLabel("이름 : ");
		nof = new JTextField("", 5);
		namef = new JTextField("", 5);
		btn = new JButton("로그인");
		panel1.add(no);
		panel1.add(nof);
		panel1.add(name);
		panel1.add(namef);
		panel1.add(btn);
		add("North", panel1);
		btn.addActionListener(this);

		JPanel panel2 = new JPanel();
		panel2.add(result);
		add("Center", result);
		JScrollPane pane = new JScrollPane(result);
		add(pane);

		JPanel panel3 = new JPanel();
		panel3.add(result2);
		add("South", result2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {

			result.setText(null);

			try {
				noi = Integer.parseInt(nof.getText());
				nai = namef.getText();
			} catch (Exception e2) {
				result.setText("입력값 오류" + e2);
			}

			try {
				sql = "SELECT jikwon_no AS 코드, jikwon_name AS 이름 FROM jikwon WHERE jikwon_no = ? AND jikwon_name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, noi);
				pstmt.setString(2, nai);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					show1();
					show2avg();
				} else {
					JOptionPane.showConfirmDialog(Test06.this, "ERROR", "로그인 실패", JOptionPane.CANCEL_OPTION);
				}

			} catch (Exception e2) {
				result.setText("로그인 오류 : " + e2);
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
	}

	private void show2avg() {
		try {
			sql = "SELECT jikwon_jik, AVG(jikwon_pay) FROM jikwon WHERE jikwon_jik IS NOT NULL GROUP BY jikwon_jik";
			pstmt = conn.prepareStatement(sql);
			rs2 = pstmt.executeQuery();

			while (rs2.next()) {
				pai = rs2.getString(1);
				pas = rs2.getString(2);

				result2.append(pai + " : " + pas + " ");
			}

		} catch (Exception e2) {
			result.setText("ERROR" + e2);
		}

	}

	private void show1() {
		try {
			sql = "SELECT jikwon_no, jikwon_name, jikwon_pay, jikwon_jik, jikwon_rating FROM jikwon";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			result.append("사번\t이름\t연봉\t직급\t평점\n");
			while (rs.next()) {
				no2 = rs.getInt(1);
				na2 = rs.getString(2);
				pa2 = rs.getString(3);
				ji2 = rs.getString(4);
				ra2 = rs.getString(5);

				result.append(no2 + "\t" + na2 + "\t" + pa2 + "\t" + ji2 + "\t" + ra2 + "\n");
			}

		} catch (Exception e2) {
			result.setText("ERROR2" + e2);
			System.out.println(e2);
		}
	}

	public static void main(String[] args) {
		new Test06();

	}

}
