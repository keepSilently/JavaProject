package cn.ggs.core.action;

import cn.ggs.core.entity.Classes;
import cn.ggs.core.entity.Department;
import cn.ggs.core.entity.Profession;
import cn.ggs.core.service.ClassesService;
import cn.ggs.core.service.DepartmentService;
import cn.ggs.core.service.ProfessionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by silent on 2017-06-10/010.
 */
public class ClassAction extends BaseAction {
    @Resource
    private ClassesService classesService;
    @Resource
    private DepartmentService departmentService;//专业
    @Resource
    private ProfessionService professionService;//院系

    private Profession profession;
    private Department department;
    private Classes classes;
    private List<Profession> professionList;

    private String message;
    private int status;
    public String addProfession(){
        if(profession!=null){
            professionService.save(profession);
            status=1;
        }
        return "json";
    }
    public String findProfessions(){
        professionList = professionService.findAll();
        status=1;
        return "json";
    }

    public  String addDepartment(){
        System.out.println("-----------分割-------");
        if(department!=null&&department.getName()!=null&&department.getProfession()!=null&&department.getProfession().getProfessionId()!=null){
            Profession objectById = professionService.findObjectById(department.getProfession().getProfessionId());
           if(objectById!=null){
               department.setProfession(objectById);
               departmentService.save(department);
               status=1;
           }

        }
        return "json";
    }
    public  String addClass(){
        if(classes!=null&&classes.getDepartment()!=null&&classes.getDepartment().getDepartmentId()!=null){

            Department objectById = departmentService.findObjectById(classes.getDepartment().getDepartmentId());
            if(objectById!=null){
                classes.setDepartment(objectById);
                classesService.save(classes);
                status=1;
            }


        }
        return "json";
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<Profession> getProfessionList() {
        return professionList;
    }
}
