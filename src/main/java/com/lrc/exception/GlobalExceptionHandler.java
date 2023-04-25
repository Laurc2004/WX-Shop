package com.lrc.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.lrc.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截
 */
@ControllerAdvice(basePackages = "com.lrc.controller")
public class GlobalExceptionHandler {
    private static final Log log = LogFactory.get();
    @ExceptionHandler(Exception.class)
    @ResponseBody  //返回json串
    public Result error(HttpServletRequest request,Exception e){
        log.error("异常信息",e);
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request,CustomException customException){
        return Result.error(customException.getCode(),customException.getMsg());
    }
}
