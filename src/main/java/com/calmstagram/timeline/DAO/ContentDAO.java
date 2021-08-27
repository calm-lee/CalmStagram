package com.calmstagram.timeline.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.calmstagram.timeline.Model.Content;

@Repository
public interface ContentDAO {

	public List<Content> selectContent(int userId);
	
}
