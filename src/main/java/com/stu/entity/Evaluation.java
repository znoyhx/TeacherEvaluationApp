package com.stu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    private Long id;

    private Long studentId;

    private Long teacherId;

    private Double score;

    private String context;

    private Timestamp createTime;

    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public Long getTeacherId() { return teacherId; }
    public Double getScore() { return score; }
    public String getContext() { return context; }
    public Timestamp getCreateTime() { return createTime; }
}
