package com.vam.mapper;

import java.util.List;

import com.vam.model.BoardVO;

//인터페이스 : 약속 => 마이 바티스를 호출하기 위해서 어떤 함수를 호출할 것인지 약속하는 곳
public interface BoardMapper {
	 /* 글 등록 */
    public void enroll(BoardVO board);
    
    /* 게시판 목록 */
    public List<BoardVO> getList();
    //리턴타입이 BoardVO인 리스트를 가져온다.

}
