package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 Mapper 接口
 * </p>
 *
 * @author ...
 * @since 2017-07-11
 */
public interface NoticeMapper extends BaseMapper<Notice> {


    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition);


}