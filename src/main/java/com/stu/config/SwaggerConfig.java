package com.stu.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "教师评价系统 API",
                version = "1.0",
                description = "教师评价系统接口文档"
        )
)
public class SwaggerConfig {
}
