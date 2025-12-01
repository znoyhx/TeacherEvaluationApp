package com.stu.service;

import com.stu.dto.EvaluationDTO;
import com.stu.entity.Evaluation;
import com.stu.vo.EvaluationStaticsVO;
import com.stu.vo.EvaluationVO;

import java.util.List;

public interface EvaluationService {
    void createEvaluation(EvaluationDTO dto);

    List<EvaluationVO> getByTeacherId(Long teacherId);

    List<EvaluationVO> getByUserId(Long currentId);

    EvaluationStaticsVO getStaticsByTeacherId(Long teacherId);
}
