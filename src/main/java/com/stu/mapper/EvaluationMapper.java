package com.stu.mapper;

import com.stu.entity.Evaluation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaluationMapper {
    Evaluation getByStudentIdAndTeacherId(Long studentId, Long teacherId);

    void insert(Evaluation evaluation);

    List<Evaluation> getByTeacherId(Long teacherId);

    List<Evaluation> getByUserId(Long currentId);
}
