package com.ivan.tpp.boot.business.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivan.tpp.boot.business.user.model.User;
import com.ivan.tpp.boot.business.user.service.IUserService;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public User login(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		User user = new User();
		user.setId(1);
		user.setName("ivanshe");
		user.setAge(35);
		logger.info("username={}; passowrd={}", username, password);
		return user;
	}

	@RequestMapping(value = { "/select" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public User selectUser(@RequestParam(value = "id", required = false) Long id) {
		User user = userService.getUserById(id);
		logger.info("username={};", user);
		return user;
	}

	@RequestMapping(value = { "/addUser" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public User addUser(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "age", required = false) Integer age) {
		User user = new User();
		user.setAge(age);
		user.setName(name);
		userService.addUser(user);
		return user;
	}

}
