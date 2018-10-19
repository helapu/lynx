package com.helapu.lynx;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.helapu.lynx.entity.Device;
import com.helapu.lynx.service.IDeviceService;
import com.helapu.lynx.telecom.AuthUtil;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helapu.lynx.common.youzan.TokenService;
import com.helapu.lynx.common.youzan.entity.AuthTokenParams;
import com.helapu.lynx.config.TelecomRunner;
import com.helapu.lynx.config.properties.TelecomProperties;

import com.iotplatform.client.NorthApiClient;
import com.iotplatform.client.NorthApiException;
import com.iotplatform.client.dto.AuthOutDTO;
import com.iotplatform.client.dto.AuthRefreshInDTO;
import com.iotplatform.client.dto.AuthRefreshOutDTO;
import com.iotplatform.client.dto.ClientInfo;
import com.iotplatform.client.dto.CommandDTOV4;
import com.iotplatform.client.dto.CreateDeviceCmdCancelTaskInDTO;
import com.iotplatform.client.dto.CreateDeviceCmdCancelTaskOutDTO;
import com.iotplatform.client.dto.NotifyDeviceDataChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceDatasChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceInfoChangedDTO;
import com.iotplatform.client.dto.NotifyServiceInfoChangedDTO;
import com.iotplatform.client.dto.PostDeviceCommandInDTO2;
import com.iotplatform.client.dto.PostDeviceCommandOutDTO2;
import com.iotplatform.client.dto.QueryDeviceCmdCancelTaskInDTO2;
import com.iotplatform.client.dto.QueryDeviceCmdCancelTaskOutDTO2;
import com.iotplatform.client.dto.QueryDeviceCommandInDTO2;
import com.iotplatform.client.dto.QueryDeviceCommandOutDTO2;
import com.iotplatform.client.dto.SubDeviceDataInDTO;
import com.iotplatform.client.dto.SubscriptionDTO;
import com.iotplatform.client.dto.UpdateDeviceCommandInDTO;
import com.iotplatform.client.dto.UpdateDeviceCommandOutDTO;
import com.iotplatform.client.invokeapi.Authentication;
import com.iotplatform.client.invokeapi.PushMessageReceiver;
import com.iotplatform.client.invokeapi.SignalDelivery;
import com.iotplatform.client.invokeapi.SubscriptionManagement;
import com.yunpian.sdk.util.JsonUtil;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import okhttp3.RequestBody;

//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.entity.ContentType;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.youzan.open.sdk.client.oauth.model.OAuthToken;
//import com.youzan.open.sdk.util.http.DefaultHttpClient;
//import com.youzan.open.sdk.util.http.HttpClient;
//import com.youzan.open.sdk.util.json.JsonUtils;


@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.helapu.lynx.config",
        "com.helapu.lynx.controller",
        "com.helapu.lynx.service",
        "com.helapu.lynx.service.impl",
        "com.helapu.lynx.aliyun.oss",
        "com.helapu.lynx.aliyun.oss.impl"})
public class LynxApplication   {

    protected final static Logger logger = LoggerFactory.getLogger(LynxApplication.class);
    
    @Autowired
	private IDeviceService deviceService;
    
	@Bean
	public Converter<String, Device> deviceConverter() {
		return new Converter<String, Device>() {
			@Override
			public Device convert(String id) {
				
				return deviceService.getById(id);
//				return messageRepository().findMessage(Long.valueOf(id));
			}
		};
	}
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LynxApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("PortalApplication is success!");
        System.err.println("sample started. http://localhost:8080/api/auth/test");
	    
//        LynxApplication.getToken();
        
//        LynxApplication.justForTest();
        
        Retrofit retrofit = new Retrofit.Builder()
        	    .baseUrl("https://open.youzan.com/")
                .addConverterFactory(JacksonConverterFactory.create())
        	    .build();

        TokenService tokenS = retrofit.create(TokenService.class);
        try {
    		ObjectMapper mapperObj = new ObjectMapper();
    		AuthTokenParams authParams = new AuthTokenParams();
    		authParams.setClient_id("578dcba00bc300539b");
    		authParams.setClient_secret("d133449a0b11d555fd8c9ca5a0ba61de");
    		authParams.setGrant_type("silent");
    		authParams.setKdt_id("41119237");
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("client_id", "");
        	map.put("client_secret", "d133449a0b11d555fd8c9ca5a0ba61de");
        	map.put("grant_type", "silent"); //grant_type
        	
        	map.put("kdt_id", "41119237");
			String jsonString = mapperObj.writeValueAsString(authParams);
       	
        	RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonString);
        	
        	logger.warn("" + tokenS.getToken(body).execute().body());

        }catch (IOException e) {
        	logger.warn(e.getMessage());
        }
    }
    
//    public static void getToken() {
//
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpClient.Params params = HttpClient.Params.custom()
//
//              .add("client_id", "test")  
//              .add("client_secret", "test")                    
//              .add("grant_type", "silent")            
//              .add("kdt_id","823456")
//        .setContentType(ContentType.APPLICATION_FORM_URLENCODED).build();
//        String resp = httpClient.post("https://open.youzan.com/oauth/token", params);
//        System.out.println(resp);
//        if (StringUtils.isBlank(resp) || !resp.contains("access_token")) {
//            throw new RuntimeException(resp);
//        }
//        
//        logger.warn("uu");
//        logger.warn("" + JsonUtils.toBean(resp, new TypeReference<OAuthToken>() { }) );
//    }
    
    private static void justForTest() {
    	logger.warn("TelecomRunner: ");

    	NorthApiClient northApiClient = new NorthApiClient();
		
		ClientInfo clientInfo = new ClientInfo();
        clientInfo.setPlatformIp("180.101.147.89");
        clientInfo.setPlatformPort("8743");
        clientInfo.setAppId("9R9qAKYA8aaIC9BtMbtO0acC5VMa");
        clientInfo.setSecret("wW8M8IVPkFJajFQ7dzOmR0rIk8Aa");

        try {
			northApiClient.setClientInfo(clientInfo);
			northApiClient.initSSLConfig();
			logger.warn("telecom init runer: " + northApiClient);
		} catch (NorthApiException e) {
			System.out.println(e.toString());
		}
        
        // signaldelivery
        SignalDelivery signalDelivery = new SignalDelivery(northApiClient);

        // accessToken
        /**---------------------get accessToken at first------------------------*/
        Authentication authentication = new Authentication(northApiClient);     
        AuthOutDTO authOutDTO = null;
        try {
            authOutDTO = authentication.getAuthToken();        

        }catch(Exception e) {
        	logger.info(e.getMessage());
        }
        String accessToken = authOutDTO.getAccessToken();
        
        /**---------------------post an NB-IoT device command------------------------*/
        //this is a test NB-IoT device
        String deviceId = "194ffe51-a4d4-4991-b0da-8ed7a71214db";
        System.out.println("======post an NB-IoT device command======");
        PostDeviceCommandOutDTO2 pdcOutDTO = LynxApplication.postCommand(signalDelivery, deviceId, accessToken);
        if (pdcOutDTO != null) {
        	System.out.println(pdcOutDTO.toString());
        	String commandId = pdcOutDTO.getCommandId();
        	
        	/**---------------------update device command------------------------*/
        	System.out.println("\n======update device command======");
            UpdateDeviceCommandInDTO udcInDTO = new UpdateDeviceCommandInDTO();
            udcInDTO.setStatus("CANCELED");
            
            try {
            	UpdateDeviceCommandOutDTO udcOutDTO = signalDelivery.updateDeviceCommand(udcInDTO, commandId, null, accessToken);
                System.out.println(udcOutDTO.toString());
            }catch(Exception e) {
            	logger.warn("updateDelivery:" + e.getMessage());
            }
            
		}
        
        /**---------------------query device commands------------------------*/
        System.out.println("\n======query device commands======");
        QueryDeviceCommandInDTO2 qdcInDTO = new QueryDeviceCommandInDTO2();
        qdcInDTO.setPageNo(0);
        qdcInDTO.setPageSize(10);               
        QueryDeviceCommandOutDTO2 qdcOutDTO = null;
        
        try {
            qdcOutDTO = signalDelivery.queryDeviceCommand(qdcInDTO, accessToken);

        }catch(Exception e) {
        	logger.info(e.getMessage());
        }
        System.out.println(qdcOutDTO.toString());
        
        /**---------------------cancel all device commands of the device------------------------*/
        System.out.println("\n======cancel all device commands of the device======");
        CreateDeviceCmdCancelTaskInDTO cdcctInDTO = new CreateDeviceCmdCancelTaskInDTO();
        cdcctInDTO.setDeviceId(deviceId);        
        CreateDeviceCmdCancelTaskOutDTO cdcctOutDTO = null;

        try {
            cdcctOutDTO = signalDelivery.createDeviceCmdCancelTask(cdcctInDTO, null, accessToken);

        }catch(Exception e) {
        	logger.info(e.getMessage());
        }
        System.out.println(cdcctOutDTO.toString());
        
        /**---------------------query device command cancel tasks of the device------------------------*/
        System.out.println("\n======query device command cancel tasks of the device======");
    	QueryDeviceCmdCancelTaskInDTO2 qdcctInDTO = new QueryDeviceCmdCancelTaskInDTO2();
        qdcctInDTO.setDeviceId(deviceId);
        qdcctInDTO.setPageNo(0);
        qdcctInDTO.setPageSize(10);
        QueryDeviceCmdCancelTaskOutDTO2 qdcctOutDTO = null;
        try {
            qdcctOutDTO = signalDelivery.queryDeviceCmdCancelTask(qdcctInDTO, accessToken);

        }catch(Exception e) {
        	logger.info(e.getMessage());
        }
        System.out.println(qdcctOutDTO.toString());
        
        // 订阅
      //得到NorthApiClient实例后，再使用northApiClient得到订阅类实例 
        SubscriptionManagement subscriptionManagement = new SubscriptionManagement(northApiClient); 
         
        //先设置好subDeviceData的第一个入参SubDeviceDataInDTO结构体 
        SubDeviceDataInDTO sddInDTO = new SubDeviceDataInDTO(); 
        sddInDTO.setNotifyType("deviceDataChanged"); 
        //需要根据实际情况修改回调的ip和端口 
        sddInDTO.setCallbackUrl("http://yitaihuian.wang:80/api/public/v1.0.0/messageReceiver"); 
        try {
            //调用订阅类实例subscriptionManagement提供的业务接口，如subDeviceData 
            SubscriptionDTO subDTO = subscriptionManagement.subDeviceData(sddInDTO, null, accessToken); 
            System.out.println(subDTO.toString()); 
            logger.info("订阅成功 nice...");
        } catch (NorthApiException e) { 
            System.out.println(e.toString()); 
            logger.info("订阅失败...");
        }
    }
    
    @SuppressWarnings("unchecked")
	private static PostDeviceCommandOutDTO2 postCommand(SignalDelivery signalDelivery, String deviceId, String accessToken) {
    	PostDeviceCommandInDTO2 pdcInDTO = new PostDeviceCommandInDTO2();
        pdcInDTO.setDeviceId(deviceId);
        
        CommandDTOV4 cmd = new CommandDTOV4();
        cmd.setServiceId("SmokeDetectorReportInfo");
        cmd.setMethod("Delivery"); //"PUT" is the command name defined in the profile
        Map<String, Object> cmdParam = new HashedMap();
        cmdParam.put("checkCycle", 24);//"brightness" is the command parameter name defined in the profile
        
        cmd.setParas(cmdParam);
        pdcInDTO.setCommand(cmd);
        
        try {
			return signalDelivery.postDeviceCommand(pdcInDTO, null, accessToken);
		} catch (NorthApiException e) {
			System.out.println(e.toString());
		}
        return null;
    }
}
