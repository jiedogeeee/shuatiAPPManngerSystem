package com.doge.controller;


import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.enums.PermissionCodeEnum;
import com.doge.entity.po.SysRole;
import com.doge.entity.query.SysRoleQuery;
import com.doge.entity.vo.ResponseVO;
import com.doge.exception.BusinessException;
import com.doge.service.SysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:系统角色表Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController
@RequestMapping("/settings")
public class SysRoleController extends ABaseController {

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/loadRoles")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_LIST)
    public ResponseVO loadRoles(SysRoleQuery query) {
        query.setOrderBy("create_time desc");
        return getSuccessResponseVO(sysRoleService.findListByPage(query));
    }

    @RequestMapping("/loadAllRoles")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_LIST)
    public ResponseVO loadAllRoles() {
        SysRoleQuery query = new SysRoleQuery();
        query.setOrderBy("create_time desc");

        return getSuccessResponseVO(sysRoleService.findListByParam(query));
    }


    @RequestMapping("/saveRole")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_EDIT)
    public ResponseVO saveRole(@VerifyParam SysRole bean,
                               String menuIds,
                               String halfMenuIds) throws BusinessException {

        this.sysRoleService.saveRole(bean, menuIds, halfMenuIds);
        return getSuccessResponseVO(null);
    }


    @RequestMapping("/saveRoleMenu")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_EDIT)
    public ResponseVO saveRoleMenu(@VerifyParam(required = true) Integer roleId,
                                   @VerifyParam(required = true) String menuIds,
                                   String halfMenuIds) throws BusinessException {

        this.sysRoleService.saveRoleManu(roleId, menuIds, halfMenuIds);
        return getSuccessResponseVO(null);
    }

    /**
     * 根据RoleId查询
     */

    @RequestMapping("/getRoleByRoleId")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_LIST)
    public ResponseVO getRoleByRoleId(@VerifyParam(required = true) Integer roleId) {
        SysRole sysRole = this.sysRoleService.getByRoleId(roleId);
        return getSuccessResponseVO(sysRole);
    }


    /**
     * 根据RoleId删除
     */

    @RequestMapping("/delRole")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ROLE_DEL)
    public ResponseVO delRole(Integer roleId) throws BusinessException {
        this.sysRoleService.deleteByRoleId(roleId);
        return getSuccessResponseVO(null);
    }

}