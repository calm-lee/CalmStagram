<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty userId}">

	<div class="d-flex justify-content-center mt-4">

		<div class="post-box">
			<textarea class="form-control" name="content" cols="10" rows="5"
				placeholder="내용을 입력해주세요."></textarea>

			<div class="d-flex justify-content-between mt-2">
				<!--  이미지 버튼 -->
				<div>
					<input type="file" name="image" id="file"
						accept=".jpg, .jpeg, .png, .gif, .JPG,.JPEG, .PNG, .GIF" class="d-none"> <a
						href="#" id="fileUploadBtn"><img
						src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"
						width="35"></a>
				</div>

				<!-- 업로드 버튼 -->
				<div>
					<button type="button" id="uploadBtn"
						class="btn form-control bg-info">
						<span class="text-white">업로드</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</c:if>
	
<script>
	$(document).ready(function(){
		
		$('#fileUploadBtn').on('click', function(e){
			e.preventDefault();
			$('#file').click();
		});
		
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
			formData.append("imgPath", $('input[name=image]')[0].files[0]); 
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
		
	});

</script>