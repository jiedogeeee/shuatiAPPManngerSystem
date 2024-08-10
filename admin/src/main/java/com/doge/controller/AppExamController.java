package com.doge.controller;


import com.doge.entity.po.AppExam;

import com.doge.service.AppExamService;

import com.doge.entity.query.AppExamQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:用户在线考试Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appExam")
public class AppExamController extends ABaseController {

	@Resource
	private AppExamService appExamService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppExamQuery query) {
		return getSuccessResponseVO(appExamService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppExam bean) {
		return getSuccessResponseVO(this.appExamService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppExam> ListBean) {

		return getSuccessResponseVO(this.appExamService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppExam> ListBean) {

		return getSuccessResponseVO(this.appExamService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据ExamId查询
	 */

	@RequestMapping("getByExamId")
	public ResponseVO getByExamId(Integer examId) {
		return getSuccessResponseVO(this.appExamService.getByExamId(examId));
	}

	/**
	 * 根据ExamId更新
	 */

	@RequestMapping("updateByExamId")
	public ResponseVO updateByExamId(AppExam bean ,Integer examId) {
		return getSuccessResponseVO(this.appExamService.updateByExamId(bean,examId));
	}

	/**
	 * 根据ExamId删除
	 */

	@RequestMapping("deleteByExamId")
	public ResponseVO deleteByExamId(Integer examId) {
		return getSuccessResponseVO(this.appExamService.deleteByExamId(examId));
	}

}