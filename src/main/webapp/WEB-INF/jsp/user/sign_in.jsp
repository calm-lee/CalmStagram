<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<div class="d-flex mt-5 justify-content-center">


</div> 

<div class="d-flex justify-content-center">

<form id="loginForm" method="post" action="/user/sign_in">	
	<div class="login-box form-control">
		
		<div>	
		<h3 class="text-center logo_bk mt-3">Outstagram</h3>
		<div style="font-size:14px;" class="text-center" style="color:3f3f40">어떤 일이 벌어지고 있을까요?</div>
		</div>		
			<div class="d-flex justify-content-center align-items-center mt-4">
				<div class="mark-id input-group-prepand form-control">ID</div>
				<input type="text" class="form-control col-7" id="loginId" name="loginId" placeholder="Username">
			</div>
			
			<div class="d-flex justify-content-center align-items-center mt-1">
				<div class="mark-pw input-group-prepand form-control">PW</div>
				<input type="password" class="form-control col-7" id="password" name="password" placeholder="password">
			</div>
		</div>
			
			<div class="d-flex justify-content-center">
			<button type="submit" id="loginBtn" class="btn btn-info btn-block mt-3 form-control" style="width: 450px; height: px;">로그인</button>	
			</div>	
			
			<div class="d-flex justify-content-center mt-2">
				<span style="color:#3f3f40">계정이 없으신가요?</span>
				<a href="/user/sign_up" class="ml-2" style="color:#3f3f40"><b>가입하기</b></a>	
			</div>		
	</form>			
</div>

<script>
$(document).ready(function(){
	$('#loginBtn').on('click',function(e){
		e.preventDefault();
		
		// 유효성 검사
		
		let loginId = $("input[name=loginId]").val().trim();
		
		if(loginId == ''){
			alert("아이디를 입력하세요.");
			return;
		}
		
		let password = $("input[name=password]").val().trim();
		
		if(password == ''){
			alert("비밀번호를 입력하세요.");
			return;
		}
		
		
		//AJAX
		let url = "/user/sign_in_check";
		let data = $('#loginForm').serialize();
		
		$.post(url,data).done(function(data){
			if(data.result == "success"){
				alert("환영합니다!");
				location.href="/timeline/timeline_view";
			} else {
				alert("아이디와 비밀번호를 확인해주세요.");
			}
		});
		
	});
	
});

</script>