package com.doge.service;

import com.doge.entity.po.SysMenu;
import com.doge.entity.query.SysMenuQuery;
import com.doge.entity.vo.PaginationResultVO;

import java.util.List;

/**
 * @Description:菜单表Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface SysMenuService {

    /**
     * 根据条件查询列表
     */


    List<SysMenu> findListByParam(SysMenuQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(SysMenuQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<SysMenu> findListByPage(SysMenuQuery query);

    /**
     * 新增
     */

    Integer add(SysMenu bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<SysMenu> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<SysMenu> ListBean);

    /**
     * 根据MenuId查询
     */
    SysMenu getByMenuId(Integer menuId);

    /**
     * 根据MenuId更新
     */
    Integer updateByMenuId(SysMenu bean, Integer menuId);

    /**
     * 根据MenuId删除
     */
    Integer deleteByMenuId(Integer menuId);

    List<SysMenu> convertLine2Tree4Menu(List<SysMenu> dataList, Integer pId);

    void saveMenu(SysMenu sysMenu);

    List<SysMenu> getALlMenuByRoleIds(String roleIds);
}