package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.ExamQuestionItem;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.ExamQuestionItemMapper;
import com.doge.service.ExamQuestionItemService;
import com.doge.entity.query.ExamQuestionItemQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("examQuestionItemService")
public class ExamQuestionItemServiceImpl implements ExamQuestionItemService {

    @Resource
    private ExamQuestionItemMapper<ExamQuestionItem, ExamQuestionItemQuery> examQuestionItemMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<ExamQuestionItem> findListByParam(ExamQuestionItemQuery query) {
        return this.examQuestionItemMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(ExamQuestionItemQuery query) {
        return this.examQuestionItemMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<ExamQuestionItem> findListByPage(ExamQuestionItemQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<ExamQuestionItem> list = this.findListByParam(query);
        PaginationResultVO<ExamQuestionItem> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(ExamQuestionItem bean) {
        return this.examQuestionItemMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<ExamQuestionItem> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.examQuestionItemMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<ExamQuestionItem> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.examQuestionItemMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据ItemId查询
     */
    @Override
    public ExamQuestionItem getByItemId(Integer itemId) {
        return this.examQuestionItemMapper.selectByItemId(itemId);
    }

    /**
     * 根据ItemId更新
     */
    @Override
    public Integer updateByItemId(ExamQuestionItem bean, Integer itemId) {
        return this.examQuestionItemMapper.updateByItemId(bean, itemId);
    }

    /**
     * 根据ItemId删除
     */
    @Override
    public Integer deleteByItemId(Integer itemId) {
        return this.examQuestionItemMapper.deleteByItemId(itemId);
    }

}