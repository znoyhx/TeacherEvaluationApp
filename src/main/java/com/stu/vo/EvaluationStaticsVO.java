package com.stu.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationStaticsVO {
    // --- 基础信息 ---
    private Integer teacherId;      // 老师ID
    private String teacherName;     // 老师姓名

    // --- 分数统计 ---
    private Double avgScore;        // 平均分
    private Integer totalCount;     // 总评价数量
    private Double maxScore;        // 最高分
    private Double minScore;        // 最低分

    private String LLMcomment;      // LLM评价

}
