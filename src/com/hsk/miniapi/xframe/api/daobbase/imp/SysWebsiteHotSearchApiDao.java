package com.hsk.miniapi.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysWebsiteHotSearchApiDao;
import com.hsk.xframe.api.persistence.SysWebsiteHotsearchEntity;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 16:55
 */
@Repository
public class SysWebsiteHotSearchApiDao extends SupperDao implements ISysWebsiteHotSearchApiDao {
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

    @Override
    public List<SysWebsiteHotsearchEntity> getSysWebsiteHotSearchListNoLogin() throws HSKDBException {
        String sql = "from SysWebsiteHotsearchTempEntity order by hotCount desc";
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        hibernateTemplate.setMaxResults(6);
        List<SysWebsiteHotsearchEntity> list = hibernateTemplate.find(sql);
        hibernateTemplate.setMaxResults(0);
        return list;
    }

    @Override
    public void saveSysWebsiteHotSearchNoLogin(String searchName) throws HSKDBException {
        String sql = "from SysWebsiteHotsearchTempEntity where hotTitle = '" + searchName + "'";
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
