package com.helapu.lynx.controller.api;

import java.sql.Timestamp;

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
import com.helapu.lynx.common.JWTUtil;
import com.helapu.lynx.config.ErrorCode;
import com.helapu.lynx.entity.Feedback;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.service.IDeviceService;
import com.helapu.lynx.service.IFeedbackService;
import com.helapu.lynx.service.IFollowService;
import com.helapu.lynx.service.IUserService;
import com.helapu.lynx.service.IVerifycodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/feedbacks")
@Api(tags="反馈")
public class FeedbackController extends ApiController {
	

	@Autowired
	private IFeedbackService feedbackService;
	
    @Autowired
    private IUserService userService;
    
    @PostMapping("")
    @ApiOperation(value="app反馈")
    public R<Object> login(
    		@Size(min=8, max=200, message="提交内容")
    		@RequestParam String content) {
    	//
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	Feedback feedback = new Feedback();
    	feedback.setUserId(user.getId());
    	feedback.setContent(content);
    	feedback.setCreatedAt( new Timestamp(System.currentTimeMillis()));
    	feedbackService.save(feedback);

    	return this.success(feedback);
    }
    
}
