package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
