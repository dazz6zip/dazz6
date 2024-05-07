package pack07GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Ex47 extends JFrame implements ActionListener {

	JTextField txtName, txtAge;
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdoM, rdoF;
	JLabel lblResult;

	public Ex47() {
		super("Practice Event");

		layoutInit();

		setBounds(200, 200, 400, 300);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void layoutInit() {
		setLayout(new GridLayout(5, 1));

		// 1행
		JLabel lbl1 = new JLabel("이름 : ");
		txtName = new JTextField("", 20);
		JPanel panel1 = new JPanel();
		panel1.add(lbl1);
		panel1.add(txtName);
		add(panel1); // Frame에 등록

		// 2행
		JLabel lbl2 = new JLabel("나이 : ");
		txtAge = new JTextField("", 20);
		JPanel panel2 = new JPanel();
		panel2.add(lbl2);
		panel2.add(txtAge);
		add(panel2);

		// 3행
		rdoM = new JRadioButton("남성", true);
		rdoF = new JRadioButton("여성", false);
		buttonGroup.add(rdoM); // JRadioButton은 그룹화 필수
		buttonGroup.add(rdoF);
		JPanel panel3 = new JPanel();
		panel3.add(rdoM);
		panel3.add(rdoF);
		add(panel3);

		// 4행
		JButton btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		JPanel panel4 = new JPanel();
		panel4.add(btnOk);
		add(panel4);

		// 5행
		lblResult = new JLabel("결과 : ", JLabel.CENTER);
		add(lblResult);

		txtName.requestFocus(); // 해당 객체로 cursor(커서) 이동
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 입력 자료 오류 검사
		if (txtName.getText().equals("")) {
			lblResult.setText("이름을 입력해 주세요");
			txtName.requestFocus(); 
			return;
		}
		if (txtAge.getText().equals("")) {
			lblResult.setText("나이를 입력해 주세요");
			txtAge.requestFocus();
			return;
		}
		// 나이 입력값 숫자 여부 확인
		int nai = 0;
		try {
			nai = Integer.parseInt(txtAge.getText());
		} catch (Exception e2) {
			lblResult.setText("나이 입력은 정수만 가능합니다");
			txtAge.requestFocus();
			return;
		}
		
//		System.out.println(rdoM.isSelected() + " " + rdoF.isSelected()); // 라디오 버튼 선택 여부 : 선택되었으면 true, 선택되지 않았으면 false
		String gender = "";
		if (rdoF.isSelected()) {
			gender = "남성";
		} else {
			gender = "여성";
		}
		
		String msg = "결과 : " + txtName.getText() + " 님의 나이는 " + nai + "세, " + gender + "입니다";
		lblResult.setText(msg);
	}

	public static void main(String[] args) {
		new Ex47();
		
	}

}
