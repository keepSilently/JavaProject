package cn.ggs.core.service.impl;

import cn.ggs.core.dao.ClassesDao;
import cn.ggs.core.dao.DepartmentDao;
import cn.ggs.core.entity.Department;
import cn.ggs.core.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by silent on 2017-05-29/029.
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department>  implements DepartmentService  {
    private DepartmentDao departmentDao ;
    @Resource
    public void   setDepartmentDao(DepartmentDao departmentDao){
        super.setBaseDao(departmentDao);
        this.departmentDao=departmentDao;
    }
}
