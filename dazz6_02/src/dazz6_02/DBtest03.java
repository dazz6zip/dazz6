package dazz6_02;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DBtest03 extends JFrame implements ActionListener {

	JButton btnA = new JButton("ALL");
	JButton btnM = new JButton("M");
	JButton btnF = new JButton("F");
	TextArea txtResult = new TextArea();

	Connection conn;
	Statement stmt;
	ResultSet rs;

	public DBtest03() {
		setTitle("Consumer list");

		layInit();
		accDB();

		setBounds(200, 200, 300, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit() {
		JPanel panel1 = new JPanel();
		panel1.add(btnA);
		panel1.add(btnM);
		panel1.add(btnF);
		
		txtResult.setEditable(false); // READ ONLY
		JScrollPane pane = new JScrollPane(txtResult);
		
		add("Center", pane);
		add("North", panel1);
		
		btnA.addActionListener(this);
		btnM.addActionListener(this);
		btnF.addActionListener(this);
	}

	private void accDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("accDB ERROR : " + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// DB 연결은 필요시에만 접속하고, 작업이 끝나면 반드시 연결을 해제해야 함
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			stmt = conn.createStatement();
			
			String sql = "SELECT gogek_no, gogek_name, gogek_jumin FROM gogek";
			
			if (e.getSource() == btnA) {
				
			} else if (e.getSource() == btnM) {
				sql = sql + " WHERE gogek_jumin LIKE '%-1%'";
			} else if (e.getSource() == btnF) {
				sql = sql + " WHERE SUBSTR(gogek_jumin, 8, 1) = 2";
			}
	
			txtResult.setText(null);
			
			rs = stmt.executeQuery(sql);
			int count = 0;
			txtResult.setText("고객번호\t고객명\t주민번호\n");
			
			while (rs.next()) {
				String imsi = rs.getString("gogek_no") + "\t\t" + rs.getString("gogek_name") + "\t" + rs.getString("gogek_jumin") + "\n";
				txtResult.append(imsi);
				count++;
			}
			txtResult.append("인원수" + count);
			
		} catch (Exception e2) {
			System.out.println("actionperformed ERROR : " + e);
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
		new DBtest03();
	}

}
