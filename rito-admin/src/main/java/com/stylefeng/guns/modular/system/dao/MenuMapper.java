package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.node.MenuNode;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectMenus(@Param("page") Page page, @Param("condition") String condition, @Param("level") String level, @Param("menuId") Long menuId, @Param("code") String code);

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Long> getMenuIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> menuTreeList();

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds);

    /**
     * 删除menu关联的relation
     *
     * @param menuId
     * @return
     * @date 2017年2月19日 下午4:10:59
     */
    int deleteRelationByMenu(@Param("menuId") Long menuId);

    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     * @date 2017年2月19日 下午7:12:38
     */
    List<String> getResUrlsByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色获取菜单
     *
     * @param roleIds
     * @return
     * @date 2017年2月19日 下午10:35:40
     */
    List<MenuNode> getMenusByRoleIds(List<Long> roleIds);

    /**
     * 查询菜单树形列表
     *
     * @author ...
     * @Date 2019/2/23 22:03
     */
    List<Map<String, Object>> selectMenuTree(@Param("condition") String condition, @Param("level") String level);

    /**
     * 获取pcodes like某个code的菜单列表
     *
     * @author ...
     * @Date 2019/3/31 15:51
     */
    List<Menu> getMenusLikePcodes(@Param("code") String code);
}