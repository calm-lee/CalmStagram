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
	
	public int deleteCommentById(String userName) {
		return commentDAO.deleteCommentById(userName);
	}
}
