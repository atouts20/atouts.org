package com.aatout.service;

import com.aatout.model.AppRole;
import com.aatout.model.AppUser;

public interface AccountService {
	
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public AppUser findUserByUsername(String username);
	public AppUser findByConfirmationToken(String confirmationToken);
	public void addRoleToUse(String username, String roleName);
	public AppUser findById(Long id);
	//public void addRoleToUse(String username, AppRole appRole);
}
