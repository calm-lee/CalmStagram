package com.calmstagram.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calmstagram.user.BO.UserBO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	//로그인 페이지
	@RequestMapping("/sign_in")
	public String signIn(Model model) {		
		model.addAttribute("viewName", "user/sign_in");
		return "layout/template";
	}
	
	//회원가입 페이지
	@RequestMapping("/sign_up")
	public String signUp(Model model) {		
		model.addAttribute("viewName", "user/sign_up");
		return "layout/template";
	}
	
	// 로그아웃
	@RequestMapping("/sign_out")
	public String signOut(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		model.addAttribute("viewName", "user/sign_in");
		return "layout/template";
	}
}
