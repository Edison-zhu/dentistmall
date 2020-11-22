package com.hsk.miniapi.dentistmall.web.transaction.service.imp;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdOrderAfterSaleApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdOrderInfoApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdOrderMxApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdSupplierApiDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.miniapi.dentistmall.web.transaction.service.IMdOrderAfterSaleApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: yangfeng
 * time: 2019/12/26 16:51
 */
@Service
public class MdOrderAfterSaleApiService extends DSTApiService implements IMdOrderAfterSaleApiService {

    @Autowired
    protected IMdOrderAfterSaleApiDao mdOrderAfterSaleDao;
    @Autowired
    protected IMdSupplierApiDao mdSupplierDao;
    @Autowired
    protected IMdOrderInfoApiDao mdOrderInfoDao;
    @Autowired
    protected IMdOrderMxApiDao mdOrderMxDao;

    /**
     * 记录售后
     *
     * @throws HSKException
     */
    private void saveMdOrderAfterSaleEx(Integer asState, Integer masId, Integer afterSale, String orgName, Double money) throws HSKException {
        String markContent = "";
        String buyerContent = "";
        String supplierContent = "";
        switch (asState) {
            case 1:
                markContent = "同意退货申请";
                buyerContent = "商家已同意退货申请并发送商家收货地址，请尽快将物品寄还给商家";
                supplierContent = "请注意查收采购商寄还的商品是否有误，收到商品后尽快完成售后处理";
                break;
            case 2:
                if (afterSale == 2) {
                    markContent = "同意退款申请";
                    buyerContent = "商家已同意退款申请，退款费用将在7个工作日内处理退款或不计算在结算费用中";
                } else {
                    markContent = "已收到货，开始退款";
                    buyerContent = "退款费用将在7个工作日内处理退款或不计算在结算费用中";
                }
                supplierContent = "请尽快退款给买家";
                break;
            case 3:
                markContent = "已经结束售后申请";
                buyerContent = "本次售后已经结束";
                supplierContent = buyerContent;
                break;
            case 4:
                markContent = "拒绝了售后申请";
                buyerContent = "商家已拒绝您的申请，您可以通过电话联系商家沟通原因";
                supplierContent = "请联系买家并提供好服务，以防被投诉";
                break;
        }
        MdOrderAfterSaleExtendEntity mdOrderAfterSaleExtendEntity = new MdOrderAfterSaleExtendEntity();
        mdOrderAfterSaleExtendEntity.setMasId(masId);
        mdOrderAfterSaleExtendEntity.setCtlDate(new Date());
        mdOrderAfterSaleExtendEntity.setBuyerCtlContent(buyerContent);
        mdOrderAfterSaleExtendEntity.setSupplierCtlContent(supplierContent);
        mdOrderAfterSaleExtendEntity.setAsRemarks(markContent);
        mdOrderAfterSaleExtendEntity.setOrgName(orgName);
        mdOrderAfterSaleExtendEntity.setMoney(money);
        try {
            mdOrderAfterSaleDao.saveMdOrderSaleAfterEx(mdOrderAfterSaleExtendEntity);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SysRetrunMessage saveMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            mdOrderAfterSaleEntity = mdOrderAfterSaleDao.saveMdOrderSaleAfter(mdOrderAfterSaleEntity);
            sm.setObj(mdOrderAfterSaleEntity);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getMdOrderSaleAfterOnlyByMasId(Integer masId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysUserInfo userInfo = this.GetOneSessionAccount();
            MdOrderAfterSaleEntity mdOrderAfterSaleEntity = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(userInfo.getSuiId(), masId);
            sm.setObj(mdOrderAfterSaleEntity);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public MdOrderAfterSaleEntity getMdIrderSaleAfterObjectByMasId(Integer masId) throws HSKException {
        MdOrderAfterSaleEntity mdOrderAfterSaleEntity = new MdOrderAfterSaleEntity();
        try {
            mdOrderAfterSaleEntity = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(null, masId);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return mdOrderAfterSaleEntity;
    }

    @Override
    public SysRetrunMessage getMdOrderSaleAfterByMasId(Integer masId, SysUserInfo account) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            MdOrderAfterSaleEntity mdOrderAfterSaleEntity = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(account.getSuiId(), masId);
            sm.setObj(mdOrderAfterSaleEntity);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getMdOrderSaleAfterPageModel(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException {
        PagerModel pm = new PagerModel();
        try {
            pm = mdOrderAfterSaleDao.getMdOrderSaleAfterPageModel(mdOrderAfterSaleEntity);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getMdOrderSaleAfterList(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException {
        PagerModel pm = new PagerModel();
        try {
            List<MdOrderAfterSaleEntity> list = mdOrderAfterSaleDao.getMdOrderSaleAfterList(mdOrderAfterSaleEntity);
            pm.setItems(list);
            pm.setRows(list);
            pm.setTotal(list.size());
            pm.setTotalCount(list.size());
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getMdOrderSaleAfterMxList(Integer masId) throws HSKException {
        PagerModel pm = new PagerModel();
//        SysUserInfo sysUserInfo = this.GetOneShoppingSessionAccount();
        try {
//            Integer sui_id = account.getSuiId();
            List<Map<String, Object>> list = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, null, null, null);
            list.add(new HashMap<String, Object>());//仅仅做一个占位
            pm.setRows(list);
            pm.setItems(list);
            pm.setTotalCount(list.size() - 1);
            pm.setTotal(list.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getMdOrderSaleAfterMxByMasId(Integer masId, Integer limit, Integer page) throws HSKException {
        PagerModel pm = new PagerModel();
        SysUserInfo sysUserInfo = this.GetOneSessionAccount();
        try {
            Integer sui_id = sysUserInfo.getSuiId();
            List<Map<String, Object>> list = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, null, limit, page);
            list.add(new HashMap<String, Object>());//仅仅做一个占位
            pm.setRows(list);
            pm.setItems(list);
            pm.setTotalCount(list.size() - 1);
            pm.setTotal(list.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage updateMdOrderSaleAfterState(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        SysUserInfo sysUserInfo = this.GetOneSessionAccount();
        try {
            String refuse = mdOrderAfterSaleEntity.getRefuse();
            MdOrderAfterSaleEntity attr_mdOrderAfterSaleEntity = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(null, mdOrderAfterSaleEntity.getMasId());
            Integer asState = mdOrderAfterSaleEntity.getAsState();
            if (asState == 3 || asState == 4) { //拒绝与成功都归为成功
//                mdOrderAfterSaleEntity.setAfterSale(3);
//                attr_mdOrderAfterSaleEntity.setAfterSale(3);
            }
            if(asState == 1) {
                attr_mdOrderAfterSaleEntity.setSupplierAddress(mdOrderAfterSaleEntity.getSupplierAddress());
                attr_mdOrderAfterSaleEntity.setSupplierPhone(mdOrderAfterSaleEntity.getSupplierPhone());
                attr_mdOrderAfterSaleEntity.setSupplierName(mdOrderAfterSaleEntity.getSupplierName());
            }
            attr_mdOrderAfterSaleEntity.setAsState(asState);
            if(refuse != null && !refuse.equals("")){
                attr_mdOrderAfterSaleEntity.setRefuse(refuse);
            }
            mdOrderAfterSaleDao.updateMdOrderSaleAfterSale(attr_mdOrderAfterSaleEntity);

            List<MdOrderAfterSaleMxEntity> list = mdOrderAfterSaleDao.getMdOrderSaleASMxList(mdOrderAfterSaleEntity.getMasId());
            for (MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity : list) {
                mdOrderAfterSaleMxEntity.setAfterSale(asState);
                mdOrderAfterSaleDao.updateMdOrderSaleAfterSaleMx(mdOrderAfterSaleMxEntity);
            }
            sm.setObj(attr_mdOrderAfterSaleEntity.getMoiId());
            saveMdOrderAfterSaleEx(attr_mdOrderAfterSaleEntity.getAsState(), attr_mdOrderAfterSaleEntity.getMasId(), attr_mdOrderAfterSaleEntity.getAfterSale(), sysUserInfo.getUserName(), attr_mdOrderAfterSaleEntity.getAfterSaleMoney());

        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdOrderByAfterState(Integer moiId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            //一个订单下所有商品都售后成功，则订单失败
            List<Map<String, Object>> mxList = mdOrderMxDao.getMxListModelByMoiIdCount(moiId, null);
            Integer mxCount = null;
            if (mxList.size() > 0) {
                Map<String, Object> map = mxList.get(0);
                if (map == null) {
                    return sm;
                }
                mxCount = Integer.parseInt(map.get("total_count") == null ? "0" : map.get("total_count").toString());

            }
            Integer finshASCount = mdOrderAfterSaleDao.getMdOrderSaleASMXCount(null, moiId, 3);
            if (mxCount != null && mxCount <= finshASCount) {
                MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
                mdOrderInfo.setProcessStatus("6");
                mdOrderInfoDao.saveOrUpdateMdOrderInfo(mdOrderInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public MdSupplier getMdOrderSaleAfterSupplier(Integer masId, SysUserInfo account) throws HSKException {
        MdSupplier mdSupplier = new MdSupplier();
        try {
            List<Map<String, Object>> list = mdOrderAfterSaleDao.getMdOrderSaleAfterSupplier(account.getSuiId(), masId);
            if (list != null && list.size() > 0) {
                mdSupplier = mdSupplierDao.findMdSupplierById(Integer.parseInt(list.get(0).get("wz_id").toString()));
            }
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return mdSupplier;
    }

    @Override
    public PagerModel getOrderAfterSalePager(Integer moiId, Integer afterSale) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderAfterSaleEntity>());
        try {
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            pm = mdOrderAfterSaleDao.getMdOrderSaleAfterPageModelByMoiId(null, moiId, afterSale); //后台查看售后单，不需要受suiid限制
            if (pm.getItems() != null && pm.getItems().size() > 0) {
                List<MdOrderAfterSaleEntity> orderList = pm.getItems();
                for (MdOrderAfterSaleEntity order : orderList) {
                    Integer masId = order.getMasId();
                    List<Map<String, Object>> addList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, null, 5, 1);
                    List<Map<String, Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(masId, null);
                    Map<String, Object> map = listCount.get(0);
                    addList.add(map);
                    order.setAsMxList(addList);
                }
                pm.setItems(orderList);
                pm.setRows(orderList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getOrderAfterSaleMxModelByMasId(Integer masId, String searchName, Integer limit, Integer page) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<Map<String, Object>>());
        try {
            List<Map<String, Object>> mxList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, searchName, limit, page);
            List<Map<String, Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(masId, searchName);
            Map<String, Object> map = listCount.get(0);
            mxList.add(map);
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotalCount(Integer.parseInt(map.get("total_count").toString()));
            pm.setTotal(Integer.parseInt(map.get("total_count").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage countOrderAfterSale(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            Map<String, Integer> reMap = new HashMap<String, Integer>();
            Integer suiId = this.GetOneSessionAccount().getSuiId();
            Integer allCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(null, moiId, null);
            Integer thCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(null, moiId, "1");
            Integer tkCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(null, moiId, "2");
            Integer wcCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(null, moiId, "3");
            reMap.put("all", allCount);
            reMap.put("th", thCount);
            reMap.put("tk", tkCount);
            reMap.put("wc", wcCount);
            srm.setObj(reMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return srm;
    }
  //导出售后商品
    public  SysRetrunMessage exportLierAsmx(Integer masId) throws HSKException{
    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    		DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
    	 SysRetrunMessage srm = new SysRetrunMessage(1l);
    	 Map<String, Object> dataMap = new HashMap<String, Object>();
    	 SysUserInfo sysUserInfo=this.GetOneSessionAccount();
    	 //dataMap.put(key, value);
    	 String moiId="";
    	 Integer afterSale=0;
    	 String remarks="";
    	 String ctlDate="";
    	 String masCode="";
    	 String orderCode="";
    	 Date createDate = null;
    	 Integer OrgGxId=0;
    	 String nodeName="";
    	 try {
			List<Map<String, Object>> list = mdOrderAfterSaleDao.exportOrderAfterMx(masId);
				for (Map<String, Object> obj : list) {
					moiId=String.valueOf(obj.get("moi_id"));
					afterSale=Integer.valueOf(obj.get("after_sale").toString());
					remarks=String.valueOf(obj.get("remarks"));
					ctlDate=String.valueOf(obj.get("ctl_date"));
					masCode=String.valueOf(obj.get("mas_code"));
					orderCode=String.valueOf(obj.get("order_code"));
					createDate=(Date)obj.get("create_date");
					nodeName=String.valueOf(obj.get("node_name"));
				}
				dataMap.put("createDate",sdf.format(createDate));
				dataMap.put("moiId", moiId!="" ? moiId:"");
				/*dataMap.put("afterSale", afterSale!="" ? afterSale:"");*/
				if (afterSale==1) {
					dataMap.put("afterSale", "退货");
				}
				if (afterSale==2) {
					dataMap.put("afterSale", "退款");
				}if (afterSale==3) {
					dataMap.put("afterSale", "完成售后状态");
				}
				if (afterSale==null) {
					dataMap.put("afterSale", " ");
				}
				dataMap.put("remarks", remarks!="" ? remarks:"");
				//创建人
				dataMap.put("nodeName", nodeName!="" ? nodeName:"");
				try {
					Date ctlDate1=sdf.parse(ctlDate);
					String ctlDate2=sdf.format(ctlDate1);
					dataMap.put("ctlDate2", sdf.format(ctlDate1)!="" ? sdf.format(ctlDate1):"");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				dataMap.put("masCode", masCode!="" ? masCode:"");
			
				dataMap.put("orderCode", orderCode!="" ? orderCode:"");
				
			   Date Newdate=new Date();
			   dataMap.put("newDate", sdf.format(Newdate));
			   
			   dataMap.put("userName", sysUserInfo.getUserName());
				List<Map<String, Object>> list1 = mdOrderAfterSaleDao.exportOrderAfterMx2(masId);
				List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
				int index = 0;
				if (list1 != null && list1.size() > 0) {
					for (Map<String, Object> obj : list1) {
						Map<String, String> reMap = new HashMap<String, String>();
						index++;
						reMap.put("index", index+"");
						//after_sale  商品状态
						/*reMap.put("afterSale", obj.get("after_sale").toString()!="" ? obj.get("after_sale").toString():"");
						if (obj.get("after_sale")!=null&&obj.get("after_sale")=="1") {
							reMap.put("afterSale","退货中");
						}
						if(obj.get("after_sale")!=null&&obj.get("after_sale")=="2"){
						}*/
						if (obj.get("after_sale")!=null) {
							if (Integer.valueOf(obj.get("after_sale").toString())==1) {
								reMap.put("afterSale","退货中");
							}
							if (Integer.valueOf(obj.get("after_sale").toString())==2) {
								reMap.put("afterSale","退款中");
							}
							if (Integer.valueOf(obj.get("after_sale").toString())==3) {
								reMap.put("afterSale","售后成功");
							}
							if (Integer.valueOf(obj.get("after_sale").toString())==4) {
								reMap.put("afterSale","售后拒绝");
							}
							if (Integer.valueOf(obj.get("after_sale").toString())==5) {
								reMap.put("afterSale","申请中");
							}
						}else{
							reMap.put("afterSale"," ");
						}
						//base_number 申请售后数量
						reMap.put("baseNumber", df2.format(obj.get("baseNumber")!= null ?obj.get("baseNumber"):0d));
						//Basic_unit 基本单位
						reMap.put("BasicUnit", obj.get("Basic_unit")!=null ? obj.get("Basic_unit").toString():" ");
						//mat_name 商品名称
						reMap.put("matName", obj.get("mat_name").toString()!="" ? obj.get("mat_name").toString():"");
						//NORM 型号规格
						reMap.put("norm", obj.get("NORM").toString()!="" ? obj.get("NORM").toString():"");
						//mat_number 订单数量
						reMap.put("matNumber",  df2.format(obj.get("mat_number")!= null ?obj.get("mat_number"):0d));
						//number2 发货数量
						reMap.put("number2",  df2.format(obj.get("number2")!= null ?obj.get("number2"):0d));
						//Unit_money 单价
						reMap.put("UnitMoney", obj.get("Unit_money").toString()!="" ? obj.get("Unit_money").toString():"");
						//product_name 包装方式
						reMap.put("productName", obj.get("product_name").toString()!="" ? obj.get("product_name").toString():"");
						 reList.add(reMap);
					}
				}
				 dataMap.put("mxList", reList);
				 Configuration configuration = new Configuration();
	             configuration.setDefaultEncoding("UTF-8");
	             // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
	             // 这里我们的模板是放在org.cnzjw.template包下面
	             configuration.setClassForTemplateLoading(this.getClass(),
	                     "/ftl");
	             Template t = configuration.getTemplate("exportOrderAfterSale.ftl");
	             // 输出文档路径及名称
	             Calendar now = Calendar.getInstance();
	             long lo = now.getTimeInMillis();
	             Date date = new Date(lo);
	             SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
	             String l =sd.format(date);
	             String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
	             File file = new File(tmpPath);
	             if (!file.exists()) {
	                 file.mkdirs();
	             }
	             String realPath = tmpPath + l +"交易分析表" + ".xls";
	             File outFile = new File(realPath);
	             Writer out = null;
	             out = new BufferedWriter(new OutputStreamWriter(
	                     new FileOutputStream(outFile), "UTF-8"));
	             t.process(dataMap, out);
	             out.close();
	             String rootUrl = request.getContextPath() + EXPORT_PATH + l +"交易分析表"+ ".xls";
	             Map<String, String> map = new HashMap<String, String>();
	             map.put("path", rootUrl);
	             map.put("fileName", l+"交易分析表" + ".xls");
	             srm.setObj(map);
		} catch (HSKDBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/* Configuration configuration = new Configuration();
         configuration.setDefaultEncoding("UTF-8");
         // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
         // 这里我们的模板是放在org.cnzjw.template包下面
         configuration.setClassForTemplateLoading(this.getClass(),
                 "/ftl");
         Template t = configuration.getTemplate("exportSevenCountC.ftl");
         // 输出文档路径及名称
         Calendar now = Calendar.getInstance();
         long lo = now.getTimeInMillis();
         Date date = new Date(lo);
         SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
         String l = sd.format(date);
         String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
         File file = new File(tmpPath);
         if (!file.exists()) {
             file.mkdirs();
         }
         String realPath = tmpPath + l +"交易分析表" + ".xls";
         File outFile = new File(realPath);
         Writer out = null;
         out = new BufferedWriter(new OutputStreamWriter(
                 new FileOutputStream(outFile), "UTF-8"));
         t.process(dataMap, out);
         out.close();
         String rootUrl = request.getContextPath() + EXPORT_PATH + l +"交易分析表"+ ".xls";
         Map<String, String> map = new HashMap<String, String>();
         map.put("path", rootUrl);
         map.put("fileName", l+"交易分析表" + ".xls");
         srm.setObj(map);
     } catch (Exception e) {
         e.printStackTrace();
     }*/
    	 return srm;
    }

    @Override
    public PagerModel findOrderAfterSaleBySearch(Integer moiId, String searchAsState, String processStatus2, String searchName, String placeOrderTime, String searchAsType, Integer limit, Integer page) throws HSKException {
        PagerModel model = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            model = mdOrderAfterSaleDao.getMdOrderSaleAfterPageModelBySearch(null, moiId, processStatus2.equalsIgnoreCase("") ? searchAsState : processStatus2, searchName, placeOrderTime,  searchAsType);
//            List<Map<String, Object>> listMasids = new ArrayList<Map<String, Object>>();
//            if(!searchAsState.equals("") || !searchName.equals("")) {
//                listMasids = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListBySearch(null, moiId, searchAsState, searchName, limit, page);
//            }
//            String masIds = "";
            List<MdOrderAfterSaleEntity> asList = model.getItems();
//            for (MdOrderAfterSaleEntity as : asList){
//                masIds += as.getMasId() + ",";
//            }
//            for (Map<String, Object> masId : listMasids) {
//                String mas_id = masId.get("mas_id").toString() + ",";
//                if (masIds.indexOf(mas_id) < 0) {
//                    masIds += mas_id;
//                }
//            }
//            if(masIds.equals("")){
//                return model;
//            }
//            masIds = masIds.substring(0, masIds.length() - 1);
            List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>(); //mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masIds, searchName);
//            if(asList.size() <= 0){
//                for (Map<String, Object> moiid : listMasids) {
//                    asList.add(mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(null, Integer.parseInt(moiid.get("mas_id").toString())));
//                }
//            }
            String countName = "";

            for (MdOrderAfterSaleEntity order : asList) {
                mxList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(order.getMasId(), searchName, limit, page);
                countName = searchName;
                if(mxList.size() <= 0){
                    mxList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(order.getMasId(), null, limit, page);
                    countName = "";
                }
                List<Map<String,Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(order.getMasId(), countName);
                Map<String,Object> map = listCount.get(0);
                mxList.add(map);
                order.setAsMxList(mxList);
            }

            model.setItems(asList);
            model.setRows(asList);

        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public Integer getMdOrderSaleAfterMxCountByMasId(Integer masId) throws HSKException {
        Integer count = 0;
        try{
            count = mdOrderAfterSaleDao.getMdOrderSaleAfterMxCountByMasId(masId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Double getMdOrderSaleAfterMxMoneyByMasId(Integer masId) throws HSKException {
        Double money = 0d;
        try{
            money = mdOrderAfterSaleDao.getMdOrderSaleAfterMxMoneyByMasId(masId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return money;
    }
}
