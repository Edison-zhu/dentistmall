package com.hsk.dentistmall.api.daobbase.imp;

import java.util.*;

import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/**
 * md_materiel_info表数据库层面操作实现类
 *
 * @author 作者:admin
 * @version 版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
@Component
public class MdMaterielInfoDao extends SupperDao implements IMdMaterielInfoDao {

    /**
     * 根据md_materiel_info表主键查找MdMaterielInfo对象记录
     *
     * @param WmsMiId Integer类型(md_materiel_info表主键)
     * @return MdMaterielInfo md_materiel_info表记录
     * @throws HSKDBException
     */
    public MdMaterielInfo findMdMaterielInfoById(Integer WmsMiId) throws HSKDBException {
        MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
        if (WmsMiId != null) {
            att_MdMaterielInfo.setWmsMiId(WmsMiId);
            Object obj = this.getOne(att_MdMaterielInfo);
            if (obj != null) {
                att_MdMaterielInfo = (MdMaterielInfo) obj;
            }
        }
        return att_MdMaterielInfo;
    }

    /**
     * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
     *
     * @param WmsMiId Integer类型(md_materiel_info表主键)
     * @throws HSKDBException
     */
    public void deleteMdMaterielInfoById(Integer WmsMiId) throws HSKDBException {
        MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
        if (WmsMiId != null) {
            att_MdMaterielInfo.setWmsMiId(WmsMiId);
            Object obj = this.getOne(att_MdMaterielInfo);
            if (obj != null) {
                this.deleteObject(obj);
            }
        }
    }

    /**
     * 根据md_materiel_info表主键修改MdMaterielInfo对象记录
     *
     * @param WmsMiId            Integer类型(md_materiel_info表主键)
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @return MdMaterielInfo  修改后的MdMaterielInfo对象记录
     * @throws HSKDBException
     */
    public MdMaterielInfo updateMdMaterielInfoById(Integer WmsMiId, MdMaterielInfo att_MdMaterielInfo) throws HSKDBException {
        if (WmsMiId != null) {
            att_MdMaterielInfo.setWmsMiId(WmsMiId);
            Object obj = this.getOne(att_MdMaterielInfo);
            if (obj != null) {
                this.updateObject(obj);
            }
        }
        return att_MdMaterielInfo;
    }

    /**
     * 新增md_materiel_info表 记录
     *
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @throws HSKDBException
     */
    public Integer saveMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException {
        return this.newObject(att_MdMaterielInfo);
    }

    /**
     * 新增或修改md_materiel_info表记录 ,如果md_materiel_info表主键MdMaterielInfo.WmsMiId为空就是添加，如果非空就是修改
     *
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @throws HSKDBException
     */
    public MdMaterielInfo saveOrUpdateMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException {
//        att_MdMaterielInfo = (MdMaterielInfo)getHibernatesession().merge(att_MdMaterielInfo);
        this.getHibernateTemplate().saveOrUpdate(att_MdMaterielInfo);
        return att_MdMaterielInfo;
    }

    /**
     * 根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取列表对象
     *
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @return List<MdMaterielInfo>  列表对象
     * @throws HSKDBException
     */
    @SuppressWarnings("unchecked")
    public List<MdMaterielInfo> getListByMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException {
        String Hql = this.getHql(att_MdMaterielInfo);
        List<MdMaterielInfo> list = this.getHibernateTemplate().find(Hql);
        return list;
    }

    /**
     * 根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     *
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @return List<MdMaterielInfo>  分页对象
     * @throws HSKDBException
     */
    public PagerModel getPagerModelByMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo)
            throws HSKDBException {
        String Hql = this.getHql(att_MdMaterielInfo);
        Hql+=" order by wmsMiId  desc";
        return this.getHibernateDao().findByPage(Hql);
    }

    /**
     * 根据MdMaterielInfo对象获取Hql字符串
     *
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @return hql字符串
     */
    private String getHql(MdMaterielInfo att_MdMaterielInfo) {
        StringBuffer sbuffer = new StringBuffer(" from  MdMaterielInfo   where  1=1  ");
        String likeStr = att_MdMaterielInfo.getTab_like();
        String orderStr = att_MdMaterielInfo.getTab_order();

        //*****************无关字段处理begin*****************
        //处理in条件 物料信息表id(wmsMiId)
        if (att_MdMaterielInfo.getWmsMiId_str() != null &&
                !"".equals(att_MdMaterielInfo.getWmsMiId_str().trim())) {
            String intStr = att_MdMaterielInfo.getWmsMiId_str().trim();
            String[] arrayStr = intStr.split(",");

            if (arrayStr.length > 0) {
                sbuffer.append(" and ( ");
                for (int i = 0; i < arrayStr.length; i++) {
                    String did = arrayStr[i];
                    if (i == arrayStr.length - 1) {
                        sbuffer.append("  wmsMiId=" + did + "   ");
                    } else {
                        sbuffer.append("  wmsMiId=" + did + " or ");
                    }
                }
                sbuffer.append(" ) ");
            }

        }
        //处理in条件 供应商id(wzId)
        if (att_MdMaterielInfo.getWzId_str() != null &&
                !"".equals(att_MdMaterielInfo.getWzId_str().trim())) {
            String intStr = att_MdMaterielInfo.getWzId_str().trim();
            sbuffer.append(" and wzId in (" + intStr + ") ");
        }
        //处理in条件 物料信息表id(mdWmsMiId)
        if (att_MdMaterielInfo.getMdWmsMiId_str() != null &&
                !"".equals(att_MdMaterielInfo.getMdWmsMiId_str().trim())) {
            String intStr = att_MdMaterielInfo.getMdWmsMiId_str().trim();
            String[] arrayStr = intStr.split(",");

            if (arrayStr.length > 0) {
                sbuffer.append(" and ( ");
                for (int i = 0; i < arrayStr.length; i++) {
                    String did = arrayStr[i];
                    if (i == arrayStr.length - 1) {
                        sbuffer.append("  mdWmsMiId=" + did + "   ");
                    } else {
                        sbuffer.append("  mdWmsMiId=" + did + " or ");
                    }
                }
                sbuffer.append(" ) ");
            }

        }
        //处理in条件 保质期限(validPeriod)
        if (att_MdMaterielInfo.getValidPeriod_str() != null &&
                !"".equals(att_MdMaterielInfo.getValidPeriod_str().trim())) {
            String intStr = att_MdMaterielInfo.getValidPeriod_str().trim();
            String[] arrayStr = intStr.split(",");

            if (arrayStr.length > 0) {
                sbuffer.append(" and ( ");
                for (int i = 0; i < arrayStr.length; i++) {
                    String did = arrayStr[i];
                    if (i == arrayStr.length - 1) {
                        sbuffer.append("  validPeriod=" + did + "   ");
                    } else {
                        sbuffer.append("  validPeriod=" + did + " or ");
                    }
                }
                sbuffer.append(" ) ");
            }

        }
        //处理in条件 预警时间(alertDay)
        if (att_MdMaterielInfo.getAlertDay_str() != null &&
                !"".equals(att_MdMaterielInfo.getAlertDay_str().trim())) {
            String intStr = att_MdMaterielInfo.getAlertDay_str().trim();
            String[] arrayStr = intStr.split(",");

            if (arrayStr.length > 0) {
                sbuffer.append(" and ( ");
                for (int i = 0; i < arrayStr.length; i++) {
                    String did = arrayStr[i];
                    if (i == arrayStr.length - 1) {
                        sbuffer.append("  alertDay=" + did + "   ");
                    } else {
                        sbuffer.append("  alertDay=" + did + " or ");
                    }
                }
                sbuffer.append(" ) ");
            }

        }
        //处理in条件 序号(serialNumber)
        if (att_MdMaterielInfo.getSerialNumber_str() != null &&
                !"".equals(att_MdMaterielInfo.getSerialNumber_str().trim())) {
            String intStr = att_MdMaterielInfo.getSerialNumber_str().trim();
            String[] arrayStr = intStr.split(",");

            if (arrayStr.length > 0) {
                sbuffer.append(" and ( ");
                for (int i = 0; i < arrayStr.length; i++) {
                    String did = arrayStr[i];
                    if (i == arrayStr.length - 1) {
                        sbuffer.append("  serialNumber=" + did + "   ");
                    } else {
                        sbuffer.append("  serialNumber=" + did + " or ");
                    }
                }
                sbuffer.append(" ) ");
            }

        }
        //时间类型开始条件处理  创建时间(createDate)
        if (att_MdMaterielInfo.getCreateDate_start() != null) {
            sbuffer.append(" and  createDate<='" + att_MdMaterielInfo.getCreateDate_start() + "'");
        }
        //时间类型结束条件处理 创建时间(createDate)
        if (att_MdMaterielInfo.getCreateDate_end() != null) {
            sbuffer.append(" and  createDate>'" + att_MdMaterielInfo.getCreateDate_end() + "'");
        }
        //时间类型开始条件处理  修改时间(editDate)
        if (att_MdMaterielInfo.getEditDate_start() != null) {
            sbuffer.append(" and  editDate<='" + att_MdMaterielInfo.getEditDate_start() + "'");
        }
        //时间类型结束条件处理 修改时间(editDate)
        if (att_MdMaterielInfo.getEditDate_end() != null) {
            sbuffer.append(" and  editDate>'" + att_MdMaterielInfo.getEditDate_end() + "'");
        }
        //*****************无关字段处理end*****************
        //*****************数据库字段处理begin*************
        //物料信息表id(wmsMiId)
        if (att_MdMaterielInfo.getWmsMiId() != null) {
            sbuffer.append(" and wmsMiId=" + att_MdMaterielInfo.getWmsMiId());
        }
        //供应商id(wzId)
        if (att_MdMaterielInfo.getWzId() != null) {
            sbuffer.append(" and wzId=" + att_MdMaterielInfo.getWzId());
        }
        //单位类型(1:供应商,2:集团,3:医院,4:门诊)(purchaseType)
        if (att_MdMaterielInfo.getPurchaseType() != null &&
                !"".equals(att_MdMaterielInfo.getPurchaseType().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("purchaseType") != -1) {
                sbuffer.append(" and purchaseType  like '%" + att_MdMaterielInfo.getPurchaseType() + "%'");
            } else {
                sbuffer.append(" and purchaseType   ='" + att_MdMaterielInfo.getPurchaseType() + "'");
            }
        }
        //物料信息表id(mdWmsMiId)
        if (att_MdMaterielInfo.getMdWmsMiId() != null) {
            sbuffer.append(" and mdWmsMiId=" + att_MdMaterielInfo.getMdWmsMiId());
        }
        //企业名称(applicantName)
        if (att_MdMaterielInfo.getApplicantName() != null &&
                !"".equals(att_MdMaterielInfo.getApplicantName().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("applicantName") != -1) {
                sbuffer.append(" and applicantName  like '%" + att_MdMaterielInfo.getApplicantName() + "%'");
            } else {
                sbuffer.append(" and applicantName   ='" + att_MdMaterielInfo.getApplicantName() + "'");
            }
        }
        //法人手机号码(phoneNumber)
        if (att_MdMaterielInfo.getPhoneNumber() != null &&
                !"".equals(att_MdMaterielInfo.getPhoneNumber().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("phoneNumber") != -1) {
                sbuffer.append(" and phoneNumber  like '%" + att_MdMaterielInfo.getPhoneNumber() + "%'");
            } else {
                sbuffer.append(" and phoneNumber   ='" + att_MdMaterielInfo.getPhoneNumber() + "'");
            }
        }
        //企业住所地(corporateDomicile)
        if (att_MdMaterielInfo.getCorporateDomicile() != null &&
                !"".equals(att_MdMaterielInfo.getCorporateDomicile().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("corporateDomicile") != -1) {
                sbuffer.append(" and corporateDomicile  like '%" + att_MdMaterielInfo.getCorporateDomicile() + "%'");
            } else {
                sbuffer.append(" and corporateDomicile   ='" + att_MdMaterielInfo.getCorporateDomicile() + "'");
            }
        }
        //经营范围(scopeBusiness)
        if (att_MdMaterielInfo.getScopeBusiness() != null &&
                !"".equals(att_MdMaterielInfo.getScopeBusiness().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("scopeBusiness") != -1) {
                sbuffer.append(" and scopeBusiness  like '%" + att_MdMaterielInfo.getScopeBusiness() + "%'");
            } else {
                sbuffer.append(" and scopeBusiness   ='" + att_MdMaterielInfo.getScopeBusiness() + "'");
            }
        }
        //物料编码(matCode)
        if (att_MdMaterielInfo.getMatCode() != null &&
                !"".equals(att_MdMaterielInfo.getMatCode().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matCode") != -1) {
                if (att_MdMaterielInfo.getMmfCodeWmsMiId()!=null&&!att_MdMaterielInfo.getMmfCodeWmsMiId().equals("")){
                    sbuffer.append(" and (matCode  like '%" + att_MdMaterielInfo.getMatCode() + "%' or wmsMiId in ("+att_MdMaterielInfo.getMmfCodeWmsMiId()+"))");
                }else {
                    sbuffer.append(" and matCode  like '%" + att_MdMaterielInfo.getMatCode() + "%'");
                }

            } else {
                sbuffer.append(" and matCode   ='" + att_MdMaterielInfo.getMatCode() + "'");
            }
        }
        //物料名称(matName)
        if (att_MdMaterielInfo.getMatName() != null &&
                !"".equals(att_MdMaterielInfo.getMatName().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matName") != -1) {
                sbuffer.append(" and (matName  like '%" + att_MdMaterielInfo.getMatName().trim() + "%' or pyName like '%" + att_MdMaterielInfo.getMatName().trim().toUpperCase() + "%')");
            } else {
                sbuffer.append(" and matName   ='" + att_MdMaterielInfo.getMatName().trim() + "'");
            }
        }
        //基本单位(basicUnit)
        if (att_MdMaterielInfo.getBasicUnit() != null &&
                !"".equals(att_MdMaterielInfo.getBasicUnit().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("basicUnit") != -1) {
                sbuffer.append(" and basicUnit  like '%" + att_MdMaterielInfo.getBasicUnit() + "%'");
            } else {
                sbuffer.append(" and basicUnit   ='" + att_MdMaterielInfo.getBasicUnit() + "'");
            }
        }
        //批次规则(batchRule)
        if (att_MdMaterielInfo.getBatchRule() != null &&
                !"".equals(att_MdMaterielInfo.getBatchRule().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("batchRule") != -1) {
                sbuffer.append(" and batchRule  like '%" + att_MdMaterielInfo.getBatchRule() + "%'");
            } else {
                sbuffer.append(" and batchRule   ='" + att_MdMaterielInfo.getBatchRule() + "'");
            }
        }
        //保质期限(validPeriod)
        if (att_MdMaterielInfo.getValidPeriod() != null) {
            sbuffer.append(" and validPeriod=" + att_MdMaterielInfo.getValidPeriod());
        }
        //预警时间(alertDay)
        if (att_MdMaterielInfo.getAlertDay() != null) {
            sbuffer.append(" and alertDay=" + att_MdMaterielInfo.getAlertDay());
        }
        //规格(norm)
        if (att_MdMaterielInfo.getNorm() != null &&
                !"".equals(att_MdMaterielInfo.getNorm().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("norm") != -1) {
                sbuffer.append(" and norm  like '%" + att_MdMaterielInfo.getNorm() + "%'");
            } else {
                sbuffer.append(" and norm   ='" + att_MdMaterielInfo.getNorm() + "'");
            }
        }
        //物料类别(matType)
        if (att_MdMaterielInfo.getMatType() != null &&
                !"".equals(att_MdMaterielInfo.getMatType().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matType") != -1) {
                sbuffer.append(" and matType  like '%" + att_MdMaterielInfo.getMatType() + "%'");
            } else {
                sbuffer.append(" and matType   ='" + att_MdMaterielInfo.getMatType() + "'");
            }
        }
        //物料类别(matType1)
        if (att_MdMaterielInfo.getMatType1() != null &&
                !"".equals(att_MdMaterielInfo.getMatType1().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matType1") != -1) {
                sbuffer.append(" and matType1  like '%" + att_MdMaterielInfo.getMatType1() + "%'");
            } else {
                sbuffer.append(" and matType1   ='" + att_MdMaterielInfo.getMatType1() + "'");
            }
        }
        //拣选分类(matType2)
        if (att_MdMaterielInfo.getMatType2() != null &&
                !"".equals(att_MdMaterielInfo.getMatType2().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matType2") != -1) {
                sbuffer.append(" and matType2  like '%" + att_MdMaterielInfo.getMatType2() + "%'");
            } else {
                sbuffer.append(" and matType2   ='" + att_MdMaterielInfo.getMatType2() + "'");
            }
        }
        //码托分类(matType3)
        if (att_MdMaterielInfo.getMatType3() != null &&
                !"".equals(att_MdMaterielInfo.getMatType3().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matType3") != -1) {
                sbuffer.append(" and matType3  like '%" + att_MdMaterielInfo.getMatType3() + "%'");
            } else {
                sbuffer.append(" and matType3   ='" + att_MdMaterielInfo.getMatType3() + "'");
            }
        }
        //标注(labelInfo)
        if (att_MdMaterielInfo.getLabelInfo() != null &&
                !"".equals(att_MdMaterielInfo.getLabelInfo().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("labelInfo") != -1) {
                sbuffer.append(" and labelInfo  like '%" + att_MdMaterielInfo.getLabelInfo() + "%'");
            } else {
                sbuffer.append(" and labelInfo   ='" + att_MdMaterielInfo.getLabelInfo() + "'");
            }
        }
        //缩小图(lessenFilecode)
        if (att_MdMaterielInfo.getLessenFilecode() != null &&
                !"".equals(att_MdMaterielInfo.getLessenFilecode().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("lessenFilecode") != -1) {
                sbuffer.append(" and lessenFilecode  like '%" + att_MdMaterielInfo.getLessenFilecode() + "%'");
            } else {
                sbuffer.append(" and lessenFilecode   ='" + att_MdMaterielInfo.getLessenFilecode() + "'");
            }
        }
        //描述(describe1)
        if (att_MdMaterielInfo.getDescribe1() != null &&
                !"".equals(att_MdMaterielInfo.getDescribe1().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("describe1") != -1) {
                sbuffer.append(" and describe1  like '%" + att_MdMaterielInfo.getDescribe1() + "%'");
            } else {
                sbuffer.append(" and describe1   ='" + att_MdMaterielInfo.getDescribe1() + "'");
            }
        }
        //描述(describe2)
        if (att_MdMaterielInfo.getDescribe2() != null &&
                !"".equals(att_MdMaterielInfo.getDescribe2().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("describe2") != -1) {
                sbuffer.append(" and describe2  like '%" + att_MdMaterielInfo.getDescribe2() + "%'");
            } else {
                sbuffer.append(" and describe2   ='" + att_MdMaterielInfo.getDescribe2() + "'");
            }
        }
        //条码(barcode)
        if (att_MdMaterielInfo.getBarcode() != null &&
                !"".equals(att_MdMaterielInfo.getBarcode().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("barcode") != -1) {
                sbuffer.append(" and barcode  like '%" + att_MdMaterielInfo.getBarcode() + "%'");
            } else {
                sbuffer.append(" and barcode   ='" + att_MdMaterielInfo.getBarcode() + "'");
            }
        }
        //条码图片文件(barcodeFilecode)
        if (att_MdMaterielInfo.getBarcodeFilecode() != null &&
                !"".equals(att_MdMaterielInfo.getBarcodeFilecode().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("barcodeFilecode") != -1) {
                sbuffer.append(" and barcodeFilecode  like '%" + att_MdMaterielInfo.getBarcodeFilecode() + "%'");
            } else {
                sbuffer.append(" and barcodeFilecode   ='" + att_MdMaterielInfo.getBarcodeFilecode() + "'");
            }
        }
        //状态(state)
        if (att_MdMaterielInfo.getState() != null &&
                !"".equals(att_MdMaterielInfo.getState().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("state") != -1) {
                sbuffer.append(" and state  like '%" + att_MdMaterielInfo.getState() + "%'");
            } else {
                sbuffer.append(" and state   ='" + att_MdMaterielInfo.getState() + "'");
            }
        }
        //商家状态
        if (att_MdMaterielInfo.getWzState() != null &&
                !"".equals(att_MdMaterielInfo.getWzState().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("wzState") != -1) {
                sbuffer.append(" and wzState  like '%" + att_MdMaterielInfo.getWzState() + "%'");
            } else {
                sbuffer.append(" and wzState   ='" + att_MdMaterielInfo.getWzState() + "'");
            }
        }
        //基本单位精度(basicUnitAccuracy)
        if (att_MdMaterielInfo.getBasicUnitAccuracy() != null &&
                !"".equals(att_MdMaterielInfo.getBasicUnitAccuracy().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("basicUnitAccuracy") != -1) {
                sbuffer.append(" and basicUnitAccuracy  like '%" + att_MdMaterielInfo.getBasicUnitAccuracy() + "%'");
            } else {
                sbuffer.append(" and basicUnitAccuracy   ='" + att_MdMaterielInfo.getBasicUnitAccuracy() + "'");
            }
        }
        //背面印字(backPrinting)
        if (att_MdMaterielInfo.getBackPrinting() != null &&
                !"".equals(att_MdMaterielInfo.getBackPrinting().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("backPrinting") != -1) {
                sbuffer.append(" and backPrinting  like '%" + att_MdMaterielInfo.getBackPrinting() + "%'");
            } else {
                sbuffer.append(" and backPrinting   ='" + att_MdMaterielInfo.getBackPrinting() + "'");
            }
        }
        //序号(serialNumber)
        if (att_MdMaterielInfo.getSerialNumber() != null) {
            sbuffer.append(" and serialNumber=" + att_MdMaterielInfo.getSerialNumber());
        }
        //创建人(createRen)
        if (att_MdMaterielInfo.getCreateRen() != null &&
                !"".equals(att_MdMaterielInfo.getCreateRen().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
                sbuffer.append(" and createRen  like '%" + att_MdMaterielInfo.getCreateRen() + "%'");
            } else {
                sbuffer.append(" and createRen   ='" + att_MdMaterielInfo.getCreateRen() + "'");
            }
        }
        //创建时间(createDate)
        if (att_MdMaterielInfo.getCreateDate() != null) {
            sbuffer.append(" and  createDate='" + att_MdMaterielInfo.getCreateDate() + "'");
        }

        if (att_MdMaterielInfo.getStartDate() != null && !att_MdMaterielInfo.getStartDate().equals("")) {
            String date = att_MdMaterielInfo.getStartDate();
            String start = "";
            String end = "";
            if (date.indexOf("~") >= 0) {
                if (date.split("~")[0].indexOf(" ") >= 0) {
                    start = date.split("~")[0];
                    end = date.split("~")[1];
                } else {
                    start = date.split("~")[0] + " 00:00:00";
                    end = date.split("~")[1] + " 23:59:59";
                }
                sbuffer.append(" and createDate >= '" + start + "'" +
                        " and createDate <= '" + end + "'");
            } else {
                sbuffer.append(" and createDate = '" + att_MdMaterielInfo.getStartDate() + "'");
            }
        }

        if (att_MdMaterielInfo.getSearchName() != null && !att_MdMaterielInfo.getSearchName().equals("")) {
            String searchName = att_MdMaterielInfo.getSearchName();
            sbuffer.append(" and (productFactory like '%" + searchName + "%'" +
                    " or matName like '%" + searchName + "%'" +
                    " or matCode like '%" + searchName + "%'" +
                    " or aliasName like '%" + searchName + "%'" +
                    " or wmsMiId in (select wmsMiId from MdMaterielFormat where mmfCode like '%" + searchName + "%' or mmfName like '%" + searchName + "%')" +
                    ")");
        }

        if (att_MdMaterielInfo.getMdpId() != null) {
            sbuffer.append(" and mdpId = " + att_MdMaterielInfo.getMdpId());
        }

        if (att_MdMaterielInfo.getMdpsId() != null) {
            sbuffer.append(" and mdpsId = " + att_MdMaterielInfo.getMdpsId());
        }

        //修改人(editRen)
        if (att_MdMaterielInfo.getEditRen() != null &&
                !"".equals(att_MdMaterielInfo.getEditRen().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
                sbuffer.append(" and editRen  like '%" + att_MdMaterielInfo.getEditRen() + "%'");
            } else {
                sbuffer.append(" and editRen   ='" + att_MdMaterielInfo.getEditRen() + "'");
            }
        }
        //修改时间(editDate)
        if (att_MdMaterielInfo.getEditDate() != null) {
            sbuffer.append(" and  editDate='" + att_MdMaterielInfo.getEditDate() + "'");
        }

        if (att_MdMaterielInfo.getMatCodes() != null && !att_MdMaterielInfo.getMatCodes().equals("")) {
            String[] matCodeA = att_MdMaterielInfo.getMatCodes().split(",");
            sbuffer.append(" and (1=1");
            for (String mc : matCodeA) {
                sbuffer.append(" and matCode = " + mc);
            }
            sbuffer.append(")");
        }

        if (att_MdMaterielInfo.getMatNames() != null && !att_MdMaterielInfo.getMatNames().equals("")) {
            String[] matNameA = att_MdMaterielInfo.getMatNames().split(",");
            sbuffer.append(" and (1=1");
            for (String mn : matNameA){
                sbuffer.append(" and matName = " + mn);
            }
            sbuffer.append(")");
        }

        if (att_MdMaterielInfo.getBasicUnits() != null && !att_MdMaterielInfo.getBasicUnits().equals("")) {
            String[] buA = att_MdMaterielInfo.getBasicUnits().split(",");
            sbuffer.append(" and (1=1");
            for (String bu : buA) {
                sbuffer.append(" and basicUnit = " + bu);
            }
            sbuffer.append(")");
        }

        if (att_MdMaterielInfo.getUnits() != null && !att_MdMaterielInfo.getUnits().equals("")) {
            String[] uA = att_MdMaterielInfo.getUnits().split(",");
            sbuffer.append(" and (1=1");
            for (String u : uA) {
                sbuffer.append(" and unit in (" + u);
            }
            sbuffer.append(")");
        }

        if (att_MdMaterielInfo.getPackages() != null && !att_MdMaterielInfo.getPackages().equals("")) {
            String[] pA = att_MdMaterielInfo.getPackages().split(",");
            sbuffer.append(" and (1=1");
            for (String p : pA) {
                sbuffer.append(" and productName = " + p);
            }
            sbuffer.append(")");
        }

//        if (att_MdMaterielInfo.getParts() != null) {
//            String[] pA = att_MdMaterielInfo.getParts().split(",");
//            for (String p : pA) {
//                sbuffer.append(" and (mdpId in (" + att_MdMaterielInfo.getParts() + ")");
//                sbuffer.append(" or mdpId in (select mdpId from MdMaterielPart where mdpName in (" + att_MdMaterielInfo.getParts() + ")))");
//            }
//            sbuffer.append(")");
//        }
//
//        if (att_MdMaterielInfo.getSecondParts() != null && !att_MdMaterielInfo.getSecondParts().equals("")) {
//            sbuffer.append(" and (mdpsId in (" + att_MdMaterielInfo.getSecondParts() + ")");
//            sbuffer.append(" or mdpsId in (select mdpsId from MdMaterielPart where mdpsName in (" + att_MdMaterielInfo.getParts() + ")))");
//        }

        //*****************数据库字段处理end***************
        if (orderStr != null && !"".equals(orderStr.trim())) {
            sbuffer.append(" order by " + orderStr);
        }
						 /*
						 else {
				 			  sbuffer.append( " order by  WmsMiId   desc " );
					      }
					      */

        return sbuffer.toString();
    }

    @Override
    public PagerModel getPagerModelByMdMdMaterielFormatView(MdMaterielFormatView mdMaterielFormatView) throws HSKDBException {
//        String hql = "from MdMaterielFormatView where 1=1";
        String hql = "SELECT\n" +
                "t.mmf_id AS mmfId,\n" +
                "t.ALERT_DAY AS ALERTDAY,\n" +
                "t.alias_name AS aliasName,\n" +
                "t.applicant_Name AS applicantName,\n" +
                "t.back_Printing AS backPrinting,\n" +
                "t.barCode AS barCode,\n" +
                "t.barCode_filecode AS barCodeFilecode,\n" +
                "t.basic_coefficent AS basicCoefficent,\n" +
                "t.Basic_unit AS basicUnit,\n" +
                "t.Basic_unit_accuracy AS basicUnitAccuracy,\n" +
                "t.batch_code AS batchCode,\n" +
                "t.Batch_rule AS batchRule,\n" +
                "t.brand AS brand,\n" +
                "t.cfca01_date AS cfca01Date,\n" +
                "t.cfca01_filecode AS cfca01Filecode,\n" +
                "t.cfca02_date AS cfca02Date,\n" +
                "t.cfca02_filecode AS cfca02Filecode,\n" +
                "t.cfca03_filecode AS cfca03Filecode,\n" +
                "t.cfca04_filecode AS cfca04Filecode,\n" +
                "t.cfca05_filecode AS cfca05Filecode,\n" +
                "t.cfca06_filecode AS cfca06Filecode,\n" +
                "t.Corporate_domicile AS corporateDomicile,\n" +
                "date_format( t.create_date, '%Y-%m-%d %H:%i:%s' ) AS createDate,\n" +
                "t.create_ren AS createRen,\n" +
                "t.describe1 AS describe1,\n" +
                "t.describe2 AS describe2,\n" +
                "date_format( t.edit_date, '%Y-%m-%d %H:%i:%s' ) AS editDate,\n" +
                "t.edit_ren AS editRen,\n" +
                "t.ingredient AS ingredient,\n" +
                "t.inventory_location AS inventoryLocation,\n" +
                "t.Label_info AS LabelInfo,\n" +
                "t.lessen_filecode AS lessenFilecode,\n" +
                "t.mat_code AS matCode,\n" +
                "t.mat_name AS matName,\n" +
                "t.mat_type AS matType,\n" +
                "t.mat_type1 AS matType1,\n" +
                "t.mat_type2 AS matType2,\n" +
                "t.mat_type3 AS matType3,\n" +
                "t.materiel_name AS materielName,\n" +
                "t.md_wms_mi_id AS mdWmsMiId,\n" +
                "t.mdp_id AS mdpId,\n" +
                "t.mdps_id AS mdpsId,\n" +
                "t.mmf_code AS mmfCode,\n" +
                "t.mmf_name AS mmfName,\n" +
                "t.mmf_state AS mmfState,\n" +
                "t.money1 AS money1,\n" +
                "t.money2 AS money2,\n" +
                "t.money3 AS money3,\n" +
                "t.money4 AS money4,\n" +
                "t.money5 AS money5,\n" +
                "t.NORM AS norm,\n" +
                "t.number1 AS number1,\n" +
                "t.number2 AS number2,\n" +
                "t.number3 AS number3,\n" +
                "t.number4 AS number4,\n" +
                "t.number5 AS number5,\n" +
                "t.Phone_number AS phoneNumber,\n" +
                "t.price AS price,\n" +
                "t.product_factory AS productFactory,\n" +
                "t.product_name AS productName,\n" +
                "t.product_use AS productUse,\n" +
                "t.purchase_type AS purchaseType,\n" +
                "t.py_name AS pyName,\n" +
                "t.remark AS remark,\n" +
                "t.retail_price AS retailPrice,\n" +
                "t.scope_business AS scopeBusiness,\n" +
                "t.Serial_number AS serialNumber,\n" +
                "t.split_unit AS splitUnit,\n" +
                "t.standard AS standard,\n" +
                "t.state AS state,\n" +
                "t.type_name AS typeName,\n" +
                "t.VALID_PERIOD AS validPeriod,\n" +
                "date_format( t.valied_date, '%Y-%m-%d %H:%i:%s' ) AS valiedDate,\n" +
                "t.wms_mi_id AS wmsMiId,\n" +
                "t.wz_id AS wzId,\n" +
                "t.wz_state AS wzState,\n" +
                "t.xid AS xid" +
                " from md_materiel_format_view t where 1=1 ";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().trim().equals(""))
            hql += " and t.purchase_type='" + mdMaterielFormatView.getPurchaseType().trim() + "'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getWzId() != null)
            hql += " and t.wz_id=" + mdMaterielFormatView.getWzId();
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatCode() != null && !mdMaterielFormatView.getMatCode().trim().equals(""))
            hql += " and t.mat_code like '%" + mdMaterielFormatView.getMatCode().trim().toUpperCase() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
            {hql += " and (t.mat_name like '%" + mdMaterielFormatView.getMatName().trim() + "%'";
            hql += " or t.mat_code like '%" + mdMaterielFormatView.getMatName().trim().toUpperCase() + "%')";}
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
            hql += " and t.mmf_name like '%" + mdMaterielFormatView.getMmfName().trim() + "%'";
        if (mdMaterielFormatView.getWmsMiIds() != null && !mdMaterielFormatView.getWmsMiIds().equals("")) {
            hql += " and t.wms_mi_id in (" + mdMaterielFormatView.getWmsMiIds().trim() + ")";
        }
        if (mdMaterielFormatView.getWmsMiId() != null) {
            hql += " and t.wms_mi_id = " + mdMaterielFormatView.getWmsMiId();
        }
        if (mdMaterielFormatView.getMmfId() != null) {
            hql += " and t.mmf_id = " + mdMaterielFormatView.getMmfId();
        }
        if (mdMaterielFormatView.getMmfIds() != null && !mdMaterielFormatView.getMmfIds().equals("")) {
            hql += " and t.mmf_id in (" + mdMaterielFormatView.getMmfIds().trim() + ")";
        }
        if (mdMaterielFormatView.getSearchName() != null && !mdMaterielFormatView.getSearchName().equals("")) {
            hql += " and (t.mmf_name like '%" + mdMaterielFormatView.getSearchName() + "%'" +
                    "or t.mmf_name like '%" + mdMaterielFormatView.getSearchName().toUpperCase() + "%'" +
                    "or t.mat_name like '%" + mdMaterielFormatView.getSearchName() + "%'" +
                    "or t.mat_name like '%" + mdMaterielFormatView.getSearchName().toUpperCase() + "%'" +
                    "or t.mat_code like '%" + mdMaterielFormatView.getSearchName() + "%'" +
                    "or t.mmf_code like '%" + mdMaterielFormatView.getSearchName() + "%'" +
                    "or t.mat_code like '%" + mdMaterielFormatView.getSearchName().toUpperCase() + "%'" +
                    "or t.mmf_code like '%" + mdMaterielFormatView.getSearchName().toUpperCase() + "%'" +
                    "or t.alias_name like '%" + mdMaterielFormatView.getSearchName().toUpperCase() + "%'" +
                    "or t.alias_name like '%" + mdMaterielFormatView.getSearchName() + "%')";
        }
        if (mdMaterielFormatView.getMdpId() != null) {
            hql += " and mdp_id = " + mdMaterielFormatView.getMdpId();
        }
        if (mdMaterielFormatView.getMdpsId() != null) {
            hql += " and mdps_id = " + mdMaterielFormatView.getMdpsId();
        }
        List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + hql + ") as t");
        hql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
        List<Map<String, Object>> list = this.getJdbcDao().query(hql);
        PagerModel pm = new PagerModel();
        pm.setRows(list);
        pm.setItems(list);

        Integer count = 0;
        if (cList == null || cList.isEmpty())
            count = 0;
        Map<String, Object> map = cList.get(0);
        if (map == null || map.isEmpty())
            count = 0;
        else
            count = Integer.parseInt(map.get("totalCount").toString());
        pm.setTotalCount(count);
        pm.setTotal(count);
        return pm;
    }

    @Override
    public PagerModel getPagerModelBySearchName(String serachName, Integer mmtId, Integer wzId, String brand, String state, String wzState, String purchaseType, String orderStr) throws HSKDBException {
        StringBuffer hql = new StringBuffer("from MdMaterielInfo where state='" + state + "' and wzState='" + wzState + "' and purchaseType='" + purchaseType + "'");
        Integer orderIndex = 0;
        StringBuffer sb = new StringBuffer("");
        if (mmtId != null)
            hql.append(" and matType like '%" + mmtId + "%'");
        if (wzId != null)
            hql.append(" and wzId=" + wzId);
        if (brand != null && !brand.trim().equals(""))
            hql.append(" and brand ='" + brand.trim() + "'");
        if (serachName != null && !serachName.trim().equals("")) {
            hql.append("and (");
            hql.append(" (upper(matName) like '%" + serachName.trim().toUpperCase()
                    + "%' or norm like '%" + serachName.trim().toUpperCase()
                    + "%' or upper(pyName) like '%" + serachName.trim().toUpperCase()
                    + "%' or upper(productName) like '%" + serachName.trim()
                    + "%' or brand like '%" + serachName.trim()
                    + "%' or upper(matCode) like '%" + serachName.trim().toUpperCase()
                    + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + serachName.trim().toUpperCase()
                    + "%') or aliasName like '%" + serachName.trim() + "%')");
            //处理字符串重有空格
            String[] spaceSplite = serachName.split(" ");
            int a = spaceSplite.length;
            // 空格进行拆分排序
            String order = "";
            for (String str : spaceSplite) {
                orderIndex += 4;
                order += str;
                sb.insert(0, ", ((case when upper(matName) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when upper(norm) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when upper(productName) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when brand like '" + order.trim() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when upper(matCode) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when aliasName like '" + order.trim() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when upper(pyName) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end)) desc," +
                        " ((case when upper(matName) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when upper(norm) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when upper(productName) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when brand like '" + order.trim() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when upper(matCode) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when aliasName like '" + order.trim() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when upper(pyName) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end)) desc," +
                        " ((case when upper(matName) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when upper(norm) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when upper(productName) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when brand like '%" + order.trim() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when upper(matCode) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when aliasName like '%" + order.trim() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when upper(pyName) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end)) desc");
                order += " ";
            }
            if (spaceSplite.length > 1 && spaceSplite.length <= 3) {
                List<String> listStr = new ArrayList<String>();
                listStr.add("upper(matName) like '%");
                listStr.add("norm like '%");
                listStr.add("upper(pyName) like '%");
                listStr.add("upper(productName) like '%");
                listStr.add("brand like '%");
                listStr.add("aliasName like '%");
                String sqlStr = "";
                boolean allEmpty = false;
                for(String sql : listStr) {
                    String temSql = "(";
                    for (String splitStr : spaceSplite) {
                        if(splitStr.trim().equals("")){
                            allEmpty = true;
                            continue;
                        }
                        allEmpty = false;
                        temSql += sql + splitStr + "%' or ";
                        temSql += " wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + serachName.trim().toUpperCase() + "%') or ";
                    }
                    if(!allEmpty) {
                        temSql = temSql.substring(0, temSql.length() - 3);

                        sqlStr += temSql + ") or ";
                    }
                }
                sqlStr = sqlStr.substring(0, sqlStr.length() - 3);
                hql.append(" or (" + sqlStr + ")");
//                hql.append(" or ((upper(matName) like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim().toUpperCase() : "") + "%' or upper(matName) like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim().toUpperCase() : "") + "%' or upper(matName) like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim().toUpperCase() : "") + "%') "
//                        + " or (norm like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim().toUpperCase() : "") + "%' or norm like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim().toUpperCase() : "") + "%' or norm like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim().toUpperCase() : "") + "%')"
//                        + " or (upper(pyName) like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim().toUpperCase() : "") + "%' or upper(pyName) like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim().toUpperCase() : "") + "%' or upper(pyName) like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim().toUpperCase() : "") + "%') "
//                        + " or (upper(productName) like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim() : "") + "%' or upper(productName) like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim() : "") + "%' or upper(productName) like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim() : "") + "%') "
//                        + " or (brand like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim() : "") + "%' or brand like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim() : "") + "%' or brand like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim() : "") + "%') "
//                        + " or (aliasName like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim() : "") + "%' or aliasName like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim() : "") + "%' or aliasName like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim() : "") + "%'))");
            } else if (spaceSplite.length > 3) {
                String temp = "";
                for (int i = 2; i < spaceSplite.length; i++) {
                    temp += spaceSplite[i];
                }
                hql.append(" or (upper(matName) like '%" + temp.trim().toUpperCase()
                        + "%' or norm like '%" + temp.trim().toUpperCase()
                        + "%' or upper(pyName) like '%" + temp.trim().toUpperCase()
                        + "%' or upper(productName) like '%" + temp.trim()
                        + "%' or brand like '%" + temp.trim()
                        + "%' or upper(matCode) like '%" + temp.trim().toUpperCase()
                        + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + temp.trim().toUpperCase()
                        + "%') or aliasName like '%" + temp.trim() + "%')");
            }
            String[] spliteStr = serachName.split("");
            String tempStr = "";
            for (String str : spliteStr){
                if(str.trim().equals("")){
                    continue;
                }
                tempStr += str + "%";
            }
            if(!tempStr.equals("")){
                tempStr = tempStr.substring(0, tempStr.length() - 1);
                hql.append(" or (upper(matName) like '%" + tempStr.trim().toUpperCase()
                        + "%' or norm like '%" + tempStr.trim().toUpperCase()
                        + "%' or upper(pyName) like '%" + tempStr.trim().toUpperCase()
                        + "%' or upper(productName) like '%" + tempStr.trim()
                        + "%' or brand like '%" + tempStr.trim()
                        + "%' or upper(matCode) like '%" + tempStr.trim().toUpperCase()
                        + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + tempStr.trim().toUpperCase()
                        + "%') or aliasName like '%" + tempStr.trim() + "%')");
            }

            hql.append(")");
        }
        // 全字匹配
        orderIndex += 4;
        sb.insert(0, " ((case when upper(matName) like '" + serachName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                " + (case when upper(norm) like '" + serachName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                " + (case when upper(productName) like '" + serachName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                " + (case when brand like '" + serachName.trim() + "' then " + orderIndex + " else 0 end) " +
                " + (case when upper(matCode) like '" + serachName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                " + (case when aliasName like '" + serachName.trim() + "' then " + orderIndex + " else 0 end) " +
                " + (case when upper(pyName) like '" + serachName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end)) desc," +
                " ((case when upper(matName) like '" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when upper(norm) like '" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when upper(productName) like '" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when brand like '" + serachName.trim() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when upper(matCode) like '" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when aliasName like '" + serachName.trim() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when upper(pyName) like '" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end)) desc," +
                " ((case when upper(matName) like '%" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when upper(norm) like '%" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when upper(productName) like '%" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when brand like '%" + serachName.trim() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when upper(matCode) like '%" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when aliasName like '%" + serachName.trim() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when upper(pyName) like '%" + serachName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end)) desc");
        hql.append(" order by " + sb.toString());
        hql.append(" , " + orderStr);
        PagerModel p = this.getHibernateDao().findByPage(hql.toString());
        return p;
    }

    @Override
    public PagerModel getPagerModelByInTimeSearchName(String searchName, String orderStr, String purchaseType) throws HSKDBException {
        StringBuffer hql = new StringBuffer("select matName, wmsMiId from MdMaterielInfo where state='1' and wzState='1' and purchaseType='" + purchaseType + "'");
        Integer orderIndex = 0;
        StringBuffer sb = new StringBuffer("");
        // 输入规格代码或规格或者商品编号或名称关键字后自动出现商品名称点击商品名称列表显示对应的商品
        if (searchName != null && !searchName.trim().equals("")) {
            hql.append("and (");
            hql.append(" (upper(matName) like '%" + searchName.trim().toUpperCase()
                    + "%' or norm like '%" + searchName.trim().toUpperCase()
                    + "%' or upper(matCode) like '%" + searchName.trim().toUpperCase()
                    + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + searchName.trim().toUpperCase()
                    + "%'))");
            //处理字符串重有空格
            String[] spaceSplite = searchName.split(" ");
            int a = spaceSplite.length;
            // 空格进行拆分排序
            String order = "";
            for (String str : spaceSplite) {
                orderIndex += 4;
                order += str;
                sb.insert(0, ", ((case when upper(matName) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when upper(norm) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                        " + (case when upper(matCode) like '" + order.trim().toUpperCase() + "' then " + orderIndex + " else 0 end)) desc," +
                        " ((case when upper(matName) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when upper(norm) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                        " + (case when upper(matCode) like '" + order.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end)) desc," +
                        " ((case when upper(matName) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when upper(norm) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                        " + (case when upper(matCode) like '%" + order.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end)) desc");
                order += " ";
            }
            if (spaceSplite.length > 1 && spaceSplite.length <= 3) {
                List<String> listStr = new ArrayList<String>();
                listStr.add("upper(matName) like '%");
                listStr.add("norm like '%");
                listStr.add("upper(matCode) like '%");
                String sqlStr = "";
                boolean allEmpty = false;
                for(String sql : listStr) {
                    String temSql = "(";
                    for (String splitStr : spaceSplite) {
                        if(splitStr.trim().equals("")){
                            allEmpty = true;
                            continue;
                        }
                        allEmpty = false;
                        temSql += sql + splitStr.toUpperCase() + "%' or ";
                        temSql += " wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + searchName.trim().toUpperCase() + "%') or ";
                    }
                    if(!allEmpty) {
                        temSql = temSql.substring(0, temSql.length() - 3);

                        sqlStr += temSql + ") or ";
                    }
                }
                sqlStr = sqlStr.substring(0, sqlStr.length() - 3);
                hql.append(" or (" + sqlStr + ")");
//                hql.append(" or ((upper(matName) like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim().toUpperCase() : "") + "%' or upper(matName) like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim().toUpperCase() : "") + "%' or upper(matName) like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim().toUpperCase() : "") + "%') "
//                        + " or (norm like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim().toUpperCase() : "") + "%' or norm like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim().toUpperCase() : "") + "%' or norm like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim().toUpperCase() : "") + "%')"
//                        + " or (upper(pyName) like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim().toUpperCase() : "") + "%' or upper(pyName) like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim().toUpperCase() : "") + "%' or upper(pyName) like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim().toUpperCase() : "") + "%') "
//                        + " or (upper(productName) like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim() : "") + "%' or upper(productName) like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim() : "") + "%' or upper(productName) like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim() : "") + "%') "
//                        + " or (brand like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim() : "") + "%' or brand like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim() : "") + "%' or brand like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim() : "") + "%') "
//                        + " or (aliasName like '%" + (spaceSplite[0] != null ? spaceSplite[0].trim() : "") + "%' or aliasName like '%" + (spaceSplite.length >= 2 ? spaceSplite[1].trim() : "") + "%' or aliasName like '%" + (spaceSplite.length >= 3 ? spaceSplite[2].trim() : "") + "%'))");
            } else if (spaceSplite.length > 3) {
                String temp = "";
                for (int i = 2; i < spaceSplite.length; i++) {
                    temp += spaceSplite[i];
                }
                hql.append(" or (upper(matName) like '%" + temp.trim().toUpperCase()
                        + "%' or norm like '%" + temp.trim().toUpperCase()
                        + "%' or upper(matCode) like '%" + temp.trim().toUpperCase()
                        + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + temp.trim().toUpperCase()
                        + "%'))");
            }
            String[] spliteStr = searchName.split("");
            String tempStr = "";
            for (String str : spliteStr){
                if(str.trim().equals("")){
                    continue;
                }
                tempStr += str + "%";
            }
            if(!tempStr.equals("")){
                tempStr = tempStr.substring(0, tempStr.length() - 1);
                hql.append(" or (upper(matName) like '%" + tempStr.trim().toUpperCase()
                        + "%' or norm like '%" + tempStr.trim().toUpperCase()
                        + "%' or upper(matCode) like '%" + tempStr.trim().toUpperCase()
                        + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + tempStr.trim().toUpperCase()
                        + "%'))");
            }

            hql.append(")");
        }
        // 全字匹配
        orderIndex += 4;
        sb.insert(0, " ((case when upper(matName) like '" + searchName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                " + (case when upper(norm) like '" + searchName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end) " +
                " + (case when upper(matCode) like '" + searchName.trim().toUpperCase() + "' then " + orderIndex + " else 0 end)) desc," +
                " ((case when upper(matName) like '" + searchName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when upper(norm) like '" + searchName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end) " +
                " + (case when upper(matCode) like '" + searchName.trim().toUpperCase() + "%' then " + (orderIndex - 1) + " else 0 end)) desc," +
                " ((case when upper(matName) like '%" + searchName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when upper(norm) like '%" + searchName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end) " +
                " + (case when upper(matCode) like '%" + searchName.trim().toUpperCase() + "%' then " + (orderIndex - 2) + " else 0 end)) desc");
        hql.append(" order by " + sb.toString());
        hql.append(" , " + orderStr);
        PagerModel p = this.getHibernateDao().findByPage(hql.toString());
        return p;
    }

    @Override
    public List<MdMaterielFormatView> getListModelByMdMdMaterielFormatView(MdMaterielFormatView mdMaterielFormatView) throws HSKDBException {
        String hql = "from MdMaterielFormatView where 1=1";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().trim().equals(""))
            hql += " and purchaseType='" + mdMaterielFormatView.getPurchaseType().trim() + "'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getWzId() != null)
            hql += " and wzId=" + mdMaterielFormatView.getWzId();
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatCode() != null && !mdMaterielFormatView.getMatCode().trim().equals(""))
            hql += " and matCode like '%" + mdMaterielFormatView.getMatCode().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getApplicantName() != null && !mdMaterielFormatView.getApplicantName().trim().equals(""))
            hql += " and applicantName like '%" + mdMaterielFormatView.getApplicantName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
            hql += " and matName like '%" + mdMaterielFormatView.getMatName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
            hql += " and mmfName like '%" + mdMaterielFormatView.getMmfName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfIds() != null && !mdMaterielFormatView.getMmfIds().trim().equals(""))
            hql += " and mmfId in (" + mdMaterielFormatView.getMmfIds().trim() + ")";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getTypeName() != null && !mdMaterielFormatView.getTypeName().trim().equals(""))
            hql += " and typeName like '%" + mdMaterielFormatView.getTypeName().trim() + "%'";
        hql += " order by wmsMiId,mmfId";
        List<MdMaterielFormatView> list = this.getHibernateTemplate().find(hql);
        return list;
    }
    @Override
    public List<MdMaterielFormatViewCarts> getListModelByMdMdMaterielFormatView2(MdMaterielFormatViewCarts mdMaterielFormatView) throws HSKDBException {
        String hql = "from MdMaterielFormatViewCarts where 1=1";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().trim().equals(""))
            hql += " and purchaseType='" + mdMaterielFormatView.getPurchaseType().trim() + "'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getWzId() != null)
            hql += " and wzId=" + mdMaterielFormatView.getWzId();
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatCode() != null && !mdMaterielFormatView.getMatCode().trim().equals(""))
            hql += " and matCode like '%" + mdMaterielFormatView.getMatCode().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getApplicantName() != null && !mdMaterielFormatView.getApplicantName().trim().equals(""))
            hql += " and applicantName like '%" + mdMaterielFormatView.getApplicantName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
            hql += " and matName like '%" + mdMaterielFormatView.getMatName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
            hql += " and mmfName like '%" + mdMaterielFormatView.getMmfName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfIds() != null && !mdMaterielFormatView.getMmfIds().trim().equals(""))
            hql += " and mmfId in (" + mdMaterielFormatView.getMmfIds().trim() + ")";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getTypeName() != null && !mdMaterielFormatView.getTypeName().trim().equals(""))
            hql += " and typeName like '%" + mdMaterielFormatView.getTypeName().trim() + "%'";
//        if (mdMaterielFormatView!=null&&mdMaterielFormatView.getSuiId()!=null)
//            hql+= " and suiId="+mdMaterielFormatView.getSuiId();
        hql += " order by editDate1 ASC";
        List<MdMaterielFormatViewCarts> list = this.getHibernateTemplate().find(hql);
        return list;
    }

    public  String getCartMmfIdSuiId(String  mmfIds,Integer suiId) throws HSKDBException{
        String sql=" SELECT  GROUP_CONCAT(t1.mmf_id) AS mmfId FROM md_carts t1 WHERE 1=1";
        if (mmfIds!=null&&!mmfIds.equals("")){
            sql+=" AND t1.mmf_id IN("+mmfIds+")";
        }
        if (suiId!=null){
            sql+=" AND t1.sui_id="+suiId;
        }
        sql+=" AND t1.mmf_id!=0 ORDER BY t1.edit_date DESC";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return null;
        if (map.get("mmfId") == null)
            return null;
        return map.get("mmfId").toString();
    }
//    @Override
//    public List<MdMaterielFormatViewCarts> getListModelByMdMdMaterielFormatView2(MdMaterielFormatViewCarts mdMaterielFormatView) throws HSKDBException {
//        String hql = "from MdMaterielFormatViewCarts mv,MdCarts mc where 1=1";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().trim().equals(""))
//            hql += " and mv.purchaseType='" + mdMaterielFormatView.getPurchaseType().trim() + "'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getWzId() != null)
//            hql += " and mv.wzId=" + mdMaterielFormatView.getWzId();
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatCode() != null && !mdMaterielFormatView.getMatCode().trim().equals(""))
//            hql += " and mv.matCode like '%" + mdMaterielFormatView.getMatCode().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getApplicantName() != null && !mdMaterielFormatView.getApplicantName().trim().equals(""))
//            hql += " and mv.applicantName like '%" + mdMaterielFormatView.getApplicantName().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
//            hql += " and mv.matName like '%" + mdMaterielFormatView.getMatName().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
//            hql += " and mv.mmfName like '%" + mdMaterielFormatView.getMmfName().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfIds() != null && !mdMaterielFormatView.getMmfIds().trim().equals(""))
//            hql += " and mv.mmfId in (" + mdMaterielFormatView.getMmfIds().trim() + ")";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getTypeName() != null && !mdMaterielFormatView.getTypeName().trim().equals(""))
//            hql += " and mv.typeName like '%" + mdMaterielFormatView.getTypeName().trim() + "%'";
////        hql += " order by wmsMiId,mmfId";
//
//        hql+=" and mc.mmfId in (" + mdMaterielFormatView.getMmfIds().trim() + ")";
//        hql+=" and mc.suiId ="+mdMaterielFormatView.getSuiId();
//        List<MdMaterielFormatViewCarts> list = this.getHibernateTemplate().find(hql);
//        return list;
//    }

//    public List<MdMaterielFormatViewCarts> getListModelByMdMdMaterielFormatView2(MdMaterielFormatViewCarts mdMaterielFormatView) throws HSKDBException {
//        String hql=" SELECT\n" +
//                "mdmateriel0_.mmf_id AS mmfId,\n" +
//                "mdcarts1_.mc_id AS mcId,\n" +
//                "mdmateriel0_.ALERT_DAY AS alertDay,\n" +
//                "mdmateriel0_.alias_name AS aliasName,\n" +
//                "mdmateriel0_.applicant_Name AS applicantName,\n" +
//                "mdmateriel0_.back_Printing AS backPrinting,\n" +
//                "mdmateriel0_.barCode AS barCode,\n" +
//                "mdmateriel0_.barCode_filecode AS barCodeFilecode,\n" +
//                "mdmateriel0_.basic_coefficent AS basicCoefficent,\n" +
//                "mdmateriel0_.Basic_unit AS basicUnit,\n" +
//                "mdmateriel0_.Basic_unit_accuracy AS basicUnitAccuracy,\n" +
//                "mdmateriel0_.batch_code AS batchCode,\n" +
//                "mdmateriel0_.Batch_rule AS batchRule,\n" +
//                "mdmateriel0_.brand AS brand,\n" +
//                "mdmateriel0_.cfca01_date AS cfca01Date,\n" +
//                "mdmateriel0_.cfca01_filecode AS cfca01Filecode,\n" +
//                "mdmateriel0_.cfca02_date AS cfca02Date,\n" +
//                "mdmateriel0_.cfca02_filecode AS cfca02Filecode,\n" +
//                "mdmateriel0_.cfca03_filecode AS cfca03Filecode,\n" +
//                "mdmateriel0_.cfca04_filecode AS cfca04Filecode,\n" +
//                "mdmateriel0_.cfca05_filecode AS cfca05Filecode,\n" +
//                "mdmateriel0_.cfca06_filecode AS cfca06Filecode,\n" +
//                "mdmateriel0_.Corporate_domicile AS corporateDomicile,\n" +
//                "mdmateriel0_.create_date AS createDate,\n" +
//                "mdmateriel0_.create_ren AS createRen,\n" +
//                "mdmateriel0_.describe1 AS describe1,\n" +
//                "mdmateriel0_.describe2 AS describe2,\n" +
//                "mdmateriel0_.edit_date AS editDate,\n" +
//                "mdmateriel0_.edit_ren AS editRen,\n" +
//                "mdmateriel0_.ingredient AS ingredient,\n" +
//                "mdmateriel0_.inventory_location AS inventoryLocation,\n" +
//                "mdmateriel0_.Label_info AS labelInfo,\n" +
//                "mdmateriel0_.lessen_filecode AS lessenFilecode,\n" +
//                "mdmateriel0_.mat_code AS matCode,\n" +
//                "mdmateriel0_.mat_name AS matName,\n" +
//                "mdmateriel0_.mat_type AS matType,\n" +
//                "mdmateriel0_.mat_type1 AS matType1,\n" +
//                "mdmateriel0_.mat_type2 AS matType2,\n" +
//                "mdmateriel0_.mat_type3 AS matType3,\n" +
//                "mdmateriel0_.materiel_name AS materielName,\n" +
//                "mdmateriel0_.md_wms_mi_id AS mdWmsMiId,\n" +
//                "mdmateriel0_.mdp_id AS mdpId,\n" +
//                "mdmateriel0_.mdps_id AS mdpsId,\n" +
//                "mdmateriel0_.mmf_code AS mmfCode,\n" +
//                "mdmateriel0_.mmf_name AS mmfName,\n" +
//                "mdmateriel0_.mmf_state AS mmfState,\n" +
//                "mdmateriel0_.money1 AS money1,\n" +
//                "mdmateriel0_.money2 AS money2,\n" +
//                "mdmateriel0_.money3 AS money3,\n" +
//                "mdmateriel0_.money4 AS money4,\n" +
//                "mdmateriel0_.money5 AS money5,\n" +
//                "mdmateriel0_.NORM AS norm,\n" +
//                "mdmateriel0_.number1 AS number1,\n" +
//                "mdmateriel0_.number2 AS number2,\n" +
//                "mdmateriel0_.number3 AS number3,\n" +
//                "mdmateriel0_.number4 AS number4,\n" +
//                "mdmateriel0_.number5 AS number5,\n" +
//                "mdmateriel0_.Phone_number AS phoneNumber,\n" +
//                "mdmateriel0_.price AS price,\n" +
//                "mdmateriel0_.product_factory AS productFactory,\n" +
//                "mdmateriel0_.product_name AS productName,\n" +
//                "mdmateriel0_.product_use AS productUse,\n" +
//                "mdmateriel0_.purchase_type AS purchaseType,\n" +
//                "mdmateriel0_.py_name AS pyName,\n" +
//                "mdmateriel0_.remark AS remark,\n" +
//                "mdmateriel0_.retail_price AS retailPrice,\n" +
//                "mdmateriel0_.scope_business AS scopeBusiness,\n" +
//                "mdmateriel0_.Serial_number AS serialNumber,\n" +
//                "mdmateriel0_.split_unit AS splitUnit,\n" +
//                "mdmateriel0_.standard AS standard,\n" +
//                "mdmateriel0_.state AS state,\n" +
//                "mdmateriel0_.type_name AS typeName,\n" +
//                "mdmateriel0_.VALID_PERIOD AS validPeriod,\n" +
//                "mdmateriel0_.valied_date AS valiedDate,\n" +
//                "mdmateriel0_.wms_mi_id AS wmsMiId,\n" +
//                "mdmateriel0_.wz_id AS wzId,\n" +
//                "mdmateriel0_.wz_state AS wzState,\n" +
//                "mdmateriel0_.xid AS xid,\n" +
//                "( SELECT a.root_path FROM sys_file_info a WHERE a.file_code = mdmateriel0_.lessen_filecode ) AS lessenFilePath,\n" +
//                "( SELECT SUM( e.QUANTITY ) FROM md_inventory_extend_view e WHERE e.mmf_id = mdmateriel0_.mmf_id ) AS quantity,\n" +
//                "( SELECT SUM( e.base_number - ( e.QUANTITY * e.ratio ) ) FROM md_inventory_extend_view e WHERE e.mmf_id = mdmateriel0_.mmf_id ) AS splitqQuantitySum\n" +
//                "FROM\n" +
//                "\tmd_materiel_format_view mdmateriel0_ LEFT JOIN md_carts mdcarts1_ ON mdmateriel0_.mmf_id= mdcarts1_.mmf_id\n" +
//                "WHERE\n" +
//                "\t1 = 1 ";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().trim().equals(""))
//            hql += " and mdmateriel0_.purchase_type='" + mdMaterielFormatView.getPurchaseType().trim() + "'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getWzId() != null)
//            hql += " and mdmateriel0_.wz_id=" + mdMaterielFormatView.getWzId();
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatCode() != null && !mdMaterielFormatView.getMatCode().trim().equals(""))
//            hql += " and mdmateriel0_.mat_code like '%" + mdMaterielFormatView.getMatCode().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getApplicantName() != null && !mdMaterielFormatView.getApplicantName().trim().equals(""))
//            hql += " and mdmateriel0_.applicant_Name like '%" + mdMaterielFormatView.getApplicantName().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
//            hql += " and mdmateriel0_.mat_name like '%" + mdMaterielFormatView.getMatName().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
//            hql += " and mdmateriel0_.mmf_name like '%" + mdMaterielFormatView.getMmfName().trim() + "%'";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfIds() != null && !mdMaterielFormatView.getMmfIds().trim().equals(""))
//            hql += " and mdmateriel0_.mmf_id in (" + mdMaterielFormatView.getMmfIds().trim() + ")";
//        if (mdMaterielFormatView != null && mdMaterielFormatView.getTypeName() != null && !mdMaterielFormatView.getTypeName().trim().equals(""))
//            hql += " and mdmateriel0_.type_name like '%" + mdMaterielFormatView.getTypeName().trim() + "%'";
//        hql+=" and mdcarts1_.sui_id ="+mdMaterielFormatView.getSuiId();
//
//        return list;
//    }

    @Override
    public void updateMdMaterielInfoApplicantName(String applicantName, Integer wzId)
            throws HSKDBException {
        String sql = "update md_materiel_info set applicant_Name='" + applicantName + "' where wz_id=" + wzId + " and purchase_type='1'";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<Map<String, Object>> getMatNameList(String matName)
            throws HSKDBException {
        String sql = "SELECT mat_name FROM md_materiel_info WHERE purchase_type=1 AND state IN (1,2) and mat_name like '%" + matName.trim() + "%' GROUP BY mat_name";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public void updateMatWzState(Integer wzId, String wzState)
            throws HSKDBException {
        String sql = "update md_materiel_info set wz_state='" + wzState + "' where purchase_type=1 and wz_id='" + wzId + "'";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public PagerModel getPagerModelMdMaterielLog(Integer wmsMiId) throws HSKDBException {
        String hql = "from MdMaterielPartDetailLogEntity where 1=1";
        if (wmsMiId != null) {
            hql += " and wmsMiId = " + wmsMiId;
        }
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public String getLogoPath(Integer wmsMiId, String lessonFileCode) throws HSKDBException {
        String sql = "select root_path from sys_file_info where 1=1";
        if (lessonFileCode != null && !lessonFileCode.equals(""))
            sql += " and file_code = '" + lessonFileCode + "'";
        if (wmsMiId != null)
            sql += " and file_code = (select b.lessen_filecode from md_materiel_info b where b.wms_mi_id = " + wmsMiId + ")";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return "";
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return "";
        return map.get("root_path");
    }

    @Override
    public MdMaterielInfo findMdMaterielInfo(MdMaterielInfo mdMaterielInfo) throws HSKDBException {
        String hql = getHql(mdMaterielInfo);
        List<MdMaterielInfo> list = this.getHibernateTemplate().find(hql);
        if (list == null || list.isEmpty())
            return null;

        return list.get(0);
    }

    @Override
    public Map<String, Object> getPriceAdjustmentCount(Integer paiId) throws HSKDBException {
        String hql = "select count(a.pai_id) as count, avg(b.percent) as percent from md_inventory_price_adjustment a left join md_inventory_price_adjustment_ex b on a.pai_id = b.pai_id where 1=1";
        if (paiId != null)
            hql += " and a.pai_id = " + paiId;
        hql += " GROUP BY a.pai_id";
        List<Map<String, Object>> list = this.getJdbcDao().query(hql);
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public List<Map<String, Object>> getLessenFilePath(String wmsMiIds, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
        String sql = "select a.root_path as lessenFilePath, b.wms_mi_id as wmsMiId from sys_file_info a left join md_materiel_info as b on a.file_code = b.lessen_filecode where 1=1";
        if (wmsMiIds != null && !wmsMiIds.equals(""))
            sql += " and b.wms_mi_id in (" + wmsMiIds + ")";
//        if (purchaseType != null && !purchaseType.equals(""))
//            sql += " and b.purchase_type = '" + purchaseType + "'";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer getMatCodeCount(String matCode, Integer wmsMiId) throws HSKDBException {
        if (matCode == null || matCode.equals(""))
            return -1;
        String sql = "select mat_code from md_materiel_info where mat_code = '" + matCode + "'";
        if (wmsMiId != null)
            sql += " and wms_mi_id <> " + wmsMiId;
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public String getMdMaterielAliasName(Integer wmsMiId) throws HSKDBException {
        String sql = "select alias_name as aliasName from md_materiel_info where wms_mi_id = " + wmsMiId;
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return null;
        if (map.get("aliasName") == null)
            return null;
        return map.get("aliasName").toString();
    }
    //通过编号查询型号编号
    @Override
    public String getMdMaterielformat(String  matCode) throws HSKDBException{
        String sql = "SELECT group_concat(DISTINCT(t1.wms_mi_id)) as wmsMiId FROM md_materiel_format t1 WHERE 1=1 AND t1.mmf_code LIKE '%"+matCode+"%'" ;
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return null;
        if (map.get("wmsMiId") == null)
            return null;
        return map.get("wmsMiId").toString();
    }

    @Override
    public Integer getOutWarehouseRef(Integer wmsMiId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "select wow_id from md_out_warehouse where 1=1";
        if (rbaId != null)
            sql += " and rba_id = " + rbaId;
        if (rbsId != null)
            sql += " and rbs_id = " + rbsId;
        if (rbbId != null)
            sql += " and rbb_id = " + rbbId;
        if (wmsMiId != null)
            sql += " and wow_id in (select wow_id from md_out_warehouse_mx where wms_mi_id = " + wmsMiId + ")";
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        return 1;
    }

    @Override
    public Integer getMdMaterielInfoCountByCHH(Integer wzId) throws HSKDBException {
        String sql = "select count(*) as count from md_materiel_info where 1=1";
        if (wzId != null) {
            sql += " and wz_id = " + wzId;
        }
        sql += " and purchase_type <> '1'";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        if (map.get("count") == null || map.get("count").toString().equals(""))
            return 0;
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public PagerModel getPagerModelByMdMaterielInfoBySomeCase(MdMaterielInfo mdMaterielInfo, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String hql = "select a.wms_mi_id as wmsMiId, a.mat_code as matCode, a.mat_name as matName, a.norm as norm, a.basic_unit as basicUnit, b.mmf_id as mmfId" +
                " from md_materiel_info a" +
                " , md_materiel_format b  " +
                " where 1=1";
        hql += " and b.wms_mi_id = a.wms_mi_id";

        if (mdMaterielInfo.getMatCode() != null && !mdMaterielInfo.getMatCode().equals(""))
            hql += " and (upper(a.mat_code) like '%" + mdMaterielInfo.getMatCode().toUpperCase() + "%'";
        else
            hql += " and (1=1";
        if (mdMaterielInfo.getSearchName() != null && !mdMaterielInfo.getSearchName().equals("")) {
            String h = " upper(a.mat_name) like  '%" + mdMaterielInfo.getSearchName().toUpperCase() + "%'" +
                    " or upper(a.py_name) like  '%" + mdMaterielInfo.getSearchName().toUpperCase() + "%'";
            if (mdMaterielInfo.getMatCode() != null && !mdMaterielInfo.getMatCode().equals(""))
                hql += " or " + h;
            else
                hql += " and " + h ;
        }
        hql += ")";

        if (mdMaterielInfo.getPurchaseType() != null && !mdMaterielInfo.getPurchaseType().equals(""))
            hql += " and a.purchase_type = '" + mdMaterielInfo.getPurchaseType() + "'";
        if (mdMaterielInfo.getWzId() != null)
            hql += " and a.wz_id = " + mdMaterielInfo.getWzId();

        if (mdMaterielInfo.getMdpId() != null)
            hql += " and a.mdp_id = " + mdMaterielInfo.getMdpId();
        if (mdMaterielInfo.getMdpsId() != null)
            hql += " and a.mdps_id = " + mdMaterielInfo.getMdpsId();
        if (mdMaterielInfo.getWmsMiId() != null)
            hql += " and a.wms_mi_id = " + mdMaterielInfo.getWmsMiId();
        List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + hql + ") as t");
        hql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
        List<Map<String, Object>> list = this.getJdbcDao().query(hql);
        PagerModel pm = new PagerModel();
        pm.setRows(list);
        pm.setItems(list);

        Integer count = 0;
        if (cList == null || cList.isEmpty())
            count = 0;
        Map<String, Object> map = cList.get(0);
        if (map == null || map.isEmpty())
            count = 0;
        else
            count = Integer.parseInt(map.get("totalCount").toString());
        pm.setTotalCount(count);
        pm.setTotal(count);
        return pm;
    }

    @Override
    public List<Map<String, Object>> getInventoryQuantity(String mmfIds, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "select a.quantity as quantity, a.base_number as baseNumber, b.mmf_id as mmfId from md_inventory a left join md_item_mx b on a.ITEM_KEY_ID = b.ITEM_KEY_ID where 1=1";
        if (rbaId != null)
            sql += " and a.rba_id = " + rbaId;
        if (rbsId != null)
            sql += " and a.rbs_id = " + rbsId;
        if (rbbId != null)
            sql += " and a.rbb_id = " + rbbId;
        if (mmfIds != null && !mmfIds.equals(""))
            sql += " and b.mmf_id in (" + mmfIds + ")";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer getMatNameCount(String matName, Integer wmsMiId, String purchaseType, Integer wzId) throws HSKDBException {
        if (matName == null || matName.equals(""))
            return -1;
        String sql = "select mat_name from md_materiel_info where mat_name = '" + matName + "'";
        if (wmsMiId != null)
            sql += " and wms_mi_id <> " + wmsMiId;
        if (purchaseType != null && !purchaseType.equals(""))
            sql += " and purchase_type = '" + purchaseType + "'";
        if (wzId != null)
            sql += " and wz_id = " + wzId;
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }
}