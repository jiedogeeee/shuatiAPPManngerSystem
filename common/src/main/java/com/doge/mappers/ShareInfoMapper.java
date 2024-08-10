package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:文章Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface ShareInfoMapper<T,P> extends BaseMapper {
	/**
	 * 根据ShareId查询
	 */
	 T selectByShareId(@Param("shareId") Integer shareId);
	/**
	 * 根据ShareId更新
	 */
	 Integer updateByShareId(@Param("bean") T t ,@Param("shareId") Integer shareId);
	/**
	 * 根据ShareId删除
	 */
	 Integer deleteByShareId(@Param("shareId") Integer shareId);
}