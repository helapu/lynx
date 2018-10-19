package com.helapu.lynx.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductEnum implements IEnum<String> {
	
	SMOKEDETECTION_V1("SMOKEDETECTION_V1", "somekey", "somkesecret", "第一代烟雾报警器");

	private String value;
	private String key;
	private String secret;
    private String nickname;
    
    ProductEnum(final String value, final String key, final String secret, final String nickname) {
        this.value = value;
        this.key = key;
        this.secret = secret;
        this.nickname = nickname;
    }

    @Override
    public String getValue() {
        return this.value;
    }

	public String getKey() {
		return key;
	}

	public String getSecret() {
		return secret;
	}

	public String getNickname() {
		return nickname;
	}

}
