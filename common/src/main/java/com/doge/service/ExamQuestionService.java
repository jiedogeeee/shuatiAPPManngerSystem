package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.ExamQuestion;
import com.doge.entity.query.ExamQuestionQuery;

import java.util.List;

/**
 * @Description:考试题目Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface ExamQuestionService {

    /**
     * 根据条件查询列表
     */


    List<ExamQuestion> findListByParam(ExamQuestionQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(ExamQuestionQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<ExamQuestion> findListByPage(ExamQuestionQuery query);

    /**
     * 新增
     */

    Integer add(ExamQuestion bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<ExamQuestion> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<ExamQuestion> ListBean);

    /**
     * 根据QuestionId查询
     */
    ExamQuestion getByQuestionId(Integer questionId);

    /**
     * 根据QuestionId更新
     */
    Integer updateByQuestionId(ExamQuestion bean, Integer questionId);

    /**
     * 根据QuestionId删除
     */
    Integer deleteByQuestionId(Integer questionId);
}