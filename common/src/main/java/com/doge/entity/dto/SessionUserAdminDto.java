package com.doge.entity.dto;

import com.doge.entity.vo.SysMenuVo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户session信息
 * @Author: doge
 * @Date: 2024/7/24 14:05
 */
public class SessionUserAdminDto implements Serializable {

    private static final long serialVersionUID = 1690149993220674991L;

    private Integer userId;
    private String userName;
    private Boolean superAdmin;
    private List<SysMenuVo> menuList;
    private List<String> permissionCodeList;


    public List<String> getPermissionCodeList() {
        return permissionCodeList;
    }

    public void setPermissionCodeList(List<String> permissionCodeList) {
        this.permissionCodeList = permissionCodeList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public List<SysMenuVo> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenuVo> menuList) {
        this.menuList = menuList;
    }
}
