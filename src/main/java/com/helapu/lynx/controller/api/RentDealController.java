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
import com.helapu.lynx.entity.RentDeal;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.entity.enums.RentStatusEnum;
import com.helapu.lynx.service.IDeviceService;
import com.helapu.lynx.service.IFollowService;
import com.helapu.lynx.service.IRentDealService;
import com.helapu.lynx.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.sql.ResultSet;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/rents")
@Api(tags="租赁订单")
public class RentDealController extends ApiController {
	
	@Autowired
	private IRentDealService rentDealService;
	
	@Autowired
	private IUserService userService;
	
    // 租赁列表
    @GetMapping("")
    @ApiOperation(value="租赁列表")
    public R<Object> list(Page page, boolean listMode) {
    	if (listMode) {
    		page.setSize(-1);
    	}
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	IPage<RentDeal> rentPage = rentDealService.page(page, new QueryWrapper<RentDeal>()
    			.lambda()
    			.eq(RentDeal::getCompanyMobile, user.getMobile()));
    	
    	return this.success( rentPage );
    }
    
    @GetMapping("/{rent_id}")
    @ApiOperation(value="根据ID查看租赁信息")
    public R<Object> show(
    		String rentId) {

//    	logger.debug("rentId" + rentId);
//    	Subject subject = SecurityUtils.getSubject();
//    	User user = (User)subject.getSession().getAttribute("user");
   	
    	return this.success( rentDealService.getById(rentId) );
    }
    
    // 新增设备
    @PostMapping("")
    @ApiOperation(value="新增")
    public R<Object> create() {    	
    	return this.failed(ErrorCode.NOT_SUPPORT);
    }
    
    //  申请退还租金
    @PostMapping("/{rent_id}/sendback")
    @ApiOperation(value=" 申请退还租金")
    public R<Object> sendback(
    		String rentId) {
    	Subject subject = SecurityUtils.getSubject();
    	User user = (User)subject.getSession().getAttribute("user");
    	
    	RentDeal rentDeal = rentDealService.getById(rentId);
    	rentDeal.setStatus(RentStatusEnum.TALKWITH);

    	return this.success( rentDeal );
    }
}

