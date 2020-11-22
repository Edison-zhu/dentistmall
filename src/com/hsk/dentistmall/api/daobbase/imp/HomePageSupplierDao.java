package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IHomePageSupplierDao;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Component
public
class HomePageSupplierDao extends SupperDao  implements IHomePageSupplierDao {
    //  今日订单总数
    public Integer CountOrers(Integer oldId)throws HSKDBException {
        Date newDate=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="select count(*) as countOrers from md_order_info where order_state='1'";
        sql+=" AND wz_id="+oldId;
        sql+=" AND Place_order_time> '"+sdf.format(newDate)+" 00:00:00'";
        sql+=" AND Place_order_time< '"+sdf.format(newDate)+" 23:59:59'";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("countOrers").toString());
        }
        return 0;
    }
    //销售额
    public Double placeOrderMoneys(Integer oldId)throws HSKDBException {
        Date newDate=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="select SUM(Actual_money) as placeOrderMoneys from md_order_info where order_state='1'";
        sql+=" AND wz_id="+oldId;
        sql+=" AND Place_order_time> '"+sdf.format(newDate)+" 00:00:00'";
        sql+=" AND Place_order_time< '"+sdf.format(newDate)+" 23:59:59'";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("placeOrderMoneys")!=null){
            return Double.parseDouble(list.get(0).get("placeOrderMoneys").toString());
        }
        return 0.00;
    }
    //昨日销售额
    public Double RetreatPlaceOrderMoney(Integer oldId)throws HSKDBException {
        Date newDate=new Date();
        Date yestDay=new Date(newDate.getTime()-24*60*60*1000);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="select SUM(Actual_money) as RetreatPlaceOrderMoney from md_order_info where order_state='1'";
        sql+=" AND wz_id="+oldId;
        sql+=" AND Place_order_time> '"+sdf.format(yestDay)+" 00:00:00'";
        sql+=" AND Place_order_time< '"+sdf.format(newDate)+" 23:59:59'";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("RetreatPlaceOrderMoney")!=null){
            return Double.parseDouble(list.get(0).get("RetreatPlaceOrderMoney").toString());
        }
        return 0.00;
    }
    //本月销售额
    public Double RetreatPlaceOrderMoney2(Integer oldId)throws HSKDBException {
        Date newDate=new Date();
        Date month=new Date(newDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="select SUM(Actual_money) as RetreatPlaceOrderMoney2 from md_order_info where order_state='1'";
        sql+=" AND wz_id="+oldId;
        sql+=" AND Place_order_time> '"+sdf.format(month)+" 00:00:00'";
        sql+=" AND Place_order_time< '"+sdf.format(newDate)+" 23:59:59'";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("RetreatPlaceOrderMoney2")!=null){
            return Double.parseDouble(list.get(0).get("RetreatPlaceOrderMoney2").toString());
        }
        return 0.00;
    }
    //本月退款额
    public Double RetreatPlaceOrderMoney3(Integer oldId)throws HSKDBException {
        Date newDate=new Date();
        Date month=new Date(newDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="select SUM(place_order_money) as RetreatPlaceOrderMoney3 from md_order_info where order_state='1'";
        sql+=" AND wz_id="+oldId;
        sql+=" AND Place_order_time> '"+sdf.format(month)+" 00:00:00'";
        sql+=" AND Place_order_time< '"+sdf.format(newDate)+" 23:59:59'";
        sql+=" AND Process_status=6";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("RetreatPlaceOrderMoney3")!=null){
            return Double.parseDouble(list.get(0).get("RetreatPlaceOrderMoney3").toString());
        }
        return 0.00;
    }
    @Override
    public Integer countOrderInfoByProcessStateTwo(Integer PurchaseId,String processState, MdOrderInfo att_MdOrderInfo,Integer afterSale)
            throws HSKDBException {
        String sql ="select count(*) as shu from md_order_info where order_state='1'";
        if (PurchaseId != null && PurchaseId!=1) {
            sql+=" and wz_id="+PurchaseId;
        }
        if (afterSale!=null){
            sql+=" and after_sale="+afterSale;
        }
        if(processState != null){//待发货的次数
            sql += " and process_status in (" + processState + ")";//and (commodity_number>number2 or number2 is null)
        }
        if (att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().equals("")){
            sql += " and (order_code like '%" + att_MdOrderInfo.getOrderCode() + "%' or" +
                    " moi_id in (select moi_id from md_order_mx where mat_name like '%" + att_MdOrderInfo.getOrderCode() + "%'))";
        }
        if(att_MdOrderInfo.getAddressee() != null && !att_MdOrderInfo.getAddressee().equals("")){
            sql += " and addressee like '%" + att_MdOrderInfo.getAddressee() +"%'";
        }
        if (att_MdOrderInfo.getPay_type_str() != null && !att_MdOrderInfo.getPay_type_str().equals("")){
            String  intStr=att_MdOrderInfo.getPay_type_str().trim();
            String[]  arrayStr=intStr.split(",");

            if(arrayStr.length>0) {
                sql += " and ( ";
                for (int i = 0; i < arrayStr.length; i++) {
                    String did = arrayStr[i];
                    if (i == arrayStr.length - 1) {
                        sql +="  pay_type='" + did + "'   ";
                    } else {
                        sql +="  pay_type='" + did + "' or ";
                    }
                }
                sql += " ) ";
            }
        }
        if(att_MdOrderInfo.getPurchaseUnit() != null && !att_MdOrderInfo.getPurchaseUnit().equals("")){
            sql += " and purchase_unit like '%"+ att_MdOrderInfo.getPurchaseUnit() +"%'";
        }
        if(att_MdOrderInfo.getAddresseeTelephone() != null && !att_MdOrderInfo.getAddresseeTelephone().equals("")){
            sql += " nad addressee_telephone like '%" + att_MdOrderInfo.getAddresseeTelephone() + "%'";
        }

        if (att_MdOrderInfo.getNeedBill() != null && !"".equals(att_MdOrderInfo.getNeedBill().trim())) {
            sql += " and need_bill  like '%" + att_MdOrderInfo.getNeedBill() + "%'";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        //时间类型开始条件处理  下单时间(placeOrderTime)
        if (att_MdOrderInfo.getPlaceOrderTime_start() != null) {
            sql += " and  place_order_time>='" + sdf.format(att_MdOrderInfo.getPlaceOrderTime_start()) + " 00:00:00'";
        }
        //时间类型结束条件处理 下单时间(placeOrderTime)
        if (att_MdOrderInfo.getPlaceOrderTime_end() != null) {
            sql += " and  place_order_time <='" + sdf.format(att_MdOrderInfo.getPlaceOrderTime_end()) + " 23:59:59'";
        }
//		else if(processState != null && processState.trim().equals("2")){//待付款
//			sql += " and process_status in (2)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("3")){//待收货
//			sql += " and process_status in (3)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("4")){//交易成功
//			sql += " and process_status in (4)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("5")){//交易成功
//			sql += " and process_status in (5)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("6")){//交易失败
//			sql += " and process_status in (6)";//and number2 is not null and (number1<number2 or number1 is null)
//		}
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("shu").toString());
        }
        return 0;
    }
    //增加交易实时战报中的今日销售额
    public Double TransactionCountMoney(Integer OldId)throws HSKDBException{
        Date NewDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="SELECT SUM(place_order_money) AS TransactionMoneyCount FROM md_order_info WHERE order_state = '1'";
        sql+=" AND Place_order_time >='"+sf.format(NewDate)+" 00:00:00' AND Place_order_time <='"+sf.format(NewDate)+" 23:59:59'";
        sql+=" AND wz_id= "+OldId;
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0&&list.get(0).get("TransactionMoneyCount")!=null){
            return Double.parseDouble(list.get(0).get("TransactionMoneyCount").toString());
        }

        return 0.0000;

    }
    //增加交易实时战报中的今日下单
    public Integer TransactionCount(Integer OldId)throws HSKDBException{
        Date NewDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="SELECT count(*) AS TransactionCount FROM md_order_info WHERE order_state = '1'"; //AND Place_order_time >= '2019-12-30 00:00:00' AND Place_order_time <= '2019-12-30 23:59:59' ";
        sql+=" AND Place_order_time >='"+sf.format(NewDate)+" 00:00:00' AND Place_order_time <='"+sf.format(NewDate)+" 23:59:59'";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        sql+=" AND wz_id= "+OldId;
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("TransactionCount").toString());
        }
        return 0;

    }
    //增加交易实战是战报中的今日下单百分比
    public Integer PercentageCount(Integer OldId) throws HSKDBException{
        Date NewDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="SELECT COUNT(*) AS PercentageCount FROM md_order_info WHERE order_state = '1'";// AND Place_order_time >= '2019-12-30 00:00:00' AND Place_order_time <= '2019-12-30 23:59:59' AND Process_status ='2'";
        sql+=" AND Place_order_time >='"+sf.format(NewDate)+" 00:00:00' AND Place_order_time <='"+sf.format(NewDate)+" 23:59:59'";
        sql+=" AND wz_id= "+OldId;
        sql+=" AND Process_status !=2";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("PercentageCount").toString());
        }
        return 0;
    }
    //增加订单实时战报
    public List<Map<String, Object>> getOrderMxListByTransaction(Integer limit, Integer page,Integer oldId) throws HSKDBException {
        Date PlaceOrderDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        /*String PlaceOrderDate1= sf.format(PlaceOrderDate);*/
        String sql = "SELECT DATE_FORMAT(Place_order_time,'%T') AS PlaceOrderTime,Purchase_unit AS Purchase_unit,commodity_number AS commodityNumber,place_order_money AS placeOrderMoney FROM md_order_info WHERE order_state='1'";
        sql+="AND Place_order_time>='"+sf.format(PlaceOrderDate)+" 00:00:00'";
        sql+="AND Place_order_time<='"+sf.format(PlaceOrderDate)+" 23:59:59'";
        sql+=" AND wz_id= "+oldId ;
        sql+=" GROUP BY Place_order_time DESC";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    //商品排行榜 top10
    public List<Map<String, Object>> getOrderMxListmaterielTop(Integer limit, Integer page,Integer oldId) throws HSKDBException {
        String sql = "SELECT mm.wms_mi_id AS wmsiId, mm.mat_name AS matName,mm.number1 AS matNumber,mx.`Total_money` AS TotalMoney FROM md_materiel_info mm LEFT JOIN md_order_mx mx ON mm.wms_mi_id = mx.wms_mi_id LEFT JOIN md_order_info mo ON mo.moi_id= mx.moi_id WHERE mm.state=1";
        sql+=" AND mo.wz_id="+oldId ;
        sql+=" GROUP BY mm.number1 DESC";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    //商品排行榜中收藏数据
    public List<Map<String, String>> getOrderMxListmaterielTopFavorites(String wmsiId) throws HSKDBException{
        String sql = "SELECT COUNT(wms_mi_id) AS wmsMiId FROM md_favorites WHERE 1=1 ";
        sql+="AND wms_mi_id='"+wmsiId+"'";
        return this.getJdbcDao().query(sql);
    }
    //商品总览
    public Integer materielState(Integer state,Integer oldId) throws HSKDBException{
        String sql ="SELECT COUNT(*) AS materielState FROM  md_materiel_info WHERE 1=1 ";
        sql+=" AND wz_id="+oldId;
        if (state==0) {
            sql+=" AND 1=1";
        }if (state==1) {
            sql+=" AND state="+1;
        }if (state==2) {
            sql+=" AND state="+2;
        }
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("materielState").toString());
        }
        return 0;
    }
    //启用禁用员工
    public Integer allYg(Integer state,Integer oldId) throws HSKDBException{
        String sql ="select count(sui_id) AS allYg from sys_user_info t1 LEFT JOIN sys_org_gx t2  on t1.org_gx_id =t2.org_gx_id where 1=1 ";
        sql+=" AND t2.old_id="+oldId ;
        if (state==0) {
            sql+=" AND 1=1";
        }if (state==1) {
            sql+=" AND state="+1;
        }if (state==2) {
            sql+=" AND state="+0;
        }
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("allYg").toString());
        }
        return 0;
    }
    //交易收支  待结算金额
    public Double branchleft1(Integer oldId)throws HSKDBException {
        String sql ="SELECT SUM(place_order_money) AS palceOrderbranch1 from md_order_info WHERE 1=1 ";
        sql+=" AND wz_id="+oldId;
        sql+=" AND (settlement=0 OR ISNULL(settlement)) ";
        sql+=" AND Process_status in(5,6) ";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("palceOrderbranch1")!=null){
            return Double.parseDouble(list.get(0).get("palceOrderbranch1").toString());
        }
        return 0.00;
    }
    //销售额  累计金额
    public Double  branchleft2(Integer oldId)throws HSKDBException {
        Date newDate=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sql ="SELECT SUM(place_order_money) AS palceOrderbranch2 from md_order_info WHERE 1=1";
        sql+=" AND wz_id="+oldId;
        sql+=" AND Process_status in(5,6) ";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("palceOrderbranch2")!=null){
            return Double.parseDouble(list.get(0).get("palceOrderbranch2").toString());
        }
        return 0.00;
    }    //交易收支中List数据
    public List<Map<String, Object>> getBranchMxList(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin,Integer limit,Integer page,Integer oldId) throws HSKDBException{
        String sql="SELECT t1.moi_id as moiId, t1.Place_order_time AS PlaceOrderTime,t1.order_code AS orderCode,t2.mas_code AS masCode,t2.after_sale_money as saleMoney,t2.create_date as saleDate,t1.Purchase_unit AS PurchaseUnit,t1.pay_type AS payType,t1.place_order_money AS placeOrderMoney,t1.Process_status AS processState,t1.settlement AS settlement ";
         sql+=" from md_order_info t1 LEFT JOIN md_order_after_sale t2 ON t1.moi_id=t2.moi_id WHERE 1=1 ";
         if (dateInput1!=null&&dateInput2!=null&&dateInput1!=""&&dateInput2!=""){
             sql+=" AND t1.Place_order_time> '"+dateInput1+"'";
             sql+=" AND t1.Place_order_time< '"+dateInput2+"'";
         }

        if (inputGuanjian!=null&&inputGuanjian!=""){
            if (selectGuanjian==1){
                sql+="AND t1.order_code LIKE '%"+inputGuanjian+"%'";
            }else if (selectGuanjian==2){
                sql+="AND t2.mas_code LIKE '%"+inputGuanjian+"%'";
            }else if (selectGuanjian==3){
                sql+="AND t1.Purchase_unit LIKE '%"+inputGuanjian+"%'";
            }
        }
        if (zhiFu!=null&&zhiFu==1){
            sql+="";
        }else if (zhiFu!=null&&zhiFu==2){
            sql+=" AND t1.pay_type=1";
        }else if (zhiFu!=null&&zhiFu==3){
            sql+=" AND t1.pay_type=2";
        }else if (zhiFu!=null&&zhiFu==4){
            sql+=" AND t1.pay_type=3";
        }

        if (state!=null&&state==1){
            sql+=" AND t1.Process_status in(5,6)";
        }else if (state!=null&&state==2){
            sql+=" AND t1.Process_status in(5)";
        }else if (state!=null&&state==3){
            sql+=" AND t1.Process_status in(6)";
        }

        if (jinE1!=null&&jinE2!=null&&jinE1!=""&&jinE2!=""){
            sql+=" AND t1.place_order_money >"+jinE1;
            sql+=" AND t1.place_order_money <"+jinE2;
        }

        if (ziJin!=null&&ziJin==1){
        }else if (ziJin!=null&&ziJin==2){
            sql+=" AND t1.settlement=1";
        }else if (ziJin!=null&&ziJin==3){
            sql+=" AND (t1.settlement = 0 OR ISNULL(settlement ))";
        }
        sql+=" AND wz_id="+oldId;
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    public List<Map<String, Object>> getBranchCount(Integer oldId,Integer settlement,Integer asState) throws HSKDBException{
        String sql="SELECT COUNT(t1.order_code) as count ,SUM(place_order_money) as money FROM md_order_info t1 LEFT JOIN md_order_after_sale t2 ON t1.moi_id = t2.moi_id  WHERE 1 = 1 AND (t1.Process_status=5 or t1.Process_status=6)";
        if (settlement!=null){
            if (settlement==0){
                sql+=" AND (settlement=0 OR ISNULL(settlement))";
            }else {
                sql+=" AND t1.settlement= "+ settlement;
            }
        }
        if (asState!=null){
            sql+=" AND t2.as_state= "+ asState;
        }
        sql+=" AND wz_id="+oldId;
        sql+=" AND Process_status in(5,6) ";
        return this.getJdbcDao().query(sql);
    }
    //增加财务工作台数据
    public List<Map<String, Object>> getBranchWorkList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS,String caiGou,Integer selectValue,Integer moiId, Integer limit, Integer page,Integer isState) throws HSKDBException{
        String sql="SELECT moi_id as moiId,settlement_log ,Process_status as pStart,order_code, Place_order_time, pay_type as payType,pay_code,pay_date,place_order_money,Actual_money,Purchase_unit,applicant_Name,settlement,settlement_money FROM md_order_info t1  where 1=1 ";
            if (orderCodeGJ!=null&&orderCodeGJ!=""){
                sql+=" AND t1.order_code LIKE '%"+orderCodeGJ+"%'";
            }
            if (moiId!=null){
                sql+=" AND t1.moi_id="+moiId;
            }
            if (dateInput1!=null&&dateInput2!=null&&dateInput1!=""&&dateInput2!=""){
                sql+=" AND t1.Place_order_time> '"+dateInput1+" 00:00:00'";
                sql+=" AND t1.Place_order_time< '"+dateInput2+" 23:59:59'";
            }
            if (selectValue!=null){
                if (selectValue==0){
                }else if (selectValue==1){
                    sql+=" AND t1.settlement=0";
                }else if (selectValue==2){
                    sql+=" AND t1.settlement=1";
                }else if (selectValue==3){
                    sql+=" AND t1.settlement=2";
                }
            }
            if (moneyFw1!=null&&moneyFw1!=""&&moneyFw2!=null&&moneyFw2!=""){
                sql+=" AND t1.place_order_money >"+moneyFw1;
                sql+=" AND t1.place_order_money <"+moneyFw2;
            }
            if (gongYingS!=null&&gongYingS!=""){
                sql+=" AND t1.applicant_Name LIKE '%"+gongYingS+"%'";
            }
            if (caiGou!=null&&caiGou!=""){
                sql+=" AND t1.Purchase_unit LIKE '%"+caiGou+"%'";
            }
//            if ()
            if (moiId!=null){
            }else{
                sql+=" AND t1.Process_status in(5,6) ";
                sql+=" AND NOT ISNULL(t1.Actual_money ) ";
                if(limit != null && page != null) {
                    sql += " limit " + (page - 1) * limit + "," + limit;
                }
            }

        return this.getJdbcDao().query(sql);
    }
    public Double getFinaceLeftRight(Integer state,Integer value) throws HSKDBException{
        Date orderDate=new Date();
        Date yesterday=new Date(orderDate.getTime()- 24*60*60*1000);
        Date sevendays=new Date(orderDate.getTime()- 24*60*60*1000*7);
        Date month=new Date(orderDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT SUM(Actual_money) AS placeOrderMoney FROM md_order_info where 1=1";
        if (state==0){
            sql+=" AND settlement= "+state+" OR ISNULL(settlement) ";
        }else if (state==1){
            sql+=" AND settlement= "+state;
        }
        if (value==0){
            sql+=" AND Place_order_time>='"+sf.format(orderDate)+" 00:00:00'";
            sql+=" AND Place_order_time<='"+sf.format(orderDate)+" 23:59:59'";
        }if (value==1){
            sql+=" AND Place_order_time>='"+sf.format(yesterday)+" 00:00:00'";
            sql+=" AND Place_order_time<='"+sf.format(yesterday)+" 23:59:59'";
        }if (value==2){
            sql+=" AND Place_order_time>='"+sf.format(sevendays)+" 00:00:00'";
            sql+=" AND Place_order_time<='"+sf.format(orderDate)+" 23:59:59'";
        }
        if (value==3){
            sql+=" AND Place_order_time>='"+sf.format(month)+" 00:00:00'";
            sql+=" AND Place_order_time<='"+sf.format(orderDate)+" 23:59:59'";
        }
        sql+=" AND Process_status in(5,6) ";
        sql+=" AND NOT ISNULL(Actual_money ) ";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("placeOrderMoney")!=null){
            return Double.parseDouble(list.get(0).get("placeOrderMoney").toString());
        }
        return 0.00;
    }
    public Double getFinaceLeftRight1(Integer state,Integer value) throws HSKDBException{
        Date orderDate=new Date();
        Date yesterday=new Date(orderDate.getTime()- 24*60*60*1000);
        Date sevendays=new Date(orderDate.getTime()- 24*60*60*1000*7);
        Date month=new Date(orderDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT SUM(settlement_money) AS placeOrderMoney FROM md_order_info where 1=1";
        if (state==0){
            sql+=" AND (settlement =1 or settlement =2)";
        }
        if (value==0){
            sql+=" AND settlement_date>='"+sf.format(orderDate)+" 00:00:00'";
            sql+=" AND settlement_date<='"+sf.format(orderDate)+" 23:59:59'";
        }if (value==1){
            sql+=" AND settlement_date>='"+sf.format(yesterday)+" 00:00:00'";
            sql+=" AND settlement_date<='"+sf.format(yesterday)+" 23:59:59'";
        }if (value==2){
            sql+=" AND settlement_date>='"+sf.format(sevendays)+" 00:00:00'";
            sql+=" AND settlement_date<='"+sf.format(orderDate)+" 23:59:59'";
        }
        if (value==3){
            sql+=" AND settlement_date>='"+sf.format(month)+" 00:00:00'";
            sql+=" AND settlement_date<='"+sf.format(orderDate)+" 23:59:59'";
        }
        sql+=" AND Process_status in(5,6) ";
        sql+=" AND NOT ISNULL(Actual_money ) ";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("placeOrderMoney")!=null){
            return Double.parseDouble(list.get(0).get("placeOrderMoney").toString());
        }
        return 0.00;
    }
    //各个供应商条形图
    public List<Map<String, Object>> getDateMxlistString(String dateInput3,String dateInput4,Integer settlement,String applicantName)throws HSKDBException{
        String sql="SELECT   SUM(place_order_money) as placeOrderMoney, applicant_Name as applicantName from md_order_info where 1=1";
        sql+=" AND Place_order_time>= '"+dateInput3+ " 00:00:00'";
        sql+=" AND Place_order_time<= '"+dateInput4+ " 23:59:59'";
        if (settlement!=null&&settlement==0){
            sql+=" AND (settlement=0 OR ISNULL(settlement))";
        }
        if (settlement!=null&&settlement==1){
            sql+=" AND settlement=1 ";
        }if (applicantName!=null){
            sql+=" AND applicant_Name= '"+applicantName+"'";
        }
        sql+=" GROUP BY applicant_Name ORDER BY COUNT(applicant_Name)";
        return this.getJdbcDao().query(sql);
    }
    @Override
    public Integer getMatCodeReadOnly1(Integer wmsMiId,Integer suiId) throws HSKDBException {
        String sql = "SELECT COUNT(t2.wew_mx_id) AS count1 FROM md_enter_warehouse t1  LEFT JOIN md_enter_warehouse_mx t2 on t1.wew_id=t2.wew_id LEFT JOIN md_materiel_info t3 on t2.wms_mi_id=t3.wms_mi_id WHERE 1=1 ";
            sql+=" AND t1.rbs_id = 22 AND t1.state = '1' AND t1.purchase_type = '2'  AND t1.bill_type = '1' ";
            sql+=" AND t1.sui_id ="+ suiId ;
            sql+=" AND t3.wms_mi_id ="+wmsMiId;
            List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("count1").toString());
        }
        return 0;
    }
    @Override
    public Integer getMatCodeReadOnly2(Integer wmsMiId,Integer suiId) throws HSKDBException {
        String sql = "SELECT COUNT(t2.wow_mx_id) as count2 FROM md_out_warehouse t1 LEFT JOIN md_out_warehouse_mx t2 ON t1.wow_id=t2.wow_id LEFT JOIN md_materiel_info t3 on t2.wms_mi_id=t3.wms_mi_id WHERE 1=1 ";
        sql+=" AND t1.rbs_id = 22 AND t1.purchase_type = '2' AND t1.COMPANY_type = '1' AND t1.state = '1' ";
        sql+=" t1.sui_id = "+suiId;
        sql+=" t1.wms_mi_id = "+wmsMiId;
        return 0;
    }
    public List<Map<String, Integer>> getSafetyNewId(String input1,String input2,Integer rbaId,Integer rbsId,Integer rbbId,String PurchaseType,Integer isNotState) throws HSKDBException{
        String sql = "SELECT mdinventor0_.wi_id AS wi_id FROM md_inventory_view  mdinventor0_  WHERE mdinventor0_.state = 1";
        if (rbaId != 0) {
            sql += " AND mdinventor0_.rba_id = " + rbaId;
        }
        if (rbaId != 0) {
            sql += " AND mdinventor0_.rbs_id = " + rbsId;
        }
        if (rbaId != 0) {
            sql += " AND mdinventor0_.rbb_id = " + rbbId;
        }
        if (PurchaseType != null) {
            sql += " AND ( mdinventor0_.purchase_type LIKE '%" + PurchaseType + "%' )";
        }

//        if (input1 != null && (input2 == null || !input2.equals(""))) {
//            if (isNotState == null)
//                sql += " AND ( mdinventor0_.mat_name LIKE '%" + input1 + "%' )";
//            else
//                sql += " AND mdinventor0_.mat_name not LIKE '%" + input1 + "%'";
//        }
//        if (input2 != null && (input1 == null && !input1.equals(""))) {
//            if (isNotState == null)
//                sql += " AND ( mdinventor0_.mmf_name LIKE '%" + input2 + "%' )";
//            else
//                sql += " AND mdinventor0_.mmf_name not LIKE '%" + input2 + "%'";
//        }

        if (input1 != null && input2 == null && isNotState == null) {
            sql += " AND ( mdinventor0_.mat_name LIKE '%" + input1 + "%' )";
        } else if (input1 != null && input2 == null && isNotState == 1) {
            sql += " AND mdinventor0_.mat_name not LIKE '%" + input1 + "%'";
        }
        if (input2 != null && input1 == null && isNotState == null) {
            sql += " AND ( mdinventor0_.mmf_name LIKE '%" + input2 + "%' )";
        } else if (input2 != null && input1 == null && isNotState == null) {
            sql += " AND mdinventor0_.mmf_name not LIKE '%" + input2 + "%'";
        }
        if (input1 != null && input2 != null && isNotState == null) {
            sql += " AND ( mdinventor0_.mmf_name LIKE '%" + input2 + "%' or mdinventor0_.mat_name LIKE '%" + input1 + "%')";
        }

        sql+=" ORDER BY mdinventor0_.edit_date DESC ";
        return this.getJdbcDao().query(sql);
    }
    public Integer saleManCount(String saleMan) throws HSKDBException{
        String sql="SELECT COUNT(leader_name) NameCount FROM sys_sales_man where leader_name = '"+saleMan+"'";
        return  0;
    }
    public Integer saleManCount2(Integer saleMan) throws HSKDBException{
            String sql = "SELECT COUNT(*) AS saleMan FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = " + saleMan +
                    " UNION ALL" +
                    " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + saleMan + ", salesman_ids )" +
                    " UNION ALL " +
                    " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + saleMan + ", salesman_ids )" +
                    " UNION ALL" +
                    " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + saleMan + ", salesman_ids )) AS a where 1=1";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("saleMan").toString());
        }
        return  0;
    }

    public Integer totalAll() throws HSKDBException{
        String sql="SELECT  COUNT(order_code) total from md_order_info where 1=1 AND Process_status in(5,6) AND NOT ISNULL(Actual_money )\n";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("total").toString());
        }
        return  0;
    }
    public Double totalMoney1() throws HSKDBException{
        String sql="SELECT SUM(Actual_money) as money1 from md_order_info where 1=1 AND settlement=1 AND Process_status in(5,6) AND NOT ISNULL(Actual_money )";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("money1")!=null){
            return Double.parseDouble(list.get(0).get("money1").toString());
        }
        return 0.00;
    }
    public Double totalMoney2() throws HSKDBException{
        String sql="SELECT SUM(Actual_money)as money2 from md_order_info where 1=1 AND (settlement = 0 OR ISNULL(settlement )) AND Process_status in(5,6) AND NOT ISNULL(Actual_money )\n";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list==null||list.size()<=0){
            return 0.00;
        }
        if(list.get(0).get("money2")!=null){
            return Double.parseDouble(list.get(0).get("money2").toString());
        }
        return 0.00;
    }

}
