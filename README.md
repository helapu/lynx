# 物联消防云系统

管理NB-IOT设备 给微信小程序提供api接口

[API文档](https://yitaihuian.wang/swagger-ui.html)

设备类型

SMOKEDETECTION_V1("SMOKEDETECTION_V1", "第一代烟雾报警器", "AMOUNT")
AUTOMATIC_EXTINGUISHING_V1("AUTOMATIC_EXTINGUISHING_V1", "自动灭火系统", "AREA")


## 常用技能

安装jar包到本地maven库

电信jar包
```
mvn install:install-file -Dfile=./lib/api-client-1.0.0.jar -DgroupId=com.tianyiiot -DartifactId=api-client -Dversion=1.0.0 -Dpackaging=jar
```

有赞jar包

```
mvn install:install-file -Dfile=./lib/open-sdk-java-2.0.2.jar -DgroupId=com.youzan -DartifactId=open-sdk -Dversion=1.0.0 -Dpackaging=jar
```

打包成可执行jar包

```
mvn clean install package spring-boot:repackage
```



```
curl -X POST https://open.youzan.com/oauth/token -H 'content-type: application/x-www-form-urlencoded' -d 'client_id=578dcba00bc300539b&client_secret=d133449a0b11d555fd8c9ca5a0ba61de&grant_type=silent&kdt_id=41119237'
```


{"access_token":"1675033191ce38328a9e8d7ef127c7f8","expires_in":604800,"scope":"storage points reviews multi_store salesman pay_qrcode item user trade_advanced trade item_category logistics coupon_advanced shop coupon crm_advanced trade_virtual retail_goods"}% 
