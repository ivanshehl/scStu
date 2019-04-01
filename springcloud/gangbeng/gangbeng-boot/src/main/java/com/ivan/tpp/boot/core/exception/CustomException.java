package com.ivan.tpp.boot.core.exception;

import com.ivan.tpp.boot.core.enums.SysResponseEnum;

public class CustomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8518706207448674794L;
	
	private SysResponseEnum codeEnum;
	
	public CustomException(){}
	
	public CustomException(SysResponseEnum codeEnum){
		super(codeEnum.getCode()+"\t"+codeEnum.getMsg());
		this.codeEnum = codeEnum;
	}
	
	@Override
	public Throwable fillInStackTrace() {
	    return this;
	}
	

	public SysResponseEnum getCodeEnum() {
		return codeEnum;
	}

	public void setCodeEnum(SysResponseEnum codeEnum) {
		this.codeEnum = codeEnum;
	}

}
