package com.helapu.lynx.controller.api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helapu.lynx.common.youzan.entity.YouzanPushMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/public/youzan")
@Api(tags="有赞推送")
public class YouzanPushMessageReceiver extends ApiController {
	
	@Value("${youzan.client-id}")
	private String clientId;

	@Value("${youzan.client-secret}")
	private String clientSecret;
	
	@Value("${youzan.grant-type}")
	private String grantType;
	
	@Value("${youzan.kdt-id}")
	private String kdtId;
	
	
    @PostMapping("")
    @ApiOperation(value="有赞消息推送")
    public JSONObject youzanPush(YouzanPushMessage pushMessage) {

    	/*
    	消息解析步骤
    	1. 判断消息是否测试  —> 解析 test
    	2. 判断消息推送的模式 —> 解析 mode
    	3. 判断消息是否伪造 —> 解析 sign
    	4. 判断消息版本  —> 解析 version
    	5. 判断消息的业务 —> 解析 type
    	6. 处理消息体 —> 解码 msg ，反序列化消息结构体
    	7. 返回接收成功标识 {"code":0,"msg":"success"}
    	*/
    	
    	logger.info("youzan push message");
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("code", 0);
    	jsonObject.put("msgz", "success");
	
    	if(pushMessage.isTest()) {
    		return jsonObject;
    	}
    	
    	 String sign= this.md5(this.clientId+pushMessage.getMsg()+this.clientSecret);
         if (!sign.equals(pushMessage.getSign())){
             return jsonObject;
         }
         
    	logger.info("" + pushMessage);
    	
    	// TODO 业务处理

    	return jsonObject;
    }
    
    public String md5(String plainText) {
		//定义一个字节数组 
		byte[] secretBytes = null;
		try { 
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			//对字符串进行加密 md.update(plainText.getBytes()); 
			//获得加密后的数据 secretBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException("没有md5这个算法！"); 
		} //将加密后的数据转换为16进制数字
		String md5code = new BigInteger(1, secretBytes).toString(16);
		// 16进制数字 
		// 如果生成数字未满32位，需要前面补0 
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		} 
		return md5code;
    }

}
