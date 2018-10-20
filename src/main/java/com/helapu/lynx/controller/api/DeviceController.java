package com.helapu.lynx.controller.api;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helapu.lynx.config.ErrorCode;
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.entity.Follow;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.service.IDeviceService;
import com.helapu.lynx.service.IFollowService;
import com.helapu.lynx.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.sql.ResultSet;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/devices")
@Api(tags="设备")
public class DeviceController extends ApiController {
	
	@Autowired
    private IFollowService followService;
   
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IUserService userService;
	
    // 关注设备列表
    @GetMapping("")
    @ApiOperation(value="设备列表")
    public R<Object> index(Page page, boolean listMode) {
    	if (listMode) {
    		page.setSize(-1);
    	}
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	logger.warn("user" + user);
    	IPage<Follow> followPage = followService.page(page, new QueryWrapper<Follow>().lambda().eq(Follow::getUserId, user.getId()));
    	return this.success( followPage );
    }
    
    @GetMapping("/{follow_id}")
    @ApiOperation(value="查看关注设备信息")
    public R<Object> show(
    		String followId) {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	List<Follow> followList = followService.list(new QueryWrapper<Follow>()
    			.lambda().eq(Follow::getId, followId) );

    	if (followList.size() != 1) {
    		return this.failed(ErrorCode.FOLLOW_NOT_FOUNT);
    	}
    	
    	Follow follow = followList.get(0);

    	return this.success(follow);
    }
    
    // 新增设备
    @PostMapping("")
    @ApiOperation(value="新增设备列表")
    public R<Object> create(
    		String deviceKey) {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");

    	Device device = deviceService.getOne(new QueryWrapper<Device>()
    			.lambda().eq(Device::getDeviceKey, deviceKey));
    	if(device == null) {
    		return this.failed(ErrorCode.DEVICE_NOT_FOUNT);
    	}
    	
    	List<Follow> followList = followService.list(new QueryWrapper<Follow>()
    			.lambda().eq(Follow::getUserId, user.getId())
    			.eq(Follow::getDeviceId, device.getId()));
    	
    	if(followList.size() == 1 ) {
    		return this.failed(ErrorCode.FOLLOWED);
    	}
    	
    	List<Follow> followListOfShare = followService.findListByIDs(user.getId(), device.getId());
//    	//followService.list(new QueryWrapper<Follow>()
//    			.lambda().eq(Follow::getDeviceId, device.getId()));
    	
    	if(followListOfShare.size() > 20) {
    		return this.failed(ErrorCode.SHARE_MAX);
    	}
    	Follow follow = new Follow();
    	follow.setUserId(user.getId());
    	follow.setDeviceId(device.getId());
    	follow.setCreatedAt( new Timestamp(System.currentTimeMillis()));
    	follow.setOwner( (followListOfShare.size() == 0) );
    	follow.setActive( true );
    	
    	followService.save(follow);
    	
    	return this.success(follow);
    }
    
    // 修改设备
    @PutMapping("/{follow_id}")
    @ApiOperation(value="修改设备")
    public R<Object> update(
    		String followId, String nickname) {
    	
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	List<Follow> followList = followService.list(new QueryWrapper<Follow>()
    			.lambda().eq(Follow::getId, followId) );

    	if (followList.size() != 1) {
    		return this.failed(ErrorCode.FOLLOW_NOT_FOUNT);
    	}
    	
    	Follow follow = followList.get(0);
    	follow.setNickname(nickname);
    	followService.save(follow);
    	
    	return this.success(follow);
    }
    
    // 删除设备
    @DeleteMapping("/{follow_id}")
    @ApiOperation(value="取消关注")
    public R<Object> delete(
    		String followId) {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	List<Follow> followList = followService.list(new QueryWrapper<Follow>()
    			.lambda().eq(Follow::getId, followId) );

    	if (followList.size() != 1) {
    		return this.failed(ErrorCode.FOLLOW_NOT_FOUNT);
    	}
    	
    	Follow follow = followList.get(0);
    	follow.setActive(false);
    	followService.save(follow);
    	
    	Device device = deviceService.getOne(new QueryWrapper<Device>()
    			.lambda().eq(Device::getId, follow.getDeviceId()));
    	
    	return this.success(device);
    }
    
    // 历史数据记录
    @GetMapping("/history_data")
    @ApiOperation(value="设备数据")
    public R<Object> historyData() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	return this.success("history_data 还没有实现");
    }
    
    // 报警事件记录
    @GetMapping("/history_alarm")
    @ApiOperation(value="设备报警")
    public R<Object> historyAlarm() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	return this.success("history_alarm 还没有实现");
    }
}

