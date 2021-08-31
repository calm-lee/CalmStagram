package com.calmstagram.post.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.calmstagram.post.Model.Post;

@Repository
public interface PostDAO {

	public int insertPost
	(@Param("userId") int userId
    , @Param("userName") String userName
    , @Param("content") String content
    , @Param("imgPath") String imgPath);
	
	public List<Post> selectPostList();
	
	public int deletePostByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);
}
