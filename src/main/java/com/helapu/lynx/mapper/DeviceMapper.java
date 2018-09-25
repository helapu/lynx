package com.helapu.lynx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.helapu.lynx.SuperMapper;
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.entity.User;

public interface DeviceMapper extends SuperMapper<Device>  {

	 int deleteAll();

    @Select("select id, device_key, device_secret from device")
    List<Device> selectListBySQL();
    
    //List<Device> selectDeviceList();
    List<Device> selectDeviceListByUserId(Integer userId);

}
