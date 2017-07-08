package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="friend")
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq")
	@SequenceGenerator(name = "seq", sequenceName = "friend_seq")
private int friend_id;
	@Column(name="from_id")
private String from;
	@Column(name="to_id")
private String to;
private char status;

public int getFriend_id() {
	return friend_id;
}
public void setFriend_id(int friend_id) {
	this.friend_id = friend_id;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}

}
