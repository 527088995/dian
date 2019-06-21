package com.stylefeng.guns.modular.antiMoneyLaundering.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.antiMoneyLaundering.model.AntiMoneyLaundering;
import com.stylefeng.guns.modular.system.model.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 反洗钱 服务类
 * </p>
 *
 * @author wj
 * @since 2019-04-19
 */
public interface IAntiMoneyLaunderingService extends IService<AntiMoneyLaundering> {

    List<Map<String, Object>> list(Page page, String condition);;

    AntiMoneyLaundering createXML(Long antiId);
}
