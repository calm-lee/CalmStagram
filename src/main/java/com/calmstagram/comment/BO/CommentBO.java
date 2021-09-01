package com.calmstagram.comment.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.comment.DAO.CommentDAO;
import com.calmstagram.comment.Model.Comment;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	public List<Comment> getCommentList(int postId){
		return commentDAO.selectCommentList(postId);
	}
	
	public int addComment(int postId, int userId, String userName, String content) {
		return commentDAO.insertComment(postId, userId, userName, content);
	}
	
	public int deleteCommentByUserName(String userName) {
		return commentDAO.deleteCommentByUserName(userName);
	}
	
	// 글 작성자가 content를 모두 삭제할 때 용도(userId 본인일 필요 없음)
	public void deleteCommentByPostId(int postId) {
		commentDAO.deleteCommentByPostId(postId);
	}
}
