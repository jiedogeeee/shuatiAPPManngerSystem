package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppExamQuestion;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppExamQuestionMapper;
import com.doge.service.AppExamQuestionService;
import com.doge.entity.query.AppExamQuestionQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:考试问题Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appExamQuestionService")
public class AppExamQuestionServiceImpl implements AppExamQuestionService {

    @Resource
    private AppExamQuestionMapper<AppExamQuestion, AppExamQuestionQuery> appExamQuestionMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppExamQuestion> findListByParam(AppExamQuestionQuery query) {
        return this.appExamQuestionMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppExamQuestionQuery query) {
        return this.appExamQuestionMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppExamQuestion> findListByPage(AppExamQuestionQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppExamQuestion> list = this.findListByParam(query);
        PaginationResultVO<AppExamQuestion> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppExamQuestion bean) {
        return this.appExamQuestionMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppExamQuestion> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appExamQuestionMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppExamQuestion> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appExamQuestionMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据Id查询
     */
    @Override
    public AppExamQuestion getById(Integer id) {
        return this.appExamQuestionMapper.selectById(id);
    }

    /**
     * 根据Id更新
     */
    @Override
    public Integer updateById(AppExamQuestion bean, Integer id) {
        return this.appExamQuestionMapper.updateById(bean, id);
    }

    /**
     * 根据Id删除
     */
    @Override
    public Integer deleteById(Integer id) {
        return this.appExamQuestionMapper.deleteById(id);
    }

    /**
     * 根据ExamIdAndUserIdAndQuestionId查询
     */
    @Override
    public AppExamQuestion getByExamIdAndUserIdAndQuestionId(Integer examId, String userId, Integer questionId) {
        return this.appExamQuestionMapper.selectByExamIdAndUserIdAndQuestionId(examId, userId, questionId);
    }

    /**
     * 根据ExamIdAndUserIdAndQuestionId更新
     */
    @Override
    public Integer updateByExamIdAndUserIdAndQuestionId(AppExamQuestion bean, Integer examId, String userId, Integer questionId) {
        return this.appExamQuestionMapper.updateByExamIdAndUserIdAndQuestionId(bean, examId, userId, questionId);
    }

    /**
     * 根据ExamIdAndUserIdAndQuestionId删除
     */
    @Override
    public Integer deleteByExamIdAndUserIdAndQuestionId(Integer examId, String userId, Integer questionId) {
        return this.appExamQuestionMapper.deleteByExamIdAndUserIdAndQuestionId(examId, userId, questionId);
    }

}