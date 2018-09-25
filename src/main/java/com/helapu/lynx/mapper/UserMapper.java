package com.helapu.lynx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.helapu.lynx.SuperMapper;
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.entity.User;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

	
    int deleteAll();

    @Select("select test_id as id, name, age, test_type from user")
    List<User> selectListBySQL();

    List<User> selectListByWrapper(@Param("ew") Wrapper wrapper);
    
    List<Device> selectDeviceList(@Param("userId") String userId);
    
    User findByMobile(@Param("mobile") String mobile);
    
}