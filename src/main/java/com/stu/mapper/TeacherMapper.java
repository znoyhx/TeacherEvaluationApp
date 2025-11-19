package com.stu.mapper;

import com.stu.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {

    @Insert("INSERT INTO teacher (name, title, department, research_area) VALUES (#{name}, #{title}, #{department}, #{researchArea})")
    void insert(Teacher teacher);

    void update(Teacher teacher);

    @Select("select * from teacher where id = #{id}")
    Teacher getByTeacherId(Integer id);

    @Select("select * from teacher where name = #{name}")
    Teacher getByName(String name);
}
