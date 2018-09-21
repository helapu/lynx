package com.helapu.lynx.controller;

import java.util.Random;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.helapu.lynx.common.YunpianUtil;
import com.helapu.lynx.entity.Verifycode;
import com.helapu.lynx.service.IVerifycodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/verifycodes")
@Api(tags="验证码")
public class VerifycodeController extends ApiController {

	@Autowired
	private IVerifycodeService verifycodeService;
	
	@PostMapping("/register")
    @ApiOperation(value="注册验证码")
    public R<Object> register(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile) {
		String beforeShuffle = "0123456789";
		StringBuilder sb = new StringBuilder(4);
		for(int i=0; i<4; i++) {
			char ch= beforeShuffle.charAt(new Random().nextInt(beforeShuffle.length()));
			sb.append(ch);
		}
    	String code = sb.toString();
    	
    	String resultMsg = YunpianUtil.sendVerifyCode(mobile, code);
    	logger.warn("yunpian result " + resultMsg);
    	
    	
    	Verifycode verifycode = new Verifycode(code, mobile, "register", resultMsg);
    	verifycode.setMobile(mobile);
    	verifycodeService.save(verifycode);
    	
    	return success( verifycode );
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






