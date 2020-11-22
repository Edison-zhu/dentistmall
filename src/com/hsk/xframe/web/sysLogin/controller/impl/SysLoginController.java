package com.hsk.xframe.web.sysLogin.controller.impl;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hsk.supper.dto.SystemContext;
import com.hsk.xframe.api.controller.imp.DSTController;
import com.hsk.xframe.web.sysLogin.controller.ISysLoginController;

@Controller
public class SysLoginController extends DSTController implements ISysLoginController {
	
	@RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
	public ModelAndView toLogin(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute(SystemContext.sessionUser) != null)
			modelAndView.setViewName("/main/main");
		else
			modelAndView.setViewName("/main/login");	
		return modelAndView;
	}
}
