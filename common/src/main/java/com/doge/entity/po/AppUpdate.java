package com.doge.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.doge.entity.enums.DateTimePatternEnum;
import com.doge.utils.DateUtils;

import java.util.Date;
import java.io.Serializable;


/**
 * @Description:app发布
 * @Author:我爱你
 * @date:2024/07/08
 */
public class AppUpdate implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 版本号
     */
    private String version;

    /**
     * 更新描述
     */
    private String updateDesc;

    /**
     * 更新类型0:全更新 1:局部热更新
     */
    private Integer updateType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 0:未发布 1:灰度发布 2:全网发布
     */
    private Integer status;

    /**
     * 灰度设备ID
     */
    private String grayscaleDevice;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    public String getUpdateDesc() {
        return this.updateDesc;
    }

    public void setUpdateType(Integer updateType) {
        this.updateType = updateType;
    }

    public Integer getUpdateType() {
        return this.updateType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setGrayscaleDevice(String grayscaleDevice) {
        this.grayscaleDevice = grayscaleDevice;
    }

    public String getGrayscaleDevice() {
        return this.grayscaleDevice;
    }

    @Override
    public String toString() {
        return "自增ID:" + (id == null ? "空" : id) + ",版本号:" + (version == null ? "空" : version) + ",更新描述:" + (updateDesc == null ? "空" : updateDesc) + ",更新类型0:全更新 1:局部热更新:" + (updateType == null ? "空" : updateType) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",0:未发布 1:灰度发布 2:全网发布:" + (status == null ? "空" : status) + ",灰度设备ID:" + (grayscaleDevice == null ? "空" : grayscaleDevice);
    }
}