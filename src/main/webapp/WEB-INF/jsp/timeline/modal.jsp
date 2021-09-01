<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 class="btn moreBtn" data-toggle="modal" data-target="#moreModal" data-post-id="${content.post.id}">
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




	$('.moreBtn').on('click', function(e) {
		 let postId = $(this).data('post-id');
		 $('#moreModal').data('post-id', postId);
		 alert(postId);
		});
		
		
		
		
		</body>
</html>