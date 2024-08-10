package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:考试问题Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppExamQuestionMapper<T,P> extends BaseMapper {
	/**
	 * 根据Id查询
	 */
	 T selectById(@Param("id") Integer id);
	/**
	 * 根据Id更新
	 */
	 Integer updateById(@Param("bean") T t ,@Param("id") Integer id);
	/**
	 * 根据Id删除
	 */
	 Integer deleteById(@Param("id") Integer id);
	/**
	 * 根据ExamIdAndUserIdAndQuestionId查询
	 */
	 T selectByExamIdAndUserIdAndQuestionId(@Param("examId") Integer examId, @Param("userId") String userId, @Param("questionId") Integer questionId);
	/**
	 * 根据ExamIdAndUserIdAndQuestionId更新
	 */
	 Integer updateByExamIdAndUserIdAndQuestionId(@Param("bean") T t ,@Param("examId") Integer examId, @Param("userId") String userId, @Param("questionId") Integer questionId);
	/**
	 * 根据ExamIdAndUserIdAndQuestionId删除
	 */
	 Integer deleteByExamIdAndUserIdAndQuestionId(@Param("examId") Integer examId, @Param("userId") String userId, @Param("questionId") Integer questionId);
}