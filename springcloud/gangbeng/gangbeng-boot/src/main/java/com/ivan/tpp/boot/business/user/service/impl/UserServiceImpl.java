package com.ivan.tpp.boot.business.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.tpp.boot.business.user.dao.UserMapper;
import com.ivan.tpp.boot.business.user.model.User;
import com.ivan.tpp.boot.business.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserById(Long id) {
		User user = new User();
		user.setAge(70);
		user.setName("xiaobai");
//		return userMapper.selectByPrimaryKey(id);
		userMapper.insert(user);
		return user;
	}

	@Override
	public void addUser(User user) {
		User temp = getUserById(1L);
		userMapper.insert(user);
	}

	
}
