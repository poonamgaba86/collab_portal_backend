package com.dao;

import com.model.ProfilePicture;

public interface ProfileUploadDao {
	void save(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);

}
