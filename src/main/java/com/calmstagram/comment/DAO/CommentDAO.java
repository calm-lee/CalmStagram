package com.calmstagram.comment.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.calmstagram.comment.Model.Comment;

@Repository
public interface CommentDAO {
	
	public List<Comment> selectCommentList(@Param("postId") int postId);
	
	public int insertComment(
			@Param("postId") int postId
			, @Param("userId") int userId
			, @Param("userName") String userName
			, @Param("content") String content);
	
	public int deleteCommentById(String userName);
	
}
