package com.care.root.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;

	@Override
	public void getBoardList(Model model) {
		ArrayList<BoardDTO> list = mapper.getBoardList();
		if(list.isEmpty()) {
			model.addAttribute("list", "null");
		}else {
			model.addAttribute("list", list);
		}
		
	}

	@Override
	public String write(BoardDTO dto, MultipartHttpServletRequest mul,
			HttpServletRequest request) {
		dto.setImageFileName(bfs.fileProcess(mul));
		int result = 0;
		String msg, url;
		
		result = mapper.write(dto);
		
		if(result == 1) {
			//저장 성공
			msg = "새 글이 추가 되었습니다.";
			url = request.getContextPath()
					+"/board/board";
		}else {
			//저장 실패
			msg = "글 등록에 실패했습니다.";
			url = request.getContextPath()
					+"/board/writeForm";
		}
		return bfs.getMessage(msg, url);
	}

}
