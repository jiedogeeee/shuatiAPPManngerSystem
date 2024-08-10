package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppFeedback;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppFeedbackMapper;
import com.doge.service.AppFeedbackService;
import com.doge.entity.query.AppFeedbackQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:问题反馈Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appFeedbackService")
public class AppFeedbackServiceImpl implements AppFeedbackService {

    @Resource
    private AppFeedbackMapper<AppFeedback, AppFeedbackQuery> appFeedbackMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppFeedback> findListByParam(AppFeedbackQuery query) {
        return this.appFeedbackMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppFeedbackQuery query) {
        return this.appFeedbackMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppFeedback> findListByPage(AppFeedbackQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppFeedback> list = this.findListByParam(query);
        PaginationResultVO<AppFeedback> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppFeedback bean) {
        return this.appFeedbackMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppFeedback> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appFeedbackMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppFeedback> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appFeedbackMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据FeedbackId查询
     */
    @Override
    public AppFeedback getByFeedbackId(Integer feedbackId) {
        return this.appFeedbackMapper.selectByFeedbackId(feedbackId);
    }

    /**
     * 根据FeedbackId更新
     */
    @Override
    public Integer updateByFeedbackId(AppFeedback bean, Integer feedbackId) {
        return this.appFeedbackMapper.updateByFeedbackId(bean, feedbackId);
    }

    /**
     * 根据FeedbackId删除
     */
    @Override
    public Integer deleteByFeedbackId(Integer feedbackId) {
        return this.appFeedbackMapper.deleteByFeedbackId(feedbackId);
    }

    /**
     * 根据FeedbackIdAndUserId查询
     */
    @Override
    public AppFeedback getByFeedbackIdAndUserId(Integer feedbackId, String userId) {
        return this.appFeedbackMapper.selectByFeedbackIdAndUserId(feedbackId, userId);
    }

    /**
     * 根据FeedbackIdAndUserId更新
     */
    @Override
    public Integer updateByFeedbackIdAndUserId(AppFeedback bean, Integer feedbackId, String userId) {
        return this.appFeedbackMapper.updateByFeedbackIdAndUserId(bean, feedbackId, userId);
    }

    /**
     * 根据FeedbackIdAndUserId删除
     */
    @Override
    public Integer deleteByFeedbackIdAndUserId(Integer feedbackId, String userId) {
        return this.appFeedbackMapper.deleteByFeedbackIdAndUserId(feedbackId, userId);
    }

}