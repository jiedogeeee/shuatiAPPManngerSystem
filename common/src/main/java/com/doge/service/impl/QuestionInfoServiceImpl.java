package com.doge.service.impl;

import com.doge.entity.enums.PageSize;
import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.entity.po.Category;
import com.doge.entity.po.QuestionInfo;
import com.doge.entity.query.QuestionInfoQuery;
import com.doge.entity.query.SimplePage;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.exception.BusinessException;
import com.doge.mappers.QuestionInfoMapper;
import com.doge.service.CategoryService;
import com.doge.service.QuestionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:问题Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("questionInfoService")
public class QuestionInfoServiceImpl implements QuestionInfoService {

    @Resource
    private QuestionInfoMapper<QuestionInfo, QuestionInfoQuery> questionInfoMapper;

    @Resource
    private CategoryService categoryService;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<QuestionInfo> findListByParam(QuestionInfoQuery query) {
        return this.questionInfoMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(QuestionInfoQuery query) {
        return this.questionInfoMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<QuestionInfo> findListByPage(QuestionInfoQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<QuestionInfo> list = this.findListByParam(query);
        PaginationResultVO<QuestionInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(QuestionInfo bean) {
        return this.questionInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<QuestionInfo> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.questionInfoMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<QuestionInfo> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.questionInfoMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据QuestionId查询
     */
    @Override
    public QuestionInfo getByQuestionId(Integer questionId) {
        return this.questionInfoMapper.selectByQuestionId(questionId);
    }

    /**
     * 根据QuestionId更新
     */
    @Override
    public Integer updateByQuestionId(QuestionInfo bean, Integer questionId) {
        return this.questionInfoMapper.updateByQuestionId(bean, questionId);
    }

    /**
     * 根据QuestionId删除
     */
    @Override
    public Integer deleteByQuestionId(Integer questionId) {
        return this.questionInfoMapper.deleteByQuestionId(questionId);
    }

    @Override
    public void saveQuestion(QuestionInfo questionInfo, Boolean isAdmin) {
        Category category = categoryService.getByCategoryId(questionInfo.getCategoryId());
        questionInfo.setCategoryName(category.getCategoryName());
        if (null == questionInfo.getQuestionId()) {
            questionInfo.setCreateTime(new Date());
            this.questionInfoMapper.insert(questionInfo);
        } else {
            QuestionInfo dbInfo = this.questionInfoMapper.selectByQuestionId(questionInfo.getQuestionId());
            if (!dbInfo.getCreateUserId().equals(questionInfo.getCreateUserId()) && isAdmin != true) {
                throw new BusinessException(ResponseCodeEnum.CODE_600);
            }
            questionInfo.setCreateUserId(null);
            questionInfo.setCreateUserName(null);
            questionInfo.setCreateTime(null);
            this.questionInfoMapper.updateByQuestionId(questionInfo, questionInfo.getQuestionId());
        }
    }

    @Override
    public void dleQuestionBatch(String questionIds, Integer userId) {
        String[] questionIdArray = questionIds.split(",");
        if (userId != null) {
            QuestionInfoQuery infoQuery = new QuestionInfoQuery();

        }
    }
}