<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js" 
integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous">
</script>
<meta charset="UTF-8">
<title>${vo.qBoardTitle }</title>
</head>
<body>
	<%@ include file="../header.jspf" %>
	<h2>글 보기</h2>
	<div>
		<p>글 번호 : ${vo.qBoardId }</p>
	</div>
	<div>
		<p>제목 : </p>
		<p>${vo.qBoardTitle }</p>
	</div>
	<div>
		<p>작성자 : ${vo.memberNickname }</p>
		<p>작성일 : ${vo.qBoardCreatedDate}</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.qBoardContent }</textarea>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="글 목록"></a>
	
	
		<a href="update?qBoardId=${vo.qBoardId }&page=${page }"><input type="button" value="글 수정"></a>
		<form action="delete" method="POST">
			<input type="hidden" id="qBoardId" name="qBoardId" value="${vo.qBoardId }">
			<input type="submit" value="글 삭제"> 
		</form>

		<c:if test="${memberNickname != null}">
			<div style="text-align: center;">
				${memberNickname}
				<input type="hidden" id="memberNickname" value=${memberNickname }>
				<input type="text" id="qBoardCommentContent" required>
				<button id="btnCommentAdd">작성</button> 
			</div>
		</c:if>
		
		<c:if test="${memberNickname == null}">
			<br> 댓글을 작성하려면 로그인해 주세요.
		</c:if>
		<hr>
	
		<div style="text-align: center;">
			<div id="comments"></div>
		</div>
	
		<div>
			<br><br><br><br><br><br><br><br><br><br>
		</div>

		<script type="text/javascript">
      $(document).ready(function() {
         getAllComments();
         
         $('#btnCommentAdd').click(function(){ // 댓글작성 버튼 클릭 할 때 함수
            var qBoardId = $('#qBoardId').val(); // 게시판 번호 데이터
            var memberNickname = $('#memberNickname').val(); // 작성자 데이터
            var qBoardCommentContent = $('#qBoardCommentContent').val(); // 댓글 내용
            var obj = {
                  'qBoardId' : qBoardId, // 게시글 번호
                  'memberNickname' : memberNickname, // 회원닉네임
                  'qBoardCommentContent' : qBoardCommentContent // 게시글 댓글 내용
            } // obj	
            console.log(obj);
            
            // $.ajax로 송수신
            $.ajax({
               type : 'POST',
               url : 'comments',
               headers : {
                  'Content-Type' : 'application/json'
               },
               data : JSON.stringify(obj), // JSON으로 변환
               success : function(result){
                  console.log(result);
                  if(result == 1) {
                     console.log('댓글 입력 성공');
                     getAllComments();
                  }
               }
            }); // end ajax()   
         }); // end btnAdd.click()
         
         // 게시판 댓글 전체 가져오기
         function getAllComments(){
            var qBoardId = $('#qBoardId').val();
            
            var url = 'comments/all/' + qBoardId;
            $.getJSON(
               url,
               function(data) {
                  
                  // data : 서버에서 전송받은 list 데이터가 저장되어 있음.
                  // getJSON()에서 json 데이터는
                  // javascript object로 자동 parsing됨.
                  console.log(data);
               
                  var memberNickname = $('#memberNickname').val();
               
                  var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
                  
                  // $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수. 사실 걍 for문 써도 됌
                  $(data).each(function(){
                     // this : 컬렉션의 각 인덱스 데이터를 의미
                     console.log(this);
                     
                     var qBoardCommentCreatedDate = new Date(this.qBoardCommentCreatedDate);
                     var disabled = 'disabled';
                     var readonly = 'readonly';
                     
                     if(memberNickname == this.memberNickname) { // 로그인한 아이디 == 댓글작성한 아이디
                        disabled = '';
                        readonly = '';
                     }
                     
                     list += '<div class="comment_item">'
                           + '<pre>'
                           + '<input type="hidden" id="qBoardCommentId" value="' + this.qBoardCommentId +'">'
                           + this.memberNickname
                           + '&nbsp;&nbsp;' // 공백
                           + '<input type="text"  id="qBoardCommentContent" value="' + this.qBoardCommentContent + '">'
                           + '&nbsp;&nbsp;' // 공백
                           + qBoardCommentCreatedDate
                           + '&nbsp;&nbsp;' // 공백
                           + '<button class="btn_update" >수정</button>'
                           + '<button class="btn_delete" >삭제</button>'
                           + '<button class="btnReply">답글</button>'
                           + '</pre>'
                           + '</div>';
                  }); // end each()
               
                  $('#comments').html(list);
                  
               }
            ); // end getJSON()
            
            
         } // end getAllComment()
         
         
         
         // 수정 버튼을 클릭하면 선택된 댓글 수정
         $("#comments").on('click','.comment_item .btn_update', function(){
            console.log(this);
            
            // 선택된 댓글의 replyId, replyContent 값을 저장
            // prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
            var qBoardCommentId = $(this).prevAll('#qBoardCommentId').val();
            var qBoardCommentContent = $(this).prevAll('#qBoardCommentContent').val();
            console.log("선택된 댓글 번호 : " + qBoardCommentId + ", 댓글 내용 : " + qBoardCommentContent);
            
            // ajax 요청
            $.ajax({
               type : 'PUT',
               url : 'comments/' + qBoardCommentId,
               headers : {
                   'Content-Type' : 'application/json'
                },
               data : qBoardCommentContent,
               success : function(result) {
                  console.log(result);
                  if(result == '1') {
                     alert('댓글 수정 성공')
                     getAllComments();
                  }
               }
               
               
            }); // end ajax()
            
         }); // end relies.on()
         
         
         // 삭제 버튼을 클릭하면 선택된 댓글 삭제
         $("#comments").on('click','.comment_item .btn_delete', function(){
            console.log(this);
            
            var qBoardId = $('#qBoardId').val();
            var qBoardCommentId = $(this).prevAll('#qBoardCommentId').val();
            console.log("선택된 댓글 번호 : " + qBoardCommentId);
            
            // ajax 요청
            $.ajax({
               type : 'DELETE',
               url : 'comments/' + qBoardCommentId,
               headers : {
                   'Content-Type' : 'application/json'
                },
               data : qBoardId,
               success : function(result) {
                  console.log(result);
                  if(result == 1) {
                     alert('댓글 삭제 성공')
                     getAllComments();
                  }
               }
               
               
            }); // end ajax()
            
         }); // end comment.on()
         
         
		$("#comments").on('click', '.comment_item .btnReply', function(){
        	 
         }) // end 대댓글 등록
      }); // end document
   </script>
	
	
</body>
</html>






















