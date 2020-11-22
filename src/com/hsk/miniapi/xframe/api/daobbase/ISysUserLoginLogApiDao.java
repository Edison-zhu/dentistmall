package com.hsk.miniapi.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysUserLoginLogEntity;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/2 16:54
 */
public interface ISysUserLoginLogApiDao {
    /**
     * 获取热门搜索前6条
     * @return
     * @throws HSKDBException
     */
    List<SysUserLoginLogEntity> getSysUserLoginLogList() throws HSKDBException;

    /**
     * 保存热门搜索
     * @param searchName
     * @throws HSKDBException
     */
    void addSysUserLoginLogAnotherTrans(Integer suiId, Integer loginType, String ip) throws HSKDBException;

    /**
     * 保存热门搜索
     * @param searchName
     * @throws HSKDBException
     */
    void addSysUserLoginLog(Integer suiId, Integer loginType, String ip) throws HSKDBException;
}
