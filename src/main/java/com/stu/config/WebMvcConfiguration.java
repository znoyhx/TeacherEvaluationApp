package com.stu.config;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import com.stu.common.json.JacksonObjectMapper;
import com.stu.interceptor.JwtTokenUserInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;

    /**
     * 注册自定义拦截器（用户端）
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册用户端自定义拦截器...");
        //TODO:记得取消这个
//        registry.addInterceptor(jwtTokenUserInterceptor)
//                .addPathPatterns("/user/**")
//                .excludePathPatterns("/user/user/login")  // 登录不拦截
//                .excludePathPatterns("/user/shop/status"); // 查询店铺状态不拦截
//
//        super.addInterceptors(registry);
    }

    /**
     * 生成老师评价系统接口文档（后台 + 用户端共用）
     */
    @Bean
    public Docket docket() {
        log.info("准备生成老师评价系统接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("老师评价系统接口文档")
                .version("1.0")
                .description("老师评价系统的 API 文档")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stu.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 生成用户端接口文档
     */
    @Bean
    public Docket docketUser() {
        log.info("准备生成用户端接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("老师评价系统 - 用户端接口文档")
                .version("1.0")
                .description("老师评价系统的用户端 API 文档")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stu.controller.user"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 静态资源映射（用于 Swagger UI）
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 扩展消息转换器（JSON 转换）
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JacksonObjectMapper());

        // 优先使用我们自定义的转换器
        converters.add(0, converter);
    }
}
//import com.stu.common.json.JacksonObjectMapper;
//import com.stu.interceptor.JwtTokenUserInterceptor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springdoc.core.models.GroupedOpenApi;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info; // 仅注解用
//
//import java.util.List;
//
//@Configuration
//@Slf4j
//@OpenAPIDefinition(
//        info = @Info(
//                title = "老师评价系统接口文档",
//                version = "1.0",
//                description = "老师评价系统的 API 文档"
//        )
//)
//public class WebMvcConfiguration extends WebMvcConfigurationSupport {
//
//    @Autowired
//    private JwtTokenUserInterceptor jwtTokenUserInterceptor;
//
//    /**
//     * 注册自定义拦截器（用户端）
//     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        log.info("开始注册用户端自定义拦截器...");
//        // 保留原来的拦截器注册逻辑
////        registry.addInterceptor(jwtTokenUserInterceptor)
////                .addPathPatterns("/user/**")
////                .excludePathPatterns("/user/user/login")
////                .excludePathPatterns("/user/shop/status");
//    }
//
//    /**
//     * OpenAPI 基础文档
//     */
//    @Bean
//    public io.swagger.v3.oas.models.OpenAPI baseOpenAPI() {
//        return new io.swagger.v3.oas.models.OpenAPI()
//                .info(new io.swagger.v3.oas.models.info.Info()
//                        .title("老师评价系统接口文档")
//                        .version("1.0")
//                        .description("老师评价系统 API 文档"));
//    }
//
//    /**
//     * 分组后台接口
//     */
//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("后台接口")
//                .packagesToScan("com.stu.controller")
//                .build();
//    }
//
//    /**
//     * 分组用户端接口
//     */
//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//                .group("用户端接口")
//                .packagesToScan("com.stu.controller.user")
//                .build();
//    }
//
//    /**
//     * 静态资源映射（用于 Swagger UI）
//     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/doc.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    /**
//     * 扩展消息转换器（JSON 转换）
//     */
//    @Override
//    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        log.info("扩展消息转换器...");
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(new JacksonObjectMapper());
//
//        // 优先使用我们自定义的转换器
//        converters.add(0, converter);
//    }
//}
