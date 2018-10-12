package com.helapu.lynx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.helapu.lynx.SuperMapper;
import com.helapu.lynx.entity.RentDeal;

public interface RentDealMapper extends SuperMapper<RentDeal> {

	List<RentDeal> selectRentDealByMobile(@Param("company_mobile") String companyMobile);

}

