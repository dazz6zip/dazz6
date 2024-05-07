package lambda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyLambda04Event {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Practice LAMBDA");
		JButton button1 = new JButton("Click(1)"); // 이벤트 처리
		JButton button2 = new JButton("Click(2)"); // 이벤트 처리
		JButton button3 = new JButton("Click(3)"); // Collection 처리

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 전통적 방법
				System.out.println("전통적 방법으로 Click(1) 이벤트 처리");
			}
		});

		button2.addActionListener((e) -> System.out.println("람다를 활용하여 Click(2) 이벤트 처리"));
		button3.addActionListener((e) -> callMethod());

		frame.add("North", button1);
		frame.add("Center", button2);
		frame.add("South", button3);

		frame.setBounds(200, 200, 300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static void callMethod() {
//		System.out.println("a");
		ArrayList<Jikwon> jikwons = new ArrayList<MyLambda04Event.Jikwon>();
		jikwons.add(new Jikwon(3, "홍길동"));
		jikwons.add(new Jikwon(1, "고길동"));
		jikwons.add(new Jikwon(2, "나길동"));

		System.out.println("정렬 전 : ");
		for (Jikwon j : jikwons) {
			System.out.println(j.bunho + " " + j.irum);
		}
//		accept(Jikwon j) {
//			System.out.println(j.bunho + " " + j.irum);
//		}
		jikwons.forEach(j -> System.out.println(j.bunho + " " + j.irum));

		System.out.println("Collections.sort 사용");
		
		// 특정 메소드의 매개변수로 람다식 사용
		Collections.sort(jikwons, new Comparator<Jikwon>() {
			public int compare(Jikwon o1, Jikwon o2) {
				return o1.bunho - o2.bunho; // 람다 없이 오름차순 정렬
			};
		});

		System.out.println("정렬 후 1 : ");
		jikwons.forEach(jik -> System.out.println(jik.bunho + " " + jik.irum));

		System.out.println("정렬 후 2 (람다) : ");
		Collections.sort(jikwons, (o1, o2) -> o1.bunho - o2.bunho);
		jikwons.forEach(jik -> System.out.println(jik.bunho + " " + jik.irum));
	}

	static class Jikwon { // DTO or VO 역할
		int bunho;
		String irum;

		public Jikwon(int bunho, String irum) {
			this.bunho = bunho;
			this.irum = irum;
		}
	}

}
