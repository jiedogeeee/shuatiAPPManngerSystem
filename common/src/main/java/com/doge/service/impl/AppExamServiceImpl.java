package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppExam;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppExamMapper;
import com.doge.service.AppExamService;
import com.doge.entity.query.AppExamQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:用户在线考试Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appExamService")
public class AppExamServiceImpl implements AppExamService {

    @Resource
    private AppExamMapper<AppExam, AppExamQuery> appExamMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppExam> findListByParam(AppExamQuery query) {
        return this.appExamMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppExamQuery query) {
        return this.appExamMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppExam> findListByPage(AppExamQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppExam> list = this.findListByParam(query);
        PaginationResultVO<AppExam> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppExam bean) {
        return this.appExamMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppExam> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appExamMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppExam> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appExamMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据ExamId查询
     */
    @Override
    public AppExam getByExamId(Integer examId) {
        return this.appExamMapper.selectByExamId(examId);
    }

    /**
     * 根据ExamId更新
     */
    @Override
    public Integer updateByExamId(AppExam bean, Integer examId) {
        return this.appExamMapper.updateByExamId(bean, examId);
    }

    /**
     * 根据ExamId删除
     */
    @Override
    public Integer deleteByExamId(Integer examId) {
        return this.appExamMapper.deleteByExamId(examId);
    }

}