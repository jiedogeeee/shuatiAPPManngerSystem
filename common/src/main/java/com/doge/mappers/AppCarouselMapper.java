package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:app轮播Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppCarouselMapper<T,P> extends BaseMapper {
	/**
	 * 根据CarouselId查询
	 */
	 T selectByCarouselId(@Param("carouselId") Integer carouselId);
	/**
	 * 根据CarouselId更新
	 */
	 Integer updateByCarouselId(@Param("bean") T t ,@Param("carouselId") Integer carouselId);
	/**
	 * 根据CarouselId删除
	 */
	 Integer deleteByCarouselId(@Param("carouselId") Integer carouselId);
}