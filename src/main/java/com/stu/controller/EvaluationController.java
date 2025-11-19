package com.stu.controller;


import com.stu.common.context.BaseContext;
import com.stu.common.result.Result;
import com.stu.dto.EvaluationDTO;
import com.stu.entity.Evaluation;
import com.stu.mapper.TeacherMapper;
import com.stu.mapper.UserMapper;
import com.stu.service.EvaluationService;
import com.stu.vo.EvaluationStaticsVO;
import com.stu.vo.EvaluationVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;


    //userid从BaseContext中传
    @PostMapping
    @ApiOperation("提交评价")
    //只能填1-10的整数,小数无效
    public Result<String> create(@RequestBody EvaluationDTO dto) {
        // 仅测试设置Baseid
        BaseContext.setCurrentId(1L);
        evaluationService.createEvaluation(dto);
        return Result.success("评价提交成功");
    }

    @GetMapping("/teacher/{teacherId}")
    @ApiOperation("根据教师id查询评价")
    public Result<List<EvaluationVO>> getByTeacherId(@PathVariable Integer teacherId) {
        List<EvaluationVO> evaluations = evaluationService.getByTeacherId(teacherId);

        return Result.success(evaluations);
    }

    @GetMapping("/user")
    @ApiOperation("根据用户id查询评价")
    public Result<List<EvaluationVO>> getByUserId() {
        BaseContext.setCurrentId(1L);

        return Result.success(evaluationService.getByUserId(BaseContext.getCurrentId()));
    }

    @GetMapping("/teacher/{teacherId}/statics")
    @ApiOperation("根据教师id查询评价统计")
    public Result<EvaluationStaticsVO> getStaticsByTeacherId(@PathVariable Integer teacherId) {
        EvaluationStaticsVO statics = evaluationService.getStaticsByTeacherId(teacherId);
        return Result.success(statics);
    }
}