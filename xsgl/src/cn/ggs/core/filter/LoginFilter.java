package cn.ggs.core.filter;

import cn.ggs.core.constant.Constant;
import cn.ggs.core.entity.User;
import cn.ggs.core.service.UserService;
import cn.ggs.core.utils.QueryHelper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by silent on 2017-06-09/009.
 * 登录过滤器与权限过滤器
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        if (uri != null && !uri.contains("login") && !uri.contains("user_isUser")) {


            //登录了
            if (request.getSession().getAttribute(Constant.USER) != null) {
                User user = (User) request.getSession().getAttribute(Constant.USER);
                //权限设置
                if (Constant.PRIVILEGE_SUPER_ADMIN.equals(user.getPermission())) {

                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (Constant.PRIVILEGE_ADMIN.equals(user.getPermission()) && !uri.contains("user")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (uri.contains("user_user") || uri.contains("home")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (Constant.PRIVILEGE_USER.equals(user.getPermission()) && (!uri.contains("student_delete") || !uri.contains("student_add") || !uri.contains("student_add"))) {
                    filterChain.doFilter(servletRequest, servletResponse);

                }else  if((Constant.PRIVILEGE_USER.equals(user.getPermission())||Constant.PRIVILEGE_ADMIN.equals(user.getPermission()))&&uri.contains("class_listTemplate")){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else if(uri.contains("page")||uri.contains("upload")){
                    filterChain.doFilter(servletRequest, servletResponse);
                }

            } else {
                if (uri.contains("user_setSuperAdmin")) {
                    WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                    UserService userService = (UserService) applicationContext.getBean("userService");
                    QueryHelper queryHelper = new QueryHelper(User.class, "u");
                    Long totalCount = userService.getTotalCount(queryHelper);
                    System.out.println(totalCount + "==" + (totalCount == 0));
                    if (totalCount == 0) {
                        System.out.println("进来totalCount");
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect(basePath + "login.action");
                    }
                } else {
                    response.sendRedirect(basePath + "login.action");
                }
            }
        } else {
            if (uri.contains("login")) {
                //  user_setSuperAdmin
                //获取spring 容器
                WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                UserService userService = (UserService) applicationContext.getBean("userService");
                QueryHelper queryHelper = new QueryHelper(User.class, "u");
                Long totalCount = userService.getTotalCount(queryHelper);
                System.out.println(totalCount + "==" + (totalCount == 0));
                if (totalCount == 0) {
                    System.out.println("进来totalCount");
                    response.sendRedirect(basePath + "user_setSuperAdminUi.action");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        }


    }

    public void destroy() {

    }
}
