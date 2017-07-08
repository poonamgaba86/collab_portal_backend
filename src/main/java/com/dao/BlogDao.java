package com.dao;

import java.util.List;

import com.model.BlogComment;
//import com.DaoImpl.List;
import com.model.BlogPost;

public interface BlogDao {
	void saveBlogPost(BlogPost blogPost);
	public List<BlogPost> getBlogPosts(int approved);
	public BlogPost getBlogPost(int id);
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogPostId);
	void update(BlogPost blogPost);
	
}
