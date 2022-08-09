package com.care.root.board.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired BoardService bs;
	
	@GetMapping("/board")
	public String board(Model model) {
		bs.getBoardList(model);
		return "board/boardAllList";
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@PostMapping("write")
	public void write(BoardDTO dto, MultipartHttpServletRequest mul,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
		String message = bs.write(dto, mul, request);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
}
