package cn.ggs.core.service.impl;

import cn.ggs.core.dao.StudentDao;
import cn.ggs.core.entity.Student;
import cn.ggs.core.service.StudentService;
import cn.ggs.core.utils.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silent on 2017-05-29/029.
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    private StudentDao studentDao;
    @Resource
    public void setStudentDao(StudentDao studentDao) {
        super.setBaseDao(studentDao);
        this.studentDao = studentDao;
    }


    public void exportExcel(String  id[], ServletOutputStream outputStream) {
        List <Student> list=new ArrayList<Student>();

        if(id!=null){
            for (String i : id ){
                list.add(studentDao.findObjectById(i));
            }
        }
        if(list.size()!=0){
            ExcelUtil.exportStudentExcel( list, outputStream);
        }
        else {
            ExcelUtil.exportStudentExcel( null, outputStream);
        }

    }
}
