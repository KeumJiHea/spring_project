package com.care.root.board.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardFileService {
	String IMG_REPO = "C:/spring/img_repo";
	public String getMessage(String msg, String url);
	public String fileProcess(MultipartHttpServletRequest mul);
	public void deleteImage(String fName);
	public String saveFile(MultipartFile file);
}
