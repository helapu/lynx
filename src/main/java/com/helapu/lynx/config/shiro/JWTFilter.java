package com.helapu.lynx.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.baomidou.mybatisplus.extension.api.R;
import com.helapu.lynx.config.ErrorCode;
import com.alibaba.fastjson.JSON;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        String requestHeader = request.getHeader("Authorization");
        
        if (requestHeader == null) {
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	
            R<Object> r = R.restResult(null, ErrorCode.JWT_HEADER_NULL);
            
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(r));
            } catch (IOException e) {
            	
//                throw new Exception(ErrorCode.TEST.getMsg());
            }
            
//            writer.write("json字符哈");
        	
        	logger.error("header is null");
        	return;
        }
        

        logger.warn("存在hearder 尝试健全");
        
        JWTToken token = new JWTToken(requestHeader);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        
        try {
            Subject subject =SecurityUtils.getSubject();
            subject.login(token);
            
//          getSubject(request, response).login(token);
          
          logger.info("some ok pass");
          chain.doFilter(request, response);
          
        }catch (AuthenticationException e) {
        	logger.error("authenticate failed" + e.getMessage());
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	
            R<Object> r = R.failed(e.getLocalizedMessage());
            
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(r));
            } catch (IOException x) {
            	
//                throw new Exception(ErrorCode.TEST.getMsg());
            }
            
//            writer.write("json字符哈");
        }
        
    }
    

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
//    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
//        return authorization != null;
        return true;
    }

    /**
     *
     */
//    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        String authorization = httpServletRequest.getHeader("Authorization");
        
        logger.error("authorization string: " + authorization);
        
        if (authorization == null) {
        	//RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
//        	httpServletResponse.reset();
        	httpServletResponse.setContentType("application/json");
        	httpServletResponse.setCharacterEncoding("UTF-8");
        	
//            PrintWriter writer = httpServletResponse.getWriter();
//            writer.write("json字符哈");
            
        	logger.error("null uoeuoe");
        	throw new ShiroException("uuu");
        	
        }
        
        logger.error("oke---");

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
//        getSubject(request, response).login(token);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
//    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                response401(request, response);
            }
        }
        return true;
    }

    /**
     * 对跨域提供支持
     */
//    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
//        return super.preHandle(request, response);
        return true;
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
