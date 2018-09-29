package com.helapu.lynx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.helapu.lynx.SuperMapper;
import com.helapu.lynx.entity.Follow;

public interface FollowMapper extends SuperMapper<Follow>  {

	List<Follow> selectListByIDs(@Param("userId") Long userId, @Param("deviceId") Long deviceId);

}
