package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppUpdate;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppUpdateMapper;
import com.doge.service.AppUpdateService;
import com.doge.entity.query.AppUpdateQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:app发布Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appUpdateService")
public class AppUpdateServiceImpl implements AppUpdateService {

    @Resource
    private AppUpdateMapper<AppUpdate, AppUpdateQuery> appUpdateMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppUpdate> findListByParam(AppUpdateQuery query) {
        return this.appUpdateMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppUpdateQuery query) {
        return this.appUpdateMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppUpdate> findListByPage(AppUpdateQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppUpdate> list = this.findListByParam(query);
        PaginationResultVO<AppUpdate> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppUpdate bean) {
        return this.appUpdateMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppUpdate> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appUpdateMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppUpdate> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appUpdateMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据Id查询
     */
    @Override
    public AppUpdate getById(Integer id) {
        return this.appUpdateMapper.selectById(id);
    }

    /**
     * 根据Id更新
     */
    @Override
    public Integer updateById(AppUpdate bean, Integer id) {
        return this.appUpdateMapper.updateById(bean, id);
    }

    /**
     * 根据Id删除
     */
    @Override
    public Integer deleteById(Integer id) {
        return this.appUpdateMapper.deleteById(id);
    }

}