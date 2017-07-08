package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="blog_post")

public class BlogPost {
//	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq")
	@SequenceGenerator(name = "seq", sequenceName = "blog_seq")
private int blogPost_id;
private Date createdOn;
@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn(name="createdby")
private User createdBy;
private String title;
@Lob
@Column(name="body")
private String body;

private boolean approved;
@OneToMany(mappedBy="blogPost",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
@JsonIgnore
private List<BlogComment> blogComments=new ArrayList<BlogComment>();

public List<BlogComment> getBlogComments() {
	return blogComments;
}

public void setBlogComments(List<BlogComment> blogComments) {
	this.blogComments = blogComments;
}



public int getBlogPost_id() {
	return blogPost_id;
}

public void setBlogPost_id(int blogPost_id) {
	this.blogPost_id = blogPost_id;
}

public Date getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}

public User getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}

public boolean isApproved() {
	return approved;
}

public void setApproved(boolean approved) {
	this.approved = approved;
}



}
