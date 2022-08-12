package com.care.root.mybatis.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

@Repository
public interface BoardMapper {
	public ArrayList<BoardDTO> getBoardList(
			@Param("s") int start, @Param("e") int end);
	//여기의 param은 xml에서 사용하기 위한 이름
	public int write(BoardDTO dto);
	public BoardDTO contentView(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	public int delete(int writeNo);
	public void addReply(Map<String, String> map);
	public List<BoardRepDTO> getReplyList(int write_group);
	public int selectBoardCount();
}
