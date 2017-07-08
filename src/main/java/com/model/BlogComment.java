package com.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="blogcomment")
public class BlogComment {

//	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq")
	@SequenceGenerator(name = "seq", sequenceName = "b_comment_seq")
private int blog_comnt_id;
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="commentedby")
private User commentedBy;
private Date commentedOn;
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="blogPost")
private BlogPost blogPost;
private String body;


public int getBlog_comnt_id() {
	return blog_comnt_id;
}
public void setBlog_comnt_id(int blog_comnt_id) {
	this.blog_comnt_id = blog_comnt_id;
}
public User getCommentedBy() {
	return commentedBy;
}
public void setCommentedBy(User commentedBy) {
	this.commentedBy = commentedBy;
}
public Date getCommentedOn() {
	return commentedOn;
}
public void setCommentedOn(Date commentedOn) {
	this.commentedOn = commentedOn;
}
public BlogPost getBlogPost() {
	return blogPost;
}
public void setBlogPost(BlogPost blogPost) {
	this.blogPost = blogPost;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}


}
