package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.NoticeMapper;
import com.stylefeng.guns.modular.system.model.Notice;
import com.stylefeng.guns.modular.system.service.INoticeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author ...123
 * @since 2018-02-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public List<Map<String, Object>> list(Page<Notice> page, String condition) {
        List<Map<String, Object>> list = this.baseMapper.list(page, condition);
        return list;
    }
}
