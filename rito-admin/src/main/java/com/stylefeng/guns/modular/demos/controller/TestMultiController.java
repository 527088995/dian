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
package com.stylefeng.guns.modular.demos.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.response.SuccessResponseData;
import com.stylefeng.guns.modular.demos.service.TestMultiDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试多数据源回滚
 *
 * @author ...
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/multi")
public class TestMultiController extends BaseController {

    @Autowired
    private TestMultiDbService testMultiDbService;

    @RequestMapping("")
    public Object auth() {
        testMultiDbService.beginTest();
        return SuccessResponseData.success();
    }

}

