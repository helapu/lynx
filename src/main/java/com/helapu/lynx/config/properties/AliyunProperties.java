package com.helapu.lynx.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component(value="Aliyun")
@ConfigurationProperties(prefix = AliyunProperties.PREFIX)
public class AliyunProperties {
	
	public static final String PREFIX = "aliyun";
	
	
	private static String endpoint;
	private static String accessKeyId;
	private static String accessKeySecret;
	private static String bucketName;
		
	public static String getEndpoint() {
		return AliyunProperties.endpoint;
	}
	@Value("${aliyun.oss.endpoint}")
	public void setEndpoint(String endpoint) {
		AliyunProperties.endpoint = endpoint;
	}
	
	public static String getAccessKeyId() {
		return AliyunProperties.accessKeyId;
	}
	@Value("${aliyun.oss.accessKey-id}")
	public void setAccessKeyId(String accessKeyId) {
		AliyunProperties.accessKeyId = accessKeyId;
	}
	
	public static String getAccessKeySecret() {
		return AliyunProperties.accessKeySecret;
	}
	@Value("${aliyun.oss.accessKey-secret}")
	public static void setAccessKeySecret(String accessKeySecret) {
		AliyunProperties.accessKeySecret = accessKeySecret;
	}
	
	public static String getBucketName() {
		return AliyunProperties.bucketName;
	}
	@Value("${aliyun.oss.bucket-name}")
	public static void setBucketName(String bucketName) {
		AliyunProperties.bucketName = bucketName;
	}
	
}
