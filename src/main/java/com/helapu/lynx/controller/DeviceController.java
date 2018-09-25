package com.helapu.lynx.controller;

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
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.service.IDeviceService;
import com.helapu.lynx.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/devices")
@Api(tags="设备管理")
public class DeviceController extends ApiController {
	
	@Autowired
    private IDeviceService deviceService;
   
	@Autowired
	private IUserService userService;
	
    // 关注设备列表
    @GetMapping("")
    @ApiOperation(value="设备列表")
    public R<Object> device_list(Page page, boolean listMode) {
    	if (listMode) {
    		page.setSize(-1);
    	}
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	IPage<Device> pagedDevices = deviceService.page(page, new QueryWrapper<Device>());

    	return this.success(pagedDevices);
    }
    
    @GetMapping("/{device_key}")
    @ApiOperation(value="设备列表")
    public R<Object> showDevice(
    		String deviceKey) {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	IPage<Device> pagedDevices = deviceService.page(new Page<Device>(1, 5), new QueryWrapper<Device>().orderByDesc("name"));

    	deviceService.getOne(new QueryWrapper<Device>()
    			.lambda().eq(Device::getDeviceKey, deviceKey));
    	return this.success(pagedDevices);
    }
    
    // 新增设备
    @PostMapping("/")
    @ApiOperation(value="设备列表")
    public R<Object> addDevice() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");

    	return this.success("设备列表");
    	
    }
    
    // 修改设备
    @PutMapping("/{device_key}")
    @ApiOperation(value="设备列表")
    public R<Object> editDevice() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	return this.success("设备列表");
    }
    
    
    // 删除设备
    @DeleteMapping("/{device_key}")
    @ApiOperation(value="设备列表")
    public R<Object> unfollowDevice() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	return this.success("设备列表");
    }
    
    
    // 历史数据记录
    @GetMapping("/history_data")
    @ApiOperation(value="设备列表")
    public R<Object> historyData() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	return this.success("设备列表");
    }
    
    // 报警事件记录
    @GetMapping("/history_alarm")
    @ApiOperation(value="设备列表")
    public R<Object> historyAlarm() {

    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	return this.success("设备列表");
    }
    
    
}
