package dazz6;

import java.util.ArrayList;

class Shape {
	public void draw() {
	}
}

class Circle extends Shape {
	public void draw() {
		System.out.println("Circle");
	}
}

class Triangle extends Shape {
	public void draw() {
		System.out.println("Triangle");
	}
}

public class Student {
	public static void main(String[] args) {

		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(new Circle());
		list.add(new Triangle());

        for (Shape s : list) {
          s.draw();
        }
	}
}
