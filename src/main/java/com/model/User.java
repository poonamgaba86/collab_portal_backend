package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Users_C")
@Component
public class User implements Serializable
{
	@Id
	private String user_id;
	private String name;
	private String password;
	private String address;
	private String email;
	private String mobile;
	private char status;
	private String reason;
	private String role;
		@Column(name="is_Online")
	private char isOnline;
		
	
	

		public String getUser_id() {
			return user_id;
		}


		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getMobile() {
			return mobile;
		}


		public void setMobile(String mobile) {
			this.mobile = mobile;
		}


		public char getStatus() {
			return status;
		}


		public void setStatus(char status) {
			this.status = status;
		}


		public String getReason() {
			return reason;
		}


		public void setReason(String reason) {
			this.reason = reason;
		}


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}


		public char getIsOnline() {
			return isOnline;
		}


		public void setIsOnline(char isOnline) {
			this.isOnline = isOnline;
		}


	public User()
	{
		
	}
	

}
