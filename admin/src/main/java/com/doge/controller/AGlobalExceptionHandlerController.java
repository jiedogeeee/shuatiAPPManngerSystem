package com.doge.controller;

import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.entity.vo.ResponseVO;
import com.doge.exception.BusinessException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AGlobalExceptionHandlerController extends ABaseController {
    private static final Logger logger = LoggerFactory.getLogger(AGlobalExceptionHandlerController.class);

    @ExceptionHandler(value = Exception.class)
    Object handlerException(Exception e, HttpServletRequest request) {
        logger.error("请求错误，错误地址{},错误信息：", request.getRequestURI(), e);
        ResponseVO ajaxResponse = new ResponseVO();
        //404
        if (e instanceof NoHandlerFoundException) {
            ajaxResponse.setCode(ResponseCodeEnum.CODE_404.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_404.getMsg());
            ajaxResponse.setStatus(STATIC_ERROR);
        } else if (e instanceof BusinessException) {
            //业务错误
            BusinessException biz = (BusinessException) e;
            ajaxResponse.setCode(biz.getCode());
            ajaxResponse.setInfo(biz.getMessage());
            ajaxResponse.setStatus(STATIC_ERROR);
        } else if (e instanceof BusinessException) {
            //参数类型错误
            ajaxResponse.setCode(ResponseCodeEnum.CODE_600.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_600.getMsg());
            ajaxResponse.setStatus(STATIC_ERROR);
        } else if (e instanceof DuplicateKeyException) {
            //主键冲突
            ajaxResponse.setCode(ResponseCodeEnum.CODE_601.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_601.getMsg());
            ajaxResponse.setStatus(STATIC_ERROR);
        } else {
            //
            ajaxResponse.setCode(ResponseCodeEnum.CODE_500.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_500.getMsg());
            ajaxResponse.setStatus(STATIC_ERROR);
        }
        return ajaxResponse;

    }
}
