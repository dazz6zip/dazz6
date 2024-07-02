package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.removeAttribute("userid");
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.html");
		mav.setRedirect(true);
		
		return mav;
	}
}
