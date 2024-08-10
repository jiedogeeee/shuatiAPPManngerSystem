package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:用户收藏Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppUserCollectMapper<T,P> extends BaseMapper {
	/**
	 * 根据CollectId查询
	 */
	 T selectByCollectId(@Param("collectId") Integer collectId);
	/**
	 * 根据CollectId更新
	 */
	 Integer updateByCollectId(@Param("bean") T t ,@Param("collectId") Integer collectId);
	/**
	 * 根据CollectId删除
	 */
	 Integer deleteByCollectId(@Param("collectId") Integer collectId);
	/**
	 * 根据UserIdAndObjectIdAndCollectType查询
	 */
	 T selectByUserIdAndObjectIdAndCollectType(@Param("userId") String userId, @Param("objectId") String objectId, @Param("collectType") Integer collectType);
	/**
	 * 根据UserIdAndObjectIdAndCollectType更新
	 */
	 Integer updateByUserIdAndObjectIdAndCollectType(@Param("bean") T t ,@Param("userId") String userId, @Param("objectId") String objectId, @Param("collectType") Integer collectType);
	/**
	 * 根据UserIdAndObjectIdAndCollectType删除
	 */
	 Integer deleteByUserIdAndObjectIdAndCollectType(@Param("userId") String userId, @Param("objectId") String objectId, @Param("collectType") Integer collectType);
}