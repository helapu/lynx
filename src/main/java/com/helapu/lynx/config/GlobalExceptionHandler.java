package com.helapu.lynx.config;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baomidou.mybatisplus.extension.api.R;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(BindException.class)
    public R<Object> handleBindException(BindException ex) {
		return R.failed(ex.getAllErrors().toString());
    }
    

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
     
        logger.error("Error: handleBadRequest StackTrace : {}", e);
        return R.failed(e.getMessage());
    }
}
