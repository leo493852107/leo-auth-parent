package com.leo23.system.exception;

import com.leo23.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }

    // 自定义异常
    @ExceptionHandler(MyBusinessException.class)
    @ResponseBody
    public Result error(MyBusinessException e) {
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
}
