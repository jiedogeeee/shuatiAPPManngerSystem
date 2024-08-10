package com.doge.entity.po;

import com.doge.annotation.VerifyParam;

import java.io.Serializable;
import java.util.List;


/**
 * @Description:菜单表
 * @Author:我爱你
 * @date:2024/07/08
 */
public class SysMenu implements Serializable {
    /**
     * menu_id，自增主键
     */
    private Integer menuId;

    /**
     * 菜单名
     */
    @VerifyParam(required = true, max = 32)
    private String menuName;

    /**
     * 菜单类型 0：菜单 1：按钮
     */
    @VerifyParam(required = true)
    private Integer menuType;

    /**
     * 菜单跳转到的地址
     */
    private String menuUrl;

    /**
     * 上级菜单ID
     */
    @VerifyParam(required = true)
    private Integer pId;

    /**
     * 菜单排序
     */
    @VerifyParam(required = true)
    private Integer sort;

    /**
     * 权限编码
     */
    @VerifyParam(required = true, max = 50)
    private String permissionCode;

    /**
     * 0:可分配菜单 1:基础菜单
     */

    private Integer showType;

    /**
     * 图标
     */
    @VerifyParam(max = 50)
    private String icon;

    private List<SysMenu> children;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuId() {
        return this.menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuType() {
        return this.menuType;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }


    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getShowType() {
        return this.showType;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return "menu_id，自增主键:" + (menuId == null ? "空" : menuId) + ",菜单名:" + (menuName == null ? "空" : menuName) + ",菜单类型 0：菜单 1：按钮:" + (menuType == null ? "空" : menuType) + ",菜单跳转到的地址:" + (menuUrl == null ? "空" : menuUrl) + ",上级菜单ID:" + (pId == null ? "空" : pId) + ",菜单排序:" + (sort == null ? "空" : sort) + ",权限编码:" + (permissionCode == null ? "空" : permissionCode) + ",0:可分配菜单 1:基础菜单:" + (showType == null ? "空" : showType) + ",图标:" + (icon == null ? "空" : icon);
    }
}