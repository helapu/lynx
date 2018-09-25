package com.helapu.lynx.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = LynxProperties.PREFIX)
public class LynxProperties {

    public static final String PREFIX = "lynx";

    private String yunpian;

	public String getYunpian() {
		return yunpian;
	}

	public void setYunpian(String yunpian) {
		this.yunpian = yunpian;
	}
	
}

