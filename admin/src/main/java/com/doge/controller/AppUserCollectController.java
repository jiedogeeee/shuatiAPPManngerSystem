package com.doge.controller;


import com.doge.entity.po.AppUserCollect;

import com.doge.service.AppUserCollectService;

import com.doge.entity.query.AppUserCollectQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:用户收藏Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appUserCollect")
public class AppUserCollectController extends ABaseController {

	@Resource
	private AppUserCollectService appUserCollectService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppUserCollectQuery query) {
		return getSuccessResponseVO(appUserCollectService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppUserCollect bean) {
		return getSuccessResponseVO(this.appUserCollectService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppUserCollect> ListBean) {

		return getSuccessResponseVO(this.appUserCollectService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppUserCollect> ListBean) {

		return getSuccessResponseVO(this.appUserCollectService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据CollectId查询
	 */

	@RequestMapping("getByCollectId")
	public ResponseVO getByCollectId(Integer collectId) {
		return getSuccessResponseVO(this.appUserCollectService.getByCollectId(collectId));
	}

	/**
	 * 根据CollectId更新
	 */

	@RequestMapping("updateByCollectId")
	public ResponseVO updateByCollectId(AppUserCollect bean ,Integer collectId) {
		return getSuccessResponseVO(this.appUserCollectService.updateByCollectId(bean,collectId));
	}

	/**
	 * 根据CollectId删除
	 */

	@RequestMapping("deleteByCollectId")
	public ResponseVO deleteByCollectId(Integer collectId) {
		return getSuccessResponseVO(this.appUserCollectService.deleteByCollectId(collectId));
	}

	/**
	 * 根据UserIdAndObjectIdAndCollectType查询
	 */

	@RequestMapping("getByUserIdAndObjectIdAndCollectType")
	public ResponseVO getByUserIdAndObjectIdAndCollectType(String userId, String objectId, Integer collectType) {
		return getSuccessResponseVO(this.appUserCollectService.getByUserIdAndObjectIdAndCollectType(userId,objectId,collectType));
	}

	/**
	 * 根据UserIdAndObjectIdAndCollectType更新
	 */

	@RequestMapping("updateByUserIdAndObjectIdAndCollectType")
	public ResponseVO updateByUserIdAndObjectIdAndCollectType(AppUserCollect bean ,String userId, String objectId, Integer collectType) {
		return getSuccessResponseVO(this.appUserCollectService.updateByUserIdAndObjectIdAndCollectType(bean,userId,objectId,collectType));
	}

	/**
	 * 根据UserIdAndObjectIdAndCollectType删除
	 */

	@RequestMapping("deleteByUserIdAndObjectIdAndCollectType")
	public ResponseVO deleteByUserIdAndObjectIdAndCollectType(String userId, String objectId, Integer collectType) {
		return getSuccessResponseVO(this.appUserCollectService.deleteByUserIdAndObjectIdAndCollectType(userId,objectId,collectType));
	}

}