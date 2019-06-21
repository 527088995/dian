package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.core.node.MenuNode;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author ...123
 * @since 2018-02-22
 */
public interface IUserService extends IService<User> {

    /**
     * 修改用户状态
     */
    int setStatus(@Param("userId") Long userId, @Param("status") String status);

    /**
     * 修改密码
     */
    void changePwd(@Param("userId") String userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     */
    List<Map<String, Object>> selectUsers(Page page, @Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Long deptid);

    /**
     * 设置用户的角色
     */
    int setRoles(@Param("userId") Long userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);

    /**
     * 功能描述: 添加用户
     *
     * @param user
     * @return void
     * @author wj
     * @date 2019/5/29 15:07
     */
    void addUser(UserDto user);

    /**
     * 刷新当前登录用户的信息
     *
     * @author ...
     * @Date 2019/1/19 5:59 PM
     */
    void refreshCurrentUser();

    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     *
     * @author ...
     * @Date 2018/12/24 22:44
     */
    void assertAuth(Long userId);
    void editUser(UserDto user);

    List<MenuNode> getUserMenuNodes(List<Long> roleList);
}
