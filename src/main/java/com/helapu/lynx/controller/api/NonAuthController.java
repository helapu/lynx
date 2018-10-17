package com.helapu.lynx.controller.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.helapu.lynx.common.BCrypt;
import com.helapu.lynx.entity.RentDeal;
import com.helapu.lynx.entity.RentOrder;
import com.helapu.lynx.entity.enums.GoodIdentifierEnum;
import com.helapu.lynx.service.IRentDealService;
import com.helapu.lynx.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/public")
@Api(tags="公开数据")
public class NonAuthController extends ApiController {
	
	@Autowired
    private IUserService userService;
	
	@Autowired
	private IRentDealService rentDealService;
	
	
    @PostMapping("/rent_goods")
    @ApiOperation(value="可租赁物品")
    public R<Object> rent_goods() {
    	return this.success( GoodIdentifierEnum.values() );
    }
    
    @PostMapping("/rent_order")
    @ApiOperation(value="预备租赁物品")
    public R<Object> rent_order(String mobile,
    		String content ) {
    	
    	return this.success( GoodIdentifierEnum.values() );
    }

    @PostMapping("/bcrypt")
    @ApiOperation(value="bcrypt密码")
    public R<Object> bcrypt(String password) {
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    	return this.success( hashed );
    }
    
    @PostMapping("/test")
//    @ApiOperation(value="测试")
    public R<Object> test(String password) {
    	logger.warn("非验证测试接口");
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    	
    	logger.warn("hashed: " + hashed);
    	
    	for(GoodIdentifierEnum good: GoodIdentifierEnum.values()) {
    		logger.warn("GoodIdentifierEnum: value" + good.getValue() + " name: " + good.getName());
    	}
    	logger.warn(GoodIdentifierEnum.SMOKEDETECTION_V1.toString());
    	    	
    	
    	IPage<RentDeal> rentPage = rentDealService.page(new Page<RentDeal>(1,12), new QueryWrapper<RentDeal>()
    			.lambda()
    			.eq(RentDeal::getCompanyMobile,"13825279842"));
    	    	
    	RentDeal rentDeal = rentDealService.getById("1");
    	return this.success( rentDeal );
    }
    
    @PostMapping("/upload")
//    @ApiOperation(value="文件上传")
    public R<Object> upload(
    		@RequestParam("image") MultipartFile uploadfile) {
    	if (uploadfile.isEmpty()) {
    		return this.failed("file is empty");
    	}else {
    		try {
    			byte[] bytes = uploadfile.getBytes();
//                Path path = Paths.get("/home/helapuWork/" + uploadfile.getOriginalFilename());
    			Path path = Paths.get("/home/helapu/Work/hello.png");
                Files.write(path, bytes);
        		return this.success("ok ");

    		} catch (IOException e) {
    			return this.failed(e.getMessage());
    		}
    		
    	}
    	
    }
    
    @PostMapping("/otp")
    @ApiOperation(value="非确定参数处理")
    public R<Object> otp(HttpServletRequest request) {
    	
    	logger.warn("request: " + request.getRemoteHost());
    	
    	return this.success("ok" + request.getParameterNames());
    	
    }
    
}
