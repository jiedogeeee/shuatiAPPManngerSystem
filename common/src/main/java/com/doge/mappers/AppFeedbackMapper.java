package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:问题反馈Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppFeedbackMapper<T,P> extends BaseMapper {
	/**
	 * 根据FeedbackId查询
	 */
	 T selectByFeedbackId(@Param("feedbackId") Integer feedbackId);
	/**
	 * 根据FeedbackId更新
	 */
	 Integer updateByFeedbackId(@Param("bean") T t ,@Param("feedbackId") Integer feedbackId);
	/**
	 * 根据FeedbackId删除
	 */
	 Integer deleteByFeedbackId(@Param("feedbackId") Integer feedbackId);
	/**
	 * 根据FeedbackIdAndUserId查询
	 */
	 T selectByFeedbackIdAndUserId(@Param("feedbackId") Integer feedbackId, @Param("userId") String userId);
	/**
	 * 根据FeedbackIdAndUserId更新
	 */
	 Integer updateByFeedbackIdAndUserId(@Param("bean") T t ,@Param("feedbackId") Integer feedbackId, @Param("userId") String userId);
	/**
	 * 根据FeedbackIdAndUserId删除
	 */
	 Integer deleteByFeedbackIdAndUserId(@Param("feedbackId") Integer feedbackId, @Param("userId") String userId);
}