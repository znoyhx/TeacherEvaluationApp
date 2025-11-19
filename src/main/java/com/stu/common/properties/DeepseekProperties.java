package com.stu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "deepseek")
@Data
public class DeepseekProperties {
    private String apiUrl;
    private String token;
    private String promptPrefix;
}