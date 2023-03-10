package org.qf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger工具类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger会帮助我们生成接口文档
     * 1. 配置 生成的文档信息
     * 2. 配置生成规则
     */

    @Bean
    public Docket getDocket(){

        //创建封面信息对象
        ApiInfoBuilder apiInfoBuilder=new ApiInfoBuilder();
        apiInfoBuilder.title("《后台管理系统》后端接口文档说明")
                .description("此文档详细说明了锋迷商城项目后端接口说明......")
                .version("1.0.0")
                .contact(new Contact("明明","www.qq.com","235346@qq.com"));
        ApiInfo apiInfo=apiInfoBuilder.build();
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)  // 指定生成文档中的封面信息，文档标题、内容
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.qf.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;

    }

}
