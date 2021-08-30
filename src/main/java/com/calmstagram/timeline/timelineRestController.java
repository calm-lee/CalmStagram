package com.calmstagram.timeline;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calmstagram.timeline.BO.ContentBO;

@RequestMapping("/timeline")
@RestController
public class timelineRestController {

	@Autowired
	private ContentBO contentBO;
	
	// 좋아요 추가 
	@RequestMapping("/add_like")
	public Map<String, Object> addLike(@Param("postId") int postId, HttpServletRequest request){
		
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		
		int row = contentBO.addLike(userId, postId);
		
		if(row == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}		
}
