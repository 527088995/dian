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
package com.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import com.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.response.ResponseData;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dto.RoleDto;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IRoleService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.RoleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author ...
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/modular/system/role";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * 跳转到角色列表页面
     *
     * @author ...
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 跳转到添加角色
     *
     * @author ...
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }

    /**
     * 跳转到修改角色
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/role_edit")
    public String roleEdit(@RequestParam Long roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.selectById(roleId);
        LogObjectHolder.me().set(role);
        return PREFIX + "/role_edit.html";
    }

    /**
     * 跳转到权限分配
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") Long roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        return PREFIX + "/role_assign.html";
    }

    /**
     * 获取角色列表
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String roleName) {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String, Object>> roles = this.roleService.selectRoles(page, roleName);
        page.setRecords(new RoleWrapper(roles).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 角色新增
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData add(Role role) {
        this.roleService.addRole(role);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData edit(RoleDto roleDto) {
        this.roleService.editRole(roleDto);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除角色", key = "roleId", dict = DeleteDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData remove(@RequestParam Long roleId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(roleId));

        this.roleService.delRoleById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.selectById(roleId);
        Map<String, Object> roleMap = BeanUtil.beanToMap(role);

        Long pid = role.getPid();
        String pName = ConstantFactory.me().getSingleRoleName(pid);
        roleMap.put("pName", pName);

        return ResponseData.success(roleMap);
    }

    /**
     * 配置权限
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData setAuthority(@RequestParam("roleId") Long roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 获取角色列表
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表，通过用户id
     *
     * @author ...
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Long userId) {
        User theUser = this.userService.selectById(userId);
        String roleId = theUser.getRoleId();
        if (ToolUtil.isEmpty(roleId)) {
            return this.roleService.roleTreeList();
        } else {

            String[] strArray = roleId.split(",");

            //转化成Long[]
            Long[] longArray = new Long[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                longArray[i] = Long.valueOf(strArray[i]);
            }

            return this.roleService.roleTreeListByRoleId(longArray);
        }
    }

}
