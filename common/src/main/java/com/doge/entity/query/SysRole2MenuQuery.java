package com.doge.entity.query;



/**
 * @Description:角色对应的菜单权限表查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class SysRole2MenuQuery extends BaseQuery{
	/**
	 * 角色ID
	 */
	private Integer roleId;

	/**
	 * 菜单ID
	 */
	private Integer menuId;

	/**
	 * 0:半选 1:全选
	 */
	private Integer checkType;

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getCheckType() {
		return this.checkType;
	}

}