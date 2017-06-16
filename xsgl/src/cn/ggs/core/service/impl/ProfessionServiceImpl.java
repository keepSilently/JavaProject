package cn.ggs.core.service.impl;

import cn.ggs.core.dao.DepartmentDao;
import cn.ggs.core.dao.ProfessionDao;
import cn.ggs.core.entity.Profession;
import cn.ggs.core.service.ProfessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by silent on 2017-05-29/029.
 */
@Service("professionService")
public class ProfessionServiceImpl extends BaseServiceImpl<Profession> implements ProfessionService {
    private  ProfessionDao professionDao ;
    @Resource
    public void   setProfessionDao(ProfessionDao professionDao){
        super.setBaseDao(professionDao);
       this.professionDao=professionDao;
    }
}
