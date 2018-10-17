package com.helapu.lynx.telecom;

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
import com.iotplatform.client.invokeapi.Authentication;

public class AuthUtil {

	
    protected final static Logger logger = LoggerFactory.getLogger(AuthUtil.class);

    public static void initApiClient() throws Exception{
    	logger.warn("TelecomRunner: ");
    	
    	NorthApiClient northApiClient = new NorthApiClient();
		
    	logger.info("properties: " + TelecomProperties.getPlatformPort());
		ClientInfo clientInfo = new ClientInfo();
        clientInfo.setPlatformIp(TelecomProperties.getPlatformIp());
        clientInfo.setPlatformPort(TelecomProperties.getPlatformPort());
        clientInfo.setAppId(TelecomProperties.getAppId());
        clientInfo.setSecret(TelecomProperties.getAppSecret());

//        clientInfo.setPlatformIp( TelecomProperties.getPlatformIp());
//        clientInfo.setPlatformPort(TelecomProperties.getPlatformPort());
//        clientInfo.setAppId(TelecomProperties.getAppId());
//        clientInfo.setSecret(TelecomProperties.getAppSecret());

        try {
			northApiClient.setClientInfo(clientInfo);
			northApiClient.initSSLConfig();
			logger.warn("telecom init runer: " + northApiClient);
		} catch (NorthApiException e) {
			System.out.println(e.toString());
		} 
        
        /**----------------------get access token-------------------------------*/
        System.out.println("======get access token======");
        Authentication authentication = new Authentication(northApiClient);
        
        // get access token
        AuthOutDTO authOutDTO = authentication.getAuthToken();        
        System.out.println(authOutDTO.toString());
        logger.warn("DTO:" + authOutDTO.toString());
        
        System.out.println("======get access token======222");

        /**----------------------refresh token--------------------------------*/
        System.out.println("\n======refresh token======");
        AuthRefreshInDTO authRefreshInDTO = new AuthRefreshInDTO();
        
        authRefreshInDTO.setAppId( TelecomProperties.getAppId());
        authRefreshInDTO.setSecret(northApiClient.getClientInfo().getSecret());
        
        //get refreshToken from the output parameter (i.e. authOutDTO) of Authentication
        String refreshToken = authOutDTO.getRefreshToken();
        authRefreshInDTO.setRefreshToken(refreshToken);
        
        AuthRefreshOutDTO authRefreshOutDTO = authentication.refreshAuthToken(authRefreshInDTO);
        
        System.out.println(authRefreshOutDTO.toString());
        System.out.println("======get access token======333");

    }
}
