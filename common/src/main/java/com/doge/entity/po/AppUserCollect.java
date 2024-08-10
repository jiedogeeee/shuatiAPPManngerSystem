package com.doge.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.doge.entity.enums.DateTimePatternEnum;
import com.doge.utils.DateUtils;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @Description:用户收藏
 * @Author:我爱你
 * @date:2024/07/08
 */
public class AppUserCollect implements Serializable {
    /**
     * 收藏ID
     */
    private Integer collectId;

    /**
     * 用户ID
     */
    @JsonIgnore
    private String userId;

    /**
     * 主体ID 问题ID,考题ID,分享文章ID
     */
    private String objectId;

    /**
     * 0:分享收藏 1:问题收藏  2:考题收藏
     */
    private Integer collectType;

    /**
     * 收藏时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectTime;

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getCollectId() {
        return this.collectId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }

    public Integer getCollectType() {
        return this.collectType;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Date getCollectTime() {
        return this.collectTime;
    }

    @Override
    public String toString() {
        return "收藏ID:" + (collectId == null ? "空" : collectId) + ",用户ID:" + (userId == null ? "空" : userId) + ",主体ID 问题ID,考题ID,分享文章ID:" + (objectId == null ? "空" : objectId) + ",0:分享收藏 1:问题收藏  2:考题收藏:" + (collectType == null ? "空" : collectType) + ",收藏时间:" + (collectTime == null ? "空" : DateUtils.format(collectTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
    }
}