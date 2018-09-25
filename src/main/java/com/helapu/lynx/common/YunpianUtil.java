package com.helapu.lynx.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.helapu.lynx.config.properties.LynxProperties;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

public class YunpianUtil {
		
	static public Map<String, Object> sendVerifyCode(String mobile, String code) {
		
		
		//初始化clnt,使用单例方式
		
		YunpianClient clnt = new YunpianClient( "e5049b80e388951b6a209b702bb1d93d" ).init();

		//发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, "【一泰慧安】您的验证码是" + code + "。有效期为" + "5分钟" + "，请尽快验证");
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

		//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
		
		String resultMsg = r.getMsg();
		
		//释放clnt
		clnt.close();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", r.getCode());
		result.put("msg", resultMsg);
		
		return result;
	}
	
	

}
