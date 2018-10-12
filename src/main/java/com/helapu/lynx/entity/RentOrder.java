package com.helapu.lynx.entity;

import java.sql.Timestamp;

@SuppressWarnings("serial")
public class RentOrder extends SuperEntity<RentOrder> {
	private String mobile;
	private String content;
	private Timestamp createdAt;
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	
}
