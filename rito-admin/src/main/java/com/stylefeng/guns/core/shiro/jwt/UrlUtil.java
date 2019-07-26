package com.stylefeng.guns.core.shiro.jwt;

import com.stylefeng.guns.core.util.SpringContextHolder;

/**
 * sso url工具
 *
 * @author fengshuonan
 * @Date 2019/4/21 16:02
 */
public class UrlUtil {

    public static String getSsoServerUrl() {

        //获取sso服务端的地址
        String ssoUrl = SpringContextHolder.getApplicationContext()
                .getEnvironment().getProperty("guns.sso-server-url");

        //获取当前应用的appId
        String appId = SpringContextHolder.getApplicationContext()
                .getEnvironment().getProperty("guns.app-id");

        //获取单点成功后本服务的url
        String ssoSuccessUrl = SpringContextHolder.getApplicationContext()
                .getEnvironment().getProperty("guns.sso-success-url");

        return ssoUrl + "?appId=" + appId + "&redirectUrl=" + ssoSuccessUrl;
    }

}
