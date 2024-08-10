package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface ExamQuestionItemMapper<T,P> extends BaseMapper {
	/**
	 * 根据ItemId查询
	 */
	 T selectByItemId(@Param("itemId") Integer itemId);
	/**
	 * 根据ItemId更新
	 */
	 Integer updateByItemId(@Param("bean") T t ,@Param("itemId") Integer itemId);
	/**
	 * 根据ItemId删除
	 */
	 Integer deleteByItemId(@Param("itemId") Integer itemId);
}