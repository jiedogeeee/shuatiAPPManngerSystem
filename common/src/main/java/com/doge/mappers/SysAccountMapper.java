package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:账号信息Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface SysAccountMapper<T,P> extends BaseMapper {
	/**
	 * 根据UserId查询
	 */
	 T selectByUserId(@Param("userId") Integer userId);
	/**
	 * 根据UserId更新
	 */
	 Integer updateByUserId(@Param("bean") T t ,@Param("userId") Integer userId);
	/**
	 * 根据UserId删除
	 */
	 Integer deleteByUserId(@Param("userId") Integer userId);
	/**
	 * 根据Phone查询
	 */
	 T selectByPhone(@Param("phone") String phone);
	/**
	 * 根据Phone更新
	 */
	 Integer updateByPhone(@Param("bean") T t ,@Param("phone") String phone);
	/**
	 * 根据Phone删除
	 */
	 Integer deleteByPhone(@Param("phone") String phone);
}