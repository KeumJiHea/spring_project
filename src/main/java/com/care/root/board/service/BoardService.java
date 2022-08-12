package com.care.root.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardService {
	String IMG_REPO = "c:/spring/img_repo";
	public void getBoardList(Model model, int num);
	public String write(BoardDTO dto, MultipartHttpServletRequest mul, HttpServletRequest request);
	public void contentView(int writeNo, Model model);
	public void modify_form(int writeNo, Model model);
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request);
	public String delete(int writeNo, String imageFileName, HttpServletRequest request);
	public void addReply(Map<String, String> map, String userId);
	public List<BoardRepDTO> getReplyList(int write_group);
}
