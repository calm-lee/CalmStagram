package com.calmstagram.post.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.calmstagram.post.Post.Post;

@Repository
public interface PostDAO {

	public int insertPost
	(@Param("userId") int userId
    , @Param("userName") String userName
    , @Param("content") String content
    , @Param("imgPath") String imgPath);
	
	public List<Post> selectPostList();
}
