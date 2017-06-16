package cn.ggs.core.entity;

import java.io.Serializable;

/**
 * Created by silent on 2017-05-28/028.
 */
public class User implements Serializable {
    private String name;
    private String img;
    private String password;
    private String permission;
    private String userId;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (img != null ? !img.equals(user.img) : user.img != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (permission != null ? !permission.equals(user.permission) : user.permission != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }


}
