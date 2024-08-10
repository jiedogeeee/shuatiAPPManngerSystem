package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppDevice;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppDeviceMapper;
import com.doge.service.AppDeviceService;
import com.doge.entity.query.AppDeviceQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:设备信息Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appDeviceService")
public class AppDeviceServiceImpl implements AppDeviceService {

    @Resource
    private AppDeviceMapper<AppDevice, AppDeviceQuery> appDeviceMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppDevice> findListByParam(AppDeviceQuery query) {
        return this.appDeviceMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppDeviceQuery query) {
        return this.appDeviceMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppDevice> findListByPage(AppDeviceQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppDevice> list = this.findListByParam(query);
        PaginationResultVO<AppDevice> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppDevice bean) {
        return this.appDeviceMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppDevice> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appDeviceMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppDevice> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appDeviceMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据DeviceId查询
     */
    @Override
    public AppDevice getByDeviceId(String deviceId) {
        return this.appDeviceMapper.selectByDeviceId(deviceId);
    }

    /**
     * 根据DeviceId更新
     */
    @Override
    public Integer updateByDeviceId(AppDevice bean, String deviceId) {
        return this.appDeviceMapper.updateByDeviceId(bean, deviceId);
    }

    /**
     * 根据DeviceId删除
     */
    @Override
    public Integer deleteByDeviceId(String deviceId) {
        return this.appDeviceMapper.deleteByDeviceId(deviceId);
    }

}