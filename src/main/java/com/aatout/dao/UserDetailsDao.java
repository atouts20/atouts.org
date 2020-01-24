package com.aatout.dao;

import com.aatout.model.UserAttempts;

public interface UserDetailsDao {
	void updateFailAttempts(String username);
	void resetFailAttempts(String username);
	UserAttempts getUserAttempts(String username);

}
