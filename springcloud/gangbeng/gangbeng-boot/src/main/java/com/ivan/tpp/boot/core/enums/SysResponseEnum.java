package com.ivan.tpp.boot.core.enums;

public enum SysResponseEnum {
	SUCCESS_CODE("000000","成功"),
	
	/*
	 *  除成功为000000外，返回码格式为：系统编码 + 业务编码
	 *  galaxy系统编码为014，建议分类命名
	 *  参数问题		0140**
	 *  数据问题		0141**
	 *  系统问题		0142**
	 *  配置问题		0143**
	 *  第三方服务问题	0144**
	 */
	PARAMS_REQUIRED			("001", "参数缺失"), 
	PARAMS_INVALID			("002", "参数无效"),
	DATA_NOT_FOUND			("101", "查无记录"),
	DATA_PARSE_ERROR		("102", "数据解析异常"),
	CARRIER_COLLECT_ERROR	("103", "运营商数据采集异常"),
	EMAIL_COLLECT_ERROR		("104", "信用卡数据采集异常"),
	SYSTEM_ERROR			("999", "系统异常"),
	CONFIG_ERROR			("301", "系统配置异常"),
	SERVICE_NOT_FOUND		("302", "配置的服务不存在"),
	SERVICE_NOT_MATCH		("303", "配置的服务不匹配"),
	STRATEGY_HANDLE_ERROR	("304", "执行风控策略异常"),
	REJECT_CODE				("400", "风控拒绝"),
	NON_HANDLE_RISK_ERROR_CODE		("900", "未执行风控策略异常"),
	CRIF_RESULT_PARSE_ERROR	("901","Crif系统返回值解析异常"),
	CRIF_RESULT_UNEXPECTED	("902","Crif系统返回值缺失"),
	CRIF_STRATEGY_FLOW_ERROR	("903","Crif系统策略流异常"),
	SYSTEM_EXCEPTION	("999","Crif系统策略流异常")
	;
	
	private String code;
	private String msg;
	
	//子系统业务吗
	public static final String SYS_NO = "003";
	
	private SysResponseEnum(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static String getMsgByCode(String code){
		if(code == null){
			return "";
		}
		SysResponseEnum[] enums = SysResponseEnum.values();
		for (int i = 0; i < enums.length; i++) {
			if(enums[i].getCode().equals(code)){
				return enums[i].getMsg();
			}
		}
		return "";
	}
}
