package com.stu.dto;

import lombok.Data;

import java.util.List;

@Data
public class EvaluationDTO {
    private Long teacherId;

    private Float score;


    private String context;
}
