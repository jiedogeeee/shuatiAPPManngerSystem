package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppDevice;
import com.doge.entity.query.AppDeviceQuery;

import java.util.List;

/**
 * @Description:设备信息Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppDeviceService {

    /**
     * 根据条件查询列表
     */


    List<AppDevice> findListByParam(AppDeviceQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(AppDeviceQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<AppDevice> findListByPage(AppDeviceQuery query);

    /**
     * 新增
     */

    Integer add(AppDevice bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<AppDevice> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<AppDevice> ListBean);

    /**
     * 根据DeviceId查询
     */
    AppDevice getByDeviceId(String deviceId);

    /**
     * 根据DeviceId更新
     */
    Integer updateByDeviceId(AppDevice bean, String deviceId);

    /**
     * 根据DeviceId删除
     */
    Integer deleteByDeviceId(String deviceId);
}