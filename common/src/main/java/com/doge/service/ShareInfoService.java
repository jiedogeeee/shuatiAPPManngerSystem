package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.ShareInfo;
import com.doge.entity.query.ShareInfoQuery;

import java.util.List;

/**
 * @Description:文章Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface ShareInfoService {

    /**
     * 根据条件查询列表
     */


    List<ShareInfo> findListByParam(ShareInfoQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(ShareInfoQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<ShareInfo> findListByPage(ShareInfoQuery query);

    /**
     * 新增
     */

    Integer add(ShareInfo bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<ShareInfo> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<ShareInfo> ListBean);

    /**
     * 根据ShareId查询
     */
    ShareInfo getByShareId(Integer shareId);

    /**
     * 根据ShareId更新
     */
    Integer updateByShareId(ShareInfo bean, Integer shareId);

    /**
     * 根据ShareId删除
     */
    Integer deleteByShareId(Integer shareId);
}