package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.state.CommonStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.page.LayuiPageFactory;
import com.stylefeng.guns.core.page.LayuiPageInfo;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.DictTypeMapper;
import com.stylefeng.guns.modular.system.model.DictType;
import com.stylefeng.guns.modular.system.model.params.DictTypeParam;
import com.stylefeng.guns.modular.system.model.result.DictTypeResult;
import com.stylefeng.guns.modular.system.service.IDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {

    /**
     * 新增
     *
     * @author ...
     * @Date 2019-03-13
     */
    public void add(DictTypeParam param) {

        //判断是否已经存在同编码或同名称字典
        Wrapper<DictType> dictQueryWrapper = new EntityWrapper<>();
        //dictQueryWrapper.eq("code", param.getCode()).or().eq("name", param.getName());
        dictQueryWrapper.eq("code", param.getCode());
        List<DictType> list = this.selectList(dictQueryWrapper);
        if (list != null && list.size() > 0) {
            throw new ServiceException(BizExceptionEnum.DICT_EXISTED);
        }

        DictType entity = getEntity(param);

        //设置状态
        entity.setStatus(CommonStatus.ENABLE.getCode());
        entity.setCreateUser(ShiroKit.getUser().getId());
        entity.setCreateTime(new Date());
        this.insert(entity);
    }

    /**
     * 删除
     *
     * @author ...
     * @Date 2019-03-13
     */
    public void delete(DictTypeParam param) {
        this.deleteById(getKey(param));
    }

    /**
     * 更新
     *
     * @author ...
     * @Date 2019-03-13
     */
    public void update(DictTypeParam param) {
        DictType oldEntity = getOldEntity(param);
        DictType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);

        //判断编码是否重复
        Wrapper<DictType> wrapper = new EntityWrapper<>();
        //wrapper.eq("code", newEntity.getCode()).or().eq("name", newEntity.getName());
        wrapper.eq("code", newEntity.getCode());
        wrapper.ne("dict_type_id", newEntity.getDictTypeId());
        int dicts = this.selectCount(wrapper);
        if (dicts > 0) {
            throw new ServiceException(BizExceptionEnum.DICT_EXISTED);
        }
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
    public DictTypeResult findBySpec(DictTypeParam param) {
        return null;
    }

    /**
     * 查询列表，Specification模式
     *
     * @author ...
     * @Date 2019-03-13
     */
    public List<DictTypeResult> findListBySpec(DictTypeParam param) {
        return null;
    }

    /**
     * 查询分页数据，Specification模式
     *
     * @author ...
     * @Date 2019-03-13
     */
    public LayuiPageInfo findPageBySpec(DictTypeParam param) {
        Page pageContext = getPageContext();
        Wrapper<DictType> objectQueryWrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(param.getCondition())) {
            objectQueryWrapper.eq("code", param.getCondition()).or().eq("name", param.getCondition());
        }
        if (ToolUtil.isNotEmpty(param.getStatus())) {
            objectQueryWrapper.eq("status", param.getStatus());
        }
        if (ToolUtil.isNotEmpty(param.getSystemFlag())) {
            objectQueryWrapper.eq("system_flag", param.getSystemFlag());
        }
        Page page = this.selectPage(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DictTypeParam param) {
        return param.getDictTypeId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private DictType getOldEntity(DictTypeParam param) {
        return this.selectById(getKey(param));
    }

    private DictType getEntity(DictTypeParam param) {
        DictType entity = new DictType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }
}
