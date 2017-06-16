package cn.ggs.core.action;

import cn.ggs.core.constant.Constant;
import cn.ggs.core.entity.Student;
import cn.ggs.core.entity.User;
import cn.ggs.core.service.UserService;
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
import java.util.List;
import java.util.UUID;

/**
 * Created by silent on 2017-05-29/029.
 */
public class UserAction extends BaseAction {
    @Resource
    private UserService userService;
    private User user;
    private String selectedRow;
    private File headImg;
    private String headImgContentType;
    private String headImgFileName;
    private int status;
    private String message;
    private String keyword;//搜索关键字
    private PageResult pageResult;
    private File excel;
    private String excelFileName;

    public String isUSer() {

        if (user != null) {
            QueryHelper queryHelper = new QueryHelper(User.class, "u");
            queryHelper.addWhere("u.name=?", user.getName());
            List<User> objects = userService.findObjects(queryHelper);
            System.out.println(objects);
            if (objects != null && objects.size() > 0) {
                status = 1;
            } else {
                status = -1;
            }
        } else {
            status = -1;
        }
        return "json";
    }


    //改变权限
    public String changePermission() {

        if (user.getUserId() != null && (Constant.PRIVILEGE_MAP.get(user.getPermission())) != null) {
            if (!user.getUserId().equals(Constant.PRIVILEGE_SUPER_ADMIN)) {
                userService.setpermission(user);
                status = 1;
            }
        }
        return "json";
    }

    //添加
    public String add() {
        if (user != null) {
            if (user.getPermission() == null) {
                user.setPermission(Constant.PRIVILEGE_USER);

            }
            userService.save(user);
        }
        status = 1;
        return "json";
    }

    //更新
    public String update() {

        if (user != null) {
            String img = user.getImg();
            if (img != null) {
                String[] split = img.split("/");
                user.setImg(split[split.length - 1]);
            }

            userService.updata(user);
        }
        status = 1;
        return "json";
    }

    //上传图片
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

    //跳转到列表页面
    public String list() {
        return "list";
    }

    //返回json 列表
    public String jsonList() {
        QueryHelper queryHelper = new QueryHelper(User.class, "u");
        User attribute = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
        if (attribute != null && attribute.getUserId() != null) {
            queryHelper.addWhere("u.id!=?", attribute.getUserId());
        }
        System.out.println(keyword);
        if (!StringUtils.isNoneBlank(keyword)) {
            queryHelper.addOrderBy("u.id", QueryHelper.ORDER_BY_DESC);
            pageResult = userService.getPageResult(queryHelper, pageNo, DEFAULT_PAGE_SIZE);
            setUserImg(pageResult);
        } else {
            queryHelper.addOrWhere("u.name like ?", "%" + keyword + "%");
            queryHelper.addOrWhere("u.password like ?", "%" + keyword + "%");
            pageResult = userService.getPageResult(queryHelper, pageNo, DEFAULT_PAGE_SIZE);
            setUserImg(pageResult);

        }


        return "json";
    }

    //c查找一个用户
    public String findUser() {
        if (keyword != null) {
            user = userService.findObjectById(keyword);
            if (user.getImg() != null && user.getImg().indexOf(".") > -1) {

                user.setImg("upload/user/" + user.getImg());
            } else {
                user.setImg(Constant.HEAD_IMG);
            }


            status = 1;
        }

        return "findUser";
    }

    //删除从ajax传过来的用户
    public String delete() {
        System.out.println("selectedRow===" + selectedRow);
        if (selectedRow != null) {
            try {
                userService.deleteById(selectedRow);
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

    //导出excel
    public void exportExcel() {

        try {
            String[] parameterValues = ServletActionContext.getRequest().getParameterValues("selectedRows");
            String temp = ServletActionContext.getRequest().getParameter("temp");
            //1、查找用户列表
            //2、导出
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/x-execl");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
            ServletOutputStream outputStream = response.getOutputStream();

            if (temp != null) {
                userService.exportExcel(null, outputStream);
            } else {
                if (parameterValues == null) {
                    ExcelUtil.exportUserExcel(userService.findAll(), outputStream);
                } else {
                    userService.exportExcel(parameterValues, outputStream);
                }
            }

            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //更新用户头像图片
    public void setUserImg(PageResult pageResult) {
        if (pageResult != null && pageResult.getItems() != null && pageResult.getItems().size() > 0) {
            for (Object temp : pageResult.getItems()) {
                User u = (User) temp;

                if (u.getImg() != null && u.getImg().indexOf(".") > -1) {
                    u.setImg("upload/user/" + u.getImg());
                } else {
                    u.setImg(Constant.HEAD_IMG);
                }

            }

        }
    }


    //导出Excel
    public String importExcel() {
        try {
            if (excel != null) {
                ExcelUtil.readUser(excel, excelFileName, userService);
                status = 1;
                message = "导入成功";
            } else {
                message = "没有上传excel文件";
            }

        } catch (Exception e) {
            status = 0;
            message = "导入失败--->" + e.getMessage();
        }

        return "json";
    }

    public String userInfo() {

        return "userInfo";
    }

    public String user() {
        User obj = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
        if (obj != null && obj.getUserId() != null) {
            user = userService.findObjectById(obj.getUserId());
            status = 1;
            if (user.getImg() != null && user.getImg().indexOf(".") > -1) {

                user.setImg("upload/user/" + user.getImg());
            } else {
                user.setImg(Constant.HEAD_IMG);
            }

        }


        return "json";
    }

    public String userUpdate() {

        User obj = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
        if (user != null && obj.getUserId().equals(user.getUserId())) {
            User objectById = userService.findObjectById(obj.getUserId());
            String img = user.getImg();
            if (img != null) {
                String[] split = img.split("/");
                objectById.setImg(split[split.length - 1]);
            }
            objectById.setPassword(user.getPassword());
        }

        return "";
    }
    public  String setSuperAdmin(){
        if(user!=null){
            user.setPermission(Constant.PRIVILEGE_SUPER_ADMIN);
            userService.save(user);
        }
        return SUCCESS;
    }
    public  String setSuperAdminUi(){
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String selectedRow) {
        this.selectedRow = selectedRow;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public void setExcel(File excel) {
        this.excel = excel;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
}
