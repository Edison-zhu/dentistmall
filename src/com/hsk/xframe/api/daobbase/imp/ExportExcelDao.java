package com.hsk.xframe.api.daobbase.imp;

import com.hsk.dentistmall.api.persistence.MdCheckInventoryEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.IExpoetExcelDao;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ExportExcelDao extends SupperDao implements IExpoetExcelDao {
    //
    public List<Map<String,Object>> getMxListbyOrderAndMx(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKDBException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT t2.order_code as orderCode,t3.rba_name as rbaName,t4.rbs_name as rbsName,t5.rbb_name as rbbName,t6.user_name userName,applicant_Name as applicantName, ";
        sql +=" DATE_FORMAT( t2.Place_order_time, '%Y-%m-%d' ) AS PlaceOrderTime";
        sql+=" ,t1.mat_name as matName,t1.NORM as NORM, (SELECT t7.mmt_name FROM md_materiel_type t7 WHERE t7.mmt_path IN ( t1.mat_type )) as matType, ";
        sql+=" ROUND( t1.Unit_money, 2 ) as UnitMoney, ROUND( t1.mat_number, 0 ) as matNumber,";
        sql+=" ROUND( ( SELECT SUM( t6.mat_number ) FROM md_order_mx t6 WHERE t6.moi_id = t2.moi_id ), 0 ) AS matNumberSum,";
        sql+=" ROUND( ( SELECT SUM( t6.Total_money ) FROM md_order_mx t6 WHERE t6.moi_id = t2.moi_id ), 2 ) AS TotalMoneySum1,";
        sql+=" ROUND( t1.Total_money, 2 ) AS TotalMoney,";
        sql+="  (SELECT a.PARAM_NAME FROM sys_parameter a,sys_parameter b WHERE a.SYS_SPAR_ID = '127' AND a.PARAM_VALUE = t2.pay_type AND a.SYS_SPAR_ID = b.spar_id AND b.param_code = 'PAR191222092351998' ) AS payName,";
        sql+="  (SELECT a.PARAM_NAME  FROM sys_parameter a,sys_parameter b WHERE a.SYS_SPAR_ID = '107' AND a.PARAM_VALUE = t2.Process_status  AND a.SYS_SPAR_ID = b.spar_id AND b.param_code = 'PAR171023031218563' ) AS ProcessName,";
        sql+="  CONCAT( '总额', ROUND(t2.place_order_money, 2), '(含12.00元快递费)', '发票抬头:', t2.bill_heard, '税号：', t2.bill_code ) AS ddmx,";
        sql+="  CONCAT( '收货人', t2.Addressee, '联系电话', t2.Addressee_telephone, '收货地址', Delivery_address, '物流信息:', ( express_name IS NOT NULL ), express_code IS NOT NULL ) AS wlxx,";
        sql+="  t2.enterprise_type as enterpriseType,";
        sql+=" t2.scope_business as scopeBusiness,";
        sql+=" ROUND( t1.number2, 0 ) as number2,";
        sql+=" ROUND( t1.shure_number, 0 ) as shureNumber,";
        sql+=" ROUND( t1.back_number, 0 ) as backNumber,";
        sql+=" ROUND( (t1.back_number * t1.Unit_money ), 2 ) as backMoney,";
        sql+=" ROUND( t1.Total_money, 2 )AS TotalMoney,";
        sql+=" ROUND( ( SELECT SUM( Total_money ) FROM md_order_mx t6 WHERE t6.moi_id = t2.moi_id ), 2 ) AS TotalMoneySum ,ROUND( t2.place_order_money,2) AS TotalMoneyOrders,d.sales_code";
        sql+=" FROM md_order_mx t1";
        sql+=" LEFT JOIN md_order_info t2 ON t1.moi_id=t2.moi_id ";
        sql+=" LEFT JOIN md_company_group t3 ON t2.rba_id=t3.rba_id";
        sql+=" LEFT JOIN md_dentist_hospital t4 ON t2.rbs_id = t4.`rbs_id`";
        sql+=" LEFT JOIN md_dental_clinic t5 ON t2.rbb_id = t5.`rbb_id`";
        sql+=" LEFT JOIN sys_user_info t6 ON t2.purchase_id = t6.`sui_id`  LEFT JOIN md_company_group a ON  t2.rba_id= a.rba_id\n" +
                "\tLEFT JOIN md_dentist_hospital b ON t2.rbs_id = b.rbs_id\n" +
                "\tLEFT JOIN md_dental_clinic c ON t2.rbb_id = c.rbb_id,\n" +
                "\tsys_sales_man d";
        sql+=" WHERE 1=1";
        sql+=" AND ( d.salesman_id IN ( a.salesman_ids ) OR d.salesman_id IN ( b.salesman_ids ) OR d.salesman_id IN ( c.salesman_ids ) ) AND t2.order_code IS NOT NULL  ";

        if(att_MdOrderInfo!=null && att_MdOrderInfo.getWzId()!= null)
            sql += " and t2.wz_id="+att_MdOrderInfo.getWzId();
        if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseType()!= null && !att_MdOrderInfo.getPurchaseType().equals(""))
            sql += " and t2.purchase_type="+att_MdOrderInfo.getPurchaseType();
        if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbaId()!= null)
            sql += " and t2.rba_id="+att_MdOrderInfo.getRbaId();
        if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbbId()!= null)
            sql += " and t2.rbb_id="+att_MdOrderInfo.getRbbId();
        if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbsId()!= null)
            sql += " and t2.rbs_id="+att_MdOrderInfo.getRbsId();
        if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseId()!= null)
            sql += " and t2.purchase_id="+att_MdOrderInfo.getPurchaseId();
        if(att_MdOrderInfo != null && att_MdOrderInfo.getOrderCode()!=null && !att_MdOrderInfo.getOrderCode().trim().equals(""))
            sql += " and t2.order_code like '%"+att_MdOrderInfo.getOrderCode().trim()+"%'";
        if(att_MdOrderInfo != null && att_MdOrderInfo.getApplicantName()!=null && !att_MdOrderInfo.getApplicantName().trim().equals(""))
            sql += " and t2.applicant_name like '%"+att_MdOrderInfo.getApplicantName().trim()+"%'";
        if(att_MdOrderInfo != null && att_MdOrderInfo.getPurchaseUnit()!=null && !att_MdOrderInfo.getPurchaseUnit().trim().equals(""))
            sql += " and t2.purchase_unit like '%"+att_MdOrderInfo.getPurchaseUnit().trim()+"%'";
        if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus()!=null && !att_MdOrderInfo.getProcessStatus().trim().equals(""))
            sql += " and t2.process_status like '%"+att_MdOrderInfo.getProcessStatus().trim()+"%'";
        if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus_str()!=null && !att_MdOrderInfo.getProcessStatus_str().trim().equals(""))
            sql += " and t2.process_status in ("+att_MdOrderInfo.getProcessStatus_str().trim()+")";
        if(att_MdOrderMx != null && att_MdOrderMx.getMatName()!=null && !att_MdOrderMx.getMatName().trim().equals(""))
            sql += " and t2.mat_name like '%"+att_MdOrderMx.getMatName().trim()+"%'";
        if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_start()!=null)
            sql += " and t2.place_order_time >='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_start())+" 00:00:00'";
        if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_end()!=null)
            sql += " and t2.place_order_time <='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_end())+" 23:59:59'";
        if (moiIds!=null) {
            sql+=" and t2.moi_id in("+moiIds+")";
        }
        sql+=" ORDER BY DATE_FORMAT( t1.Place_order_time, '%Y-%m-%d' ) DESC,t2.order_code DESC";
        return this.getJdbcDao().query(sql);
    }

    public Integer orderCodeCount(String orderCode,String orderCodeNext) throws HSKDBException{
        String sql="SELECT COUNT(t2.order_code) as orderCodeCount";
        sql+=" FROM md_order_mx t1";
        sql+=" LEFT JOIN md_order_info t2 ON t1.moi_id=t2.moi_id ";
        sql+=" LEFT JOIN md_company_group t3 ON t2.rba_id=t3.rba_id";
        sql+=" LEFT JOIN md_dentist_hospital t4 ON t2.rbs_id = t4.`rbs_id`";
        sql+=" LEFT JOIN md_dental_clinic t5 ON t2.rbb_id = t5.`rbb_id`";
        sql+=" LEFT JOIN sys_user_info t6 ON t2.purchase_id = t6.`sui_id`";
        sql+=" WHERE 1=1";
        sql+=" AND t2.order_code IS NOT NULL ";
        sql+=" AND t2.order_code='"+orderCode+"'";
        sql+=" ORDER BY DATE_FORMAT( t1.Place_order_time, '%Y-%m-%d' ) DESC";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("orderCodeCount").toString());
        }
        return  0;
    }
    public List<Map<String, Object>> getSevenListCountAll1(Date Date1, Date Date2) throws HSKDBException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT t2.rba_name as rba_name, t3.`rbs_name` as rbs_name,t4.`rbb_name` as rbb_name, DATE_FORMAT(t1.Place_order_time, '%Y-%m-%d') AS time1, COUNT(t1.order_code) AS OrderNum,(SELECT SUM(t5.mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id) AS matNumber,";
        sql+=" (SELECT SUM(t5.Total_money) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id) AS TotalMoney, ((SELECT SUM(t5.Total_money) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id) / (COUNT(t1.order_code))) AS price,";
        sql+="CONCAT('修复填充类(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2200),'0')),')','\r\n'/*CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/  '车针/扩锉/磨头(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2201),'0')),') ','\r\n'  ";
        sql+="'口腔常用材料(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2202),'0')),')','\r\n'/*CHAR(10),CHAR(10),CHAR(10),*/";
        sql+="'医用耗材(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2203),'0')),')','\r\n'/*CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/";
        sql+="'口腔常用器械(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2204),'0')),')','\r\n'/*CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/";
        sql+="'正畸产品类(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2205),'0')),')','\r\n'/*CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/";
        sql+="'口腔护理(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2206),'0')),')','\r\n'/*CHAR(13),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/";
        sql+="'种植产品类(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2207),'0')),')','\r\n'/*CHAR(13),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/";
        sql+="'口腔设备(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2208),'0')),')', '\r\n'/*CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),*/";
        sql+=" 	'其他类别(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1 !=2200 AND mat_type1!=2201 AND mat_type1!=2202 AND mat_type1!=2203 AND mat_type1!=2204 AND mat_type1!=2205 AND mat_type1!=2206 AND mat_type1!=2207 AND mat_type1!=2208),'0')),')'";
        sql+=" ) AS typenum";
        sql+=" FROM md_order_info t1 ";
        sql+=" LEFT JOIN md_company_group t2 ON t1.rba_id=t2.rba_id ";
        sql+=" LEFT JOIN md_dentist_hospital t3 ON t1.rbs_id =t3.`rbs_id` ";
        sql+=" LEFT JOIN md_dental_clinic t4 ON t1.rbb_id =t4.`rbb_id` ";
        sql+=" WHERE t1.Place_order_time<= '"+sdf.format(Date2)+" 23:59:59'";
        sql+=" AND t1.Place_order_time>= '"+sdf.format(Date1)+" 00:00:00'";
        sql+=" GROUP BY t2.rba_name, t3.`rbs_name`,t4.`rbb_name`, DATE_FORMAT(t1.Place_order_time, '%Y-%m-%d') ";
        return this.getJdbcDao().query(sql);
    }
public List<Map<String, Object>> getSevenListCountAll(Date Date1, Date Date2) throws HSKDBException{
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT DISTINCT t2.rba_name AS rba_name, t3.`rbs_name` AS rbs_name, t4.`rbb_name` AS rbb_name,DATE_FORMAT( t1.Place_order_time, '%Y-%m-%d' ) AS time1," +
            " (SELECT sum( t6.mat_number ) FROM md_order_mx t6  WHERE DATE_FORMAT( t6.Place_order_time, '%Y-%m-%d' ) = DATE_FORMAT( t5.Place_order_time, '%Y-%m-%d' ) " +
            " AND t6.Place_order_time <= '"+sdf.format(Date2)+" 23:59:59' AND t6.Place_order_time >= '"+sdf.format(Date1)+" 00:00:00' ) AS mat_number, " +
            " (SELECT sum( t6.Total_money ) FROM md_order_mx t6 WHERE DATE_FORMAT( t6.Place_order_time, '%Y-%m-%d' ) = DATE_FORMAT( t5.Place_order_time, '%Y-%m-%d' ) AND t6.Place_order_time <= '"+sdf.format(Date2)+" 23:59:59' AND t6.Place_order_time >= '"+sdf.format(Date1)+" 00:00:00' ) AS Total_money, " +
            " (SELECT sum( t6.Total_money )/sum( t6.mat_number )  FROM md_order_mx t6 WHERE DATE_FORMAT( t6.Place_order_time, '%Y-%m-%d' ) = DATE_FORMAT( t5.Place_order_time, '%Y-%m-%d' ) AND t6.Place_order_time <= '"+sdf.format(Date2)+" 23:59:59' AND t6.Place_order_time >= '"+sdf.format(Date1)+" 00:00:00' ) AS price, "+
            " (SELECT COUNT(t6.order_code) FROM md_order_info t6  WHERE DATE_FORMAT( t6.Place_order_time, '%Y-%m-%d' ) = DATE_FORMAT( t5.Place_order_time, '%Y-%m-%d' )  AND t6.Place_order_time <= '"+sdf.format(Date2)+" 23:59:59' AND t6.Place_order_time >= '"+sdf.format(Date1)+" 00:00:00' ) AS OrderNum, "+
//            " t5.mat_type1 AS mat_type, " +
//            " CONCAT(t5.mat_type1,'(',(select COUNT(t6.mat_type1) from md_order_mx t6 where t6.mat_type1 = t5.mat_type1 and t6.Place_order_time <= '2020-05-19 23:59:59' AND t6.Place_order_time >= '2020-05-12 00:00:00' ),')') AS matTypeCount"+
            " (CASE WHEN t5.mat_type1=2200 THEN '修复、充填类' WHEN t5.mat_type1=2201 THEN '车针/扩锉/磨头' WHEN t5.mat_type1=2202 THEN '口腔常用材料' WHEN t5.mat_type1=2203 THEN '医用耗材类' WHEN t5.mat_type1=2204 THEN '口腔科常用器械' WHEN t5.mat_type1=2205 THEN '正畸产品类' WHEN t5.mat_type1=2206 THEN '口腔护理' WHEN t5.mat_type1=2207 THEN '种植产品类' WHEN t5.mat_type1=2208 THEN '口腔设备类'  ELSE '其他类别' END) as matType "+
            " FROM md_order_mx t5" +
            " LEFT JOIN md_order_info t1 ON t1.moi_id = t5.moi_id" +
            " LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id" +
            " LEFT JOIN md_dentist_hospital t3 ON t1.rbs_id = t3.`rbs_id`" +
            " LEFT JOIN md_dental_clinic t4 ON t1.rbb_id = t4.`rbb_id` " +
            " WHERE t1.Place_order_time <= '"+sdf.format(Date2)+" 23:59:59' AND t1.Place_order_time >= '"+sdf.format(Date1)+" 00:00:00'";
    return this.getJdbcDao().query(sql);
}
    public Integer getSevenListCountAll1Count(String type,String Date) throws HSKDBException{
        String sql="SELECT  COUNT(t1.mat_type1) as countType FROM  md_order_mx t1 ";
        if (type.equals("修复、充填类")){
            sql+=" WHERE t1.mat_type1=2200";
        }else if (type.equals("车针/扩锉/磨头")){
            sql+=" WHERE t1.mat_type1=2201";
        }else if (type.equals("口腔常用材料")){
            sql+=" WHERE t1.mat_type1=2202";
        }else if (type.equals("医用耗材类")){
            sql+=" WHERE t1.mat_type1=2203";
        }else if (type.equals("口腔科常用器械")){
            sql+=" WHERE t1.mat_type1=2204";
        }else if (type.equals("正畸产品类")){
            sql+=" WHERE t1.mat_type1=2205";
        }else if (type.equals("口腔护理")){
            sql+=" WHERE t1.mat_type1=2206";
        }else if (type.equals("种植产品类")){
            sql+=" WHERE t1.mat_type1=2207";
        }else if (type.equals("口腔设备类")){
            sql+=" WHERE t1.mat_type1=2208";
        }else {
            sql+=" WHERE (t1.mat_type1=1334 or t1.mat_type1=1339)";
        }
        sql+=" AND t1.Place_order_time <='"+Date+" 23:59:59'";
        sql+=" AND t1.Place_order_time >='"+Date+" 00:00:00'";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("countType").toString());
        }
        return 0;
    }
    @Override
    public List<Map<String, Object>> getSalesManLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen,
                                                           Integer selectValue, String dateInput7, String dateInput8, Integer moiId) throws HSKDBException {
        String sql = "SELECT t5.sales_name, t5.sales_type, t2.sales_name as cs_name, t3.sales_name as hs_name, t4.sales_name as cls_name," +
                " t1.settlement, t1.moi_id as moi_id, t1.rba_id, t1.rbs_id, t1.rbb_id,t1.loan_log ,t1.Process_status as process_status,t1.order_code, t1.place_order_time, t1.pay_type as pay_type,t1.pay_code," +
                "t1.pay_date,t1.place_order_money,t1.actual_money,t1.purchase_unit,t1.applicant_name,t1.loan_state,t1.loan_money, t1.settlement FROM md_order_info t1 " +
                " left join md_company_group t2 on t1.rba_id = t2.rba_id" +
                " left join md_dentist_hospital t3 on t3.rbs_id = t1.rbs_id" +
                " left join md_dental_clinic t4 on t4.rbb_id = t1.rbb_id," +
                " sys_sales_man t5" +
                " where (t5.salesman_id in (t2.salesman_ids) or t5.salesman_id in (t3.salesman_ids) or t5.salesman_id in (t4.salesman_ids))";
        if (orderCodeGJ != null && orderCodeGJ != "") {
            sql += " AND t1.order_code LIKE '%" + orderCodeGJ.toUpperCase() + "%'";
        }
        if (moiId != null) {
            sql += " AND t1.moi_id=" + moiId;
        }
        if (dateInput1 != null && dateInput2 != null && dateInput1 != "" && dateInput2 != "") {
            sql += " AND t1.Place_order_time> '" + dateInput1 + " 00:00:00'";
            sql += " AND t1.Place_order_time< '" + dateInput2 + " 23:59:59'";
        }
        if (selectValue != null && selectValue != 0) {
            if (selectValue != 3) {
                sql += " AND t1.loan_state=" + selectValue;
            } else {
                sql += " AND (t1.loan_state = 3 or t1.loan_state is null)";
            }
        }
        if (selectValue1 != null && selectValue1 != 0) {
            if (selectValue1 != 7) {
                sql += " and t1.process_status = " + selectValue1;
            } else {
                sql += " and (t1.after_sale is not null and t1.after_sale <> 0)";
            }
        }
        if (moneyFw1 != null && moneyFw1 != "" && moneyFw2 != null && moneyFw2 != "") {
            sql += " AND t1.place_order_money >" + moneyFw1;
            sql += " AND t1.place_order_money <" + moneyFw2;
        }
        if (gongYingS != null && gongYingS != "") {
            sql += " AND t1.applicant_Name LIKE '%" + gongYingS + "%'";
        }
        if (caiGou != null && caiGou != "") {
            sql += " AND t5.salesman_id = " + caiGou;
        }
        if (jiGouMenZhen != null) {
            sql += " and t1.purchase_unit like '%" + jiGouMenZhen + "%'";
        }
        return this.getJdbcDao().query(sql);
    }
    //导出领料员申领管理批量导出物料清单和导出单个物料清单
    public List<Map<String, Object>> exportPick(Integer  moiId) throws HSKDBException{
        String sql=" SELECT\n" +
                "\tt1.moo_code AS mooCode,\n" +
                "\tt1.order_time AS orderTime,\n" +
                "\tt1.flow_state AS flowState,\n" +
                "\tt1.user_name AS user_name ,\n" +
                "\tt2.mat_code as mat_code,\n" +
                "\tt2.mat_name as mat_name,\n" +
                "\tt2.NORM AS NORM,t2.Basic_unit,\n" +
                "\t(SELECT t3.brand FROM md_materiel_info t3 WHERE t3.mat_name=t2.mat_name LIMIT 1) AS brand,\n" +
                "\t(SELECT t3.product_name FROM md_materiel_info t3 WHERE t3.mat_name=t2.mat_name LIMIT 1) AS productName,\n" +
                "IFNULL(t2.base_number,0.00) AS base_number,\n" +
                "\tIFNULL(t2.number1,0.00) AS number1,\n" +
                "\t(IFNULL(t2.base_number,0.00)-IFNULL(t2.number1,0.00)) AS chaNumber,\n" +
                "\tt1.remarks as remarks,\n" +
                "\t(SELECT t3.Basic_unit_accuracy FROM md_materiel_info t3 WHERE t3.mat_name=t2.mat_name LIMIT 1) AS basicUnitAccuracy,\n" +
                "\t(SELECT t3.Batch_rule FROM md_materiel_info t3 WHERE t3.mat_name=t2.mat_name LIMIT 1) AS batchRule,\n" +
                "\t(SELECT t3.back_Printing FROM md_materiel_info t3 WHERE t3.mat_name=t2.mat_name LIMIT 1) AS backPrinting,\n" +
                "\tt4.USER_name as outName,\n" +
                "\tt4.FINSH_DATE as finshDate,\n" +
                "\tt4.wow_remarks as wow_remarks,\n#申请最小单位数量\n" +
                "\tIFNULL(t2.split_quantity,0.00) as split_quantity,\n" +
                "\t#最小出库数量\n" +
                "\tIFNULL(t2.split_number1,0.00) as split_number1,\t(SELECT t3.split_unit FROM md_materiel_info t3 WHERE t3.wms_mi_id=t2.wms_mi_id) AS splitUnit,\n" +
                "\t#最小数量差数\n" +
                "\t(IFNULL(t2.split_quantity,0.00)-IFNULL(t2.split_number1,0.00)) AS spitChaNumber  " +
                "FROM\n" +
                "\tmd_out_order t1\n" +
                "\tLEFT JOIN md_out_order_mx t2 ON t1.moo_id = t2.moo_id \n" +
                "\tLEFT JOIN md_out_warehouse t4 ON t1.moo_code = t4.RELATED_BILL1 \n" +
                "WHERE 1=1";
            if (moiId!=null){
                sql+=" AND t1.moo_id="+moiId;
            }
            sql+=" ORDER BY t1.order_time DESC";
        return this.getJdbcDao().query(sql);
    }

    public List<Map<String,Object>> exportWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate,Integer desc, Integer rbaId, Integer rbsId, Integer rbbId, Integer suiId, String purchaseType) throws HSKDBException{
        String sql="  SELECT\n" +
                "mdordermx0_.mom_id AS mom1_87_,\n" +
                "mdordermx0_.back_number AS back2_87_,\n" +
                "mdordermx0_.Basic_unit AS Basic3_87_,\n" +
                "mdordermx0_.changeStae AS changeStae87_,\n" +
                "mdordermx0_.create_date AS create5_87_,\n" +
                "mdordermx0_.create_ren AS create6_87_,\n" +
                "mdordermx0_.Days_arrival AS Days7_87_,\n" +
                "mdordermx0_.describes AS describes87_,\n" +
                "mdordermx0_.edit_date AS edit9_87_,\n" +
                "mdordermx0_.edit_ren AS edit10_87_,\n" +
                "mdordermx0_.enter_number AS enter11_87_,\n" +
                "mdordermx0_.mat_code AS mat12_87_,\n" +
                "mdordermx0_.mat_name AS mat13_87_,\n" +
                "mdordermx0_.mat_number AS mat14_87_,\n" +
                "mdordermx0_.mat_type AS mat15_87_,\n" +
                "mdordermx0_.mat_type1 AS mat16_87_,\n" +
                "mdordermx0_.mat_type2 AS mat17_87_,\n" +
                "mdordermx0_.mat_type3 AS mat18_87_,\n" +
                "mdordermx0_.mmf_id AS mmf19_87_,\n" +
                "mdordermx0_.moi_id AS moi20_87_,\n" +
                "mdordermx0_.money1 AS money21_87_,\n" +
                "mdordermx0_.money2 AS money22_87_,\n" +
                "mdordermx0_.money3 AS money23_87_,\n" +
                "mdordermx0_.money4 AS money24_87_,\n" +
                "mdordermx0_.money5 AS money25_87_,\n" +
                "mdordermx0_.NORM AS NORM87_,\n" +
                "mdordermx0_.number1 AS number27_87_,\n" +
                "mdordermx0_.number2 AS number28_87_,\n" +
                "mdordermx0_.number3 AS number29_87_,\n" +
                "mdordermx0_.number4 AS number30_87_,\n" +
                "mdordermx0_.number5 AS number31_87_,\n" +
                "mdordermx0_.Place_order_time AS Place32_87_,\n" +
                "mdordermx0_.Purchase_unit AS Purchase33_87_,\n" +
                "mdordermx0_.shure_number AS shure34_87_,\n" +
                "mdordermx0_.Total_money AS Total35_87_,\n" +
                "mdordermx0_.Unit_money AS Unit36_87_,\n" +
                "mdordermx0_.wms_mi_id AS wms37_87_,t3.batch_code as batch_code,t3.valied_date AS valied_date,t3.applicant_Name as applicant_Name,t3.back_Printing as back_Printing,t2.Relation_billCode AS relationBillCode,\n" +
                "( SELECT max( a.mas_id ) FROM md_order_after_sale_mx a WHERE a.mom_id = mdordermx0_.mom_id AND a.after_sale != 3 ) AS formula121_,\n" +
                "( SELECT t.mmt_name FROM md_materiel_type t WHERE t.mmt_path = mdordermx0_.mat_type ) AS formula122_,\n" +
                "( SELECT f.mmf_code FROM md_materiel_format f WHERE f.mmf_id = mdordermx0_.mmf_id ) AS formula123_,( SELECT w.brand FROM md_materiel_info w WHERE w.wms_mi_id = mdordermx0_.wms_mi_id ) AS formula124_ ,( SELECT w.product_name FROM md_materiel_info w WHERE w.wms_mi_id = mdordermx0_.wms_mi_id ) AS formula125_ \n \n" +
                "FROM\n" +
                "\tmd_order_mx mdordermx0_ LEFT JOIN md_order_info t1 ON t1.moi_id=mdordermx0_.moi_id LEFT JOIN md_enter_warehouse t2 ON t2.Relation_billCode=t1.order_code\n LEFT JOIN md_materiel_info t3 ON t3.wms_mi_id=mdordermx0_.wms_mi_id " +
                "WHERE\n" +
                "\t1 = 1 ";
        if(warehousCode!=null&&warehousCode!="null"&&!warehousCode.equals("")){
            sql+=" AND t2.Billcode like'%"+warehousCode+"%'";
        }
        if (rbaId != null) {
            sql += " and t2.rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and t2.rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and t2.rbb_id = " + rbbId;
        }
        if (suiId != null) {
            sql += " and t2.sui_id = " + suiId;
        }
        if (purchaseType != null && !purchaseType.equals("")) {
            sql += " and t2.purchase_type = '" + purchaseType + "'";
        }

        if(select1!=null){
            if (select1==0){
            }else if (select1==1){
                sql+=" AND t2.bill_type=2";
            }else if(select1==2){
                sql+=" AND t2.bill_type=1";
            }
        }
        if(operationDate!=null&&operationDate!="null"&&!operationDate.equals("")){
            sql+=" AND t2.create_date>= '"+operationDate+" 00:00:00'";
            sql+=" AND t2.create_date<= '"+operationDate+" 23:59:59'";
        }
            sql += " order by t2.create_date desc";
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportWarehousingListMx(Integer wewId,String wmsMiIds, MdMaterielInfo att_MdMaterielInfo) throws HSKDBException{
        String sql="SELECT\n" +
                "mdenterwar0_.mat_code AS mat33_50_,##物料号\n" +
                "mdenterwar0_.mat_name AS mat34_50_,##物料名称\n" +
                "t1.mmf_name AS mmf44_50_,\n" +
                "t1.mmf_code AS mmf42_50_,\n" +
                "mdenterwar0_.Basic_unit AS Basic8_50_,##基本单位\n" +
                "mdenterwar0_.split_unit AS unit,##最小拆分单位\n" +
                "mdenterwar0_.number1 AS number52_50_,##库存数量\n" +
                "t1.price AS price50_,##采购均价\n" +
                "t1.retail_price AS retail_money,##零售均价\n" +
                "mdenterwar0_.alias_name AS alias_name,##别名\n" +
                "mdenterwar0_.product_name AS product62_50_,##包装方式\n" +
                "mdenterwar0_.brand AS brand50_,##品牌\n" +
                "mdenterwar0_.mat_type1 AS mat38_50_,##分类\n" +
                "mdenterwar0_.create_date AS receipt68_50_,##创建时间\n" +
                "mdenterwar0_.create_ren AS Purchase66_50_,##制作部门\n" +
                "mdenterwar0_.create_ren AS create23_50_,##制作人\n" +
                "mdenterwar0_.valied_date AS yxq,##有效期\n" +
                "mdenterwar0_.Basic_unit_accuracy as zcCode,##注册证有效期/备案日期\n" +
                "mdenterwar0_.Serial_number as serialNumbe,##货号\n" +
                "mdenterwar0_.standard as standard,##产品标准\n" +
                "mdenterwar0_.materiel_name as materiel_name,##材质\n" +
                "mdenterwar0_.back_Printing as back_Printing,##注册号\n" +
                "\tmdenterwar0_.product_factory AS applicant_Name,##生产企业\n" +
                "\tmdenterwar0_.product_place AS Corporate_domicile,##产地\n" +
                "mdenterwar0_.ingredient as ingredient,##主要成分\n" +
                "mdenterwar0_.product_use as product_use,##产品用途\n" +
                "mdenterwar0_.VALID_PERIOD as VALID_PERIOD,##保质期\n" +
                "mdenterwar0_.valid_period_unit as valid_period_unit,##保质期单位\n" +
                "mdenterwar0_.weight as weight,##重量\n" +
                "mdenterwar0_.batch_code as batch_code,##批号\n" +
                "mdenterwar0_.remark as remark,t2.ratio,\n" +
                "t2.QUANTITY,\n" +
                "FLOOR((t2.base_number / t2.ratio)) * t2.ratio - t2.base_number AS splitBaseNumber,\n" +
                "\t( SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id = mdenterwar0_.mdp_id ) AS mat111_,\n" +
                "\t( SELECT a.mdps_name FROM md_materiel_part_second a WHERE a.mdps_id = mdenterwar0_.mdps_id ) AS formula91_\n" +
                "FROM\n" +
                "\tmd_materiel_info mdenterwar0_ \n" +
                "\tLEFT JOIN md_materiel_format t1 ON t1.wms_mi_id=mdenterwar0_.wms_mi_id  LEFT JOIN md_inventory_view t2  ON mdenterwar0_.wms_mi_id=t2.wms_mi_id2\n" +
                "WHERE\n" +
                "\t1 = 1 ";
        if (att_MdMaterielInfo.getWzId()!=null&&!att_MdMaterielInfo.equals("")){
            sql+=" AND mdenterwar0_.wz_id="+att_MdMaterielInfo.getWzId();
        }
        if (att_MdMaterielInfo.getPurchaseType()!=null&&!att_MdMaterielInfo.getPurchaseType().equals("")){
            sql+=" AND mdenterwar0_.purchase_type="+ att_MdMaterielInfo.getPurchaseType();
        }
        if (wmsMiIds!=null&&!wmsMiIds.equals("")){
            sql+=" AND mdenterwar0_.wms_mi_id in("+wmsMiIds+")";
        }
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportCheckInvent(Integer ciId,String ciIds,String searchName,String brand,String batchCode,String checkCode,String checkType,String createRen,String startDate) throws HSKDBException{
        String sql="SELECT\n" +
                "mdcheckinv0_.ci_id,\n" +
                "mdcheckinv0_.check_code AS check4_36_,\n" +
                "mdcheckinv0_.check_name AS check5_36_,\n" +
                "mdcheckinv0_.create_date AS create8_36_,\n" +
                "mdcheckinv0_.check_type AS check7_36_,\n" +
                "(CASE mdcheckinv0_.check_type \nWHEN '1' THEN '月度'\n" +
                "       WHEN '2' THEN '季度'\n" +
                "\t\t\t WHEN '3' THEN '年度'\n" +
                "\t\t\t WHEN '4' THEN '每次'\n" +
                "       ELSE ''" +
                "       END) AS check_type,\n" +
                "( SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id = mdinventor0_.mdp_id )  AS check6_36_,\n" +
                "( SELECT b.mat_code FROM md_materiel_info b  WHERE b.wms_mi_id = mdinventor0_.wms_mi_id ) AS matCode,\n" +
                "mdinventor0_.mat_name AS mat18_54_,\n" +
                "mdinventor0_.mmf_name AS mmf31_54_,\n" +
                "mdinventor0_.brand AS brand54_,\n" +
                "( SELECT b.product_factory FROM md_materiel_info b  WHERE b.wms_mi_id = mdinventor0_.wms_mi_id ) AS product_factory,\n" +
                "( SELECT b.back_Printing FROM md_materiel_info b  WHERE b.wms_mi_id = mdinventor0_.wms_mi_id ) AS back_Printing,\n" +
                "\tIFNULL(mdinventor0_.QUANTITY,0.00) AS base5_54_,\n" +
                "\tIFNULL(mdinventor0_.now_number,0.00) AS now33_54_,\n" +
                "\t( IFNULL(mdinventor0_.now_number,0.00) - IFNULL(mdinventor0_.base_number,0.00) ) AS lackNumber,\n" +
                "\t\t(IFNULL(mdinventor0_.base_number,0.00)-(IFNULL(mdinventor0_.QUANTITY*mdinventor0_.ratio,0.00))) AS splitNumber,\n" +
                "\t\tIFNULL(mdinventor0_.now_split_number,0.00) AS nowSplitNumber,\n" +
                "\t\t(IFNULL(mdinventor0_.now_split_number,0.00)-(IFNULL(mdinventor0_.base_number,0.00)-(IFNULL(mdinventor0_.QUANTITY*mdinventor0_.ratio,0.00)))) AS  lackSplitNumber,\n" +
                "mdinventor0_.check_remark AS check11_54_,\n" +
                "mdinventor0_.check_inventory AS check10_54_,\n" +
                "mdcheckinv0_.create_ren AS create9_36_,(SELECT t1.Basic_unit FROM md_materiel_info t1 WHERE t1.wms_mi_id=mdinventor0_.wms_mi_id) AS basicUnit,\t\t( SELECT t1.split_unit FROM md_materiel_info t1 WHERE t1.wms_mi_id = mdinventor0_.wms_mi_id ) AS split_unit,\n\n" +
                "mdcheckinv0_.create_date AS create_date\n" +
                "FROM\n" +
                "\tmd_inventory_check_view mdinventor0_ LEFT JOIN md_check_inventory mdcheckinv0_ ON mdinventor0_.ci_id=mdcheckinv0_.ci_id\n" +
                "WHERE\n" +
                "\t1 = 1 ";
        if (ciId!=null){
            sql+="AND mdcheckinv0_.ci_id = "+ciId;
        }
        if (ciIds!=null&&!ciIds.equals("")){
            sql+="AND mdcheckinv0_.ci_id in ("+ciIds+")";
        }
        if (searchName!=null&&!searchName.equals("")){
            sql+="\tAND (mdinventor0_.mat_name like '%"+searchName+"%' OR mdinventor0_.mmf_name like '%"+searchName+"%')";
        }
        if (brand!=null&&!brand.equals("")){
            sql+="\tAND mdinventor0_.brand="+brand;
        }
        if (checkCode!=null&&!checkCode.equals("")){
            sql+="\tAND mdcheckinv0_.check_code like '%"+checkCode+"%'";
        }
        if (checkType!=null&&!checkType.equals("")){
//            sql+="\tAND mdcheckinv0_.check_code="+checkCode;
        }
        if (createRen!=null&&!createRen.equals("")){
            sql+="\tAND mdcheckinv0_.create_ren like '%"+createRen+"%'";
        }
        if (startDate!=null&&!startDate.equals("")){
            sql+="\tAND mdcheckinv0_.create_date="+startDate;
        }
        sql+=" GROUP BY mdinventor0_.cie_id  ORDER BY mdcheckinv0_.create_date DESC";
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportInventoryView(String wiId,String InventoryStart,String InventoryEnd,String searchName,String brand,String createDate1,Integer rba,Integer rbs,Integer rbb,String purchaseType) throws HSKDBException{
        String sql=" SELECT\n" +
                " mdinventor0_.mat_code AS mat11_65_,\n" +
                " mdinventor0_.mat_name AS mat12_65_,\n" +
                " ( SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id = mdinventor0_.mdp_id ) AS t2matType1,\n" +
                "\t( SELECT a.mdps_name FROM md_materiel_part_second a WHERE a.mdps_id = mdinventor0_.mdps_id ) AS t2matType2,\n" +
                "  (CASE WHEN  t2.stype IS NOT NULL AND t2.stype=1 THEN '入库'\n" +
                "\t\t\tWHEN t2.stype IS NOT NULL AND t2.stype=2 THEN '出库'\n" +
                "       ELSE ''\n" +
                "       END) AS t2stype,\n" +
                "(\n" +
                "\t\t\t\tCASE\n" +
                "\t\t\t\t\t\tWHEN ISNULL( t2.productValitime ) THEN\n" +
                "\t\t\t\t\t\t'未设置' \n" +
                "\t\t\t\t\t\tWHEN DATEDIFF(  t2.productValitime, NOW( ) ) > mdinventor0_.warning_day or ISNULL(mdinventor0_.warning_day) THEN\n" +
                "\t\t\t\t\t\t'正常' \n" +
                "\t\t\t\t\t\tWHEN DATEDIFF(  t2.productValitime, NOW( ) ) > 0 \n" +
                "\t\t\t\t\t\tAND DATEDIFF(  t2.productValitime, NOW( ) ) < mdinventor0_.warning_day THEN\n" +
                "\t\t\t\t\t\t\t'快过期' ELSE '已过期' \n" +
                "\t\t\t\t\t\tEND \n" +
                "\t\t\t\t\t\t) AS t2state, " +
                " t2.mmfName AS t2mmfName,\n" +
                " (CASE WHEN  mdinventor0_.QUANTITY IS NOT NULL AND(mdinventor0_.QUANTITY<warning_shu OR mdinventor0_.QUANTITY>mdinventor0_.max_shu) THEN '是'\n" +
                "       ELSE '否'\n" +
                "       END) AS quantitymax,\n" +
                " mdinventor0_.max_shu AS max14_65_,\n" +
                " mdinventor0_.warning_shu AS warning35_65_,\n" +
                "t2.brand AS t2brand,\n" +
                "t2.productName as t2productName,\n" +
                "mdinventor0_.product_factory AS product24_65_,\n" +
                "t3.back_Printing AS t3back_Printing,\n" +
                "t3.valied_date AS t3valied_date,\n" +
                "t2.relatedCode AS t2relatedCode,\n" +
                "-- t2.price,\n" +
                " (CASE WHEN  (t2.price) IS NOT NULL  THEN t2.price\n" +
                "       ELSE '0.00'\n" +
                "       END) AS t2price,\n" +
                "-- t2.retailPrice,\n" +
                " (CASE WHEN  (t2.retailPrice) IS NOT NULL  THEN t2.retailPrice\n" +
                "       ELSE '0.00'\n" +
                "       END) AS t2retailPrice,\n" +
                " (CASE WHEN  (t2.number*t2.price) IS NOT NULL  THEN Round(t2.number*t2.price,2)\n" +
                "       ELSE '0.00'\n" +
                "       END) AS t2priceNumber,\n" +
                " (CASE WHEN  (t2.number*t2.retailPrice) IS NOT NULL  THEN Round(t2.number*t2.price,2)\n" +
                "       ELSE '0.00'\n" +
                "       END) AS t2retailPriceNumber,\n" +
                " t2.QUANTITY AS t2QUANTITY,\n" +
                " t2.splitQuantity AS t2splitQuantity,\n" +
                " t2.BasicUnit AS t2BasicUnit,\n" +
                " t2.unit AS t2unit,\n" +
                " t2.curNumber,FLOOR(t2.curNumber/t2.ratio) AS maxNumber,\n" +
                "\t\t\t\t\t\t\t\tt2.curNumber-(t2.curNumber/t2.ratio) AS minNumber \n" +
                "FROM\n" +
                "\tmd_inventory_view mdinventor0_  LEFT JOIN md_inventory_en_out_log_view t2 ON mdinventor0_.wi_id =t2.wiId LEFT JOIN md_materiel_info t3 ON t3.wms_mi_id=t2.wmsMiId\n" +
                "WHERE \n" +
                "\tmdinventor0_.state = 1 ";
        if (wiId!=null){
            sql+=" AND mdinventor0_.wi_id in("+wiId+")";
        }
        if (brand!=null&&!brand.equals("")){
            sql+=" AND t2.brand='"+brand+"'";
        }
        if(rba!=null){
            sql+=" AND mdinventor0_.rba_id='"+rba+"'";
        }
        if(rbb!=null){
            sql+=" AND mdinventor0_.rbb_id='"+rbb+"'";
        }
        if(rbs!=null){
            sql+=" AND mdinventor0_.rbs_id='"+rbs+"'";
        }
        if (purchaseType!=null&&!purchaseType.equals("")){
           sql+="AND ( mdinventor0_.purchase_type LIKE '%"+purchaseType+"%' )";
        }
        if (createDate1!=null&&!createDate1.equals("")){
            sql+=" AND t2.createDate1>='"+createDate1+" 00:00:00'";
            sql+=" AND t2.createDate1<='"+createDate1+" 23:59:59'";
        }
        if (searchName!=null&&!searchName.equals("")){
            sql+= " AND (mdinventor0_.mat_name LIKE '%"+searchName+"%' OR mdinventor0_.mat_code LIKE '%"+searchName+"%' OR mdinventor0_.mmf_name LIKE '%"+searchName+"%')";
        }
        if (InventoryStart!=null&&!InventoryStart.equals("")){
            sql+= " AND mdinventor0_.QUANTITY>"+Integer.parseInt(InventoryStart);
        }
        if (InventoryStart!=null&&!InventoryStart.equals("")){
            sql+= " AND mdinventor0_.QUANTITY<"+Integer.parseInt(InventoryEnd);
        }
        sql+=" GROUP BY t2.cCode,t2.mmfId\n" +
                "\tORDER BY\n" +
                "\tmdinventor0_.edit_date DESC ";
        return this.getJdbcDao().query(sql);
    }

    //仓管角色导出入库管理模块报表  调整完善报表中的数据
    public List<Map<String, Object>> exportWarehousingInfoList(Integer wewId,String warehousCode,String select1,String remarks,String operationDate,String wewId1) throws HSKDBException{
        String sql="SELECT\n" +
                "\tt2.wew_id AS a1,\n" +
                "\tt2.Billcode AS a2,\n" +
                "\tt2.bill_type AS a3, \n" +
                "\t(mdenterwar0_.price*mdenterwar0_.mat_number) AS a4,##采购金额\n" +
                "\tt2.warehousing_remarks AS a5,\n" +
                "\t(mdenterwar0_.retail_money*mdenterwar0_.mat_number) AS a6,##零售金额\n" +
                "\tt2.invoice_code AS a7,\n" +
                "\tt2.create_ren AS a8,\n" +
                "\tt2.create_date AS a9,\tt4.mat_number AS mat14_87_,##订单数量\n" +
                "\tt4.number2 AS number27_87_,##发货数量\n" +
                "\tmdenterwar0_.mat_number AS mat_number231,##入库数量\n" +
                "mdenterwar0_.mat_code AS mat33_50_,##物料号\n" +
                "mdenterwar0_.mat_name AS mat34_50_,##物料名称\n" +
                "mdenterwar0_.mmf_name AS mmf44_50_,##物料规格\n" +
                "mdenterwar0_.mmf_code AS mmf42_50_,##物料编号\n" +
                "mdenterwar0_.Basic_unit AS Basic8_50_,##基本单位\n" +
                "mdenterwar0_.unit AS unit,##最小拆分单位\n" +
                "mdenterwar0_.number1 AS number52_50_,##库存数量\n" +
                "mdenterwar0_.price AS price50_,##采购均价\n" +
                "mdenterwar0_.retail_money AS retail_money,##零售均价\n" +
                "-- t2.purchase_money AS t2purchase_money,##采购金额\n" +
                "-- t2.retail_money AS t2retail_money,##采购金额\n" +
                "mdenterwar0_.alias_name AS alias_name,##别名\n" +
                "mdenterwar0_.product_name AS product62_50_,##包装方式\n" +
                "mdenterwar0_.brand AS brand50_,##品牌\n" +
                "mdenterwar0_.mat_type1 AS mat38_50_,##分类\n" +
                "mdenterwar0_.receipt_datetime AS receipt68_50_,##创建时间\n" +
                "mdenterwar0_.Purchase_unit AS Purchase66_50_,##制作部门\n" +
                "mdenterwar0_.create_ren AS create23_50_,##制作人\n" +
                "mdenterwar0_.valied_date AS yxq,##有效期\n" +
                "mdenterwar0_.Basic_unit_accuracy as zcCode,##注册证有效期/备案日期\n" +
                "mdenterwar0_.Serial_number as serialNumbe,##货号\n" +
                "mdenterwar0_.standard as standard,##产品标准\n" +
                "mdenterwar0_.materiel_name as materiel_name,##材质\n" +
                "mdenterwar0_.back_Printing as back_Printing,##注册号\n" +
                "mdenterwar0_.applicant_Name as applicant_Name,##生产企业\n" +
                "mdenterwar0_.Corporate_domicile as Corporate_domicile,##产地\n" +
                "mdenterwar0_.ingredient as ingredient,##主要成分\n" +
                "mdenterwar0_.product_use as product_use,##产品用途\n" +
                "mdenterwar0_.valid_period_unit as valid_period_unit,##保质期\n" +
                "mdenterwar0_.weight as weight,##重量\n" +
                "mdenterwar0_.batch_code as batch_code,##批号\n" +
                "mdenterwar0_.remark as remark,t3.order_code AS orderCode,t2.warehousing_remarks\n" +
                "FROM\n" +
                "\tmd_enterwarehousemx_materiel mdenterwar0_  LEFT JOIN md_enter_warehouse t2  ON t2.wew_id=mdenterwar0_.wew_id LEFT JOIN md_order_info t3 ON t3.order_code=t2.Relation_billCode  LEFT JOIN md_order_mx t4 ON t3.moi_id=t4.moi_id\n" +
                "WHERE\n" +
                "\t1 = 1 ";
        if (wewId!=null){
            sql+=" AND t2.wew_id="+wewId;
        }
        if (wewId1!=null&&!wewId1.equals("")){
            sql+=" AND t2.wew_id in ("+wewId1+")";
        }
        if (warehousCode!=null&&!warehousCode.equals("")){
            sql+=" AND t2.Billcode= '"+warehousCode+"'";
        }
        if (select1!=null&&!select1.equals("")){
//            sql+=" AND t2.bill_type="+select1;
            if (select1=="0"){

            }else if (select1=="1"){
                sql+=" AND t2.bill_type='1'";
            }else if (select1=="2"){
                sql+=" AND t2.bill_type='2'";
            }
        }
        if(operationDate!=null&&!operationDate.equals("")){
            String[] rangeDate = operationDate.split("~");
            String startDate = rangeDate[0].trim();
            String endDate = null;
            if (rangeDate.length > 1) {
                endDate = rangeDate[1].trim();
            }
            sql+=" AND create_date>= '"+startDate+" 00:00:00'";
            if (endDate != null)
                sql+=" AND create_date<= '"+endDate+" 23:59:59'";
        }
        if (remarks!=null&&!remarks.equals("")){
            sql+=" AND (mdenterwar0_.mat_code like '%"+remarks+"%' OR mdenterwar0_.mat_name like '%"+remarks+"%' OR mdenterwar0_.mmf_code like '%"+remarks+"%' )";
        }
        sql+=" GROUP BY t2.wew_id,mdenterwar0_.mat_name,mdenterwar0_.mmf_name  ORDER BY t2.create_date DESC";
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportPriceAdjustmentInv(String paiIds,String checkCode,String searchName,String select) throws HSKDBException{
        String sql="SELECT\n" +
                "\tt2.create_date AS create2_63_,\n" +
                "\tt2.create_ren AS create3_63_,\n" +
                "\tt2.pai_code,\n" +
                "\tmdinventor0_.mat_code AS mat12_60_,\n" +
                "mdinventor0_.mat_name AS mat13_60_,\n" +
                "mdinventor0_.brand AS brand60_,\n" +
                "mdinventor0_.mmf_name AS mmf16_60_,\n" +
                "mdinventor0_.mmf_code AS mmf14_60_,mdinventor0_.mmf_id, \n" +
                "mdinventor0_.basic_unit AS basic5_60_, \n" +
//                "mdinventor0_.avg_price AS avg3_60_,\n" +
                "mdinventor0_.price AS origion17_60_, ##原零售价\n" +
                "mdinventor0_.retail_price AS retail27_60_,##现零售价\n" +
                "(mdinventor0_.retail_price-mdinventor0_.price)AS lackPrice,##差价\n" +
                "(mdinventor0_.percent) AS basePrice,##百分比\n" +
                "mdinventor0_.back_Printing AS back4_60_,##注册证号\n" +
                "mdinventor0_.batch_code AS batch6_60_,##批号\n" +
                "mdinventor0_.remark AS remark60_##\n" +
                "\n" +
                "FROM\n" +
                "\tmd_inventory_extend_pa_ex mdinventor0_  LEFT JOIN md_inventory_price_adjustment t2 ON mdinventor0_.pai_id=t2.pai_id\n" +
                "WHERE\n" +
                "\t1 = 1 ";
        if (paiIds!=null&&!paiIds.equals("")){
            sql+=" AND mdinventor0_.pai_id in("+paiIds+")";
        }
        sql+=" AND mdinventor0_.rba_id = 50 \n" +
                "\tAND mdinventor0_.rbs_id = 22 \n" +
                "ORDER BY\n" +
                "\tmdinventor0_.create_date DESC ";
        return this.getJdbcDao().query(sql);
    }
    //调价中采购均价
//    SELECT
//    sum( base_number ) AS base_number,
//    avg_price AS avg_price,
//    mmf_id AS mmf_id
//            FROM
//    md_inventory_extend_view
//            WHERE
//	1 = 1
    public Double exportPriceAdjustmentInv2(Integer mmfId,Integer rbaId, Integer rbsId, Integer rbbId,String purchaseType) throws HSKDBException{
        String sql="\t\n" +
                "\tSELECT\n" +
                "\tavg_price AS avg_price\n" +
                "FROM\n" +
                "\tmd_inventory_extend_view \n" +
                "WHERE\n" +
                "\t1 = 1 ";
//        if (rbaId != null)
//            sql += " and rba_id = " + rbaId;
//        if (rbsId != null)
//            sql += " and rbs_id = " + rbsId;
//        if (rbbId != null)
//            sql += " and rbb_id = " + rbbId;
//        if (mmfId!=null){
//            sql += " AND  mmf_id = " + mmfId;
//        }
        if (purchaseType != null && !purchaseType.equals("")) {
            sql += " and purchase_type in (" + purchaseType + ")";
            if (rbaId != null)
                sql += " and rba_id = " + rbaId;
            if (rbsId != null)
                sql += " and rbs_id = " + rbsId;
            if (rbbId != null)
                sql += " and rbb_id = " + rbbId;
        }
        if (mmfId!=null){
            sql += " AND  mmf_id = " + mmfId;
        }
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Double.parseDouble(list.get(0).get("avg_price").toString());
        }
        return 0.00;
    }

    public List<Map<String, Object>> exportOutWarehousing(Integer mooId,Integer wowId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws HSKDBException{
         String  sql=" SELECT  t1.wow_code AS wowCode,t1.FINSH_DATE AS FINSH_DATE,\n" +
                    "\t( CASE WHEN t1.wow_type = '1' AND t1.wow_id IS NOT NULL THEN '领料出库' WHEN wow_type = '2' AND t1.wow_id IS NOT NULL THEN '退货出库' WHEN wow_type = '3' AND t1.wow_id IS NOT NULL THEN '报损出库' WHEN t1.wow_type IS NULL AND t1.wow_id IS NOT NULL THEN '领料出库'  ELSE '领料出库' END ) AS wow_type,\nt2.flow_state,t1.GROUP_name as groupName,t1.USER_name AS userName,t3.mat_code AS mat_code,t3.mat_name AS mat_name,t3.norm AS norm,t4.brand AS brand, t4.product_name AS productName,t2.number2 AS baseNumber1,t3.create_ren createRen,t5.price AS price,\n" +
                    "(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = t3.mmf_id) AS retailPrice,(t5.price*t6.number1)AS prices ,(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = t3.mmf_id)*t6.number1 AS retailPrices,t4.back_Printing as back_Printing,t4.batch_code AS batch_code,t4.valied_date,t2.moo_code as moo_code,t2.create_date AS create_date,t2.number1 AS baseNumber2,(t2.number1-t2.number2) AS lacknumber,t1.wow_remarks AS wow_remarks\n" +
                    "\n" +
                    " FROM  md_out_warehouse t1  \n" +
                    " LEFT JOIN md_out_order t2 ON t1.RELATED_BILL1=t2.moo_code  \n" +
                    " LEFT JOIN md_out_warehouse_mx t3 ON t1.wow_id=t3.wow_id  \n" +
                    " LEFT JOIN md_materiel_info t4 ON t3.wms_mi_id=t4.wms_mi_id  \n" +
                    " LEFT JOIN md_inventory_extend_view t5 ON  t5.wms_mi_id=t3.wms_mi_id\n" +
                    " LEFT JOIN md_out_order_mx t6 ON t2.moo_id=t6.moo_id\n" +
                    " where 1=1 ";
            sql+=" AND (t1.wow_id in("+wowIds+") or t2.moo_id in("+mooIds+"))";
            sql+="  GROUP BY t3.wow_mx_id";
            return this.getJdbcDao().query(sql);
    }
//        String  sql=" SELECT  t1.wow_code AS wowCode,t1.FINSH_DATE AS FINSH_DATE,\n" +
//                            "\t( CASE WHEN t1.wow_type = '1' AND t1.wow_id IS NOT NULL THEN '领料出库' WHEN wow_type = '2' AND t1.wow_id IS NOT NULL THEN '退货出库' WHEN wow_type = '3' AND t1.wow_id IS NOT NULL THEN '报损出库' WHEN t1.wow_type IS NULL AND t1.wow_id IS NOT NULL THEN '领料出库'  ELSE '领料出库' END ) AS wow_type,\nt2.flow_state,t1.GROUP_name as groupName,t1.USER_name AS userName,t3.mat_code AS mat_code,t3.mat_name AS mat_name,t3.norm AS norm,t4.brand AS brand, t4.product_name AS productName,t2.number2 AS baseNumber1,t3.create_ren createRen,t5.price AS price,\n" +
//                            "(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = t3.mmf_id) AS retailPrice,(t5.price*t6.number1)AS prices ,(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = t3.mmf_id)*t6.number1 AS retailPrices,t4.back_Printing as back_Printing,t4.batch_code AS batch_code,t4.valied_date,t2.moo_code as moo_code,t2.create_date AS create_date,t2.number1 AS baseNumber2,(t2.number1-t2.number2) AS lacknumber,t1.wow_remarks AS wow_remarks\n" +
//                            "\n" +
//                            " FROM  md_out_warehouse t1  \n" +
//                            " LEFT JOIN md_out_order t2 ON t1.RELATED_BILL1=t2.moo_code  \n" +
//                            " LEFT JOIN md_out_warehouse_mx t3 ON t1.wow_id=t3.wow_id  \n" +
//                            " LEFT JOIN md_materiel_info t4 ON t3.wms_mi_id=t4.wms_mi_id  \n" +
//                            " LEFT JOIN md_inventory_extend_view t5 ON  t5.wms_mi_id=t3.wms_mi_id\n" +
//                            " LEFT JOIN md_out_order_mx t6 ON t2.moo_id=t6.moo_id\n" +
//                            " where 1=1 ";
//                    if (mooId!=null){
//                        sql+=" AND t2.moo_id="+mooId;
//                    }
//                    if (wowId!=null){
//                        sql+=" AND t1.wow_id="+wowId;
//                    }
//                    if (mooIds!=null&&!mooIds.equals("")){
//                        if (wowIds!=null&&!wowIds.equals("")){
//                            sql+=" AND (t2.moo_id in("+mooIds+") or t1.wow_id in("+wowIds+"))";
//                        }else {
//                            sql+=" AND t2.moo_id in("+mooIds+")";
//                        }
//                    }
//                    if (wowIds!=null&&!wowIds.equals("")){
//                        if (mooIds!=null&&!mooIds.equals("")){
//                            sql+=" AND (t1.wow_id in("+wowIds+") or t2.moo_id in("+mooIds+"))";
//                        }
//                        else {
//                            sql+=" AND t1.wow_id in("+wowIds+")";
//                        }
//                    }
//                    if (mooCode!=null&&!mooCode.equals("")){
//                        sql+=" AND t2.moo_code like '%"+ mooCode+"%'";
//                    }
//                    if (customer!=null&&!customer.equals("")){
//                        sql+=" AND t2.group_name LIKE '%"+customer+ "%'";
//                    }
//                    if (customerName!=null&&!customerName.equals("")){
//                        sql+=" AND t2.user_name LIKE '%"+customerName+ "%'";
//                    }
//                    if (orderTimeStart!=null&&!orderTimeStart.equals("")){
//                        sql+=" AND t2.order_time>='"+orderTimeEnd+"'";
//                    }
//                    if (orderTimeEnd!=null&&!orderTimeEnd.equals("")){
//                        sql+=" AND t2.order_time<='"+orderTimeEnd+"'";
//                    }
//
//                    if (outTimeStart!=null&&!outTimeStart.equals("")){
//                        sql+=" AND t2.FINSH_DATE>='"+outTimeStart+"'";
//                    }
//                    if (outTimeEnd!=null&&!outTimeEnd.equals("")){
//                        sql+=" AND t2.FINSH_DATE<='"+outTimeEnd+"'";
//                    }
//                    if (flowState!=null&&!flowState.equals("")){
//                        sql+=" AND t2.flow_state='"+flowState+"'";
//                    }
//                    sql+="  GROUP BY t3.wow_mx_id";

        /* 分割线+==============*/
//                if (mooIds!=null){
//                     sql=" SELECT\n" +
//                             "\tt1.wow_code AS wowCode,\n" +
//                             "\tt1.FINSH_DATE AS FINSH_DATE,\n" +
//                             "\t( CASE WHEN t1.wow_type = '1' AND t1.wow_id IS NOT NULL THEN '领料出库' WHEN wow_type = '2' AND t1.wow_id IS NOT NULL THEN '退货出库' WHEN wow_type = '3' AND t1.wow_id IS NOT NULL THEN '报损出库' WHEN t1.wow_type IS NULL AND t1.wow_id IS NOT NULL THEN '领料出库'  ELSE '领料出库' END ) AS wow_type,\n" +
//                             "\tt2.flow_state,\n" +
//                             "\tt2.group_name AS groupName,\n" +
//                             "\tt2.user_name AS userName,\n" +
//                             "\tt6.mat_code AS mat_code,\n" +
//                             "\tt6.mat_name AS mat_name,\n" +
//                             "\tt6.norm AS norm,\n" +
//                             "\tt4.brand AS brand,\n" +
//                             "\tt4.product_name AS productName,\n" +
//                             "\tt2.number2 AS baseNumber1,\n" +
//                             "\tt3.create_ren createRen,\n" +
//                             "\tt5.price AS price,\n" +
//                             "\t( SELECT a.retail_price FROM md_materiel_format a WHERE a.mmf_id = t3.mmf_id ) AS retailPrice,\n" +
//                             "\t( t5.price * t6.number1 ) AS prices,\n" +
//                             "\t( SELECT a.retail_price FROM md_materiel_format a WHERE a.mmf_id = t3.mmf_id ) * t6.number1 AS retailPrices,\n" +
//                             "\tt4.back_Printing AS back_Printing,\n" +
//                             "\tt4.batch_code AS batch_code,\n" +
//                             "\tt4.valied_date,\n" +
//                             "\tt2.moo_code AS moo_code,\n" +
//                             "\tt2.create_date AS create_date,\n" +
//                             "\tt2.number1 AS baseNumber2,\n" +
//                             "\t( t2.number1 - t2.number2 ) AS lacknumber,\n" +
//                             "\tt1.wow_remarks AS wow_remarks \n" +
//                             "FROM\n" +
//                             "\tmd_out_order t2 \n" +
//                             "\tLEFT JOIN md_out_warehouse t1 ON t1.RELATED_BILL1 = t2.moo_code\n" +
//                             "\tLEFT JOIN md_out_order_mx t6 ON t2.moo_id = t6.moo_id \n" +
//                             "\tLEFT JOIN md_out_warehouse_mx t3 ON t1.wow_id = t3.wow_id\n" +
//                             "\tLEFT JOIN md_materiel_info t4 ON t6.wms_mi_id = t4.wms_mi_id\n" +
//                             "\tLEFT JOIN md_inventory_extend_view t5 ON t5.wms_mi_id = t3.wms_mi_id\n" +
//                             "WHERE\n" +
//                             "\t1 = 1 ";
//                    if (mooId!=null){
//                        sql+=" AND t2.moo_id="+mooId;
//                    }
//                    if (wowId!=null){
//                        sql+=" AND t1.wow_id="+wowId;
//                    }
//                    if (mooIds!=null&&!mooIds.equals("")){
//                        if (wowIds!=null&&!wowIds.equals("")){
//                            sql+=" AND (t2.moo_id in("+mooIds+") or t1.wow_id in("+wowIds+"))";
//                        }else {
//                            sql+=" AND t2.moo_id in("+mooIds+")";
//                        }
//                    }
//                    if (wowIds!=null&&!wowIds.equals("")){
//                        if (mooIds!=null&&!mooIds.equals("")){
//                            sql+=" AND (t1.wow_id in("+wowIds+") or t2.moo_id in("+mooIds+"))";
//                        }
//                        else {
//                            sql+=" AND t1.wow_id in("+wowIds+")";
//                        }
//                    }
//                    if (mooCode!=null&&!mooCode.equals("")){
//                        sql+=" AND t2.moo_code like '%"+ mooCode+"%'";
//                    }
//                    if (customer!=null&&!customer.equals("")){
//                        sql+=" AND t2.group_name LIKE '%"+customer+ "%'";
//                    }
//                    if (customerName!=null&&!customerName.equals("")){
//                        sql+=" AND t2.user_name LIKE '%"+customerName+ "%'";
//                    }
//                    if (orderTimeStart!=null&&!orderTimeStart.equals("")){
//                        sql+=" AND t2.order_time>='"+orderTimeEnd+"'";
//                    }
//                    if (orderTimeEnd!=null&&!orderTimeEnd.equals("")){
//                        sql+=" AND t2.order_time<='"+orderTimeEnd+"'";
//                    }
//
//                    if (outTimeStart!=null&&!outTimeStart.equals("")){
//                        sql+=" AND t2.FINSH_DATE>='"+outTimeStart+"'";
//                    }
//                    if (outTimeEnd!=null&&!outTimeEnd.equals("")){
//                        sql+=" AND t2.FINSH_DATE<='"+outTimeEnd+"'";
//                    }
//                    if (flowState!=null&&!flowState.equals("")){
//                        sql+=" AND t2.flow_state='"+flowState+"'";
//                    }
//                    sql+="  GROUP BY t3.wow_mx_id";

    //仓管导出出库批量导出
    public List<Map<String, Object>> plExportOutWarehousing(Integer stateMooId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws HSKDBException{
//        if (mooIds!=null&&)
        String sql="";
            sql=" SELECT\n" +
                    " c.* \n" +
                    "FROM\n" +
                    " (\n" +
                    " SELECT\n" +
                    " c.mat_code as matCode,\n" +
                    " c.mat_name AS matName,\n" +
                    " ( SELECT f.mmf_name FROM md_materiel_format f WHERE d.mmf_id = f.mmf_id ) AS NORM,( SELECT f.price FROM md_materiel_format f WHERE d.mmf_id = f.mmf_id ) AS price,\n" +
                    "\t(SELECT IFNULL(f.retail_price,0.00) FROM md_materiel_format f WHERE d.mmf_id = f.mmf_id ) AS retail_price,\n" +
                    " c.brand,\n" +
                    " c.product_name,\n" +
                    " NULL AS outNumber,\n" +
                    " NULL AS outRen,\n" +
                    " \tc.back_Printing AS backPrinting,\n" +
                    "\tc.batch_code,\n" +
                    "\tc.valied_date,\n" +
                    "\tb.moo_code AS mooCode,\n" +
                    "\tb.create_date AS createDate2,\n" +
                    "\td.base_number AS sqNumber,\n" +
                    "\t(d.base_number-0) AS lackNumber,IFNULL(d.split_quantity,0.00) AS split_quantity,\n" +
                    "\t\t0.00 AS split_number1,\n" +
                    "\t\t(IFNULL(d.split_quantity,0.00)-0.00) AS lacksplitNumber,c.split_unit,NULL AS wow_remarks,\tc.Basic_unit,\n" +
                    "--  d.number1 AS number1s,\n" +
                    "--   d.number2 AS number2s,\n" +
                    "  b.moo_id AS mooId,\n" +
                    "  date_format( b.order_time, '%Y-%m-%d %H:%i:%s' ) AS orderTime,\n" +
                    "--   b.moo_code AS mooCode,\n" +
                    "  b.group_name AS groupName,\n" +
                    "  b.user_name AS userName,\n" +
                    "  b.number1 AS number1,\n" +
                    "  b.number2 AS number2,\n" +
                    "  b.flow_state AS flowState,\n" +
                    "  null AS wowId,\n" +
                    "  a.wow_remarks AS wowRemarks,\n" +
                    "  a.wow_type AS wowType,\n" +
                    "  a.wow_code AS wowCode,\n" +
                    "  a.receiving_object AS receivingObject,\n" +
                    "  date_format( a.finsh_date, '%Y-%m-%d %H:%i:%s' ) AS finishDate,\n" +
                    "  a.RELATED_BILL1 AS relatedBill1,\n" +
                    "  b.rba_id AS rbaId,\n" +
                    "  b.rbs_id AS rbsId,\n" +
                    "  b.rbb_id AS rbbId,\n" +
                    "  b.purchase_type AS purchaseType,\n" +
                    "  a.create_date AS createDate,\n" +
                    "  NULL AS moiId,\n" +
                    "  NULL AS wiId,\n" +
                    "  NULL AS wewId,\n" +
                    "  b.create_ren AS createRen,\n" +
                    "  NULL AS missNumber,\n" +
                    "  b.state AS state \n" +
                    "\t\n" +
                    " FROM\n" +
                    "  md_out_order b\n" +
                    "  LEFT JOIN md_out_warehouse a ON b.moo_code = a.RELATED_BILL1 left join md_out_order_mx d on b.moo_id = d.moo_id LEFT JOIN md_materiel_info c on d.wms_mi_id = c.wms_mi_id\n" +
                    " WHERE 1=1\n" +
//                    "  b.number1 > 0 \n" +
                    "  GROUP BY d.mom_id UNION ALL\n" +
                    " SELECT \n" +
                    " c.mat_code as matCode,\n" +
                    "  c.mat_name AS matName,\n" +
                    "\t  ( SELECT f.mmf_name FROM md_materiel_format f WHERE b.mmf_id = f.mmf_id ) AS NORM,( SELECT f.price FROM md_materiel_format f WHERE b.mmf_id = f.mmf_id ) AS price,\n" +
                    "\t(SELECT IFNULL(f.retail_price,0.00) FROM md_materiel_format f WHERE b.mmf_id = f.mmf_id ) AS retail_price,\n" +
                    "\t\t c.brand,\n" +
                    " c.product_name,\n" +
                    " IFNULL(b.base_number,0.00) AS outNumber,\n" +
                    "  b.create_ren AS outRen,\n" +
                    "\tc.back_Printing AS backPrinting,\n" +
                    "\tc.batch_code,\n" +
                    "\tc.valied_date,\n" +
                    "\ta.RELATED_BILL1 AS mooCode,\n" +
                    "d.create_date AS createDate2,\n" +
                    "\tb.base_number AS sqNumber,\n" +
                    "\t(b.base_number-b.number1) AS lackNumber,IFNULL(b.split_number ,0.00) AS split_quantity,\n" +
                    "\t\tIFNULL(b.split_number ,0.00) AS split_number1,\n" +
                    "\t\t 0.00 AS lacksplitNumber,c.split_unit,a.wow_remarks,\tc.Basic_unit,\n" +
                    "-- null AS number1s,\n" +
                    "-- b.base_number AS number2s,\n" +
                    " NULL AS mooId,\n" +
                    "  a.create_date AS orderTime,\n" +
                    "--   a.RELATED_BILL1 AS mooCode,\n" +
                    "  a.group_name AS groupName,\n" +
                    "  a.user_name AS userName,\n" +
                    "  \td.number1 AS number1,\n" +
                    "  a.base_number AS number2,\n" +
                    "  4 AS flowState,\n" +
                    "  a.wow_id AS wowId,\n" +
                    "  a.wow_remarks AS wowRemarks,\n" +
                    "  a.wow_type AS wowType,\n" +
                    "  a.wow_code AS wowCode,\n" +
                    "  a.receiving_object AS receivingObject,\n" +
                    "  date_format( a.finsh_date, '%Y-%m-%d %H:%i:%s' ) AS finishDate,\n" +
                    "  a.RELATED_BILL1 AS relatedBill1,\n" +
                    "  a.rba_id AS rbaId,\n" +
                    "  a.rbs_id AS rbsId,\n" +
                    "  a.rbb_id AS rbbId,\n" +
                    "  a.purchase_type AS purchaseType,\n" +
                    "  a.create_date AS createDate,\n" +
                    "  a.moi_id AS moiId,\n" +
                    "  a.wi_id AS wiId,\n" +
                    "  a.wew_id AS wewId,\n" +
                    "  a.create_ren AS createRen,\n" +
                    "  ( d.number1 - d.number2 ) AS missNumber,\n" +
                    "  1 AS state\n" +
                    " FROM\n" +
                    "  md_out_warehouse a left join md_out_warehouse_mx b on a.wow_id = b.wow_id  LEFT JOIN md_out_order d ON d.moo_code = a.RELATED_BILL1  left join md_materiel_info c on c.wms_mi_id=b.wms_mi_id where 1=1 GROUP BY b.wow_mx_id)  c\n" +
                    "\tWHERE\n" +
                    " 1 = 1 \n" +
                    " AND state = 1  ";
//            if (wowIds!=null&&!wowIds.equals("")&&mooIds!=null&&!mooIds.equals("")){
////
////            }
//        if (!wowIds.equals("0")&&!mooIds.equals("0")){
////            sql+="  AND (wowId in("+wowIds+") or mooId in("+mooIds+"))\n";
////        }
        if (stateMooId!=1){
            sql+="  AND (wowId in("+wowIds+") or mooId in("+mooIds+"))\n";
        }
//        if (stateMooId!=1){
//        }else {
//            sql+=" GROUP BY c.Norm,c.wowCode";
//        }
         sql+= " GROUP BY c.NORM,c.flowState,c.mooCode,c.sqNumber ORDER BY\n" +
                    "\tc.orderTime DESC";
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportOutWarehousingMooIdAndWowId(Integer mooId,Integer wowId) throws HSKDBException{
        String sql="";
       if (mooId!=null){
            sql=" SELECT\n" +
                    "\tt1.wow_code AS wowCode,\n" +
                    "\tt1.FINSH_DATE AS FINSH_DATE,\n" +
                    "\t( CASE WHEN t1.wow_type = '1' AND t1.wow_id IS NOT NULL THEN '领料出库' WHEN wow_type = '2' AND t1.wow_id IS NOT NULL THEN '退货出库' WHEN wow_type = '3' AND t1.wow_id IS NOT NULL THEN '报损出库' WHEN t1.wow_type IS NULL AND t1.wow_id IS NOT NULL THEN '领料出库'  ELSE '领料出库' END ) AS wow_type,\n" +
                    "\tIF(t6.base_number-t6.number1=0 AND t6.split_number1-t6.split_quantity=0, 4, t2.flow_state) AS flow_state,\n" +
                    "\tt2.group_name AS groupName,\n" +
                    "\tt2.user_name AS userName,\n" +
                    "\tt6.mat_code AS mat_code,\n" +
                    "\tt6.mat_name AS mat_name,\n" +
                    "\tt6.norm AS norm,\n" +
                    "\tt4.brand AS brand,\n" +
                    "\tt4.product_name AS productName,\n" +
                    "\tt6.number1 AS baseNumber1,t6.Basic_unit,\n" +
                    "\tt3.create_ren createRen,\n" +
                    "\t( SELECT a.price FROM md_materiel_format a WHERE a.mmf_id = t6.mmf_id ) AS price,\n" +
                    "\t( SELECT a.retail_price FROM md_materiel_format a WHERE a.mmf_id = t6.mmf_id ) AS retailPrice,\n" +
                    "\t( ( SELECT a.price FROM md_materiel_format a WHERE a.mmf_id = t6.mmf_id ) * t6.number1 ) AS prices,\n" +
                    "\t( SELECT a.retail_price FROM md_materiel_format a WHERE a.mmf_id = t6.mmf_id ) * t6.number1 AS retailPrices,\n" +
                    "\tt4.back_Printing AS back_Printing,\n" +
                    "\tt4.batch_code AS batch_code,\n" +
                    "\tt4.valied_date,\n" +
                    "\tt2.moo_code AS moo_code,\n" +
                    "\tt2.create_date AS create_date,\n" +
                    "\tt6.base_number AS baseNumber2,\n" +
                    "\t(t6.base_number - IFNULL(t6.number1,0) ) AS lacknumber,\n" +
                    "\tt1.wow_remarks AS wow_remarks,t6.split_quantity,\n" +
                    "\t\t\t\t\t\tt4.split_unit,\n" +
                    "\t\t\t\t\t\tt6.split_number1 AS split_number,\n" +
                    "\t\t\t\t\t\t( t6.split_quantity - IFNULL(t6.split_number1,0) ) AS lackSplitNumber \n" +
                    "FROM\n" +
                    "\tmd_out_order t2 \n" +
                    "\tLEFT JOIN md_out_warehouse t1 ON t1.RELATED_BILL1 = t2.moo_code\n" +
                    "\tLEFT JOIN md_out_order_mx t6 ON t2.moo_id = t6.moo_id \n" +
                    "\tLEFT JOIN md_out_warehouse_mx t3 ON t1.wow_id = t3.wow_id\n" +
                    "\tLEFT JOIN md_materiel_info t4 ON t6.wms_mi_id = t4.wms_mi_id\n" +
                    "\tLEFT JOIN md_inventory_extend_view t5 ON t5.wms_mi_id = t3.wms_mi_id\n" +
                    "WHERE\n" +
                    "\t1 = 1 ";
            sql+=" AND t2.moo_id="+mooId;
            sql+=" \tGROUP BY\n" +
                    "\tt6.mat_name,t6.norm";
        }
        else  if (wowId!=null){
            sql=" SELECT  t1.wow_code AS wowCode,t1.FINSH_DATE AS FINSH_DATE,\n" +
                    "\t( CASE WHEN t1.wow_type = '1' AND t1.wow_id IS NOT NULL THEN '领料出库' WHEN wow_type = '2' AND t1.wow_id IS NOT NULL THEN '退货出库' WHEN wow_type = '3' AND t1.wow_id IS NOT NULL THEN '报损出库' WHEN wow_type = '4' \n" +
                    "\t\t\t\t\t\tAND t1.wow_id IS NOT NULL THEN\n" +
                    "\t\t\t\t\t\t'退货出库'  WHEN t1.wow_type IS NULL AND t1.wow_id IS NOT NULL THEN '领料出库'  ELSE '领料出库' END ) AS wow_type,\nt2.flow_state,t1.GROUP_name as groupName,t1.USER_name AS userName,t3.mat_code AS mat_code,t3.mat_name AS mat_name,t3.norm AS norm,t4.brand AS brand, t4.product_name AS productName,t3.base_number  AS baseNumber1,t3.Basic_unit,t3.create_ren createRen,t5.price AS price,\n" +
                    "(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = t3.mmf_id) AS retailPrice,(t5.price*t6.number1)AS prices ,(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = t3.mmf_id)*t6.number1 AS retailPrices,t4.back_Printing as back_Printing,t4.batch_code AS batch_code,t4.valied_date,t2.moo_code as moo_code,t2.create_date AS create_date,t3.base_number AS baseNumber2,0.00 AS lacknumber,t1.wow_remarks AS wow_remarks,t3.split_quantity AS split_quantity,\n" +
                    "\t\t\t\t\t\t\tt4.split_unit,\n" +
                    "\t\t\t\t\t\t\tt3.split_quantity as split_number,\n" +
                    "\t\t\t\t\t\t\t0.00 AS lackSplitNumber\n" +
                    "\n" +
                    " FROM  md_out_warehouse t1  \n" +
                    " LEFT JOIN md_out_order t2 ON t1.RELATED_BILL1=t2.moo_code  \n" +
                    " LEFT JOIN md_out_warehouse_mx t3 ON t1.wow_id=t3.wow_id  \n" +
                    " LEFT JOIN md_materiel_info t4 ON t3.wms_mi_id=t4.wms_mi_id  \n" +
                    " LEFT JOIN md_inventory_extend_view t5 ON  t5.wms_mi_id=t3.wms_mi_id\n" +
                    " LEFT JOIN md_out_order_mx t6 ON t2.moo_id=t6.moo_id\n" +
                    " where 1=1 ";
            sql+=" AND t1.wow_id="+wowId;
            sql+= " GROUP BY t3.mat_name,t3.mmf_id";
        }
        return this.getJdbcDao().query(sql);
    }
        //库存导出日志中的出库明细
    public List<Map<String, Object>> exportWzidExcel(Integer wiId) throws HSKDBException{
        String sql="SELECT\n" +
                "\tc.* \n" +
                "FROM\n" +
                "\t(\n" +
                "\tSELECT\n" +
                "\t\ta.c_code AS cCode,\n" +
                "\t\ta.mmf_id AS mmfId1,\n" +
                "\t\ta.create_date AS createDate1,\n" +
                "\t\ta.number AS number,\n" +
                "\t\ta.stype AS stype,\n" +
                "\t\ta.cur_number AS curNumber,\n" +
                "\t\te.mie_id AS mieId,\n" +
                "\t\ta.mx_id AS mxId,\n" +
                "\t\te.wi_id AS wiId,\n" +
                "\t\te.wms_mi_id AS wmsMiId,\n" +
                "\t\te.mmf_id AS mmfId,\n" +
                "\t\te.price AS price,\n" +
                "\t\te.base_price AS basePrice,\n" +
                "\t\te.retail_price AS retailPrice,\n" +
                "\t\te.QUANTITY AS QUANTITY,\n" +
                "\t\te.split_quantity AS splitQuantity,\n" +
                "\t\te.Basic_unit AS BasicUnit,\n" +
                "\t\te.unit AS unit,\n" +
                "\t\te.base_number AS baseNumber,\n" +
                "\t\ta.related_code AS relatedCode,\n" +
                "\t\te.purchase_user AS purchaseUser,\n" +
                "\t\tdate_format( e.create_date, '%Y-%m-%d %H:%i:%s' ) AS createDate,\n" +
                "\t\tdate_format( e.edit_date, '%Y-%m-%d %H:%i:%s' ) AS editDate,\n" +
                "\t\te.mat_name AS matName,\n" +
                "\t\te.mmf_name AS mmfName,\n" +
                "\t\te.mat_name2 AS matName2,\n" +
                "\t\te.norm2 AS norm2,\n" +
                "\t\te.mat_type AS matType,\n" +
                "\t\te.mat_type1 AS matType1,\n" +
                "\t\te.mat_type2 AS matType2,\n" +
                "\t\te.mat_type3 AS matType3,\n" +
                "\t\te.product_name AS productName,\n" +
                "\t\te.brand AS brand,\n" +
                "\t\te.Label_info AS LabelInfo,\n" +
                "\t\te.applicant_name AS applicantName,\n" +
                "\t\te.state AS state,\n" +
                "\t\te.batch_code AS batchCode,\n" +
                "\t\te.mdp_id AS mdpId,\n" +
                "\t\te.mdn_id AS mdnId,\n" +
                "\t\te.mdd_id AS mddId,\n" +
                "\t\te.mdps_id AS mdpsId,\n" +
                "\t\te.mdn_code AS mdnCode,\n" +
                "\t\te.mdd_name AS mddName,\n" +
                "\t\te.mdn_norm AS mdnNorm,\n" +
                "\t\te.mdn_node AS mdnNode,\n" +
                "\t\ta.create_ren AS createRen,e.price*a.number AS allPrice \n" +
                "\tFROM\n" +
                "\t\tmd_inventory_extend e\n" +
                "\t\tLEFT JOIN (\n" +
                "\t\t\t(\n" +
                "\t\t\tSELECT\n" +
                "\t\t\t\tb.wew_mx_id AS mx_id,\n" +
                "\t\t\t\ta.Billcode AS c_code,\n" +
                "\t\t\t\ta.Relation_billCode AS related_code,\n" +
                "\t\t\t\tb.cur_number AS cur_number,\n" +
                "\t\t\t\tb.mmf_id AS mmf_id,\n" +
                "\t\t\t\tdate_format( a.receipt_datetime, '%Y-%m-%d %H:%i:%s' ) AS create_date,\n" +
                "\t\t\t\tb.mat_number AS number,\n" +
                "\t\t\t\t1 AS stype,\n" +
                "\t\t\t\tb.create_ren AS create_ren \n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tmd_enter_warehouse a\n" +
                "\t\t\t\tLEFT JOIN md_enter_warehouse_mx b ON a.wew_id = b.wew_id \n" +
                "\t\t\t) UNION ALL\n" +
                "\t\t\t(\n" +
                "\t\t\tSELECT\n" +
                "\t\t\t\td.wow_mx_id AS mx_id,\n" +
                "\t\t\t\tc.wow_code AS c_code,\n" +
                "\t\t\t\tc.RELATED_BILL1 AS related_code,\n" +
                "\t\t\t\td.cur_number AS cur_number,\n" +
                "\t\t\t\td.mmf_id AS mmf_id,\n" +
                "\t\t\t\tdate_format( c.FINSH_DATE, '%Y-%m-%d %H:%i:%s' ) AS create_date,\n" +
                "\t\t\t\td.base_number AS number,\n" +
                "\t\t\t\t2 AS stype,\n" +
                "\t\t\t\td.create_ren AS create_ren \n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tmd_out_warehouse c\n" +
                "\t\t\t\tLEFT JOIN md_out_warehouse_mx d ON c.wow_id = d.wow_id \n" +
                "\t\t\t) \n" +
                "\t\t) a ON a.mmf_id = e.mmf_id \n" +
                "\t) AS c \n" +
                "WHERE\n" +
                "\t1 = 1 ";
                if (wiId!=null){
                    sql+=" AND c.wiId ="+wiId;
                }
                sql+=" GROUP BY\n" +
                        "\tcCode,\n" +
                        "\tmmfId \n" +
                        "ORDER BY\n" +
                        "\tcreateDate1";
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportmMdMateriel(String wmsMiIds) throws HSKDBException{
        String sql=" SELECT t1.mat_code AS matCode,t1.mat_name AS matName,t1.brand AS brand,t2.mmf_code,t2.mmf_name,t2.price as retail_price,t1.Basic_unit,t1.number1,t1.state,t1.applicant_Name,t1.create_date,(SELECT t4.mmt_name FROM md_materiel_type t4 WHERE t4.mmt_id=t1.mat_type1) AS matType,t3.model_mat_code,t1.mat_removal_reasons\n";
        sql+=" \tFROM md_materiel_info t1 LEFT JOIN md_materiel_format t2 ON t1.wms_mi_id=t2.wms_mi_id  LEFT JOIN md_model_materiel t3 ON t1.md_model_id=t3.wms_model_id WHERE 1=1\n";
        if (wmsMiIds!=null&&!wmsMiIds.equals("")){
            sql+=" AND t1.wms_mi_id in("+wmsMiIds+")";
        }
        sql+=" ORDER BY t1.mat_code,t1.mat_name DESC";
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> exportmMdMaterielModel(String wmsModelIds) throws HSKDBException{
        String sql=" SELECT t1.model_mat_code,t1.model_mat_name,t1.alias_name,t3.attr_content,t2.mmf_name,t3.can_search,t1.sell_unit,(SELECT count(wms_mi_id) FROM md_materiel_info t4 WHERE t4.md_model_id=t1.wms_model_id) AS count1,t1.state,t1.introduction,t1.create_date,t1.key_word,t1.remark,(SELECT t5.mmt_name FROM md_materiel_type t5 WHERE t5.mmt_id=t1.mat_type1) AS mattype,( SELECT t5.mmt_name FROM md_materiel_type t5 WHERE t5.mmt_id = t1.mat_type2 ) AS mattype2,( SELECT t5.mmt_name FROM md_materiel_type t5 WHERE t5.mmt_id = t1.mat_type3 ) AS mattype3,(SELECT a.mbd_name FROM md_materiel_brand a WHERE a.mbd_id=t1.mbd_id) AS brand,(SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='注册证号' order by t7.createDate desc limit 1) AS back_Printing,\n";
        sql+=" (SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='注册证号有效期' order by t7.createDate desc limit 1) AS Basic_unit_accuracy,";
        sql+=" (SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='包装方式' order by t7.createDate desc limit 1) AS product_name,";
        sql+=" \t\t(SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='生产企业' order by t7.createDate desc limit 1) AS factory,";
        sql+=" (SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='产地' order by t7.createDate desc limit 1) AS origin,";
        sql+=" (SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='保质期' order by t7.createDate desc limit 1) AS guarantee,";
        sql+=" (SELECT t7.mmp_content FROM md_model_parameter_info t7 WHERE t7.md_model_id=t1.wms_model_id AND t7.mmp_name='主要成分' order by t7.createDate desc limit 1) AS components";
        sql+=" FROM md_model_materiel t1 LEFT JOIN md_model_format t2 ON t1.wms_model_id=t2.wms_model_id LEFT JOIN md_model_format_attr_info t3 ON t2.model_mmf_id=t3.model_mmf_id   LEFT JOIN  md_model_parameter_info t6 ON t6.md_model_id=t1.wms_model_id where 1=1 ";
        if (wmsModelIds!=null&&!wmsModelIds.equals("")){
            sql+=" AND t1.wms_model_id in("+wmsModelIds+")";
        }
        //GROUP BY t1.wms_model_id
        sql+=" GROUP BY t2.mmf_name,t1.wms_model_id ORDER BY t1.model_mat_code DESC";
        return this.getJdbcDao().query(sql);
    }
    }

