package com.hsk.xframe.web.webSite.service;

import com.hsk.exception.HSKException;
import com.hsk.xframe.api.persistence.SysWebsiteHotsearchEntity;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 16:48
 */
public interface ISysWebsiteHotSearchService {
    /**
     * 获取热门搜索前6条
     * @return
     * @throws HSKException
     */
    List<SysWebsiteHotsearchEntity> getSysWebsiteHotSearchList() throws HSKException;

    /**
     * 保存热门搜索
     * @param searchName
     * @throws HSKException
     */
    void saveSysWebsiteHotSearch(String searchName) throws HSKException;
}
