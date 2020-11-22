package com.hsk.dentistmall.web.transaction.service.imp;

import com.hsk.dentistmall.api.daobbase.IHomePageSupplierDao;
import com.hsk.dentistmall.api.daobbase.IMdOrderInfoDao;
import com.hsk.dentistmall.api.daobbase.imp.HomePageSupplierDao;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOutOrder;
import com.hsk.dentistmall.web.transaction.service.IFinancialWorkbenchService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FinancialWorkbenchService extends DSTService implements IFinancialWorkbenchService {
    @Autowired
    protected IHomePageSupplierDao homePageSupplierDao;
    @Autowired
    protected IMdOrderInfoDao mdOrderInfoDao;
    public PagerModel getFinanciaBranchMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS,String caiGou,Integer selectValue,Integer moiId, Integer limit, Integer page) throws HSKException{

        PagerModel pm=new PagerModel();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<Map<String,Object>> mxList =homePageSupplierDao.getBranchWorkList(orderCodeGJ,dateInput1,dateInput2,selectValue1,moneyFw1,moneyFw2,gongYingS,caiGou,selectValue,moiId,limit,page,null);
            for (Map<String, Object> map : mxList) {
                Object PlaceOrderTime=map.get("Place_order_time");
                map.put("PlaceOrderTime",sdf.format(PlaceOrderTime));


                Object paydate=map.get("pay_date");
                if (paydate!=null&&!paydate.equals("")){
                    map.put("paydate",sdf.format(paydate));
                }else {
                    map.put("paydate","");
                }
            }
            List<Map<String,Object>> mxList1 =homePageSupplierDao.getBranchWorkList(orderCodeGJ,dateInput1,dateInput2,selectValue1,moneyFw1,moneyFw2,gongYingS,caiGou,selectValue,moiId,null,null,null);
            Integer mxListCount1=mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());

        } catch (HSKDBException e) {
            e.printStackTrace(); }
        return pm;
    }
    @Transactional(readOnly=false)
    public SysRetrunMessage savePlJieSuan(String checkStr,String settlementMoney) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        Date newDate=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            String check[] = checkStr.split(",");

            MdOrderInfo att_mdOrderInfo=null;
            for(String mooIds : check){
                Integer mooId=Integer.valueOf(mooIds);
                att_mdOrderInfo= mdOrderInfoDao.findMdOrderInfoById(mooId);
                if (settlementMoney!=null&&settlementMoney!=""){
                    Double settlementMoneyD=att_mdOrderInfo.getSettlementMoney();
                    if (settlementMoneyD==att_mdOrderInfo.getActualMoney()){
                        att_mdOrderInfo.setSettlement(1);
                        att_mdOrderInfo.setLoanState(2);
                        att_mdOrderInfo.setSettlementDate(newDate);
                        Double settlementMoney1=Double.valueOf(settlementMoney);
                        att_mdOrderInfo.setSettlementMoney(att_mdOrderInfo.getActualMoney());

                        String setSettlementMoneyStr=String.valueOf(att_mdOrderInfo.getActualMoney());
                        String SettlementLogStr =sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr;
                        att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                    }else{
                    Double settlementMoney1=Double.valueOf(settlementMoney);
                    Double settlementMoneyGet=att_mdOrderInfo.getSettlementMoney();
                    if (settlementMoneyGet!=null){
                        Double settlementMoneys=settlementMoneyGet+settlementMoney1;
                        String money01=String.valueOf(settlementMoneys);
                        String money02=String.valueOf(att_mdOrderInfo.getActualMoney());
                        if (!money01.equals(money02.trim())){
                            att_mdOrderInfo.setSettlementMoney(settlementMoneys);
                            att_mdOrderInfo.setSettlement(2);
                            att_mdOrderInfo.setSettlementDate(newDate);
                            String SettlementLogGet=att_mdOrderInfo.getSettlementLog();
                            String setSettlementMoneyStr=String.valueOf(settlementMoney);
                            if (SettlementLogGet!=null&&SettlementLogGet!=""){
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t"+SettlementLogGet+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }else{
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }

                        }else {
                            att_mdOrderInfo.setSettlementMoney(settlementMoneys);
                            att_mdOrderInfo.setSettlement(1);
                            att_mdOrderInfo.setLoanState(2);
                            att_mdOrderInfo.setSettlementDate(newDate);
                            String SettlementLogGet=att_mdOrderInfo.getSettlementLog();
                            String setSettlementMoneyStr=String.valueOf(settlementMoney);
                            if (SettlementLogGet!=null&&SettlementLogGet!=""){
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t"+SettlementLogGet+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }else{
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }
                        }
                    }else {
                        String money01=String.valueOf(settlementMoney1);
                        String money02=String.valueOf(att_mdOrderInfo.getActualMoney());
                        if (money01.equals(money02.trim())){
                            att_mdOrderInfo.setSettlementMoney(settlementMoney1);
                            att_mdOrderInfo.setSettlement(1);
                            att_mdOrderInfo.setSettlementDate(newDate);
                            String SettlementLogGet=att_mdOrderInfo.getSettlementLog();
                            String setSettlementMoneyStr=String.valueOf(settlementMoney);
                            if (SettlementLogGet!=null&&SettlementLogGet!=""){
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t"+SettlementLogGet+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }else{
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }
                        }else {
                            att_mdOrderInfo.setSettlementMoney(settlementMoney1);
                            att_mdOrderInfo.setSettlement(2);
                            att_mdOrderInfo.setSettlementDate(newDate);
                            String SettlementLogGet=att_mdOrderInfo.getSettlementLog();
                            String setSettlementMoneyStr=String.valueOf(settlementMoney);
                            if (SettlementLogGet!=null&&SettlementLogGet!=""){
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t"+SettlementLogGet+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }else{
                                String SettlementLogStr ="\t\t"+sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr+"\t\t\t\t";
                                att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                            }
                        }
                    }
                    }
                }else{
                    att_mdOrderInfo.setSettlement(1);
                    att_mdOrderInfo.setLoanState(2);
                    att_mdOrderInfo.setSettlementDate(newDate);
                    att_mdOrderInfo.setSettlementMoney(att_mdOrderInfo.getActualMoney());
                    String setSettlementMoneyStr=String.valueOf(att_mdOrderInfo.getActualMoney());
                    String SettlementLogStr =sysUserInfo.getUserName()+"/"+sdf.format(newDate)+"\t\t"+"结算￥"+setSettlementMoneyStr;
                    att_mdOrderInfo.setSettlementLog(SettlementLogStr);
                }
                mdOrderInfoDao.saveOrUpdateMdOrderInfo(att_mdOrderInfo);
            }
            srm.setObj(att_mdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }
    //首页待结算金额与已结算金额数据
    public SysRetrunMessage getFinanceLeftRight(Integer value) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, Double> SeMap = new HashMap<String, Double>();
        try{
            Double placeOrderMoney1= homePageSupplierDao.getFinaceLeftRight1(0,value);
            SeMap.put("placeOrderMoney1",placeOrderMoney1);
            Double placeOrderMoney2= homePageSupplierDao.getFinaceLeftRight(1,value);
            SeMap.put("placeOrderMoney2",placeOrderMoney2);
            srm.setObj(SeMap);
        }catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    //供应商交易分析
    public PagerModel getDateMxlistString(String dateInput3,String dateInput4) throws HSKException{
        PagerModel pm=new PagerModel();
        DecimalFormat df = new DecimalFormat("######0.00");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Double placeOrderMoney1=0.0;
        try {
        List<Map<String,Object>> mxList =homePageSupplierDao.getDateMxlistString(dateInput3,dateInput4,null,null);

        //List<Map<String,Object>> mxList1 =homePageSupplierDao.getDateMxlistString(dateInput3,dateInput4,0);

//            for (Map<String, Object> map : mxList1) {
//                Object placeOrderMoneys=map.get("placeOrderMoney");
//                placeOrderMoney1+=Double.parseDouble(placeOrderMoneys.toString());
//            }
        for (Map<String, Object> map : mxList) {
            //Object placeOrderMoney2=map.get("placeOrderMoney");
            Object applicantName1=map.get("applicantName");
            String applicantName=String.valueOf(applicantName1);
            Double placeOrderMoney1=0.0;
            List<Map<String,Object>> mxList1 =homePageSupplierDao.getDateMxlistString(dateInput3,dateInput4,0,applicantName);
            for (Map<String, Object> map1 : mxList1) {
                Object placeOrderMoney2=map1.get("placeOrderMoney");
                placeOrderMoney1=Double.parseDouble(placeOrderMoney2.toString());

            }
            map.put("placeOrderMoney1",df.format(placeOrderMoney1));
            }
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotalCount(mxList.size());
        } catch (HSKDBException e) {
            e.printStackTrace(); }

        return  pm;
    }
    //导出财务统计表
    public SysRetrunMessage exportFinanciaBranchMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS, String caiGou,Integer selectValue,Integer moiId)throws HSKException{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SysUserInfo SysUserInfo=this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",SysUserInfo.getUserName());
        dataMap.put("nodeName",SysUserInfo.getOrgGxId_Str());
        Date newDate=new Date();
        dataMap.put("newDate",sdf.format(newDate));
        dataMap.put("DateStart",dateInput1!=" "? dateInput1:"");
        dataMap.put("endStart",dateInput2!=" "? dateInput1:"");
        try{
         Double   sumActualMoney=0.0;
         Double   sumPlaceOrderMoney=0.0;
        List<Map<String, Object>> list = homePageSupplierDao.getBranchWorkList(orderCodeGJ,dateInput1,dateInput2,selectValue1,moneyFw1,moneyFw2,gongYingS,caiGou,selectValue,moiId,null,null,01);
        List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index",index+"");

                    Object orderCode1=map.get("order_code");
                    String orderCode=String.valueOf(orderCode1);
                    reMap.put("orderCode",orderCode!="null" ? orderCode:"");

                    Object PlaceOrderTime=map.get("Place_order_time");
                    reMap.put("PlaceOrderTime",sdf.format(PlaceOrderTime));


                    Object payType1=map.get("payType");
                    String payType=String.valueOf(payType1);
                    if (payType.equals("1")){
                        reMap.put("payType","支付宝");
                    }else if (payType.equals("2")){
                        reMap.put("payType","微信");
                    }else if (payType.equals("3")){
                        reMap.put("payType","月结");
                    }else {
                        reMap.put("payType","");
                    }

                    Object payCode1=map.get("pay_code");
                    String payCode=String.valueOf(payCode1);
                    reMap.put("payCode",payCode!="null" ? payCode:"");


                    Object payDate=map.get("pay_date");
                    if (payDate!=null&&!payDate.equals("")){
                        reMap.put("payDate",sdf.format(payDate));
                    }else{
                        reMap.put("payDate","");
                    }
                    reMap.put("placeOrderMoney",df2.format(map.get("place_order_money")!=null ? map.get("place_order_money"):0d));
                    reMap.put("ActualMoney",df2.format(map.get("Actual_money")!=null ? map.get("Actual_money"):0d));

                    if (map.get("place_order_money")!=null){
                        sumPlaceOrderMoney+=Double.parseDouble(String.valueOf(map.get("place_order_money")));
                    }else {
                        sumPlaceOrderMoney+=0.0;
                    }
                    if (map.get("Actual_money")!=null){
                        sumActualMoney+=Double.parseDouble(String.valueOf(map.get("Actual_money")));
                    }else {
                        sumActualMoney+=0.0;
                    }

                    Object PurchaseUnit1=map.get("Purchase_unit");
                    String PurchaseUnit=String.valueOf(PurchaseUnit1);
                    reMap.put("PurchaseUnit",PurchaseUnit!="null" ? PurchaseUnit:"");


                    Object applicantName1=map.get("applicant_Name");
                    String applicantName=String.valueOf(applicantName1);
                    reMap.put("applicantName",applicantName!="null" ? applicantName:"");

                    Object settlement1=map.get("settlement");
                    String settlement=String.valueOf(settlement1);
                    if (settlement.equals("1")){
                        reMap.put("settlement","已结算");

                    }else if (settlement.equals("0")){
                        reMap.put("settlement","未结算");
                    }else if (settlement.equals("2")){
                        reMap.put("settlement","部分结算");
                    }else {
                        reMap.put("settlement","");
                    }
                    reList.add(reMap);
                }
            }
            dataMap.put("sumPlaceOrderMoney",sumPlaceOrderMoney);
            dataMap.put("sumActualMoney",sumActualMoney);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportFinanciaMxList.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("hhmmss");
            String l =sd.format(date1);

            String str="";
            if (dateInput1!=null&&dateInput1!=""&&dateInput2!=null&&dateInput2!=""){
                str= dateInput1+"至"+dateInput2+"财务统计表";
            }else{
                str="财务统计表";
            }

            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + str+ ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + str+ ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", str + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  srm;
    }
    public SysRetrunMessage exportapplicName(String dateInput3, String dateInput4)throws HSKException{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SysUserInfo SysUserInfo=this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",SysUserInfo.getUserName());
        dataMap.put("nodeName",SysUserInfo.getOrgGxId_Str());
        Date newDate=new Date();
        dataMap.put("newDate",sdf.format(newDate));
        dataMap.put("DateStart",dateInput3!=" "? dateInput3:"");
        dataMap.put("endStart",dateInput4!=" "? dateInput4:"");
        try{
            List<Map<String,Object>> list =homePageSupplierDao.getDateMxlistString(dateInput3,dateInput4,null,null);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            Double placeOrderMoneySums=0.0;
            Double placeOrderMoneyDai=0.0;
            Double placeOrderMoneyYi=0.0;
            if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                index++;
                Map<String, String> reMap = new HashMap<String, String>();
                reMap.put("index",index+"");
                Object applicantName1=map.get("applicantName");
                String applicantName=String.valueOf(applicantName1);
                Double placeOrderMoney1=0.0;
                Double placeOrderMoney1s=0.0;
                List<Map<String,Object>> mxList1 =homePageSupplierDao.getDateMxlistString(dateInput3,dateInput4,0,applicantName);
                for (Map<String, Object> map1 : mxList1) {
                    Object placeOrderMoney2=map1.get("placeOrderMoney");
                    placeOrderMoney1=Double.parseDouble(placeOrderMoney2.toString());
                }
                List<Map<String,Object>> mxList2 =homePageSupplierDao.getDateMxlistString(dateInput3,dateInput4,1,applicantName);
                for (Map<String, Object> map2 : mxList2) {
                    Object placeOrderMoney3=map2.get("placeOrderMoney");
                    placeOrderMoney1s=Double.parseDouble(placeOrderMoney3.toString());
                }
                if (placeOrderMoney1!=null){
                    placeOrderMoneyDai+=placeOrderMoney1;
                }else {
                    placeOrderMoneyDai+=0.0;
                }
                if (placeOrderMoney1s!=null){
                    placeOrderMoneyYi+=placeOrderMoney1s;
                }else {
                    placeOrderMoneyYi+=0.0;
                }
                reMap.put("placeOrderMoney1s",df.format(placeOrderMoney1s/10000));
                reMap.put("placeOrderMoney1",df.format(placeOrderMoney1/10000));
//                Double df2.format(map.get("placeOrderMoney")!=null ? map.get("placeOrderMoney"):0d);
                Object placeOrderMoney=map.get("placeOrderMoney");
                Double placeOrderMoneyAll=Double.parseDouble(placeOrderMoney.toString());
                if (placeOrderMoney!=null){
                    reMap.put("placeOrderMoney", df2.format((placeOrderMoneyAll/1000)));
                    placeOrderMoneySums+=placeOrderMoneyAll;
                }else {
                    reMap.put("placeOrderMoney", "0.00");
                    placeOrderMoneySums+=0.00;
                }

                reMap.put("applicantName",applicantName!="null" ? applicantName:"");
                reList.add(reMap);
            }
            }
            dataMap.put("placeOrderMoneySums", placeOrderMoneySums/10000);
            dataMap.put("placeOrderMoneyDai", placeOrderMoneyDai/10000);
            dataMap.put("placeOrderMoneyYi", placeOrderMoneyYi/10000);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportapplicNameplaceOrderMoney.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("hhmmss");
            String l =sd.format(date1);
            String str="";
            if (dateInput3!=null&&dateInput3!=""&&dateInput4!=null&&dateInput4!=""){
                str= "供应商交易金额分析报表(单位万)";//dateInput3+"至"+dateInput4+
            }else{
                str="供应商交易金额分析报表(单位万)";
            }

            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + str+ ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + str+ ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", str + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  srm;
    }
    public SysRetrunMessage getMatCodeReadOnly(Integer wmsMiId) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, Integer> SeMap = new HashMap<String, Integer>();
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();

		try{
			Integer count1= homePageSupplierDao.getMatCodeReadOnly1(wmsMiId,sysUserInfo.getSuiId());
			//SeMap.put("placeOrderMoney1",placeOrderMoney1);
			Integer count2= homePageSupplierDao.getMatCodeReadOnly2(wmsMiId,sysUserInfo.getSuiId());
			if ((count1!=0&&count1!=null)||(count2!=0&&count2!=null)){
                SeMap.put("MatCodeReadOnly",1);
            }else{
                SeMap.put("MatCodeReadOnly",0);
            }
			srm.setObj(SeMap);
		}catch (HSKDBException e) {
			e.printStackTrace();
		}
        return srm;
    }
    public SysRetrunMessage getTotalMoney() throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, String> SeMap = new HashMap<String, String>();
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();

        try{
            Integer total= homePageSupplierDao.totalAll();
            String total1=String.valueOf(total);
            SeMap.put("total",total1);

            Double money1= homePageSupplierDao.totalMoney1();
            String money11=String.valueOf(money1);
            SeMap.put("money1",money11);

            Double money2= homePageSupplierDao.totalMoney2();
            String money21=String.valueOf(money2);
            SeMap.put("money2",money21);
            srm.setObj(SeMap);
        }catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }

}

