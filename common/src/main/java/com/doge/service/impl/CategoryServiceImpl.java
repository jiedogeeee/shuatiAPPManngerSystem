package com.doge.service.impl;

import com.doge.entity.enums.CategoryTypeEnum;
import com.doge.entity.enums.PageSize;
import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.entity.po.Category;
import com.doge.entity.query.CategoryQuery;
import com.doge.entity.query.SimplePage;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.exception.BusinessException;
import com.doge.mappers.CategoryMapper;
import com.doge.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:分类Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper<Category, CategoryQuery> categoryMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<Category> findListByParam(CategoryQuery query) {
        return this.categoryMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(CategoryQuery query) {
        return this.categoryMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<Category> findListByPage(CategoryQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<Category> list = this.findListByParam(query);
        PaginationResultVO<Category> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(Category bean) {
        return this.categoryMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<Category> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.categoryMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<Category> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.categoryMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据CategoryId查询
     */
    @Override
    public Category getByCategoryId(Integer categoryId) {
        return this.categoryMapper.selectByCategoryId(categoryId);
    }

    /**
     * 根据CategoryId更新
     */
    @Override
    public Integer updateByCategoryId(Category bean, Integer categoryId) {
        return this.categoryMapper.updateByCategoryId(bean, categoryId);
    }

    /**
     * 根据CategoryId删除
     */
    @Override
    public Integer deleteByCategoryId(Integer categoryId) {
        return this.categoryMapper.deleteByCategoryId(categoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCategory(Category category) {
        if (null == category.getCategoryId()) {
            CategoryQuery categoryQuery = new CategoryQuery();
            Integer count = this.categoryMapper.selectCount(categoryQuery);
            category.setSort(count + 1);
            this.categoryMapper.insert(category);
        } else {
            this.categoryMapper.updateByCategoryId(category, category.getCategoryId());
        }

        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setCategoryName(category.getCategoryName());
        categoryQuery.setType(category.getType());
        Integer count = this.categoryMapper.selectCount(categoryQuery);
        if (count > 1) {
            throw new BusinessException("分类每次重复");
        }
        if (null == category.getCategoryName()) {
            return;
        }

        Category dbInfo = this.categoryMapper.selectByCategoryId(category.getCategoryId());
        if (!dbInfo.getCategoryName().equals(category.getCategoryName())) {
            categoryMapper.updateCategoryName(category.getCategoryName(), category.getCategoryId());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeSort(String categoryIds) {
        String[] categoryArray = categoryIds.split(",");
        Integer index = 1;
        for (String categoryIdStr : categoryArray) {
            Integer categoryId = Integer.parseInt(categoryIdStr);
            Category category = new Category();
            category.setSort(index);
            this.categoryMapper.updateByCategoryId(category, categoryId);
            index++;
        }
    }

    @Override
    public List<Category> loadAllCategoryByType(Integer type) {
        CategoryTypeEnum typeEnum = CategoryTypeEnum.getByType(type);
        if (typeEnum == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        CategoryQuery categoryQuery = new CategoryQuery();
        categoryQuery.setOrderBy("sort asc");
        categoryQuery.setTypes(new Integer[]{typeEnum.getType(), CategoryTypeEnum.QUESTION_EXAM.getType()});
        return this.categoryMapper.selectList(categoryQuery);
    }
}