package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.ExamQuestionItem;
import com.doge.entity.query.ExamQuestionItemQuery;
import java.util.List;
/**
 * @Description:Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface ExamQuestionItemService {

	/**
	 * 根据条件查询列表
	 */


		List<ExamQuestionItem> findListByParam( ExamQuestionItemQuery query); 

	/**
	 * 根据条件查询数量
	 */


		Integer findCountByParam( ExamQuestionItemQuery query);

	/**
	 * 分页查询
	 */

		PaginationResultVO<ExamQuestionItem> findListByPage(ExamQuestionItemQuery query);

	/**
	 * 新增
	 */

		Integer add(ExamQuestionItem bean);

	/**
	 * 批量新增
	 */

		Integer addBatch(List<ExamQuestionItem> ListBean);

	/**
	 * 批量新增或者更新
	 */

		Integer addBatchOrUpdate(List<ExamQuestionItem> ListBean);

	/**
	 * 根据ItemId查询
	 */
	ExamQuestionItem getByItemId(Integer itemId);
	/**
	 * 根据ItemId更新
	 */
	 Integer updateByItemId(ExamQuestionItem bean ,Integer itemId);
	/**
	 * 根据ItemId删除
	 */
	 Integer deleteByItemId(Integer itemId);
}