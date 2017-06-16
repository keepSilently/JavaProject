package cn.ggs.login.action;

import cn.ggs.core.constant.Constant;
import cn.ggs.core.entity.User;
import cn.ggs.core.service.UserService;
import cn.ggs.core.utils.QueryHelper;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by silent on 2017-06-09/009.
 */
public class LoginAction extends ActionSupport{
    private User user;
    private  String headImg;
    @Resource
    private UserService userService;
    private String loginMessage;


    public String login(){
        System.out.println("login user==="+user);
        if(user!=null){
            QueryHelper queryHelper=new QueryHelper(User.class,"u");
            queryHelper.addWhere("u.name=?",user.getName());
            queryHelper.addWhere("u.password=?",user.getPassword());
            List<User> objects = userService.findObjects(queryHelper);
            if(objects!=null&&objects.size()>0){
                headImg=(objects.get(0).getImg()==null)?Constant.HEAD_IMG:"upload/user/"+objects.get(0).getImg();
                objects.get(0).setImg(headImg);
                ServletActionContext.getRequest().getSession().setAttribute(Constant.USER,objects.get(0));
                return SUCCESS;
            }else {
                loginMessage="用户名或者密码错误";
                return ERROR;
            }

        }else {
            return  ERROR;
        }



    }
    public String logout(){
        ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
        return "login";
    }
    public  String pageUi(){
        return "page";
    }public  String uploadUi(){
        return "upload";
    }
    public  String home(){
        return "home";
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}
