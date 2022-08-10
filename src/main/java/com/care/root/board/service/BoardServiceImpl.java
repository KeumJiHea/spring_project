package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper mapper;

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
	public void write(BoardDTO dto, MultipartHttpServletRequest mul) {
		dto.setImageFileName(fileProcess(mul));
		mapper.write(dto);
		
	}

	@Override
	public String fileProcess(MultipartHttpServletRequest mul) {
		MultipartFile file = mul.getFile("f");
		String imageFileName = null;
		
		if(!file.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");
			String sysFileName = format.format(new Date());
			//저장할 파일 이름 설정
			sysFileName += file.getOriginalFilename();
			imageFileName = sysFileName;
			
			//저장할 파일 경로 설정
			File saveFile = new File(IMG_REPO + "/" + sysFileName);
			
			try {
				//업로드 한 파일 저장
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			imageFileName = "nan";
		}
		return imageFileName;
		
	}

}
