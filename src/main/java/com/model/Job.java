package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="job")
@Component
public class Job implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq")
	@SequenceGenerator(name = "seq", sequenceName = "job_seq")
	//one sequence job_seq was created in oracle
	private int id;
	private String jobTitle;
	private String jobDescr;
	private String location;
	private int yrsOfExp;
	private String company;
	private String skillsRequired;
	private String salary;
	private Date PostedOn;
	private boolean Active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobDescr() {
		return jobDescr;
	}
	public void setJobDescr(String jobDescr) {
		this.jobDescr = jobDescr;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getYrsOfExp() {
		return yrsOfExp;
	}
	public void setYrsOfExp(int yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}
	public Date getPostedOn() {
		return PostedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.PostedOn = postedOn;
	}
	
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
		
		
	

	
	
}
