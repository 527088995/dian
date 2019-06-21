package com.stylefeng.guns.modular.system.warpper;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.warpper.BaseControllerWrapper;
import com.stylefeng.guns.core.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wj
 * @Date: 2019/4/16 15:32
 * @Description:
 */
public class BaseWrapper extends BaseControllerWrapper {
    public BaseWrapper(Map<String, Object> single) {
        super(single);
    }

    public BaseWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public BaseWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public BaseWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        // map.put("delFlag", ConstantFactory.me().getDelFlag((String) map.get("delFlag")));
//        Long creater = (Long) map.get("createUser");
//        map.put("createUser", ConstantFactory.me().getUserNameById(creater));
    }
}
