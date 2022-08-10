package com.care.root.member.service;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberService {
	public int login(MemberDTO dto);
	public void memberInfo(Model model);
	public void getUser(Model model, String id);
	public int register(MemberDTO dto);
	public int modify(MemberDTO dto);
	public int delete(String id);
	public void keepLogin(String id, String cookieId);
	public MemberDTO getCookieUser(String cookie);
}
