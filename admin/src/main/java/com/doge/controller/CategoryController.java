package com.doge.controller;


import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.enums.PermissionCodeEnum;
import com.doge.entity.po.Category;
import com.doge.entity.query.CategoryQuery;
import com.doge.entity.vo.ResponseVO;
import com.doge.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:分类Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends ABaseController {

    @Resource
    private CategoryService categoryService;

    /**
     * 新增
     */

    @RequestMapping("/loadAllCategory")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEOGRY_LIST)
    public ResponseVO loadAllCategory(CategoryQuery query) {
        query.setOrderBy("sort asc");
        return getSuccessResponseVO(categoryService.findListByParam(query));
    }

    /**
     * 新增
     */
    @RequestMapping("/saveCategory")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEOGRY_EDIT)
    public ResponseVO saveCategory(Category category) {
        categoryService.saveCategory(category);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/delCategory")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEOGRY_DEL)
    public ResponseVO delCategory(@VerifyParam(required = true) Integer categoryId) {
        categoryService.deleteByCategoryId(categoryId);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/changeSort")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEOGRY_DEL)
    public ResponseVO changeSort(@VerifyParam(required = true) String categoryIds) {
        categoryService.changeSort(categoryIds);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/loadAllCategory4Select")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.CATEOGRY_LIST)
    public ResponseVO loadAllCategory4Select(@VerifyParam(required = true) Integer type) {
        List<Category> list = categoryService.loadAllCategoryByType(type);
        return getSuccessResponseVO(list);
    }

}