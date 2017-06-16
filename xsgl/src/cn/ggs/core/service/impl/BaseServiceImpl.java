package cn.ggs.core.service.impl;

import cn.ggs.core.dao.BaseDao;
import cn.ggs.core.service.BaseService;
import cn.ggs.core.utils.PageResult;
import cn.ggs.core.utils.QueryHelper;

import java.io.Serializable;
import java.util.List;


public    class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}


	public void save(T entity) {
    	baseDao.save(entity);

    }

	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	public void deleteById(Serializable id) {
		baseDao.deleteById(id);
	}

	public List<T> findAll() {
		return baseDao.findAll();
	}

	public List<T> findObjects(QueryHelper queryHelper) {
		return baseDao.findObjects(queryHelper);
	}

	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		return baseDao.getPageResult(queryHelper,pageNo,pageSize);
	}

    public void updata(T entity) {
        baseDao.update(entity);
    }

	public void deleteByObject(T entity) {
		baseDao.dedleteByObject(entity);
	}

	public Long getTotalCount(QueryHelper queryHelper) {
		return baseDao.getTotalCount(queryHelper);
	}
}
