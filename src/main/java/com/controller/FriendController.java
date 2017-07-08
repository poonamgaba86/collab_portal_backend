package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.FriendDao;
import com.model.Friend;
import com.model.User;
import com.model.Error;

@RestController

public class FriendController {
	@Autowired
private FriendDao friendDao;
	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
public ResponseEntity<?> getAllUsers(HttpSession session){
	User user=(User)session.getAttribute("user");
	
	if(user==null){
		Error error=new Error(1,"Unauthroized user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<User> users=friendDao.getSuggestedUsers(user);
	return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	
}
	@RequestMapping(value="/friendRequest/{to}",method=RequestMethod.PUT)
public ResponseEntity<?> friendRequest(@PathVariable String to,HttpSession session){
		User user=(User)session.getAttribute("user");
		
		if(user==null){
			Error error=new Error(1,"Unauthroized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		friendDao.friendRequest(user.getName(), to);
		return new ResponseEntity<Void>(HttpStatus.OK);
}
	
	@RequestMapping(value="/pendingRequests",method=RequestMethod.GET)
public ResponseEntity<?> pendingRequests(HttpSession session){
User user=(User)session.getAttribute("user");
		
		if(user==null){
			Error error=new Error(1,"Unauthroized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> pendingRequests= friendDao.pendingRequests(user.getName());
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
		
}	
	@RequestMapping(value="/updatependingrequest/{from}/{status}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@PathVariable String from,@PathVariable char status,HttpSession session){
	User user=(User)session.getAttribute("user");
			
			if(user==null){
				Error error=new Error(1,"Unauthroized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			 friendDao.updatePendingRequest(from,user.getName(),status);
			return new ResponseEntity<Void>(HttpStatus.OK);
			
	}	
	@RequestMapping(value="/friendslist",method=RequestMethod.GET)
	public ResponseEntity<?> getAllFriends(HttpSession session){
	User user=(User)session.getAttribute("user");
			
			if(user==null){
				Error error=new Error(1,"Unauthroized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			List<Friend> friends= friendDao.listOfFriends(user.getName());
			return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
			
	}	

}