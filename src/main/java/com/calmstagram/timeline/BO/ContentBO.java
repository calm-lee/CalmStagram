package com.calmstagram.timeline.BO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.comment.BO.CommentBO;
import com.calmstagram.post.BO.LikeBO;
import com.calmstagram.post.BO.PostBO;
import com.calmstagram.post.Model.Post;
import com.calmstagram.timeline.Model.Content;

@Service
public class ContentBO {
	
	@Autowired
	private PostBO postBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;

	public List<Content> getContentList(Integer userId){ // filledLike을 위해 userId(Integer) 추가
		
		//1. contentList 만들기
		List<Content> contentList = new ArrayList<>();
		
		//2. contentList에 post 넣기
		
		// (1) 반복문에 쓸 postList 호출
		List<Post> postList = postBO.getPostList();
		
		// (2) 반복문으로 각 content에 post, commentList 넣기
		for(Post post : postList) {
			
			Content content = new Content();
			Content deleteContent = new Content();
			
			//setPost로 지금 post값 넣기
			content.setPost(post);
			
			//setCommentList로 댓글 넣기
			content.setCommentList(commentBO.getCommentList(post.getId()));
			
			// 좋아요 상태
			content.setFilledLike(likeBO.existedLike(userId, post.getId()));
			
			// 좋아요 개수 넣기
			content.setLikeCount(likeBO.getLikeCountByPostId(post.getId()));
			
			contentList.add(content);
		}
		
		return contentList;
		
	}
	
}
