package com.stu.service;

import com.stu.dto.TeacherDTO;
import com.stu.entity.Teacher;

public interface TeacherService {
    void createTeacher(TeacherDTO teacherDTO);

    void updateTeacher(Long id, TeacherDTO teacherDTO);

    Teacher getById(Long id);
}
