package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.stylefeng.guns.core.exception.CoreExceptionEnum;
import com.stylefeng.guns.core.exception.ServiceException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.system.dao.FileInfoMapper;
import com.stylefeng.guns.modular.system.model.FileInfo;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文件信息表
 * 服务实现类
 * </p>
 *
 * @author ...
 * @since 2018-12-07
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> {

    @Autowired
    private IUserService userService;

    /**
     * 上传头像
     *
     * @author ...
     * @Date 2018/11/10 4:10 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadAvatar(String avatar) {
        ShiroUser currentUser = ShiroKit.getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        User user = userService.selectById(currentUser.getId());

        //保存文件信息
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(String.valueOf(IdWorker.getId()));
        fileInfo.setFileData(avatar);
        this.insert(fileInfo);

        //更新用户的头像
        user.setAvatar(fileInfo.getFileId());
        userService.updateById(user);
    }
}
