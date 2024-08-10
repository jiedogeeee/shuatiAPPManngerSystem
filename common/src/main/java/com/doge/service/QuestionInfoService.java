package com.doge.service;

import com.doge.entity.po.QuestionInfo;
import com.doge.entity.query.QuestionInfoQuery;
import com.doge.entity.vo.PaginationResultVO;

import java.util.List;

/**
 * @Description:问题Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface QuestionInfoService {

    /**
     * 根据条件查询列表
     */


    List<QuestionInfo> findListByParam(QuestionInfoQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(QuestionInfoQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<QuestionInfo> findListByPage(QuestionInfoQuery query);

    /**
     * 新增
     */

    Integer add(QuestionInfo bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<QuestionInfo> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<QuestionInfo> ListBean);

    /**
     * 根据QuestionId查询
     */
    QuestionInfo getByQuestionId(Integer questionId);

    /**
     * 根据QuestionId更新
     */
    Integer updateByQuestionId(QuestionInfo bean, Integer questionId);

    /**
     * 根据QuestionId删除
     */
    Integer deleteByQuestionId(Integer questionId);

    void saveQuestion(QuestionInfo questionInfo, Boolean isAdmin);


    void dleQuestionBatch(String questionIds, Integer userId);
}