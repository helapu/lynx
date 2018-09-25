package com.helapu.lynx.config;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

public enum ErrorCode implements IErrorCode {
	
	// ##返回码 前两位表示类别 后两位表示具体错误 99个类别+99个具体错误
	
	// 用户
	USER_EXIST(1000, "用户已经存在"),
	USER_NOT_EXIST(1001, "用户不存在"),
	USER_PASSWORD(1002, "用户密码错误"),
	VERIFYCODE_NOTFOUND(1003, "验证码没有找到"),
	VERIFYCODE_EXPIRED(1004, "验证码过期"),
	
	// 发送短信
	SMS_FAILED(1005, "同一个手机号同一验证码模板每30秒只能发送一条"),
	
	// 登录 授权
	JWT_HEADER_NULL(1006, "未提供Authorization"),
	JWT_HEADER_FORMAT(1007, "Authorization格式错误或者过期"),
	
	
	// 系统错误
	
	SERVER_ERROR(500, "服务器内部错误,请联系王小波处理"),
	JWTFORMAT(333, "jwt格式错误"),
	
	
	// 常规反馈
	
	UNAUTHORIZED(401, "未授权访问"),
    FAILED(-1, "我也不知道为啥,反正失败了"),
	SUCCESS(200, "哇赛 提交请求成功"),
    TEST(1000, "测试错误编码");


    private long code;
    private String msg;

    ErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
