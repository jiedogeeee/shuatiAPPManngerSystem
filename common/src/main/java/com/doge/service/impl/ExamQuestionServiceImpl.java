package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.ExamQuestion;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.ExamQuestionMapper;
import com.doge.service.ExamQuestionService;
import com.doge.entity.query.ExamQuestionQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:考试题目Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("examQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {

    @Resource
    private ExamQuestionMapper<ExamQuestion, ExamQuestionQuery> examQuestionMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<ExamQuestion> findListByParam(ExamQuestionQuery query) {
        return this.examQuestionMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(ExamQuestionQuery query) {
        return this.examQuestionMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<ExamQuestion> findListByPage(ExamQuestionQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<ExamQuestion> list = this.findListByParam(query);
        PaginationResultVO<ExamQuestion> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(ExamQuestion bean) {
        return this.examQuestionMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<ExamQuestion> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.examQuestionMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<ExamQuestion> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.examQuestionMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据QuestionId查询
     */
    @Override
    public ExamQuestion getByQuestionId(Integer questionId) {
        return this.examQuestionMapper.selectByQuestionId(questionId);
    }

    /**
     * 根据QuestionId更新
     */
    @Override
    public Integer updateByQuestionId(ExamQuestion bean, Integer questionId) {
        return this.examQuestionMapper.updateByQuestionId(bean, questionId);
    }

    /**
     * 根据QuestionId删除
     */
    @Override
    public Integer deleteByQuestionId(Integer questionId) {
        return this.examQuestionMapper.deleteByQuestionId(questionId);
    }

}