package com.care.root.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLogin extends HandlerInterceptorAdapter{
	
	@Autowired MemberService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie cookie = WebUtils.getCookie(request, "loginCookie");
		//해당하는 쿠키값이 없으면 null이 됨
		
		if(cookie != null) {//자동로그인 체크한 사용자
			MemberDTO dto = ms.getCookieUser(cookie.getValue());
			if(dto != null) {
				request.getSession().setAttribute("loginUser", dto.getId());
			}
		}
		return true;
	}
}
