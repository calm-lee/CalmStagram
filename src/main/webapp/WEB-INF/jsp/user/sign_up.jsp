<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap d-flex justify-content-center">
	<div>
	<h2><b>회원가입</b></h2>
	<form id="signUpForm" method="post" action="/user/sign_up_for_submit">
		<div class="sign-up-box">
		
			<div class="m-3">
			<span class="sign-up-subject">ID</span>
			<div class="d-flex">
				<input type="text" id="loginId" name="loginId" class="form-control col-8" placeholder="아이디를 입력해주세요.">
				<button type="button" id="checkBtn" class="form-control btn-info col-2 ml-2" style="font-size: 12px;">중복확인</button>
			</div>
			</div>
			
			<div id="statusArea">
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용 중인 아이디입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 아이디입니다.</div>
			</div>

			<div class="m-3">
			<span class="sign-up-subject">password</span>
			<input type="password" id="password" name="password" class="form-control col-6" placeholder="비밀번호를 입력해주세요.">
			</div>
						
			<div class="m-3">
			<span class="sign-up-subject">confirm password</span>
			<input type="password" id="confirmPassword" name="confirmPassword" class="form-control col-6" placeholder="비밀번호를 입력해주세요.">
			</div>
			
			<div class="m-3">
			<span class="sign-up-subject">이름</span>
			<input type="text" id="name" name="name" class="form-control col-8" placeholder="이름을 입력해주세요.">
			</div>
			
			<div class="m-3">
			<span class="sign-up-subject">이메일</span>
			<input type="text" id="email" name="email" class="form-control col-8" placeholder="이메일을 입력해주세요."> 
			</div>
			
			
			<div class="d-flex justify-content-center m-3">
				<button type="button" id="signUpBtn" class="btn btn-info">회원가입</button>		
			</div>
		</div>
	</form>
	</div>
</div>
<script>
	$(document).ready(function(){
		
		// 아이디 유효성 검사
		$('#checkBtn').on('click', function(e){
			
			var loginId = $('#loginId').val().trim();
			
			// ID 4자 이상이 아니면 경고 노출 (if문 이용)
			if(loginId.length < 4){
				$('#idCheckLength').removeClass('d-none');
				$('#idCheckDuplicated').addClass('d-none');
				$('#idCheckOk').addClass('d-none');
				return;
			}
			
			// 중복 여부는 DB를 조회해야 하므로 서버에 묻는다. (AJAX 이용)
			// 화면을 중복시키지 않고 AJAX 통신으로 중복 여부 확인하고 동적으로 문구 노출
			$.ajax({
				url: "/user/is_duplicated"
				, type: 'POST'
				, data: {"loginId": loginId}
				, success: function(data){
					if(data.result == true){ // 중복일 경우
						$('#idCheckDuplicated').removeClass('d-none');
						$('#idCheckLength').addClass('d-none');
						$('#idCheckOk').addClass('d-none');
					} else { // 중복이 아닐 경우
						$('#idCheckOk').removeClass('d-none');
						$('#idCheckLength').addClass('d-none');
						$('#idCheckDuplicated').addClass('d-none');
					}
				}
				, error: function(e){
					alert("아이디 중복확인에 실패했습니다. 관리자에게 문의해주십시오." + e);
				}
			});
		});
		
		$('#signUpBtn').on('click',function(e){
			e.preventDefault; // submit 기본동작 중단
			
			// 유효성 검사
			let loginId = $("input[name=loginId]").val().trim();
			
			if(loginId == ''){
				alert("아이디를 입력하세요.");
				return;
			}
			
			let password = $("input[name=password]").val().trim();
			
			let confirmPassword = $("input[name=confirmPassword]").val().trim();
			if(password == '' || confirmPassword == ''){
				alert("비밀번호를 입력하세요.");
				return;
			}
			
			
			if(password != confirmPassword){
				alert("비밀번호가 일치하지 않습니다.");
				
				// 비밀번호가 일치하지 않으면 input창 초기화
				$('#password').val('');
				$('#confirmPassword').val('');
				return;
			}
			
			let name = $("input[name=name]").val().trim();
			if(name == ''){
				alert("이름을 입력하세요.");
				return;
			}
			
			let email = $("input[name=email]").val().trim();
			if(email == ''){
				alert("이메일을 입력하세요.");
				return;
			}
			
			//아이디 중복확인이 완료됐는지 확인
			// idCheckOk -> d-none이 remove 됐으면 OK라고 가정
			if($('#idCheckOk').hasClass('d-none')){
				alert("아이디 중복확인을 해주세요.");
				return;
			}
			
			// 서버로 보내는 방법
			// --1) submit
			// --2) AJAX
			
			// 1) submit : name 속성에 있는 값들이 서버로 넘어간다. (RequestParam)
			// $(this)[0].submit(); //submit은 submit 후 전체화면을 이동시키는 경우 사용한다.
			
			// 2) AJAX
			
			let url = '/user/sign_up_for_submit';
			let formData = $('#signUpForm').serialize();

			$.post(url,formData).done(function(formData){
				if(formData.result == "success"){
					alert("회원 가입을 환영합니다. 로그인을 해주세요.");
					location.href = "/user/sign_in";
				} else {
					alert("회원가입에 실패했습니다. 다시 시도해주세요.");
				}
			});			
		});		
	}); 
				
</script>