package com.stylefeng.guns.core.shiro.jwt;

import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.shiro.factory.IShiro;
import com.stylefeng.guns.core.shiro.factory.ShiroFactroy;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基于JWT的认证域
 *
 * @author fengshuonan
 * @Date 2019/4/21 11:01
 */
public class JwtRealm extends AuthorizingRealm {

    public Class<?> getAuthenticationTokenClass() {
        return JwtToken.class;
    }

    /**
     * 认证
     *
     * @author fengshuonan
     * @Date 2019/4/21 11:01
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) jwtToken.getPrincipal();
        ShiroUser shiroUser;

        IShiro IShiro = ShiroFactroy.me();
        try {
            Claims claimFromToken = JwtTokenUtil.getClaimFromToken(jwt);
            String account = (String) claimFromToken.get("account");
            User user = IShiro.user(account);
            shiroUser = IShiro.shiroUser(user);
        } catch (JwtException e) {
            throw new AuthenticationException("JWT令牌无效:" + e.getMessage());
        }

        return new SimpleAuthenticationInfo(shiroUser, Boolean.TRUE, getName());
    }

    /**
     * 授权,JWT已包含访问主张只需要解析其中的主张定义就行了
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        IShiro shiroFactory = ShiroFactroy.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        for (Long roleId : roleList) {
            List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (ToolUtil.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }
}