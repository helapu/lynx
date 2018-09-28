package com.helapu.lynx.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.Device;
import com.helapu.lynx.entity.User;
import com.helapu.lynx.mapper.UserMapper;
import com.helapu.lynx.service.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Override
    public List<User> findListByWrapper(Wrapper wrapper) {
		return baseMapper.selectListByWrapper(wrapper);
    }
    
    public User getOneByMobile(String mobile) {
    	return baseMapper.selectOne(new QueryWrapper<User>()
    			.lambda().eq(User::getMobile, mobile));
    }
    
    public User getOneById(String userId) {
    	return baseMapper.selectOne(new QueryWrapper<User>()
    			.lambda().eq(User::getId, userId));
    }
    
}