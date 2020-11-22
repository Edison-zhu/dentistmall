package com.hsk.miniapi.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserLoginLogApiDao;
import com.hsk.xframe.api.persistence.SysUserLoginLogEntity;
import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 16:55
 */
@Repository
public class SysUserLoginLogApiDao extends SupperDao implements ISysUserLoginLogApiDao {

    @Override
    public List<SysUserLoginLogEntity> getSysUserLoginLogList() throws HSKDBException {
        String sql = "from SysUserLoginLogEntity order by loginDate desc";
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
//        hibernateTemplate.setMaxResults(6);
        return hibernateTemplate.find(sql);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void addSysUserLoginLogAnotherTrans(Integer suiId, Integer loginType,String ip) throws HSKDBException {
        saveSysUerLoginLog(suiId, loginType, ip);
    }

    @Override
    public void addSysUserLoginLog(Integer suiId, Integer loginType,String ip) throws HSKDBException {
        saveSysUerLoginLog(suiId, loginType, ip);
    }

    private void saveSysUerLoginLog(Integer suiId, Integer loginType,String ip) {
        SysUserLoginLogEntity entity = new SysUserLoginLogEntity();
        entity.setSuiId(suiId);
        entity.setLoginDate(new Date());
        entity.setLoginType(loginType);
        entity.setIp(ip);
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
        hibernateTemplate.save(entity);
    }
}
