package com.helapu.lynx.entity;


@SuppressWarnings("serial")
public class Device extends SuperEntity<Device> {
	
	private String nickname;
	private String productKey;
	private String key;
	private String secret;
	private String iotid;
	private String utcActive;
	private String status;
	private String region;
	private String nodeType;
	private String utcOnline;
	private String ipAddress;
	private String firmwareVersion;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getIotid() {
		return iotid;
	}
	public void setIotid(String iotid) {
		this.iotid = iotid;
	}
	public String getUtcActive() {
		return utcActive;
	}
	public void setUtcActive(String utcActive) {
		this.utcActive = utcActive;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getUtcOnline() {
		return utcOnline;
	}
	public void setUtcOnline(String utcOnline) {
		this.utcOnline = utcOnline;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getFirmwareVersion() {
		return firmwareVersion;
	}
	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}
	
	


}