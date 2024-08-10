package com.doge.controller;


import com.doge.annotation.GlobalInterceptor;
import com.doge.annotation.VerifyParam;
import com.doge.entity.config.AppConfig;
import com.doge.entity.enums.PermissionCodeEnum;
import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.entity.enums.UserStatusEnum;
import com.doge.entity.enums.VerifyRegexEnum;
import com.doge.entity.po.SysAccount;
import com.doge.entity.query.SysAccountQuery;
import com.doge.entity.vo.ResponseVO;
import com.doge.exception.BusinessException;
import com.doge.service.SysAccountService;
import com.doge.utils.StringTools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:账号信息Controller
 * @Author:我爱你
 * @date:2024/07/08
 */
@RestController("sysAccountController")
@RequestMapping("/settings")
public class SysAccountController extends ABaseController {

    @Resource
    private SysAccountService sysAccountService;

    @Resource
    private AppConfig appConfig;

    /**
     * 新增
     */

    @RequestMapping("/loadAccountList")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_LIST)
    public ResponseVO loadAccountList(SysAccountQuery query) {
        query.setOrderBy("create_time desc");
        query.setQueryRoles(true);
        return getSuccessResponseVO(sysAccountService.findListByPage(query));
    }

    /**
     * 新增
     */


    @RequestMapping("/saveAccount")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.APP_UPDATE_EDIT)
    public ResponseVO saveAccount(@VerifyParam SysAccount sysAccount) throws BusinessException {
        sysAccountService.saveSysAccount(sysAccount);
        return getSuccessResponseVO(null);
    }

    /**
     * 修改密码
     *
     * @param
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/updatePassword")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_UPDATE_PASSWORD)
    public ResponseVO updatePassword(@VerifyParam Integer userId,
                                     @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password) throws BusinessException {
        SysAccount updateInfo = new SysAccount();
        updateInfo.setPassword(StringTools.encodeByMD5(password));
        sysAccountService.updateByUserId(updateInfo, userId);
        return getSuccessResponseVO(null);
    }

    /**
     * 更新账号状态
     *
     * @param userId
     * @param
     * @return
     * @throws BusinessException
     */

    @RequestMapping("/updateStatus")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_OP_STATUS)
    public ResponseVO updateStatus(@VerifyParam Integer userId,
                                   @VerifyParam(required = true) Integer status) throws BusinessException {
        UserStatusEnum userStatusEnum = UserStatusEnum.getByStatus(status);
        if (userStatusEnum == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        SysAccount updateInfo = new SysAccount();
        updateInfo.setStatus(status);
        sysAccountService.updateByUserId(updateInfo, userId);
        return getSuccessResponseVO(null);
    }

    /**
     * 删除
     */

    @RequestMapping("/delAccount")
    @GlobalInterceptor(permissionCode = PermissionCodeEnum.SETTINGS_ACCOUNT_DEL)
    public ResponseVO delAccount(@VerifyParam Integer userId
    ) throws BusinessException {

        SysAccount sysAccount = this.sysAccountService.getByUserId(userId);
        if (!StringTools.isEmpty(appConfig.getSuperAdminPhones()) && ArrayUtils.contains(appConfig.getSuperAdminPhones().split(","),
                sysAccount.getPhone())) {
            throw new BusinessException("系统超级管理员不允许删除");
        }

        sysAccountService.deleteByUserId(userId);
        return getSuccessResponseVO(null);
    }


}