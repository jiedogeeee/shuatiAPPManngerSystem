package com.doge.controller;


import com.doge.entity.po.AppCarousel;

import com.doge.service.AppCarouselService;

import com.doge.entity.query.AppCarouselQuery;

import com.doge.entity.vo.ResponseVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Description:app轮播Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/appCarousel")
public class AppCarouselController extends ABaseController {

	@Resource
	private AppCarouselService appCarouselService;
	/**
	 * 新增
	 */

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AppCarouselQuery query) {
		return getSuccessResponseVO(appCarouselService.findListByPage(query));
	}

	/**
	 * 新增
	 */


	@RequestMapping("add")
	public ResponseVO add(AppCarousel bean) {
		return getSuccessResponseVO(this.appCarouselService.add(bean));
	}

	/**
	 * 批量新增
	 */


	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<AppCarousel> ListBean) {

		return getSuccessResponseVO(this.appCarouselService.addBatch(ListBean));
	}

	/**
	 * 批量新增或者更新
	 */


	@RequestMapping("addBatchOrUpdate")
	public ResponseVO addBatchOrUpdate(@RequestBody List<AppCarousel> ListBean) {

		return getSuccessResponseVO(this.appCarouselService.addBatchOrUpdate(ListBean));
	}

	/**
	 * 根据CarouselId查询
	 */

	@RequestMapping("getByCarouselId")
	public ResponseVO getByCarouselId(Integer carouselId) {
		return getSuccessResponseVO(this.appCarouselService.getByCarouselId(carouselId));
	}

	/**
	 * 根据CarouselId更新
	 */

	@RequestMapping("updateByCarouselId")
	public ResponseVO updateByCarouselId(AppCarousel bean ,Integer carouselId) {
		return getSuccessResponseVO(this.appCarouselService.updateByCarouselId(bean,carouselId));
	}

	/**
	 * 根据CarouselId删除
	 */

	@RequestMapping("deleteByCarouselId")
	public ResponseVO deleteByCarouselId(Integer carouselId) {
		return getSuccessResponseVO(this.appCarouselService.deleteByCarouselId(carouselId));
	}

}