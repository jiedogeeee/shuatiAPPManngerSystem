package com.doge.entity.po;

import com.doge.annotation.VerifyParam;

import java.io.Serializable;


/**
 * @Description:分类
 * @Author:我爱你
 * @date:2024/07/08
 */
public class Category implements Serializable {
    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 名称
     */
    @VerifyParam(required = true)
    private String categoryName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String iconPath;

    /**
     * 背景颜色
     */
    private String bgColor;

    /**
     * 0:问题分类 1:考题分类 2:问题分类和考题分类
     */
    @VerifyParam(required = true)
    private Integer type;

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "分类ID:" + (categoryId == null ? "空" : categoryId) + ",名称:" + (categoryName == null ? "空" : categoryName) + ",排序:" + (sort == null ? "空" : sort) + ",图标:" + (iconPath == null ? "空" : iconPath) + ",背景颜色:" + (bgColor == null ? "空" : bgColor) + ",0:问题分类 1:考题分类 2:问题分类和考题分类:" + (type == null ? "空" : type);
    }
}