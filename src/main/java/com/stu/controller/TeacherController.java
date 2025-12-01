package com.stu.controller;

import com.stu.common.constant.TeacherConstant;

import com.stu.common.result.Result;
import com.stu.dto.TeacherDTO;
import com.stu.entity.Teacher;
import com.stu.service.TeacherService;
import com.stu.vo.TeacherVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {
    // 新增老师, 更改老师数据, 查询老师
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/data/titles")
    public Result<List<String>> getTitles() {
        return Result.success(TeacherConstant.TITLES);
    }

    // ② 单独获取部门列表
    @GetMapping("/data/departments")
    public Result<List<String>> getDepartments() {
        return Result.success(TeacherConstant.DEPARTMENTS);
    }

    // ③ 单独获取研究方向列表
    @GetMapping("/data/research-areas")
    public Result<List<String>> getResearchAreas() {
        return Result.success(TeacherConstant.RESEARCH_AREAS);
    }
    @PostMapping
    public Result<String> createTeacher(@RequestBody TeacherDTO teacherDTO){
        teacherService.createTeacher(teacherDTO);
        return Result.success("新增老师成功");
    }

    @PutMapping("/{id}")
    public Result<String> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO){
        teacherService.updateTeacher(id, teacherDTO);
        return Result.success("更新老师成功");
    }

    @GetMapping("/{id}")
    public Result<Teacher> getTeacherById(@PathVariable Long id){
        Teacher teacher = teacherService.getById(id);
        if (teacher == null) {
            return Result.error("老师不存在");
        }
        //TODO:老师的评价也要返回, 后续改成VO
        return Result.success(teacher);
    }
}
