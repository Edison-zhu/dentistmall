package com.hsk.xframe.web.webSite.service.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.xframe.api.daobbase.ISysWebsiteHotSearchDao;
import com.hsk.xframe.api.persistence.SysWebsiteHotsearchEntity;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.webSite.service.ISysWebsiteHotSearchService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 17:03
 */
@Service
public class SysWebsiteHotSearchService extends DSTService implements ISysWebsiteHotSearchService {
    @Autowired
    ISysWebsiteHotSearchDao _websiteHotSearchDao;

    @Override
    public List<SysWebsiteHotsearchEntity> getSysWebsiteHotSearchList() throws HSKException {
        List<SysWebsiteHotsearchEntity> list = null;
        try {
            list = _websiteHotSearchDao.getSysWebsiteHotSearchList();
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveSysWebsiteHotSearch(String searchName) throws HSKException {

    }
}
