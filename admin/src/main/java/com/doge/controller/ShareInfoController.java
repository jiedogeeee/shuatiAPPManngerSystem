package com.doge.controller;


import com.doge.entity.po.ShareInfo;

import com.doge.service.ShareInfoService;

import com.doge.entity.query.ShareInfoQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:文章Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/shareInfo")
public class ShareInfoController extends ABaseController {

	@Resource
	private ShareInfoService shareInfoService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ShareInfoQuery query) {
		return getSuccessResponseVO(shareInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(ShareInfo bean) {
		return getSuccessResponseVO(this.shareInfoService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ShareInfo> ListBean) {

		return getSuccessResponseVO(this.shareInfoService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<ShareInfo> ListBean) {

		return getSuccessResponseVO(this.shareInfoService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据ShareId查询
	 */

	@RequestMapping("getByShareId")
	public ResponseVO getByShareId(Integer shareId) {
		return getSuccessResponseVO(this.shareInfoService.getByShareId(shareId));
	}

	/**
	 * 根据ShareId更新
	 */

	@RequestMapping("updateByShareId")
	public ResponseVO updateByShareId(ShareInfo bean ,Integer shareId) {
		return getSuccessResponseVO(this.shareInfoService.updateByShareId(bean,shareId));
	}

	/**
	 * 根据ShareId删除
	 */

	@RequestMapping("deleteByShareId")
	public ResponseVO deleteByShareId(Integer shareId) {
		return getSuccessResponseVO(this.shareInfoService.deleteByShareId(shareId));
	}

}