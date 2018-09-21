package com.helapu.lynx.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.helapu.lynx.entity.enums.AgeEnum;
import com.helapu.lynx.entity.enums.PhoneEnum;

/**
 * 用户表
 */
public class User extends SuperEntity<User> {

	private static final long serialVersionUID = 1L;
	
    private String nickname;
    private String username;
    
    @TableField("mobile")
    @TableLogic
    private String mobile;
    private String encryptedPassword;
    private String lastLoginAt;
    private String createdAt;
    

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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getLastLoginAt() {
		return lastLoginAt;
	}
	public void setLastLoginAt(String lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
    public String toString() {
		return "User id: " + this.getId()
		        + "tenant_id: " + this.getTenantId()
				+ " mobile: " + this.getMobile()
				+ " username: " + this.getMobile()
				+ " password: " + this.getEncryptedPassword();
    }

}
