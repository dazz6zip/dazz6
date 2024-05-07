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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// Connection 객체는 필요할 때만 연결하고 끊는 것이 원칙이나, 상황에 따라 예외인 경우도 있음
public class DBtest05RecMove extends JFrame implements ActionListener {

	JButton btnF = new JButton("|<<");
	JButton btnP = new JButton("<");
	JButton btnN = new JButton(">");
	JButton btnL = new JButton(">>|");

	JTextField txtNo = new JTextField("", 5);
	JTextField txtName = new JTextField("", 10);
	
	TextArea ta = new TextArea("코드\t이름\t주민번호\t담당자\n");

	Connection conn;
	Statement stmt; 
	ResultSet rs, rs2;
	
	String c;

	public DBtest05RecMove() {
		super("MOVE RECORD");

		layInit();
		accDB();

		setBounds(200, 200, 400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layInit() {

		JPanel panel1 = new JPanel(); // Flow layout
		panel1.add(new JLabel("직원 정보 : "));
//		txtNo.setEditable(false); // READ ONLY, 편집 불가
		txtNo.setEnabled(false); // READ ONLY, 비활성화 (커서 인식 불가)
		txtName.setEnabled(false); // READ ONLY, 비활성화 (커서 인식 불가)
		panel1.add(txtNo);
		panel1.add(txtName);
		add("North", panel1);

		JPanel panel2 = new JPanel();
		panel2.add(btnF);
		panel2.add(btnP);
		panel2.add(btnN);
		panel2.add(btnL);
		add("Center", panel2);

		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
		
		JPanel panel3 = new JPanel();
		panel3.add(ta);
		ta.setEnabled(false);
		JScrollPane pane = new JScrollPane(ta);
		add("South", panel3);
		add("South", pane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnF) {
				rs.first();
			} else if (e.getSource() == btnP) {
				rs.previous();
			} else if (e.getSource() == btnN) {
				rs.next();
			} else if (e.getSource() == btnL) {
				rs.last();
			}

			display();
		} catch (Exception e2) {
		}

	}

	private void display() {
		try {
			txtNo.setText(rs.getString("jikwon_no"));
			txtName.setText(rs.getString("jikwon_name"));

			
			
		} catch (Exception e) {
		}
	}

	private void accDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			processDB();
		} catch (Exception e) {
			System.out.println("accDB ERROR : " + e);
		}
	}

	private void processDB() {
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			stmt = conn.createStatement();

			String sql = "SELECT jikwon_no, jikwon_name, gogek_no, gogek_name, gogek_jumin, jikwon_name from gogek RIGHT OUTER JOIN jikwon ON gogek_damsano = jikwon_no";
//			String sql2 = " WHERE jikwon_no =" + c;
			rs = stmt.executeQuery(sql);
//			rs2 = stmt.executeQuery(sql+sql2);
			
			rs.next();
			display();
		} catch (Exception e) {
			System.out.println("processDB ERROR : " + e);
		}
	}

	public static void main(String[] args) {
		new DBtest05RecMove();

	}

}
