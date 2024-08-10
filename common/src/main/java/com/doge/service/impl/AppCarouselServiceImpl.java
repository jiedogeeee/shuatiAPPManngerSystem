package com.doge.service.impl;

import com.doge.entity.query.SimplePage;
import com.doge.entity.enums.PageSize;
import com.doge.entity.po.AppCarousel;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.AppCarouselMapper;
import com.doge.service.AppCarouselService;
import com.doge.entity.query.AppCarouselQuery;

import java.util.List;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:app轮播Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("appCarouselService")
public class AppCarouselServiceImpl implements AppCarouselService {

    @Resource
    private AppCarouselMapper<AppCarousel, AppCarouselQuery> appCarouselMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<AppCarousel> findListByParam(AppCarouselQuery query) {
        return this.appCarouselMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(AppCarouselQuery query) {
        return this.appCarouselMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<AppCarousel> findListByPage(AppCarouselQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<AppCarousel> list = this.findListByParam(query);
        PaginationResultVO<AppCarousel> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(AppCarousel bean) {
        return this.appCarouselMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<AppCarousel> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appCarouselMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<AppCarousel> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.appCarouselMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据CarouselId查询
     */
    @Override
    public AppCarousel getByCarouselId(Integer carouselId) {
        return this.appCarouselMapper.selectByCarouselId(carouselId);
    }

    /**
     * 根据CarouselId更新
     */
    @Override
    public Integer updateByCarouselId(AppCarousel bean, Integer carouselId) {
        return this.appCarouselMapper.updateByCarouselId(bean, carouselId);
    }

    /**
     * 根据CarouselId删除
     */
    @Override
    public Integer deleteByCarouselId(Integer carouselId) {
        return this.appCarouselMapper.deleteByCarouselId(carouselId);
    }

}