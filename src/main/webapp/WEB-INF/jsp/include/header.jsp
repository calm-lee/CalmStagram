<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="mb-4">
 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<div class="header d-flex justify-content-between">

<!-- 로고 -->
	<div class="d-flex align-items-center ml-5">
		<a href="/timeline/timeline_view"><span class="logo">Outstagram</span></a>
	</div>

<!-- 검색 -->
	<div class="d-flex justify-content-center align-items-center col-7">
	<div class="col-7 input-group">
		<input type="text" id="hashtagWord" class="form-control" placeholder="검색">
		<div class="input-group-append">
			<button type="button" id="searchWordBtn" class="btn"><div style="color:#7d7d7d"><i class="fas fa-search fa-sm"></i></div></button>
		</div>
	</div>
	</div>

<!-- 로그아웃 -->
		<div class="d-flex align-items-center mr-5">
		
			<!-- 로그인이 된 경우 -->
			
			<c:if test="${not empty userName}">
				<span class="mr-3" style="color:#3f3f40"><b>${userName}</b>님 안녕하세요!</span>
				<a href="/user/sign_out">로그아웃</a>
			</c:if>
			
			<!-- 로그인 안 된 경우 -->
			<c:if test="${empty userName}">
				<a href="/user/sign_in" style="color:#3f3f40"><span>로그인</span></a>
			</c:if>
				
		</div>
		
</div>

<!-- Modal -->
<div class="modal" id="profileEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  	<div class="modal-dialog" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title" id="exampleModalLabel">프로필 사진을 수정하시겠습니까?</h5>
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         			<span aria-hidden="true">&times;</span>
        		</button>
      		</div>
      		<div class="modal-body">
      			<div class="d-flex justify-content-center mb-2">
      				<div id="previewProfileImg"></div>
      			</div>
      			<div class="d-flex justify-content-center mb-2">
   					<div id="profileFileName"></div>
   				</div>
      			<div class="d-flex justify-content-center">
      			<button type="button" id="profileEditBtn" class="btn btn-success mr-2">수정하기</button>
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        		</div>
   			</div>
   			
    	</div>
  	</div>
</div>
</header>