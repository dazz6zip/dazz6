package dazz6;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;

public class Test02 extends JFrame implements ActionListener {

	JTextField name, kor, mat, eng;
	JLabel prs, ars, srs, trry;
	JPanel im;
	JButton ok, re;
	String ss = "";
	ImageIcon imageIcon;
	Image image;
	double ks, es, ms, tot, ssa;

	public Test02() {
		super("성적 출력");

		input();

		setBounds(200, 200, 500, 300);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void input() {
		setLayout(new GridLayout());

		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("이름 : ");
		name = new JTextField("", 8);
		panel1.add(label1);
		panel1.add(name);
		add(panel1);

		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("국어 : ");
		kor = new JTextField("", 8);
		JLabel label3 = new JLabel("영어 : ");
		eng = new JTextField("", 8);
		JLabel label4 = new JLabel("수학 : ");
		mat = new JTextField("", 8);
		panel2.add(label2);
		panel2.add(kor);
		panel2.add(label3);
		panel2.add(eng);
		panel2.add(label4);
		panel2.add(mat);
		add(panel2);

		JPanel panel3 = new JPanel();
		prs = new JLabel("총점 :		");
		ars = new JLabel("평균 :		");
		srs = new JLabel("평가 :		");
		panel3.add(prs);
		panel3.add(ars);
		panel3.add(srs);
		add(panel3);

		JPanel panel4 = new JPanel();
		ok = new JButton("확인");
		re = new JButton("초기화");
		ok.addActionListener(this);
		re.addActionListener(this);
		panel4.add(ok);
		panel4.add(re);
		add(panel4);

		JPanel panel5 = new JPanel();
		trry = new JLabel();
		trry.setIcon(null);
		panel5.add(trry);
		add(panel5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			try {
				ks = Double.parseDouble(kor.getText());
				es = Double.parseDouble(eng.getText());
				ms = Double.parseDouble(mat.getText());
			} catch (Exception e2) {
				kor.requestFocus();
			}
			tot = ks + es + ms;
			ssa = tot / 3;

			if (ssa >= 90) {
				ss = "A";
			} else if (ssa >= 80) {
				ss = "B";
			} else if (ssa >= 70) {
				ss = "C";
			} else if (ssa >= 60) {
				ss = "D";
			} else {
				ss = "F";
			}

			prs.setText("총점 : " + tot);
			ars.setText("평균 : " + ssa);
			srs.setText("평가 : " + ss);
			
			if (ss == "A" || ss == "B") {
				trry.setIcon(new ImageIcon("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack4.jpg"));
			} else {
				trry.setIcon(new ImageIcon("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack7.jpg"));
			}
			
		} else if (e.getSource() == re) {
			name.setText("");
			kor.setText("");
			eng.setText("");
			mat.setText("");
			prs.setText("총점 : 		");
			ars.setText("평균 : 		");
			srs.setText("평가 :		");
			trry.setIcon(null);

		}
	}

	public static void main(String[] args) {
		new Test02();

	}

}