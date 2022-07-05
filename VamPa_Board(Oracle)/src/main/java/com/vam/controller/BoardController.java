package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*@Controller 어노테이션의 경우 해당 클래스를 
스프링의 빈으로 인식하도록 하기 위함
@RequestMapping("/board/*")의 경우 '/board'로 시작하는 
모든 처리를 BoardController.java 가 하도록 지정하는 역할을 합니다.*/

@Controller
@RequestMapping("/board/*") //요청을 맵핑시켜줬다 board 뒤의 의 모든 요청을 받는다
public class BoardController {
//	static final : 값을 바꿀 수 없고 없이도 사용할 수 있다
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	// get로 온 요청중에 list로 온것은 boardListGET()이 함수를 실행해라
	@GetMapping("/list")
	public void boardListGET() {    
        log.info("게시판 목록 페이지 진입");   
    }
	
	// get로 온 요청중에 enroll로 온것은 boardEnrollGET()이 함수를 실행해라
	@GetMapping("/enroll")
	// Enroll : 등록하다
	public void boardEnrollGET() {       
		log.info("게시판 등록 페이지 진입");
	}


}
