package com.helapu.lynx.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component(value="Aliyun")
@ConfigurationProperties(prefix = AliyunIotProperties.PREFIX)
public class AliyunIotProperties {
	
	public static final String PREFIX = "aliyun";
	
	
	private static String endpoint;
	private static String accessKeyId;
	private static String accessKeySecret;
	private static String bucketName;
		
	public static String getEndpoint() {
		return AliyunIotProperties.endpoint;
	}
	@Value("${aliyun.oss.endpoint}")
	public void setEndpoint(String endpoint) {
		AliyunIotProperties.endpoint = endpoint;
	}
	
	public static String getAccessKeyId() {
		return AliyunIotProperties.accessKeyId;
	}
	@Value("${aliyun.oss.accessKey-id}")
	public void setAccessKeyId(String accessKeyId) {
		AliyunIotProperties.accessKeyId = accessKeyId;
	}
	
	public static String getAccessKeySecret() {
		return AliyunIotProperties.accessKeySecret;
	}
	@Value("${aliyun.oss.accessKey-secret}")
	public static void setAccessKeySecret(String accessKeySecret) {
		AliyunIotProperties.accessKeySecret = accessKeySecret;
	}
	
	public static String getBucketName() {
		return AliyunIotProperties.bucketName;
	}
	@Value("${aliyun.oss.bucket-name}")
	public static void setBucketName(String bucketName) {
		AliyunIotProperties.bucketName = bucketName;
	}
	
}
