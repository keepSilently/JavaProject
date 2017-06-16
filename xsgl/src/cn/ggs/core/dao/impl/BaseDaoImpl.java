package cn.ggs.core.dao.impl;

import cn.ggs.core.dao.BaseDao;
import cn.ggs.core.utils.PageResult;
import cn.ggs.core.utils.QueryHelper;
import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by silent on 2017-05-29/029.
 */
@SuppressWarnings("unchecked")
public  abstract   class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType pt = (ParameterizedType ) this.getClass().getGenericSuperclass();//BaseDaoImpl<User>
        clazz=( Class<T>)pt.getActualTypeArguments()[0];
    }

    public void save(T entity) {

        getHibernateTemplate().save(entity);


    }
    public T findObjectById(Serializable id) {

        return getHibernateTemplate().get(clazz, id);
    }

    public void deleteById(Serializable id) {
        getHibernateTemplate().delete(findObjectById(id));
    }

    public List<T> findAll() {

        Query query = getSessionFactory().getCurrentSession().createQuery("from " + clazz.getSimpleName());


        return  query.list();
    }

    public List<T> findObjects(QueryHelper queryHelper) {
        Query query = getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryListHql());
        List<Object> parameters = queryHelper.getParameters();
        if(parameters != null){
            for(int i = 0; i < parameters.size(); i++){
                query.setParameter(i, parameters.get(i));
            }
        }
        return query.list();


    }

    public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
        Query query = getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryListHql());
        List<Object> parameters = queryHelper.getParameters();
        if(parameters != null){
            for(int i = 0; i < parameters.size(); i++){
                query.setParameter(i, parameters.get(i));
            }
        }
        if(pageNo < 1) pageNo = 1;

        query.setFirstResult((pageNo-1)*pageSize);//设置数据起始索引号
        query.setMaxResults(pageSize);
        List items = query.list();
        //获取总记录数
        Query queryCount = getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryCountHql());
        if(parameters != null){
            for(int i = 0; i < parameters.size(); i++){
                queryCount.setParameter(i, parameters.get(i));
            }
        }
        long totalCount = (Long)queryCount.uniqueResult();

        return new PageResult(totalCount, pageNo, pageSize, items);
    }

    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    public void dedleteByObject(T entity) {
        getHibernateTemplate().delete(entity);
    }

    public Long getTotalCount(QueryHelper queryHelper) {
        Query queryCount = getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryCountHql());
        Long o =(Long) queryCount.uniqueResult();
        return o;
    }
}
