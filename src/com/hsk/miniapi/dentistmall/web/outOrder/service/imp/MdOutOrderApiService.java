package com.hsk.miniapi.dentistmall.web.outOrder.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import org.apache.derby.tools.sysinfo;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdInventoryApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdMaterielInfoApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdOutOrderApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdOutOrderMxApiDao;
import com.hsk.dentistmall.api.persistence.MdInventory;
import com.hsk.dentistmall.api.persistence.MdInventoryExtend;
import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdOutOrder;
import com.hsk.dentistmall.api.persistence.MdOutOrderMx;
import com.hsk.dentistmall.api.persistence.MdOutWarehouseMx;
import com.hsk.dentistmall.api.persistence.MdOutwarehouseOutorderEntity;
import com.hsk.dentistmall.web.outOrder.model.ExportOrderInfo;
import com.hsk.miniapi.dentistmall.web.outOrder.service.IMdOutOrderApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.miniapi.xframe.api.daobbase.imp.SysUserInfoApiDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.ExportExcel;

import freemarker.template.Configuration;
import freemarker.template.Template;


/** 
  outOrder业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
 
@Service
public class MdOutOrderApiService extends DSTApiService implements IMdOutOrderApiService {
   /**
   *业务处理dao类  mdOutOrderDao 
   */
	@Autowired
	protected IMdOutOrderApiDao mdOutOrderDao;
	@Autowired
	private IMdInventoryApiDao mdInventoryDao;
	@Autowired
	protected IorgApiDao orgDao;
	@Autowired
	private IMdOutOrderMxApiDao mdOutOrderMxDao;
	@Autowired 
	
	private IMdMaterielInfoApiDao mdMaterielInfoDao;
	
	//新增获取制作部门
		@Autowired
		private ISysUserInfoApiDao sysUserInfoDao;
 /**
	 * 根据md_out_order表主键删除MdOutOrder对象记录
     * @param  mooId  Integer类型(md_out_order表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer mooId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdOutOrder     att_MdOutOrder= mdOutOrderDao.findMdOutOrderById( mooId) ;
					srm.setObj(att_MdOutOrder);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_out_order表主键删除MdOutOrder对象记录
     * @param  mooId  Integer类型(md_out_order表主键)
	 * @throws HSKException
	 */

	public MdOutOrder findObject(Integer mooId) throws HSKException{
			MdOutOrder  att_MdOutOrder=new MdOutOrder();		
			try{
		        att_MdOutOrder= mdOutOrderDao.findMdOutOrderById( mooId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdOutOrder;
	}
	
	 
	 /**
	 * 根据md_out_order表主键删除MdOutOrder对象记录
     * @param  mooId  Integer类型(md_out_order表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mooId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   MdOutOrder  att_MdOutOrder= mdOutOrderDao.findMdOutOrderById( mooId) ;
			   att_MdOutOrder.setState("0");
			   this.updateObject(att_MdOutOrder);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_out_order表主键删除多条MdOutOrder对象记录
     * @param  MooIds  Integer类型(md_out_order表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mooIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = mooIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdOutOrderDao.deleteMdOutOrderById(Integer
							.valueOf(did));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			HSKException hse = new HSKException(e);
			hse.setDisposeType("01");
			throw hse;
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;	  
	 }
	 
 
	 /**
	 * 新增或修改md_out_order表记录 ,如果md_out_order表主键MdOutOrder.MooId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return MdOutOrder  修改后的MdOutOrder对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdOutOrder att_MdOutOrder) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdOutOrderDao.saveOrUpdateMdOutOrder(att_MdOutOrder); 
					srm.setObj(att_MdOutOrder);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOutOrder att_MdOutOrder) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdOutOrder>());
			  try{
				  SysUserInfo account = this.GetOneSessionAccount();
				  if(account.getOrganizaType().equals("20001")){
					  att_MdOutOrder.setRbaId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdOutOrder.setSuiId(account.getSuiId());
					  att_MdOutOrder.setPurchaseType("1");
				  }else if(account.getOrganizaType().equals("20002")){
					  att_MdOutOrder.setRbsId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdOutOrder.setSuiId(account.getSuiId());
					  att_MdOutOrder.setPurchaseType("2");
				  }else if(account.getOrganizaType().equals("20003")){
					  att_MdOutOrder.setRbbId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdOutOrder.setSuiId(account.getSuiId());
					  att_MdOutOrder.setPurchaseType("3");
				  }
				  att_MdOutOrder.setState("1");
				  att_MdOutOrder.setTab_like("mooCode,userName");
				  att_MdOutOrder.setTab_order("mooId desc");
					pm=mdOutOrderDao.getPagerModelByMdOutOrder(att_MdOutOrder);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

	@Override
	public SysRetrunMessage saveMdOutOrder(MdOutOrder att_MdOutOrder,
			String wiIds, String shus) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			//保存入库单信息
			String[] wiIdArray = wiIds.split(",");
			String[] shuArray = shus.split(",");
			Double baseNumber = 0d;
			for(String shu : shuArray)
				baseNumber += Double.parseDouble(shu);
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				att_MdOutOrder.setRbaId(account.getOldId());
				att_MdOutOrder.setPurchaseType("1");
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutOrder.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				att_MdOutOrder.setRbsId(account.getOldId());
				att_MdOutOrder.setPurchaseType("2");
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutOrder.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				if(orgMap.containsKey("tow")){//如果存在上级医院
					att_MdOutOrder.setRbsId(Integer.parseInt(orgMap.get("tow")));
				}
				att_MdOutOrder.setRbbId(account.getOldId());
				att_MdOutOrder.setPurchaseType("3");
			}
			att_MdOutOrder.setSuiId(account.getSuiId());
			att_MdOutOrder.setOrderTime(new Date());
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setFlowState("2");
			//添加申领管理中的申领部门
			att_MdOutOrder.setGroupName(account.getOrgGxId_Str());
			this.newObject(att_MdOutOrder);
			//保存入库明细信息和修改库存信息以及修改订单明细信息
			Double count=0d;
			for(int i=0;i < wiIdArray.length;i++){
				Integer wiId = Integer.parseInt(wiIdArray[i]);
				Double number = Double.parseDouble(shuArray[i]);
				count += number;
				MdInventoryView view = mdInventoryDao.findMdInventoryViewById(wiId);
				//查询库存视图信息
				MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
				//保存入库明细信息
				MdOutOrderMx mdOutOrderMx = new MdOutOrderMx();
				mdOutOrderMx.setMooId(att_MdOutOrder.getMooId());
				//mdOutOrderMx.setWmsMiId(view.getWmsMiId());
				//mdOutOrderMx.setMmfId(view.getMmfId());
				mdOutOrderMx.setItemKeyId(view.getItemKeyId()+"");
				mdOutOrderMx.setNorm(view.getMmfName());
				mdOutOrderMx.setBaseNumber(number);
				//mdOutOrderMx.setMatCode(view.getMatCode());
				mdOutOrderMx.setMatName(view.getMatName());
				mdOutOrderMx.setBasicUnit(view.getBasicUnit());
				//mdOutOrderMx.setSupplier(view.getApplicantName());
				//mdOutOrderMx.setInputMode(view.getMatPurchase());
				this.newObject(mdOutOrderMx);
			}
			att_MdOutOrder.setNumber1(count);
			att_MdOutOrder.setNumber2(0d);
			this.updateObject(att_MdOutOrder);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

    public SysRetrunMessage updateMdOutOrderMx(MdOutOrder att_MdOutOrder, String shus, String momIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try{
			String[] shuArray = shus.split(",");
			String[] momIdArray = momIds.split(",");
			Double count = 0d;
			MdOutOrderMx att_MdOutOrderMx = null;
			Integer mooId = 0;
            for(int index = 0; index < momIdArray.length; index++){
            	Integer momId = Integer.parseInt(momIdArray[index]);
            	att_MdOutOrderMx = mdOutOrderMxDao.findMdOutOrderMxById(momId);
				Double baseNumber = Double.parseDouble(shuArray[index]);
				if(att_MdOutOrderMx.getBaseNumber() != baseNumber) {
					att_MdOutOrderMx.setBaseNumber(baseNumber);
					mdOutOrderMxDao.updateMdOutOrderMxById(momId, att_MdOutOrderMx);
				}
				count += baseNumber;
				mooId = att_MdOutOrderMx.getMooId();
			}
            String remarks = att_MdOutOrder.getRemarks(); //获取remarks，否则再次搜索时会被清空
            att_MdOutOrder = mdOutOrderDao.findMdOutOrderById(mooId);
			att_MdOutOrder.setNumber1(count);
			att_MdOutOrder.setRemarks(remarks);
            mdOutOrderDao.saveOrUpdateMdOutOrder(att_MdOutOrder);
        }catch (Exception e){
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new  HSKException(e);
        }
        return srm;
    }

    @Override
	public SysRetrunMessage updateObjectFlowState(Integer mooId)
			throws HSKException {
		 SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   MdOutOrder  att_MdOutOrder= mdOutOrderDao.findMdOutOrderById( mooId) ;
			   if(att_MdOutOrder.getFlowState().equals("3")){
			   		att_MdOutOrder.setFlowState("4");
			   } else {
				   att_MdOutOrder.setFlowState("5");
			   }
			   this.updateObject(att_MdOutOrder);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}

	@Override
	public PagerModel getPagerMdOutOrder(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd)throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOutOrder>());
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setFlowState_str("2,3");
			if(account.getOrganizaType().equals("20001")){
				att_MdOutOrder.setRbaId(account.getOldId());
			}else if(account.getOrganizaType().equals("20002")){
				att_MdOutOrder.setRbsId(account.getOldId());
			}else if(account.getOrganizaType().equals("20003")){
				att_MdOutOrder.setRbbId(account.getOldId());
			}
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setTab_like("mooCode,userName");
			att_MdOutOrder.setTab_order("mooId desc");


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(outTimeStart != null && !outTimeStart.equals("")) {
				att_MdOutOrder.setOutTimeStart(sdf.parse(outTimeStart));
			}
			if(outTimeEnd != null && !outTimeEnd.equals("")) {
				att_MdOutOrder.setOutTimeEnd(sdf.parse(outTimeEnd));
			}
			if(orderTimeStart != null && !orderTimeStart.equals("")) {
				att_MdOutOrder.setOrderTime_start(sdf.parse(orderTimeStart));
			}
			if(orderTimeEnd != null && !orderTimeEnd.equals("")) {
				att_MdOutOrder.setOrderTime_end(sdf.parse(orderTimeEnd));
			}

			pm=mdOutOrderDao.getPagerModelByMdOutOrder(att_MdOutOrder);
		}catch (Exception e) {
			e.printStackTrace(); 
			throw new  HSKException(e);
		}
		return pm;
	}

	@Override
	public PagerModel getPagerMdOutOrderDistinct(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String distinctName)throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOutOrder>());
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setFlowState_str("2,3");
			if(account.getOrganizaType().equals("20001")){
				att_MdOutOrder.setRbaId(account.getOldId());
			}else if(account.getOrganizaType().equals("20002")){
				att_MdOutOrder.setRbsId(account.getOldId());
			}else if(account.getOrganizaType().equals("20003")){
				att_MdOutOrder.setRbbId(account.getOldId());
			}
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setTab_like("mooCode,userName");
			att_MdOutOrder.setTab_order("mooId desc");


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(outTimeStart != null && !outTimeStart.equals("")) {
				att_MdOutOrder.setOutTimeStart(sdf.parse(outTimeStart));
			}
			if(outTimeEnd != null && !outTimeEnd.equals("")) {
				att_MdOutOrder.setOutTimeEnd(sdf.parse(outTimeEnd));
			}
			if(orderTimeStart != null && !orderTimeStart.equals("")) {
				att_MdOutOrder.setOrderTime_start(sdf.parse(orderTimeStart));
			}
			if(orderTimeEnd != null && !orderTimeEnd.equals("")) {
				att_MdOutOrder.setOrderTime_end(sdf.parse(orderTimeEnd));
			}

			pm=mdOutOrderDao.getPagerModelByMdOutOrderDistinct(att_MdOutOrder, distinctName);
		}catch (Exception e) {
			e.printStackTrace();
			throw new  HSKException(e);
		}
		return pm;
	}
	
	/**
	 * 新增导出申领单详情 用于立即出库的导出之中
	 * @param wowID
	 * @return
	 * @throws HSKException
	 */
	
	@Override
	public SysRetrunMessage exportInfoApply(Integer mooId) throws HSKException {
	
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdOutOrder att_MdOutOrder= mdOutOrderDao.findMdOutOrderById(mooId) ;
			MdOutOrderMx mx = new MdOutOrderMx();
			mx.setMooId(mooId);
			List<MdOutOrderMx> mxList = this.getList(mx);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df= new DecimalFormat("######0.00");   
			DecimalFormat df2= new DecimalFormat("######0"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=2;
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			int index=0;
			//申领数量  出库数量  缺少数量  各个总和
			Double numberCount1=0d;
			Double numberCount2=0d;
			Double numberLack1=0d;
			Double numberLack=0d;
			for(MdOutOrderMx obj : mxList){
				index++;
				Map<String,String> reMap = new HashMap<String,String>();
				reMap.put("index",  index+"");
				reMap.put("matCode", obj.getMatCode());
				reMap.put("matName", obj.getMatName());
				reMap.put("mmfName", obj.getNorm());
				reMap.put("groupName", att_MdOutOrder.getGroupName() != null?att_MdOutOrder.getGroupName():" ");
				reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
				reMap.put("basicUnit", obj.getBasicUnit()!= null?obj.getBasicUnit():" ");
				//申领数量
				reMap.put("baseNumber",df2.format(obj.getBaseNumber()!=null?obj.getBaseNumber():0d));
				reMap.put("number1", df2.format(obj.getNumber1()!=null?obj.getNumber1():0d));
				//缺少数量
				/*numberLack=obj.getBaseNumber()-obj.getNumber1();
				reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");*/
				if (obj.getNumber1()!=null) {
					numberLack=obj.getBaseNumber()-obj.getNumber1();
				}if (obj.getNumber1()==null) {
					numberLack=obj.getBaseNumber()-0.0;
				}
					reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
				/*reMap.put("groupName", att_MdOutOrder.getGroupName() != null?att_MdOutOrder.getGroupName():" ");
				reMap.put("groupName", att_MdOutOrder.getGroupName() != null?att_MdOutOrder.getGroupName():" ");*/
				//包装方式
				MdMaterielInfo att_mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
				reMap.put("productName",
						 att_mdMaterielInfo.getProductName() != null ?  att_mdMaterielInfo.getProductName(): " ");

				/**
				 * 测试
				 */
			if(att_MdOutOrder.getOrderTime()!=null){
				reMap.put("orderTime", sdf.format(att_MdOutOrder.getOrderTime()));
			}else{
				reMap.put("orderTime", "");
			}
				reMap.put("userName", att_MdOutOrder.getUserName() != null?att_MdOutOrder.getUserName():" ");
				reList.add(reMap);
				//申请数量 总和
				numberCount1+=obj.getBaseNumber();
				//出库数量
				if (obj.getNumber1()!=null) {
					numberCount2+=obj.getNumber1();
				}
				numberLack1+=numberLack;
			}
			rowCount += mxList.size();
			dataMap.put("rowCount", rowCount);
			dataMap.put("mxList", reList);
			dataMap.put("mooCode", att_MdOutOrder.getMooCode());
			dataMap.put("groupName", att_MdOutOrder.getGroupName());
			//新增制作部门
			SysUserInfo account = this.GetOneSessionAccount();
			account.setSuiId(att_MdOutOrder.getSuiId());
			//制作部门
			dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
			if(att_MdOutOrder.getOrderTime()!=null){
				dataMap.put("orderTime", sdf.format(att_MdOutOrder.getOrderTime()));
			}else{
				dataMap.put("orderTime", "");
			}
			//
			//申领状态
			int in = Integer.parseInt(att_MdOutOrder.getFlowState()); 
			if (in==2) {
				dataMap.put("flowState", "申请中");
			}
			if (in==3) {
				dataMap.put("flowState", "部分出库");
			}
			
			if (in==4) {
				dataMap.put("flowState", "已完成");
			}
			if (in==5) {
				dataMap.put("flowState", "撤销");
			}
			dataMap.put("userName", att_MdOutOrder.getUserName());
			dataMap.put("numberCount1", numberCount1);
			dataMap.put("numberCount2", numberCount2);
			dataMap.put("numberLack1", numberLack1);
			
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate("exportOutOrderInfoApply.ftl");
			// 输出文档路径及名称
			Calendar now = Calendar.getInstance();
			/*long l = now.getTimeInMillis();*/
			long lo = now.getTimeInMillis();
			Date date=new Date(lo);
			SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
			String l=sd.format(date);
			String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
			File file = new File(tmpPath);
			if(!file.exists()) {
				file.mkdirs();
			}
	        String realPath = tmpPath +"CGSLCKDG"+l+ ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile),"UTF-8"));
			t.process(dataMap, out);
		
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH+"CGSLCKDG"+l+ ".xls";
			Map<String,String> map = new HashMap<String,String>();
			map.put("path", rootUrl);
			map.put("fileName","CGSLCKDG"+ l+ ".xls");
			srm.setObj(map);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	
	
	@Override
	public SysRetrunMessage exportInfo(Integer mooId) throws HSKException {
	
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdOutOrder att_MdOutOrder= mdOutOrderDao.findMdOutOrderById(mooId) ;
			MdOutOrderMx mx = new MdOutOrderMx();
			mx.setMooId(mooId);
			List<MdOutOrderMx> mxList = this.getList(mx);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df= new DecimalFormat("######0.00");   
			DecimalFormat df2= new DecimalFormat("######0"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=2;
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			for(MdOutOrderMx obj : mxList){
				Map<String,String> reMap = new HashMap<String,String>();
				reMap.put("matCode", obj.getMatCode());
				reMap.put("matName", obj.getMatName());
				reMap.put("mmfName", obj.getNorm());
				reMap.put("groupName", att_MdOutOrder.getGroupName() != null?att_MdOutOrder.getGroupName():" ");
				reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
				
				/**
				 * 测试
				 */
			if(att_MdOutOrder.getOrderTime()!=null){
				reMap.put("orderTime", sdf.format(att_MdOutOrder.getOrderTime()));
			}else{
				reMap.put("orderTime", "");
			}
				reMap.put("userName", att_MdOutOrder.getUserName() != null?att_MdOutOrder.getUserName():" ");
				reList.add(reMap);
			}
			rowCount += mxList.size();
			dataMap.put("rowCount", rowCount);
			dataMap.put("mxList", reList);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate("exportOutOrderInfo.ftl");
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
	//增加申领管理中的批量导出功能
	@Override
	public SysRetrunMessage exportListPi(MdOutOrder attMdOutOrder,MdOutOrderMx att_MdOutOrderMx, String mooIds) throws HSKException {
		String[] mooIdsArray = mooIds.split(",");
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			Map<String, Object> dataMap = null;
			int rowCount = 4;
				List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
				Double numberCount1=0d;
				Double numberCount2=0d;
				Double numberLack1=0d;
				int index=0;
			for (int i = 0; i < mooIdsArray.length; i++) {
				Integer momId = Integer.parseInt(mooIdsArray[i]);
				MdOutOrder att_MdOutOrder= mdOutOrderDao.findMdOutOrderById(momId) ;
				MdOutOrderMx mx = new MdOutOrderMx();
				mx.setMooId(momId);
				List<MdOutOrderMx> mxList = (List<MdOutOrderMx>) this
						.getList(mx);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat df = new DecimalFormat("######0.00");
				DecimalFormat df2 = new DecimalFormat("######0");
				dataMap = new HashMap<String, Object>();
				String timeArea="";
				if(att_MdOutOrder.getOrderTime_start()!=null){
					timeArea+=sdf.format(att_MdOutOrder.getOrderTime_start());
				}if (att_MdOutOrder.getOrderTime_start()!=null||att_MdOutOrder.getOrderTime_end()!=null) {
					timeArea+="-";
				}if (att_MdOutOrder.getOrderTime_end()!=null) {
					timeArea+=sdf.format(att_MdOutOrder.getOrderTime_end());
				}
				dataMap.put("timeArea", timeArea);
			/*	dataMap.put("userName", att_MdOutOrder.getUserName() != null ? att_MdOutOrder
						.getUserName() : " ");
				dataMap.put("groupName",
						att_MdOutOrder.getGroupName() != null ? att_MdOutOrder
								.getGroupName() : " ");
				*/
				//制作日期
				if(att_MdOutOrder.getCreateDate()!=null){
					dataMap.put("createDate", sdf.format(att_MdOutOrder.getCreateDate()));
				}else{
					dataMap.put("createDate", "");
				}
				int in = Integer.parseInt(att_MdOutOrder.getFlowState()); 
				if (in==2) {
					dataMap.put("flowState", "申请中");
				}
				if (in==3) {
					dataMap.put("flowState", "部分发货");
				}
				
				if (in==4) {
					dataMap.put("flowState", "已完成");
				}
				if (in==5) {
					dataMap.put("flowState", "撤销");
				}
				//新增制作部门
				SysUserInfo account = this.GetOneSessionAccount();
				account.setSuiId(att_MdOutOrder.getSuiId());
				//制作人
				dataMap.put("createRen",account.getUserName()!= null?account.getUserName():"");
				//制作部门
				
				dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
				//dataMap.put("index", index);*/
				Double numberLack=0d;

				for (MdOutOrderMx obj : mxList) {
						index++;
					Map<String, String> reMap = new HashMap<String, String>();	
					reMap.put("index", index+"");
					reMap.put("matCode",att_MdOutOrder.getMooCode());
					reMap.put("matName", obj.getMatName());
					reMap.put("mmfName", obj.getNorm());
					reMap.put("basicUnit",
							obj.getBasicUnit() != null ? obj.getBasicUnit(): " ");
					reMap.put("userName", att_MdOutOrder.getUserName()!= null?att_MdOutOrder.getUserName():"");
					
					reMap.put("groupName",
							att_MdOutOrder.getGroupName() != null ? att_MdOutOrder
									.getGroupName() : " ");
					
					//包装方式
					MdMaterielInfo att_mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
					reMap.put("productName",
							 att_mdMaterielInfo.getProductName() != null ?  att_mdMaterielInfo.getProductName(): " ");
					//申领数量
					reMap.put("baseNumber",df2.format(obj.getBaseNumber()!=null?obj.getBaseNumber():0d));
					reMap.put("number1", df2.format(obj.getNumber1()!=null?obj.getNumber1():0d));
					//reMap.put("number2", df2.format(obj.getNumber2()));
					//缺少数量
					//obj.getBaseNumber()-obj.getNumber1()
					
					//numberLack=obj.getBaseNumber()-obj.getNumber1();
					if (obj.getNumber1()!=null) {
						numberLack=obj.getBaseNumber()-obj.getNumber1();
					}if (obj.getNumber1()==null) {
						numberLack=obj.getBaseNumber()-0.0;
					}
						reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
					if (att_MdOutOrder.getOrderTime() != null) {
						reMap.put("orderTime",
								sdf.format(att_MdOutOrder.getOrderTime()));
					} else {
						reMap.put("orderTime", "");
					}
					reMap.put(
							"userName",
							att_MdOutOrder.getUserName() != null ? att_MdOutOrder
									.getUserName() : " ");
					reList.add(reMap);
					//申请数量 总和
					/*for (MdOutOrderMx mdOutOrderMx : mxList) {
						
					}*/
					numberCount1+=obj.getBaseNumber();
					//出库数量
					if (obj.getNumber1()!=null) {
						numberCount2+=obj.getNumber1();
					}
					//缺少数量
					numberLack1+=numberLack;
				}
				rowCount += mxList.size();
				dataMap.put("rowCount", rowCount);
				dataMap.put("mxList", reList);
				dataMap.put("numberCount1", numberCount1);
				dataMap.put("numberCount2", numberCount2);
				dataMap.put("numberLack1", numberLack1);
			}
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
			Template t = configuration.getTemplate("exportOutOrderInfoCPi.ftl");
			// 输出文档路径及名称
			Calendar now = Calendar.getInstance();
			long lo = now.getTimeInMillis();
			Date date=new Date(lo);
			SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
			String l=sd.format(date);
			String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;// 获得工程运行web的目录
			File file = new File(tmpPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String realPath = tmpPath +"CGSLCKPL"+ l + ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			if (dataMap != null) {
				t.process(dataMap, out);
			}
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH +"CGSLCKPL"+ l
					+ ".xls";
			Map<String, String> map = new HashMap<String, String>();
			map.put("path", rootUrl);
			map.put("fileName", "CGSLCKPL"+l + ".xls");
			srm.setObj(map);

		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	
	//---end
	
	
	//新增单个或多个导出
	@Override
	public SysRetrunMessage exportList(MdOutOrder attMdOutOrder,
			MdOutOrderMx att_MdOutOrderMx, String mooIds) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String[] mooIdsArray = mooIds.split(",");
		if (mooIdsArray.length==1) {
			try {
				Map<String, Object> dataMap = null;
				int rowCount = 4;
					List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
				for (int i = 0; i < mooIdsArray.length; i++) {
					Integer momId = Integer.parseInt(mooIdsArray[i]);	
					MdOutOrder att_MdOutOrder= mdOutOrderDao.findMdOutOrderById(momId) ;
					MdOutOrderMx mx = new MdOutOrderMx();
					mx.setMooId(momId);
					List<MdOutOrderMx> mxList = (List<MdOutOrderMx>) this
							.getList(mx);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					DecimalFormat df = new DecimalFormat("######0.00");
					DecimalFormat df2 = new DecimalFormat("######0");
					dataMap = new HashMap<String, Object>();
					/**
					 * 导出多个报表用到
					 */
					//订单号
					//申领时间
					if(att_MdOutOrder.getOrderTime()!=null){
						dataMap.put("orderTime", sdf.format(att_MdOutOrder.getOrderTime()));
					}else{
						dataMap.put("orderTime", "");
					}
					
					//申领部门
					dataMap.put("groupName",
							att_MdOutOrder.getGroupName() != null ? att_MdOutOrder
									.getGroupName() : " ");
					//申领人
					dataMap.put("userName", att_MdOutOrder.getUserName()!= null?att_MdOutOrder.getUserName():"");
					//创建时间
					//dataMap.put("createDate", sdf.format(att_MdOutOrder.getCreateDate()));
					
					if(att_MdOutOrder.getCreateDate()!=null){
						dataMap.put("createDate", sdf.format(att_MdOutOrder.getCreateDate()));
					}else{
						dataMap.put("createDate", "");
					}
					//制作部门
					SysUserInfo account = this.GetOneSessionAccount();
					account.setSuiId(att_MdOutOrder.getSuiId());
					dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
					
					//申领状态
					int in = Integer.parseInt(att_MdOutOrder.getFlowState()); 
					if (in==2) {
						dataMap.put("flowState", "申请中");
					}
					if (in==3) {
						dataMap.put("flowState", "部分发货");
					}
					
					if (in==4) {
						dataMap.put("flowState", "已完成");
					}
					if (in==5) {
						dataMap.put("flowState", "撤销");
					}
					int index=0;
					//申领数量  出库数量  缺少数量  各个总和
					Double numberCount1=0d;
					Double numberCount2=0d;
					Double numberLack1=0d;
					Double numberLack=0d;
					String matCode="";
					for (MdOutOrderMx obj : mxList) {
						 index++;
						Map<String, String> reMap = new HashMap<String, String>();	
						reMap.put("index", index+"");
						reMap.put("matCode", obj.getMatCode());
						reMap.put("matName", obj.getMatName());
						//单位类型
						reMap.put("basicUnit", obj.getBasicUnit());
						//包装方式
						MdMaterielInfo att_mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
						//reMap.put("productName",  att_mdMaterielInfo.getProductName());
						//申领数量
						reMap.put("baseNumber",df2.format(obj.getBaseNumber()!=null?obj.getBaseNumber():0d));
						reMap.put("number1", df2.format(obj.getNumber1()!=null?obj.getNumber1():0d));
						//reMap.put("number2", df2.format(obj.getNumber2()));
						//缺少数量
						if (obj.getNumber1()!=null) {
							numberLack=obj.getBaseNumber()-obj.getNumber1();
						}if(obj.getNumber1()==null){
							numberLack=obj.getBaseNumber()-0;
						}
						reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
						reMap.put("mmfName",obj.getNorm()!= null ?  obj.getNorm() : " ");
						reMap.put("groupName",att_MdOutOrder.getGroupName() != null ? att_MdOutOrder.getGroupName() : " ");
						if (att_MdOutOrder.getOrderTime() != null) {
							reMap.put("orderTime",
									sdf.format(att_MdOutOrder.getOrderTime()));
						} else {
							reMap.put("orderTime", "");
						}
						reMap.put(
								"userName",
								att_MdOutOrder.getUserName() != null ? att_MdOutOrder
										.getUserName() : " ");
						reList.add(reMap);
						matCode=obj.getMatCode();
						//申请数量 总和
						numberCount1+=obj.getBaseNumber();
						//出库数量
						if (obj.getNumber1()!=null) {
							numberCount2+=obj.getNumber1();
						}else{
							numberCount2=0.0;
						}
						
						//缺少数量
						numberLack1+=numberLack;
					}
					rowCount += mxList.size();
					dataMap.put("rowCount", rowCount);
					dataMap.put("matCode",att_MdOutOrder.getMooCode());
					
					dataMap.put("mxList", reList);
					dataMap.put("numberCount1", numberCount1);
					dataMap.put("numberCount2", numberCount2);
					dataMap.put("numberLack1", numberLack1);
				}

				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
				Template t = configuration.getTemplate("exportOutOrderInfoCC.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				/*long l = now.getTimeInMillis();----*/
				long lo = now.getTimeInMillis();
				Date date=new Date(lo);
				SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
				String l=sd.format(date);
				String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;// 获得工程运行web的目录
				File file = new File(tmpPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String realPath = tmpPath +"SLGLDG"+ l + ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile), "UTF-8"));
				if (dataMap != null) {
					t.process(dataMap, out);
				}
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH + "SLGLDG"+l
						+ ".xls";
				Map<String, String> map = new HashMap<String, String>();
				map.put("path", rootUrl);
				map.put("fileName", "SLGLDG"+l + ".xls");
				srm.setObj(map);

			} catch (Exception e) {
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg("操作失败!");
			}
		}
		else{
			try {
				Map<String, Object> dataMap = null;
				int rowCount = 4;
					List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
					Double numberCount1=0d;
					Double numberCount2=0d;
					Double numberLack1=0d;
					int index=0;
				for (int i = 0; i < mooIdsArray.length; i++) {
					Integer momId = Integer.parseInt(mooIdsArray[i]);
					MdOutOrder att_MdOutOrder= mdOutOrderDao.findMdOutOrderById(momId) ;
					MdOutOrderMx mx = new MdOutOrderMx();
					mx.setMooId(momId);
					List<MdOutOrderMx> mxList = (List<MdOutOrderMx>) this
							.getList(mx);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					DecimalFormat df = new DecimalFormat("######0.00");
					DecimalFormat df2 = new DecimalFormat("######0");
					dataMap = new HashMap<String, Object>();
					String timeArea="";
					if(att_MdOutOrder.getOrderTime_start()!=null){
						timeArea+=sdf.format(att_MdOutOrder.getOrderTime_start());
					}if (att_MdOutOrder.getOrderTime_start()!=null||att_MdOutOrder.getOrderTime_end()!=null) {
						timeArea+="-";
					}if (att_MdOutOrder.getOrderTime_end()!=null) {
						timeArea+=sdf.format(att_MdOutOrder.getOrderTime_end());
					}
					dataMap.put("timeArea", timeArea);
					//制作部门
					SysUserInfo account = this.GetOneSessionAccount();
					account.setSuiId(att_MdOutOrder.getSuiId());
					dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
					dataMap.put("groupName",
							att_MdOutOrder.getGroupName() != null ? att_MdOutOrder
									.getGroupName() : " ");
					dataMap.put("userName", att_MdOutOrder.getUserName()!= null?att_MdOutOrder.getUserName():"");
					int in = Integer.parseInt(att_MdOutOrder.getFlowState()); 
					if (in==2) {
						dataMap.put("flowState", "申请中");
					}
					if (in==3) {
						dataMap.put("flowState", "部分发货");
					}
					
					if (in==4) {
						dataMap.put("flowState", "已完成");
					}
					if (in==5) {
						dataMap.put("flowState", "撤销");
					}
					

					//dataMap.put("index", index);*/
					Double numberLack=0d;

					for (MdOutOrderMx obj : mxList) {
							index++;
						Map<String, String> reMap = new HashMap<String, String>();	
						reMap.put("index", index+"");
						reMap.put("matCode",att_MdOutOrder.getMooCode());
						reMap.put("matName", obj.getMatName());
						reMap.put("mmfName", obj.getNorm());
						reMap.put("basicUnit",
								obj.getBasicUnit() != null ? obj.getBasicUnit(): " ");
						reMap.put("groupName",
								att_MdOutOrder.getGroupName() != null ? att_MdOutOrder
										.getGroupName() : " ");
						reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
						//包装方式
						MdMaterielInfo att_mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
						reMap.put("productName",
								 att_mdMaterielInfo.getProductName() != null ?  att_mdMaterielInfo.getProductName(): " ");
						//申领数量
						reMap.put("baseNumber",df2.format(obj.getBaseNumber()!=null?obj.getBaseNumber():0d));
						reMap.put("number1", df2.format(obj.getNumber1()!=null?obj.getNumber1():0d));
						//reMap.put("number2", df2.format(obj.getNumber2()));
						//缺少数量
						//obj.getBaseNumber()-obj.getNumber1()
						/*if (obj.getNumber1()!=null) {
							numberLack=obj.getBaseNumber()-obj.getNumber1();
						}else{
							numberLack=obj.getBaseNumber()-0.0;
						}*/
						if (obj.getNumber1()!=null) {
							numberLack=obj.getBaseNumber()-obj.getNumber1();
						}if (obj.getNumber1()==null) {
							numberLack=obj.getBaseNumber()-0.0;
						}
						/*if (obj.getBaseNumber()!=null||obj.getNumber1()!=null) {	
						}*/
						if (numberLack!=null) {
							reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
						}
						if (att_MdOutOrder.getOrderTime() != null) {
							reMap.put("orderTime",
									sdf.format(att_MdOutOrder.getOrderTime()));
						} else {
							reMap.put("orderTime", "");
						}
						reMap.put(
								"userName",
								att_MdOutOrder.getUserName() != null ? att_MdOutOrder
										.getUserName() : " ");
						reList.add(reMap);
						//申请数量 总和
						/*for (MdOutOrderMx mdOutOrderMx : mxList) {
							
						}*/
						numberCount1+=obj.getBaseNumber();
						//出库数量
						if (obj.getNumber1()!=null) {
							numberCount2+=obj.getNumber1();
						}
						//缺少数量
						numberLack1+=numberLack;
					}
					rowCount += mxList.size();
					dataMap.put("rowCount", rowCount);
					dataMap.put("mxList", reList);
					dataMap.put("numberCount1", numberCount1!= null?numberCount2:"");
					dataMap.put("numberCount2", numberCount2!= null?numberCount2:"");
					dataMap.put("numberLack1", numberLack1);
				}
				
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
				Template t = configuration.getTemplate("exportOutOrderInfoC.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				//获取当前导出时间
				long lo = now.getTimeInMillis();
				Date date=new Date(lo);
				SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
				String l=sd.format(date);
				String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;// 获得工程运行web的目录
				File file = new File(tmpPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String realPath = tmpPath + "SLGLPL"+l + ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile), "UTF-8"));
				if (dataMap != null) {
					t.process(dataMap, out);
				}
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH + "SLGLPL"+l
						+ ".xls";
				Map<String, String> map = new HashMap<String, String>();
				map.put("path", rootUrl);
				map.put("fileName", "SLGLPL"+l + ".xls");
				srm.setObj(map);

			} catch (Exception e) {
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg("操作失败!");
			}
		}
		
		return srm;
	}

	public static void main(String[] args) {
		Map<String,String> reMap =new IdentityHashMap<String,String>();/*new HashMap<String,String>();*/
	}
	@Override
	public SysRetrunMessage copyFormObject(Integer mooId) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
	  	
		try{
				MdOutOrder mdOutOrder = new MdOutOrder();
	   			MdOutOrder att_MdOutOrder= mdOutOrderDao.findMdOutOrderById( mooId) ;
	   			mdOutOrder.setRbaId(att_MdOutOrder.getRbaId());
	   			mdOutOrder.setRbsId(att_MdOutOrder.getRbsId());
	   			mdOutOrder.setRbbId(att_MdOutOrder.getRbbId());
	   			mdOutOrder.setUserName(att_MdOutOrder.getUserName());
	   			mdOutOrder.setGroupName(att_MdOutOrder.getGroupName());
	   			mdOutOrder.setOrderTime(att_MdOutOrder.getOrderTime());
	   			mdOutOrder.setRemarks(att_MdOutOrder.getRemarks());
				srm.setObj(mdOutOrder);
			} catch (HSKDBException e) {
				e.printStackTrace(); 
				srm.setCode(0l);
				srm.setMeg(e.getMessage()); 
			}
			 return srm ;
	}
	/**
	 * 科室申领出库统计报表
	 */
	public PagerModel departmentStatisticalReport(Integer value) throws HSKException{
		PagerModel pm=new PagerModel();
		//Map<String, String> reMap = new HashMap<String, String>();
		try{
			List<Map<String,Object>> mxList = mdOutOrderDao.departmentStatisticalReport(value);
			pm.setItems(mxList);
			pm.setRows(mxList);
		}catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	public SysRetrunMessage countDepartmentReport() throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Map<String, Double> SeMap = new HashMap<String, Double>();
		Integer suiId = null;
		Integer rbsId= null;
		try{
			SysUserInfo sys=this.GetOneSessionAccount();
			suiId=sys.getSuiId();
			rbsId=sys.getOldId();

			Double outStock=mdOutOrderDao.outStock(rbsId);

			Double warehouse=mdOutOrderDao.warehouse(suiId,rbsId);

			Double returnStock=mdOutOrderDao.returnStock(suiId,rbsId);
			Double stockAlarm=mdOutOrderDao.stockAlarm(suiId,rbsId);
			SeMap.put("outStock",outStock);
			SeMap.put("warehouse",warehouse);
			SeMap.put("returnStock",returnStock);
			SeMap.put("stockAlarm",stockAlarm);
			Double safetyStockAlarm=mdOutOrderDao.safetyStockAlarm(suiId,rbsId);
			SeMap.put("safetyStockAlarm",safetyStockAlarm);
			srm.setObj(SeMap);
		}catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	}
	//导出库存不足报表
	public SysRetrunMessage exportCumulativeWarning() throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		DecimalFormat df = new DecimalFormat("######0.00");
		DecimalFormat df2 = new DecimalFormat("######0");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		Date StartDate=new Date();
		SysUserInfo SysUserInfo=this.GetOneSessionAccount();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userName",SysUserInfo.getUserName());
		Date date=new Date();
		dataMap.put("newDate",sdf.format(date));
		dataMap.put("nodeName",SysUserInfo.getOrgGxId_Str());
		try{
		List<Map<String, Object>> list = mdOutOrderDao.exportCumulativeWarning();
			List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
			int index = 0;
			Double countLack=0.0;
			if (list != null && list.size() > 0) {
				for (Map<String, Object> map : list) {
					index++;
					Map<String, String> reMap = new HashMap<String, String>();
					reMap.put("index",index+"");
					Object mmiLable1=map.get("mmiLable");
					String mmiLable=String.valueOf(mmiLable1);
					reMap.put("mmiLable", mmiLable!="null" ? mmiLable:"");

					Object mmfCode1=map.get("mmfCode");
					String mmfCode=String.valueOf(mmfCode1);
					reMap.put("mmfCode",mmfCode!=null ? mmfCode:"");

					Object matName1=map.get("matName");
					String matName=String.valueOf(matName1);
					reMap.put("matName",matName!=null ? matName:"");

					Object norm1=map.get("norm");
					String norm=String.valueOf(norm1);
					reMap.put("norm",norm!=null ? norm:"");
					reMap.put("lack",df2.format(map.get("lack")!=null ? map.get("lack"):0d));

					Object lack=map.get("lack");
					Double countLack1=Double.parseDouble(String.valueOf(lack));
					countLack+=countLack1;
					reList.add(reMap);
				}
			}
			dataMap.put("countLack",countLack);
			dataMap.put("mxList", reList);

			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate("exportWarehouseMissing.ftl");
			// 输出文档路径及名称
			Calendar now = Calendar.getInstance();
			long lo = now.getTimeInMillis();
			Date date1 = new Date(lo);
			SimpleDateFormat sd = new SimpleDateFormat("hhmmss");
			String l =sd.format(date1);
			String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
			File file = new File(tmpPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String realPath = tmpPath + "CGKCBXZ"+l+ ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			t.process(dataMap, out);
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH + "CGKCBXZ"+l+ ".xls";
			Map<String, String> map = new HashMap<String, String>();
			map.put("path", rootUrl);
			map.put("fileName", "CGKCBXZ"+l + ".xls");
			srm.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  srm;
	}
	public PagerModel sevenClaimant() throws HSKException{
		PagerModel pm=new PagerModel();
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = mdOutOrderDao.sevenClaimant(null,null,sysUserInfo.getSuiId());
			for (Map<String, Object> map : mxList) {
				Object orderTime=map.get("orderTime");
				map.put("orderTime",sdf.format(orderTime));
			}
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return  pm;
	}

	//展示申领中，部分出库，已完成数据
	public SysRetrunMessage ClaimPartialOut() throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Map<String, Double> SeMap = new HashMap<String, Double>();
		Integer suiId = null;
		try{
			SysUserInfo sys=this.GetOneSessionAccount();
			suiId=sys.getSuiId();
			Double claim=mdOutOrderDao.Claim(suiId);
			SeMap.put("claim",claim);

			Double partial=mdOutOrderDao.Partial(suiId);
			SeMap.put("partial",partial);

			Double completeOut=mdOutOrderDao.CompleteOut(suiId);
			SeMap.put("completeOut",completeOut);
			srm.setObj(SeMap);
		}catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	}
	//弹出页面数据
	public PagerModel sevenOutMx(Integer mooId) throws HSKException{
		PagerModel pm=new PagerModel();
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList =mdOutOrderDao.sevenOutMx(mooId);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return  pm;
	}
	public SysRetrunMessage  saveRead(String checkStr) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				String check[] = checkStr.split(",");
				MdOutOrder att_MdOutOrder=null;
				for(String mooIds : check){
					Integer mooId=Integer.valueOf(mooIds);
					att_MdOutOrder= mdOutOrderDao.findMdOutOrderById(mooId);
					att_MdOutOrder.setIsItRead("1");
					mdOutOrderDao.saveOrUpdateMdOutOrder(att_MdOutOrder);
				}
				srm.setObj(att_MdOutOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	}
}