package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppUserInfo;
import com.doge.entity.query.AppUserInfoQuery;

import java.util.List;

/**
 * @Description:Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppUserInfoService {

    /**
     * 根据条件查询列表
     */


    List<AppUserInfo> findListByParam(AppUserInfoQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(AppUserInfoQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<AppUserInfo> findListByPage(AppUserInfoQuery query);

    /**
     * 新增
     */

    Integer add(AppUserInfo bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<AppUserInfo> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<AppUserInfo> ListBean);

    /**
     * 根据UserId查询
     */
    AppUserInfo getByUserId(String userId);

    /**
     * 根据UserId更新
     */
    Integer updateByUserId(AppUserInfo bean, String userId);

    /**
     * 根据UserId删除
     */
    Integer deleteByUserId(String userId);

    /**
     * 根据Email查询
     */
    AppUserInfo getByEmail(String email);

    /**
     * 根据Email更新
     */
    Integer updateByEmail(AppUserInfo bean, String email);

    /**
     * 根据Email删除
     */
    Integer deleteByEmail(String email);
}