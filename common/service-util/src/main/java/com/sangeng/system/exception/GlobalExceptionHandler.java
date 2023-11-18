package com.sangeng.system.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sangeng.common.result.Result;

/**
 * 全局异常处理类
 *
 * @author: calos
 * @create: 2023-11-18 16:36
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(SanException.class)
    @ResponseBody
    public Result error(SanException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
