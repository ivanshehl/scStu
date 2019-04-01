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
	
//	@Transactional(propagation=Propagation.NEVER,rollbackFor=Exception.class)
	@Override
	public User getUserById(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		User mmu = new User();
		mmu.setAge(70);
		mmu.setName("xiaobai");
		userMapper.insert(mmu);
		if(true){
			throw new RuntimeException("getUserByIdgetUserByIdgetUserByIdgetUserById");
		}
		return user;
	}

	@Override
	public void addUser(User user) {
		User temp = getUserById(1L);
		User mmu = new User();
		mmu.setAge(70);
		mmu.setName("xiaobai");
		userMapper.insert(mmu);
		if(true){
			throw new RuntimeException("12345");
		}
	}

	
}
