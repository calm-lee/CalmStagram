package com.calmstagram.post.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.post.DAO.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요 추가
	public void like(Integer userId, int postId) {
		
		boolean existedLike = existedLike(userId, postId);
		
		if(existedLike == true) { // 이미 내가 좋아요 누른 상태이면
			likeDAO.deleteLikeByPostIdUserId(userId, postId); // 좋아요 취소
		} else { // 좋아요 누르지 않은 상태이면
			likeDAO.insertLike(userId, postId); // 좋아요 추가
		}
	
	}
	
	// 현재 내가 좋아요 누른 상태
	public boolean existedLike(Integer userId, int postId) {
		
		if(userId == null) { // 비로그인 상태일 경우
			return false; // 좋아요 안 보임
		} 
		
		int count = likeDAO.selectLikeByUserIdOrPostId(postId, userId);
		
		return count > 0 ? true : false; // 좋아요 개수가 0보다 크면 true
		
	}
	
	// 좋아요 개수 구하기
	public int getLikeCountByPostId(int postId) { // 포스트 당 좋아요 개수이기 때문에 userId 필요없음
		return likeDAO.selectLikeByUserIdOrPostId(postId, null);
	}
	
	// 좋아요 삭제 (postId)
	/*
	 * public void deleteLikeByPostId(int postId) {
	 * likeDAO.deleteLikeByPostId(postId); }
	 */
	
}
