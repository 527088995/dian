package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.DictType;
import com.stylefeng.guns.modular.system.model.params.DictParam;
import com.stylefeng.guns.modular.system.model.params.DictTypeParam;
import com.stylefeng.guns.modular.system.model.result.DictResult;

import java.util.List;

/**
 * 字典服务
 *
 * @author ...
 * @date 2017-04-27 17:00
 */
public interface IDictTypeService extends IService<DictType> {


    void delete(DictTypeParam dictTypeParam);

    void add(DictTypeParam dictTypeParam);

    void update(DictTypeParam dictTypeParam);

    LayuiPageInfo findPageBySpec(DictTypeParam dictTypeParam);
}
