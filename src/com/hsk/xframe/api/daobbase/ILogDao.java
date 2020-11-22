package com.hsk.xframe.api.daobbase;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysUserInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ILogDao {
    public Integer saveMdMaterielPartDetailLog(MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity) throws HSKDBException;

    /**
     * 保存商品模型操作日志
     * @param
     * @return
     * @throws HSKDBException
     */
    Integer saveMdModelMaterielOperateLog(SysUserInfo account, Integer operateName, Integer wmsModelId) throws HSKDBException;
}
