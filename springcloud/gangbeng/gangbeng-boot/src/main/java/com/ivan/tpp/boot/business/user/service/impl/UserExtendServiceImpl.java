package com.ivan.tpp.boot.business.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.tpp.boot.business.user.dao.UserMapper;
import com.ivan.tpp.boot.business.user.model.User;
import com.ivan.tpp.boot.business.user.service.IUserExtendService;

@Service("userExtendService")
public class UserExtendServiceImpl implements IUserExtendService {
	@Autowired
	private UserMapper mapper;

	@Override
	public User userInfo(Long id) {
		// TODO Auto-generated method stub
		User user = mapper.selectByPrimaryKey(1L);
		return user;
	}
}
