package com.stu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    private Integer id;

    private Long studentId;

    private Integer teacherId;

    private Float score;

    private String context;

    private LocalDateTime createTime;
}
