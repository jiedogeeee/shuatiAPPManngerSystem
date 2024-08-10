package com.doge.entity.query;


/**
 * @Description:菜单表查询对象
 * @Author:我爱你
 * @date:2024/07/08
 */
public class SysMenuQuery extends BaseQuery {
    /**
     * menu_id，自增主键
     */
    private Integer menuId;

    /**
     * 菜单名
     */
    private String menuName;

    private String menuNameFuzzy;

    /**
     * 菜单类型 0：菜单 1：按钮
     */
    private Integer menuType;

    /**
     * 菜单跳转到的地址
     */
    private String menuUrl;

    private String menuUrlFuzzy;

    /**
     * 上级菜单ID
     */
    private Integer pId;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 权限编码
     */
    private String permissionCode;

    private String permissionCodeFuzzy;

    /**
     * 0:可分配菜单 1:基础菜单
     */
    private Integer showType;

    /**
     * 图标
     */
    private String icon;

    private String iconFuzzy;

    private Boolean format2Tree;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Boolean getFormat2Tree() {
        return format2Tree;
    }

    public void setFormat2Tree(Boolean format2Tree) {
        this.format2Tree = format2Tree;
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

    public void setMenuNameFuzzy(String menuNameFuzzy) {
        this.menuNameFuzzy = menuNameFuzzy;
    }

    public String getMenuNameFuzzy() {
        return this.menuNameFuzzy;
    }

    public void setMenuUrlFuzzy(String menuUrlFuzzy) {
        this.menuUrlFuzzy = menuUrlFuzzy;
    }

    public String getMenuUrlFuzzy() {
        return this.menuUrlFuzzy;
    }

    public void setPermissionCodeFuzzy(String permissionCodeFuzzy) {
        this.permissionCodeFuzzy = permissionCodeFuzzy;
    }

    public String getPermissionCodeFuzzy() {
        return this.permissionCodeFuzzy;
    }

    public void setIconFuzzy(String iconFuzzy) {
        this.iconFuzzy = iconFuzzy;
    }

    public String getIconFuzzy() {
        return this.iconFuzzy;
    }

}