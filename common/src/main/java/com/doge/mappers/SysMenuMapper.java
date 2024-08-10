package com.doge.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:菜单表Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface SysMenuMapper<T, P> extends BaseMapper {
    /**
     * 根据MenuId查询
     */
    T selectByMenuId(@Param("menuId") Integer menuId);

    /**
     * 根据MenuId更新
     */
    Integer updateByMenuId(@Param("bean") T t, @Param("menuId") Integer menuId);

    /**
     * 根据MenuId删除
     */
    Integer deleteByMenuId(@Param("menuId") Integer menuId);

    List<T> selectALlMenuByRoleIds(@Param("roleIds") int[] roleIds);
}