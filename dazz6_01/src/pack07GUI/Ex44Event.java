package pack07GUI;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ex44Event extends Frame implements ActionListener {

	private Button btn1 = new Button("button1");
	private Button btn2 = new Button("button2");
	private Button btn3 = new Button("button3");
	private Button btn4 = new Button("button4");
	private Button btn5 = new Button("button5");

	public Ex44Event() { 
		super("이벤트 연습");
		addLayout();
		setBounds(200, 200, 400, 400);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void addLayout() {
		add("East", btn1); // Frame은 BorderLayout이 기본
		add("West", btn2);
		add("Center", btn3);
		add("South", btn4);
		add("North", btn5);

		btn1.addActionListener(this); // 방법 1 : 현재 클래스의 implements ActionListener 사용
		btn2.addActionListener(this);
		btn3.addActionListener(new EventTest()); // 방법 2 : 내부 클래스의 implements ActionListener 사용
		btn4.addMouseListener(new EventTest2()); // 방법 3 : 내부 클래스의 extends MouseAdapter 사용
		btn5.addMouseListener(new MouseAdapter() { // 방법 4 : 내부 무명클래스 MouseEvent 사용
			@Override
			public void mouseClicked(MouseEvent e) {
				// btn5을 클릭하면 수행되는 메소드
				setTitle("Click (Button 5) with CASE 4");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// btn1, btn2를 클릭하면 수행되는 메소드
//		System.out.println(e.getActionCommand());
//		System.out.println(e.getSource());
		if (e.getSource() == btn1) { // 객체 비교
			setTitle("Click (Button 1) with CASE 1");
		} else if (e.getSource() == btn2) {
			setTitle("Click (Button 2) with CASE 1");
		}

	}

	class EventTest implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// btn3을 클릭하면 수행되는 메소드
			setTitle("Click (Button 3) with CASE 2");
		}
	}

	class EventTest2 extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// btn4을 클릭하면 수행되는 메소드
			setTitle("Click (Button 4) with CASE 3");
		}
	}

	public static void main(String[] args) {
		// 이벤트 처리 정리
		new Ex44Event();

	}

}
