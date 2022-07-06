package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.BoardVO;
import com.vam.model.Criteria;
import com.vam.model.PageMakerDTO;
import com.vam.service.BoardService;

/*@Controller 어노테이션의 경우 해당 클래스를 
스프링의 빈으로 인식하도록 하기 위함
@RequestMapping("/board/*")의 경우 '/board'로 시작하는 
모든 처리를 BoardController.java 가 하도록 지정하는 역할을 합니다.*/

@Controller
@RequestMapping("/board/*") //요청을 맵핑시켜줬다 board 뒤의 의 모든 요청을 받는다
public class BoardController {
//	static final : 값을 바꿀 수 없고 없이도 사용할 수 있다
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
    private BoardService bservice;
	
	// get로 온 요청중에 list로 온것은 boardListGET()이 함수를 실행해라
	@GetMapping("/list") //void로 하면 어노테이션에서 요청한 값과 같은 값이 알아서 리턴된다.
	public void boardListGET(Model model, Criteria cri) {  
		//화면에서 넘어온 name과 controller에서 쓰는 변수의 이름이 카멜케이스로 변환한 것이 같으면 자동매핑됨
		//Criteria에는 pageNum이 있고, get.jsp에는 bno가 있다.
		log.info("게시판 목록 페이지 진입");  
        // 리턴값이 return "board/list"; 와 같다
        model.addAttribute("list", bservice.getListPaging(cri));
        int total = bservice.getTotal();
        PageMakerDTO pageMake = new PageMakerDTO(cri, total); 
        model.addAttribute("pageMaker", pageMake);
	}
	
	// get로 온 요청중에 enroll로 온것은 boardEnrollGET()이 함수를 실행해라
	// Enroll : 등록하다
	@GetMapping("/enroll")
	public void boardEnrollGET() {       
		log.info("게시판 등록 페이지 진입");
	}

	 
    /* 게시판 등록 */
    @PostMapping("/enroll")
    public String boardEnrollPOST(BoardVO board, RedirectAttributes rttr) {
        log.info("BoardVO : " + board);
        bservice.enroll(board); //글쓰기
        //addFlashAttribute : 일회성으로만 데이터 전송. 한번만 창을 띄운다.
        rttr.addFlashAttribute("result", "enroll success"); //등록 성공 창 띄우기
        return "redirect:/board/list"; //글을 쓰고나면 list페이지로 이동
    }
	
    
    /* GET요청은 페이지 이동이 거듭되는 동안 이전페이지들의 요청정보를 기억하고 있어햐 한다.
     * URL에 파라미터가 눡되어 전달되는데 이런 기법을 URL Rewrite처리라고 한다. */
    /* 게시글 상세조회 */
    @GetMapping("/get")
    public void boardGetPageGET(int bno, Model model, Criteria cri) {
        
        model.addAttribute("pageInfo", bservice.getPage(bno));
        model.addAttribute("cri", cri);
    }
    
    /* 수정 페이지 이동 */
    @GetMapping("/modify")
    public void boardModifyGET(int bno, Model model, Criteria cri) {
        
        model.addAttribute("pageInfo", bservice.getPage(bno));
        model.addAttribute("cri", cri);
    }
    
    /* 글 수정 기능*/
    @PostMapping("/modify")
    public String boardModifyPOST(BoardVO board, RedirectAttributes rttr) {
        
        bservice.modify(board);
        
        rttr.addFlashAttribute("result", "modify success");
        
        return "redirect:/board/list";
        
    }
    
    /* 글 삭제 기능 */
    @PostMapping("/delete")
    public String boardDeletePOST(int bno, RedirectAttributes rttr) {
        
        bservice.delete(bno);
        
        rttr.addFlashAttribute("result", "delete success");
        
        return "redirect:/board/list";
        
    }
    
//	@GetMapping("/custom")
//	public String boardCustomGET() {       
//		log.info("커스텀 페이지 진입");
//		return "board/custom";
//	}

}
