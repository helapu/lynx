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









import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

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
import com.iotplatform.client.dto.PostDeviceCommandInDTO2;
import com.iotplatform.client.dto.PostDeviceCommandOutDTO2;
import com.iotplatform.client.dto.QueryDeviceCmdCancelTaskInDTO2;
import com.iotplatform.client.dto.QueryDeviceCmdCancelTaskOutDTO2;
import com.iotplatform.client.dto.QueryDeviceCommandInDTO2;
import com.iotplatform.client.dto.QueryDeviceCommandOutDTO2;
import com.iotplatform.client.dto.UpdateDeviceCommandInDTO;
import com.iotplatform.client.dto.UpdateDeviceCommandOutDTO;
import com.iotplatform.client.invokeapi.Authentication;
import com.iotplatform.client.invokeapi.SignalDelivery;









@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.helapu.lynx.config",
        "com.helapu.lynx.controller",
        "com.helapu.lynx.service",
        "com.helapu.lynx.service.impl",
        "com.helapu.lynx.aliyun.oss",
        "com.helapu.lynx.aliyun.oss.impl"})
public class LynxApplication {

    protected final static Logger logger = LoggerFactory.getLogger(LynxApplication.class);
    
    /**
     * <p>
     * 测试 RUN<br>
     * 查看 h2 数据库控制台：http://localhost:8080/console<br>
     * 使用：JDBC URL 设置 jdbc:h2:mem:lynx 用户名 sa 密码 sa 进入，可视化查看 user 表<br>
     * 误删连接设置，开发机系统本地 ~/.h2.server.properties 文件<br>
     * <br>
     * 1、http://localhost:8080/user/test<br>
     * 2、http://localhost:8080/user/test1<br>
     * 3、http://localhost:8080/user/test2<br>
     * 4、http://localhost:8080/user/test3<br>
     * 5、http://localhost:8080/user/add<br>
     * 6、http://localhost:8080/user/selectsql<br>
     * 7、分页 size 一页显示数量  current 当前页码
     * 方式一：http://localhost:8080/user/page?size=1&current=1<br>
     * 方式二：http://localhost:8080/user/pagehelper?size=1&current=1<br>
     * </p>
     */
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
	    
        LynxApplication.justForTest();
    }
    
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
