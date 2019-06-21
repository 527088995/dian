package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.node.TreeviewNode;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.DeptMapper;
import com.stylefeng.guns.modular.system.model.Dept;
import com.stylefeng.guns.modular.system.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {


    @Resource
    private DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @author ...
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getSimpleName(), dept.getFullName(), dept.getPid())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);

        this.insert(dept);
    }

    /**
     * 修改部门
     *
     * @author ...
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getDeptId(), dept.getSimpleName(), dept.getFullName(), dept.getPid(), dept.getDescription())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);

        this.updateById(dept);
    }

    /**
     * 删除部门
     *
     * @author ...
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteDept(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);

        //根据like查询删除所有级联的部门
        List<Dept> subDepts = deptMapper.likePids(dept.getDeptId());

        for (Dept temp : subDepts) {
            this.deleteById(temp.getDeptId());
        }

        this.deleteById(dept.getDeptId());
    }

    /**
     * 获取ztree的节点列表
     *
     * @author ...
     * @Date 2018/12/23 5:16 PM
     */
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    /**
     * 获取ztree的节点列表
     *
     * @author ...
     * @Date 2018/12/23 5:16 PM
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    /**
     * 获取所有部门列表
     *
     * @author ...
     * @Date 2018/12/23 5:16 PM
     */
    public List<Map<String, Object>> list(Page page,String condition, Long deptId) {
        return this.baseMapper.list(page, condition, deptId);
    }

    /**
     * 设置部门的父级ids
     *
     * @author ...
     * @Date 2018/12/23 4:58 PM
     */
    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0L)) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = this.selectById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}
