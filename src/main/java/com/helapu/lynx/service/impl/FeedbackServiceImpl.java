package com.helapu.lynx.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.helapu.lynx.entity.Feedback;
import com.helapu.lynx.mapper.FeedbackMapper;
import com.helapu.lynx.service.IFeedbackService;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

}
