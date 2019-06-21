package com.stylefeng.guns.core.exception;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/28 10:26
 */
public enum YesOrNotEnum {
    Y(true, "是", 1),
    N(false, "否", 0);

    private Boolean flag;
    private String desc;
    private Integer code;

    private YesOrNotEnum(Boolean flag, String desc, Integer code) {
        this.flag = flag;
        this.desc = desc;
        this.code = code;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            YesOrNotEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                YesOrNotEnum s = var1[var3];
                if (s.getCode().equals(status)) {
                    return s.getDesc();
                }
            }

            return "";
        }
    }

    public Boolean getFlag() {
        return this.flag;
    }

    public String getDesc() {
        return this.desc;
    }

    public Integer getCode() {
        return this.code;
    }
}
