package com.ivan.tpp.boot.business.error.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ivan.tpp.boot.business.error.model.ResponseEntity;
import com.ivan.tpp.boot.core.enums.SysResponseEnum;
import com.ivan.tpp.boot.core.enums.SysStatusEnum;
import com.ivan.tpp.boot.util.ResponseUtils;

@RestControllerAdvice
public class WebErrorController {
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", exception.getMessage());
		ResponseEntity<Map<String,String>> resEntity = new ResponseEntity<Map<String,String>>();
		ResponseUtils.errorResWrap(resEntity, SysResponseEnum.SYSTEM_EXCEPTION, SysStatusEnum.SERVER_EXCEPTION, request);
		return JSON.toJSONString(resEntity);
	}
}
