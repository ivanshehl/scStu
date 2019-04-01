package com.ivan.tpp.boot.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.ivan.tpp.boot.business.error.model.ResponseEntity;
import com.ivan.tpp.boot.core.enums.SysResponseEnum;
import com.ivan.tpp.boot.core.enums.SysStatusEnum;

public class ResponseUtils {

	private ResponseUtils() {

	}

	public static ResponseEntity<?> errorResWrap(ResponseEntity<?> resEntity,
			SysResponseEnum resEnum, SysStatusEnum status,WebRequest request) {
		resEntity.setStatus(status.getStatus());
		resEntity.setCode(resEntity.getCode());
		resEntity.setMsg(resEnum.getMsg());
		resEntity.setParams(request.getParameterMap());
		resEntity.setUri(getServletPath(request));
		return resEntity;
	}
	
	public static String getServletPath(WebRequest request){
		NativeWebRequest nativeWebRequest = (NativeWebRequest)request;
		HttpServletRequest httpRequest = (HttpServletRequest)nativeWebRequest.getNativeRequest();
		String servletPath = (String) httpRequest.getAttribute(WebUtils.INCLUDE_SERVLET_PATH_ATTRIBUTE);
		if (servletPath == null) {
			servletPath = httpRequest.getServletPath();
		}
		return servletPath;
	}
}
