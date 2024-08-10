package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.ShareInfo;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.ShareInfoMapper;
import com.doge.service.ShareInfoService;
import com.doge.entity.query.ShareInfoQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:文章Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("shareInfoService")
public class ShareInfoServiceImpl implements ShareInfoService {

    @Resource
    private ShareInfoMapper<ShareInfo, ShareInfoQuery> shareInfoMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<ShareInfo> findListByParam(ShareInfoQuery query) {
        return this.shareInfoMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(ShareInfoQuery query) {
        return this.shareInfoMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<ShareInfo> findListByPage(ShareInfoQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<ShareInfo> list = this.findListByParam(query);
        PaginationResultVO<ShareInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(ShareInfo bean) {
        return this.shareInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<ShareInfo> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.shareInfoMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<ShareInfo> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.shareInfoMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据ShareId查询
     */
    @Override
    public ShareInfo getByShareId(Integer shareId) {
        return this.shareInfoMapper.selectByShareId(shareId);
    }

    /**
     * 根据ShareId更新
     */
    @Override
    public Integer updateByShareId(ShareInfo bean, Integer shareId) {
        return this.shareInfoMapper.updateByShareId(bean, shareId);
    }

    /**
     * 根据ShareId删除
     */
    @Override
    public Integer deleteByShareId(Integer shareId) {
        return this.shareInfoMapper.deleteByShareId(shareId);
    }

}