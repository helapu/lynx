package com.helapu.lynx.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

public class YunpianUtil {
	
	@Value(value = "${lynx.yunpian-apikey}")
	static public String apiKey;
	
	static public String sendVerifyCode(String mobile, String code) {
		//初始化clnt,使用单例方式
		YunpianClient clnt = new YunpianClient("apikey").init();

		//发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, "18616020***");
		param.put(YunpianClient.TEXT, "【云片网】您的验证码是1234");
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

		//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

		//释放clnt
		clnt.close();
		
		return r.getMsg();
	}
	
	

}
