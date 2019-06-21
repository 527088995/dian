package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.params.DictParam;
import com.stylefeng.guns.modular.system.model.result.DictResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典服务
 *
 * @author ...
 * @date 2017-04-27 17:00
 */
public interface IDictService extends IService<Dict> {

    LayuiPageInfo findPageBySpec(DictParam param);
    List<ZTreeNode> dictTreeList(Long dictTypeId, Long dictId);
    DictResult dictDetail(Long dictId);

    void update(DictParam dictParam);

    void add(DictParam dictParam);

    List<Dict> selectByParentCode(String code);
}
