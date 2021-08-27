package com.calmstagram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calmstagram.post.BO.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	// 글쓰기
	@RequestMapping("/post_create")
	public Map<String, Object> createPost(
		     @RequestParam("content") String content
		    , @RequestParam(value="imgPath", required=false) String imgPath
		    , HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		Map<String, Object> result = new HashMap<>();
		
		int row = postBO.addPost(userId, userName, content, imgPath);
		
		if(row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
}
