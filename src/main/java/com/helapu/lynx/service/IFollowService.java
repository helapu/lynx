package com.helapu.lynx.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.helapu.lynx.entity.Follow;

public interface IFollowService extends IService<Follow> {
	
    public List<Follow> findListByIDs(Long userId, Long deviceId);

}
