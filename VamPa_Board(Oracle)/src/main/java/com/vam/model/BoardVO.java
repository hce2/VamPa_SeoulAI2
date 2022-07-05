package com.vam.model;

import java.util.Date;

import lombok.Data;

@Data //db에 있는 정보를 자바에 가져오기 위해서 정보를 객체를 받아오기 하는 중
//VO : 벨류 오브젝트 => 값을 저장하고 있는 객체
public class BoardVO {
	/* 게시판 번호 */
    private int bno;
    
    /* 게시판 제목 */
    private String title;
    
    /* 게시판 내용 */
    private String content;
    
    /* 게시판 작가 */
    private String writer;
    
    /* 등록 날짜 */
    private Date regdate;
    
    /* 수정 날짜 */
    private Date updateDate;
}
