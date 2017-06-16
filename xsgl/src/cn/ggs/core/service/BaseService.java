package cn.ggs.core.service;

import java.io.Serializable;
import java.util.List;

import cn.ggs.core.utils.PageResult;
import cn.ggs.core.utils.QueryHelper;





public interface BaseService<T> {
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
	void updata(T entity);
	//根据对象删除
	void deleteByObject(T entity);
	Long getTotalCount(QueryHelper queryHelper);
}
