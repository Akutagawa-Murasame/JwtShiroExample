package org.mura.ssrjwt.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Akutagawa Murasame
 * @date 2022/3/23 13:03
 * principal一般是用户名
 * credentials一般是密码
 * 这里都返回密钥，因为密钥中包括了用户信息
 */
public class JWTToken implements AuthenticationToken {
    /**
     * 密钥
     */
    private final String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
