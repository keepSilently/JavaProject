package cn.ggs.core.service;

import cn.ggs.core.entity.User;

import javax.servlet.ServletOutputStream;

/**
 * Created by silent on 2017-05-29/029.
 */
public interface UserService extends BaseService<User> {
    void exportExcel(String id[],ServletOutputStream outputStream);
    void setpermission(User user);
}
