package com.stu.mapper;

import com.stu.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    void insert(Teacher teacher);

    void update(Teacher teacher);

    Teacher getByTeacherId(Long id);

    Teacher getByName(String name);
}
