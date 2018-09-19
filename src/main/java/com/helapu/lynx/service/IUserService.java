package com.helapu.lynx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.helapu.lynx.entity.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	boolean deleteAll();
//
	public List<User> selectListBySQL();
//
	public List<User> selectListByWrapper(Wrapper wrapper);
    
    public User getOneByMobile(String mobile);
    
    
    public User getOne(Wrapper wrapper);

	
}
