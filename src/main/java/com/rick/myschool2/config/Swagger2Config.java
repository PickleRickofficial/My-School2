package com.rick.myschool2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.JacksonSerializerConvention;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket(){

       return new Docket(DocumentationType.SWAGGER_2)
                //文档信息（全局的）
               .apiInfo(apiInfo())  //必须放select前面
               //非必要
               .enable(true)//默认true
               .groupName("小组的名字")
               .host("主机的地址")
//               .consumes()//设置接受的请求内容类型（MIME 类型）
               //.select()：表示进入到 API 选择的配置部分。
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.rick.myschool2.controller"))
               .paths(PathSelectors.ant("/api/**"))//只有路径以 "/api/" 开头的接口会被包括在文档中。
               .build();


    }

    @Bean
    public ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("我是一个文档标题")
                .description("我是一个描述")
                .contact("我是联系人信息")
                .license("我是许可证信息")
                .licenseUrl("https://baidu.com")
                .version("1.0.1")
                .termsOfServiceUrl("提供服务的Url")
                //.extensions()扩展插件
                .build();

    }





}
