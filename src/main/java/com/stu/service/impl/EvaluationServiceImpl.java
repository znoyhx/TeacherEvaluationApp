package com.stu.service.impl;

import com.stu.common.context.BaseContext;
import com.stu.common.exception.TeacherNotFoundException;
import com.stu.dto.EvaluationDTO;
import com.stu.entity.Evaluation;
import com.stu.entity.Teacher;
import com.stu.mapper.EvaluationMapper;
import com.stu.mapper.TeacherMapper;
import com.stu.mapper.UserMapper;
import com.stu.service.DeepseekService;
import com.stu.service.EvaluationService;
import com.stu.vo.EvaluationStaticsVO;
import com.stu.vo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeepseekService deepseekService;

    @Override
    public void createEvaluation(EvaluationDTO dto) {
        // 从dto中获取评价信息
        Long studentId = BaseContext.getCurrentId();
        Long teacherId = dto.getTeacherId();
        Double score = dto.getScore();
        String context = dto.getContext();
        Evaluation evaluation = Evaluation.builder()
               .studentId(studentId)
               .teacherId(teacherId)
               .score(score)
               .context(context)
               .build();
        if(teacherMapper.getByTeacherId(teacherId) == null)
        {
            throw new TeacherNotFoundException("教师不存在");
        }
        if(evaluation.getStudentId() != 0 && evaluation.getTeacherId()!=0&&
            evaluationMapper.getByStudentIdAndTeacherId(evaluation.getStudentId(), evaluation.getTeacherId()) != null)
        {
            throw new RuntimeException("用户已对该教师评价过");
        }
        evaluationMapper.insert(evaluation);
    }

    @Override
    public List<EvaluationVO> getByTeacherId(Long teacherId) {
        List<Evaluation> evaluations = evaluationMapper.getByTeacherId(teacherId);
        String teacherName = teacherMapper.getByTeacherId(teacherId).getName();

        //存在N+1的效率问题, 不想改了
        return evaluations.stream()
                .map(evaluation -> EvaluationVO.builder()
                        .id(evaluation.getId())
                        .teacherName(teacherName)
                        .studentName(userMapper.getById(evaluation.getStudentId()).getUsername())
                        .score(evaluation.getScore())
                        .context(evaluation.getContext())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationVO> getByUserId(Long currentId) {
        List<Evaluation> evaluations = evaluationMapper.getByUserId(currentId);
        String userName = userMapper.getById(currentId).getNickname();
        return evaluations.stream()
                .map(evaluation -> EvaluationVO.builder()
                        .id(evaluation.getId())
                        .teacherName(teacherMapper.getByTeacherId(evaluation.getTeacherId()).getName())
                        .studentName(userName)
                        .score(evaluation.getScore())
                        .context(evaluation.getContext())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationStaticsVO getStaticsByTeacherId(Long teacherId) {

        // 查询该老师的全部评价
        List<Evaluation> evaluations = evaluationMapper.getByTeacherId(teacherId);

        EvaluationStaticsVO vo = new EvaluationStaticsVO();
        vo.setTeacherId(teacherId);

        // 查老师信息（名字）
        Teacher teacher = teacherMapper.getByTeacherId(teacherId);
        if (teacher != null) {
            vo.setTeacherName(teacher.getName());
        }

        // 如果没有评价，全部字段返回默认值
        if (evaluations == null || evaluations.isEmpty()) {
            vo.setAvgScore(0.0);
            vo.setTotalCount(0);
            vo.setMaxScore(0.0);
            vo.setMinScore(0.0);
            return vo;
        }

        // 评价数量
        vo.setTotalCount(evaluations.size());

        // 提取所有分数
        List<Double> scores = evaluations.stream()
                .map(Evaluation::getScore)
                .toList();

        // 平均分
        double avg = scores.stream()
                .mapToDouble(Double::floatValue)
                .average()
                .orElse(0.0);
        vo.setAvgScore(avg);

        // 最高分
        double max = scores.stream()
                .mapToDouble(Double::floatValue)
                .max()
                .orElse(0.0);
        vo.setMaxScore(max);

        // 最低分
        double min = scores.stream()
                .mapToDouble(Double::floatValue)
                .min()
                .orElse(0.0);
        vo.setMinScore(min);

        List<String> contexts = evaluations.stream()
                .map(Evaluation::getContext)
                .toList();
        if(teacher == null){
            throw new TeacherNotFoundException("教师不存在");
        }
        String department = teacher.getDepartment();
        String title = teacher.getTitle();
        String researchArea = teacher.getResearchArea();
        String name = teacher.getName();
        String prompt = String.format("教师姓名：%s，部门：%s，职称：%s，研究方向：%s，评价内容：%s",
                name, department, title, researchArea, contexts);
        String summary = deepseekService.generateText(prompt);
        vo.setLLMcomment(summary);
        return vo;
    }

}
