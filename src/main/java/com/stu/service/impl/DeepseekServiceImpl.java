package com.stu.service.impl;

import com.stu.common.properties.DeepseekProperties;
import com.stu.service.DeepseekService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
@RequiredArgsConstructor  // 自动生成带 final 字段的构造方法
public class DeepseekServiceImpl implements DeepseekService {

    private final RestTemplate restTemplate;
    private final DeepseekProperties properties;

    /**
     * 调用 DeepSeek API 根据 prompt 生成文本
     */
//    public String generateText(String prompt) {
//        Map<String, Object> body = new HashMap<>();
//        prompt = properties.getPromptPrefix() + prompt;
//        body.put("prompt", prompt);
//        body.put("max_tokens", 200);
//        body.put("temperature", 0.7);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(properties.getToken());
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//
//        Map<String, Object> response = restTemplate.postForObject(properties.getApiUrl(), request, Map.class);
//
//        return response != null ? (String) response.get("generated_text") : null;
//    }
    public String generateText(String prompt) {
        // 构建消息列表
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are an assistant.");
        messages.add(systemMessage);

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", properties.getPromptPrefix() + prompt);
        messages.add(userMessage);

        // 构建请求体
        Map<String, Object> body = new HashMap<>();
        body.put("model","deepseek-chat");
        body.put("messages", messages);
        body.put("max_tokens", 200);
        body.put("temperature", 0.7);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getToken());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        // 发送请求
        Map<String, Object> response = restTemplate.postForObject(properties.getApiUrl(), request, Map.class);

        // DeepSeek 返回结果通常在 choices[0].message.content
        if (response != null && response.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            if (!choices.isEmpty()) {
                Map<String, Object> firstChoice = choices.get(0);
                Map<String, String> message = (Map<String, String>) firstChoice.get("message");
                return message.get("content");
            }
        }

        return null;
    }

}

