package cn.ggs.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silent on 2017-06-03/003.
 */
public class Constant {
    //系统中用户在session中的标识符
    public static String USER = "SYS_USER";

    //用户默认头像
    public static String HEAD_IMG = "img/tx.jpg";

    //权限
    public  static String PRIVILEGE_USER="user";
    public  static String PRIVILEGE_ADMIN="admin";
    public  static String PRIVILEGE_SUPER_ADMIN="superAdmin";


    /*----------------------系统权限集合--------------------------*/


    public static Map<String, String> PRIVILEGE_MAP;
    static {
        PRIVILEGE_MAP = new HashMap<String, String>();
        PRIVILEGE_MAP.put(PRIVILEGE_USER, "普通用户");
        PRIVILEGE_MAP.put(PRIVILEGE_ADMIN, "管理员");
        PRIVILEGE_MAP.put(PRIVILEGE_SUPER_ADMIN, "超级管理员");
    }

}
