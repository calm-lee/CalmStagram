package com.calmstagram.post.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.post.DAO.PostDAO;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String userName, String content, String imgPath) {
		return postDAO.insertPost(userId, userName, content, imgPath);
	}
}
