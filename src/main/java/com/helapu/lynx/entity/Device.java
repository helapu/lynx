package com.helapu.lynx.entity;

import com.helapu.lynx.entity.enums.ProductEnum;

@SuppressWarnings("serial")
public class Device extends SuperEntity<Device> {
	
	private Long userId;
	
	private String nickname; //昵称
	private ProductEnum product; //产品key
	private String deviceKey; //设备key
	private String deviceSecret; //设备secret
	private String iotid;
	//添加时间
	private String utcActive; //激活时间
	private String status;
	private String region; //区域
	private String nodeType; //节点类型
	private String utcOnline; //最后上线时间
	private String ipAddress; //IP地址
	private String firmwareVersion; //固件版本
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public ProductEnum getProduct() {
		return product;
	}
	public void setProduct(ProductEnum product) {
		this.product = product;
	}
	public String getDeviceKey() {
		return deviceKey;
	}
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}
	public String getDeviceSecret() {
		return deviceSecret;
	}
	public void setDeviceSecret(String deviceSecret) {
		this.deviceSecret = deviceSecret;
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
