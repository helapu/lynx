package com.helapu.lynx.config;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

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



//@Component
public class TelecomRunner implements ApplicationRunner{
	@Autowired
	private Environment env;
	
    protected final static Logger logger = LoggerFactory.getLogger(TelecomRunner.class);

    // throws Exception
    @Override    public void run(ApplicationArguments applicationArguments) throws Exception {
    	logger.warn("TelecomRunner: ");
    	logger.info("cc:" + env.getProperty("telecom.platform-ip"));
    	NorthApiClient northApiClient = new NorthApiClient();
		
		ClientInfo clientInfo = new ClientInfo();
        clientInfo.setPlatformIp(env.getProperty("telecom.platform-ip"));
        clientInfo.setPlatformPort(env.getProperty("telecom.platform-port"));
        clientInfo.setAppId(env.getProperty("telecom.app-id"));
        clientInfo.setSecret(env.getProperty("telecom.app-secret"));

        try {
			northApiClient.setClientInfo(clientInfo);
			northApiClient.initSSLConfig();
			logger.warn("telecom init runer: " + northApiClient);
		} catch (NorthApiException e) {
			System.out.println(e.toString());
		}
        
//        /**----------------------get access token-------------------------------*/
//        System.out.println("======get access token======");
//        Authentication authentication = new Authentication(northApiClient);
//        
//        // get access token
//        AuthOutDTO authOutDTO = authentication.getAuthToken();        
//        System.out.println(authOutDTO.toString());
//        logger.warn("DTO:" + authOutDTO.toString());
//        
//        System.out.println("======get access token======222");
//
//        /**----------------------refresh token--------------------------------*/
//        System.out.println("\n======refresh token======");
//        AuthRefreshInDTO authRefreshInDTO = new AuthRefreshInDTO();
//        
//        authRefreshInDTO.setAppId( env.getProperty("telecom.app-id"));
//        authRefreshInDTO.setSecret(northApiClient.getClientInfo().getSecret());
//        
//        //get refreshToken from the output parameter (i.e. authOutDTO) of Authentication
//        String refreshToken = authOutDTO.getRefreshToken();
//        authRefreshInDTO.setRefreshToken(refreshToken);
//        
//        AuthRefreshOutDTO authRefreshOutDTO = authentication.refreshAuthToken(authRefreshInDTO);
//        
//        System.out.println(authRefreshOutDTO.toString());
//        System.out.println("======get access token======333");
        
        // signaldelivery
        SignalDelivery signalDelivery = new SignalDelivery(northApiClient);

        // accessToken
        /**---------------------get accessToken at first------------------------*/
        Authentication authentication = new Authentication(northApiClient);        
        AuthOutDTO authOutDTO = authentication.getAuthToken();        
        String accessToken = authOutDTO.getAccessToken();
        
        /**---------------------post an NB-IoT device command------------------------*/
        //this is a test NB-IoT device
        String deviceId = "b3936cee-93e7-4f20-9790-809d6a73d851";
        System.out.println("======post an NB-IoT device command======");
        PostDeviceCommandOutDTO2 pdcOutDTO = TelecomRunner.postCommand(signalDelivery, deviceId, accessToken);
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
        QueryDeviceCommandOutDTO2 qdcOutDTO = signalDelivery.queryDeviceCommand(qdcInDTO, accessToken);
        System.out.println(qdcOutDTO.toString());
        
        /**---------------------cancel all device commands of the device------------------------*/
        System.out.println("\n======cancel all device commands of the device======");
        CreateDeviceCmdCancelTaskInDTO cdcctInDTO = new CreateDeviceCmdCancelTaskInDTO();
        cdcctInDTO.setDeviceId(deviceId);        
        CreateDeviceCmdCancelTaskOutDTO cdcctOutDTO = signalDelivery.createDeviceCmdCancelTask(cdcctInDTO, null, accessToken);
        System.out.println(cdcctOutDTO.toString());
        
        /**---------------------query device command cancel tasks of the device------------------------*/
        System.out.println("\n======query device command cancel tasks of the device======");
    	QueryDeviceCmdCancelTaskInDTO2 qdcctInDTO = new QueryDeviceCmdCancelTaskInDTO2();
        qdcctInDTO.setDeviceId(deviceId);
        qdcctInDTO.setPageNo(0);
        qdcctInDTO.setPageSize(10);
        QueryDeviceCmdCancelTaskOutDTO2 qdcctOutDTO = signalDelivery.queryDeviceCmdCancelTask(qdcctInDTO, accessToken);
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
