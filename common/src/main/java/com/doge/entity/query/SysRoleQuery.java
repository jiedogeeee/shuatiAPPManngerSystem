package com.doge.entity.query;

import java.util.Date;


/**
 * @Description:系统角色表查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class SysRoleQuery extends BaseQuery{
	/**
	 * 角色ID
	 */
	private Integer roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	private String roleNameFuzzy;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	private String roleDescFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;

	private String lastUpdateTimeStart;

	private String lastUpdateTimeEnd;

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setRoleNameFuzzy(String roleNameFuzzy) {
		this.roleNameFuzzy = roleNameFuzzy;
	}

	public String getRoleNameFuzzy() {
		return this.roleNameFuzzy;
	}

	public void setRoleDescFuzzy(String roleDescFuzzy) {
		this.roleDescFuzzy = roleDescFuzzy;
	}

	public String getRoleDescFuzzy() {
		return this.roleDescFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	public void setLastUpdateTimeStart(String lastUpdateTimeStart) {
		this.lastUpdateTimeStart = lastUpdateTimeStart;
	}

	public String getLastUpdateTimeStart() {
		return this.lastUpdateTimeStart;
	}

	public void setLastUpdateTimeEnd(String lastUpdateTimeEnd) {
		this.lastUpdateTimeEnd = lastUpdateTimeEnd;
	}

	public String getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}

}