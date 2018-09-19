package com.helapu.lynx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.helapu.lynx.common.BCrypt;
import com.helapu.lynx.common.JWTUtil;
import com.helapu.lynx.config.ErrorCode;
import com.helapu.lynx.config.properties.JWTProperties;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.mapper.UserMapper;
import com.helapu.lynx.service.IUserService;

import java.util.List;

import javax.validation.constraints.*;


@RestController
@RequestMapping("/auth")
public class SessionController extends ApiController {
	
    @Autowired
    private IUserService userService;
	
    @Autowired
	private JWTProperties jwtPropertis;
	
    @PostMapping("/login")
    public R<Object> login(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile,
    		@Size(min=6, max=24, message="密码为6-24位")
    		@RequestParam String password) {
    	//
    	User user = userService.getOne(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));
    	
    	if(user == null) {
    		return this.failed(ErrorCode.USER_NOT_EXIST);
    	}
    	
    	if ( BCrypt.checkpw(password, user.getEncryptedPassword() ) ) {
    		
    		logger.warn("login success and mobile: " + user.getMobile());
    		return this.success( JWTUtil.sign(user.getMobile()) );
    	}else {
    		return this.failed(ErrorCode.USER_PASSWORD);
    	}
    }
    
    @PostMapping("/register")
    public R<Object> register(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile,
    		@Size(min=6, max=24, message="密码为6-24位")
    		@RequestParam String password,
    		@Size(min=6, max=24, message="四位验证码")
    		@RequestParam String code) {
    	//
    	User user = userService.getOne(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));

    	if(user == null) {
    		// TODO 校验验证码
    		
        	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

    		User newUser = new User();
    		newUser.setMobile(mobile);
    		newUser.setEncryptedPassword( hashed );
    		userService.save(newUser);
    		
    		return this.success(newUser);
    	}else {
    		return this.failed(ErrorCode.USER_EXIST);
    	}
    }

    @PostMapping("/forgot")
    public R<Object> forgot(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile,
    		@Size(min=6, max=24, message="密码为6-24位")
    		@RequestParam String password,
    		@Size(min=6, max=24, message="四位验证码")
    		@RequestParam String code) {
    	//
    	
    	User user = userService.getOne(new QueryWrapper<User>()
    			.eq("mobile", mobile));
    	if(user == null) {
    		return this.failed(ErrorCode.USER_EXIST);

    	}else {

    		// TODO 校验验证码
    		
        	String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
    		user.setEncryptedPassword( hashed );
    		userService.save(user);
    		
    		return this.success(user);
    	}
    	
    }
    @PostMapping("/test")
    public R<Object> test(String password) {
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    	return this.success(hashed);
    }

}
