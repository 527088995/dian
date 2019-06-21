package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.TreeviewNode;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门服务
 *
 * @author ...
 * @date 2017-04-27 17:00
 */
public interface IDeptService extends IService<Dept> {

    void addDept(Dept dept);
    void editDept(Dept dept);
    void deleteDept(Long deptId);

    List<Map<String, Object>> list(Page page, String condition, Long deptId);

    List<ZTreeNode> tree();

    List<TreeviewNode> treeviewNodes();
}
