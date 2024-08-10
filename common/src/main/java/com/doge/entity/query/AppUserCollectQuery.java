package com.doge.entity.query;

import java.util.Date;


/**
 * @Description:用户收藏查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class AppUserCollectQuery extends BaseQuery{
	/**
	 * 收藏ID
	 */
	private Integer collectId;

	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 主体ID 问题ID,考题ID,分享文章ID
	 */
	private String objectId;

	private String objectIdFuzzy;

	/**
	 * 0:分享收藏 1:问题收藏  2:考题收藏
	 */
	private Integer collectType;

	/**
	 * 收藏时间
	 */
	private Date collectTime;

	private String collectTimeStart;

	private String collectTimeEnd;

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public Integer getCollectId() {
		return this.collectId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}

	public Integer getCollectType() {
		return this.collectType;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public Date getCollectTime() {
		return this.collectTime;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	public void setObjectIdFuzzy(String objectIdFuzzy) {
		this.objectIdFuzzy = objectIdFuzzy;
	}

	public String getObjectIdFuzzy() {
		return this.objectIdFuzzy;
	}

	public void setCollectTimeStart(String collectTimeStart) {
		this.collectTimeStart = collectTimeStart;
	}

	public String getCollectTimeStart() {
		return this.collectTimeStart;
	}

	public void setCollectTimeEnd(String collectTimeEnd) {
		this.collectTimeEnd = collectTimeEnd;
	}

	public String getCollectTimeEnd() {
		return this.collectTimeEnd;
	}

}