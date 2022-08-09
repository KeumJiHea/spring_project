package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class BoardFileServiceImpl implements BoardFileService{
	
	public String getMessage(String msg, String url) {
		String message = "";
		message += "<script>alert('"+msg+"');";
		message += "location.href='"+url+"';</script>";
		return message;
	}
	
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
