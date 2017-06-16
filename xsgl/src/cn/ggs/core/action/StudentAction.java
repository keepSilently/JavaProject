package cn.ggs.core.action;

import cn.ggs.core.constant.Constant;
import cn.ggs.core.entity.Classes;
import cn.ggs.core.entity.Student;
import cn.ggs.core.service.ClassesService;
import cn.ggs.core.service.StudentService;
import cn.ggs.core.utils.ExcelUtil;
import cn.ggs.core.utils.PageResult;
import cn.ggs.core.utils.QueryHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by silent on 2017-05-29/029.
 */
@SuppressWarnings("unchecked")
public class StudentAction extends BaseAction {

    //从ioc注入studentService
    @Resource
    private StudentService studentService;
    @Resource
    private ClassesService classesService;
    private String selectedRow;
    private File headImg;
    private String headImgContentType;
    private String headImgFileName;
    private int status;
    private String message;
    private String keyword;//搜索关键字
    private Student student;
    private PageResult pageResult;
    private  File excel;
    private String excelFileName;




    //    {  json 返回码status设计  删除   0失败  1成功
////         “status”:1
//         “message”:””
//
//    }



    public  String checkSchoolId(){

        if(student!=null) {
            QueryHelper queryHelper = new QueryHelper(Student.class, "s");
            if(student.getStudentId()!=null){
                queryHelper.addWhere("s.studentId!=?",student.getStudentId());
            }

            queryHelper.addWhere("s.schoolId=?",student.getSchoolId());
            List<Student> objects = studentService.findObjects(queryHelper);
            if(objects!=null&&objects.size()>0){
                status=-1;
            }else {
                status=1;
            }

        }


        return "json";
    }


    public String add() {

        if (student != null) {
            if(student.getClasses()!=null&&student.getClasses().getClassesId()!=null){
                Classes objectById = classesService.findObjectById(student.getClasses().getClassesId());
                student.setClasses(objectById);
            }
            studentService.save(student);
        }
        status = 1;
        return "json";
    }

    public String update() {
        if (student != null) {
            if(student.getClasses()!=null&&student.getClasses().getClassesId()!=null){
                Classes objectById = classesService.findObjectById(student.getClasses().getClassesId());
                student.setClasses(objectById);
            }
            String img = student.getImg();
            if (img != null) {
                String[] split = img.split("/");
                student.setImg(split[split.length - 1]);
            }

            studentService.updata(student);
        }
        status = 1;
        return "json";
    }

    public String addImg() {

        if (headImg != null) {
            String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
            System.out.println(filePath);
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
            headImgFileName = fileName;
            status = 1;
            //复制文件
            try {
                FileUtils.copyFile(headImg, new File(filePath, fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "json";
    }


    public String list() {
        return "list";
    }

    public String jsonList() {
        QueryHelper queryHelper = new QueryHelper(Student.class, "s");

        System.out.println(keyword);
        if (!StringUtils.isNoneBlank(keyword)) {
            queryHelper.addOrderBy("s.studentId", QueryHelper.ORDER_BY_DESC);
            pageResult = studentService.getPageResult(queryHelper, pageNo, DEFAULT_PAGE_SIZE);
            setStudentImg(pageResult);

        } else {

            queryHelper.addOrWhere("s.name like ?", "%" + keyword + "%");
            queryHelper.addOrWhere("s.schoolId like ?", "%" + keyword + "%");
            queryHelper.addOrWhere("s.mobile like ?", "%" + keyword + "%");
            queryHelper.addOrWhere("s.dormitory like ?", "%" + keyword + "%");
            queryHelper.addOrWhere("s.dormitoryNum like ?", "%" + keyword + "%");
            pageResult = studentService.getPageResult(queryHelper, pageNo, DEFAULT_PAGE_SIZE);
            setStudentImg(pageResult);

        }


        return "json";
    }

    public String findStudent() {
        if (keyword != null) {
            student = studentService.findObjectById(keyword);
            if (student.getImg() != null && student.getImg().indexOf(".") > -1) {

                student.setImg("upload/user/" + student.getImg());
            } else {
                student.setImg(Constant.HEAD_IMG);
            }


            status = 1;
        }

        return "findStudent";
    }


    //删除从ajax传过来的学生
    public String delete() {

        if (selectedRow != null) {
            try {
                studentService.deleteById(selectedRow);
                status = 1;
                message = "删除ID为" + selectedRow + "学生成功";
            } catch (Exception e) {
                e.printStackTrace();
                status = 0;
                message = "删除ID为" + selectedRow + "学生失败";
            }
        }

        return "json";
    }

    public void exportExcel() {

        try {
            String[] parameterValues = ServletActionContext.getRequest().getParameterValues("selectedRows");
            String temp = ServletActionContext.getRequest().getParameter("temp");
            //1、查找用户列表
            //2、导出
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("application/x-execl");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String("学生列表.xls".getBytes(), "ISO-8859-1"));
                ServletOutputStream outputStream = response.getOutputStream();
                if(temp!=null){
                    studentService.exportExcel(null, outputStream);
                }else{
                    if(parameterValues==null){
                        List<Student> all = studentService.findAll();
                        ExcelUtil.exportStudentExcel(all,outputStream);
                    }else {
                        studentService.exportExcel(parameterValues, outputStream);
                    }

                }

                if (outputStream != null) {
                    outputStream.close();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setStudentImg(PageResult pageResult) {
        if (pageResult != null && pageResult.getItems() != null && pageResult.getItems().size() > 0) {
            for (Object temp : pageResult.getItems()) {
                Student s = (Student) temp;

                if (s.getImg() != null && s.getImg().indexOf(".") > -1) {
                    s.setImg("upload/user/" + s.getImg());
                } else {
                    s.setImg(Constant.HEAD_IMG);
                }

            }

        }
    }

    public String importExcel() {
        try{
            if(excel!=null){


            ExcelUtil.readStudent(excel,excelFileName,studentService);
            status=1;
            message="导入成功";
            }else {
                message="没有上传excel文件";
            }
        }catch (Exception e){
            status=0;
            message="导入失败--->"+e.getMessage();
        }

        return "json";
    }


    public void setExcel(File excel) {
        this.excel = excel;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }


    public File getHeadImg() {
        return headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeyword() {
        return keyword;
    }

    public Student getStudent() {
        return student;
    }


    public PageResult getPageResult() {
        return pageResult;
    }


    public void setSelectedRow(String selectedRow) {
        this.selectedRow = selectedRow;
    }
}
