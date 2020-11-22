package com.hsk.miniapi;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * author: yangfeng
 * time: 2020/5/5 9:21
 */
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        HttpSession session = httpServletRequest.getSession();
//        String name = SessionUtil.getLocalSessionId(session.getId());
//        if (session != null) {
//            if (name != null && !name.equals("")) {
//                session.setAttribute("shoppingApiAccount", null);
//                session.invalidate();
//            }
//            System.out.println("after interceptor clear session" + session.getId());
//        }
    }
}
