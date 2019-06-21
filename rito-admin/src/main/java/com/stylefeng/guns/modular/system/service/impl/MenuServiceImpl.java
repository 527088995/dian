package com.stylefeng.guns.modular.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.MenuStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.RequestEmptyException;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.listener.ConfigListener;
import com.stylefeng.guns.core.node.MenuNode;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ApiMenuFilter;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.MenuMapper;
import com.stylefeng.guns.modular.system.dto.MenuDto;
import com.stylefeng.guns.modular.system.model.Menu;
import com.stylefeng.guns.modular.system.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 菜单服务
 *
 * @author ...
 * @date 2017-05-05 22:20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 添加菜单
     *
     * @author ...
     * @Date 2018/12/23 5:59 PM
     */
    @Transactional
    public void addMenu(MenuDto menuDto) {

        if (ToolUtil.isOneEmpty(menuDto, menuDto.getCode(), menuDto.getName(), menuDto.getPid(), menuDto.getMenuFlag(), menuDto.getUrl())) {
            throw new RequestEmptyException();
        }

        //判断是否已经存在该编号
        String existedMenuName = ConstantFactory.me().getMenuNameByCode(menuDto.getCode());
        if (ToolUtil.isNotEmpty(existedMenuName)) {
            throw new ServiceException(BizExceptionEnum.EXISTED_THE_MENU);
        }

        //组装属性，设置父级菜单编号
        Menu resultMenu = this.menuSetPcode(menuDto);

        resultMenu.setStatus(MenuStatus.ENABLE.getCode());
        resultMenu.setCreateUser(ShiroKit.getUser().getId());
        resultMenu.setCreateTime(new Date());
        this.insert(resultMenu);
    }

    /**
     * 更新菜单
     *
     * @author ...
     * @Date 2019/2/27 4:09 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuDto menuDto) {

        //如果菜单为空
        if (menuDto == null || ToolUtil.isOneEmpty(menuDto.getMenuId(), menuDto.getCode())) {
            throw new RequestEmptyException();
        }

        //获取旧的菜单
        Long id = menuDto.getMenuId();
        Menu menu = this.selectById(id);

        if (menu == null) {
            throw new RequestEmptyException();
        }

        //设置父级菜单编号
        Menu resultMenu = this.menuSetPcode(menuDto);

        //查找该节点的子集合,并修改相应的pcodes和level(因为修改菜单后,层级可能变化了)
        updateSubMenuLevels(menu, resultMenu);
        resultMenu.setUpdateUser(ShiroKit.getUser().getId());
        resultMenu.setUpdateTime(new Date());
        this.updateById(resultMenu);
    }

    /**
     * 更新所有子菜单的结构
     *
     * @param oldMenu 原来的菜单
     * @param newMenu 新菜单
     * @author ...
     * @Date 2019/2/27 4:25 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSubMenuLevels(Menu oldMenu, Menu newMenu) {

        List<Menu> menus = menuMapper.getMenusLikePcodes(oldMenu.getCode());

        for (Menu menu : menus) {

            //更新pcode
            if (oldMenu.getCode().equals(menu.getPcode())) {
                menu.setPcode(newMenu.getCode());
            }

            //更新pcodes
            String oldPcodesPrefix = oldMenu.getPcodes() + "[" + oldMenu.getCode() + "],";
            String oldPcodesSuffix = menu.getPcodes().substring(oldPcodesPrefix.length());
            String menuPcodes = newMenu.getPcodes() + "[" + newMenu.getCode() + "]," + oldPcodesSuffix;
            menu.setPcodes(menuPcodes);

            //更新levels
            int level = StrUtil.count(menuPcodes, "[");
            menu.setLevels(level);
            menu.setUpdateUser(ShiroKit.getUser().getId());
            menu.setUpdateTime(new Date());

            this.updateById(menu);
        }

    }

    /**
     * 删除菜单
     *
     * @author ...
     * @Date 2017/5/5 22:20
     */
    @Transactional
    public void delMenu(Long menuId) {

        //删除菜单
        this.menuMapper.deleteById(menuId);

        //删除关联的relation
        this.menuMapper.deleteRelationByMenu(menuId);
    }

    /**
     * 删除菜单包含所有子菜单
     *
     * @author ...
     * @Date 2017/6/13 22:02
     */
    @Transactional
    public void delMenuContainSubMenus(Long menuId) {

        Menu menu = menuMapper.selectById(menuId);

        //删除当前菜单
        delMenu(menuId);

        //删除所有子菜单
        List<Menu> menus = menuMapper.getMenusLikePcodes(menu.getCode());

        for (Menu temp : menus) {
            delMenu(temp.getMenuId());
        }
    }

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<Map<String, Object>> selectMenus(Page page, String condition, String level, Long menuId) {
        //获取menuId的code
        String code = "";
        if (menuId != null && menuId != 0L) {
            Menu menu = this.selectById(menuId);
            code = menu.getCode();
        }
        return this.baseMapper.selectMenus(page, condition, level, menuId, code);
    }

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return this.baseMapper.getMenuIdsByRoleId(roleId);
    }

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    public List<ZTreeNode> menuTreeList() {
        return this.baseMapper.menuTreeList();
    }

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    public List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds) {
        return this.baseMapper.menuTreeListByMenuIds(menuIds);
    }

    /**
     * 删除menu关联的relation
     *
     * @param menuId
     * @return
     * @date 2017年2月19日 下午4:10:59
     */
    public int deleteRelationByMenu(Long menuId) {
        return this.baseMapper.deleteRelationByMenu(menuId);
    }

    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     * @date 2017年2月19日 下午7:12:38
     */
    public List<String> getResUrlsByRoleId(Long roleId) {
        return this.baseMapper.getResUrlsByRoleId(roleId);
    }

    /**
     * 根据角色获取菜单
     *
     * @param roleIds
     * @return
     * @date 2017年2月19日 下午10:35:40
     */
    public List<MenuNode> getMenusByRoleIds(List<Long> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return new ArrayList<>();
        } else {
            List<MenuNode> menus = this.baseMapper.getMenusByRoleIds(roleIds);
            //给所有的菜单url加上ctxPath
            for (MenuNode menuItem : menus) {
                menuItem.setUrl(ConfigListener.getConf().get("contextPath") + menuItem.getUrl());
            }
            List<MenuNode> titles = MenuNode.buildTitle(menus);
            return ApiMenuFilter.build(titles);
        }
    }

    /**
     * 根据code查询菜单
     *
     * @author ...
     * @Date 2018/12/20 21:54
     */
    public Menu selectByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);
        return this.baseMapper.selectOne(menu);
    }

    /**
     * 根据请求的父级菜单编号设置pcode和层级
     *
     * @author ...
     * @Date 2018/12/23 5:54 PM
     */
    public Menu menuSetPcode(MenuDto menuParam) {

        Menu resultMenu = new Menu();
        BeanUtil.copyProperties(menuParam, resultMenu);

        if (ToolUtil.isEmpty(menuParam.getPid()) || menuParam.getPid().equals(0L)) {
            resultMenu.setPcode("0");
            resultMenu.setPcodes("[0],");
            resultMenu.setLevels(1);
        } else {
            Long pid = menuParam.getPid();
            Menu pMenu = this.selectById(pid);
            Integer pLevels = pMenu.getLevels();
            resultMenu.setPcode(pMenu.getCode());

            //如果编号和父编号一致会导致无限递归
            if (menuParam.getCode().equals(menuParam.getPcode())) {
                throw new ServiceException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }

            resultMenu.setLevels(pLevels + 1);
            resultMenu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }

        return resultMenu;
    }

    /**
     * 获取菜单树形列表
     *
     * @author ...
     * @Date 2019/2/23 22:02
     */
    public List<Map<String, Object>> selectMenuTree(String condition, String level) {
        List<Map<String, Object>> maps = this.baseMapper.selectMenuTree(condition, level);

        if (maps == null) {
            maps = new ArrayList<>();
        }

        //创建根节点
        Menu menu = new Menu();
        menu.setMenuId(-1L);
        menu.setName("根节点");
        menu.setCode("0");
        menu.setPcode("-2");

        maps.add(BeanUtil.beanToMap(menu));

        return maps;
    }
}
