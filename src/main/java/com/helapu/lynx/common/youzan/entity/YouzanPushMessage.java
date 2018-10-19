package com.helapu.lynx.common.youzan.entity;

public class YouzanPushMessage {

	private String mode;
	private String id;
	private String client_id;
	private String type;
	private String status;
	private String msg;
	private String kdt_id;
	private String sign;
	private String version;
    private boolean test = false;
    private String send_count;
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getKdt_id() {
		return kdt_id;
	}
	public void setKdt_id(String kdt_id) {
		this.kdt_id = kdt_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isTest() {
		return test;
	}
	public void setTest(boolean test) {
		this.test = test;
	}
	public String getSend_count() {
		return send_count;
	}
	public void setSend_count(String send_count) {
		this.send_count = send_count;
	}
	
	@Override
	public String toString() {
		return "[YouzanPushMessage client_id=" + this.getClient_id() + " version=" + this.getVersion() + " msg=" + this.getMsg() + "]";
	}

}
