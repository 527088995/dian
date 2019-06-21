package com.stylefeng.guns.modular.demos.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author ...
 * @since 2018-12-07
 */
@Service
public class OtherDbService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private IUserService userService;

    @DataSource(name = "otherdb")
    public void otherdb() {
        User user = new User();
        user.setAccount(RandomUtil.randomString(5));
        user.setPassword(RandomUtil.randomString(5));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCreateUser(1L);
        user.setUpdateUser(1L);
        userService.insert(user);
    }

}
