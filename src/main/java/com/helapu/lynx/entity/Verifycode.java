package com.helapu.lynx.entity;

import java.sql.Timestamp;

public class Verifycode extends SuperEntity<Verifycode> {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String mobile;
	private String type;
	private Integer resultCode;
	private String message;
	private Timestamp createdAt;
	
	
//	[java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.sql.Timestamp, java.lang.Long]

	public Verifycode() {
		
	}
	public Verifycode(String code, String mobile, String type, Integer resultCode, String message) {
		this.code = code;
		this.mobile = mobile;
		this.type = type;
		this.resultCode = resultCode;
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
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Verifycode [Mobile:" + this.getMobile()
				+ ", code:" + this.getCode()
				+ ", msg:" + this.getMessage();
	}
	
}
