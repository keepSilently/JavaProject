package cn.ggs.core.service.impl;

import cn.ggs.core.dao.ClassesDao;
import cn.ggs.core.dao.UserDao;
import cn.ggs.core.entity.Classes;
import cn.ggs.core.service.ClassesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by silent on 2017-05-29/029.
 */
@Service("classesService")
public class ClassesServiceImpl extends BaseServiceImpl<Classes> implements ClassesService{
    private ClassesDao classesDao ;
    @Resource
    public void   setClassesDao(ClassesDao classesDao){
        super.setBaseDao(classesDao);
        this.classesDao=classesDao;
    }

}
