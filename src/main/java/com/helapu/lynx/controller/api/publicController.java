package com.helapu.lynx.controller.api;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.util.IOUtils;
import com.baomidou.mybatisplus.extension.api.ApiController;

import io.swagger.annotations.Api;

@RestController
@Api(tags="公开访问")
public class publicController extends ApiController {

	@RequestMapping(value = {"/robots", "/robot", "/robot.txt", "/robots.txt", "/null"})
	public void robot(HttpServletResponse response) {
		 
	    InputStream resourceAsStream = null;
	    try {
	 
	        ClassLoader classLoader = getClass().getClassLoader();
	        resourceAsStream = classLoader.getResourceAsStream("robot.txt");
	 
	        response.addHeader("Content-disposition", "filename=robot.txt");
	        response.setContentType("text/plain");
	        
	        int x;
	        while ((x = resourceAsStream.read()) != -1) {
	                response.getOutputStream().write(x);
	        }
	        resourceAsStream.close();
	        
	        response.flushBuffer();
	    } catch (Exception e) {
	        logger.error("Problem with displaying robot.txt", e);
	    } finally {
	    }
	}
}
