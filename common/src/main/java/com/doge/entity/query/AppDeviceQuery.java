package com.doge.entity.query;

import java.util.Date;


/**
 * @Description:设备信息查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class AppDeviceQuery extends BaseQuery{
	/**
	 * 设备ID
	 */
	private String deviceId;

	private String deviceIdFuzzy;

	/**
	 * 手机品牌
	 */
	private String deviceBrand;

	private String deviceBrandFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 最后使用时间
	 */
	private Date lastUseTime;

	private String lastUseTimeStart;

	private String lastUseTimeEnd;

	/**
	 * ip
	 */
	private String ip;

	private String ipFuzzy;

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	public String getDeviceBrand() {
		return this.deviceBrand;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setLastUseTime(Date lastUseTime) {
		this.lastUseTime = lastUseTime;
	}

	public Date getLastUseTime() {
		return this.lastUseTime;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
	}

	public void setDeviceBrandFuzzy(String deviceBrandFuzzy) {
		this.deviceBrandFuzzy = deviceBrandFuzzy;
	}

	public String getDeviceBrandFuzzy() {
		return this.deviceBrandFuzzy;
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

	public void setLastUseTimeStart(String lastUseTimeStart) {
		this.lastUseTimeStart = lastUseTimeStart;
	}

	public String getLastUseTimeStart() {
		return this.lastUseTimeStart;
	}

	public void setLastUseTimeEnd(String lastUseTimeEnd) {
		this.lastUseTimeEnd = lastUseTimeEnd;
	}

	public String getLastUseTimeEnd() {
		return this.lastUseTimeEnd;
	}

	public void setIpFuzzy(String ipFuzzy) {
		this.ipFuzzy = ipFuzzy;
	}

	public String getIpFuzzy() {
		return this.ipFuzzy;
	}

}