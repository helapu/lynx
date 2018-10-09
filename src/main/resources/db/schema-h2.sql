-- schema

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id          BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',

	nickname    VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	username    VARCHAR(30) NULL DEFAULT NULL COMMENT '用户名称',
	mobile      VARCHAR(16) NULL DEFAULT NULL COMMENT '手机号码',
	email       VARCHAR(128) NULL DEFAULT NULL COMMENT '邮箱',
	encrypted_password    VARCHAR(60) NULL DEFAULT NULL COMMENT '加密后的密码',
	login_at              DATETIME NULL DEFAULT NULL COMMENT '最后登录时间',
	created_at            DATETIME NULL DEFAULT NULL COMMENT '创建时间',

	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS verifycode;

CREATE TABLE verifycode
(
    id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',
	
	code        VARCHAR(8) NULL DEFAULT NULL COMMENT '验证码',
	mobile      VARCHAR(16) NULL DEFAULT NULL COMMENT '手机号码',
	type        VARCHAR(30) NULL DEFAULT NULL COMMENT '类型',
	result_code INT         NULL DEFAULT NULL COMMENT '发送短信返回状态码',
	message     VARCHAR(30) NULL DEFAULT NULL COMMENT '发送验证的反馈提示',
	created_at  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	
	PRIMARY KEY (id)
);

 
DROP TABLE IF EXISTS device;

CREATE TABLE device
(

	id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',

	user_id     BIGINT(20) NOT NULL COMMENT '用户ID',

	nickname    VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	product_key VARCHAR(240) NULL DEFAULT NULL COMMENT '产品key',
	device_key         VARCHAR(240) NULL DEFAULT NULL COMMENT '设备唯一编码',
	device_secret      VARCHAR(240) NULL DEFAULT NULL COMMENT '设备密码',
	iotid       VARCHAR(30) NULL DEFAULT NULL COMMENT '阿里云iot id',
	utc_active  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	utc_create  DATETIME NULL DEFAULT NULL COMMENT '激活时间',
	status      TINYINT NULL DEFAULT NULL COMMENT '状态',
	region      VARCHAR(30) NULL DEFAULT NULL COMMENT '设备投放区域',
	node_type   TINYINT NULL DEFAULT NULL COMMENT '节点类型',
	utc_online  VARCHAR(30) NULL DEFAULT NULL COMMENT '在线时间',
	ip_address  VARCHAR(30) NULL DEFAULT NULL COMMENT 'IP',
	firmware_version    VARCHAR(30) NULL DEFAULT NULL COMMENT '固件版本',

	PRIMARY KEY (id)
);



DROP TABLE IF EXISTS follow;

CREATE TABLE follow
(
    id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',
	
	
	user_id     BIGINT(20) NOT NULL COMMENT '用户ID',
	device_id   BIGINT(20) NOT NULL COMMENT '设备ID',

	owner       INT NOT NULL COMMENT '是否是设备拥有者',
	created_at  DATETIME NULL DEFAULT NULL COMMENT '创建时间',

	nickname    VARCHAR(30) NULL DEFAULT NULL COMMENT '昵称',
	comment     VARCHAR(30) NULL DEFAULT NULL COMMENT '设备备注信息',

	active       TINYINT    NULL DEFAULT 0  COMMENT '是否激活',
	
	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS feedback;

CREATE TABLE feedback
(
    id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',

	user_id     BIGINT(20) NOT NULL COMMENT '用户ID',
	
	content     VARCHAR(200) NOT NULL COMMENT '反馈内容',
	created_at  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS rent;

CREATE TABLE rent
(
	id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',

	company_name    VARCHAR(200) NOT NULL COMMENT '公司名称',
	company_mobile  VARCHAR(16) NULL DEFAULT NULL COMMENT '绑定手机号码',
	deposit         DECIMAL(10,2) DEFAULT 0.0  COMMENT '租赁押金',
	rent_type       VARCHAR(20) NULL DEFAULT NULL  COMMENT '租赁时间计算单位', 
	price           DECIMAL(10,2) DEFAULT 0.0  COMMENT '每单位时间租赁金额',

	rent_at         DATETIME NULL DEFAULT NULL COMMENT '开始租赁计算时间',
	rent_content    VARCHAR(400)  DEFAULT NULL COMMENT '租赁物品描述',     
	
	active          TINYINT NULL DEFAULT 0  COMMENT '是否激活',
	status          VARCHAR(20) NULL DEFAULT NULL COMMENT '租赁状态', -- 正在租赁 -> 

	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS rent_apply;

CREATE TABLE rent_apply
(
	id     	    BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id   BIGINT(20) NOT NULL COMMENT '租户ID',

	apply_content   VARCHAR(400) DEFAULT NULL COMMENT '申请租赁信息',		
	PRIMARY KEY (id)
);

