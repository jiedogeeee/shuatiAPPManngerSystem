package com.doge.service;

import com.doge.entity.po.SysRole;
import com.doge.entity.query.SysRoleQuery;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.exception.BusinessException;

import java.util.List;

/**
 * @Description:系统角色表Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface SysRoleService {

    /**
     * 根据条件查询列表
     */


    List<SysRole> findListByParam(SysRoleQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(SysRoleQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<SysRole> findListByPage(SysRoleQuery query);

    /**
     * 新增
     */

    Integer add(SysRole bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<SysRole> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<SysRole> ListBean);

    /**
     * 根据RoleId查询
     */
    SysRole getByRoleId(Integer roleId);

    /**
     * 根据RoleId更新
     */
    Integer updateByRoleId(SysRole bean, Integer roleId);

    /**
     * 根据RoleId删除
     */
    Integer deleteByRoleId(Integer roleId) throws BusinessException;

    void saveRole(SysRole sysRole, String menuIds, String halfMenuIds) throws BusinessException;

    void saveRoleManu(Integer roleId, String menuIds, String halfMenuIds) throws BusinessException;
}