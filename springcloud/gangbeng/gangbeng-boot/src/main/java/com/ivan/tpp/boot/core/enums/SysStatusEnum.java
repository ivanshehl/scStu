package com.ivan.tpp.boot.core.enums;

public enum SysStatusEnum {
	
	SUCCESS(200,"success"),
	SERVER_EXCEPTION(500,"server exception")
	;
	
	private int status;
	
	private String msg;
	
	private SysStatusEnum(int status,String msg){
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static String getMsgByCode(String status){
		if(status == null){
			return "";
		}
		SysResponseEnum[] enums = SysResponseEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if(enums[i].getCode().equals(status)){
				return enums[i].getMsg();
			}
		}
		return "";
	}

}
