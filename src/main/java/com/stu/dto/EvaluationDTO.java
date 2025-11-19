package com.stu.dto;

import lombok.Data;

import java.util.List;

@Data
public class EvaluationDTO {
    private Integer teacherId;

    private Float score;


    private String context;
}
