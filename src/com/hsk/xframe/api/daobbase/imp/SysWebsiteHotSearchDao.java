package com.hsk.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.ISysWebsiteHotSearchDao;
import com.hsk.xframe.api.persistence.SysWebsiteHotsearchEntity;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 16:55
 */
@Repository
public class SysWebsiteHotSearchDao extends SupperDao implements ISysWebsiteHotSearchDao {
    @Override
    public List<SysWebsiteHotsearchEntity> getSysWebsiteHotSearchList() throws HSKDBException {
        String sql = "from SysWebsiteHotsearchEntity order by hotCount desc";
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        hibernateTemplate.setMaxResults(6);
        List<SysWebsiteHotsearchEntity> list = hibernateTemplate.find(sql);
        hibernateTemplate.setMaxResults(0);
        return list;
    }

    @Override
    public void saveSysWebsiteHotSearch(String searchName) throws HSKDBException {
        String sql = "from SysWebsiteHotsearchEntity where hotTitle = '" + searchName + "'";
        List<SysWebsiteHotsearchEntity> list = this.getHibernateTemplate().find(sql);
        if(list.size() > 0){
            SysWebsiteHotsearchEntity entity = list.get(0);
            entity.setHotCount(entity.getHotCount() + 1);
            this.getHibernateDao().update(entity);
        } else {
            SysWebsiteHotsearchEntity entity = new SysWebsiteHotsearchEntity();
            entity.setHotCount(1);
            entity.setHotTitle(searchName);
            this.getHibernateDao().save(entity);
        }
    }
}
