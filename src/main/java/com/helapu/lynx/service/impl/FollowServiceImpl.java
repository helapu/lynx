package com.helapu.lynx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.Follow;
import com.helapu.lynx.mapper.FollowMapper;
import com.helapu.lynx.service.IFollowService;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService{

    public List<Follow> findListByIDs(Long userId, Long deviceId) {
    	return baseMapper.selectListByIDs(userId, deviceId);
    }
}
