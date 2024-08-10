package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppUserInfo;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppUserInfoMapper;
import com.doge.service.AppUserInfoService;
import com.doge.entity.query.AppUserInfoQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appUserInfoService")
public class AppUserInfoServiceImpl implements AppUserInfoService {

    @Resource
    private AppUserInfoMapper<AppUserInfo, AppUserInfoQuery> appUserInfoMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppUserInfo> findListByParam(AppUserInfoQuery query) {
        return this.appUserInfoMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppUserInfoQuery query) {
        return this.appUserInfoMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppUserInfo> findListByPage(AppUserInfoQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppUserInfo> list = this.findListByParam(query);
        PaginationResultVO<AppUserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppUserInfo bean) {
        return this.appUserInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppUserInfo> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appUserInfoMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppUserInfo> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appUserInfoMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据UserId查询
     */
    @Override
    public AppUserInfo getByUserId(String userId) {
        return this.appUserInfoMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId更新
     */
    @Override
    public Integer updateByUserId(AppUserInfo bean, String userId) {
        return this.appUserInfoMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据UserId删除
     */
    @Override
    public Integer deleteByUserId(String userId) {
        return this.appUserInfoMapper.deleteByUserId(userId);
    }

    /**
     * 根据Email查询
     */
    @Override
    public AppUserInfo getByEmail(String email) {
        return this.appUserInfoMapper.selectByEmail(email);
    }

    /**
     * 根据Email更新
     */
    @Override
    public Integer updateByEmail(AppUserInfo bean, String email) {
        return this.appUserInfoMapper.updateByEmail(bean, email);
    }

    /**
     * 根据Email删除
     */
    @Override
    public Integer deleteByEmail(String email) {
        return this.appUserInfoMapper.deleteByEmail(email);
    }

}