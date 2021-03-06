package com.vam.model;

import lombok.Data;

@Data
public class Criteria {
	//속성이 3개, 생성자가 2개 있다
	
	/* 현재 페이지 */
    private int pageNum;
    
    /* 한 페이지 당 보여질 게시물 갯수 */
    private int amount;
    
    /* 컴색 키워드 */
    private String keyword;
    
    
    /* 기본 생성자 -> 기봅 세팅 : pageNum = 1, amount = 10 */
    public Criteria() {
    	//this : 이 클래스로 만들어진 인스턴스를 말한다.
        this(1,10); //여기 구문이 아래의 인자가 있는 크리터리아를 호출했다. 
        // this() : 클래스이름에 괄호를 열고 닫고있으니 생성자
        // 생성자인데 인자가 2개가 있으니까 밑의 생성자
        // 아무 인자를 전달하지 않은 기본생성자로 객체를 만들경우,
        // 1페이지, 한페이지당 10개 보이기로 만든다.
    }
    
    /* 생성자 => 원하는 pageNum, 원하는 amount */
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
