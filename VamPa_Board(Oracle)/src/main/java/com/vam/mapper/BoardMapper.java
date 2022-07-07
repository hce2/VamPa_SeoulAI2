package com.vam.mapper;

import java.util.List;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;

//인터페이스 : 약속 => 마이 바티스를 호출하기 위해서 어떤 함수를 호출할 것인지 약속하는 곳
public interface BoardMapper {
	 /* 글 등록 */
    public void enroll(BoardVO board);
    
    /* 게시판 목록 */
    public List<BoardVO> getList();
    //리턴타입이 BoardVO인 리스트를 가져온다.
    
    /* 게시글 상세조회 */
    public BoardVO getPage(int bno);
    
    /* 게시글 수정 */
    public int modify(BoardVO board);
    
    /* 게시판 삭제 */
    public int delete(int bno);
    
    /* 게시글 목록(페이징 적용) */
    public List<BoardVO> getListPaging(Criteria cri);
    //함수이름 : getListPaging
    //이 함수가 실행될떄 필요한 데이터가 하나 있는데 그놈의 데이터타입이 Criteria여야 한다.
    //그 전달된 참조형변수를 이 함수 안에서는 cri라는 이름으로 쓸거다
    //이 함수가 실행되고 나면 BoardVO로 구성된리스트르 반환할거다
    //위의 함수정의에 대한 약속 => 이 파일은 인터페이스 파일이기 떄문에
    
    /* 게시글 총 갯수 */
    public int getTotal(Criteria cri);
}
