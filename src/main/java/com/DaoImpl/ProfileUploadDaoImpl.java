package com.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ProfileUploadDao;
import com.model.ProfilePicture;

@Repository
public class ProfileUploadDaoImpl implements ProfileUploadDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void save(ProfilePicture profilePicture) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(profilePicture);
		session.flush();
		session.close();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProfilePicture getProfilePic(String username) {
		Session session=sessionFactory.openSession();
		ProfilePicture profilePic=(ProfilePicture)
				session.get(ProfilePicture.class, username);
		session.close();
		return profilePic;
		// TODO Auto-generated method stub
		
	}

}
