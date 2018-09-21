package com.helapu.lynx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.mapper.DeviceMapper;
import com.helapu.lynx.service.IDeviceService;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<Device> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}
}
