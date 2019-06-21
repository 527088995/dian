package com.stylefeng.guns.modular.demos.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Guns复杂表单的示例
 * </p>
 *
 * @author ...
 * @since 2019-02-18
 */
@Data
public class EgFormResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long formId;

    /**
     * 名称
     */
    private String name;

    /**
     * 单个时间
     */
    private String singleTime;

    /**
     * 时间段
     */
    private String betweenTime;

    /**
     * 单行选择框
     */
    private String simpleSelect;

    /**
     * 分组选择框
     */
    private String fenzuSelect;

    /**
     * 搜索选择框
     */
    private String modules;

    /**
     * 多个选择
     */
    private String multiSelectHidden;

    /**
     * 图片文件id
     */
    private String pictureInputHidden;

    /**
     * 文件id
     */
    private String fileInputHidden;

    /**
     * close
     */
    private String closeFlag;

    /**
     * 单选框
     */
    private String sex;

    /**
     * 复选框
     */
    private String[] checkbox;

    /**
     * 数字选择
     */
    private Integer number;

    /**
     * 长字段
     */
    private String longText;

}
