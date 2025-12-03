package com.stu.service.impl;
import com.stu.common.constant.TeacherConstant;
import com.stu.dto.TeacherDTO;
import com.stu.entity.Teacher;
import com.stu.mapper.TeacherMapper;
import com.stu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void createTeacher(TeacherDTO teacherDTO) {
        // 校验 title
        if(teacherDTO.getName() != null && teacherMapper.getByName(teacherDTO.getName()) != null) {
            throw new IllegalArgumentException("姓名已存在");
        }
        checkIfValid(teacherDTO);
        Teacher teacher = new Teacher();

        teacher.setName(teacherDTO.getName());
        teacher.setTitle(teacherDTO.getTitle());
        teacher.setDepartment(teacherDTO.getDepartment());
        teacher.setResearchArea(teacherDTO.getResearchArea());
        teacherMapper.insert(teacher);
    }

    @Override
    public void updateTeacher(Long id, TeacherDTO teacherDTO) {
        //TODO:小bug, 可以改成别人的名字
        checkIfValid(teacherDTO);
        Teacher teacher = new Teacher();

        teacher.setId(id);
        teacher.setName(teacherDTO.getName());
        teacher.setTitle(teacherDTO.getTitle());
        teacher.setDepartment(teacherDTO.getDepartment());
        teacher.setResearchArea(teacherDTO.getResearchArea());
        teacherMapper.update(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherMapper.getByTeacherId(id);
    }

    private void checkIfValid(TeacherDTO teacherDTO) {

        if (teacherDTO.getTitle() != null &&
                !TeacherConstant.TITLES.contains(teacherDTO.getTitle())) {
            throw new IllegalArgumentException("title 值不合法");
        }

        if (teacherDTO.getDepartment() != null &&
                !TeacherConstant.DEPARTMENTS.contains(teacherDTO.getDepartment())) {
            throw new IllegalArgumentException("department 值不合法");
        }

        if (teacherDTO.getResearchArea() != null &&
                !TeacherConstant.RESEARCH_AREAS.contains(teacherDTO.getResearchArea())) {
            throw new IllegalArgumentException("researchArea 值不合法");
        }

    }
}
