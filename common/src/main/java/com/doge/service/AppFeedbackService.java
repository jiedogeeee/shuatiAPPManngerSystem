package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppFeedback;
import com.doge.entity.query.AppFeedbackQuery;

import java.util.List;

/**
 * @Description:问题反馈Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppFeedbackService {

    /**
     * 根据条件查询列表
     */


    List<AppFeedback> findListByParam(AppFeedbackQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(AppFeedbackQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<AppFeedback> findListByPage(AppFeedbackQuery query);

    /**
     * 新增
     */

    Integer add(AppFeedback bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<AppFeedback> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<AppFeedback> ListBean);

    /**
     * 根据FeedbackId查询
     */
    AppFeedback getByFeedbackId(Integer feedbackId);

    /**
     * 根据FeedbackId更新
     */
    Integer updateByFeedbackId(AppFeedback bean, Integer feedbackId);

    /**
     * 根据FeedbackId删除
     */
    Integer deleteByFeedbackId(Integer feedbackId);

    /**
     * 根据FeedbackIdAndUserId查询
     */
    AppFeedback getByFeedbackIdAndUserId(Integer feedbackId, String userId);

    /**
     * 根据FeedbackIdAndUserId更新
     */
    Integer updateByFeedbackIdAndUserId(AppFeedback bean, Integer feedbackId, String userId);

    /**
     * 根据FeedbackIdAndUserId删除
     */
    Integer deleteByFeedbackIdAndUserId(Integer feedbackId, String userId);
}