package com.stylefeng.guns.modular.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.generator.dao.DatabaseInfoMapper;
import com.stylefeng.guns.modular.generator.po.DatabaseInfo;
import com.stylefeng.guns.modular.generator.service.IDatabaseInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据库表 服务实现类
 * </p>
 *
 * @author wj
 * @since 2019-04-25
 */
@Service
public class DatabaseInfoServiceImpl extends ServiceImpl<DatabaseInfoMapper, DatabaseInfo> implements IDatabaseInfoService {

}
