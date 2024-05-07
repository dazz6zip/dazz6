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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test05 extends JFrame implements ActionListener {

	JLabel name, jumin, jumin2;
	JTextArea result = new JTextArea("담당직원 정보");
	JTextField namef, juminf1, juminf2;
	JButton btn;

	int co, sa;

	String sql, gn, ju1, ju2, na, bu, te, ji;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public Test05() {

		super("담당 직원 정보");

		defaultset();

		input();

		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void defaultset() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			result.setText("FRONT" + e);
		}
	}

	private void input() {
		setLayout(new GridLayout(4, 3));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

		JPanel panel1 = new JPanel();
		name = new JLabel("고객명 : ");
		namef = new JTextField("", 5);
		panel1.add(name);
		panel1.add(namef);
		add(panel1);

		JPanel panel2 = new JPanel();
		jumin = new JLabel("주민번호 : ");
		juminf1 = new JTextField("", 5);
		jumin2 = new JLabel("-");
		juminf2 = new JTextField("", 5);
		btn = new JButton("확인");
		btn.addActionListener(this);
		panel2.add(jumin);
		panel2.add(juminf1);
		panel2.add(jumin2);
		panel2.add(juminf2);
		panel2.add(btn);
		add(panel2);

		JPanel panel3 = new JPanel();
		panel3.add(result);
		result.setEnabled(false);
		JScrollPane pane = new JScrollPane(result);
		add(panel3);
		add(pane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {

			result.setText("담당직원 정보");

			gn = namef.getText();
			ju1 = juminf1.getText();
			ju2 = juminf2.getText();
			String ju3 = ju1 + "-" + ju2;

			sql = "SELECT jikwon_no, jikwon_name, buser_name, buser_tel, jikwon_jik FROM jikwon INNER JOIN buser ON buser_num = buser_no INNER JOIN gogek ON jikwon_no = gogek_damsano WHERE gogek_name = ? AND gogek_jumin = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, gn);
				pstmt.setString(2, ju3);
				rs = pstmt.executeQuery();

				result.append("\n사번\t이름\t부서명\t전화\t\t직급\n");
				while (rs.next()) {
					co = rs.getInt("jikwon_no");
					na = rs.getString("jikwon_name");
					bu = rs.getString("buser_name");
					te = rs.getString("buser_tel");
					ji = rs.getString("jikwon_jik");

					result.append(co + "\t" + na + "\t" + bu + "\t" + te + "\t" + ji + "\n");
				}

			} catch (Exception e2) {
				result.setText("ERROR");
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

	public static void main(String[] args) {
		new Test05();
	}

}
