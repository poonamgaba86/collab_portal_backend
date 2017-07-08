package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.BlogDao;
import com.model.BlogComment;
import com.model.BlogPost;
import com.model.User;
import com.model.Error;
import com.model.Job;

@RestController
public class BlogPostController {
	@Autowired
	private BlogDao blogDao;
	@RequestMapping(value="/saveBlogPost",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost, HttpSession session )
	{
		System.out.println("backend method called");
		User user=(User)session.getAttribute("user");
		if(user==null){
			Error error=new Error(3,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			blogPost.setCreatedBy(user);
			blogPost.setCreatedOn(new Date());
			blogDao.saveBlogPost(blogPost);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/list/{approved}")
	public ResponseEntity<?> getBlogList(@PathVariable int approved,HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null){
	Error error=new Error(1,"Unauthroized user");
	return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<BlogPost> blogPosts=blogDao.getBlogPosts(approved);
	return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable(value="id") int id,
	HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null){
	Error error=new Error(1,"Unauthroized user");
	return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	//select * from blogpost where id=33
	System.out.println("blog id is "+id);
	BlogPost blogPost=blogDao.getBlogPost(id);
	return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}

	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthroized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		blogDao.addBlogComment(blogComment);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

	@RequestMapping(value="/getBlogComments/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> blogComments(@PathVariable int blogPostId,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthroized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments=blogDao.getBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	@RequestMapping(value="/updateApproval",method=RequestMethod.PUT)
	public ResponseEntity<?> updateApproval(@RequestBody BlogPost blogPost,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null){
			Error error=new Error(1,"Unauthroized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		blogDao.update(blogPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}




}
