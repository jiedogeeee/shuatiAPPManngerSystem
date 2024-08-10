package com.doge.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:分类Mapper
 * @Author:我爱你
 * @date:2024/07/08
 */
public interface CategoryMapper<T, P> extends BaseMapper {
    /**
     * 根据CategoryId查询
     */
    T selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 根据CategoryId更新
     */
    Integer updateByCategoryId(@Param("bean") T t, @Param("categoryId") Integer categoryId);

    /**
     * 根据CategoryId删除
     */
    Integer deleteByCategoryId(@Param("categoryId") Integer categoryId);


    void updateCategoryName(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId);
}