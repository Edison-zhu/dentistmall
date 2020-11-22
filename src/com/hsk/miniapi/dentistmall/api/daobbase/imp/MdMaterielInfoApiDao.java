package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/**
 * md_materiel_info表数据库层面操作实现类
 *
 * @author 作者:admin
 * @version 版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
@Component
public class MdMaterielInfoApiDao extends SupperDao implements IMdMaterielInfoApiDao {

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
        return this.getHibernateDao().findByPage(Hql);
    }

    /**
     * 根据MdMaterielInfo对象获取Hql字符串
     *
     * @param att_MdMaterielInfo MdMaterielInfo类型(md_materiel_info表记录)
     * @return hql字符串
     */
    private String getHql(MdMaterielInfo att_MdMaterielInfo) {
        StringBuffer sbuffer = new StringBuffer(" from  MdMaterielInfo  where  1=1  ");
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
                sbuffer.append(" and matCode  like '%" + att_MdMaterielInfo.getMatCode() + "%'");
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
        //*****************数据库字段处理end***************
        if (orderStr != null && !"".equals(orderStr.trim())) {
            sbuffer.append(" order by " + orderStr);
        }
						 /*
						 else {
				 			  sbuffer.append( " order by  WmsMiId   desc " );
					      }
					      */
        if (att_MdMaterielInfo.getBrand() != null && !att_MdMaterielInfo.getBrand().equals("")) {
            sbuffer.append(" and brand = '" + att_MdMaterielInfo.getBrand() +"'");
        }
        return sbuffer.toString();
    }

    @Override
    public PagerModel getPagerModelByMdMdMaterielFormatView(MdMaterielFormatView mdMaterielFormatView) throws HSKDBException {
        String hql = "from MdMaterielFormatView where 1=1";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().trim().equals(""))
            hql += " and purchaseType='" + mdMaterielFormatView.getPurchaseType().trim() + "'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getWzId() != null)
            hql += " and wzId=" + mdMaterielFormatView.getWzId();
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatCode() != null && !mdMaterielFormatView.getMatCode().trim().equals(""))
            hql += " and matCode like '%" + mdMaterielFormatView.getMatCode().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
            hql += " and matName like '%" + mdMaterielFormatView.getMatName().trim() + "%'";
        if (mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
            hql += " and mmfName like '%" + mdMaterielFormatView.getMmfName().trim() + "%'";
        return this.getHibernateDao().findByPage(hql);
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
                    + "%') or aliasName like '%" + serachName.trim()
                    + "%' or applicantName like '%" + serachName.trim() + "%')");
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
                        + "%') or aliasName like '%" + temp.trim()
                        + "%' or applicantName like '%" + serachName.trim() + "%')");
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
                        + "%' or wmsMiId in (select wmsMiId from MdMaterielFormat where upper(mmfCode) like '%" + tempStr.trim().toUpperCase()
                        + "%') or aliasName like '%" + tempStr.trim()
                        + "%' or applicantName like '%" + serachName.trim() + "%')");
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
        hql.append(" order by " + orderStr);
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
    public PagerModel getHotMaterielInfo(Integer page, Integer limit) throws HSKDBException {
        String sql = "SELECT a.wms_mi_id as wmsMiId, c.root_path AS 'lessFilePath' " +
                " FROM md_materiel_info a LEFT JOIN sys_file_info c ON a.lessen_filecode=c.file_code ";
        sql += " where a.wz_state = 1 and a.state = 1";
        sql += " order by a.number1 desc";
        if (page != null && limit != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().findByPage(sql);
    }

    @Override
    public List<Map<String, Object>> getHotMaterielInfoDetail(Integer page, Integer limit, String excludeIds) throws HSKDBException {
        String sql = "SELECT a.*, (select c.root_path from sys_file_info c where  a.lessen_filecode = c.file_code ) as 'lessFilePath' " +
                " FROM md_materiel_info a ";
        sql += " where a.wz_state = 1 and a.state = 1";
        if (excludeIds != null && !excludeIds.equals("")) {
            excludeIds = excludeIds.substring(0, excludeIds.length() - 1);
            sql += " and a.wms_mi_id IN ( select b.wms_mi_id from md_materiel_format b where b.mmf_id not in (" + excludeIds + "))";
        }
        sql += " order by a.number1 desc";
        if (page != null && limit != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getMaterielInfoByHomeType(Integer index, Integer limit, Integer page) throws HSKDBException {
        String sql = "SELECT a.*, (select c.root_path from sys_file_info c where  a.lessen_filecode = c.file_code ) as 'lessFilePath' " +
                " FROM md_materiel_info a  ";
        sql += " where a.wz_state = 1 and a.state = 1";
        switch (index) {
            case 0: // 热门商品
                sql += " order by a.number1 desc";
                break;
            case 1: // 最新上架
                sql += " order by a.wms_mi_id desc";
                break;
            case 3: // 必备
            case 4: // 爆款
                sql += " order by a.number1 desc";
                break;
            default:
                sql += " order by a.number1 desc";
                break;
        }
        if (page != null && limit != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public PagerModel getPagerModelByBrandSearchNoLogin(String searchName, String s) throws HSKDBException {
        String sql = "select distinct brand from md_materiel_info_temp where 1=1";
        if (searchName != null && !searchName.equals("")){
            sql += " and brand like '%" + searchName + "%'";
        }
        sql += " and brand is not null and brand <> '' and state = 1 and wz_state = 1 and purchase_type = 1";
        return this.getJdbcDao().findByPage(sql);
    }

    @Override
    public List<Map<String, Object>> getHotMaterielInfoDetailNoLogin(Integer page, Integer limit, String excludeIds) throws HSKDBException {
        String sql = "SELECT a.*, (select c.root_path from sys_file_info_temp c where  a.lessen_filecode = c.file_code ) as 'lessFilePath' " +
                " FROM md_materiel_info_temp a ";
        sql += " where a.wz_state = 1 and a.state = 1";
        if (excludeIds != null && !excludeIds.equals("")) {
            excludeIds = excludeIds.substring(0, excludeIds.length() - 1);
            sql += " and a.wms_mi_id IN ( select b.wms_mi_id from md_materiel_format_temp b where b.mmf_id not in (" + excludeIds + "))";
        }
        sql += " order by a.number1 desc";
        if (page != null && limit != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getMaterielInfoByHomeTypeNoLogin(Integer index, Integer limit, Integer page) throws HSKDBException {
        String sql = "SELECT a.*, (select c.root_path from sys_file_info_temp c where  a.lessen_filecode = c.file_code ) as 'lessFilePath' " +
                " FROM md_materiel_info_temp a  ";
        sql += " where a.wz_state = 1 and a.state = 1";
        switch (index) {
            case 0: // 热门商品
                sql += " order by a.number1 desc";
                break;
            case 1: // 最新上架
                sql += " order by a.wms_mi_id desc";
                break;
            case 3: // 必备
            case 4: // 爆款
                sql += " order by a.number1 desc";
                break;
            default:
                sql += " order by a.number1 desc";
                break;
        }
        if (page != null && limit != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public PagerModel getPagerModelBySearchNameNoLogin(String serachName, Integer mmtId, Integer wzId, String brand, String state, String wzState, String purchaseType, String orderStr) throws HSKDBException {
        StringBuffer hql = new StringBuffer("from MdMaterielInfoTemp where state='" + state + "' and wzState='" + wzState + "' and purchaseType='" + purchaseType + "'");
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
                    + "%' or aliasName like '%" + serachName.trim()
                    + "%' or applicantName like '%" + serachName.trim() + "%')");
            //处理字符串重有空格
            String[] spaceSplite = serachName.split(" ");
            int a = spaceSplite.length;
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
            }
            if (spaceSplite.length > 3) {
                String temp = "";
                for (int i = 2; i < spaceSplite.length; i++) {
                    temp += spaceSplite[i];
                }
                hql.append(" or (upper(matName) like '%" + temp.trim().toUpperCase()
                        + "%' or norm like '%" + temp.trim().toUpperCase()
                        + "%' or upper(pyName) like '%" + temp.trim().toUpperCase()
                        + "%' or upper(productName) like '%" + temp.trim()
                        + "%' or brand like '%" + temp.trim()
                        + "%' or aliasName like '%" + temp.trim()
                        + "%' or applicantName like '%" + serachName.trim() + "%')");
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
                        + "%' or aliasName like '%" + tempStr.trim()
                        + "%' or applicantName like '%" + serachName.trim() + "%')");
            }

            hql.append(")");
        }
        hql.append(" order by " + orderStr);
        PagerModel p = this.getHibernateDao().findByPage(hql.toString());
        return p;
    }

    @Override
    public MdMaterielInfoTemp findMdMaterielInfoByIdNoLogin(Integer wmsMiId) throws HSKDBException {
        MdMaterielInfoTemp att_MdMaterielInfo = new MdMaterielInfoTemp();
        if (wmsMiId != null) {
            att_MdMaterielInfo.setWmsMiId(wmsMiId);
            Object obj = this.getOne(att_MdMaterielInfo);
            if (obj != null) {
                att_MdMaterielInfo = (MdMaterielInfoTemp) obj;
            }
        }
        return att_MdMaterielInfo;
    }

    @Override
    public PagerModel getPagerModelByBrandSearch(String searchName, String s) throws HSKDBException {
        String sql = "select distinct brand from md_materiel_info where 1=1";
        if (searchName != null && !searchName.equals("")){
            sql += " and brand like '%" + searchName + "%'";
        }
        sql += " and brand is not null and brand <> '' and state = 1 and wz_state = 1 and purchase_type = 1";
        return this.getJdbcDao().findByPage(sql);
    }

    @Override
    public List<MdMaterielInfo> getListByMdMaterielInfoLimit(MdMaterielInfo att_mdMaterielInfo, Integer limit, Integer page) throws HSKDBException {
        String Hql = this.getHql(att_mdMaterielInfo);
//        if (limit != null && page != null) {
//            Hql += " limit " + (page - 1) * limit + "," + limit;
//        }
        PagerModel pm = this.getHibernateDao().findByPage(Hql);
        List<MdMaterielInfo> list = pm.getItems();
        return list;
    }
}