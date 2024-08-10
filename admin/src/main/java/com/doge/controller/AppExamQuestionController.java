package com.doge.controller;


import com.doge.entity.po.AppExamQuestion;

import com.doge.service.AppExamQuestionService;

import com.doge.entity.query.AppExamQuestionQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:考试问题Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appExamQuestion")
public class AppExamQuestionController extends ABaseController {

	@Resource
	private AppExamQuestionService appExamQuestionService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppExamQuestionQuery query) {
		return getSuccessResponseVO(appExamQuestionService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppExamQuestion bean) {
		return getSuccessResponseVO(this.appExamQuestionService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppExamQuestion> ListBean) {

		return getSuccessResponseVO(this.appExamQuestionService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppExamQuestion> ListBean) {

		return getSuccessResponseVO(this.appExamQuestionService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据Id查询
	 */

	@RequestMapping("getById")
	public ResponseVO getById(Integer id) {
		return getSuccessResponseVO(this.appExamQuestionService.getById(id));
	}

	/**
	 * 根据Id更新
	 */

	@RequestMapping("updateById")
	public ResponseVO updateById(AppExamQuestion bean ,Integer id) {
		return getSuccessResponseVO(this.appExamQuestionService.updateById(bean,id));
	}

	/**
	 * 根据Id删除
	 */

	@RequestMapping("deleteById")
	public ResponseVO deleteById(Integer id) {
		return getSuccessResponseVO(this.appExamQuestionService.deleteById(id));
	}

	/**
	 * 根据ExamIdAndUserIdAndQuestionId查询
	 */

	@RequestMapping("getByExamIdAndUserIdAndQuestionId")
	public ResponseVO getByExamIdAndUserIdAndQuestionId(Integer examId, String userId, Integer questionId) {
		return getSuccessResponseVO(this.appExamQuestionService.getByExamIdAndUserIdAndQuestionId(examId,userId,questionId));
	}

	/**
	 * 根据ExamIdAndUserIdAndQuestionId更新
	 */

	@RequestMapping("updateByExamIdAndUserIdAndQuestionId")
	public ResponseVO updateByExamIdAndUserIdAndQuestionId(AppExamQuestion bean ,Integer examId, String userId, Integer questionId) {
		return getSuccessResponseVO(this.appExamQuestionService.updateByExamIdAndUserIdAndQuestionId(bean,examId,userId,questionId));
	}

	/**
	 * 根据ExamIdAndUserIdAndQuestionId删除
	 */

	@RequestMapping("deleteByExamIdAndUserIdAndQuestionId")
	public ResponseVO deleteByExamIdAndUserIdAndQuestionId(Integer examId, String userId, Integer questionId) {
		return getSuccessResponseVO(this.appExamQuestionService.deleteByExamIdAndUserIdAndQuestionId(examId,userId,questionId));
	}

}