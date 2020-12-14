package com.xh.config.mp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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


/**
 * author  Xiao Hong
 * date  2020/5/2 11:18
 * description Swagger Config
 */

@Configuration
@EnableSwagger2 // 开启swagger
@Profile("dev") // dev 开启
public class SwaggerConfig {



    @Bean // Docket 实例
    public Docket docket(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization").description("Authorization")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());
    
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters)// set header api
                .apiInfo(apiInfo())
//                .groupName("xh")
                .enable(true)  // 启用 默认true
                .select()
                //扫描类注解为 @RestController
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //扫描方法注解为 @PostMapping
                //.apis(RequestHandlerSelectors.withMethodAnnotation(PostMapping.class))
                //基于包扫描
                .apis(RequestHandlerSelectors.basePackage("com.xh"))
                    //扫描的路径
                    .paths(PathSelectors.ant("/api/**"))
                .build()
                ;
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Xiao Hong", "127.0.0.1", "czxqxxxh@126.com");// 作者信息
        return new ApiInfo("Api Documentation",
                "Api Documentation",
                "v1.0",
                "127.0.0.1",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }

}
