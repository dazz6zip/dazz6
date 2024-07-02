package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.mvc.model.UserManager;

public class LoginController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 넘어온 값 받기
		String id = request.getParameter("userid");
		String pwd = request.getParameter("password");
		
		// 모델과 통신
		// (참고) 비지니스 로직은 컨트롤러가 아닌 모델에서만!
		UserManager manager = UserManager.instance();
		boolean b = manager.login(id, pwd);
		
		ModelAndView mav = new ModelAndView();
		
		if (b) {
			// 로그인 성공
			HttpSession session = request.getSession(true); // 없으면 만들고 있으면 읽음
			session.setAttribute("userid", id);
			mav.setViewName("list.m2");
		} else {
			// 로그인 실패
			mav.setViewName("fail.html");
		}
		
		mav.setRedirect(true);
		return mav;
	}
}
