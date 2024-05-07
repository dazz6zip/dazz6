package pack07GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Ex50Packman extends JFrame implements KeyListener {

	Image image;
	int x = 100, y = 100;
	int selImage = 1;

	public Ex50Packman() {
		super("Use keyboard");

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack1.jpg"));

		setLayout(null); // 배치관리자 없음
		setResizable(false); // 크기 고정

		setBounds(200, 200, 300, 300);
		setBackground(Color.WHITE);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(this);

		x = super.getWidth() / 2; // Frame의 너비 절반(super는 명시적 호출을 위함. 없어도 무방함)
		y = super.getHeight() / 2;
	}

	@Override
	public void paint(Graphics g) {
		// Frame에 무언가를 그려 주는 메소드, 자동 호출
		switch (selImage) {
		case 1 : 
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack1.jpg");
			break;
		case 2 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack2.jpg");
			break;
		case 3 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack3.jpg");
			break;
		case 4 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack4.jpg");
			break;
		case 5 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack5.jpg");
			break;
		case 6 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack6.jpg");
			break;
		case 7 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack7.jpg");
			break;
		case 8 :
			image = Toolkit.getDefaultToolkit().getImage("C:\\work\\JAVA\\dazz6\\src\\pack07GUI\\pack8.jpg");
			break;
		}
		g.clearRect(0, 0, getWidth(), getHeight()); // 화면 전체 선택 후 클리어
		g.drawImage(image, (x - image.getWidth(this) / 2), (y - image.getHeight(this) / 2), this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_NUMPAD6) { // 오른쪽 화살표
			selImage = (selImage == 1)?2:1; // 삼항 연산자
			x = (x < getWidth())?x += 10:-image.getWidth(this);
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_NUMPAD4) { // 왼쪽 화살표
			selImage = (selImage == 3)?4:3;
			x = (x > 0)?x -= 10:getWidth() + image.getWidth(this);
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_NUMPAD2) { // 위쪽 화살표
			selImage = (selImage == 5)?6:5;
			y = (y < getHeight())?y += 10:-image.getHeight(this);
		} else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_NUMPAD8) { // 아래쪽 화살표 
			selImage = (selImage == 7)?8:7;
			y = (y > 0)?y -= 10:getHeight() + image.getHeight(this);		
		}
		
		repaint(); // paint() 호출

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		// 키보드 이벤트 연습
		new Ex50Packman();

	}

}
