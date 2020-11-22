package com.hsk.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysWebsiteHotsearchEntity;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 16:54
 */
public interface ISysWebsiteHotSearchDao {
    /**
     * 获取热门搜索前6条
     * @return
     * @throws HSKDBException
     */
    List<SysWebsiteHotsearchEntity> getSysWebsiteHotSearchList() throws HSKDBException;

    /**
     * 保存热门搜索
     * @param searchName
     * @throws HSKDBException
     */
    void saveSysWebsiteHotSearch(String searchName) throws HSKDBException;
}
