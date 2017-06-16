package cn.ggs.core.dao;


import cn.ggs.core.entity.Student;
import cn.ggs.core.utils.PageResult;
import cn.ggs.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by silent on 2017-05-29/029.
 */
public interface BaseDao<T>  {
    //保存一个
    void save(T entity);
    //根据id查询
    T findObjectById(Serializable id);
    //根据id删除
    void deleteById(Serializable id);
    //查询所有
    List<T> findAll();
    //条件查询实体列表--查询助手queryHelper
    List<T> findObjects(QueryHelper queryHelper);
    //分页条件查询实体列表--查询助手queryHelper
    PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
    //更新
    void update(T entity);
    //删除对象
    void dedleteByObject(T entity);
    //获取总记录数
    Long getTotalCount(QueryHelper queryHelper);

}
