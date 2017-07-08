package com.dao;

import java.util.List;

import com.model.User;

public interface UserDAO
{
	public  List<User> findAllUsers();
	public User get(String id);
	public boolean save(User user);
	public boolean update(User user);
	public User validate(String is,String password);
	public List<String> getOnlineUsers();

}
