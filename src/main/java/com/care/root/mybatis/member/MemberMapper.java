package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.care.root.member.dto.MemberDTO;

@Repository
public interface MemberMapper {
	public ArrayList<MemberDTO> getMember();
	public MemberDTO getUser(String id);
	public int register(MemberDTO dto);
	public int modify(MemberDTO dto);
	public int delete(String id);
	public void keepLogin(Map<String, Object> map);
	public MemberDTO getCookieUser(String cookie);
}
