package com.doge.service;

import com.doge.entity.po.Category;
import com.doge.entity.query.CategoryQuery;
import com.doge.entity.vo.PaginationResultVO;

import java.util.List;

/**
 * @Description:分类Service
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface CategoryService {

    /**
     * 根据条件查询列表
     */


    List<Category> findListByParam(CategoryQuery query);

    /**
     * 根据条件查询数量
     */


    Integer findCountByParam(CategoryQuery query);

    /**
     * 分页查询
     */

    PaginationResultVO<Category> findListByPage(CategoryQuery query);

    /**
     * 新增
     */

    Integer add(Category bean);

    /**
     * 批量新增
     */

    Integer addBatch(List<Category> ListBean);

    /**
     * 批量新增或者更新
     */

    Integer addBatchOrUpdate(List<Category> ListBean);

    /**
     * 根据CategoryId查询
     */
    Category getByCategoryId(Integer categoryId);

    /**
     * 根据CategoryId更新
     */
    Integer updateByCategoryId(Category bean, Integer categoryId);

    /**
     * 根据CategoryId删除
     */
    Integer deleteByCategoryId(Integer categoryId);

    void saveCategory(Category category);


    void changeSort(String categoryIds);

    List<Category> loadAllCategoryByType(Integer type);
}