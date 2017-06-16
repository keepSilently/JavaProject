package cn.ggs.core.service.impl;

import cn.ggs.core.dao.UserDao;
import cn.ggs.core.entity.User;
import cn.ggs.core.service.UserService;
import cn.ggs.core.utils.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silent on 2017-05-29/029.
 */
@Service("userService")
public class UserServiceImpl extends  BaseServiceImpl<User> implements UserService {
    private UserDao userDao;
    @Resource
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao =userDao;
    }

    public void setpermission(User user){
        User objectById = userDao.findObjectById(user.getUserId());
        if(objectById!=null){
            objectById.setPermission(user.getPermission());
            userDao.update(objectById);
        }

    }

    public void exportExcel(String  id[], ServletOutputStream outputStream) {
        List<User> list=new ArrayList<User>();

        if(id!=null){
            for (String i : id ){
                list.add(userDao.findObjectById(i));
            }
        }
        if(list.size()!=0){
            ExcelUtil.exportUserExcel( list, outputStream);
        }
        else {
            ExcelUtil.exportUserExcel( null, outputStream);
        }

    }
}
