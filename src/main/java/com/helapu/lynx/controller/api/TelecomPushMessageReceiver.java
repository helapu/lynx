package com.helapu.lynx.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.iotplatform.client.dto.NotifyBindDeviceDTO;
import com.iotplatform.client.dto.NotifyCommandRspDTO;
import com.iotplatform.client.dto.NotifyDeviceAddedDTO;
import com.iotplatform.client.dto.NotifyDeviceDataChangedDTO;

//import com.helapu.lynx.entity.telecom.NotifyDeviceDataChangedDTO;

import com.iotplatform.client.dto.NotifyDeviceDatasChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceDeletedDTO;
import com.iotplatform.client.dto.NotifyDeviceDesiredStatusChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceEventDTO;
import com.iotplatform.client.dto.NotifyDeviceInfoChangedDTO;
import com.iotplatform.client.dto.NotifyDeviceModelAddedDTO;
import com.iotplatform.client.dto.NotifyDeviceModelDeletedDTO;
import com.iotplatform.client.dto.NotifyFwUpgradeResultDTO;
import com.iotplatform.client.dto.NotifyFwUpgradeStateChangedDTO;
import com.iotplatform.client.dto.NotifyMessageConfirmDTO;
import com.iotplatform.client.dto.NotifyNBCommandStatusChangedDTO;
import com.iotplatform.client.dto.NotifyRuleEventDTO;
import com.iotplatform.client.dto.NotifyServiceInfoChangedDTO;
import com.iotplatform.client.dto.NotifySwUpgradeResultDTO;
import com.iotplatform.client.dto.NotifySwUpgradeStateChangedDTO;
import com.iotplatform.client.invokeapi.PushMessageReceiver;

import io.swagger.annotations.Api;


//Open Telecom Platform
@RestController
@RequestMapping("/api/public/v1.0.0")
@Api(tags="电信推送")
public class TelecomPushMessageReceiver  extends ApiController {
	
	@PostMapping("/messageReceiver")
	public void handleDeviceDataChanged( NotifyDeviceDataChangedDTO body) {
		logger.warn("uu--------------" + body);
		System.out.println("deviceDataChanged ==> " + body.getDeviceId());
		
	}
	
//	@Override
//	public void handleBody(String body) {
//		System.out.println("handleBody ==> " + body);
//	}
	
	//override the callback functions if needed, otherwise, you can delete them.
	
	public void handleDeviceAdded(NotifyDeviceAddedDTO body) {
		System.out.println("deviceAdded ==> " + body);
		//TODO deal with deviceAdded notification
	}

	
	public void handleBindDevice(NotifyBindDeviceDTO body) {
		System.out.println("bindDevice ==> " + body);
		//TODO deal with BindDevice notification
	}

	
	public void handleDeviceInfoChanged(NotifyDeviceInfoChangedDTO body) {
		System.out.println("deviceInfoChanged ==> " + body);
		//TODO deal with DeviceInfoChanged notification
	}


	
	public void handleDeviceDatasChanged(NotifyDeviceDatasChangedDTO body) {
		System.out.println("deviceDatasChanged ==> " + body);
	}

	
	public void handleServiceInfoChanged(NotifyServiceInfoChangedDTO body) {
		System.out.println("serviceInfoChanged ==> " + body);
	}

	
	public void handleDeviceDeleted(NotifyDeviceDeletedDTO body) {
		System.out.println("deviceDeleted ==> " + body);
	}

	
	public void handleMessageConfirm(NotifyMessageConfirmDTO body) {
		System.out.println("messageConfirm ==> " + body);
	}

	
	public void handleCommandRsp(NotifyCommandRspDTO body) {
		System.out.println("commandRsp ==> " + body);
	}

	
	public void handleDeviceEvent(NotifyDeviceEventDTO body) {
		System.out.println("deviceEvent ==> " + body);
	}

	
	public void handleDeviceModelAdded(NotifyDeviceModelAddedDTO body) {
		System.out.println("deviceModelAdded ==> " + body);
	}

	
	public void handleDeviceModelDeleted(NotifyDeviceModelDeletedDTO body) {
		System.out.println("deviceModelDeleted ==> " + body);
	}

	
	public void handleRuleEvent(NotifyRuleEventDTO body) {
		System.out.println("ruleEvent ==> " + body);
	}

	
	public void handleDeviceDesiredStatusChanged(NotifyDeviceDesiredStatusChangedDTO body) {
		System.out.println("deviceDesiredStatusChanged ==> " + body);
	}

	
	public void handleSwUpgradeStateChanged(NotifySwUpgradeStateChangedDTO body) {
		System.out.println("swUpgradeStateChanged ==> " + body);
	}

	
	public void handleSwUpgradeResult(NotifySwUpgradeResultDTO body) {
		System.out.println("swUpgradeResult ==> " + body);
	}

	
	public void handleFwUpgradeStateChanged(NotifyFwUpgradeStateChangedDTO body) {
		System.out.println("fwUpgradeStateChanged ==> " + body);
	}

	
	public void handleFwUpgradeResult(NotifyFwUpgradeResultDTO body) {
		System.out.println("fwUpgradeResult ==> " + body);
	}

	
	public void handleNBCommandStateChanged(NotifyNBCommandStatusChangedDTO body) {
		System.out.println("NBCommandStateChanged ==> " + body);
	}
	
}