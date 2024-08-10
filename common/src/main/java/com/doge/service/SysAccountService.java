package com.doge.service;

import com.doge.entity.dto.SessionUserAdminDto;
import com.doge.entity.po.SysAccount;
import com.doge.entity.query.SysAccountQuery;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.exception.BusinessException;

import java.util.List;

/**
 * @Description:账号信息Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface SysAccountService {

    /**
     * 根据条件查询列表
     */


    List<SysAccount> findListByParam(SysAccountQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(SysAccountQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<SysAccount> findListByPage(SysAccountQuery query);

    /**
     * 新增
     */

    Integer add(SysAccount bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<SysAccount> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<SysAccount> ListBean);

    /**
     * 根据UserId查询
     */
    SysAccount getByUserId(Integer userId);

    /**
     * 根据UserId更新
     */
    Integer updateByUserId(SysAccount bean, Integer userId);

    /**
     * 根据UserId删除
     */
    Integer deleteByUserId(Integer userId);

    /**
     * 根据Phone查询
     */
    SysAccount getByPhone(String phone);

    /**
     * 根据Phone更新
     */
    Integer updateByPhone(SysAccount bean, String phone);

    /**
     * 根据Phone删除
     */
    Integer deleteByPhone(String phone);

    /**
     * 登录验证
     *
     * @param phone
     * @param password
     */
    SessionUserAdminDto login(String phone, String password) throws BusinessException;

    void saveSysAccount(SysAccount sysAccount) throws BusinessException;
}