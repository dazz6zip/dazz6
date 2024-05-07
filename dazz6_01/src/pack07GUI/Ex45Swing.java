package pack07GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// SWING
// AWT 기술을 기반으로 순수 자바 언어로 만들어진 라이브러리
// 모든 AWT 기능 + 추가된 풍부하고 화려한 고급 컴포넌트
// AWT 컴포넌트에 J자가 덧붙여진 이름의 클래스
// 그 외 J 자로 시작하는 클래스
// javax.swing 패키지
// Swing 컴포넌트는 경량 컴포넌트(Light weight components)

public class Ex45Swing extends JFrame implements ActionListener {
	JLabel lblShow;
	int count = 0;

	public Ex45Swing() {
		setTitle("Practice Swing");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); // TOP, LEFT, BOTTOM, RIGHT 여백

		JButton button = new JButton("Click(C)");
		button.setMnemonic(KeyEvent.VK_C); // Mneminic Key (swing only) : alt + c
		button.addActionListener(this);
		panel.add(button);

		lblShow = new JLabel("Count(Button Click) : 0");
		panel.add(lblShow); 

		add(panel, BorderLayout.CENTER);
//		add("Center", panel); // 위와 같은 결과를 도출함

		setBounds(200, 200, 300, 300);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 종료 (JFrame)
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count += 1;
		lblShow.setText("Count(Button Click) : " + count);
	}

	public static void main(String[] args) {
		new Ex45Swing();
	}

}
