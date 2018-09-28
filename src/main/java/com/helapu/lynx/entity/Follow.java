package com.helapu.lynx.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;

public class Follow extends SuperEntity<Follow> {
	

	private Long userId;
	private Long deviceId;
	private Boolean owner;
	private Timestamp createdAt;
	private String nickname;
	private String comment;
	private Boolean active;
	
	@TableField(exist = false)
	private String productKey;

	@TableField(exist = false)
	private String deviceKey;
	
	@TableField(exist = false)
	private String deviceSecret;
	
	@TableField(exist = false)
	private String iotid;
	
	@TableField(exist = false)
	private String utcActive;
	
	@TableField(exist = false)
	private String status;
	
	@TableField(exist = false)
	private String region;
	
	@TableField(exist = false)
	private String nodeType;
	
	@TableField(exist = false)
	private String utcOnline;
	
	@TableField(exist = false)
	private String ipAddress;
	
	@TableField(exist = false)
	private String firmwareVersion;
	
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}


	public Boolean getOwner() {
		return owner;
	}


	public void setOwner(Boolean owner) {
		this.owner = owner;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

 
	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public String getProductKey() {
		return productKey;
	}


	public void setProductKey(String productKey) {
		this.productKey = productKey;
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



