package com.ivan.tpp.boot.business.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ivan.tpp.boot.business.user.service.IUserExtendService;

@RestController
@RequestMapping("/userExtend")
public class UserExtendController {
	
	@Autowired
	private IUserExtendService userExtendService;
	
	@RequestMapping(value="info" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String getUserInfo(){
		return JSON.toJSONString(userExtendService.userInfo(1L));
	}
	
}
