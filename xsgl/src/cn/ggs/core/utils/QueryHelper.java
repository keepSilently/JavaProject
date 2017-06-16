package cn.ggs.core.utils;

/**
 * Created by silent on 2017-05-29/029.
 */

import antlr.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * HQl查询助手
 */
public class QueryHelper {
    private String from= "";
    private String orderBy="";
    private String where="";
    private List<Object> parameters;
    //排序顺序
    public static String ORDER_BY_DESC = "DESC";//降序
    public static String ORDER_BY_ASC = "ASC";//升序

    /**
     *
     * @param clazz  要查询对象的字节码（例如 查询学生  ：new QueryHelper(Student.class,"stu")  ）
     * @param alias 别名
     */
    public QueryHelper (Class  clazz,String  alias){
        if(alias!=null&&!alias.equals("")){
            from="FROM " + clazz.getSimpleName() + " " + alias;
        }
        else {
            from="FROM " + clazz.getSimpleName() + " " ;
        }
    }

    public void addclass(Class  clazz,String  alias){
        from+=", "+clazz.getSimpleName()+" " + alias;
    }


    /**
     * 构造where子句
     * @param condition 查询条件语句；例如：i.title like ?
     * @param params 查询条件语句中?对应的查询条件值；例如： %标题%
     */



    public void addWhere(String condition, Object... params){
        if (where.length() > 1) {//非第一个查询条件
            where += " AND " + condition;
        } else {//第一个查询条件
            where += " WHERE " + condition;
        }

        //设置查询条件值到查询条件值集合中
        if(parameters == null){
            parameters = new ArrayList<Object>();
        }
        if(params != null){
            for(Object param: params){
                parameters.add(param);
            }
        }
    }

    public void addOrWhere(String condition, Object... params){
        if (where.length() > 1) {//非第一个查询条件
            where += " OR " + condition;
        } else {//第一个查询条件
            where += " WHERE " + condition;
        }

        //设置查询条件值到查询条件值集合中
        if(parameters == null){
            parameters = new ArrayList<Object>();
        }
        if(params != null){
            for(Object param: params){
                parameters.add(param);
            }
        }
    }


    /**
     * 构造order by子句
     * @param property 排序属性，如：i.createTime
     * @param order 排序顺序，如：DESC 或者 ASC
     */
    public void addOrderBy(String property, String order){
        if (orderBy.length() > 1) {//非第一个排序属性
            orderBy += "," + property + " " + order;
        } else {//第一个排序属性
            orderBy = " ORDER BY " + property + " " + order;
        }
    }

    //查询hql语句
    public String getQueryListHql(){
        return from + where + orderBy;
    }

    //查询统计数的hql语句
    public String getQueryCountHql(){
        return "SELECT COUNT(1) " + from + where;
    }

    //查询hql语句中?对应的查询条件值集合
    public List<Object> getParameters(){
        return parameters;
    }

}
