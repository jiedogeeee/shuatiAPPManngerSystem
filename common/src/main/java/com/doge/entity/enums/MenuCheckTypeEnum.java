package com.doge.entity.enums;

/**
 * @Description: 检查半选或全选枚举
 * @Author: doge
 * @Date: 2024/7/25 23:06
 */
public enum MenuCheckTypeEnum {
    ALL(1, "全选"), HALF(0, "半选");

    private Integer type;
    private String desc;

    MenuCheckTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
