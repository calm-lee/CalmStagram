package com.calmstagram.user.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.user.DAO.UserDAO;
import com.calmstagram.user.User.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	// 로그인용
	public User getUserByIdAndPassword(String loginId, String password) {
		return userDAO.selectUserByIdAndPassword(loginId, password);
	}
	
	// 중복확인용
	public int getExsistedUser(String loginId) {
		return userDAO.selectUserByLoginId(loginId);
	}
	
	// 회원가입용
	public int addUser(String loginId, String password, String name, String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
	
	
}
