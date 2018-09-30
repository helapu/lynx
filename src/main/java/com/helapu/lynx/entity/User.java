package com.helapu.lynx.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.helapu.lynx.entity.enums.AgeEnum;
import com.helapu.lynx.entity.enums.PhoneEnum;

import lombok.Data;

/**
 * 用户表
 */

public class User extends SuperEntity<User> {

	private static final long serialVersionUID = 1L;
	
    private String nickname;
    private String username;
    
    private String mobile;
    private String email;
    private String encryptedPassword;
    private Timestamp loginAt;
    private Timestamp createdAt;
        
    
    public User() {
    }
    
    public User(String nickname) {
    	this.nickname = nickname;
    }
    
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public Timestamp getLoginAt() {
		return loginAt;
	}

	public void setLoginAt(Timestamp loginAt) {
		this.loginAt = loginAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
    public String toString() {
		return "User id: " + this.getId()
		        + "tenant_id: " + this.getTenantId()
		        + "nickname: " + this.getNickname()
				+ " mobile: " + this.getMobile()
				+ " username: " + this.getMobile()
				+ " password: " + this.getEncryptedPassword();
    }

}
