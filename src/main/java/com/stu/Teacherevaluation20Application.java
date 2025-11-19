package com.stu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@Slf4j
@SpringBootApplication
public class Teacherevaluation20Application {

    public static void main(String[] args) {
        SpringApplication.run(Teacherevaluation20Application.class, args);
        log.info("项目启动成功");
    }

}
