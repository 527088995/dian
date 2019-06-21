package com.stylefeng.guns.modular.guar.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.guar.model.GuarAcctBsInfoFb;
import com.stylefeng.guns.modular.guar.dao.GuarAcctBsInfoFbMapper;
import com.stylefeng.guns.modular.guar.service.IGuarAcctBsInfoFbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业担保-账户信息流水表 服务实现类
 * </p>
 *
 * @author wj123
 * @since 2019-06-19
 */
@Service
public class GuarAcctBsInfoFbServiceImpl extends ServiceImpl<GuarAcctBsInfoFbMapper, GuarAcctBsInfoFb> implements IGuarAcctBsInfoFbService {

    @Override
    public List<Map<String, Object>> list(Page page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
