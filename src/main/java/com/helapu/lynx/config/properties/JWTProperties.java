package com.helapu.lynx.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component(value="Jwt")
@ConfigurationProperties(prefix = JWTProperties.PREFIX)
public class JWTProperties {
	
	public static final String PREFIX = "jwt";
	
    private static Integer invalidate;
    private static String secret;

	public static Integer getInvalidate() {
		return invalidate;
	}
	
	@Value("${jwt.invalidate}")
	public void setInvalidate(Integer invalidate) {
		JWTProperties.invalidate = invalidate;
	}
	
	
	public static String getSecret() {
		return secret;
	}

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		JWTProperties.secret = secret;
	}

	
}
