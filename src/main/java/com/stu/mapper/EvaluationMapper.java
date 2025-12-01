package com.stu.mapper;

import com.stu.entity.Evaluation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluationMapper {
    @Select("select * from evaluation where student_id = #{studentId} and teacher_id = #{teacherId}")
    Evaluation getByStudentIdAndTeacherId(Long studentId, Long teacherId);

    @Insert("insert into evaluation (student_id, teacher_id, score, context) values (#{studentId}, #{teacherId}, #{score}, #{context})")
    void insert(Evaluation evaluation);

    @Select("select * from evaluation where teacher_id = #{teacherId}")
    List<Evaluation> getByTeacherId(Long teacherId);

    @Select("select * from evaluation where student_id = #{currentId}")
    List<Evaluation> getByUserId(Long currentId);
}
