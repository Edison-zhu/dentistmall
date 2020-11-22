package com.hsk.miniapi.xframe.web.webSite.service.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.miniapi.xframe.api.daobbase.ISysWebsiteHotSearchApiDao;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSwitchEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.persistence.SysWebsiteHotsearchEntity;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.miniapi.xframe.web.webSite.service.ISysWebsiteHotSearchApiService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * author: yangfeng
 * time: 2019/12/2 17:03
 */
@Service
public class SysWebsiteHotSearchApiService extends DSTApiService implements ISysWebsiteHotSearchApiService {
    @Autowired
    ISysWebsiteHotSearchApiDao _websiteHotSearchDao;

    @Override
    public List<SysWebsiteHotsearchEntity> getSysWebsiteHotSearchList() throws HSKException {
        List<SysWebsiteHotsearchEntity> list = null;
        try {
            SysUserInfo sysUserInfo = this.getApiSessionAccount();
            SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
            if (sysUserInfo == null && sysSwitchEntity != null && Objects.equals(sysSwitchEntity.getState(), 1)) {
                list = _websiteHotSearchDao.getSysWebsiteHotSearchListNoLogin();
            } else {
                list = _websiteHotSearchDao.getSysWebsiteHotSearchList();
            }
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveSysWebsiteHotSearch(String searchName) throws HSKException {

    }
}
