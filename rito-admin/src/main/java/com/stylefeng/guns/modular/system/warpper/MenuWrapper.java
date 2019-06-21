/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stylefeng.guns.modular.system.warpper;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.warpper.BaseControllerWrapper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.exception.YesOrNotEnum;
import com.stylefeng.guns.core.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 * @author ...
 * @date 2017年2月19日15:07:29
 */
public class MenuWrapper extends BaseControllerWrapper {

    public MenuWrapper(Map<String, Object> single) {
        super(single);
    }

    public MenuWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public MenuWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public MenuWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((String) map.get("status")));

        String menuFlag = (String) map.get("menuFlag");
        for (YesOrNotEnum value : YesOrNotEnum.values()) {
            if (value.name().equals(menuFlag)) {
                map.put("isMenuName", value.getDesc());
            }
        }
    }

}
