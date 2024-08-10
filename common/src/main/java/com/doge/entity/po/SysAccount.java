package com.doge.entity.po;

import com.doge.annotation.VerifyParam;
import com.doge.entity.enums.DateTimePatternEnum;
import com.doge.entity.enums.VerifyRegexEnum;
import com.doge.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description:账号信息
 * @Author:我爱你
 * @date:2024/07/08
 */
public class SysAccount implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 手机号
     */
    @VerifyParam(required = true, regex = VerifyRegexEnum.PHONE)
    private String phone;

    /**
     * 用户名
     */
    @VerifyParam(required = true, max = 20)
    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    @VerifyParam(regex = VerifyRegexEnum.PASSWORD)
    private String password;

    /**
     * 职位
     */
    private String position;

    /**
     * 状态 0:禁用 1:启用
     */

    private Integer status;

    /**
     * 用户拥有的角色多个用逗号隔开
     */
    @VerifyParam(required = true)
    private String roles;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String roleNames;

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public String toString() {
        return "用户ID:" + (userId == null ? "空" : userId) + ",手机号:" + (phone == null ? "空" : phone) + ",用户名:" + (userName == null ? "空" : userName) + ",密码:" + (password == null ? "空" : password) + ",职位:" + (position == null ? "空" : position) + ",状态 0:禁用 1:启用:" + (status == null ? "空" : status) + ",用户拥有的角色多个用逗号隔开:" + (roles == null ? "空" : roles) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
    }
}