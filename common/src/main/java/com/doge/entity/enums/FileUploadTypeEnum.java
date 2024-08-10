package com.doge.entity.enums;

/**
 * @Description:
 * @Author: doge
 * @Date: 2024/6/8 23:55
 */
public enum FileUploadTypeEnum {
    CATEGORY(0, 150, "分类图片"),
    Carouse1(1, 400, "轮播图"),
    SHARE_LARGE(2, 400, "分享大图"),
    SHARE_SMALL(3, 100, "分享小图");

    private Integer type;
    private Integer maxLength;
    private String desc;

    FileUploadTypeEnum(Integer type, Integer maxLength, String desc) {
        this.type = type;
        this.maxLength = maxLength;
        this.desc = desc;
    }


    public static FileUploadTypeEnum getType(Integer type) {
        for (FileUploadTypeEnum at : FileUploadTypeEnum.values()) {
            if (at.type.equals(type)) {
                return at;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
