package pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestJstl1
 */
@WebServlet("/TestJstl")
public class TestJstl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String irum = "이름";
		request.setAttribute("irum", irum);
		
		Person person = new Person(); // init 메소드에서 하는 게 바람직함
		person.setName("바보");
		request.setAttribute("person", person);
		
		Student student = new Student();
		student.setAge(22);
		request.setAttribute("student", student);
		
		String[] animal = {"강아지", "고양이", "말"};
		request.setAttribute("animal", animal);
		
		String[] foods = {"당근", "시금치", "호박"};
		List<Object> list = new ArrayList<Object>();
		list.add(animal);
		list.add(foods);
		request.setAttribute("list", list);
		
//		response.sendRedirect("textjstl.jsp?irum=...");
		// 이렇게 하면 String 값만 넘어감
		
		request.getRequestDispatcher("testjstl.jsp").forward(request, response);
	}

}
