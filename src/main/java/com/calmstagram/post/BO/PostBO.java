package com.calmstagram.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.post.DAO.PostDAO;
import com.calmstagram.post.Post.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String userName, String content, String imgPath) {
		return postDAO.insertPost(userId, userName, content, imgPath);
	}
	
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
}
