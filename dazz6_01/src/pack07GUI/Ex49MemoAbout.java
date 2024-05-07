package pack07GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

// JDialog : 맞춤형 대화 상자 작성, 커스터마이징 가능
// 독립 호출 불가능, Frame에서 자식창으로 호출
public class Ex49MemoAbout extends JDialog implements ActionListener {
	
	public Ex49MemoAbout(JFrame frame) {
		super(frame);
//		super(frame, "About MemoPad", true); // 생성자로 Modal, Modaless 지정하기
		
		setTitle("About MemoPad");
		setModal(true); // true Modal
//		setModal(false); // false Modal(modaless)
		
		initLayoutAbout();
		
		setBackground(Color.LIGHT_GRAY);
		setBounds(350, 350, 200, 150);
		setVisible(true);
			
	}
	
	private void initLayoutAbout() {
		JLabel lblMes = new JLabel("Mini MemoPad v0.9 (Beta)");
		JButton btnconfirm = new JButton("OK");
		btnconfirm.addActionListener(this);
		add("Center", lblMes);
		add("South", btnconfirm);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose(); // JDialog 닫기
	}
	

}
