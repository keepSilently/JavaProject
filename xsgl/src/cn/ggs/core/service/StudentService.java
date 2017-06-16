package cn.ggs.core.service;

import cn.ggs.core.entity.Student;

import javax.servlet.ServletOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by silent on 2017-05-29/029.
 */
public interface StudentService extends BaseService<Student> {

    void exportExcel(String id[],ServletOutputStream outputStream);
}
