package pack07GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import dazz6.Test01;

public class Ex49Memo extends JFrame implements ActionListener {

	private JTextArea txtMemo = new JTextArea("", 10, 30);
	private String copyText;

	private JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit;
	private JMenuItem mnuCopy, mnuPaste, mnuCut, mnuDel, mnuFontSize;
	private JMenuItem mnuAbout, mnuEtc1, mnuEtc2;

	private JPopupMenu popup; // 팝업 메뉴
	private JMenuItem m_white, m_blue, m_yellow;

	public Ex49Memo() {
		super("MemoPad");

		initLayout();
		menuLayout();

		setBounds(200, 200, 400, 350);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Ex49Memo.this, "Exit", "Check", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
				else
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
	}

	private void initLayout() {
		JScrollPane scrollPane = new JScrollPane(txtMemo);
		txtMemo.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		add("Center", scrollPane);
	}

	private void menuLayout() {
		JMenuBar menuBar = new JMenuBar(); // 메뉴 바

		JMenu mnuFile = new JMenu("File");
		mnuNew = new JMenuItem("New Memo");
		mnuOpen = new JMenuItem("Open...");
		mnuSave = new JMenuItem("Save...");
		mnuExit = new JMenuItem("Exit");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuSave);
		mnuFile.addSeparator();
		mnuFile.add(mnuExit);

		JMenu mnuEdit = new JMenu("Edit");
		mnuCopy = new JMenuItem("Copy(C)");
//		mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK)); // 단축키(Shortcut key)
		mnuPaste = new JMenuItem("Paste");
		mnuCut = new JMenuItem("Cut");
		mnuDel = new JMenuItem("Delete");
		mnuFontSize = new JMenuItem("Font Size...");
		mnuEdit.add(mnuCopy);
		mnuEdit.add(mnuPaste);
		mnuEdit.add(mnuCut);
		mnuEdit.add(mnuDel);
		mnuEdit.addSeparator();
		mnuEdit.add(mnuFontSize);

		JMenu mnuShow = new JMenu("Show");
		mnuAbout = new JMenuItem("About...");
		mnuEtc1 = new JMenuItem("Calculator");
		mnuEtc2 = new JMenuItem("Cafe");
		mnuShow.add(mnuAbout);
		mnuShow.addSeparator();
		mnuShow.add(mnuEtc1);
		mnuShow.add(mnuEtc2);

		menuBar.add(mnuFile);
		menuBar.add(mnuEdit);
		menuBar.add(mnuShow);

		setJMenuBar(menuBar);

		// 메뉴 리스너 호출
		mnuNew.addActionListener(this);
		mnuOpen.addActionListener(this);
		mnuSave.addActionListener(this);
		mnuExit.addActionListener(this);
		mnuCopy.addActionListener(this);
		mnuPaste.addActionListener(this);
		mnuCut.addActionListener(this);
		mnuDel.addActionListener(this);
		mnuFontSize.addActionListener(this);
		mnuAbout.addActionListener(this);
		mnuEtc1.addActionListener(this);
		mnuEtc2.addActionListener(this);

		// 팝업 메뉴
		popup = new JPopupMenu();
		JMenu menuColor = new JMenu("Choose Color");
		m_white = new JMenuItem("White");
		m_blue = new JMenuItem("Blue");
		m_yellow = new JMenuItem("Yellow");
		menuColor.add(m_white);
		menuColor.add(m_blue);
		menuColor.add(m_yellow);
		m_white.addActionListener(this);
		m_blue.addActionListener(this);
		m_yellow.addActionListener(this);
		popup.add(menuColor);
		txtMemo.add(popup);

		txtMemo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스 오른쪽 버튼 클릭할 경우 해당 지점에 팝업 띄우기
//				if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
//					popup.show(txtMemo, e.getX(), e.getY());
//				}
				if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK) {
					popup.show(txtMemo, e.getX(), e.getY());
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e.getActionCommand());
//		System.out.println(e.getSource());
		if (e.getSource() == mnuNew) { // 새 문서
			txtMemo.setText("");
			setTitle("Untitle");

		} else if (e.getSource() == mnuOpen) { // 열기
			// 파일을 열기 위한 경로명과 파일명 얻기 : OS 지원창 사용
			FileDialog dialog = new FileDialog(this, "Open", FileDialog.LOAD);
			dialog.setDirectory("."); // . : 현재 디렉토리
			dialog.setVisible(true);
			if (dialog.getFile() == null) {
				return;
			}
			String dfName = dialog.getDirectory() + dialog.getFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(dfName));
				txtMemo.setText("");
				String line = "";
				while ((line = reader.readLine()) != null) {
					txtMemo.append(line + "\n");
				}
				reader.close();
				setTitle(dialog.getFile() + " - MemoPad");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == mnuSave) { // 저장
			// 파일 저장을 위한 경로명과 파일명 얻기 : OS 지원창 사용
			FileDialog dialog = new FileDialog(this, "Save", FileDialog.SAVE);
			dialog.setDirectory("."); // . : 현재 디렉토리
			dialog.setVisible(true);
			if (dialog.getFile() == null) {
				return;
			}
			String dfName = dialog.getDirectory() + dialog.getFile();
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
				// 윈도우 타입 경로 및 파일 지정 : c:\\work\\a.txt
				writer.write(txtMemo.getText());
				writer.close();
				setTitle(dialog.getFile() + " - MemoPad");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == mnuExit) { // 종료
			int result = JOptionPane.showConfirmDialog(this, "Exit", "Check", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION)
				System.exit(0);
			else
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		} else if (e.getSource() == mnuCopy) { // 복사
			copyText = txtMemo.getSelectedText();

		} else if (e.getSource() == mnuPaste) { // 붙여넣기
			int position = txtMemo.getCaretPosition();
			txtMemo.insert(copyText, position);

		} else if (e.getSource() == mnuCut) { // 잘라내기
			copyText = txtMemo.getSelectedText();
			// copyText에 저장된 부분 txtMemo에서 지우기
			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd();
			txtMemo.replaceRange("", start, end);

		} else if (e.getSource() == mnuDel) { // 삭제
			int start = txtMemo.getSelectionStart();
			int end = txtMemo.getSelectionEnd();
			txtMemo.replaceRange("", start, end);

		} else if (e.getSource() == mnuFontSize) { // 폰트 사이즈
			String fontSize = JOptionPane.showInputDialog(this, "Font Size : ", txtMemo.getFont().getSize());
			if (fontSize != null) {
				try {
					int fsize = Integer.parseInt(fontSize);
					txtMemo.setFont(new Font(txtMemo.getFont().getName(), txtMemo.getFont().getStyle(), fsize));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Font Size Error");
				}
			}

		} else if (e.getSource() == mnuAbout) { // 메모장이란
			new Ex49MemoAbout(this);

		} else if (e.getSource() == mnuEtc1) { // 기타1 (계산기)
			// exe 실행하기
			try {
				Runtime runtime = Runtime.getRuntime();
				runtime.exec("calc.exe");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
			}

		} else if (e.getSource() == mnuEtc2) { // 기타2 (카페)
			// 브라우저 실행(호출)하기
			try {
//				Desktop 클래스는 Java 응용 프로그램 URI 나 파일을 처리하기 위해 기본 등록된 관련 응용 프로그램을 실행
//				1. 기본 브라우저를 통해서 URL 전시
//				2. 메일 클라이언트 실행
//				3. 기본 응용프로그램을 통해서 파일을 열거나 편집
				String url = "https://cafe.daum.net/flowlife";
				if (Desktop.isDesktopSupported()) { // Desktop 지원 확인
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.BROWSE)) {
						desktop.browse(new URI(url));
					}
				} else {
					JOptionPane.showMessageDialog(this, "BROWSER ERROR");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
			}

		} else if (e.getSource() == m_white) {
			txtMemo.setBackground(Color.WHITE);
		} else if (e.getSource() == m_blue) {
			txtMemo.setBackground(Color.BLUE);
		} else if (e.getSource() == m_yellow) {
			txtMemo.setBackground(Color.YELLOW);
		}
	}

	public static void main(String[] args) {
		// 간단한 메모장 만들기
		new Ex49Memo();
	}

}
