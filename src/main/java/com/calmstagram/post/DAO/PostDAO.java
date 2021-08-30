package com.calmstagram.post.DAO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface PostDAO {

	public int insertPost
	(@Param("userId") int userId
    , @Param("userName") String userName
    , @Param("content") String content
    , @Param("imgPath") MultipartFile imgPath);
	
}
