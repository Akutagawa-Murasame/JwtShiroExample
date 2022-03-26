package org.mura.ssrjwt.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mura.ssrjwt.entity.UserBean;
import org.mura.ssrjwt.service.UserService;
import org.mura.ssrjwt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Akutagawa Murasame
 * @date 2022/3/23 13:07
 */
@Component
public class MyRealm extends AuthorizingRealm {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 必须重写此方法，否则shiro会报错
     * @return 是否支持JWTToken类型
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        UserBean userBean = userService.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(userBean.getRole());
        Set<String> permission = new HashSet<>(Arrays.asList(userBean.getPermission().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);

        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
//        获得密钥，调用的是我们自定义的JWTToken中的方法
        String token = (String) auth.getCredentials();

//        解密获得username，用于和数据库对比
        String username = JWTUtil.getUsername(token);
        if (null == username) {
            throw new AuthenticationException("token invalid");
        }

        UserBean userBean = userService.getUser(username);
        if (null == userBean) {
            throw new AuthenticationException("User didn't exists");
        }

        if (!JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "myRealm");
    }
}
