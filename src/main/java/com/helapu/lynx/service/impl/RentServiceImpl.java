package com.helapu.lynx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.Rent;
import com.helapu.lynx.mapper.RentMapper;
import com.helapu.lynx.service.IRentService;

@Service
public class RentServiceImpl extends ServiceImpl<RentMapper, Rent> implements IRentService {

}
