package com.calmstagram.user.DAO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.calmstagram.user.User.User;

@Repository
public interface UserDAO {
	
	public User selectUserByIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);
	
	public int selectUserByLoginId(@Param("loginId") String loginId);
	
	public int insertUser(@Param("loginId") String loginId, @Param("password") String password, @Param("name") String name, @Param("email") String email);
	
}
