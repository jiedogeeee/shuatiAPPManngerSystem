package com.doge.mappers;

import org.apache.ibatis.annotations.Param;
/**
 * @Description:考试题目Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface ExamQuestionMapper<T,P> extends BaseMapper {
	/**
	 * 根据QuestionId查询
	 */
	 T selectByQuestionId(@Param("questionId") Integer questionId);
	/**
	 * 根据QuestionId更新
	 */
	 Integer updateByQuestionId(@Param("bean") T t ,@Param("questionId") Integer questionId);
	/**
	 * 根据QuestionId删除
	 */
	 Integer deleteByQuestionId(@Param("questionId") Integer questionId);
}