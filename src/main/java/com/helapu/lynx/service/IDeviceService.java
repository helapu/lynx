package com.helapu.lynx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.helapu.lynx.entity.Device;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.Wrapper;


public interface IDeviceService extends IService<Device> {

	boolean deleteAll();
	public List<Device> selectListBySQL();
	
}
