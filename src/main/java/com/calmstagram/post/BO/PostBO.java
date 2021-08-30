package com.calmstagram.post.BO;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.calmstagram.common.FileManagerService;
import com.calmstagram.post.DAO.PostDAO;
import com.calmstagram.post.Post.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
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
	
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
}
