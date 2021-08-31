package com.calmstagram.post.DAO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public int selectLikeByUserIdOrPostId(@Param("postId") int postId, @Param("userId") Integer userId);
	
	public void insertLike(@Param("userId") int userId, @Param("postId") int postId);
	
	public void deleteLikeByPostIdUserId(@Param("userId") int userId, @Param("postId") int postId);
	
	/* public void deleteLikeByPostId(int postId); */
}
