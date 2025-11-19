package com.stu.handler;

import com.stu.common.constant.MessageConstant;
import com.stu.common.exception.BaseException;
import com.stu.common.exception.TeacherNotFoundException;
import com.stu.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 此时需要处理下相同登录的用户名已存在，抛出异常未处理的情况
     * 核心是异常未处理，所以要处理异常
     * 本质就是将异常通过Result进行返回处理，前段会有对应的提示，这里需要写出msg也就是错误原因
     * 而错误原因已经在异常的报错信息里面了，所以直接提取就好
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message=ex.getMessage();

        if (message.contains("Duplicate entry")) {
            //Duplicate entry 'zhangsan' for key employee.idx_username
            String[] split = message.split(" ");
            String username=split[2];
            String msg = username+ MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
    /**
     * 捕获参数不合法异常（你现在遇到的就是这里）
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException ex){
        log.error("参数异常：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleBadRequest(HttpMessageNotReadableException ex){
        String msg = "score 必须为整数";
        return Result.error(msg);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public Result<?> handleTeacherNotFoundException(TeacherNotFoundException ex){
        String msg = ex.getMessage();
        log.error("教师不存在");
        return Result.error(msg);
    }

}
