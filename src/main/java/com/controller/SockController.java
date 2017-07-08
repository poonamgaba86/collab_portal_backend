package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDAO;
import com.model.Chat;
import com.model.User;


@RestController





public class SockController {
	
	
private static final Log logger = LogFactory.getLog(SockController.class);

	
private final SimpMessagingTemplate messagingTemplate;
	
private List<String> users = new ArrayList<String>();
	
@Autowired
	
private UserDAO userDao;

	
@Autowired
	
public SockController( SimpMessagingTemplate messagingTemplate) {
		
this.messagingTemplate = messagingTemplate;
	
}
		
	
@SubscribeMapping("/join")
	
public List<String> join() {
		
System.out.println("inside join method in sock controller");
	
String username=null;
		
//System.out.println(username);
	
List<String> onlineUsers=	userDao.getOnlineUsers();
	
System.out.println("users " + users);
	
System.out.println("online users " + onlineUsers );
		
for(String s : onlineUsers)
		{
			
if(!users.contains(s)){
				
username=s;
				
users.add(s);
			
}
		
}
		
for(String u:users){
			
if(!onlineUsers.contains(u)){
			
	users.remove(u);
			
}
		
}
		
System.out.println("====JOIN==== " + username );
		
// notify all subscribers of new user
		
messagingTemplate.convertAndSend("/topic/join",username);
		
		
return users;
	
}
	
	
	
	
public void remove(String username){
		
users.remove(username);
	
}

	
@MessageMapping(value="/chat")
	
public void chatReveived(Chat chat) {
		
		
System.out.println("From value in chatreceived in chat object " + chat.getFrom());
				
System.out.println(chat.getFrom());
		
if("all".equals(chat.getTo())) {
			
System.out.println("IN CHAT REVEIVED " + chat.getMessage() + " " + chat.getFrom() + " to " + chat.getTo());
			
messagingTemplate.convertAndSend("/queue/chats", chat);
		
}
		
else {
			
System.out.println("CHAT TO " + chat.getTo() + " From " + chat.getFrom() + " Message " + chat.getMessage());
		
			
messagingTemplate.convertAndSend( "/queue/chats/"+chat.getTo(),chat);
			
messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(),chat);
		
}
		
	
}
	
	
	


}
