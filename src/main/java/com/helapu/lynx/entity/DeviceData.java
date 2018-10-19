package com.helapu.lynx.entity;

import java.sql.Timestamp;

public class DeviceData extends SuperEntity<DeviceData> {
	
	private Long deviceId;
	private Timestamp reportAt;

	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}public Timestamp getReportAt() {
		return reportAt;
	}
	public void setReportAt(Timestamp reportAt) {
		this.reportAt = reportAt;
	}

}
