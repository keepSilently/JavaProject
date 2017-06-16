

import cn.ggs.core.entity.*;
import cn.ggs.core.service.ClassesService;
import cn.ggs.core.service.StudentService;
import cn.ggs.core.service.UserService;

import cn.ggs.core.utils.ExcelUtil;
import cn.ggs.core.utils.PageResult;
import cn.ggs.core.utils.QueryHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.io.File;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Created by silent on 2017-05-29/029.
 */
public class MyTest {
    private ClassPathXmlApplicationContext ca;
    @Before
    public void set(){

         ca = new ClassPathXmlApplicationContext("bean.xml");
    }
    @Test
    public void test(){

        UserService userService = (UserService) ca.getBean("userService");
        for(int i=0;i<100;i++){
            User user = new User();
            user.setImg("图片"+i);
            user.setName("名字"+i);
            user.setPassword("密码"+i);
            user.setPermission("权限"+i);
            userService.save(user);
        }


    }

    @Test
    public void myUser() {
        StudentService studentService = (StudentService) ca.getBean("studentService");
        QueryHelper queryHelper = new QueryHelper(Student.class, "s");
        queryHelper.addOrderBy("s.studentId",QueryHelper.ORDER_BY_DESC);
        PageResult pageResult = studentService.getPageResult(queryHelper, 1, 10);
        List <Student>items = pageResult.getItems();
    }

    @Test
    public void deleteTest() {
        StudentService studentService = (StudentService) ca.getBean("studentService");
        Student objectById = studentService.findObjectById(2);
        System.out.println(objectById);
        studentService.deleteById(2);
    }
 @Test
    public void deleteCaseTest() {
        StudentService studentService = (StudentService) ca.getBean("studentService");
        Student objectById = studentService.findObjectById("4028b8815c5d1891015c5d18986e0001");
//        objectById.setClasses(null);
//        studentService.updata(objectById);
     studentService.deleteByObject(objectById);


    }

    @Test
    public void studentTest() {
        StudentService studentService = (StudentService) ca.getBean("studentService");
        Student s=new Student();
        s.setBirthday(new Date());
        s.setName("我的测试jj呵呵");
        s.setDormitory("setDormitoryj15j");
        Classes classes = new Classes();
        classes.setClassesName("班级jj121");
        Department department = new Department();
        department.setName("我的专业jjj221");
        classes.setDepartment(department);
        Profession profession = new Profession();
        profession.setProfessionName("我的院系jjj121");
        department.setProfession(profession);
        s.setClasses(classes);

        studentService.save(s);
        List<Student> all = studentService.findAll();
        System.out.println(all);
    }
    @Test
    public void studentTest1() {
        StudentService studentService = (StudentService) ca.getBean("studentService");
        ClassesService ClassesService =(ClassesService)ca.getBean("classesService");
        Student s=new Student();
        s.setBirthday(new Date());
        s.setName("我的测试");
        s.setDormitory("setDormitory");

        s.setClasses(ClassesService.findObjectById(6));

        studentService.save(s);
        List<Student> all = studentService.findAll();

        for (Student ss:all){
            System.out.println(ss.getName());
        }
        System.out.println(all);
    }


    @Test
    public void classesStudentTest() {
        ClassesService ClassesService =(ClassesService)ca.getBean("classesService");
        Classes classes = new Classes();
        classes.setClassesName("classes级联hh42");
        Set<Student> studentSet = classes.getStudentSet();
        Student student = new Student();
        student.setName("classes级联学生ff252");
        student.setBirthday(new Date());
        student.setDormitory("classes级联学生setDormitory1415Eorror");
        studentSet.add(student);
        student.setClasses(classes);
        ClassesService.save(classes);

    }


   @Test
    public void classesTest() {
        System.out.println("进来after");
        ClassesService ClassesService =(ClassesService)ca.getBean("classesService");
        List<Classes> all = ClassesService.findAll();

        System.out.println(all);
    }


    @Test
    public void classessdeleteTest() {
        ClassesService c =(ClassesService)ca.getBean("classesService");

        Classes cla = c.findObjectById(6);
        System.out.println(cla.getClassesName());
        Set<Student> studentSet = cla.getStudentSet();
        for (Student i:studentSet){
            System.out.println(i.getName());
        }
       



    }


    @Test
    public void log() {
        Log log = LogFactory.getLog(getClass());

        for(int i=0;i<1000;i++){
            log.info("用户名称为： 的用户登录了系统。");
        }

        
        System.out.println();
    }

    @Test
    public void exlTest() throws  Exception{

//        StudentService studentService = (StudentService) ca.getBean("studentService");
//        FileOutputStream f=new FileOutputStream("学生.xls");
//
//        ExcelUtil.exportUserExcel(studentService.findAll(),f);
        
    }

    @Test
    public void readexcelTest() {
        StudentService s=(StudentService)ca.getBean("studentService");
        File f=new File("学生.xls");
        ExcelUtil.readStudent(f,"学生.xls",s);

    }


}
