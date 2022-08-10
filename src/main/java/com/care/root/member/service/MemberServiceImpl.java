package com.care.root.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberMapper mapper;
	BCryptPasswordEncoder en = new BCryptPasswordEncoder();
	
	public int login(MemberDTO dto) {
		
		ArrayList<MemberDTO> list =  mapper.getMember();
		String userId = dto.getId();
		String userPwd = dto.getPwd();
		
		//로그인 성공: 1/로그인 실패: 0/비밀번호 틀림: -1
		for(MemberDTO d : list) {
			
			if(d.getId().equals(userId)) { //아이디가 같은 경우
				if(en.matches(userPwd, d.getPwd()) || d.getPwd().equals(userPwd)) { //로그인 성공
					return 1;
				}else {//비밀번호가 틀린 경우
					return -1;
				}
			}
		}
		return 0;
	}
	
	public void memberInfo(Model model) {
		ArrayList<MemberDTO> list =  mapper.getMember();
		model.addAttribute("list", list);
	}
	
	public void getUser(Model model, String id) {
		MemberDTO dto = mapper.getUser(id);
		String[] addr = dto.getAddr().split(",");

		model.addAttribute("addr", addr);
		model.addAttribute("dto", mapper.getUser(id));
	}
	
	public int register(MemberDTO dto) {

		try {
			String seq = en.encode(dto.getPwd());
			dto.setPwd(seq);
			return mapper.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int modify(MemberDTO dto) {
		MemberDTO d = mapper.getUser(dto.getId());
		System.out.println("수정값: " + dto.getPwd());
		System.out.println("원래값: " + d.getPwd());
		System.out.println(en.matches(dto.getPwd(), d.getPwd()));
		try {
			if(dto.getPwd().equals("********")) { //만약 비밀번호가 안 바뀌었으면
				dto.setPwd(d.getPwd());
			}else if(en.matches(dto.getPwd(), d.getPwd())==false) { //만약 비밀번호가 바뀌었으면
				String seq = en.encode(dto.getPwd());
				dto.setPwd(seq);
			}
			return mapper.modify(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int delete(String id) {
		try {
			return mapper.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void keepLogin(String id, String cookieId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cookieId", cookieId);
		mapper.keepLogin(map);
	}
	
	public MemberDTO getCookieUser(String cookie) {
		return mapper.getCookieUser(cookie);
	}

}
