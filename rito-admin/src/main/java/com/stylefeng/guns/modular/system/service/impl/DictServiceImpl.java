package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.CommonStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.RequestEmptyException;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.DictMapper;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.params.DictParam;
import com.stylefeng.guns.modular.system.model.result.DictResult;
import com.stylefeng.guns.modular.system.service.IDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    /**
     * 新增
     *
     * @author ...
     * @Date 2019-03-13
     */
    public void add(DictParam param) {

        //判断是否已经存在同编码或同名称字典
        Wrapper<Dict> dictQueryWrapper = new EntityWrapper<>();
        //dictQueryWrapper.eq("code", param.getCode()).or().eq("name", param.getName());
        dictQueryWrapper.eq("code", param.getCode());
        dictQueryWrapper.eq("dict_type_id", param.getDictTypeId());
        List<Dict> list = this.selectList(dictQueryWrapper);
        if (list != null && list.size() > 0) {
            throw new ServiceException(BizExceptionEnum.DICT_EXISTED);
        }

        Dict entity = getEntity(param);

        //设置pids
        dictSetPids(entity);

        //设置状态
        entity.setStatus(CommonStatus.ENABLE.getCode());
        entity.setCreateUser(ShiroKit.getUser().getId());
        entity.setCreateTime(new Date());
        this.insert(entity);
    }

    @Override
    public List<Dict> selectByParentCode(String code) {
        return null;
    }

    /**
     * 删除
     *
     * @author ...
     * @Date 2019-03-13
     */
    public void delete(DictParam param) {

        //删除字典的所有子级
        List<Long> subIds = getSubIds(param.getDictId());
        if (subIds.size() > 0) {
            this.deleteBatchIds(subIds);
        }

        this.deleteById(getKey(param));
    }

    /**
     * 更新
     *
     * @author ...
     * @Date 2019-03-13
     */
    public void update(DictParam param) {
        Dict oldEntity = this.selectById(getKey(param));
        Dict newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);

        //判断编码是否重复
        Wrapper<Dict> wrapper = new EntityWrapper<>();
        //wrapper.eq("code", newEntity.getCode()).or().eq("name", newEntity.getName());
        wrapper.eq("code", newEntity.getCode());
        wrapper.ne("dict_id", newEntity.getDictId());
        int dicts = this.selectCount(wrapper);
        if (dicts > 0) {
            throw new ServiceException(BizExceptionEnum.DICT_EXISTED);
        }

        //设置pids
        dictSetPids(newEntity);
        newEntity.setUpdateUser(ShiroKit.getUser().getId());
        newEntity.setUpdateTime(new Date());
        this.updateById(newEntity);
    }

    /**
     * 查询单条数据，Specification模式
     *
     * @author ...
     * @Date 2019-03-13
     */
    public DictResult findBySpec(DictParam param) {
        return null;
    }

    /**
     * 查询列表，Specification模式
     *
     * @author ...
     * @Date 2019-03-13
     */
    public List<DictResult> findListBySpec(DictParam param) {
        return null;
    }

    /**
     * 查询分页数据，Specification模式
     *
     * @author ...
     * @Date 2019-03-13
     */
    public LayuiPageInfo findPageBySpec(DictParam param) {
        Wrapper<Dict> objectQueryWrapper = new EntityWrapper<>();
        objectQueryWrapper.eq("dict_type_id", param.getDictTypeId());

        if (ToolUtil.isNotEmpty(param.getCondition())) {
            objectQueryWrapper.eq("code", param.getCondition()).or().eq("name", param.getCondition());
        }

        List<Dict> list = this.selectList(objectQueryWrapper);

        //创建根节点
        Dict dict = new Dict();
        dict.setName("根节点");
        dict.setDictId(0L);
        dict.setParentId(-999L);
        list.add(dict);

        LayuiPageInfo result = new LayuiPageInfo();
        result.setData(list);

        return result;
    }

    /**
     * 获取字典的树形列表（ztree结构）
     *
     * @author ...
     * @Date 2019/3/14 3:40 PM
     */
    public List<ZTreeNode> dictTreeList(Long dictTypeId, Long dictId) {
        if (dictTypeId == null) {
            throw new RequestEmptyException();
        }

        List<ZTreeNode> tree = this.baseMapper.dictTree(dictTypeId);

        //获取dict的所有子节点
        List<Long> subIds = getSubIds(dictId);

        //如果传了dictId，则在返回结果里去掉
        List<ZTreeNode> resultTree = new ArrayList<>();
        for (ZTreeNode zTreeNode : tree) {

            //如果dictId等于树节点的某个id则去除
            if (ToolUtil.isNotEmpty(dictId) && dictId.equals(zTreeNode.getId())) {
                continue;
            }
            if (subIds.contains(zTreeNode.getId())) {
                continue;
            }
            resultTree.add(zTreeNode);
        }

        resultTree.add(ZTreeNode.createParent());

        return resultTree;
    }

    /**
     * 查看dict的详情
     *
     * @author ...
     * @Date 2019/3/14 5:22 PM
     */
    public DictResult dictDetail(Long dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            throw new RequestEmptyException();
        }

        DictResult dictResult = new DictResult();

        //查询字典
        Dict detail = this.selectById(dictId);
        if (detail == null) {
            throw new RequestEmptyException();
        }

        //查询父级字典
        if (ToolUtil.isNotEmpty(detail.getParentId())) {
            Long parentId = detail.getParentId();
            Dict dictType = this.selectById(parentId);
            if (dictType != null) {
                dictResult.setParentName(dictType.getName());
            } else {
                dictResult.setParentName("无父级");
            }
        }

        ToolUtil.copyProperties(detail, dictResult);

        return dictResult;
    }

    private Serializable getKey(DictParam param) {
        return param.getDictId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Dict getOldEntity(DictParam param) {
        return this.selectById(getKey(param));
    }

    private Dict getEntity(DictParam param) {
        Dict entity = new Dict();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    private List<Long> getSubIds(Long dictId) {

        ArrayList<Long> longs = new ArrayList<>();

        if (ToolUtil.isEmpty(dictId)) {
            return longs;
        } else {
            List<Dict> list = this.baseMapper.likeParentIds(dictId);
            for (Dict dict : list) {
                longs.add(dict.getDictId());
            }
            return longs;
        }
    }

    private void dictSetPids(Dict param) {
        if (param.getParentId().equals(0L)) {
            param.setParentIds("[0]");
        } else {
            //获取父级的pids
            Long parentId = param.getParentId();
            Dict parent = this.selectById(parentId);
            if (parent == null) {
                param.setParentIds("[0]");
            } else {
                param.setParentIds(parent.getParentIds() + "," + "[" + parentId + "]");
            }
        }
    }
}
