package com.helapu.lynx.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

	@Value(value = "${lynx.swagger-open}")
    Boolean swaggerEnabled;
	
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable( true ).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.helapu.lynx.controller.api"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }
    
    private ApiInfo apiInfo() {
    	Contact contact = new Contact();
    	contact.setName("helapu");
    	contact.setUrl("https://yitaihuian.wang");
    	contact.setEmail("helapu@126.com");
        return new ApiInfoBuilder()
                .title("Etaihuian Doc")
                .description("物联网平台API文档")
                .version("0.1")
                .build();
    }

}
