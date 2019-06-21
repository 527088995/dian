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
import com.stylefeng.guns.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * pdf预览
 *
 * @author ...
 * @Date 2017年2月17日20:27:22
 */
@Controller
@Slf4j
public class PdfPreviewController extends BaseController {

    private String PREFIX = "/modular/demos/pdf/";

    /**
     * pdf预览
     */
    @RequestMapping("/pdf")
    public String pdf() {
        return PREFIX + "pdf.html";
    }

    /**
     * 预览
     */
    @GetMapping(value = "/loadPdfFile", produces = "application/pdf")
    @ResponseBody
    public FileSystemResource loadPdfFile(@RequestParam(value = "file", required = false) String file) {

        if (ToolUtil.isEmpty(file)) {
            file = "demo.pdf";
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource("assets/expand/pdf/demo/" + file);
            return new FileSystemResource(classPathResource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
