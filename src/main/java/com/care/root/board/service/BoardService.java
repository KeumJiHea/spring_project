package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;

public interface BoardService {
	public void getBoardList(Model model);
	public String write(BoardDTO dto, MultipartHttpServletRequest mul, HttpServletRequest request);
}
