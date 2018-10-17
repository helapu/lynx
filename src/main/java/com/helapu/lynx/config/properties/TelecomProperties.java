package com.helapu.lynx.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component(value="Telecom")
@ConfigurationProperties(prefix = TelecomProperties.PREFIX)
public class TelecomProperties {
	
	public static final String PREFIX = "telecom";
	
	
	private static String platformIp;
	private static String platformPort;
	private static String appId;
	private static String appSecret;
	
	
	
	
	public static String getPlatformIp() {
		return platformIp;
	}
	@Value("${telecom.platform-ip}")
	public static void setPlatformIp(String platformIp) {
		TelecomProperties.platformIp = platformIp;
	}
	public static String getPlatformPort() {
		return platformPort;
	}
	@Value("${telecom.platform-port}")
	public static void setPlatformPort(String platformPort) {
		TelecomProperties.platformPort = platformPort;
	}
	public static String getAppId() {
		return appId;
	}
	@Value("${telecom.app-id}")
	public static void setAppId(String appId) {
		TelecomProperties.appId = appId;
	}
	public static String getAppSecret() {
		return appSecret;
	}
	@Value("${telecom.app-secret}")
	public static void setAppSecret(String appSecret) {
		TelecomProperties.appSecret = appSecret;
	}
}
