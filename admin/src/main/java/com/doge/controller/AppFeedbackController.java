package com.doge.controller;


import com.doge.entity.po.AppFeedback;

import com.doge.service.AppFeedbackService;

import com.doge.entity.query.AppFeedbackQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:问题反馈Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appFeedback")
public class AppFeedbackController extends ABaseController {

	@Resource
	private AppFeedbackService appFeedbackService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppFeedbackQuery query) {
		return getSuccessResponseVO(appFeedbackService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppFeedback bean) {
		return getSuccessResponseVO(this.appFeedbackService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppFeedback> ListBean) {

		return getSuccessResponseVO(this.appFeedbackService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppFeedback> ListBean) {

		return getSuccessResponseVO(this.appFeedbackService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据FeedbackId查询
	 */

	@RequestMapping("getByFeedbackId")
	public ResponseVO getByFeedbackId(Integer feedbackId) {
		return getSuccessResponseVO(this.appFeedbackService.getByFeedbackId(feedbackId));
	}

	/**
	 * 根据FeedbackId更新
	 */

	@RequestMapping("updateByFeedbackId")
	public ResponseVO updateByFeedbackId(AppFeedback bean ,Integer feedbackId) {
		return getSuccessResponseVO(this.appFeedbackService.updateByFeedbackId(bean,feedbackId));
	}

	/**
	 * 根据FeedbackId删除
	 */

	@RequestMapping("deleteByFeedbackId")
	public ResponseVO deleteByFeedbackId(Integer feedbackId) {
		return getSuccessResponseVO(this.appFeedbackService.deleteByFeedbackId(feedbackId));
	}

	/**
	 * 根据FeedbackIdAndUserId查询
	 */

	@RequestMapping("getByFeedbackIdAndUserId")
	public ResponseVO getByFeedbackIdAndUserId(Integer feedbackId, String userId) {
		return getSuccessResponseVO(this.appFeedbackService.getByFeedbackIdAndUserId(feedbackId,userId));
	}

	/**
	 * 根据FeedbackIdAndUserId更新
	 */

	@RequestMapping("updateByFeedbackIdAndUserId")
	public ResponseVO updateByFeedbackIdAndUserId(AppFeedback bean ,Integer feedbackId, String userId) {
		return getSuccessResponseVO(this.appFeedbackService.updateByFeedbackIdAndUserId(bean,feedbackId,userId));
	}

	/**
	 * 根据FeedbackIdAndUserId删除
	 */

	@RequestMapping("deleteByFeedbackIdAndUserId")
	public ResponseVO deleteByFeedbackIdAndUserId(Integer feedbackId, String userId) {
		return getSuccessResponseVO(this.appFeedbackService.deleteByFeedbackIdAndUserId(feedbackId,userId));
	}

}