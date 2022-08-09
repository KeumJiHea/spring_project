package com.care.root.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardFileService {
	String IMG_REPO = "D:/오후취업반/img_repo";
	public String getMessage(String msg, String url);
	public String fileProcess(MultipartHttpServletRequest mul);
}
