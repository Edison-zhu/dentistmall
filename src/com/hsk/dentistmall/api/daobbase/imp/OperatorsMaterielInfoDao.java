package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.daobbase.IOperatorsMaterielInfoDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * md_materiel_info表数据库层面操作实现类
 *
 * @author 作者:yanglei
 * @version 版本信息:v1.0   创建时间: 2020-07-29 15:30:22
 */
@Component
public class OperatorsMaterielInfoDao extends SupperDao implements IOperatorsMaterielInfoDao {

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
                sbuffer.append(" and (matName  like '%" + att_MdMaterielInfo.getMatName().trim() + "%' or pyName like '%" + att_MdMaterielInfo.getMatName().trim().toUpperCase() + "%' or matCode like '%" + att_MdMaterielInfo.getMatName().trim().toUpperCase() + "%'  or serialNumber like  '%"+att_MdMaterielInfo.getMatName().trim()+"%')");
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
            } else if (att_MdMaterielInfo.getState().indexOf(",") >= 0) {
                sbuffer.append(" and state in (" + att_MdMaterielInfo.getState() + ")");
            } else {
                sbuffer.append(" and state   ='" + att_MdMaterielInfo.getState() + "'");
            }
        }
        //品牌
        if (att_MdMaterielInfo.getBrand() != null &&
                !"".equals(att_MdMaterielInfo.getBrand().trim())) {
            if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("brand") != -1) {
                sbuffer.append(" and brand  like '%" + att_MdMaterielInfo.getBrand() + "%'");
            } else {
                String[] brandArr = att_MdMaterielInfo.getBrand().split(",");
                String brands="";
                for (int i = 0; i < brandArr.length; i++) {
//                    if (i==0){
//                        brands+="'"
//                    }
                    brands+="'"+brandArr[i]+"',";
                }
                brands= brands.substring(0,brands.length()-1);
                sbuffer.append(" and brand  in (" + brands + ")");
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
    public Integer getMdMaterielInfoCount(Integer state) throws HSKDBException{
        String sql = "SELECT COUNT(t1.wms_mi_id) as count FROM md_materiel_info t1 WHERE 1 = 1 ";
        if (state!=null){
            sql+=" AND t1.state="+state;
        }else {
//            sql+=" AND t1.state=t1.state";
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("count").toString());
        }
        return 0;
    }

    public List<Map<String, Object>> getMdmaterielFormatList(Integer wmsmiId,String mmfCode,Integer limit,Integer page) throws HSKDBException{
        String sql=" SELECT t1.mmf_code as mmfCode,t1.mmf_name as mmfName,t1.price,IFNULL((SELECT SUM( t2.QUANTITY) FROM md_inventory_extend t2 WHERE t2.mmf_id=t1.mmf_id),0) AS quantity FROM md_materiel_format t1 WHERE 1 = 1 \n";
        if (wmsmiId!=null){
            sql+=" AND t1.wms_mi_id="+wmsmiId;
        }
        if (mmfCode!=null&&!mmfCode.equals("")){
            sql+=" AND t1.mmf_code like '%"+mmfCode+"%'";
        }
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> getBrandList(String brand) throws HSKDBException{
        String sql=" SELECT DISTINCT t1.mbd_name AS brand, t1.mbd_id as mbdId FROM  md_materiel_brand t1 WHERE 1=1 AND t1.mbd_name is not null  AND t1.mbd_name!='' ";
        if (brand!=null&&!brand.equals("")){
            sql+=" AND t1.mbd_name like '%"+brand+"%'";
        }
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> getApplicantNameList(String applicantName) throws HSKDBException{
        String sql=" SELECT DISTINCT t1.applicant_Name as applicantName  FROM  md_materiel_info t1 WHERE 1=1 AND t1.applicant_Name is not null  AND t1.applicant_Name!='' ";
        if (applicantName!=null&&!applicantName.equals("")){
            sql+=" AND t1.applicant_Name like '%"+applicantName+"%'";
        }
        return this.getJdbcDao().query(sql);
    }
    //查询日志列表
    public List<Map<String, Object>> getMdMaterielInfoLog(Integer wmsmiId,Integer limit,Integer page) throws HSKDBException{
        String sql=" SELECT t1.create_date AS createDate,t1.create_ren as createRen,t1.log,t1.log_name AS logName from md_materiel_part_detail_log t1 WHERE 1=1";
        if (wmsmiId!=null){
            sql+=" AND t1.wms_mi_id="+wmsmiId;
        }else {
            sql+=" AND t1.wms_mi_id=''";
        }
        sql+=" order by t1.create_date desc";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    //查询品牌管理
    public List<Map<String, Object>> getMdMaterielBrandInfo(String mbdName,Integer state,Integer limit,Integer page) throws HSKDBException{
        String sql=" SELECT t1.mbd_id AS mbaCode,t1.mbd_name AS mbdName,t1.mbd_manufacturer AS mbdManufacturer,t1.state AS state,t1.create_ren AS createRen,t1.create_date AS createDate,t1.lessen_filecode AS lessenFilecode,t1.filecode AS filecode,t1.describes AS describes FROM md_materiel_brand t1 LEFT JOIN md_materiel_info t2  ON t1.mbd_id = t2.mbd_id WHERE 1=1 ";
        if (mbdName!=null&&!mbdName.equals("")){
            sql+=" AND t1.mbd_name like '%"+mbdName+"%'";
        }
        if (state!=null){
            if(state==1){
                sql+=" AND (t1.state='1' OR t1.state is null)";
            }else if (state==2){
                sql+=" AND t1.state='2' ";
            }else {
            }
        }
        sql+=" GROUP BY t1.mbd_id order by t1.mbd_id desc";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    public	Integer getMdMaterielState(Integer mbdId) throws HSKDBException{
        String sql = "SELECT COUNT(t1.wms_mi_id) as countState FROM md_materiel_info t1 WHERE 1 = 1 ";
        if (mbdId!=null){
            sql+=" AND t1.mbd_id="+mbdId;
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("countState").toString());
        }
        return 0;
    }
    public MdMaterielBrand findMdMaterielBrandInfoById(Integer mbdId) throws HSKDBException{
        MdMaterielBrand mdMaterielBrand = new MdMaterielBrand();
        if (mbdId != null) {
            mdMaterielBrand.setMbdId(mbdId);
            Object obj = this.getOne(mdMaterielBrand);
            if (obj != null) {
                mdMaterielBrand = (MdMaterielBrand) obj;
            }
        }
        return mdMaterielBrand;
    }

    public  MdMaterielBrand updateMaterielBrandInfoById(Integer mbdId, MdMaterielBrand mdMaterielBrand) throws HSKDBException{
        if (mbdId != null) {
            mdMaterielBrand.setMbdId(mbdId);
            Object obj = this.getOne(mdMaterielBrand);
            if (obj != null) {
                this.updateObject(obj);
            }
        }
        return mdMaterielBrand;
    }
    public void deleteMaterielBrandInfoById(Integer mbdId) throws HSKDBException{
        MdMaterielBrand mdMaterielBrand = new MdMaterielBrand();
            if (mbdId != null) {
                mdMaterielBrand.setMbdId(mbdId);
            Object obj = this.getOne(mdMaterielBrand);
            if (obj != null) {
                this.deleteObject(obj);
            }
        }
    }
    public List<Map<String, Object>> getMdMaterielParameter(String mmpName,String isRequired,String state,Integer limit,Integer page) throws HSKDBException{
        String sql=" SELECT t1.mmp_id AS mmpCode,t1.mmp_name AS mmpName,t1.inputMode,t1.optiona_value AS optionaValue,t1.isRequired,t1.state,t1.createRen,t1.createDate,t1.editRen,t1.editDate  FROM md_materiel_parameter t1 WHERE 1=1  ";
        if (mmpName!=null&&!mmpName.equals("")){
            sql+=" AND t1.mmp_name like'%"+mmpName+"%'";
        }
        if (isRequired!=null && !isRequired.equals("")){
            if (isRequired.equals("1")){
                sql+=" AND t1.isRequired ="+isRequired;
            }else {
                sql+="AND (t1.isRequired != 1 OR t1.isRequired is NULL)";
            }
        }
        if (state!=null&&!state.equals("")){
            sql+=" AND t1.state ="+state;
        }
        sql+=" order by CAST(t1.mmp_sort as SIGNED) DESC ,t1.mmp_id DESC ";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    //通过参数Id查询
    public MdMaterielParameter findMdMaterielParameterById(Integer mmpId) throws HSKDBException{
        MdMaterielParameter mdMaterielParameter=new MdMaterielParameter();
        if (mmpId != null) {
            mdMaterielParameter.setMmpId(mmpId);
            Object obj = this.getOne(mdMaterielParameter);
            if (obj != null) {
                mdMaterielParameter = (MdMaterielParameter) obj;
            }
        }
        return mdMaterielParameter;
    }

    @Override
    public List<MdMaterielParameter> findMdMaterielParameterByIds(String mmpIds) throws HSKDBException {
        String hql = "from MdMaterielParameter where 1=1";
        if (mmpIds != null && !mmpIds.equals(""))
            hql += " and mmpId in (" + mmpIds + ")";
        return this.getHibernateTemplate().find(hql);
    }

    public  MdMaterielParameter updateMdMaterielParameterById(Integer mmpId, MdMaterielParameter mdMaterielParameter) throws HSKDBException{
        if (mmpId != null) {
            mdMaterielParameter.setMmpId(mmpId);
            Object obj = this.getOne(mdMaterielParameter);
            if (obj != null) {
                this.updateObject(obj);
            }
        }
        return mdMaterielParameter;
    }
    public void deleteMdMaterielParameterById(String mmpId) throws HSKDBException{
        if (mmpId == null || mmpId.equals(""))
            return;
        String sql = "delete from md_materiel_parameter where mmp_id in (" + mmpId + ")";
        this.getJdbcDao().execute(sql);
//        MdMaterielParameter mdMaterielParameter = new MdMaterielParameter();
//        if (mmpId != null) {
//            mdMaterielParameter.setMmpId(mmpId);
//            Object obj = this.getOne(mdMaterielParameter);
//            if (obj != null) {
//                this.deleteObject(obj);
//            }
//        }
    }
    //查询属性列表
    public List<Map<String, java.lang.Object>> getmdMaterielAttributeInfo(String mmaName, String state, String isOptional, Integer limit, Integer page) throws HSKDBException{
        String sql=" SELECT t1.mma_id AS mmaCode,t1.mma_name AS mmaName,t1.isOptional,t1.input_model AS inputModel,t1.optional_value AS optionalValue,t1.state,t1.createRen,t1.createDate,t1.editRen,t1.editDate FROM md_materiel_attribute t1 WHERE 1=1";
        if (mmaName!=null&&!mmaName.equals("")){
            sql+=" AND t1.mma_name like '%"+mmaName+"%'";
        }
        if (isOptional!=null&&!isOptional.equals("")){
            sql+=" AND t1.isOptional= '"+isOptional+"'";
        }
        if (state!=null&&!state.equals("")){
            if(state.equals("1")){
                sql+=" AND t1.state='1'";
            }else if (state.equals("2")){
                sql+=" AND (t1.state is NULL or t1.state <> '1')";
            }else {
            }
        }
        sql+=" order by CAST(t1.mma_sort as SIGNED) DESC ,t1.mma_id DESC ";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer getMdMaterielParameterRefCountOne(String mmpId) throws HSKDBException {
        String hql = "from MdModelParameterInfoEntity where 1=1";
        if (mmpId != null&&!mmpId.equals(""))
            hql += " and mmpId in (" + mmpId + ")";
        List<MdModelParameterInfoEntity> list = this.getHibernateTemplate().find(hql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }
    public List<Map<String, Object>> getmdMaterielAttributeMxInfo(Integer mmaId,String mmamxName,String state,String isOptional,Integer limit,Integer page) throws HSKDBException{
        String sql=" \tSELECT t1.mmamx_id AS mmamxCode,t1.mma_id AS mmaId,t1.mmamx_name AS mmamxName,t1.attribute,t1.iconCode,t1.state,t1.createRen,t1.createDate,t1.editRen,t1.editDate FROM md_materiel_attribute_mx t1 LEFT JOIN md_materiel_attribute t2 ON t1.mma_id=t2.mma_id WHERE 1=1";
//        if (mmaId!=null){
//            sql+=" AND t1.mma_id= "+mmaId;
//        }
        if (mmamxName!=null&&!mmamxName.equals("")){
            sql+=" AND (t1.mmamx_name like '%"+mmamxName+"%' or t2.mma_name like '%"+mmamxName+"%') ";
        }
        if (isOptional!=null&&!isOptional.equals("")){
            sql+=" AND t2.isOptional= '"+isOptional+"'";
        }
        if (state!=null&&!state.equals("")){
//            sql+=" AND t1.state= '"+state+"'";
            if(state.equals("1")){
                sql+=" AND t1.state='1'";
            }else if (state.equals("2")){
                sql+=" AND (t1.state is NULL or t1.state <> '1')";
            }else {
            }
        }
        sql+="  order by t1.mmamx_id DESC";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }



    //通过品牌名称查询
    public Integer findMdMaterielBrandInfoByName(String  mbdName) throws HSKDBException{
        String sql = "SELECT * FROM md_materiel_brand t1 WHERE t1.mbd_name='"+mbdName+"'";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }
    //通过属性Id查询
    public mdMaterielAttribute findMdMaterielAttributeById(Integer mmaId) throws HSKDBException{
        mdMaterielAttribute att_mdMaterielAttribute=new mdMaterielAttribute();
        if (mmaId != null) {
            att_mdMaterielAttribute.setMmaId(mmaId);
            Object obj = this.getOne(att_mdMaterielAttribute);
            if (obj != null) {
                att_mdMaterielAttribute = (mdMaterielAttribute) obj;
            }
        }
        return att_mdMaterielAttribute;
    }

    public  mdMaterielAttribute updateMdMaterielAttributeById(Integer mmaId, mdMaterielAttribute att_mdMaterielAttribute) throws HSKDBException{
        if (mmaId != null) {
            att_mdMaterielAttribute.setMmaId(mmaId);
            Object obj = this.getOne(att_mdMaterielAttribute);
            if (obj != null) {
                this.updateObject(obj);
            }
        }
        return att_mdMaterielAttribute;
    }

    //通过属性ID 删除
    public void deleteMdMaterielAttributerById(Integer mmaId) throws HSKDBException{
        mdMaterielAttribute att_mdMaterielParameter = new mdMaterielAttribute();
        if (mmaId != null) {
            att_mdMaterielParameter.setMmaId(mmaId);
            Object obj = this.getOne(att_mdMaterielParameter);
            if (obj != null) {
                this.deleteObject(obj);
            }
        }
    }

    //通过属性Id查询
    public mdMaterielAttributeMx findMdMaterielAttributeMxById(Integer mmamxId) throws HSKDBException{
        mdMaterielAttributeMx att_mdMaterielAttributeMx=new mdMaterielAttributeMx();
        if (mmamxId != null) {
            att_mdMaterielAttributeMx.setMmamxId(mmamxId);
            Object obj = this.getOne(att_mdMaterielAttributeMx);
            if (obj != null) {
                att_mdMaterielAttributeMx = (mdMaterielAttributeMx) obj;
            }
        }
        return att_mdMaterielAttributeMx;
    }
    //通过属性ID修改
    public  mdMaterielAttributeMx updatemdMaterielAttributeMxById(Integer mmamxId, mdMaterielAttributeMx att_mdMaterielAttributeMx) throws HSKDBException{
        if (mmamxId != null) {
            att_mdMaterielAttributeMx.setMmamxId(mmamxId);
            Object obj = this.getOne(att_mdMaterielAttributeMx);
            if (obj != null) {
                this.updateObject(obj);
            }
        }
        return att_mdMaterielAttributeMx;
    }
    //通过属性ID 删除
    public void deleteMdMaterielAttributeMxById(Integer mmamxId) throws HSKDBException{
        mdMaterielAttributeMx att_mdMaterielAttributeMx=new mdMaterielAttributeMx();
        if (mmamxId != null) {
            att_mdMaterielAttributeMx.setMmamxId(mmamxId);
            Object obj = this.getOne(att_mdMaterielAttributeMx);
            if (obj != null) {
                this.deleteObject(obj);
            }
        }
    }
    public String getMmaName(Integer mmaId) throws HSKDBException{
        String sql = "SELECT group_concat(mmamx_name) AS mmaName FROM md_materiel_attribute_mx WHERE 1=1 ";
        if (mmaId!=null){
            sql+=" AND mma_id="+mmaId;
        }else {
            sql+=" AND mma_id=''";
        }
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list.size() <= 0)
            return null;
        Map<Object, Object> map = list.get(0);
        if (map == null){
            return null;
        }
        if (map.get("mmaName")==null){
            return null;
        }
        return map.get("mmaName").toString();
    }

    @Override
    public Integer getMdMaterielAttrRefCount(String mmaIds) throws HSKDBException {
        String sql = "select * from md_materiel_attribute where 1=1";
        if (mmaIds != null && !mmaIds.equals(""))
            sql += " and mma_id in (" + mmaIds + ") " +
                    " and mma_id in (select mma_id from md_model_format_attr_info)";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public Integer getMdMaterielAttrMxRefCount(Integer mmamxId) throws HSKDBException {
        String sql = "select * from md_model_format_attr_info where 1=1";
        if (mmamxId != null)
            sql += " and mmamx_id in (" + mmamxId + ")";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public Integer getMdMaterielBrandRefCount(Integer mbdId) throws HSKDBException {
        String sql = "from MdModelBrandInfoEntity where 1=1";
        if (mbdId != null)
            sql += " and mbdId = " + mbdId;
        List<MdModelBrandInfoEntity> list = this.getHibernateTemplate().find(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }

    //通过参数名称查询
    public Integer findMdMaterielParameterInfoByName(String  mmpName) throws HSKDBException{
        String sql = "SELECT * FROM md_materiel_parameter t1 WHERE t1.mmp_name='"+mmpName+"'";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }
    //通过属性名称查询
    public Integer findMdMaterielAttributeInfoByName(String  mmaName,Integer mmaId) throws HSKDBException{
        String sql = "SELECT * FROM md_materiel_attribute t1 WHERE t1.mma_name='"+mmaName+"'";
        if (mmaId!=null){
            sql+=" AND t1.mma_id!="+mmaId;
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }
}