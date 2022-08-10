package com.care.root.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.SessionName;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController implements SessionName {
	
	@Autowired
	MemberService ms;
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("/logout")
	public String login(HttpSession session,
			@CookieValue(required = false)Cookie loginCookie,
			HttpServletResponse response) {
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			ms.keepLogin((String)session.getAttribute(LOGIN), "nan");
		}
		session.invalidate();
		return "redirect:/index";
	}
	
	@PostMapping("/successLogin")
	public String successLogin(MemberDTO dto, Model model, HttpServletResponse response,
								HttpSession session, String autoLogin)
	{
		int result = ms.login(dto);
		//로그인 성공: 1/로그인 실패: 0/비밀번호 틀림: -1
		model.addAttribute("loginResult", result);
		
		if(result==1) {
			session.setAttribute(LOGIN, dto.getId());
			session.setAttribute("login", "로그아웃");
			
			if(autoLogin != null) { //자동로그인에 체크했다면
				int time = 60*60*24*90; //90일 동안 유지되는 쿠키값을 만들 것임
				Cookie cookie = new Cookie("loginCookie", dto.getId());
				cookie.setMaxAge(time);
				cookie.setPath("/");
				response.addCookie(cookie);
				
				ms.keepLogin(dto.getId(),dto.getId());
			}
			
			return "index";
		}
		
		return "redirect:login";
	}
	
	@GetMapping("/memberInfo")
	public String memberInfo(Model model) {
			ms.memberInfo(model);
			return "member/memberInfo";
	}
	
	@GetMapping("/info")
	public String info(Model model, String id) {
		ms.getUser(model, id);
		return "member/info";
	}
	
	@GetMapping("/register")
	public String register() {
		return "member/register";
	}
	
	@PostMapping("regChk")
	public String regChk(MemberDTO dto, RedirectAttributes re) {
		int result = ms.register(dto);
		re.addFlashAttribute("regResult", result);
		
		if(result==0) {
			return "redirect:register";
		}
		return "redirect:/index";
	}
	
	@PostMapping("modify")
	public String modify(MemberDTO dto) {
		System.out.println(dto.getPwd());
		int result = ms.modify(dto);
		if(result==0) {
			return "member/info";
		}
		return "redirect:/index";
	}
	
	@GetMapping("delete")
	public String delete(String id) {
		int result = ms.delete(id);
		if(result==0) {
			return "member/info";
		}
		
		return "logout";
		//아래를 로그아웃으로 처리하는게 더 간단함
//		session.invalidate();
//		return "redirect:/index";
	}
}
