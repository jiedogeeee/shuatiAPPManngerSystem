package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppExam;
import com.doge.entity.query.AppExamQuery;

import java.util.List;

/**
 * @Description:用户在线考试Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppExamService {

    /**
     * 根据条件查询列表
     */


    List<AppExam> findListByParam(AppExamQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(AppExamQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<AppExam> findListByPage(AppExamQuery query);

    /**
     * 新增
     */

    Integer add(AppExam bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<AppExam> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<AppExam> ListBean);

    /**
     * 根据ExamId查询
     */
    AppExam getByExamId(Integer examId);

    /**
     * 根据ExamId更新
     */
    Integer updateByExamId(AppExam bean, Integer examId);

    /**
     * 根据ExamId删除
     */
    Integer deleteByExamId(Integer examId);
}