package com.care.root.board.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;

	@Override
	public void getBoardList(Model model, int num) {
		System.out.println("getBoardList 실행");
		int pageLetter = 3;
		int allCount = mapper.selectBoardCount();
		
		int repeat = allCount / pageLetter;
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		
		int end = num * pageLetter;
		int start = end + 1 -pageLetter;
		
		model.addAttribute("repeat", repeat);
		
		ArrayList<BoardDTO> list = mapper.getBoardList(start, end);
		
		if(list.isEmpty()) {
			model.addAttribute("list", "null");
		}else {
			model.addAttribute("list", list);
		}
		
	}

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
	
	public void contentView(int writeNo, Model model) {
		model.addAttribute("dto", mapper.contentView(writeNo));
	}
	
	public void modify_form(int writeNo, Model model) {
		model.addAttribute("dto", mapper.contentView(writeNo));
	}
	
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")));
		dto.setTitle( mul.getParameter("title"));
		dto.setContent( mul.getParameter("content"));
		
		MultipartFile file = mul.getFile("imageFileName");
		
		if(file.getSize() != 0) {
			dto.setImageFileName(bfs.saveFile(file));
			bfs.deleteImage(mul.getParameter("originFileName"));
		}else {
			dto.setImageFileName(mul.getParameter("originFileName"));
		}
		
		int result = mapper.modify(dto);
		String msg, url;
		if(result == 1) {
			msg = "수정되었습니다.";
			url = request.getContextPath()+
					"/board/contentView?writeNo="+dto.getWriteNo();
		}else {
			msg = "수정에 실패했습니다.";
			url = request.getContextPath()+
					"/board/modify_form?writeNo="+dto.getWriteNo();
		}
		return bfs.getMessage(msg, url);
	}
	
	
	
	public String delete(int writeNo, String imageFileName, HttpServletRequest request) {
		int result = mapper.delete(writeNo);
		String msg, url;
		if(result == 1) {
			bfs.deleteImage(imageFileName);
			msg = "삭제되었습니다.";
			url = request.getContextPath()+
					"/board/board";
		}else {
			msg = "삭제에 실패했습니다.";
			url = request.getContextPath()+
					"/board/contentView?writeNo="+writeNo;
		}
		return bfs.getMessage(msg, url);
	}
	
	public void addReply(Map<String, String> map, String userId) {
		map.put("userId", userId);
		mapper.addReply(map);
	}
	
	public List<BoardRepDTO> getReplyList(int write_group) {
		return mapper.getReplyList(write_group);
	}

}
