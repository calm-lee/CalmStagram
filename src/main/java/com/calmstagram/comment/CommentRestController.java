package com.calmstagram.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calmstagram.comment.BO.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentBO commentBO;

	
	@RequestMapping("/create_comment")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpServletRequest request
			){
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		Map<String, Object> result = new HashMap<>();
		
		//DB에 코멘트 넣기
		int row = commentBO.addComment(postId, userId, userName, content);
		
		if(row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@RequestMapping("/delete_comment")
	public Map<String, Object> deleteComment(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId"); // 로그인한 사람이 지울 수 있도록
		String userName = (String) session.getAttribute("userName");
		
		Map<String, Object> result = new HashMap<>();

		int row = commentBO.deleteCommentById(userName);
		
		if(row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
}
