<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div class="bar d-flex justify-content-between">
		<div class="mt-3 ml-5">
			<span class="calmstagram"><b>Calmstargram</b></span>
		</div>
		<div class="justify-content-end mt-5 mr-5">
			
			<!-- 로그인이 된 경우 -->
			<c:if test="${not empty userName}">
				<span class="mr-3"><b>${userName}</b>님 안녕하세요!</span>
				<a href="/user/sign_out">로그아웃</a>
			</c:if>
			
			<!-- 로그인 안 된 경우 -->
			<c:if test="${empty userName}">
				<a href="/user/sign_in"><span>로그인</span></a>
			</c:if>
				
		</div>
	</div>
</header>