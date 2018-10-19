package com.helapu.lynx.entity.telecom;

public class NotifyDeviceDataChangedDTO {

	private String notifyType;
	private String deviceId;
	private String gatewayId;
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	
	@Override
	public String toString() {
		return "[DeviceDataChanged: deviceId=" + this.getDeviceId() + " gatewayId=" + this.getGatewayId() + "]";
	}
	
	
}

