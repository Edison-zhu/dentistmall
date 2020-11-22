package com.hsk.dentistmall.web.storage.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdEnterOutCountDao;
import com.hsk.dentistmall.api.daobbase.IMdInventoryDao;
import com.hsk.dentistmall.api.daobbase.IMdOutWarehouseMxDao;
import com.hsk.dentistmall.api.persistence.MdEnterOutCount;
import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.dentistmall.api.persistence.MdOutWarehouse;
import com.hsk.dentistmall.api.persistence.MdOutWarehouseMx;
import com.hsk.dentistmall.web.storage.service.IMdExportService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class MdExportService extends DSTService implements IMdExportService {
	
	@Autowired
	private IMdOutWarehouseMxDao mdOutWarehouseMxDao;
	@Autowired
	protected IorgDao orgDao;
	@Autowired
	private IMdInventoryDao mdInventoryDao;
	@Autowired
	private IMdEnterOutCountDao mdEnterOutCountDao;
	
	@Override
	public SysRetrunMessage exportYkhc(String startTime, String endTime) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			MdOutWarehouse att_MdOutWarehouse = new MdOutWarehouse();
			MdOutWarehouseMx att_MdOutWarehouseMx = new MdOutWarehouseMx();
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				att_MdOutWarehouse.setRbaId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("1");
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				att_MdOutWarehouse.setRbsId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("2");
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				if(orgMap.containsKey("tow")){//如果存在上级医院
					att_MdOutWarehouse.setRbsId(Integer.parseInt(orgMap.get("tow")));
				}
				att_MdOutWarehouse.setRbbId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("3");
			}
			
			if(startTime != null && !startTime.trim().equals(""))
				att_MdOutWarehouse.setFinshDate_start(sdf.parse(startTime));
			if(endTime != null && !endTime.trim().equals(""))
				att_MdOutWarehouse.setFinshDate_end(sdf.parse(endTime));
			List<Map<Object,Object>> list = mdOutWarehouseMxDao.getMxListByOut(att_MdOutWarehouse, att_MdOutWarehouseMx);
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=1;
			int index=0;
			if(list != null && list.size() > 0){
				for(Map<Object,Object> obj: list){
					index++;
					Map<String,String> reMap=new HashMap<String,String>();
					reMap.put("index", index+"");
					reMap.put("finshDate", obj.get("FINSH_DATE").toString().substring(0, 10));
					reMap.put("wowCode", obj.get("wow_code").toString());
					reMap.put("customerName", obj.get("CUSTOMER_name")!=null?obj.get("CUSTOMER_name").toString():"");
					reMap.put("matCode", obj.get("mat_code").toString());
					reMap.put("matName", obj.get("mat_name").toString());
					reMap.put("norm", obj.get("NORM")!=null?obj.get("NORM").toString():"");
					reMap.put("basicUnit", obj.get("Basic_unit").toString());
					Double baseNumber=obj.get("base_number")!=null?Double.parseDouble(obj.get("base_number").toString()):0d;
					reMap.put("baseNumber", df2.format(baseNumber));
					Double price=Double.parseDouble(obj.get("price")!=null?obj.get("price").toString():"0");
					reMap.put("price", df.format(price));
					Double countMoney=price*baseNumber;
					reMap.put("countMoney", df.format(countMoney));
					reMap.put("customer", obj.get("CUSTOMER")!=null?obj.get("CUSTOMER").toString():"");
					
					reList.add(reMap);
				}
				rowCount+=reList.size();
			  }
			  dataMap.put("rowCount", rowCount);
			  dataMap.put("mxList", reList);
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(),
						"/ftl");
				Template t = configuration.getTemplate("exportYkhcList.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName", l+ ".xls");
				srm.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage exportSfchz(String time) throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysRetrunMessage exportPdd(MdInventoryView att_MdInventoryView) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdInventoryView.setRbaId(account.getOldId());
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdInventoryView.setRbsId(account.getOldId());
				  
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdInventoryView.setRbbId(account.getOldId());
			  }
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=7;
			dataMap.put("pdCode", CreateCodeUtil.getNo(""));
			dataMap.put("orgName", account.getOrgName());
			dataMap.put("timeArea", sdf.format(new Date()));
			dataMap.put("userName", account.getUserName());
			List<MdInventoryView> list = mdInventoryDao.getListByMdInventoryView(att_MdInventoryView);
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			int index=0;
			  if(list != null && list.size() > 0){
				  for(MdInventoryView obj: list){
					  index++;
					  Map<String,String> reMap=new HashMap<String,String>();
					  reMap.put("index", index+"");
					  reMap.put("matCode",obj.getMatCode()!=null?obj.getMatCode():"");
					  reMap.put("matName",obj.getMatName()!=null?obj.getMatName():"");
					 // reMap.put("brand", obj.getBrand());
					  reMap.put("mmfName", obj.getMmfName()!=null?obj.getMmfName():"");
					  //reMap.put("typeName", obj.getTypeName()!=null?obj.getTypeName():"");
					  reMap.put("quantity", df2.format(obj.getQuantity()));
					  reMap.put("basicUnit", obj.getBasicUnit()!=null?obj.getBasicUnit():"");
					  reList.add(reMap);
				  }
				  rowCount+=list.size();
			  }
			  dataMap.put("rowCount", rowCount);
			  dataMap.put("mxList", reList);
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(),
						"/ftl");
				Template t = configuration.getTemplate("exportPddList.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName", l+ ".xls");
				srm.setObj(map);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	private List<MdEnterOutCount> getMdEnterOutCount(MdInventoryView att_MdInventoryView,String month) throws Exception{
		List<MdEnterOutCount> reList = new ArrayList<MdEnterOutCount>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df= new DecimalFormat("######0.00"); 
		//获取上个月信息
		Date x = sdf.parse(month+"-01");
		Calendar cal = Calendar.getInstance();
		cal.setTime(x);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		x=cal.getTime();
		String pre = sdf.format(x);
		pre = pre.substring(0, 7);
		List<MdEnterOutCount> preList = mdEnterOutCountDao.getMdEnterWarehouseList(pre, att_MdInventoryView);
		//获取当前的库存信息
		List<MdInventoryView> modelList = mdInventoryDao.getListByMdInventoryView(att_MdInventoryView);
		List<Map<String,Object>> nowList = mdEnterOutCountDao.getNowInvemtoryCount(att_MdInventoryView);
		//获取本月的入库信息
		List<Map<String,Object>> enterList = mdEnterOutCountDao.getInvemtoryEnterCount(month,att_MdInventoryView);
		for(MdInventoryView model : modelList){
			MdEnterOutCount reModel = new MdEnterOutCount();
			reModel.setRbaId(model.getRbaId());
			reModel.setRbbId(model.getRbbId());
			reModel.setRbsId(model.getRbsId());
			reModel.setWiId(model.getWiId());
			reModel.setChCode(model.getMatCode()!=null?model.getMatCode():"");
			reModel.setChDm(model.getMatCode()!=null?model.getMatCode():"");
			reModel.setMatName(model.getMatName()!=null?model.getMatName():"");
			reModel.setNorm(model.getMmfName()!=null?model.getMmfName():"");
			reModel.setUnit(model.getUnit()!=null?model.getUnit():"");
			reModel.setCountTime(month);
			boolean isAdd=false;
			Double oldNum=0.0d;
			Double oldPrice=0.0d;
			Double oldMoney=0.0d;
			if(preList!= null && preList.size()>0){
				for(MdEnterOutCount preCount : preList){
					if(preCount.getWiId().equals(reModel.getWiId())){
						oldNum=Double.parseDouble(preCount.getNum());
						oldPrice=Double.parseDouble(preCount.getPrice());
						oldMoney=Double.parseDouble(preCount.getMoney());
					}
				}
			}
			Double num=0.0d;
			Double price=0.0d;
			Double money=0.0d;
			if(nowList!= null && nowList.size()>0){
				for(Map<String,Object> nowCount : nowList){
					if(nowCount.get("wi_id").toString().equals(reModel.getWiId()+"")){
						num=Double.parseDouble(nowCount.get("quantity").toString());
						money=Double.parseDouble(nowCount.get("money").toString());
						if(num>0)
							price=money/num;
					}
				}
			}
			
			Double inNum=0.0d;
			Double inPrice=0.0d;
			Double inMoney=0.0d;
			if(enterList!= null && enterList.size()>0){
				for(Map<String,Object> enterCount : enterList){
					if(enterCount.get("wi_id").toString().equals(reModel.getWiId()+"")){
						inNum=Double.parseDouble(enterCount.get("quantity").toString());
						inMoney=Double.parseDouble(enterCount.get("money").toString());
						if(inNum>0)
							inPrice=inMoney/inNum;
					}
				}
			}
			
			Double outNum=0.0d;
			Double outPrice=0.0d;
			Double outMoney=0.0d;
			outNum = oldNum+inNum-num;
			outMoney = oldMoney+inMoney-money;
			if(outNum>0)
				outPrice=outMoney/outNum;
			if(oldNum>0 || num>0 || inNum>0 || outNum>0)
				isAdd=true;
			if(isAdd){
				reModel.setOldNum(df.format(oldNum));
				reModel.setOldMoney(df.format(oldMoney));
				reModel.setOldPrice(df.format(oldPrice));
				reModel.setInNum(df.format(inNum));
				reModel.setInMoney(df.format(inMoney));
				reModel.setInPrice(df.format(inPrice));
				reModel.setOutNum(df.format(outNum));
				reModel.setOutMoney(df.format(outMoney));
				reModel.setOutPrice(df.format(outPrice));
				reModel.setNum(df.format(num));
				reModel.setMoney(df.format(money));
				reModel.setPrice(df.format(price));
				reList.add(reModel);
			}
		}
		return reList;
	}

	@Override
	public SysRetrunMessage newMdEnterOutCount(String month) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage();
		MdInventoryView att_MdInventoryView = new MdInventoryView();
		try {
			List<MdEnterOutCount> dataList = getMdEnterOutCount(att_MdInventoryView,month);
			if(dataList != null && dataList.size() >0){
				for(MdEnterOutCount model : dataList){
					mdEnterOutCountDao.saveMdEnterWarehouse(model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public SysRetrunMessage exportSfhz(String month) throws HSKException {
		
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			
			MdInventoryView att_MdInventoryView = new MdInventoryView();
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdInventoryView.setRbaId(account.getOldId());
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdInventoryView.setRbsId(account.getOldId());
				  
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdInventoryView.setRbbId(account.getOldId());
			  }
			SimpleDateFormat sdfx = new SimpleDateFormat("yyyy.MM");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=3;
			String nowTime = sdf.format(new Date());
			List<MdEnterOutCount> list = new ArrayList<MdEnterOutCount>();
			if(nowTime.equals(month)){//如果是当前月
				list = getMdEnterOutCount(att_MdInventoryView,month);
			}else{
				list = mdEnterOutCountDao.getMdEnterWarehouseList(month, att_MdInventoryView);
			}
			Double oldNumCount=0.0d;
			Double oldMoneyCount=0.0d;
			Double inNumCount=0.0d;
			Double inMoneyCount=0.0d;
			Double outNumCount=0.0d;
			Double outMoneyCount=0.0d;
			Double numCount=0.0d;
			Double moneyCount=0.0d;
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			if(list!= null && list.size()>0){
				for(MdEnterOutCount count:list){
					Map<String,String> reMap=new HashMap<String,String>();
					reMap.put("chCode",count.getChCode()!=null ? count.getChCode():"");
					reMap.put("chDm",count.getChDm());
					reMap.put("matName",count.getMatName());
					reMap.put("norm",count.getNorm());
					reMap.put("unit",count.getUnit());
					reMap.put("oldNum",count.getOldNum());
					reMap.put("oldPrice",count.getOldPrice());
					reMap.put("oldMoney",count.getOldMoney());
					reMap.put("inNum",count.getInNum());
					reMap.put("inPrice",count.getInPrice());
					reMap.put("inMoney",count.getInMoney());
					reMap.put("outNum",count.getOutNum());
					reMap.put("outPrice",count.getOutPrice());
					reMap.put("outMoney",count.getOutMoney());
					reMap.put("num",count.getNum());
					reMap.put("price",count.getPrice());
					reMap.put("money",count.getMoney());
					reList.add(reMap);
					oldNumCount+=Double.parseDouble(count.getOldNum());
					oldMoneyCount+=Double.parseDouble(count.getOldMoney());
					inNumCount+=Double.parseDouble(count.getInNum());
					inMoneyCount+=Double.parseDouble(count.getInMoney());
					outNumCount+=Double.parseDouble(count.getOutNum());
					outMoneyCount+=Double.parseDouble(count.getOutMoney());
					numCount+=Double.parseDouble(count.getNum());
					moneyCount+=Double.parseDouble(count.getMoney());
				}
				rowCount=rowCount+list.size();
			}
			Date x = sdf.parse(month);
			dataMap.put("month", sdfx.format(x));
			dataMap.put("oldNumCount", df.format(oldNumCount));
			dataMap.put("oldMoneyCount", df.format(oldMoneyCount));
			dataMap.put("inNumCount", df.format(inNumCount));
			dataMap.put("inMoneyCount", df.format(inMoneyCount));
			dataMap.put("outNumCount", df.format(outNumCount));
			dataMap.put("outMoneyCount", df.format(outMoneyCount));
			dataMap.put("numCount", df.format(numCount));
			dataMap.put("moneyCount", df.format(moneyCount));
			dataMap.put("rowCount", rowCount);
			
			dataMap.put("mxList", reList);
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(),
						"/ftl");
				Template t = configuration.getTemplate("exportSfhzList.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName", l+ ".xls");
				srm.setObj(map);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
}
