package com.helapu.lynx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.RentDeal;
import com.helapu.lynx.mapper.RentDealMapper;
import com.helapu.lynx.service.IRentDealService;

@Service
public class RentDealServiceImpl extends ServiceImpl<RentDealMapper, RentDeal> implements IRentDealService {

    public List<RentDeal> findListByMobile(String companyMobile) {
    	return baseMapper.selectRentDealByMobile(companyMobile);
    }
}
