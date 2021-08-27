package com.calmstagram.timeline.Model;

import java.util.List;

import com.calmstagram.comment.Model.Comment;
import com.calmstagram.post.Post.Post;

public class Content {
	
	
	private Post post;
	private List<Comment> commentList;
	private boolean filledLike;
	private int likeCount;

}
