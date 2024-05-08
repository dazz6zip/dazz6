package debug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// 개발자가 작업 도중 임의의 값이나 흐름 확인을 목적으로 디버깅 작업하기
public class DebugTest01 extends JFrame implements ActionListener {

	int cou = 0, tot = 0;
	JButton btn = new JButton("CLICK");
	JTextField txtA = new JTextField();

	public DebugTest01() {
		add("North", txtA);
		add("Center", btn);
		btn.addActionListener(this);

		setBounds(200, 200, 300, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		for (int i = 0; i < 5; i++) {
//			cou++;
//			System.out.println(cou); // 변수 값 확인 1 : console로 출력
//			tot += cou;
//		}
//		System.out.println("tot : " + tot);
//
//		// 변수 값 확인 2 : MessageDialog
//		JOptionPane.showMessageDialog(this, cou);
//
//		// 변수 값 확인 3 : 임의의 컴포넌트로 출력
//		txtA.setText("합 : " + String.valueOf(tot));
		
		// 변수 값 확인 4 : 디버깅 도구 사용
		
	}

	public static void main(String[] args) {
		new DebugTest01();
	}

}
