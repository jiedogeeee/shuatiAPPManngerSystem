package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppExamQuestion;
import com.doge.entity.query.AppExamQuestionQuery;
import java.util.List;
/**
 * @Description:考试问题Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppExamQuestionService {

	/**
	 * 根据条件查询列表
	 */


		List<AppExamQuestion> findListByParam( AppExamQuestionQuery query); 

	/**
	 * 根据条件查询数量
	 */


		Integer findCountByParam( AppExamQuestionQuery query);

	/**
	 * 分页查询
	 */

		PaginationResultVO<AppExamQuestion> findListByPage(AppExamQuestionQuery query);

	/**
	 * 新增
	 */

		Integer add(AppExamQuestion bean);

	/**
	 * 批量新增
	 */

		Integer addBatch(List<AppExamQuestion> ListBean);

	/**
	 * 批量新增或者更新
	 */

		Integer addBatchOrUpdate(List<AppExamQuestion> ListBean);

	/**
	 * 根据Id查询
	 */
	AppExamQuestion getById(Integer id);
	/**
	 * 根据Id更新
	 */
	 Integer updateById(AppExamQuestion bean ,Integer id);
	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);
	/**
	 * 根据ExamIdAndUserIdAndQuestionId查询
	 */
	AppExamQuestion getByExamIdAndUserIdAndQuestionId(Integer examId, String userId, Integer questionId);
	/**
	 * 根据ExamIdAndUserIdAndQuestionId更新
	 */
	 Integer updateByExamIdAndUserIdAndQuestionId(AppExamQuestion bean ,Integer examId, String userId, Integer questionId);
	/**
	 * 根据ExamIdAndUserIdAndQuestionId删除
	 */
	 Integer deleteByExamIdAndUserIdAndQuestionId(Integer examId, String userId, Integer questionId);
}