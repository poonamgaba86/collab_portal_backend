package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.dao.UserDAO;
import com.model.User;
import com.model.Error;
@RestController
public class UserController 
{
	 @Autowired
	 UserDAO userDao; 
	 @Autowired 
	 User user;
	
	 
	 
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() 
    {
    	System.out.println("controller called");
        List<User> users = userDao.findAllUsers();
        if(users.isEmpty())
        {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    } 
	
/*	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserDetails(@PathVariable("id") String id)
	{
		System.out.println("controller called for id"+id);
		user=userDao.get(id);
		
		if(user==null)
		{
			 return new ResponseEntity<User>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session){
		//ONLY FOR AUTHENTICATION
	          User user=(User)session.getAttribute("user");
	          if(user==null){
	        	 Error error=new Error(3,"Unauthorized user..");
	        	 return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	          }
	          else
	          {
	        	  user=userDao.get(user.getUser_id());
	        	  return new ResponseEntity<User>(user,HttpStatus.OK);
	          }
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?>register(@RequestBody User user)
	{
		
		if(userDao.get(user.getUser_id())!= null)
		{
			
			Error error=new Error(2," Cannot have duplicate values " );
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			user.setIsOnline('N');
			user.setReason(null);
			user.setRole("student");
			user.setStatus('N');
			
			if(userDao.save(user))
			{
			System.out.println("record successfully entered");
			return new ResponseEntity<User>(user,HttpStatus.OK);
			}
			else
			{
				Error error=new Error(2," Could not insert user detail " );
				return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
		}
		
	}
	
	@RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
	public ResponseEntity<User>accept(@PathVariable("id")String id)
	{
		user=updateStatus(id,'A',"");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reject/{id}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User>reject(@PathVariable("id")String id,@PathVariable("reason")String reason)
	{
		updateStatus(id,'R',reason);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	private User updateStatus(String id,char status,String reason)
	{
		user=userDao.get(id);
		if(user==null)
		{
			
		}
		else
		{
		user.setStatus(status);	
		user.setReason(reason);
		userDao.update(user);
		}
		return user;
	}
	
	@RequestMapping(value="/updateuser",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User updatedUserDetails,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null){
			Error error=new Error(3,"Unauthorized user..");
	   	 return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			//firstname = John, lastname=Smith
			userDao.update(updatedUserDetails);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
	}
	
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session)
	{
		System.out.println("controller called for validate");
		user=userDao.validate(user.getUser_id(),user.getPassword());
		System.out.println("controller called for user"+user);
		if(user==null)
		{
			Error error=new Error(3,"Invalid credentials.. please enter valid username and password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else
		{
			
			user.setIsOnline('Y');
			userDao.update(user);
			session.setAttribute("user", user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session)
	{
		//String loggedin=(String)session.getAttribute("user");
	//	user=userDao.get(loggedin);
		User user=(User)session.getAttribute("user");
		user.setIsOnline('N');
		session.invalidate();
		if(userDao.update(user))
		{
			return new ResponseEntity<User>(user,HttpStatus.OK);	
		}
		else
		{
			Error error=new Error(3,"unauthorized user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		
	}
	

}
