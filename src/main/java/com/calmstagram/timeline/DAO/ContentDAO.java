package com.calmstagram.timeline.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ContentDAO {
	
	public int insertLike(@RequestParam("userId") int userId, @RequestParam("postId") int postId);
	
}
