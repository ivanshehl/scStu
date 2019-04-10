package com.ivan.tpp.boot.business.user.controller;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivan.tpp.boot.business.user.model.User;
import com.ivan.tpp.boot.business.user.service.IHystrixService;
import com.ivan.tpp.boot.business.user.service.IUserService;
import com.ivan.tpp.boot.business.user.service.impl.HelloWorldCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	private static AtomicInteger idx = new AtomicInteger();

	@Autowired
	private RabbitTemplate jsonRabbitTemplate;
	
	@Autowired
	private RabbitTemplate simpleRabbitTemplate;
	
	@Autowired
	private IUserService userService;
	
	@Resource(name="hystrixService")
	private IHystrixService hystrixService;
	
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
		logger.info("getVirtualHost={};", jsonRabbitTemplate.getConnectionFactory().getUsername());
//		Message
//		rabbitTemplate.send("exchangeOne", "exchangeOne.queue.key", buildMessage());
//		rabbitTemplate.send(buildMessage());
		sendObject();
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
	
	private Message buildMessage(){
		Integer val = idx.addAndGet(1);
		MessageProperties properties = new MessageProperties();
		Message message = new Message(("tes2222222222t"+idx).getBytes(), properties);
		properties.setHeader("comein", val);
		properties.setHeader("time1", 1);
		properties.setHeader("time2", 1);
		properties.setHeader("time3", 1);
		
		return message;
	}
	
	private void sendObject(){
		simpleRabbitTemplate.convertAndSend("exchangeOne", "exchangeOne.queue.key", buildMessage());
			
	}
	
	private void sendJson(){
		User mmu = new User();
		mmu.setAge(16);
		mmu.setName("1234");
		jsonRabbitTemplate.convertAndSend("exchangeOne", "exchangeOne.queue.key", mmu);
	}
	
	@RequestMapping(value = { "/helloHy" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public String helloHy() {
		
		HelloWorldCommand command = new HelloWorldCommand();
		String rlt = command.execute();
		logger.info("username={};", rlt);
		return rlt;
	}
	
	@RequestMapping(value = { "/hyAnto" }, method = { RequestMethod.POST,
			RequestMethod.GET })
	public String hyAnto(){
		logger.info("============================/user/hyAnto===========================");
		return hystrixService.login();
	}
	
	
	
}
