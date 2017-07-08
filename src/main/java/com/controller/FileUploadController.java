package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dao.ProfileUploadDao;
import com.model.ProfilePicture;
import com.model.User;
import com.model.Error;

@RestController
public class FileUploadController {
	@Autowired
	private ProfileUploadDao profileUploadDao;
	@RequestMapping(value="/doUpload",method=RequestMethod.PUT)
	public ResponseEntity<?> uploadProfilePic(
			HttpSession session,@RequestParam CommonsMultipartFile fileUpload){
		System.out.println("inside do upload method");
			User user=(User)session.getAttribute("user");
			if(user==null){
				Error error=new Error(3,"Unauthorized user.. Please login");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			else{
				ProfilePicture profilePicture=new ProfilePicture();
				profilePicture.setUsername(user.getName());
				profilePicture.setImage(fileUpload.getBytes());
				profileUploadDao.save(profilePicture);
				System.out.println("image saved");
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
		
	}
		@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
			
			User user=(User)session.getAttribute("user");
			if(user==null)
				return null;
			else
			{
				
				ProfilePicture profilePic=profileUploadDao.getProfilePic(username);
				if(profilePic==null)
					return null;
				else
				{
					System.out.println("image is there");
					return profilePic.getImage();
				}
			}
			
			
			
		}


}
