package com.stylefeng.guns.core.shiro.jwt;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt令牌
 *
 * @author fengshuonan
 * @Date 2019/4/21 10:59
 */
@Data
public class JwtToken implements AuthenticationToken {

    private String jwt;
    private String host;

    public JwtToken(String jwt, String host) {
        this.jwt = jwt;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }
}