package com.helapu.lynx.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.helapu.lynx.common.YunpianUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/verify_codes")
@Api(tags="验证码")
public class VerifyCodeController extends ApiController {

	@PostMapping("/register")
    @ApiOperation(value="注册验证码")
    public R<Object> register(String mobile) {
		String beforeShuffle = "0123456789";
		StringBuilder sb = new StringBuilder(4);
		for(int i=0; i<4; i++) {
			char ch= beforeShuffle.charAt(new Random().nextInt(beforeShuffle.length()));
			sb.append(ch);
		}
    	String code = sb.toString();
    	
    	YunpianUtil.sendVerifyCode(mobile, code);
    	
    	return success(code);
    }
    
	@PostMapping("/forgot")
    @ApiOperation(value="注册验证码")
    public R<Object> forgot(String mobile) {
		String beforeShuffle = "0123456789";
		StringBuilder sb = new StringBuilder(4);
		for(int i=0; i<4; i++) {
			char ch= beforeShuffle.charAt(new Random().nextInt(beforeShuffle.length()));
			sb.append(ch);
		}
    	String code = sb.toString();
    	
    	YunpianUtil.sendVerifyCode(mobile, code);
    	
    	return success(code);  	
    }
    
}






