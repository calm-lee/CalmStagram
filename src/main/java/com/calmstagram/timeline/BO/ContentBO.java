package com.calmstagram.timeline.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calmstagram.timeline.DAO.ContentDAO;
import com.calmstagram.timeline.Model.Content;

@Service
public class ContentBO {
	
	@Autowired
	private ContentDAO contentDAO;
	
	public List<Content> getContentList(int userId){
		return contentDAO.selectContent(userId);
	};
	
}
