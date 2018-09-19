package com.helapu.lynx.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = JWTProperties.PREFIX)
public class JWTProperties {
	
	public static final String PREFIX = "jwt";
	
    private Integer invalidateTime;
    private String secret;

	public Integer getInvalidateTime() {
		return invalidateTime;
	}
	public void setInvalidateTime(Integer invalidateTime) {
		this.invalidateTime = invalidateTime;
	}
	
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}

	
}

