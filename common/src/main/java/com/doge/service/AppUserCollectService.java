package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppUserCollect;
import com.doge.entity.query.AppUserCollectQuery;

import java.util.List;

/**
 * @Description:用户收藏Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppUserCollectService {

    /**
     * 根据条件查询列表
     */


    List<AppUserCollect> findListByParam(AppUserCollectQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(AppUserCollectQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<AppUserCollect> findListByPage(AppUserCollectQuery query);

    /**
     * 新增
     */

    Integer add(AppUserCollect bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<AppUserCollect> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<AppUserCollect> ListBean);

    /**
     * 根据CollectId查询
     */
    AppUserCollect getByCollectId(Integer collectId);

    /**
     * 根据CollectId更新
     */
    Integer updateByCollectId(AppUserCollect bean, Integer collectId);

    /**
     * 根据CollectId删除
     */
    Integer deleteByCollectId(Integer collectId);

    /**
     * 根据UserIdAndObjectIdAndCollectType查询
     */
    AppUserCollect getByUserIdAndObjectIdAndCollectType(String userId, String objectId, Integer collectType);

    /**
     * 根据UserIdAndObjectIdAndCollectType更新
     */
    Integer updateByUserIdAndObjectIdAndCollectType(AppUserCollect bean, String userId, String objectId, Integer collectType);

    /**
     * 根据UserIdAndObjectIdAndCollectType删除
     */
    Integer deleteByUserIdAndObjectIdAndCollectType(String userId, String objectId, Integer collectType);
}