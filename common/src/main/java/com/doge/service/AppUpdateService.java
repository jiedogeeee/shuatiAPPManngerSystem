package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppUpdate;
import com.doge.entity.query.AppUpdateQuery;

import java.util.List;

/**
 * @Description:app发布Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppUpdateService {

    /**
     * 根据条件查询列表
     */


    List<AppUpdate> findListByParam(AppUpdateQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(AppUpdateQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<AppUpdate> findListByPage(AppUpdateQuery query);

    /**
     * 新增
     */

    Integer add(AppUpdate bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<AppUpdate> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<AppUpdate> ListBean);

    /**
     * 根据Id查询
     */
    AppUpdate getById(Integer id);

    /**
     * 根据Id更新
     */
    Integer updateById(AppUpdate bean, Integer id);

    /**
     * 根据Id删除
     */
    Integer deleteById(Integer id);
}