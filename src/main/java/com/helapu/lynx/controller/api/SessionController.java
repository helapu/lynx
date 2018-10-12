package com.helapu.lynx.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.helapu.lynx.aliyun.oss.AppletOSS;
import com.helapu.lynx.common.BCrypt;
import com.helapu.lynx.common.JWTUtil;
import com.helapu.lynx.config.ErrorCode;
import com.helapu.lynx.config.properties.JWTProperties;
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.entity.Follow;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.entity.Verifycode;
import com.helapu.lynx.entity.enums.GoodIdentifierEnum;
import com.helapu.lynx.mapper.UserMapper;
import com.helapu.lynx.service.IDeviceService;
import com.helapu.lynx.service.IFollowService;
import com.helapu.lynx.service.IUserService;
import com.helapu.lynx.service.IVerifycodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;


@RestController
@RequestMapping("/api/auth")
@Api(tags="会话管理")
public class SessionController extends ApiController {
	
	@Autowired
	private IFollowService followService;
	
	@Autowired
    private IDeviceService deviceService;
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IVerifycodeService verifycodeService;
    
//    @Autowired
//    private IAppletOssService  appletOssService;
    
    @PostMapping("/login")
    @ApiOperation(value="登录")
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
    	
    	logger.debug("user" + user);
    	if ( BCrypt.checkpw(password, user.getEncryptedPassword() ) ) {
    		
    		logger.warn("login success and mobile: " + user.getMobile());
    		String token = JWTUtil.sign(user.getMobile());
    		logger.debug("token " + token);
    		user.setLoginAt(new Timestamp(System.currentTimeMillis()));
    		return this.success( token );
    	}else {
    		return this.failed(ErrorCode.USER_PASSWORD);
    	}
    }
    
    @PostMapping("/register")
    @ApiOperation(value="注册")
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
    	
    	logger.debug("user " + user);
    	Verifycode lastRegisterCode = verifycodeService.getOne(new QueryWrapper<Verifycode>()
    			.lambda().eq(Verifycode::getMobile, mobile)
    			.eq(Verifycode::getType, "register")
    			.eq(Verifycode::getCode, code)
    			);
    	//
		
    	logger.debug("code" + lastRegisterCode);
    	
		// 校验验证码
    	if (lastRegisterCode == null) {
    		return this.failed(ErrorCode.VERIFYCODE_NOTFOUND);
    	}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -3);// 3分钟之前的时间
		beforeTime.getTime();
		Date beforeD = beforeTime.getTime();
		
		if ( lastRegisterCode.getCreatedAt().before(Timestamp.valueOf(sdf.format(beforeD) ))) {
			return this.failed(ErrorCode.VERIFYCODE_EXPIRED);
		}
    	
    	if(user != null) {
    		return this.failed(ErrorCode.USER_EXIST);
    	}
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

		User newUser = new User();
		newUser.setMobile(mobile);
		newUser.setEncryptedPassword( hashed );
		
		newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		userService.save(newUser);
		
		return this.success(newUser);
    	
    }

    @PostMapping("/forgot")
    @ApiOperation(value="忘记密码")
    public R<Object> forgot(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile,
    		@Size(min=6, max=24, message="密码为6-24位")
    		@RequestParam String password,
    		@Size(min=6, max=24, message="四位验证码")
    		@RequestParam String code) {
    	//
    	
    	User user = userService.getOne(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));
    	
    	logger.debug("user " + user);
    	Verifycode lastForgotCode = verifycodeService.getOne(new QueryWrapper<Verifycode>()
    			.lambda().eq(Verifycode::getMobile, mobile)
    			.eq(Verifycode::getType, "forgot")
    			.eq(Verifycode::getCode, code)
    			);
    	//
		
    	logger.debug("code" + lastForgotCode);
    	
		// 校验验证码
    	if (lastForgotCode == null) {
    		return this.failed(ErrorCode.VERIFYCODE_NOTFOUND);
    	}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -3);// 3分钟之前的时间
		beforeTime.getTime();
		Date beforeD = beforeTime.getTime();
		
		if ( lastForgotCode.getCreatedAt().before(Timestamp.valueOf(sdf.format(beforeD) ))) {
			return this.failed(ErrorCode.VERIFYCODE_EXPIRED);
		}
    	
    	if(user == null) {
    		return this.failed(ErrorCode.USER_NOT_EXIST);
    	}
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());


		user.setEncryptedPassword( hashed );
		
//		userService.save(user);
		userService.update(user, null);
		
		return this.success(user);
    	
    }
    
}
