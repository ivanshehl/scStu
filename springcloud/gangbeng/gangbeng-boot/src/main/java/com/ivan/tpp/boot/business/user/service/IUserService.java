package com.ivan.tpp.boot.business.user.service;

import com.ivan.tpp.boot.business.user.model.User;

public interface IUserService {

	public User getUserById(Long id);
	
	public void addUser(User user);
	
}
