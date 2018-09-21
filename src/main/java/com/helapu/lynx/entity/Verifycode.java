package com.helapu.lynx.entity;

public class Verifycode extends SuperEntity<Verifycode> {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String mobile;
	private String type;
	private Integer resultCode;
	private String message;
	
	
	public Verifycode(String code, String mobile, String type, String message) {
		this.code = code;
		this.mobile = mobile;
		this.type = type;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
