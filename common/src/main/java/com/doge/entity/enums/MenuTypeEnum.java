package com.doge.entity.enums;

/**
 * @Description:
 * @Author: doge
 * @Date: 2024/7/8 14:38
 */
public enum MenuTypeEnum {
    MENU(0, "菜单"), BUTTON(1, "按钮");

    private Integer type;
    private String desc;

    MenuTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static MenuTypeEnum getMenuTypeByType(Integer type) {
        if (null == type) {
            return null;
        }
        for (MenuTypeEnum item : MenuTypeEnum.values()) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }
}
