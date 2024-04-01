package com.iot.mywind.controller;

import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.exception.SystemException;
import com.iot.mywind.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

//    系统异常
    @ExceptionHandler(SystemException.class)
    public JsonResult<Void> doSystemException(SystemException ex){
//        1.记录日志
//        2.上报运维人员
//        3.上报开发人员

//        4.反馈给用户
        return new JsonResult<Void>(ex.getCode(),null,ex.getMessage());
    }

    //    业务异常
    @ExceptionHandler(BusinessException.class)
    public JsonResult<Void> doBusinessException(BusinessException ex){
//        1.反馈给用户
        return new JsonResult<Void>(ex.getCode(), null,ex.getMessage());
    }

    //    其他异常
    @ExceptionHandler(Exception.class)
    public JsonResult<Void> doException(){
//        1.记录日志
//        2.上报运维人员
//        3.上报开发人员

//        4.反馈给用户
        return new JsonResult<Void>(State.EXCEPTION_ERR, null,"未知异常，请重试");
    }

}
