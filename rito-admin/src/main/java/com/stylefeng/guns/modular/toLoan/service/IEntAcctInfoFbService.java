package com.stylefeng.guns.modular.toLoan.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.modular.toLoan.model.EntAcctInfoFb;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业借贷账户信息流水表 服务类
 * </p>
 *
 * @author wj
 * @since 2019-06-11
 */
public interface IEntAcctInfoFbService extends IService<EntAcctInfoFb> {


    LayuiPageInfo list();
}
