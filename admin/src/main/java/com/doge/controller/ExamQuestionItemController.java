package com.doge.controller;


import com.doge.entity.po.ExamQuestionItem;

import com.doge.service.ExamQuestionItemService;

import com.doge.entity.query.ExamQuestionItemQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/examQuestionItem")
public class ExamQuestionItemController extends ABaseController {

	@Resource
	private ExamQuestionItemService examQuestionItemService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ExamQuestionItemQuery query) {
		return getSuccessResponseVO(examQuestionItemService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(ExamQuestionItem bean) {
		return getSuccessResponseVO(this.examQuestionItemService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ExamQuestionItem> ListBean) {

		return getSuccessResponseVO(this.examQuestionItemService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<ExamQuestionItem> ListBean) {

		return getSuccessResponseVO(this.examQuestionItemService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据ItemId查询
	 */

	@RequestMapping("getByItemId")
	public ResponseVO getByItemId(Integer itemId) {
		return getSuccessResponseVO(this.examQuestionItemService.getByItemId(itemId));
	}

	/**
	 * 根据ItemId更新
	 */

	@RequestMapping("updateByItemId")
	public ResponseVO updateByItemId(ExamQuestionItem bean ,Integer itemId) {
		return getSuccessResponseVO(this.examQuestionItemService.updateByItemId(bean,itemId));
	}

	/**
	 * 根据ItemId删除
	 */

	@RequestMapping("deleteByItemId")
	public ResponseVO deleteByItemId(Integer itemId) {
		return getSuccessResponseVO(this.examQuestionItemService.deleteByItemId(itemId));
	}

}