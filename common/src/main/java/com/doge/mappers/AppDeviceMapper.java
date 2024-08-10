package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:设备信息Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppDeviceMapper<T,P> extends BaseMapper {
	/**
	 * 根据DeviceId查询
	 */
	 T selectByDeviceId(@Param("deviceId") String deviceId);
	/**
	 * 根据DeviceId更新
	 */
	 Integer updateByDeviceId(@Param("bean") T t ,@Param("deviceId") String deviceId);
	/**
	 * 根据DeviceId删除
	 */
	 Integer deleteByDeviceId(@Param("deviceId") String deviceId);
}