package com.doge.service;

import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.po.AppCarousel;
import com.doge.entity.query.AppCarouselQuery;
import java.util.List;
/**
 * @Description:app轮播Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface AppCarouselService {

	/**
	 * 根据条件查询列表
	 */


		List<AppCarousel> findListByParam( AppCarouselQuery query); 

	/**
	 * 根据条件查询数量
	 */


		Integer findCountByParam( AppCarouselQuery query);

	/**
	 * 分页查询
	 */

		PaginationResultVO<AppCarousel> findListByPage(AppCarouselQuery query);

	/**
	 * 新增
	 */

		Integer add(AppCarousel bean);

	/**
	 * 批量新增
	 */

		Integer addBatch(List<AppCarousel> ListBean);

	/**
	 * 批量新增或者更新
	 */

		Integer addBatchOrUpdate(List<AppCarousel> ListBean);

	/**
	 * 根据CarouselId查询
	 */
	AppCarousel getByCarouselId(Integer carouselId);
	/**
	 * 根据CarouselId更新
	 */
	 Integer updateByCarouselId(AppCarousel bean ,Integer carouselId);
	/**
	 * 根据CarouselId删除
	 */
	 Integer deleteByCarouselId(Integer carouselId);
}