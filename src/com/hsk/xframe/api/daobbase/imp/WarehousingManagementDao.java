package com.hsk.xframe.api.daobbase.imp;

import com.hsk.dentistmall.api.persistence.MdEnterWarehouse;
import com.hsk.dentistmall.api.persistence.MdInventoryExtend;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.IWarehousingManagementDao;
import com.hsk.xframe.api.persistence.SysFeedbackEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class WarehousingManagementDao extends SupperDao implements IWarehousingManagementDao {
    public List<Map<String,Object>> getWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate,Integer desc,
                                                       Integer rbaId, Integer rbsId, Integer rbbId, Integer suiId, String purchaseType, Integer limit, Integer page,String wewId1) throws HSKDBException{
        String sql=" SELECT wew_id,Billcode,bill_type,purchase_money,warehousing_remarks,retail_money,invoice_code,create_ren,create_date FROM md_enter_warehouse where 1=1";
        if(warehousCode!=null&&warehousCode!="null"&&!warehousCode.equals("")){
             sql+=" AND Billcode like'%"+warehousCode+"%'";
        }
        if (wewId1!=null&&!wewId1.equals("")){
            sql+=" AND wew_id in("+wewId1+")";
        }
        if (remarks != null && !remarks.equals("")) {
            sql += " and wew_id in (select wew_id from md_enter_warehouse_mx where mat_name like '%" + remarks + "%'" +
                    " or norm like '%" + remarks + "%' or wms_mi_id in (select wms_mi_id from md_materiel_info where alias_name like '%" + remarks + "%'))";
        }

        if (rbaId != null) {
            sql += " and rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and rbb_id = " + rbbId;
        }
        if (suiId != null) {
            sql += " and sui_id = " + suiId;
        }
        if (purchaseType != null && !purchaseType.equals("")) {
            sql += " and purchase_type = '" + purchaseType + "'";
        }

        if(select1!=null){
            if (select1==0){
            }else if (select1==1){
                sql+=" AND bill_type=2";
            }else if(select1==2){
                sql+=" AND bill_type=1";
            }
        }
        if(operationDate!=null&&operationDate!="null"&&!operationDate.equals("")){
            String[] rangeDate = operationDate.split("~");
            String startDate = rangeDate[0].trim();
            String endDate = null;
            if (rangeDate.length > 1) {
                endDate = rangeDate[1].trim();
            }

            sql+=" AND create_date>= '"+startDate+"'";
            if (endDate != null)
                sql+=" AND create_date<= '"+endDate+"'";
        }
        if (desc != null && !desc.equals("")) {
            switch (desc) {
                case 1:
                    sql += " order by Billcode desc";
                    break;
                case 11:
                    sql += " order by Billcode asc";
                    break;
                case 2:
                    sql += " order by bill_type desc";
                    break;
                case 12:
                    sql += " order by bill_type asc";
                    break;
                case 4:
                    sql += " order by purchase_money desc";
                    break;
                case 14:
                    sql += " order by purchase_money asc";
                    break;
                case 5:
                    sql += " order by retail_money desc";
                    break;
                case 15:
                    sql += " order by retail_money asc";
                    break;
                case 6:
                    sql += " order by CONVERT(create_ren USING gb2312) desc";
                    break;
                case 16:
                    sql += " order by CONVERT(create_ren USING gb2312) asc";
                    break;
                case 7:
                    sql += " order by create_date desc";
                    break;
                case 17:
                    sql += " order by create_date asc";
                    break;
                case 8:
                    sql += " order by CONVERT(invoice_code USING gb2312) desc";
                    break;
                case 18:
                    sql += " order by CONVERT(invoice_code USING gb2312) asc";
                    break;
            }
//            if (desc == 1) {
//                sql += " order by Billcode desc";
//            }
//            if (desc == 11) {
//                sql += " order by Billcode asc";
//            }
//            if (desc == 2) {
//                sql += " order by bill_type desc";
//            }
//            if (desc == 12) {
//                sql += " order by bill_type asc";
//            }
//            if (desc == 4) {
//                sql += " order by purchase_money desc";
//            }
//            if (desc == 14) {
//                sql += " order by purchase_money asc";
//            }
//
//            if (desc == 5) {
//                sql += " order by retail_money desc";
//            }
//            if (desc == 15) {
//                sql += " order by retail_money asc";
//            }
//
//            if (desc == 6) {
//                sql += " order by CONVERT(create_ren USING gb2312) desc";
//            }
//            if (desc == 16) {
//                sql += " order by CONVERT(create_ren USING gb2312) asc";
//            }
//            if (desc == 7) {
//                sql += " order by create_date desc";
//            } else if (desc == 17) {
//                sql += " order by create_date asc";
//            } else {
//                sql += " order by create_date desc";
//            }
//            if (desc == 8) {
//                sql += " order by CONVERT(invoice_code USING gb2312) desc";
//            }
//            if (desc == 18) {
//                sql += " order by CONVERT(invoice_code USING gb2312) asc";
//            }
        } else {
            sql += " order by create_date desc";
        }
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    //根据ID查询方法
    public MdEnterWarehouse findWareById(Integer wewId) throws HSKDBException{
        MdEnterWarehouse mdEnterWarehouse=new MdEnterWarehouse();
        if (wewId!=null){
            mdEnterWarehouse.setWewId(wewId);
            Object obj = this.getOne(mdEnterWarehouse);
            if (obj!=null)
                mdEnterWarehouse=(MdEnterWarehouse) obj;
        }
        return mdEnterWarehouse;
    }

    public void  deleteWare(Integer wewId) throws HSKDBException{
        MdEnterWarehouse mdEnterWarehouse=new MdEnterWarehouse();
        if (wewId!=null){
            mdEnterWarehouse.setWewId(wewId);
            Object obj = this.getOne(mdEnterWarehouse);
            if (obj!=null){
                this.deleteObject(obj);
            }
        }
    }

    @Override
    public void deleteEnterMx(Integer wewId) throws HSKDBException {
        if (wewId == null) {
            return;
        }
        String sql = "delete from md_enter_warehouse_mx where wew_id = " + wewId;
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteEnterMxByMxId(Integer wewMxId) throws HSKDBException {
        if (wewMxId == null) {
            return;
        }
        String sql = "delete from md_enter_warehouse_mx where wew_mx_id = " + wewMxId;
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<Map<String,Object>> getPagerModelByEnterMdOrderInfo(MdOrderInfo att_MdOrderInfo,String cjDate,Integer limit, Integer page) throws HSKDBException {
        String sql="SELECT t1.moi_id as moiId,t1.order_code as orderCode,t1.Place_order_time as PlaceOrderTime,t1.place_order_money as placeOrderMoney ,t1.commodity_number as commodityNumber,t1.number2 as number2,(SELECT SUM(t2.enter_number) FROM md_order_mx t2 WHERE t2.moi_id=t1.moi_id) as enterNumber,t1.applicant_Name as applicantName,t1.purchase_Account as purchaseAccount,t1.Process_status as ProcessStatus" +
                " FROM md_order_info t1 where t1.order_state='1'";
        if(att_MdOrderInfo.getRbaId() != null)
            sql += " and t1.rba_id="+att_MdOrderInfo.getRbaId();
        if(att_MdOrderInfo.getRbbId() != null)
            sql += " and t1.rbb_id="+att_MdOrderInfo.getRbbId();
        if(att_MdOrderInfo.getRbsId() != null)
            sql += " and t1.rbs_id="+att_MdOrderInfo.getRbsId();
        if(att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().trim().equals("")){
            sql += "  AND (t1.order_code LIKE '%"+att_MdOrderInfo.getOrderCode()+"%' OR t1.moi_id IN(SELECT t3.moi_id FROM md_order_mx t3 WHERE t3.mat_name LIKE '%"+att_MdOrderInfo.getOrderCode()+"%') OR t1.moi_id IN(SELECT t3.moi_id FROM md_order_mx t3 WHERE t3.NORM LIKE '%"+att_MdOrderInfo.getOrderCode()+"%')OR t1.express_code LIKE '%"+att_MdOrderInfo.getOrderCode()+"%' )";
        }
        if(att_MdOrderInfo.getPurchaseAccount() != null && !att_MdOrderInfo.getPurchaseAccount().trim().equals("")){
            sql += " and t1.purchase_Account like '%"+att_MdOrderInfo.getPurchaseAccount().trim()+"%'";
        }
        if(att_MdOrderInfo.getPurchaseType() != null && !att_MdOrderInfo.getPurchaseType().trim().equals(""))
            sql += " and purchase_type='"+att_MdOrderInfo.getPurchaseType().trim()+"'";
        if (cjDate!=null&&!cjDate.trim().equals("")){
            sql += " and t1.Place_order_time >= '"+cjDate+" 00:00:00'";
            sql += " and t1.Place_order_time <= '"+cjDate+" 23:59:59'";
        }
        sql += " and (t1.Process_status not in (6) and t1.number2 is not null and (t1.number3<t1.number2 or t1.number3 is null))";
//        sql += " or moi_id in (select moi_id from md_order_mx where  changeStae IS NULL)";
        sql += " order by t1.Place_order_time desc";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    //新增入库数据
    public List<Map<String,Object>> getAddgetWarehousingMx(Integer moiId,Integer state1,Integer limit,Integer page) throws HSKDBException{
        String sql="SELECT t2.basic_coefficent as basicCoefficent, t1.enter_number as enterNumber, t1.number2 - t1.enter_number as leftNumber, t1.mom_id as momId,t2.wms_mi_id as wmsMiId,t3.mmf_id as mmfId,t4.mmt_id as mmtId,t1.mat_code as matCode,t1.mat_name as matName,t2.brand as brand,t3.mmf_name as mmfName,t4.mmt_code as mmtCode,t1.Basic_unit as BasicUnit,t1.mat_number as matNumber,t1.number2 as number2,t2.product_name as productName,t1.Unit_money as unitMoney,t2.back_Printing as backPrinting  ";
            sql+=" FROM md_order_mx t1 ";
            sql+=" LEFT JOIN md_materiel_info t2 ON t1.wms_mi_id=t2.wms_mi_id";
            sql+=" LEFT JOIN md_materiel_format t3 ON t1.mmf_id=t3.mmf_id";
            sql+=" LEFT JOIN md_materiel_type t4 ON t1.mat_type=t4.mmt_path";
            sql+=" where 1=1 AND moi_id ="+moiId;
            sql+=" and t1.number2 is not null and (t1.enter_number<t1.number2 or t1.enter_number is null)";
            if (state1!=null){
                sql+=" and t1.changeStae IS NULL";
            }
            sql += " order by t1.create_date desc";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public MdOrderMx findMdOrderMx(String relationBillCode, Integer mmfId) throws HSKDBException {
        String Hql= "from MdOrderMx where 1=1";
        if (relationBillCode != null && !relationBillCode.equals("")) {
            Hql += " and moiId in (select moiId from MdOrderInfo where orderCode = '" + relationBillCode + "')";
        }
        if (mmfId != null) {
            Hql += " and mmfId = " + mmfId;
        }
        List<MdOrderMx> list=this.getHibernateTemplate().find(Hql);
        if (list.isEmpty()) {
            return null;
        }
        return  list.get(0);
    }
}
