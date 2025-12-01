package com.stu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationVO {
    private Long id;

    private String teacherName;

    private String studentName;

    private Double score;

    private String context;

    private LocalDateTime updateTime;
}
