package com.doge.controller;


import com.doge.entity.po.ExamQuestion;

import com.doge.service.ExamQuestionService;

import com.doge.entity.query.ExamQuestionQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:考试题目Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/examQuestion")
public class ExamQuestionController extends ABaseController {

	@Resource
	private ExamQuestionService examQuestionService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ExamQuestionQuery query) {
		return getSuccessResponseVO(examQuestionService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(ExamQuestion bean) {
		return getSuccessResponseVO(this.examQuestionService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ExamQuestion> ListBean) {

		return getSuccessResponseVO(this.examQuestionService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<ExamQuestion> ListBean) {

		return getSuccessResponseVO(this.examQuestionService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据QuestionId查询
	 */

	@RequestMapping("getByQuestionId")
	public ResponseVO getByQuestionId(Integer questionId) {
		return getSuccessResponseVO(this.examQuestionService.getByQuestionId(questionId));
	}

	/**
	 * 根据QuestionId更新
	 */

	@RequestMapping("updateByQuestionId")
	public ResponseVO updateByQuestionId(ExamQuestion bean ,Integer questionId) {
		return getSuccessResponseVO(this.examQuestionService.updateByQuestionId(bean,questionId));
	}

	/**
	 * 根据QuestionId删除
	 */

	@RequestMapping("deleteByQuestionId")
	public ResponseVO deleteByQuestionId(Integer questionId) {
		return getSuccessResponseVO(this.examQuestionService.deleteByQuestionId(questionId));
	}

}