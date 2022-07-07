<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<style>
  a{
  	text-decoration : none;
  }
  table{
 	border-collapse: collapse;
 	width: 1000px;    
 	margin-top : 20px;
 	text-align: center;
  }
  td, th{
  	border : 1px solid black;
  	height: 50px;
  }
  th{
  	font-size : 17px;
  }
  thead{
  	font-weight: 700;
  }
  .table_wrap{
  	margin : 50px 0 0 50px;
  }
  .bno_width{
  	width: 12%;
  }
  .writer_width{
  	width: 20%;
  }
  .regdate_width{
  	width: 15%;
  }
  .updatedate_width{
  	width: 15%;
  }
  .top_btn{
  	font-size: 20px;
    padding: 6px 12px;
    background-color: #fff;
    border: 1px solid #ddd;
    font-weight: 600;
  }
  
  .pageInfo{
      list-style : none;
      display: inline-block;
    margin: 50px 0 0 100px;      
  }
  .pageInfo li{
      float: left;
    font-size: 20px;
    margin-left: 18px;
    padding: 7px;
    font-weight: 500;
  }
 a:link {color:black; text-decoration: none;}
 a:visited {color:black; text-decoration: none;}
 a:hover {color:black; text-decoration: underline;}
  
   .active{
      background-color: #cdd5ec;
  }
  
  .search_area{
    display: inline-block;
    margin-top: 30px;
    margin-left: 260px;
  }
  .search_area input{
      height: 30px;
    width: 250px;
  }
  .search_area button{
     width: 100px;
    height: 36px;
  }
  
  </style>

</head>
<body>
	<h1>목록페이지입니다.</h1>
	<div class="table_wrap">
		<a href="/board/enroll" class="top_btn">게시판 등록</a>
		<table>
			<thead>
				<tr>
					<th class="bno_width">번호</th>
					<th class="title_width">제목</th>
					<th class="writer_width">작성자</th>
					<th class="regdate_width">작성일</th>
					<th class="updatedate_width">수정일</th>
				</tr>
			</thead>
			<c:forEach items="${list}" var="list">
			<!-- 아이템으로 들어온 값을 list에 하나의 값으로 넣는다-->	            
				<tr>
	                <td><c:out value="${list.bno}"/></td>
	                <td>
		                <a class="move" href='<c:out value="${list.bno}"/>'>
	                        <c:out value="${list.title}"/>
	                    </a>
	                </td> 
	                <td><c:out value="${list.writer}"/></td>
	                
	           <%-- <td><c:out value="${list.regdate}"/></td>
	                <td><c:out value="${list.updateDate}"/></td> --%>
	            	<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.regdate}"/></td>
                	<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.updateDate}"/></td>
	            </tr>
	        </c:forEach>
		</table>
		
		<!-- 검색 영역 -->
		<div class="search_wrap">
        <div class="search_area">
            <input type="text" name="keyword" value="${pageMaker.cri.keyword }">
            <button>Search</button>
        </div>
    	</div>    
		
		<!-- 페이지 영역 -->
		<div class="pageInfo_wrap" >
       		<div class="pageInfo_area"> 	
       			<ul id="pageInfo" class="pageInfo">	
	       			<!-- 이전페이지 버튼 -->
	                <c:if test="${pageMaker.prev}">
	                    <li class="pageInfo_btn previous"><a href="${pageMaker.startPage-1}">Previous</a></li>
	                </c:if>
	       			
	       			<!-- 각 번호 페이지 버튼 -->
	                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
	                     <!-- 컨트롤러에서 전달된 페이지 번호가 현재 반복문을 돌다가 나온 페이지 번호이면 active라는 클래스 동적 추가 -->
	                     <li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? "active":"" }"><a href="${num}">${num}</a></li>
	                </c:forEach>
	                
	                 <!-- 다음페이지 버튼 -->
	                <c:if test="${pageMaker.next}">
	                    <li class="pageInfo_btn next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
	                </c:if>
                </ul>
        	</div>
    	</div>
				
		<form id="moveForm" method="get">    
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
	        <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
	    	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
	    </form>
	</div>
	
	<script>
    $(document).ready(function(){
    	let result = '<c:out value="${result}"/>'; //백엔드의 값을 프론트의 변수로 가져온다.
 		
    	//함수호출
    	checkAlert(result);
        
        function checkAlert(result){
            
            if(result === ''){ //result에 아무것도 안오면 실행되지 않음.
                return;
            }
            
            else if(result === "enroll success"){
                alert("등록이 완료되었습니다.");
            }
            
            else if(result === "modify success"){
                alert("수정이 완료되었습니다.");
            }
            
            else if(result === "delete success"){
                alert("삭제가 완료되었습니다.");
            }
        }    

    });
    /* a 태그가 동작 되도록 하는 자바스크립트 코드 */
    let moveForm = $("#moveForm");
    /* a 태그(class move인 제목)가 클릭이 되면 이 함수를 실행한다. */
    $(".move").on("click", function(e){
        e.preventDefault(); //이벤트 버블링 막기. 자식의 이벤트를 부모에서도 인식해서 실행. 이벤트를 부모에서 실행하지 않도록
       
	    // jquery name 받아와서 내용을 제거해주기
	   	let nameEle = $("input[name=bno]"); //name속성값이 bno인 객체 가져오기
	   	nameEle.remove(); //그놈 dom요소 제거
	   	//vanilla js는 부모.removeChile(자식)으로 지우고
	   	// jquery는 자기.remove()로 지운다.
	   	
	   	
        //비어있는 moveForm에 동적으로 hidden input으로 bno를 추가.
        //이것만 있으면 뒤로가기를 했을때 bno가 누적되는 현상이 발생한다.
        moveForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
        moveForm.attr("action", "/board/get");
        moveForm.submit();
    });
    
    //페이지 이동
 	$(".pageInfo a").on("click", function(e){
 		e.preventDefault();
 		//name이 pageNum인 input태그의 값에다가 그놈의 href값으로 대입
        moveForm.find("input[name='pageNum']").val($(this).attr("href"));
        moveForm.attr("action", "/board/list"); //action속성 추가
        moveForm.submit(); 
    });
    
    //검색
 	/* search_area자손의 button태그 */
 	$(".search_area button").on("click", function(e){
        e.preventDefault();
        let val = $("input[name='keyword']").val();
        moveForm.find("input[name='keyword']").val(val);
        moveForm.find("input[name='pageNum']").val(1);
        moveForm.submit();
    });
    
	</script>
</body>
</html>