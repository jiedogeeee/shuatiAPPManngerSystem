package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppUserCollect;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppUserCollectMapper;
import com.doge.service.AppUserCollectService;
import com.doge.entity.query.AppUserCollectQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:用户收藏Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appUserCollectService")
public class AppUserCollectServiceImpl implements AppUserCollectService {

    @Resource
    private AppUserCollectMapper<AppUserCollect, AppUserCollectQuery> appUserCollectMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppUserCollect> findListByParam(AppUserCollectQuery query) {
        return this.appUserCollectMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppUserCollectQuery query) {
        return this.appUserCollectMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppUserCollect> findListByPage(AppUserCollectQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppUserCollect> list = this.findListByParam(query);
        PaginationResultVO<AppUserCollect> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppUserCollect bean) {
        return this.appUserCollectMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppUserCollect> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appUserCollectMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppUserCollect> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appUserCollectMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据CollectId查询
     */
    @Override
    public AppUserCollect getByCollectId(Integer collectId) {
        return this.appUserCollectMapper.selectByCollectId(collectId);
    }

    /**
     * 根据CollectId更新
     */
    @Override
    public Integer updateByCollectId(AppUserCollect bean, Integer collectId) {
        return this.appUserCollectMapper.updateByCollectId(bean, collectId);
    }

    /**
     * 根据CollectId删除
     */
    @Override
    public Integer deleteByCollectId(Integer collectId) {
        return this.appUserCollectMapper.deleteByCollectId(collectId);
    }

    /**
     * 根据UserIdAndObjectIdAndCollectType查询
     */
    @Override
    public AppUserCollect getByUserIdAndObjectIdAndCollectType(String userId, String objectId, Integer collectType) {
        return this.appUserCollectMapper.selectByUserIdAndObjectIdAndCollectType(userId, objectId, collectType);
    }

    /**
     * 根据UserIdAndObjectIdAndCollectType更新
     */
    @Override
    public Integer updateByUserIdAndObjectIdAndCollectType(AppUserCollect bean, String userId, String objectId, Integer collectType) {
        return this.appUserCollectMapper.updateByUserIdAndObjectIdAndCollectType(bean, userId, objectId, collectType);
    }

    /**
     * 根据UserIdAndObjectIdAndCollectType删除
     */
    @Override
    public Integer deleteByUserIdAndObjectIdAndCollectType(String userId, String objectId, Integer collectType) {
        return this.appUserCollectMapper.deleteByUserIdAndObjectIdAndCollectType(userId, objectId, collectType);
    }

}