package dazz6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Test01 extends JFrame implements ActionListener {

	JTextField num1, num2;
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdo1, rdo2, rdo3, rdo4;
	JLabel result;
	JButton btn1, btn2, btn3;

	public Test01() {
		super("계산기");

		layoutInput();

		setBounds(200, 200, 300, 300);
		setVisible(true);

		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn3) {
			close();
		}
		
		if (num1.getText().equals("") || num2.getText().equals("")) {
			result.setText("빈칸 없이 입력해 주세요");
			num1.requestFocus();
			return;
		}

		double n1, n2, res;
		try {
			n1 = Double.parseDouble(num1.getText());
			n2 = Double.parseDouble(num2.getText());
			res = 0;
		} catch (Exception e2) {
			result.setText("숫자만 입력 가능합니다");
			num1.requestFocus();
			return;
		}

		if (e.getSource() == btn1) {
			if (rdo1.isSelected()) {
				res = n1 + n2;
			} else if (rdo2.isSelected()) {
				res = n1 - n2;
			} else if (rdo3.isSelected()) {
				res = n1 * n2;
			} else if (rdo4.isSelected()) {
				res = n1 / n2;
			}
			result.setText("결과 : " + res);
		} else if (e.getSource() == btn2) {
			num1.setText("");
			num2.setText(""); 
			rdo1.setSelected(true);
		}
	} 

	private void close() {
		int i = JOptionPane.showConfirmDialog(this, "종료 확인", "종료하시겠습니까?", JOptionPane.OK_CANCEL_OPTION);
		switch (i) {
		case JOptionPane.OK_OPTION:
			System.exit(0);
		case JOptionPane.CANCEL_OPTION: 
			break;
		}
	}

	private void layoutInput() {
		setLayout(new GridLayout(5, 3));

		// 1행
		JLabel label1 = new JLabel("숫자 1 : ");
		num1 = new JTextField("", 20);
		JPanel panel1 = new JPanel();
		panel1.add(label1);
		panel1.add(num1);
		add(panel1);

		// 2행
		JLabel label2 = new JLabel("숫자 2 : ");
		num2 = new JTextField("", 20);
		JPanel panel2 = new JPanel();
		panel2.add(label2);
		panel2.add(num2);
		add(panel2);

		// 3행
		JLabel label3 = new JLabel("연산선택 : ");
		rdo1 = new JRadioButton("+");
		rdo2 = new JRadioButton("-");
		rdo3 = new JRadioButton("*");
		rdo4 = new JRadioButton("/");
		buttonGroup.add(rdo1);
		buttonGroup.add(rdo2);
		buttonGroup.add(rdo3);
		buttonGroup.add(rdo4);
		JPanel panel3 = new JPanel();
		panel3.add(label3);
		panel3.add(rdo1);
		panel3.add(rdo2);
		panel3.add(rdo3);
		panel3.add(rdo4);
		add(panel3);

		// 4행
		result = new JLabel("결과 : ");
		add(result);
		num1.requestFocus();

		// 5행
		btn1 = new JButton("계산");
		btn2 = new JButton("초기화");
		btn3 = new JButton("종료");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		JPanel panel4 = new JPanel();
		panel4.add(btn1);
		panel4.add(btn2);
		panel4.add(btn3);
		add(panel4);

	}

	public static void main(String[] args) {
		new Test01();
	}

}

/*
 * package pack7gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ex48Calculator extends JFrame implements ActionListener {
	private JTextField txt1, txt2;
	int num1, num2;
	private ButtonGroup rbGroup = new ButtonGroup();
	private JRadioButton rbA, rbS, rbM, rbD;
	private JLabel lblResult;
	private JButton btnCalc, btnReset, btnFin;
	
	public Ex48Calculator() {
		super("미니 계산기");
		
		calcLayout();
		
		setBounds(200, 200, 300, 400);
		setVisible(true);
		
		//super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Ex48Calculator.this, 
						"정말 종료할거야?", "종료 확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) 
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				else
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
	}
	
	private void calcLayout() {
		setLayout(new GridLayout(5,1));
		
		// 1행
		JLabel lbl1 = new JLabel("숫자1: ");
		txt1 = new JTextField("",20);
		JPanel panel1 = new JPanel();
		panel1.add(lbl1);
		panel1.add(txt1);
		add(panel1);
		
		// 2행
		JLabel lbl2 = new JLabel("숫자2: ");
		txt2 = new JTextField("",20);
		JPanel panel2 = new JPanel();
		panel2.add(lbl2);
		panel2.add(txt2);
		add(panel2);
		
		// 3행
		JLabel lbl3 = new JLabel("연산선택: ");
		rbA = new JRadioButton("+",true);
		rbS = new JRadioButton("-",false);
		rbM = new JRadioButton("*",false);
		rbD = new JRadioButton("/",false);
		rbGroup.add(rbA);
		rbGroup.add(rbS);
		rbGroup.add(rbM);
		rbGroup.add(rbD);
		
		JPanel panel3 = new JPanel();
		panel3.add(lbl3);
		panel3.add(rbA);
		panel3.add(rbS);
		panel3.add(rbM);
		panel3.add(rbD);
		add(panel3);
		
		// 4행
		lblResult = new JLabel("결과: ", JLabel.CENTER);
		add(lblResult);
		
		// 5행
		btnCalc = new JButton("계산"); 
		btnReset = new JButton("초기화");
		btnFin = new JButton("종료");
		btnCalc.addActionListener(this);
		btnReset.addActionListener(this);
		btnFin.addActionListener(this);
		
		JPanel panel5 = new JPanel();
		panel5.add(btnCalc);
		panel5.add(btnReset);
		panel5.add(btnFin);
		add(panel5);	

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCalc) {			
			// 숫자1 오류 검사
			if(txt1.getText().equals("")) {
				lblResult.setText("숫자1 입력해");
				txt1.requestFocus();
				return;
			}
			try {
				num1 = Integer.parseInt(txt1.getText());
			} catch (Exception e2) {
				lblResult.setText("숫자1 정수로 제대로 입력!!");
				txt1.requestFocus();
				return;
			}
			
			// 숫자2 오류 검사
			if(txt2.getText().equals("")) {
				lblResult.setText("숫자2 입력해");
				txt2.requestFocus();
				return;
			}
			try {
				num2 = Integer.parseInt(txt2.getText());
			} catch (Exception e2) {
				lblResult.setText("숫자2 정수로 제대로 입력!!");
				txt2.requestFocus();
				return;
			}
			
			// 계산
			int result;
			if(rbA.isSelected()) lblResult.setText("결과: " + (num1 + num2));
			else if (rbS.isSelected()) lblResult.setText("결과: " + (num1 - num2));
			else if (rbM.isSelected()) lblResult.setText("결과: " + (num1 * num2));
			else {
				if(num1==0) {
					lblResult.setText("0은 나눌 수 없어");
					txt1.setText("");
					txt1.requestFocus();
				}
				else if(num2==0) {
					lblResult.setText("0으로 나눌 수 없어");
					txt2.setText("");
					txt2.requestFocus();
				} else
				lblResult.setText("결과: " + (double)num1 / (double)num2);
			}
			
			
		} else if(e.getSource() == btnReset) {
			txt1.setText("");
			txt2.setText("");
			rbA.setSelected(true);
			rbS.setSelected(false);
			rbM.setSelected(false);
			rbD.setSelected(false);
			txt1.requestFocus();
			lblResult.setText("결과: ");
		} else {
			int result = JOptionPane.showConfirmDialog(this, "정말 종료할거야?", "종료 확인", JOptionPane.YES_NO_OPTION);
			if(result == 0) System.exit(0);
			else return;
		}		
	}

	public static void main(String[] args) {
		new Ex48Calculator();
	}
}
*/
