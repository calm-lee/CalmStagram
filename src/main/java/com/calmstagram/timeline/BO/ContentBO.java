package com.calmstagram.timeline.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.comment.BO.CommentBO;
import com.calmstagram.post.BO.PostBO;
import com.calmstagram.post.Post.Post;
import com.calmstagram.timeline.DAO.ContentDAO;
import com.calmstagram.timeline.Model.Content;

@Service
public class ContentBO {
	
	@Autowired
	private PostBO postBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private ContentDAO contentDAO;
	
	
	public List<Content> getContentList(){
		
		//1. contentList 만들기
		List<Content> contentList = new ArrayList<>();
		
		//2. contentList에 post 넣기
		
		// (1) 반복문에 쓸 postList 호출
		List<Post> postList = postBO.getPostList();
		
		// (2) 반복문으로 각 content에 post, commentList 넣기
		for(Post post : postList) {
			
			Content content = new Content();
			
			//setPost로 지금 post값 넣기
			content.setPost(post);
			
			//setCommentList로 댓글 넣기
			content.setCommentList(commentBO.getCommentList(post.getId()));
			
			contentList.add(content);
		}
		
		return contentList;
		
	}
	
	// 좋아요 추가
	public int addLike(int userId, int postId) {
		return contentDAO.insertLike(userId, postId);
	}
}
