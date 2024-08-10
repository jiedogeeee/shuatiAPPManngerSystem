package com.doge.controller;


import com.doge.entity.po.AppUpdate;

import com.doge.service.AppUpdateService;

import com.doge.entity.query.AppUpdateQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:app发布Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appUpdate")
public class AppUpdateController extends ABaseController {

	@Resource
	private AppUpdateService appUpdateService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppUpdateQuery query) {
		return getSuccessResponseVO(appUpdateService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppUpdate bean) {
		return getSuccessResponseVO(this.appUpdateService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppUpdate> ListBean) {

		return getSuccessResponseVO(this.appUpdateService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppUpdate> ListBean) {

		return getSuccessResponseVO(this.appUpdateService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据Id查询
	 */

	@RequestMapping("getById")
	public ResponseVO getById(Integer id) {
		return getSuccessResponseVO(this.appUpdateService.getById(id));
	}

	/**
	 * 根据Id更新
	 */

	@RequestMapping("updateById")
	public ResponseVO updateById(AppUpdate bean ,Integer id) {
		return getSuccessResponseVO(this.appUpdateService.updateById(bean,id));
	}

	/**
	 * 根据Id删除
	 */

	@RequestMapping("deleteById")
	public ResponseVO deleteById(Integer id) {
		return getSuccessResponseVO(this.appUpdateService.deleteById(id));
	}

}