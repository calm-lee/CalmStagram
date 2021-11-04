<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="timeline" style="background-color:#f5f5f5">

<!-- 글쓰기 영역 -->
<c:if test="${not empty userId}">

	<div class="d-flex justify-content-center">

		<div class="post-box form-control  mt-4">
			<textarea name="content" cols="50" rows="4"
				placeholder="내용을 입력해주세요."></textarea>

			<div class="d-flex justify-content-between mt-3">
				<!--  이미지 버튼 -->
				<div class="ml-2">
					<input type="file" name="image" id="file"
						accept=".jpg, .jpeg, .png, .gif" class="d-none"> <a
						href="#" id="fileUploadBtn"><img
						src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"
						width="35"></a>
				</div>

				<!-- 업로드 버튼 -->
				<div class="mr-2">
					<button type="button" id="uploadBtn"
						class="btn form-control bg-info" style="color:white; font-size:12px">
						업로드
					</button>
				</div>
			</div>
		</div>
	</div>
</c:if>


<!-- 피드 -->

<c:forEach var="content" items="${contentList}" varStatus="status"> <!-- postList 반복문으로 노출 -->
<div class="d-flex justify-content-center mt-5">
	<div class="feed-box form-control">
	
	<!-- 글쓴이 아이디, 삭제(...)버튼 -->
		
		<div class="feed-header d-flex justify-content-between mt-2">
			<b>${content.post.userName}</b></span>
			
		<!-- userName이 post의 userName과 일치할 때만 노출 -->
			<c:if test="${content.post.userName eq userName}">
				<a href="#" class="btn moreBtn" data-toggle="modal" data-target="#moreModal" data-post-id="${content.post.id}">
				<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="35"></a>
			</c:if>
		</div>
		
	<!-- 피드 이미지 -->
		<c:if test="${not empty content.post.imgPath}">
		<div class="mt-3">
			<img src="${content.post.imgPath}" width="350">
		</div>
		</c:if>
		 
	<!-- 좋아요 영역 -->
		<div class="like-feed d-flex mt-1 align-items-center">
		
		<!-- filledLike이 false이면 빈하트가 보이게 -->
		<c:if test="${content.filledLike eq false}">
			<a href="#" class="btn likeBtn" data-post-id="${content.post.id}"  data-user-id="${userId}">
			<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="20" height="20"></a>
		</c:if>	
		
		<!-- filledLike이 true이면 채운하트가 보이게 -->
		<c:if test="${content.filledLike eq true}">
			<a href="#" class="btn likeBtn" data-post-id="${content.post.id}"  data-user-id="${userId}"><img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="20" height="20"></a>		
		</c:if>
		좋아요 ${content.likeCount}개
		</div>
		
	<!-- 글 영역 -->	
		<div class="post-feed mt-2">
			<span class="font-weight-bold">${content.post.userName}</span>
			<span>
				${content.post.content}
			</span>
		</div>
		
	<!-- 댓글 영역 -->			
		<div class="comment-feed mt-4 border-bottom">
			<div style="font-size:16px; color: #808080">댓글</div>
			
		</div>
		
		<!-- 댓글 목록 -->
	<c:forEach var="comment" items="${content.commentList}">

		<div class="comment-list mt-2 clearfix">
			<div class="commentBlock">
	            <span class="font-weight-bold">${comment.userName}</span>	
				<span>${comment.content}</span>
			
			<!-- 댓글 삭제 버튼 -->
				<a href="#" class="commentDelBtn float-right mr-3" data-comment-userName="${comment.userName}">
				<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
				</a>
			</div>
		</div>
	</c:forEach>
		
	<!-- 댓글 쓰기 영역 -->	
	<c:if test="${not empty content.post.userName}">
		<div class="comment-create d-flex mt-2">
			<input type="text" placeholder="댓글을 입력해주세요." id="commentTxt${content.post.id}" class="form-control">
			<button type="button" class="btn btn-light commentBtn" data-post-id="${content.post.id}">게시</button>
		</div>
	</c:if>
	</div>
</div>
</c:forEach>
</div>

<!-- Modal layer -->
<div class="modal fade bd-example-modal-sm" id="moreModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
     <div class="m-3 text-center">
      	<a href="#" class="deletePost d-block"><b>삭제하기</b></a> <!-- d-block: 삭제할 수 있는 영역을 넓게 하기 위함  -->
      </div>
    </div>
    <div class="modal-content">
     <div class="m-3 text-center">
      	<a href="#" class="cancel d-block" data-dismiss="modal">취소</a><!--  data-dismiss: 자동으로 modal창 닫음  -->
      </div>
    </div>
  </div>
</div>
<script>


	$(document).ready(function(){
		
		// 파일 업로드
		$('#fileUploadBtn').on('click', function(e){
			e.preventDefault();
			$('#file').click();
		});
		
		// 글 업로드 
		$('#uploadBtn').on('click', function(e){
			e.preventDefault();
			
			let content = $('textarea[name=content]').val();
			if(content == ''){
				alert("내용을 입력해주세요.");
				return;
			}
			
	
			let imgPath = $('input[name=image]').val();
			
			// 확장자 체크
			if(imgPath != ''){ // file이 업로드되었다면
				var ext = imgPath.split('.').pop().toLowerCase(); // 확장자
				console.log(ext);
				if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){ // 확장자가 배열에 없을 경우
					alert("jpg, jpeg, png, gif 파일만 업로드할 수 있습니다.");
					$('input[name=image]').val('') // 파일 내용을 비운다.
					return;
				} 
			}
			 
			// formData에 content, file 추가
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('input[name=image]')[0].files[0]); 
			// $('input[name=image]')[0] => 첫번째 input file 의미
			// .files[0] => 업로드된 파일 중 첫번째 의미

			$.ajax({
				url: "/post/post_create"
				, method: 'POST'
				, data: formData
				// 파일 업로드 시 필수 파라미터 
				, processData: false // 기본은 true(json, String)로 넘어갔으나 이번에는 formData로 넘어가므로 false
				, contentType: false
				, enctype: 'multipart/form-data'
				
				, success: function(data){
					if(data.result == 'success'){
						alert("포스트가 업로드되었습니다.");
						location.href = "/timeline/timeline_view";
					} 
				}
				, error: function(request,status,error){
					alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
				}
			});
			
		});
		
		// 코멘트 업로드
		$('.commentBtn').on('click', function(e){
			e.preventDefault();
			
			//postId 저장
			let postId = $(this).data('post-id');
			
			//content 저장
			let commentContent = $('#commentTxt' + postId).val();
			
			if(commentContent == ''){
				return;
			}
			
			$.ajax({
				type:'POST',
				url:'/comment/create_comment',
				data: {"postId":postId, "content":commentContent},
				success: function(data) {
					if (data.result == 'success') {
						location.reload(); // 새로고침
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
			
		});
			
		// 코멘트 삭제
		$('.commentDelBtn').on('click', function(e){
			e.preventDefault();
			
			//commentId 저장
			let commentUserName = $(this).data('comment-userName');
			
			
			$.ajax({
				type:'POST',
				url:'/comment/delete_comment',
				data: {"id": commentUserName},
				success: function(data) {
					if (data.result == 'success') {
						location.reload(); // 새로고침
					} else {
						alert("삭제 권한이 없습니다.");
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
			
		});
		
		// 좋아요 추가/해제
		$('.likeBtn').on('click', function(e){
			
			e.preventDefault();
			
			let postId = $(this).data('post-id');
			
			$.ajax({
				type:'POST',
				url:'/post/like_status',
				data: {"postId": postId},
				success: function(data) {
					if (data.result == 'success') {
						location.reload(); // 새로고침
					} else {
						alert("로그인해주세요.");
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});

		});
		
		// 글 삭제
		
		$('.moreBtn').on('click', function(e) {
		 let postId = $(this).data('post-id');
		 $('#moreModal').data('post-id', postId);
		});
		
		// moreModal -> deletePost 버튼
		$('#moreModal .deletePost').on('click', function(e){
			e.preventDefault();
			
			let postId = $('#moreModal').data('post-id');
			
			$.ajax({
				type:'POST',
				url:'/post/delete_post',
				data: {"postId": postId},
				success: function(data) {
					if (data.result == 'success') {
						alert("삭제가 완료되었습니다.");
						location.reload(); // 새로고침
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});
	});
	
</script>