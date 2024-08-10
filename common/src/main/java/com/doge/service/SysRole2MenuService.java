package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.SysRole2Menu;
import com.doge.entity.query.SysRole2MenuQuery;
import java.util.List;
/**
 * @Description:角色对应的菜单权限表Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface SysRole2MenuService {

	/**
	 * 根据条件查询列表
	 */


		List<SysRole2Menu> findListByParam( SysRole2MenuQuery query); 

	/**
	 * 根据条件查询数量
	 */


		Integer findCountByParam( SysRole2MenuQuery query);

	/**
	 * 分页查询
	 */

		PaginationResultVO<SysRole2Menu> findListByPage(SysRole2MenuQuery query);

	/**
	 * 新增
	 */

		Integer add(SysRole2Menu bean);

	/**
	 * 批量新增
	 */

		Integer addBatch(List<SysRole2Menu> ListBean);

	/**
	 * 批量新增或者更新
	 */

		Integer addBatchOrUpdate(List<SysRole2Menu> ListBean);

	/**
	 * 根据RoleIdAndMenuId查询
	 */
	SysRole2Menu getByRoleIdAndMenuId(Integer roleId, Integer menuId);
	/**
	 * 根据RoleIdAndMenuId更新
	 */
	 Integer updateByRoleIdAndMenuId(SysRole2Menu bean ,Integer roleId, Integer menuId);
	/**
	 * 根据RoleIdAndMenuId删除
	 */
	 Integer deleteByRoleIdAndMenuId(Integer roleId, Integer menuId);
}