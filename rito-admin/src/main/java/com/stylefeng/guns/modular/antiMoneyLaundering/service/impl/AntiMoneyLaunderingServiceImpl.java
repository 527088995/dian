package com.stylefeng.guns.modular.antiMoneyLaundering.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.modular.antiMoneyLaundering.dao.AntiMoneyLaunderingMapper;
import com.stylefeng.guns.modular.antiMoneyLaundering.model.AntiMoneyLaundering;
import com.stylefeng.guns.modular.antiMoneyLaundering.service.IAntiMoneyLaunderingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 反洗钱 服务实现类
 * </p>
 *
 * @author wj
 * @since 2019-04-19
 */
@Service
public class AntiMoneyLaunderingServiceImpl extends ServiceImpl<AntiMoneyLaunderingMapper, AntiMoneyLaundering> implements IAntiMoneyLaunderingService {

    /**
     * 获取通知列表
     *
     * @author ...
     * @Date 2018/12/23 6:05 PM
     */
    public List<Map<String, Object>> list(Page page, String condition) {
        return this.baseMapper.list(page, condition);
    }

    /**
     * 生成XML
     *
     * @author lyc
     * @Date 2019/4/23 3:41 PM
     */
    public AntiMoneyLaundering createXML(Long antiId) {
        AntiMoneyLaundering antiMoneyLaundering = this.baseMapper.createXML(antiId);
        return antiMoneyLaundering;
    }
}
