package com.stylefeng.guns.modular.flowable.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWrapper;

import java.util.Map;

/**
 * 报销列表的包装
 *
 * @author ...
 * @date 2017年12月4日21:56:06
 */
public class ExpenseWarpper extends BaseControllerWrapper {


    public ExpenseWarpper(Map<String, Object> single) {
        super(single);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        // map.put("delFlag", ConstantFactory.me().getDelFlag((String) map.get("delFlag")));
//        Long creater = (Long) map.get("createUser");
//        map.put("createUser", ConstantFactory.me().getUserNameById(creater));
    }

}
