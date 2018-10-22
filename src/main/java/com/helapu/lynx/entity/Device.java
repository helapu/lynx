package com.helapu.lynx.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.helapu.lynx.entity.enums.ProductEnum;

@SuppressWarnings("serial")
public class Device extends SuperEntity<Device> {
	
	private Long userId;
	
	private String nickname; //昵称
	private ProductEnum product; //产品key
	private String deviceKey; //设备key
	private String deviceSecret; //设备secret
	private String iotid;
	private LocalDateTime activeAt; //激活时间
	private String status;
	private String region; //区域
	private String nodeType; //节点类型
	private LocalDateTime onlineAt; //最后上线时间
	private String ipAddress; //IP地址
	private String firmwareVersion; //固件版本
	
	@TableField(exist = false)
	private String productKey;
	
	@TableField(exist = false)
	private String productNickname;
	
	//
	
	public String getProductKey() {
		return this.product.getKey();
	}
	public String getProductNickname() {
		return this.product.getNickname();
	}
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
	
	public LocalDateTime getActiveAt() {
		return activeAt;
	}
	public void setActiveAt(LocalDateTime activeAt) {
		this.activeAt = activeAt;
	}
	public LocalDateTime getOnlineAt() {
		return onlineAt;
	}
	public void setOnlineAt(LocalDateTime onlineAt) {
		this.onlineAt = onlineAt;
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
