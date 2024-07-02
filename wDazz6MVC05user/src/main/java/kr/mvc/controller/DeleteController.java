package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class DeleteController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");

		int result = UserManager.instance().delete(userid);
		
		ModelAndView mav = new ModelAndView();
		if (result > 0) { // INSERT 성공
			mav.setViewName("list.m2"); 
		} else { // DELETE 실패
			mav.setViewName("fail.html");
		}
		
		mav.setRedirect(true);

		return mav;
	}
}
