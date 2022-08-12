package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;
import com.care.root.mybatis.board.BoardMapper;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired BoardService bs;
	@Autowired BoardMapper mapper;
	
	@GetMapping("/board")
	public String board(Model model,
	@RequestParam(value="num", required = false, defaultValue = "1") int num) {
		
		bs.getBoardList(model, num);
		return "board/boardAllList";
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		System.out.println("write 메소드 실행");
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
	
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	@GetMapping("contentView")
	public String contentView(int writeNo, Model model) {
		upHit(writeNo);
		bs.contentView(writeNo, model);
		return "board/contentView";
	}
	
	@GetMapping("download")
	public void download(@RequestParam("imageFileName") String imageFileName,
	         HttpServletResponse response) throws Exception {
	    response.addHeader(
	   "Content-disposition","attachment;fileName="+imageFileName);
	    File file = new File(BoardFileService.IMG_REPO+"/"+imageFileName);
	    FileInputStream in = new FileInputStream(file);
	    FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	}
	
	@GetMapping("modify_form")
	public String modify_form(int writeNo, Model model) {
		bs.modify_form(writeNo, model);
		return "board/modify_form";
	}
	
	@PostMapping("modify")
	public void modify(MultipartHttpServletRequest mul,
						HttpServletRequest request,
						HttpServletResponse response) throws Exception {
		String message = bs.modify(mul, request);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
	
	@GetMapping("delete")
	public void delete(int writeNo, String imageFileName,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String msg = bs.delete(writeNo, imageFileName, request);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(msg);
	}
}
