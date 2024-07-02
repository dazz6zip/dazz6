package kr.mvc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ListController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<UserDto> list = UserManager.instance().getUserAll();
		request.setAttribute("list", list);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list.jsp");
		mav.setRedirect(false); // forwarding
		
		return mav;
	}
}
