package com.hsk.xframe.api.daobbase.imp;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.IExpoetExcelDao;
import com.hsk.xframe.api.daobbase.ILogDao;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class LogDao extends SupperDao implements ILogDao {
    //保存商品操作日志
    public Integer saveMdMaterielPartDetailLog(MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity) throws HSKDBException{
        return this.newObject(mdMaterielPartDetailLogEntity);
    }

    @Override
    public Integer saveMdModelMaterielOperateLog(SysUserInfo account, Integer operateName, Integer wmsModelId) throws HSKDBException {
        String on = "";
        switch (operateName) {
            case 1:
                on = "新增商品模型";
                break;
            case 2:
                on = "上传图片";
                break;
            case 3:
                on = "禁用商品";
                break;
            case 4:
                on = "启用商品";
                break;
            case 5:
                on = "修改商品名称";
                break;
            case 6:
                on = "修改规格";
                break;
            case 7:
                on = "删除规格";
                break;
        }
        MdModelOperateLogEntity mdModelOperateLogEntity = new MdModelOperateLogEntity();
        mdModelOperateLogEntity.setOperateName(on);
        mdModelOperateLogEntity.setSuiId(account.getSuiId());
        mdModelOperateLogEntity.setOperateUser(account.getAccount());
        mdModelOperateLogEntity.setMdModelId(wmsModelId);
        mdModelOperateLogEntity.setCreateDate(new Date());
        mdModelOperateLogEntity.setCreateRen(account.getAccount());
        mdModelOperateLogEntity.setEditDate(new Date());
        mdModelOperateLogEntity.setEditRen(account.getAccount());
        return this.newObject(mdModelOperateLogEntity);
    }
}