package org.mura.ssrjwt.service;

import org.mura.ssrjwt.dao.DataSource;
import org.mura.ssrjwt.entity.UserBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Akutagawa Murasame
 * @date 2022/3/22 21:38
 */
@Component
public class UserService {

    public UserBean getUser(String username) {
        // 没有此用户直接返回null
        if (!DataSource.getData().containsKey(username)) {
            return null;
        }

        UserBean user = new UserBean();
        Map<String, String> detail = DataSource.getData().get(username);

        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermission(detail.get("permission"));
        return user;
    }
}