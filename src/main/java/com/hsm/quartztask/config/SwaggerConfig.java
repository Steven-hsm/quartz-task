package com.hsm.quartztask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huangsenming
 * @Description:
 * @date 2020/4/9 14:03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket coreTaskAPI() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("token")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("基本权益模块")
                .select()
                //controller 包路径
                .apis(RequestHandlerSelectors.basePackage("com.hsm"))
                //自定义 生成指定请求地址下的文档
                .paths(PathSelectors.regex("^(?!auth).*$"))
                .build()
//                .globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "LEGAL RIGHT REST API",
                "任务模块",
                "1.0",
                "Terms of service",
                new Contact("xx", "www.example.com", "xx@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
