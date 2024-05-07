package dazz6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test04 extends JFrame implements ActionListener {

	DecimalFormat fm = new DecimalFormat("###,##0");
	JLabel code, name, su, dan;
	JTextField codef, namef, suf, danf;
	JButton btn;
	JTextArea tarea = new JTextArea("코드\t상품명\t수량\t단가\t금액\n");
	int co, s, da, count, pay;
	String na, sql;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public Test04() {
		super("상품 처리");

		pro1();

		input();

		setBounds(200, 200, 500, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void pro1() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			tarea.setText("FRONT" + e);
		}
	}

	public void input() {

		JPanel panel1 = new JPanel();

		code = new JLabel("코드 : ");
		codef = new JTextField("", 5);
		name = new JLabel("상품명 : ");
		namef = new JTextField("", 5);
		su = new JLabel("수량 : ");
		suf = new JTextField("", 5);
		dan = new JLabel("단가 : ");
		danf = new JTextField("", 5);
		btn = new JButton("추가");

		panel1.add(code);
		panel1.add(codef);
		panel1.add(name);
		panel1.add(namef);
		panel1.add(su);
		panel1.add(suf);
		panel1.add(dan);
		panel1.add(danf);
		panel1.add(btn);
		add("North", panel1);

		btn.addActionListener(this);

		JPanel panel2 = new JPanel();
		panel2.add(tarea);
		tarea.setEnabled(false);

		JScrollPane pane = new JScrollPane(tarea);
		add(panel2);
		add(pane);

		showdt();
	}

	private void showdt() {
		try {
			sql = "SELECT code, sang, su, dan, su*dan AS 금액 FROM sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			count = 0;

			while (rs.next()) {
				co = rs.getInt("code");
				na = rs.getString("sang");
				s = rs.getInt("su");
				da = rs.getInt("dan");
				int pay = s * da;
				tarea.append(co + "\t" + na + "\t" + s + "\t" + da + "\t" + fm.format(pay) + "\n");
				count++;
			}
			tarea.append("건수 : " + count);
		} catch (Exception e) {
			tarea.setText("default1 ERROR : " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn) {

			if (codef == null || namef == null || suf == null || danf == null) {
				tarea.setText("공백 없이 작성 바랍니다");
			}

			try {
				co = Integer.parseInt(codef.getText());
				na = namef.getText();
				s = Integer.parseInt(suf.getText());
				da = Integer.parseInt(danf.getText());
				pay = s * da;
			} catch (Exception e2) {
				tarea.setText("입력값 오류" + e2);
			}

			try {
				sql = "INSERT INTO sangdata VALUES (?, ?, ?, ?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, co);
				pstmt.setString(2, na);
				pstmt.setInt(3, s);
				pstmt.setInt(4, da);
				pstmt.executeUpdate();

				tarea.setText(null);

				showdt();
			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(Test04.this, "ERROR", "중복", JOptionPane.CANCEL_OPTION);
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
		new Test04();
	}

}
