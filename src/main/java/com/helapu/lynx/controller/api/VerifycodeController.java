package com.helapu.lynx.controller.api;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.helapu.lynx.common.YunpianUtil;
import com.helapu.lynx.config.ErrorCode;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.entity.Verifycode;
import com.helapu.lynx.service.IUserService;
import com.helapu.lynx.service.IVerifycodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/verifycodes")
@Api(tags="验证码")
public class VerifycodeController extends ApiController {

	@Autowired
	private IVerifycodeService verifycodeService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/register")
    @ApiOperation(value="注册验证码")
    public R<Object> register(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile) {
		
		User user = userService.getOne(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));
    	
    	if(user != null) {
    		return this.failed(ErrorCode.USER_EXIST);
    	}
    	
		return sendCode(mobile, "register");
    }
    
	@PostMapping("/forgot")
    @ApiOperation(value="忘记密码验证码")
    public R<Object> forgot(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile) {
		User user = userService.getOne(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));
    	
    	if(user == null) {
    		return this.failed(ErrorCode.USER_NOT_EXIST);
    	}
		return sendCode(mobile, "forgot");
    }
	
	private R<Object> sendCode(String mobile, String type) {
		
		String beforeShuffle = "0123456789";
		StringBuilder sb = new StringBuilder(4);
		for(int i=0; i<4; i++) {
			char ch= beforeShuffle.charAt(new Random().nextInt(beforeShuffle.length()));
			sb.append(ch);
		}
		Timestamp  now = new Timestamp(System.currentTimeMillis());
		// 一分钟最多发送三条短信
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -2);// 3分钟之前的时间
		beforeTime.getTime();
		Date beforeD = beforeTime.getTime();
		
		int lastCodes = verifycodeService.count(new QueryWrapper<Verifycode>()
				.lambda().eq(Verifycode::getMobile, mobile)
				.ge(Verifycode::getCreatedAt, Timestamp.valueOf(sdf.format(beforeD)))
				.isNotNull(Verifycode::getCreatedAt));
		
		logger.warn("verifycodes count " + lastCodes);
		if (lastCodes > 3) {
			return this.failed(ErrorCode.SMS_FAILED);
		}
		
    	String code = sb.toString();
    	
    	Map<String, Object>  codeResult= YunpianUtil.sendVerifyCode(mobile, code);
    	
    	logger.warn("yunpian result " + codeResult );
    	
    	Verifycode verifycode = new Verifycode(code, mobile, type, (Integer)codeResult.get("code"), (String)codeResult.get("msg"));
    	verifycode.setCreatedAt( now );

    	verifycodeService.save(verifycode);
    	
    	if((Integer)codeResult.get("code") == 0) {
        	return success( verifycode );
    	}else {
    		return this.failed( (String)codeResult.get("msg"));
    	}
    	
	}
    
}






