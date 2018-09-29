package com.helapu.lynx.controller.api;




import java.util.List;

import javax.validation.constraints.Size;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.helapu.lynx.common.BCrypt;
import com.helapu.lynx.config.ErrorCode;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
@Api(tags="用户")
public class UserController extends ApiController {

    @Autowired
    private IUserService userService;
   
    // 更换手机号码
    @PostMapping("/change_mobile")
    @ApiOperation(value="更换手机号码")
    public R<Object> changeMobile(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String new_mobile,
    		@Size(min=4, max=4, message="4位验证码")
    		@RequestParam String code) {
    	//
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	user.setMobile(new_mobile);
    	
    	userService.save(user);
    	return this.success(user);
    }
    
    // 修改密码
    @PostMapping("/change_password")
    @ApiOperation(value="修改密码")
    public R<Object> changePassword(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String new_password,
    		@Size(min=4, max=4, message="4位验证码")
    		@RequestParam String code) {
    	//
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	String hashed = BCrypt.hashpw(new_password, BCrypt.gensalt(12));
    	user.setEncryptedPassword(hashed);
    	
    	userService.save(user);
    	return this.success(user);
    }
    
    // 设置昵称
    @PostMapping("/change_nickname")
    @ApiOperation(value="修改昵称")
    public R<Object> changeNickname(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String nickname) {
    	//
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	user.setNickname(nickname);
    	
    	userService.save(user);
    	return this.success(user);
    }
    
    // 搜索用户
    @PostMapping("/search_username")
    @ApiOperation(value="搜索用户")
    public R<Object> searchUsername(
    		@Size(min=11, max=11, message="手机号码为11位")
    		@RequestParam String mobile) {
    	//
    	List<User> userList = userService.list(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));
    	if( userList.size() == 0) {
    		return this.failed(ErrorCode.USER_NOT_EXIST);
    	}
    	User user = userList.get(0);
    	return this.success(user);
    }
    
}
