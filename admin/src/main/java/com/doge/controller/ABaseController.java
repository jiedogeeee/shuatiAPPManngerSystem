package com.doge.controller;

import com.doge.entity.constants.Constants;
import com.doge.entity.dto.SessionUserAdminDto;
import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.entity.vo.ResponseVO;

import javax.servlet.http.HttpSession;

public class ABaseController {
    protected static final String STATIC_SUCCESS = "success";
    protected static final String STATIC_ERROR = "error";

    protected <T> ResponseVO getSuccessResponseVO(T t) {
        ResponseVO<T> responseVO = new ResponseVO<T>();
        responseVO.setStatus(STATIC_SUCCESS);
        responseVO.setCode(ResponseCodeEnum.CODE_200.getCode());
        responseVO.setInfo(ResponseCodeEnum.CODE_200.getMsg());
        responseVO.setData(t);
        return responseVO;
    }

    protected SessionUserAdminDto getUserAdminFromSession(HttpSession session) {
        SessionUserAdminDto sessionUserAdminDto = (SessionUserAdminDto) session.getAttribute(Constants.SESSION_KEY);

        return sessionUserAdminDto;
    }
}
