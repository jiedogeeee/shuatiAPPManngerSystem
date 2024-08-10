package com.doge.controller;


import com.doge.entity.po.AppUserInfo;

import com.doge.service.AppUserInfoService;

import com.doge.entity.query.AppUserInfoQuery;

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
@RequestMapping("/appUserInfo")
public class AppUserInfoController extends ABaseController {

	@Resource
	private AppUserInfoService appUserInfoService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppUserInfoQuery query) {
		return getSuccessResponseVO(appUserInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppUserInfo bean) {
		return getSuccessResponseVO(this.appUserInfoService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppUserInfo> ListBean) {

		return getSuccessResponseVO(this.appUserInfoService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppUserInfo> ListBean) {

		return getSuccessResponseVO(this.appUserInfoService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据UserId查询
	 */

	@RequestMapping("getByUserId")
	public ResponseVO getByUserId(String userId) {
		return getSuccessResponseVO(this.appUserInfoService.getByUserId(userId));
	}

	/**
	 * 根据UserId更新
	 */

	@RequestMapping("updateByUserId")
	public ResponseVO updateByUserId(AppUserInfo bean ,String userId) {
		return getSuccessResponseVO(this.appUserInfoService.updateByUserId(bean,userId));
	}

	/**
	 * 根据UserId删除
	 */

	@RequestMapping("deleteByUserId")
	public ResponseVO deleteByUserId(String userId) {
		return getSuccessResponseVO(this.appUserInfoService.deleteByUserId(userId));
	}

	/**
	 * 根据Email查询
	 */

	@RequestMapping("getByEmail")
	public ResponseVO getByEmail(String email) {
		return getSuccessResponseVO(this.appUserInfoService.getByEmail(email));
	}

	/**
	 * 根据Email更新
	 */

	@RequestMapping("updateByEmail")
	public ResponseVO updateByEmail(AppUserInfo bean ,String email) {
		return getSuccessResponseVO(this.appUserInfoService.updateByEmail(bean,email));
	}

	/**
	 * 根据Email删除
	 */

	@RequestMapping("deleteByEmail")
	public ResponseVO deleteByEmail(String email) {
		return getSuccessResponseVO(this.appUserInfoService.deleteByEmail(email));
	}

}