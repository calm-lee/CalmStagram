package com.calmstagram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.calmstagram.post.BO.LikeBO;
import com.calmstagram.post.BO.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// 글쓰기
	@RequestMapping("/post_create")
	public Map<String, Object> createPost(
		     @RequestParam("content") String content
		    , @RequestParam(value="file", required=false) MultipartFile file
		    , HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		Map<String, Object> result = new HashMap<>();
		
		int row = postBO.addPost(userId, userName, content, file);
		
		if(row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	
	// 좋아요 반영
		@RequestMapping("/like_status")
		public Map<String, Object> like(
				@RequestParam("postId") int postId,
				HttpServletRequest request){
			
			Map<String, Object> result = new HashMap<>();
			
			HttpSession session = request.getSession();
			
			Integer userId = (Integer) session.getAttribute("userId");
			
			if(userId == null) { // 로그인이 안된 상태이면
				result.put("result", "fail"); // 실패
			} else { // 로그인이 된 상태이면
				likeBO.like(userId, postId); // 좋아요 추가 or 삭제
				result.put("result", "success");
			}
			return result;
		}	
		
		
	// 글 삭제
		@RequestMapping("/delete_post")
		public Map<String, Object> deletePost(
				@RequestParam("postId") int postId,
				HttpServletRequest request){
			
			Map<String, Object> result = new HashMap<>();
			
			HttpSession session = request.getSession();
			
			int userId = (int) session.getAttribute("userId");
			
			int row = postBO.deletePostByPostIdAndUserId(postId, userId); // 본인이 해당 포스트를 삭제해야 하므로 userId, postId 모두 필요
			
			if(row > 0) {
				result.put("result", "success");
			} else {
				result.put("result", "fail");
			}
			
			return result;
		}	
}
