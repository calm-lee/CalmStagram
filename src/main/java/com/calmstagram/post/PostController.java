package com.calmstagram.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calmstagram.timeline.BO.ContentBO;
import com.calmstagram.timeline.Model.Content;

@RequestMapping("/timeline")
@Controller
public class PostController {

	@Autowired
	private ContentBO contentBO;
	
	@RequestMapping("/timeline_view")
	public String postCreate(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		
		List<Content> contentList = contentBO.getContentList(userId);
		
		model.addAttribute("contentList", contentList);
		model.addAttribute("viewName", "timeline/timeline");
		return "layout/template";
	}
	
}
