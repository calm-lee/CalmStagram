package com.calmstagram.post.BO;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.calmstagram.common.FileManagerService;
import com.calmstagram.post.DAO.PostDAO;
import com.calmstagram.post.Model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	// 글 삽입하기
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		String imgPath = null;
		
		if(file != null) {
			try {
				imgPath = fileManagerService.saveFile(userId, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return postDAO.insertPost(userId, userName, content, imgPath);
	}
	
	// 글 가져오기
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	// 글 삭제하기
	public int deletePostByPostIdAndUserId(int postId, int userId) {
		return postDAO.deletePostByPostIdAndUserId(postId, userId);
	}
	
}
