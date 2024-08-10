package com.doge.controller;


import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.dto.SessionUserAdminDto;
import com.doge.entity.enums.PermissionCodeEnum;
import com.doge.entity.po.QuestionInfo;
import com.doge.entity.query.QuestionInfoQuery;
import com.doge.entity.vo.ResponseVO;
import com.doge.service.QuestionInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description:问题Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/questionInfo")
public class QuestionInfoController extends ABaseController {

    @Resource
    private QuestionInfoService questionInfoService;

    /**
     * 新增
     */

    @RequestMapping("/loadDataList")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_LIST)
    public ResponseVO loadDataList(QuestionInfoQuery query) {
        query.setOrderBy("question_id desc");
        query.setQueryTextContent(true);
        return getSuccessResponseVO(questionInfoService.findListByPage(query));
    }

    /**
     * 新增
     */

    @RequestMapping("/saveQuestionInfo")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_EDIT)
    public ResponseVO saveQuestionInfo(HttpSession session, QuestionInfo questionInfo) {
        SessionUserAdminDto sessionUserAdminDto = getUserAdminFromSession(session);
        questionInfo.setCreateUserId(String.valueOf(sessionUserAdminDto.getUserId()));
        questionInfo.setCreateUserName(sessionUserAdminDto.getUserName());
        this.questionInfoService.saveQuestion(questionInfo, sessionUserAdminDto.getSuperAdmin());

        return getSuccessResponseVO(null);
    }


    @RequestMapping("/delQuestionInfo")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.QUESTION_DEL)
    public ResponseVO delQuestionInfo(HttpSession session,
                                      @VerifyParam(required = true) Integer questionId) {


        return getSuccessResponseVO(null);
    }


}