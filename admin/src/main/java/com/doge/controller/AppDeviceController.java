package com.doge.controller;


import com.doge.entity.po.AppDevice;

import com.doge.service.AppDeviceService;

import com.doge.entity.query.AppDeviceQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:设备信息Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appDevice")
public class AppDeviceController extends ABaseController {

	@Resource
	private AppDeviceService appDeviceService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppDeviceQuery query) {
		return getSuccessResponseVO(appDeviceService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppDevice bean) {
		return getSuccessResponseVO(this.appDeviceService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppDevice> ListBean) {

		return getSuccessResponseVO(this.appDeviceService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppDevice> ListBean) {

		return getSuccessResponseVO(this.appDeviceService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据DeviceId查询
	 */

	@RequestMapping("getByDeviceId")
	public ResponseVO getByDeviceId(String deviceId) {
		return getSuccessResponseVO(this.appDeviceService.getByDeviceId(deviceId));
	}

	/**
	 * 根据DeviceId更新
	 */

	@RequestMapping("updateByDeviceId")
	public ResponseVO updateByDeviceId(AppDevice bean ,String deviceId) {
		return getSuccessResponseVO(this.appDeviceService.updateByDeviceId(bean,deviceId));
	}

	/**
	 * 根据DeviceId删除
	 */

	@RequestMapping("deleteByDeviceId")
	public ResponseVO deleteByDeviceId(String deviceId) {
		return getSuccessResponseVO(this.appDeviceService.deleteByDeviceId(deviceId));
	}

}