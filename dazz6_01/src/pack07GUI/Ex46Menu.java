package pack07GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.foreign.AddressLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ex46Menu extends JFrame implements ActionListener {
	JButton btnR, btnG, btnB;
	JTextArea txtArea = new JTextArea("", 10, 5); // 키보드로 여러 행 입력 가능 (Textfield는 행 하나만 가능)
	JMenuBar mBar;
	JMenuItem mnuMes, mnuOk, mnuInput;

	public Ex46Menu() {
		setTitle("Practice"); 

		addLayout(); // 버튼 관련
		addMenuLayout(); // 메뉴 관련

		setBounds(200, 200, 400, 300);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addLayout() {
		btnR = new JButton("RED");
		btnG = new JButton("GREEN");
		btnB = new JButton("BLUE");
		btnR.addActionListener(this);
		btnG.addActionListener(this);
		btnB.addActionListener(this);

		JPanel panel = new JPanel();
		panel.add(btnR);
		panel.add(btnG);
		panel.add(btnB);

		add("South", panel);
		add("Center", txtArea);
	}

	private void addMenuLayout() {
		mBar = new JMenuBar();
		JMenu menu = new JMenu("Menu Box");
		mnuMes = new JMenuItem("Message");
		mnuOk = new JMenuItem("Check");
		mnuInput = new JMenuItem("Input");
		menu.add(mnuMes); // 메뉴에 메뉴 아이템 등록
		menu.add(mnuOk);
		menu.add(mnuInput);

		JMenu menu2 = new JMenu("MENU");
		mBar.add(menu); // 메뉴바에 메뉴 등록
		mBar.add(menu2);

		setJMenuBar(mBar); // JFrame에 메뉴바 등록

		// 메뉴에 리스너 장착
		mnuMes.addActionListener(this);
		mnuOk.addActionListener(this);
		mnuInput.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnR) {
			txtArea.setBackground(Color.RED);
		} else if (e.getSource() == btnG) {
			txtArea.setBackground(Color.GREEN);
		} else if (e.getSource() == btnB) {
			txtArea.setBackground(Color.BLUE);
		} // ----- 메뉴, 내장 다이얼로그 박스 호출 -----
		else if (e.getSource() == mnuMes) {
			JOptionPane.showMessageDialog(this, "MESSAGE", "NOTICE", JOptionPane.INFORMATION_MESSAGE);
			// Modal dialogbox : 실행을 멈추고 창을 보여 줌, 창을 닫으면 처리 진행함
			System.out.println("Continue");
		} else if (e.getSource() == mnuOk) {
			int result = JOptionPane.showConfirmDialog(this, "Choose button", "Choice",
					JOptionPane.YES_NO_CANCEL_OPTION); // return값이 있음
			System.out.println(result);
			switch (result) {
			case JOptionPane.YES_OPTION:
				txtArea.append("CHOOSE YES\n");
				break;
			case JOptionPane.NO_OPTION:
				txtArea.append("CHOOSE NO\n");
				break;
			case JOptionPane.CANCEL_OPTION:
				txtArea.append("CHOOSE CANCEL\n");
				break;
			}
		} else if (e.getSource() == mnuInput) {
			String str = JOptionPane.showInputDialog(this, "INPUT DATA");
			txtArea.append(str + "\n");
		}
	}

	public static void main(String[] args) {
		// 메뉴 작성, 메시지 박스...
		new Ex46Menu();
	}

}
