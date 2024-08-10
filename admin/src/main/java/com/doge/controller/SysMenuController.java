package com.doge.controller;


import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.enums.PermissionCodeEnum;
import com.doge.entity.po.SysMenu;
import com.doge.entity.query.SysMenuQuery;
import com.doge.entity.vo.ResponseVO;
import com.doge.service.SysMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:菜单表Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController("sysMenuController")
@RequestMapping("/settings")
public class SysMenuController extends ABaseController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     *
     */

    @RequestMapping("menuList")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_MENU)
    public ResponseVO menuList() {
        SysMenuQuery query = new SysMenuQuery();
        query.setFormat2Tree(true);
        query.setOrderBy("sort asc");
        List<SysMenu> sysMenuList = sysMenuService.findListByParam(query);
        return getSuccessResponseVO(sysMenuList);
    }

    /**
     *
     */
    @RequestMapping("saveMenu")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_MENU_EDIT)
    public ResponseVO saveMenu(@VerifyParam SysMenu sysMenu) {
        sysMenuService.saveMenu(sysMenu);
        return getSuccessResponseVO(null);
    }


    @RequestMapping("delMenu")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_MENU_EDIT)
    public ResponseVO delMenu(@VerifyParam(required = true) Integer menuId) {
        sysMenuService.deleteByMenuId(menuId);
        return getSuccessResponseVO(null);
    }


}