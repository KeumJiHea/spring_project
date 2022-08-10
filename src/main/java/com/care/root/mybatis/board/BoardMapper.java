package com.care.root.mybatis.board;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.care.root.board.dto.BoardDTO;

@Repository
public interface BoardMapper {
	public ArrayList<BoardDTO> getBoardList();
	public void write(BoardDTO dto);
}
