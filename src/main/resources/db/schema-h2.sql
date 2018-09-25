-- schema

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id          BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',

	nickname    VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	username    VARCHAR(30) NULL DEFAULT NULL COMMENT '用户名称',
	mobile      VARCHAR(30) NULL DEFAULT NULL COMMENT '手机号码',
	email       VARCHAR(30) NULL DEFAULT NULL COMMENT '邮箱',
	encrypted_password    VARCHAR(60) NULL DEFAULT NULL COMMENT '加密后的密码',
	last_login_at VARCHAR(30) NULL DEFAULT NULL COMMENT '最后登录时间',
	created_at  DATETIME NULL DEFAULT NULL COMMENT '创建时间',

	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS verifycode;

CREATE TABLE verifycode
(
    id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',
	
	code        VARCHAR(30) NULL DEFAULT NULL COMMENT '验证码',
	mobile      VARCHAR(30) NULL DEFAULT NULL COMMENT '手机号码',
	type        VARCHAR(30) NULL DEFAULT NULL COMMENT '类型',
	result_code INT         NULL DEFAULT NULL COMMENT '发送短信返回状态码',
	message     VARCHAR(30) NULL DEFAULT NULL COMMENT '发送验证的反馈提示',
	created_at  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	
	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS feedback;

CREATE TABLE feedback
(
    id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',
	
	code        VARCHAR(30) NULL DEFAULT NULL COMMENT '验证码',
	mobile      VARCHAR(30) NULL DEFAULT NULL COMMENT '手机号码',
	type        VARCHAR(30) NULL DEFAULT NULL COMMENT '类型',
	message     VARCHAR(30) NULL DEFAULT NULL COMMENT '发送验证的反馈提示',
	
	PRIMARY KEY (id)
);

 
DROP TABLE IF EXISTS device;

CREATE TABLE device
(

	id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',
	
    user_id     	    BIGINT(20) NOT NULL COMMENT '用户ID',

	nickname    VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	product_key VARCHAR(30) NULL DEFAULT NULL COMMENT '产品key',
	device_key         VARCHAR(30) NULL DEFAULT NULL COMMENT '设备唯一编码',
	device_secret      VARCHAR(30) NULL DEFAULT NULL COMMENT '设备密码',
	iotid       VARCHAR(30) NULL DEFAULT NULL COMMENT '阿里云iot id',
	utc_active  VARCHAR(30) NULL DEFAULT NULL COMMENT '创建时间',
	utc_create  VARCHAR(30) NULL DEFAULT NULL COMMENT '激活时间',
	status      VARCHAR(30) NULL DEFAULT NULL COMMENT '状态',
	region      VARCHAR(30) NULL DEFAULT NULL COMMENT '设备投放区域',
	node_type   VARCHAR(30) NULL DEFAULT NULL COMMENT '节点类型',
	utc_online  VARCHAR(30) NULL DEFAULT NULL COMMENT '在线时间',
	ip_address  VARCHAR(30) NULL DEFAULT NULL COMMENT 'IP',
	firmware_version    VARCHAR(30) NULL DEFAULT NULL COMMENT '固件版本',

	PRIMARY KEY (id)
);


