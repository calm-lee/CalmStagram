package com.calmstagram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calmstagram.common.EncryptUtils;
import com.calmstagram.user.BO.UserBO;
import com.calmstagram.user.User.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;

	// 아이디 중복 찾기
	@RequestMapping("/is_duplicated")
	public Map<String, Object> isDuplicated(@RequestParam(value="loginId", required=false) String loginId){
		
		Map<String, Object> result = new HashMap<>();
		int user = userBO.getExsistedUser(loginId);
		
		if(user > 0) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		
		return result;
	}
	
	// 회원가입하기
	@RequestMapping("/sign_up_for_submit")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		String encrpytedPassword = EncryptUtils.md5(password);
		
		int row = userBO.addUser(loginId, encrpytedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		
		if(row == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	// 로그인
	@RequestMapping("sign_in_check")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request){

		
		
		
		Map<String, Object> result = new HashMap<>();
		
		//password 암호화
		String encrpytedPassword = EncryptUtils.md5(password);
		
		User user = userBO.getUserByIdAndPassword(loginId, encrpytedPassword);

		if(user != null) {
			// 세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId", user.getLoginId()); // 로그인한 id 가져오기
			session.setAttribute("userId", user.getId()); // 로그인한 넘버 가져오기
			session.setAttribute("userName", user.getName()); // 로그인한 이름 가져오기
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
}

