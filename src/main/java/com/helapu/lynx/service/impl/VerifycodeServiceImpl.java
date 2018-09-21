package com.helapu.lynx.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.Verifycode;
import com.helapu.lynx.mapper.VerifycodeMapper;
import com.helapu.lynx.service.IVerifycodeService;

@Service
public class VerifycodeServiceImpl extends ServiceImpl<VerifycodeMapper, Verifycode> implements IVerifycodeService{

}
