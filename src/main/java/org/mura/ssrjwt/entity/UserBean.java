package org.mura.ssrjwt.entity;

/**
 * @author Akutagawa Murasame
 * @date 2022/3/22 21:39
 */
public class UserBean {
    private String username;

    private String password;

    private String role;

    private String permission;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
