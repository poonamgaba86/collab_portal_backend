package com.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.dao.*;
import com.model.*;
@Repository("userDao")
public class UserDaoImpl  implements UserDAO
{
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	User user;
	
	//-----------------------------------------------
	
	public UserDaoImpl(SessionFactory sessionFactory) {
		try {
			System.out.println("Inside SessionFactory constructor  ====");

			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	@Transactional
	public List<User> findAllUsers() 
	{
		System.out.println("find  allll  ====");
		//Session s=sessionFactory.openSession();
		//Transaction tx=s.getTransaction();
		//tx.begin();
	//	List<User> showuser = sessionFactory.openSession().createQuery("FROM User").list();
		List<User> showuser = sessionFactory.getCurrentSession().createQuery("FROM User").list();

         for(User u:showuser)
         {
        	 System.out.println(u.getEmail());
         }

		//tx.commit();
		//s.flush();
		//s.clear();
		//s.close();
		return showuser;
	}
	@Transactional
	public List<String> getOnlineUsers()
{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("select name  from User where isOnline='Y'");
		System.out.println("online users called");
		List<String> onlineUsers =query.list();
		for(String u:onlineUsers)
        {
       	 System.out.println(u);
        }
		session.close();
		return  onlineUsers;
}
	@Transactional
	public User get(String id)
	{
		System.out.println("find  id"+id);
		user=(User)sessionFactory.getCurrentSession().get(User.class,id);
		
		return user;
	}

	@Transactional	
	public boolean save(User user)
	{
		try
		{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
		 		
	}
	@Transactional
	public boolean update(User user)
	{
		System.out.println("update called  ====");
		try
		{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	@Transactional
	public User validate(String id,String password)
	{
		System.out.println("validate called of daoimpl  ====");
		String hql ="from User where id ='"+id+"'and password ='"+password+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return (User)query.uniqueResult();
	
	}
	
}
