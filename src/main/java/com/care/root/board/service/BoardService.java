package com.care.root.board.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;

public interface BoardService {
	String IMG_REPO = "c:/spring/img_repo";
	public void getBoardList(Model model);
	public void write(BoardDTO dto, MultipartHttpServletRequest mul);
	public String fileProcess(MultipartHttpServletRequest mul);
}
