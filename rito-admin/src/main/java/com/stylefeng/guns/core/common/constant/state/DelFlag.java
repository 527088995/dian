package com.stylefeng.guns.core.common.constant.state;

import lombok.Getter;

/**
 * @Description:删除标记
 *
 * @Auther: wj
 * @Date: 2019/4/17 09:26
 */
@Getter
public enum DelFlag {

    DELETED("1", "已删除"),
    UNDELETED("0", "未删除");

    String code;
    String message;

    DelFlag(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getDescription(String status) {
        if (status == null) {
            return "";
        } else {
            for (DelFlag s : DelFlag.values()) {
                if (s.getCode().equals(status)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
