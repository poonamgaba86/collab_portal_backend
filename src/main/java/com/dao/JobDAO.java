package com.dao;

import java.util.List;

import com.model.Job;

public interface JobDAO {
	
	public void saveJobDetails(Job job);
	public List<Job> getAllJobDetails();
	public Job getJobById(int id);
	

}
