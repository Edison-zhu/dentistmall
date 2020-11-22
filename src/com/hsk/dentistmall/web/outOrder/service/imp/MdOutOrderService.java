package com.hsk.dentistmall.web.outOrder.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.internal.Objects;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;
import org.apache.derby.tools.sysinfo;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.web.outOrder.model.ExportOrderInfo;
import com.hsk.dentistmall.web.outOrder.service.IMdOutOrderService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysUserInfoDao;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.daobbase.imp.SysUserInfoDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.ExportExcel;

import freemarker.template.Configuration;
import freemarker.template.Template;


/** 
  outOrder业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
 
@Service
public class  MdOutOrderService extends DSTService implements IMdOutOrderService {	
   /**
   *业务处理dao类  mdOutOrderDao 
   */
	@Autowired
	protected IMdOutOrderDao mdOutOrderDao;
	@Autowired
	private IMdInventoryDao mdInventoryDao;
	@Autowired
	protected IorgDao orgDao;
	@Autowired
	private IMdOutOrderMxDao mdOutOrderMxDao;
	@Autowired 
	
	private IMdMaterielInfoDao mdMaterielInfoDao;

	//购物车
	@Autowired
	private IMdCartsDao mdCartsDao;
	//保存收藏
//	@Autowired
////	private
	
	//新增获取制作部门
		@Autowired
		private ISysUserInfoDao sysUserInfoDao;
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
		   			SysUserInfo sysUserInfo = new SysUserInfo();
		   			sysUserInfo.setSuiId(att_MdOutOrder.getSuiId());
		   			sysUserInfo = (SysUserInfo) this.getOne(sysUserInfo);
		   			if (sysUserInfo != null)
		   				att_MdOutOrder.setReceivingObject(sysUserInfo.getAccount());
					srm.setObj(att_MdOutOrder);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	}

	@Override
	public SysRetrunMessage findFormObjectByWowId(Integer wowId) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);

		try {
			MdOutWarehouse mdOutWarehouse = new MdOutWarehouse();
			mdOutWarehouse.setWowId(wowId);
			mdOutWarehouse = (MdOutWarehouse) this.getOne(mdOutWarehouse);
			if (mdOutWarehouse == null || !Objects.equal(mdOutWarehouse.getWowId(), wowId)) {
				srm.setCode(2L);
				return srm;
			}
			MdOutOrder att_MdOutOrder = new MdOutOrder();
			att_MdOutOrder.setMooCode(mdOutWarehouse.getRelatedBill1());
			att_MdOutOrder = mdOutOrderDao.findMdOutOrderByMdOutOrder(att_MdOutOrder);
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setSuiId(att_MdOutOrder.getSuiId());
			sysUserInfo = (SysUserInfo) this.getOne(sysUserInfo);
			if (sysUserInfo != null)
				att_MdOutOrder.setReceivingObject(sysUserInfo.getAccount());
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
			//att_MdOutOrder.setFlowState_str("2,3");
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
	public PagerModel getPagerMdOutInfo(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, Integer warning) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<>());
		try{
			// 申领部门、申领人、申领单号、申领日期、出库日期、状态 ，相关的需要转为退货出库与报损出库

			// 申领单

			// 退货出库、报损出库
			SysUserInfo account = this.GetOneSessionAccount();
			att_MdOutOrder.setState("1");
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			//att_MdOutOrder.setFlowState_str("2,3");
			if(account.getOrganizaType().equals("20001")){
				att_MdOutOrder.setRbaId(account.getOldId());
				rbaId = account.getOldId();
			}else if(account.getOrganizaType().equals("20002")){
				att_MdOutOrder.setRbsId(account.getOldId());
				rbsId = account.getOldId();
			}else if(account.getOrganizaType().equals("20003")){
				att_MdOutOrder.setRbbId(account.getOldId());
				rbbId = account.getOldId();
			}
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setTab_like("mooCode,userName");
			att_MdOutOrder.setTab_order("mooId desc");


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

			pm = mdOutOrderDao.getPagerModelOutWare(att_MdOutOrder, rbaId, rbsId, rbbId, warning);

//			pm=mdOutOrderDao.getPagerModelByMdOutOrder(att_MdOutOrder);
		}catch (Exception e) {
			e.printStackTrace();
			throw new  HSKException(e);
		}
		return pm;
	}

	@Override
	public PagerModel getPagerMdOutOrderFlowState(MdOutOrder mdOutOrder) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			mdOutOrder.setState("1");
			//att_MdOutOrder.setFlowState_str("2,3");
			if(account.getOrganizaType().equals("20001")){
				mdOutOrder.setRbaId(account.getOldId());
			}else if(account.getOrganizaType().equals("20002")){
				mdOutOrder.setRbsId(account.getOldId());
			}else if(account.getOrganizaType().equals("20003")){
				mdOutOrder.setRbbId(account.getOldId());
			}
			mdOutOrder.setTab_like("mooCode,userName,orderTime,groupName");
			mdOutOrder.setTab_order("mooId desc");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			pm=mdOutOrderDao.getPagerModelByMdOutOrder(mdOutOrder);
		} catch (Exception e){
			e.printStackTrace();
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
		SysUserInfo account=this.GetOneSessionAccount();
		Integer rbaId = null;
		Integer rbsId = null;
		Integer rbbId = null;
		//att_MdOutOrder.setFlowState_str("2,3");
		if(account.getOrganizaType().equals("20001")){
			rbaId = account.getOldId();
		}else if(account.getOrganizaType().equals("20002")){
			rbsId = account.getOldId();
		}else if(account.getOrganizaType().equals("20003")){
			rbbId = account.getOldId();
		}
		try{
			List<Map<String,Object>> mxList = mdOutOrderDao.departmentStatisticalReport(value,rbaId,rbsId,rbbId);
			pm.setItems(mxList);
			pm.setRows(mxList);
		}catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	public SysRetrunMessage countDepartmentReport() throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Map<String, Object> SeMap = new HashMap<String, Object>();
		try{
			SysUserInfo sys=this.GetOneSessionAccount();
			Integer suiId=sys.getSuiId();
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			String purchaseType = null;
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				rbaId = account.getOldId();
				purchaseType = "1";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = Integer.parseInt(orgMap.get("tow"));
				rbbId = account.getOldId();
				purchaseType = "3";
			}

			Double outStock=mdOutOrderDao.outStock(suiId, rbaId, rbsId, rbbId);

			Double warehouse=mdOutOrderDao.warehouse(suiId,rbaId, rbsId, rbbId,purchaseType);

			Double returnStock=mdOutOrderDao.returnStock(suiId,rbaId, rbsId, rbbId);
			Double stockAlarm=mdOutOrderDao.stockAlarm(suiId,rbaId, rbsId, rbbId,purchaseType);
			SeMap.put("outStock",outStock);
			SeMap.put("warehouse",warehouse);
			SeMap.put("returnStock",returnStock);
			SeMap.put("stockAlarm",stockAlarm);
			Map<String, Object> safetyStockAlarm = mdOutOrderDao.safetyStockAlarm(suiId, rbaId, rbsId, rbbId, purchaseType);
			SeMap.put("safetyStockAlarm", safetyStockAlarm);
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
					reMap.put("mmiLable","申领出库");
					Object BasicUnit1=map.get("Basic_unit");
					String BasicUnit=String.valueOf(BasicUnit1);
					reMap.put("BasicUnit", BasicUnit!="null" ? BasicUnit:"");

					Object mmfCode1=map.get("mmfCode");
					String mmfCode=String.valueOf(mmfCode1);
					reMap.put("mmfCode",mmfCode!="null" ? mmfCode:"");

					Object matName1=map.get("matName");
					String matName=String.valueOf(matName1);
					reMap.put("matName",matName!="null" ? matName:"");

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


			List<Map<String, Object>> listSafety = mdOutOrderDao.SafetyEarlyWarning();
			List<Map<String, String>> reListSafety = new ArrayList<Map<String, String>>();
			if (listSafety != null && listSafety.size() > 0) {
				for (Map<String, Object> safetyMap : listSafety) {
					index++;
					Map<String, String> reMap = new HashMap<String, String>();
					reMap.put("index",index+"");
					reMap.put("mmiLable","安全库存");
//					Object mmiLable1=safetyMap.get("mmiLable");
//					String mmiLable=String.valueOf(mmiLable1);
//					reMap.put("mmiLable", mmiLable!="null" ? mmiLable:"");

					Object mmfCode1=safetyMap.get("mmfCode");
					String mmfCode=String.valueOf(mmfCode1);
					reMap.put("mmfCode",mmfCode!="null" ? mmfCode:"");

					Object BasicUnit1=safetyMap.get("Basic_unit");
					String BasicUnit=String.valueOf(BasicUnit1);
					reMap.put("BasicUnit", BasicUnit!="null" ? BasicUnit:"");

					Object matName1=safetyMap.get("matName");
					String matName=String.valueOf(matName1);
					reMap.put("matName",matName!="null" ? matName:"");

					Object norm1=safetyMap.get("norm");
					String norm=String.valueOf(norm1);
					reMap.put("norm",norm!=null ? norm:"");
					reMap.put("lack",df2.format(safetyMap.get("lack")!=null ? safetyMap.get("lack"):0d));

					Object lack=safetyMap.get("lack");
					Double countLack1=Double.parseDouble(String.valueOf(lack));
					countLack+=countLack1;
					reListSafety.add(reMap);
				}
			}
			dataMap.put("countLack",countLack);
			dataMap.put("mxList", reList);
			dataMap.put("mxListSafety", reListSafety);
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
	public PagerModel sevenClaimant(Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = mdOutOrderDao.sevenClaimant(limit,page,sysUserInfo.getSuiId());
			List<Map<String,Object>> mxList1 = mdOutOrderDao.sevenClaimant(null,null,sysUserInfo.getSuiId());
			for (Map<String, Object> map : mxList) {
				Object orderTime=map.get("orderTime");
				map.put("orderTime",sdf.format(orderTime));
			}
			Integer mxListCount1 =mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
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
	public PagerModel sevenOutMx(Integer mooId,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList =mdOutOrderDao.sevenOutMx(mooId,limit,page);
			List<Map<String,Object>> mxList1 =mdOutOrderDao.sevenOutMx(mooId,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
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
	public SysRetrunMessage getMatCodeReadOnly(Integer wmsMiId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Map<String, Integer> SeMap = new HashMap<String, Integer>();
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();

		try{
			Integer count1= mdOutOrderDao.getMatCodeReadOnly1(wmsMiId,sysUserInfo.getSuiId());
			//SeMap.put("placeOrderMoney1",placeOrderMoney1);
			Integer count2= mdOutOrderDao.getMatCodeReadOnly2(wmsMiId,sysUserInfo.getSuiId());
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
	//领料申领管理列表
	public PagerModel getPicking(String searchDdName,String searchDdStartTime,String selectState1,Integer date,Integer state,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer suiId=sysUserInfo.getSuiId();
		String date1="";
		String date2="";
		if (searchDdStartTime!=null&&!searchDdStartTime.equals("")){
			searchDdStartTime.split("~");
			String[] split=searchDdStartTime.split("~");
			date1=split[0];
			date2=split[1];
		}
		try {
			List<Map<String,Object>> mxList =mdOutOrderDao.getPicking(searchDdName,date1,date2,null,selectState1,suiId,limit,page);
			List<Map<String,Object>> mxList1 =mdOutOrderDao.getPicking(searchDdName,date1,date2,null,selectState1,suiId,null,null);
			for (Map<String, Object> map : mxList) {
				Object orderTime=map.get("orderTime");
				map.put("orderTime",sdf.format(orderTime));
			}
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return  pm;
	}

	//领料申领管理列表明细
	public PagerModel getPickingMx(Integer mooId,Integer limit,Integer page) throws HSKException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PagerModel pm=new PagerModel();
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			String purchaseType = null;
			if(account.getOrganizaType().equals("20001")){
				rbaId = account.getOldId();
				purchaseType = "1";
			}else if(account.getOrganizaType().equals("20002")){
				rbsId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20003")){
				rbbId = account.getOldId();
				purchaseType = "3";
			}
		List<Map<String,Object>> mxList =mdOutOrderDao.getPickingMx(mooId,null, rbaId, rbsId, rbbId, purchaseType);
			for (Map<String, Object> map : mxList) {
				Object orderTime=map.get("orderTime");
				if (orderTime!=null&&!orderTime.equals("")){
					map.put("orderTime",sdf.format(orderTime));
				}else {
					map.put("orderTime","");
				}
			}
		pm.setItems(mxList);
		pm.setRows(mxList);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return  pm;
	}

    //领料申领管理列表明细
    public PagerModel getMdoutLog(Integer mooId1,Integer limit,Integer page) throws HSKException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PagerModel pm=new PagerModel();
        try {
            List<Map<String,Object>> mxList =mdOutOrderDao.getMdoutLog(mooId1,limit,page);
            for (Map<String, Object> map : mxList) {
                Object createDate=map.get("createDate");
                if (createDate!=null&&!createDate.equals("")){
                    map.put("createDate",sdf.format(createDate));
                }else {
                    map.put("createDate","");
                }
            }
            pm.setItems(mxList);
            pm.setRows(mxList);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return  pm;
    }
	//领料管理列表详情
	//领料申领管理列表明细
	public PagerModel getPickingXq(Integer mooId,String gjz,Integer limit,Integer page) throws HSKException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PagerModel pm=new PagerModel();
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			String purchaseType = null;
			if(account.getOrganizaType().equals("20001")){
				rbaId = account.getOldId();
				purchaseType = "1";
			}else if(account.getOrganizaType().equals("20002")){
				rbsId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20003")){
				rbbId = account.getOldId();
				purchaseType = "3";
			}
			List<Map<String,Object>> mxList =mdOutOrderDao.getPickingMx(mooId,gjz, rbaId, rbsId, rbbId, purchaseType);
//			for (Map<String, Object> map : mxList) {
//				Object orderTime=map.get("orderTime");
//				if (orderTime!=null&&!orderTime.equals("")){
//					map.put("orderTime",sdf.format(orderTime));
//				}else {
//					map.put("orderTime","");
//				}
//			}
			pm.setItems(mxList);
			pm.setRows(mxList);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return  pm;
	}
	@Override
	public PagerModel getPagerModelBySearchName(String searchName,Integer wmsMiId,Integer mmtId,String brand, Integer mdpId, Integer mdpsId) throws HSKDBException{
		PagerModel model = new PagerModel(new ArrayList<MdMaterielInfo>());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			String purchaseType = "";
			Integer wzId = null;
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				rbaId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if (orgMap.containsKey("one"))
					rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = account.getOldId();
				purchaseType = "3";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if (orgMap.containsKey("one"))
					rbaId = Integer.parseInt(orgMap.get("one"));
				if (orgMap.containsKey("tow"))
					rbsId = Integer.parseInt(orgMap.get("tow"));
				rbbId = account.getOldId();
				purchaseType = "4";
			}
			wzId = account.getOldId();
			String brans = "";
			if (brand != null && !brand.equals("")) {
				String[] brandArray = brand.split(",");
				for (String b : brandArray) {
					brans += "'" + b + "',";
				}
				if (!brans.equals(""))
					brans = brans.substring(0, brans.length() - 1);
			}
			model = mdOutOrderDao.getPagerModelBySearchName(searchName,wmsMiId,mmtId,brans,mdpId,mdpsId,rbaId, rbsId, rbbId, wzId, purchaseType);
			if (model.getItems().isEmpty() || model.getTotalCount() == 0) {

				return model;
			}
			List<Map<String, Object>> list = model.getItems();
			Double qunatity = 0d;
			Double baseNumber = 0d;
			Double splitQuantitys=0d;

			String wmsMiIds = "";
			for (Map<String, Object> mdMaterielInfo : list) {
				wmsMiIds += mdMaterielInfo.get("wmsMiId") + ",";
			}
			if (!wmsMiIds.trim().equals(""))
				wmsMiIds = wmsMiIds.substring(0, wmsMiIds.length() - 1);

			List<Map<String, Object>> mapList = mdOutOrderDao.getCountBaseNumberAndQuantity(wmsMiIds, rbaId, rbsId, rbbId, purchaseType);
			List<Map<String, Object>> fileList = mdMaterielInfoDao.getLessenFilePath(wmsMiIds, rbaId, rbsId, rbbId, purchaseType);
			List<Map<String, Object>> normList = mdInventoryDao.getInventoryNormStringList(wmsMiIds, rbaId, rbsId, rbbId, purchaseType);

			Integer wmiid = null;
			boolean needBreak = false;
			for (Map<String, Object> mdMaterielInfo : list) {
				for (Map<String, Object> map : normList) {
					if (map.get("wmsMiId") == null && map.get("wmsMiId").toString().trim().equals("")) {

					} else {
						wmiid = Integer.parseInt(map.get("wmsMiId").toString());
						if (Objects.equal(mdMaterielInfo.get("wmsMiId"), wmiid)) {
							mdMaterielInfo.put("norm", map.get("normList"));
							break;
						}
					}
				}
				for (Map<String, Object> map : fileList) {
					if (map.get("wmsMiId") == null && map.get("wmsMiId").toString().trim().equals("")) {

					} else {
						wmiid = Integer.parseInt(map.get("wmsMiId").toString());
						if (Objects.equal(mdMaterielInfo.get("wmsMiId"), wmiid)) {
							mdMaterielInfo.put("lessenFilePath", map.get("lessenFilePath"));
							break;
						}
					}
				}
				for (Map<String, Object> map : mapList) {
//					qunatity = mdOutOrderDao.getCountQuantity(mdMaterielInfo.getWmsMiId(), null, rbaId, rbsId, rbbId, purchaseType);
//					baseNumber = mdOutOrderDao.getCountBaseNumber(mdMaterielInfo.getWmsMiId(), null, rbaId, rbsId, rbbId, purchaseType);
					if (map.get("wmsMiId") == null || map.get("wmsMiId").toString().trim().equals("")) {
						qunatity = 0d;
						baseNumber = 0d;
						splitQuantitys=0d;
					}else {
						wmiid = Integer.parseInt(map.get("wmsMiId").toString());
						if (Objects.equal(mdMaterielInfo.get("wmsMiId"), wmiid)) {
							needBreak = true;
							qunatity = Double.parseDouble(map.get("quantitys").toString());
							splitQuantitys = Double.parseDouble(map.get("splitQuantitys").toString());
							baseNumber = Double.parseDouble(map.get("baseNumber").toString());
						}
					}
					mdMaterielInfo.put("quantity", qunatity);
					mdMaterielInfo.put("baseNumber", baseNumber);
					mdMaterielInfo.put("splitQuantitys", splitQuantitys);
					if (needBreak) {
						needBreak = false;
						break;
					}
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		} catch (HSKException e) {
			e.printStackTrace();
		}
		return  model;
	}
	@Override
	public PagerModel getmmfMx(Integer wmsMiId) throws HSKDBException{
		PagerModel model = new PagerModel(new ArrayList<MdMaterielFormat>());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			String purchaseType = null;
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				rbaId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one"))
					rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = account.getOldId();
				purchaseType = "3";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one"))
					rbaId = Integer.parseInt(orgMap.get("one"));
				if(orgMap.containsKey("tow"))
					rbsId = Integer.parseInt(orgMap.get("tow"));
				rbbId = account.getOldId();
				purchaseType = "4";
			}
			model = mdOutOrderDao.getMmfMx(wmsMiId, rbaId, rbsId, rbbId);
			List<MdMaterielFormat> list = model.getItems();
			Double quantity = 0d;
			Double baseNumber = 0d;
			Double ratio = 1D;
			for (MdMaterielFormat mdMaterielFormat : list) {
				quantity = mdOutOrderDao.getCountQuantity(null, mdMaterielFormat.getMmfId(), rbaId, rbsId, rbbId, purchaseType);
				baseNumber = mdOutOrderDao.getCountBaseNumber(null, mdMaterielFormat.getMmfId(), rbaId, rbsId, rbbId, purchaseType);
				ratio = mdInventoryDao.getRatioByMdMaterielFomart(mdMaterielFormat, rbaId, rbsId, rbbId, purchaseType);
				mdMaterielFormat.setQuantity(quantity);
				mdMaterielFormat.setBaseNumber(baseNumber);
				mdMaterielFormat.setRatio(ratio);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		} catch (HSKException e) {
			e.printStackTrace();
		}
		return  model;
	}
	@Override
	public SysRetrunMessage saveCollect(Integer wmsMiId,String mmfId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		MdInventoryCollect att_MdInventoryCollect=new MdInventoryCollect();
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			String purchaseType = "";
			Integer wzId = null;
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				rbaId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one"))
					rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = account.getOldId();
				purchaseType = "3";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one"))
					rbaId = Integer.parseInt(orgMap.get("one"));
				if(orgMap.containsKey("tow"))
					rbsId = Integer.parseInt(orgMap.get("tow"));
				rbbId = account.getOldId();
				purchaseType = "4";
			}
			wzId = account.getOldId();


			Integer wiId=mdOutOrderDao.returnWiId(wmsMiId);
			if (mmfId!=null&&!mmfId.equals("")){
				String[] mmfIdArr= mmfId.split(",");
				for (int i = 0; i < mmfIdArr.length; ++i){
					MdInventoryCollect save1=new MdInventoryCollect();
						if (wiId!=0){
							save1.setWiId(wiId);
						}else {
							save1.setWiId(null);
						}
						Integer IntMmfId=Integer.parseInt(mmfIdArr[i]);
					save1.setMmfId(IntMmfId);
					save1.setWmsMiId(wmsMiId);
					save1.setSuiId(account.getSuiId());
					Integer count=mdOutOrderDao.seachCollect(save1);
					if (count>0){
						Integer micId=mdOutOrderDao.seachCollectId(save1);
						MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
						mdInventoryCollect.setMicId(micId);
						mdInventoryCollect = (MdInventoryCollect) this.getOne(mdInventoryCollect);
						this.deleteObjects(mdInventoryCollect);
					}else {
						save1.setState("1");
						this.newObject(save1);
						srm.setObj(save1);
					}
				}
			}else {
				if (wiId != 0) {
					att_MdInventoryCollect.setWiId(wiId);
				} else {
					att_MdInventoryCollect.setWiId(null);
				}
				Integer mmfId1 = mdOutOrderDao.returnMmfId(wmsMiId,rbaId,rbsId,rbbId,wzId,purchaseType);
				att_MdInventoryCollect.setMmfId(mmfId1);
				att_MdInventoryCollect.setWmsMiId(wmsMiId);
				att_MdInventoryCollect.setSuiId(account.getSuiId());
			Integer count=mdOutOrderDao.seachCollect(att_MdInventoryCollect);
			if (count>0){
				att_MdInventoryCollect.setState("0");
				Integer micId=mdOutOrderDao.seachCollectId(att_MdInventoryCollect);
				MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
				mdInventoryCollect.setMicId(micId);
				mdInventoryCollect = (MdInventoryCollect) this.getOne(mdInventoryCollect);
				this.deleteObjects(mdInventoryCollect);
				srm.setObj(att_MdInventoryCollect);
			}else {
				att_MdInventoryCollect.setState("1");
				this.newObject(att_MdInventoryCollect);
				srm.setObj(att_MdInventoryCollect);
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return srm;
	}
//
@Override
public SysRetrunMessage seachCollect(Integer wmsMiId,String mmfId) throws HSKException{
	SysRetrunMessage srm = new SysRetrunMessage(1l);
	MdInventoryCollect att_MdInventoryCollect=new MdInventoryCollect();
	try {
		Integer counts=0;
		SysUserInfo account = this.GetOneSessionAccount();
		//查看当前组织是否存在集团、医院
		Integer rbsId = null;
		Integer rbaId = null;
		Integer rbbId = null;
		SysOrgGx sysOrgGx=new SysOrgGx();
		sysOrgGx.setOrgGxId(account.getOrgGxId());
		Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
		String purchaseType = "";
		Integer wzId = null;
		if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
			rbaId = account.getOldId();
			purchaseType = "2";
		}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
			if(orgMap.containsKey("one"))
				rbaId = Integer.parseInt(orgMap.get("one"));
			rbsId = account.getOldId();
			purchaseType = "3";
		}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
			if(orgMap.containsKey("one"))
				rbaId = Integer.parseInt(orgMap.get("one"));
			if(orgMap.containsKey("tow"))
				rbsId = Integer.parseInt(orgMap.get("tow"));
			rbbId = account.getOldId();
			purchaseType = "4";
		}
		wzId = account.getOldId();
		Integer wiId=mdOutOrderDao.returnWiId(wmsMiId);
		if (mmfId!=null&&!mmfId.equals("")){
			String[] mmfIdArr= mmfId.split(",");
			for (int i = 0; i < mmfIdArr.length; ++i){
				MdInventoryCollect save1=new MdInventoryCollect();
				if (wiId!=0){
					save1.setWiId(wiId);
				}else {
					save1.setWiId(null);
				}
				Integer IntMmfId=Integer.parseInt(mmfIdArr[i]);
				save1.setMmfId(IntMmfId);
				save1.setWmsMiId(wmsMiId);
				save1.setSuiId(account.getSuiId());
				Integer count=mdOutOrderDao.seachCollect(save1);
				srm.setObj(count);
			}
		}else {
			if (wiId!=0){
				att_MdInventoryCollect.setWiId(wiId);
			}else {
				att_MdInventoryCollect.setWiId(null);
			}
			Integer mmfId1=mdOutOrderDao.returnMmfId(wmsMiId,rbaId,rbsId,rbbId,wzId,purchaseType);
			att_MdInventoryCollect.setMmfId(mmfId1);
			att_MdInventoryCollect.setSuiId(account.getSuiId());
			att_MdInventoryCollect.setWmsMiId(wmsMiId);
			Integer count=mdOutOrderDao.seachCollect(att_MdInventoryCollect);
			srm.setObj(count);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return srm;
}

//收藏列表
@Override
public PagerModel getCollectList(MdInventoryCollect mdInventoryCollect) throws HSKException {
	PagerModel pm = new PagerModel(new ArrayList<MdInventoryCollectView>());
	try {
		SysUserInfo account = this.GetOneSessionAccount();
		mdInventoryCollect.setSuiId(account.getSuiId());
		pm = mdOutOrderDao.getCollectList(mdInventoryCollect);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return pm;
}

//取消收藏
//	micId
@Override
public SysRetrunMessage deleteMicId(Integer micId) throws HSKException{
	SysRetrunMessage srm = new SysRetrunMessage(1l);
	try {
	MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
	mdInventoryCollect.setMicId(micId);
	mdInventoryCollect = (MdInventoryCollect) this.getOne(mdInventoryCollect);
	this.deleteObjects(mdInventoryCollect);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return srm;
	}
	//取消全部收藏
	@Override
	public SysRetrunMessage delectAllObjectBySuiId() throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.GetOneSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			mdOutOrderDao.delectAllObjectBySuiId(suiId);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new  HSKException(e);
		}
		return srm;
	}

	//一键加入物料清单
	@Override
	public SysRetrunMessage oneClickJoin(String wmsIds,String mmfIds1) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
//			SysUserInfo sysUserInfo = this.GetOneSessionAccount();
//			MdInventoryCollect mdInventoryCollect=mdOutOrderDao.findMdInventoryCollectById(351);
//			System.out.println(mdInventoryCollect.getWmsMiId());

		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new  HSKException(e);
		}
		return srm;
	}
	//通过商品名称关键字查询
	@Override
	public PagerModel searchMdFavoritesBySearch(String searchName) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<String,Object>>());
		try{
			pm=mdOutOrderDao.searchMdFavoritesBySearch(this.GetOneSessionAccount().getSuiId(), searchName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	//物料添加购物车
	@Override
	public SysRetrunMessage addCarts(Integer mcId,Integer wmsMiId ,String mmfId, String shus, String splitShus, Double price) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		try{
			SysUserInfo account = this.GetOneSessionAccount();

			String[] shuArray = shus.split(",");
			String[] splitShuArray = splitShus == null ? null : splitShus.split(",");
			Integer shu = null;
			Integer splitShu = null;
			Integer sui_id = sysUserInfo.getSuiId();
			if (mmfId!=null&&!mmfId.equals("")){
				String[] mmfIdArr= mmfId.split(",");
				for (int i = 0; i < mmfIdArr.length; ++i){
					MdCarts mdCarts = null;
					Integer IntMmfId=Integer.parseInt(mmfIdArr[i]);
					List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, IntMmfId);
					if(list != null && list.size() > 0){
						mdCarts = list.get(0);
						shu = Integer.parseInt(shuArray[i]);
						splitShu = splitShuArray == null ? 0 : Integer.parseInt(splitShuArray[i]);
						if(shu == null || shu == 0){
							shu = mdCarts.getMcCount() + 1;
						}
						if(splitShu == null){
							splitShu = mdCarts.getMcSplitCount() + 1;
						}
						mdCarts.setMcCount(shu+mdCarts.getMcCount());
						if (splitShu != 0&&splitShu!=null){
							if (mdCarts.getMcSplitCount()!=null){
								mdCarts.setMcSplitCount(splitShu+mdCarts.getMcSplitCount());
							}else {
								mdCarts.setMcSplitCount(splitShu+0);
							}
						}
						shu=mdCarts.getMcCount();
						splitShu=mdCarts.getMcSplitCount();
					} else {
						mdCarts = new MdCarts();
//						if (shu == null || shu == 0) {
////							shu = 1;
//							shu = Integer.parseInt(shuArray[i]);
//						}
//						if(splitShu == null){
//							splitShu = splitShuArray == null ? 0 : Integer.parseInt(splitShuArray[i]);
////							splitShu = 1;
//						}
						shu = Integer.parseInt(shuArray[i]);
						splitShu = splitShuArray == null ? 0 : Integer.parseInt(splitShuArray[i]);
//						mdCarts.setMcCount(shu);
//						if (splitShu != 0)
//							mdCarts.setMcSplitCount(splitShu);
					}
					mdCarts.setEditRen(account.getUserName());
					mdCarts.setEditDate(new Date());
					mdCarts.setMmfId(IntMmfId);
					mdCarts.setWmsMiId(wmsMiId);
					mdCarts.setMcCount(shu);
//					if (splitShu != 0&&splitShu!=null){
//						mdCarts.setMcSplitCount(splitShu);
//					}else {
//
//					}
//					if (splitShu != 0&&splitShu!=null){
						mdCarts.setMcSplitCount(splitShu);
//					}
					mdCarts.setSuiId(sui_id);
					mdCarts.setType(1);
					mdCartsDao.saveOrUpdateMdCarts(mdCarts);
				}
			}else {
				//查看当前组织是否存在集团、医院
				Integer rbsId = null;
				Integer rbaId = null;
				Integer rbbId = null;
				SysOrgGx sysOrgGx=new SysOrgGx();
				sysOrgGx.setOrgGxId(account.getOrgGxId());
				Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
				String purchaseType = "";
				Integer wzId = null;
				if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
					rbaId = account.getOldId();
					purchaseType = "2";
				}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
					rbaId = Integer.parseInt(orgMap.get("one"));
					rbsId = account.getOldId();
					purchaseType = "3";
				}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
					rbaId = Integer.parseInt(orgMap.get("one"));
					rbsId = Integer.parseInt(orgMap.get("two"));
					rbbId = account.getOldId();
					purchaseType = "4";
				}
				wzId = account.getOldId();
				Integer mmfId1 = mdOutOrderDao.returnMmfId(wmsMiId,rbaId,rbsId,rbbId,wzId,purchaseType);
				MdCarts mdCarts = null;
				List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, mmfId1);
				if (list != null && list.size() > 0) {
					mdCarts = list.get(0);
					if (shu == null) {
						shu = mdCarts.getMcCount() + 1;
					}
					mdCarts.setMcCount(shu);
					mdCarts.setEditRen(account.getUserName());
					mdCarts.setEditDate(new Date());
				} else {
					mdCarts = new MdCarts();
					if (shu == null) {
						shu = 1;
					}
				}
				mdCarts.setEditRen(account.getUserName());
				mdCarts.setEditDate(new Date());
				mdCarts.setMmfId(mmfId1);
				mdCarts.setWmsMiId(wmsMiId);
				mdCarts.setMcCount(shu);
				mdCarts.setSuiId(sui_id);
				mdCarts.setType(1);
				mdCartsDao.saveOrUpdateMdCarts(mdCarts);
			}
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	//收藏夹一件添加购物车
	@Override
	public SysRetrunMessage addCarts2(Integer mcId,String wmsMiId ,String mmfId, Integer shu, Double price) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		try{
			Integer sui_id = sysUserInfo.getSuiId();
//			if (mmfId!=null&&!mmfId.equals("")){
				String[] mmfIdArr= mmfId.split(",");
				String[] wmsMiIdArr=wmsMiId.split(",");
				for (int i = 0; i < mmfIdArr.length; ++i){
					MdCarts mdCarts = null;
					Integer IntMmfId=Integer.parseInt(mmfIdArr[i]);
					Integer IntwmsMiId=Integer.parseInt(wmsMiIdArr[i]);
					List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, IntMmfId);
					if(list != null && list.size() > 0){
						mdCarts = list.get(0);
						if(shu == null){
							shu = mdCarts.getMcCount() + 1;
						}
						mdCarts.setMcCount(shu);
						mdCarts.setEditRen(sysUserInfo.getUserName());
						mdCarts.setEditDate(new Date());
					} else{
						mdCarts = new MdCarts();
						if(shu == null){
							shu = 1;
						} }
					mdCarts.setEditRen(sysUserInfo.getUserName());
					mdCarts.setEditDate(new Date());
					mdCarts.setMmfId(IntMmfId);
					mdCarts.setWmsMiId(IntwmsMiId);
					mdCarts.setMcCount(shu);
					mdCarts.setSuiId(sui_id);
					mdCarts.setType(1);
					mdCartsDao.saveOrUpdateMdCarts(mdCarts);
				}
//			}
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	public SysRetrunMessage savePicking(String numberSums,String  mmfIdSums ,String wmsIdSums,String remarks,String splitQuantitys1) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		MdOutOrder att_MdOutOrder=new MdOutOrder();
		try {
			String[] numberSumsArray=numberSums.split(",");
			String[] mmfIdSumsArray=mmfIdSums.split(",");
			String[] wmsIdSumsArray=wmsIdSums.split(",");
			String[] splitQuantityArray=splitQuantitys1.split(",");
			Double baseNumber = 0d;
			for(String shu : numberSumsArray)
				baseNumber += Double.parseDouble(shu);
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);

			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			String purchaseType = null;

			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				att_MdOutOrder.setRbaId(account.getOldId());
				att_MdOutOrder.setPurchaseType("1");
				rbaId = account.getOldId();
				purchaseType = "1";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutOrder.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				att_MdOutOrder.setRbsId(account.getOldId());
				att_MdOutOrder.setPurchaseType("2");
				rbsId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutOrder.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				if(orgMap.containsKey("tow")){//如果存在上级医院
					att_MdOutOrder.setRbsId(Integer.parseInt(orgMap.get("tow")));
				}
				att_MdOutOrder.setRbbId(account.getOldId());
				att_MdOutOrder.setPurchaseType("3");
				rbbId = account.getOldId();
				purchaseType = "3";
			}
			att_MdOutOrder.setSuiId(account.getSuiId());
			att_MdOutOrder.setOrderTime(new Date());
			att_MdOutOrder.setState("1");
			att_MdOutOrder.setFlowState("2");
			att_MdOutOrder.setGroupName(account.getOrgGxId_Str());
			att_MdOutOrder.setRemarks(remarks);

			Date mooCodeDate=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMddhhmmssSSS");
			String mooCode="CK"+sdf.format(mooCodeDate);
			att_MdOutOrder.setMooCode(mooCode);
			att_MdOutOrder.setUserName(account.getUserName());
			this.newObject(att_MdOutOrder);

            MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
            att_MdOutOrderLog.setMooId(att_MdOutOrder.getMooId());
            att_MdOutOrderLog.setMooCode(att_MdOutOrder.getMooCode());
            att_MdOutOrderLog.setCreateRen(account.getUserName());
//            if (att_MdOutOrder.getFlowState().equals("1")){
//                att_MdOutOrderLog.setOperationState("");
//            }else if (att_MdOutOrder.getFlowState().equals("2")){
//                att_MdOutOrderLog.setOperationState("申领中");
//            }else if (att_MdOutOrder.getFlowState().equals("3")){
//                att_MdOutOrderLog.setOperationState("部分出库");
//            }else if (att_MdOutOrder.getFlowState().equals("4")){
//                att_MdOutOrderLog.setOperationState("已完成");
//            }else if (att_MdOutOrder.getFlowState().equals("5")){
//                att_MdOutOrderLog.setOperationState("撤销");
//            }
            att_MdOutOrderLog.setOperationState("提交申领单");
            att_MdOutOrderLog.setCreateDate(new Date());
            String createLog="提交申领单";
            att_MdOutOrderLog.setCreateLog(createLog);
            this.newObject(att_MdOutOrderLog);
			//保存入库明细信息和修改库存信息以及修改订单明细信息
			Double count=0d;
			Double number6=0d;
			Double number7=0d;
			for(int i=0;i < mmfIdSumsArray.length;i++){
				Integer mmfId = Integer.parseInt(mmfIdSumsArray[i]);
				Double number = Double.parseDouble(numberSumsArray[i]);
				Double splitQuantity=Double.parseDouble(splitQuantityArray[i]);
				Integer wmsId=Integer.parseInt(wmsIdSumsArray[i]);
				count += number;
				number6+=splitQuantity;
				MdInventoryView mdInventoryView=new MdInventoryView();
				mdInventoryView.setMmfId2(mmfId);
				MdInventoryView view1 = mdOutOrderDao.findMdInventoryViewByMdInventoryView(mdInventoryView);

				MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
				mdInventoryExtend.setMmfId(mmfId);
				MdInventory att_MdInventory = mdInventoryDao.findMdInventoryByExtend(mdInventoryExtend, rbaId, rbsId, rbbId, purchaseType);

				MdInventoryView view = mdInventoryDao.findMdInventoryViewById(att_MdInventory.getWiId());
//				//查询库存视图信息
//				MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(view1.getWiId());
				//保存入库明细信息
				MdOutOrderMx mdOutOrderMx = new MdOutOrderMx();
				mdOutOrderMx.setMooId(att_MdOutOrder.getMooId());
				mdOutOrderMx.setWmsMiId(view.getWmsMiId2());
				mdOutOrderMx.setMmfId(view.getMmfId2());
				MdMaterielInfo mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(wmsId);
				mdOutOrderMx.setMatCode(mdMaterielInfo.getMatCode());
				mdOutOrderMx.setItemKeyId(view.getItemKeyId()+"");
				mdOutOrderMx.setNorm(view.getMmfName());
				mdOutOrderMx.setBaseNumber(number);
				mdOutOrderMx.setSplitQuantity(splitQuantity);
				//mdOutOrderMx.setMatCode(view.getMatCode());
				mdOutOrderMx.setMatName(view.getMatName());
				mdOutOrderMx.setBasicUnit(view.getBasicUnit());
//				mdOutOrderMx.setSupplier(view.getApplicantName());
//				mdOutOrderMx.setInputMode(view.getMatPurchase());
				this.newObject(mdOutOrderMx);
			}
			att_MdOutOrder.setNumber6(number6);
			att_MdOutOrder.setNumber1(count);
//			att_MdOutOrder.
			att_MdOutOrder.setNumber2(0d);
			this.updateObject(att_MdOutOrder);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return  srm;
	}
	//查询物料清单个数
	public SysRetrunMessage countCollect1() throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
	try{
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		Integer countCollect1=mdOutOrderDao.countCollect1(sysUserInfo.getSuiId());
		srm.setObj(countCollect1);
	}catch (Exception e){
		e.printStackTrace();
		srm.setCode(0l);
		srm.setMeg("操作失败");
	}
		return srm;
	}
	//查询申领管理各状态个数
	public SysRetrunMessage getPickingStateCount(String searchDdName,String searchDdStartTime) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			Map<String, Integer> SeMap = new HashMap<String, Integer>();
			SysUserInfo sysUserInfo=this.GetOneSessionAccount();
			String date1="";
			String date2="";
			if (searchDdStartTime!=null&&!searchDdStartTime.equals("")){
				searchDdStartTime.split("~");
				String[] split=searchDdStartTime.split("~");
				date1=split[0];
				date2=split[1];
			}
			Integer countAll=mdOutOrderDao.getPickingStateCount(searchDdName,date1,date2,1,sysUserInfo.getSuiId());
			Integer countApplying=mdOutOrderDao.getPickingStateCount(searchDdName,date1,date2,2,sysUserInfo.getSuiId());
			Integer countPartial=mdOutOrderDao.getPickingStateCount(searchDdName,date1,date2,3,sysUserInfo.getSuiId());
			Integer countComplete=mdOutOrderDao.getPickingStateCount(searchDdName,date1,date2,4,sysUserInfo.getSuiId());
			Integer countrevoke=mdOutOrderDao.getPickingStateCount(searchDdName,date1,date2,5,sysUserInfo.getSuiId());
			SeMap.put("countAll",countAll);
			SeMap.put("countApplying",countApplying);
			SeMap.put("countPartial",countPartial);
			SeMap.put("countComplete",countComplete);
			SeMap.put("countrevoke",countrevoke);
			srm.setObj(SeMap);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

//	getPickingStateCount

	//查询领料申请明细申请订单
	public SysRetrunMessage getPickingOrderInfo(Integer mooId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			Map<String, String> SeMap = new HashMap<String, String>();
			SysUserInfo sysUserInfo=this.GetOneSessionAccount();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Map<String,Object>> list=mdOutOrderDao.getPickingOrderInfo(mooId);
			if (list != null && list.size() > 0) {
				for (Map<String, Object> map : list) {
					Object mooCode=map.get("moo_code");
					String mooCode1=mooCode.toString();
					SeMap.put("mooCode",mooCode1);
					Object remarks=map.get("remarks");
					String remarks1="";
					if (remarks!=null&&!remarks.equals("")){
						remarks1=remarks.toString();
					}
					SeMap.put("remarks",remarks1);
					Object orderTime=map.get("order_time");
					SeMap.put("orderTime",sdf.format(orderTime));
					Object flowState=map.get("flow_state");
					if (flowState.equals("2")){
						SeMap.put("flowState","申请中");
					}else if (flowState.equals("3")){
						SeMap.put("flowState","部分发货");
					}else if (flowState.equals("4")){
						SeMap.put("flowState","已完成");
					}else if (flowState.equals("5")){
						SeMap.put("flowState","撤销");
					}else if (flowState.equals("7")){
						SeMap.put("flowState","售后");
					}
				}
			}
			srm.setObj(SeMap);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	//撤销申领  申请中将状态改为撤销，部分则改为已成功
	public SysRetrunMessage saveCancelAll(Integer mooId1) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account=this.GetOneSessionAccount();
		try{
				MdOutOrder mdOutOrder=mdOutOrderDao.findMdOutOrderById(mooId1);
			if (mdOutOrder.getFlowState().equals("2")){
				mdOutOrder.setFlowState("5");
                MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
                att_MdOutOrderLog.setMooId(mdOutOrder.getMooId());
                att_MdOutOrderLog.setMooCode(mdOutOrder.getMooCode());
                att_MdOutOrderLog.setCreateRen(account.getUserName());
                att_MdOutOrderLog.setOperationState("撤销");
                att_MdOutOrderLog.setCreateDate(new Date());
                String createLog="撤销申领中商品";
                att_MdOutOrderLog.setCreateLog(createLog);
                this.newObject(att_MdOutOrderLog);
			}else if (mdOutOrder.getFlowState().equals("3")){
				mdOutOrder.setFlowState("4");
                MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
                att_MdOutOrderLog.setMooId(mdOutOrder.getMooId());
                att_MdOutOrderLog.setMooCode(mdOutOrder.getMooCode());
                att_MdOutOrderLog.setCreateRen(account.getUserName());
                att_MdOutOrderLog.setOperationState("已完成");
                att_MdOutOrderLog.setCreateDate(new Date());
                String createLog="撤销部分未出库商品，并完成申领";
                att_MdOutOrderLog.setCreateLog(createLog);
                this.newObject(att_MdOutOrderLog);
			}
			mdOutOrderDao.saveOrUpdateMdOutOrder(mdOutOrder);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}
	//再次申请物料清单
	public SysRetrunMessage saveReapply(Integer mooId1) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			String purchaseType = null;
			if(account.getOrganizaType().equals("20001")){
				rbaId = account.getOldId();
				purchaseType = "1";
			}else if(account.getOrganizaType().equals("20002")){
				rbsId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20003")){
				rbbId = account.getOldId();
				purchaseType = "3";
			}
			List<Map<String,Object>> mxList =mdOutOrderDao.getPickingMx(mooId1,null, rbaId, rbsId, rbbId, purchaseType);
			for (Map<String, Object> map : mxList) {
				MdCarts mdCarts = null;
				Integer shu=0;
				Integer splitShu=0;
				Object norm1=map.get("norm");
				String norm=norm1.toString();

				Object matName1=map.get("matName");
				String matName=matName1.toString();

				Integer wmsMiId=0;
				Integer mmfId=0;
				List<Map<String,Integer>> listMmfWmsId=mdOutOrderDao.getmmfWmsId(matName,norm);

				for (Map<String, Integer> mapMmfWmsId : listMmfWmsId) {
					wmsMiId=mapMmfWmsId.get("wmsMiId");
					mmfId=mapMmfWmsId.get("mmfId");
				}
				Object baseNumber1=map.get("baseNumber");
				String baseNumber=String.valueOf(baseNumber1.toString());
				Integer baseNumber2 =Double.valueOf(baseNumber).intValue();

				Object splitbasenumber1=map.get("splitbasenumber");
				Integer splitbasenumber2=0;
				if (splitbasenumber1!=null&&!splitbasenumber1.equals("")){
					String splitbasenumber=String.valueOf(splitbasenumber1.toString());
					 splitbasenumber2 =Double.valueOf(splitbasenumber).intValue();
				}
				List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sysUserInfo.getSuiId(), mmfId);
					if(list != null && list.size() > 0){
						mdCarts = list.get(0);
//						if(shu == null){
//							shu = mdCarts.getMcCount() + baseNumber2;
//						}
						if (mdCarts.getMcCount()!=null){
							shu=mdCarts.getMcCount() + baseNumber2;
						}else {
							shu=baseNumber2;
						}
						if (mdCarts.getMcSplitCount()!=null){
							splitShu=mdCarts.getMcSplitCount()+splitbasenumber2;
						}else {
							splitShu=splitbasenumber2;
						}
					} else{
						mdCarts = new MdCarts();
//						if(shu == null){
//							shu = baseNumber2;
//						}
//						mdCarts.setMcCount(shu);
						shu=baseNumber2;
						splitShu=splitbasenumber2;
					}
			mdCarts.setMmfId(mmfId);
			mdCarts.setWmsMiId(wmsMiId);
			mdCarts.setMcCount(shu);
				mdCarts.setMcSplitCount(splitShu);
			mdCarts.setSuiId(sysUserInfo.getSuiId());
			mdCarts.setType(1);
			mdCartsDao.saveOrUpdateMdCarts(mdCarts);
			}
			}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}


	//s
	public SysRetrunMessage saveBaseNumber(String momIds,String baseNumbers,String remarks,String splitQuantitys) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
        SysUserInfo account=this.GetOneSessionAccount();
		try{
			String[] momIdArr= momIds.split(",");
			String[] baseNumberArr= baseNumbers.split(",");
			String[] splitQuantityArr=splitQuantitys.split(",");
			Integer mooId=0;
			for (int i = 0; i < momIdArr.length; ++i){
				Integer IntMmfId=Integer.parseInt(momIdArr[i]);
				Double baseNumber=Double.parseDouble(baseNumberArr[i]);
				Double splitQuantity=Double.parseDouble(splitQuantityArr[i]);
				MdOutOrderMx mdOutOrderMx=mdOutOrderMxDao.findMdOutOrderMxById(IntMmfId);
				mdOutOrderMx.setBaseNumber(baseNumber);
				mdOutOrderMx.setSplitQuantity(splitQuantity);
                mooId=mdOutOrderMx.getMooId();
				mdOutOrderMxDao.saveOrUpdateMdOutOrderMx(mdOutOrderMx);
			}
            MdOutOrder mdOutOrder=mdOutOrderDao.findMdOutOrderById(mooId);
			mdOutOrder.setRemarks(remarks);
            MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
            att_MdOutOrderLog.setMooId(mdOutOrder.getMooId());
            att_MdOutOrderLog.setMooCode(mdOutOrder.getMooCode());
            att_MdOutOrderLog.setCreateRen(account.getUserName());
            att_MdOutOrderLog.setOperationState("修改");
            att_MdOutOrderLog.setCreateDate(new Date());
            String createLog="修改申领单";
            att_MdOutOrderLog.setCreateLog(createLog);
            this.newObject(att_MdOutOrderLog);
            this.saveOrUpdateObject(mdOutOrder);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}
	//删除申领详情中的规格商品
	public SysRetrunMessage saveDeletemomId(String momId1s) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account=this.GetOneSessionAccount();
		try{
		String[] momIdArr= momId1s.split(",");
		Integer mooId=0;
		for (int i = 0; i < momIdArr.length; ++i){
			Integer IntMmfId=Integer.parseInt(momIdArr[i]);
			MdOutOrderMx mdOutOrderMx=mdOutOrderMxDao.findMdOutOrderMxById(IntMmfId);
			mooId=mdOutOrderMx.getMooId();
			this.deleteObjects(mdOutOrderMx);
		}
			MdOutOrder mdOutOrder=mdOutOrderDao.findMdOutOrderById(mooId);
			MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
			att_MdOutOrderLog.setMooId(mdOutOrder.getMooId());
			att_MdOutOrderLog.setMooCode(mdOutOrder.getMooCode());
			att_MdOutOrderLog.setCreateRen(account.getUserName());
			att_MdOutOrderLog.setOperationState("移除");
			att_MdOutOrderLog.setCreateDate(new Date());
			String createLog="移除申请详情商品";
			att_MdOutOrderLog.setCreateLog(createLog);
			this.newObject(att_MdOutOrderLog);

		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}
	//根据规格ID查询库存
	public PagerModel selectMmfId(Integer mmfId) throws HSKException{
		PagerModel pm = new PagerModel();
		DecimalFormat df2 = new DecimalFormat("######0");
		Map<String, String> SeMap = new HashMap<String, String>();
		try{
			SysUserInfo sysUserInfo=this.GetOneSessionAccount();
			Map<String, Object> dataMap = null;
			List<Map<String,Object>> mxList=mdOutOrderDao.selectMmfId(mmfId);
			if (mxList!=null&& mxList.size()>0){
				for (Map<String, Object> map : mxList) {
					Object quantity=map.get("quantity");
					if(quantity!=null&&!quantity.equals("")){
						map.put("quantity",df2.format(quantity));
					}else {
						map.put("quantity","0");
					}
					Object basicnUnit=map.get("Basic_unit");
					if(basicnUnit!=null&&!basicnUnit.equals("")){
						map.put("basicnUnit",basicnUnit.toString());
					}else {
						map.put("basicnUnit","");
					}
					Object splitQuantity=map.get("split_quantity");
					if(splitQuantity!=null&&!splitQuantity.equals("")){
						map.put("splitQuantity",df2.format(splitQuantity));
					}else {
						map.put("splitQuantity","0");
					}
					Object unit=map.get("unit");
					if(unit!=null&&!unit.equals("")){
						map.put("unit",unit.toString());
					}else {
						map.put("unit","");
					}
					Object mmtCode=map.get("mmt_code");
					if(mmtCode!=null&&!mmtCode.equals("")){
						map.put("mmtCode1",mmtCode.toString());
					}else {
						map.put("mmtCode1","");
					}
				}
			}
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotalCount(mxList.size());
		}catch (Exception e){
			e.printStackTrace();
		}
		return pm;
	}

	//当有多个规格时  进行收藏判断

	public SysRetrunMessage getCollectMx(Integer wmsMiId,String  mmfIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account=this.GetOneSessionAccount();
		if (mmfIds!=null&&!mmfIds.equals("")){
			try{
				String[] momIdArr= mmfIds.split(",");
				Integer seachCollectInts=0;
				for (int i = 0; i < momIdArr.length; ++i){
					Integer IntMmfId=Integer.parseInt(momIdArr[i]);
					MdInventoryCollect mdInventoryCollect=new MdInventoryCollect();
					mdInventoryCollect.setWmsMiId(wmsMiId);
					mdInventoryCollect.setSuiId(account.getSuiId());
					mdInventoryCollect.setMmfId(IntMmfId);
					Integer seachCollectInt=mdOutOrderDao.seachCollect(mdInventoryCollect);
					seachCollectInts+=seachCollectInt;
				}
				if (seachCollectInts==momIdArr.length){
					srm.setObj(1);
				}else {
					srm.setObj(0);
				}
			}catch (Exception e){
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg("操作失败");
			}
		}
		return  srm;
	}

	@Override
	public SysRetrunMessage saveInventoryMaterielAliasName(Integer wmsMiId, String aliasName) throws HSKException {
		aliasName=aliasName.trim();
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		if (wmsMiId == null) {
			sm.setCode(2L); //无数据
			return sm;
		}
		if (aliasName == null || aliasName.equals("")) {
			sm.setCode(3L); //空字符串
			return sm;
		}
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			String purchaseType = null;
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				rbaId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = account.getOldId();
				purchaseType = "3";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = Integer.parseInt(orgMap.get("tow"));
				rbbId = account.getOldId();
				purchaseType = "4";
			}
			Integer wzId = account.getOldId();
			MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
			mdInventoryExtend.setWmsMiId(wmsMiId);
			mdInventoryExtend = (MdInventoryExtend) this.getOne(mdInventoryExtend);
			if (!Objects.equal(mdInventoryExtend.getWmsMiId(), wmsMiId)) {
				sm.setCode(3L);
				return sm;
			}
			if (mdInventoryExtend.getLinkWmsMiId() != null) {
				wmsMiId = mdInventoryExtend.getLinkWmsMiId();
			}
			MdMaterielInfo mdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(wmsMiId);
			if (mdMaterielInfo == null) {
				sm.setCode(2L); //无数据
				return sm;
			}
			if (mdMaterielInfo.getPurchaseType().equals(purchaseType) && Objects.equal(mdMaterielInfo.getWzId(), wzId)) {
				if (mdMaterielInfo.getAliasName() != null && !mdMaterielInfo.getAliasName().equals("")) {
					String[] aliasNames = mdMaterielInfo.getAliasName().split(",");
					if (aliasNames.length >= 3) {
						sm.setCode(6L);
						return sm;
					}
					if (Arrays.asList(aliasNames).contains(aliasName)) {
						sm.setCode(4L);
						return sm;
					}
				}
				if (mdMaterielInfo.getAliasName() != null && !mdMaterielInfo.getAliasName().equals("")) {
					mdMaterielInfo.setAliasName(mdMaterielInfo.getAliasName() + "," + aliasName);
				} else {
					mdMaterielInfo.setAliasName(aliasName);
				}
				this.updateObject(mdMaterielInfo);
				sm.setObj(mdMaterielInfo.getAliasName());
			} else {
				if (mdInventoryExtend.getAliasName() != null && !mdInventoryExtend.getAliasName().equals("")) {
					String[] aliasNames = mdInventoryExtend.getAliasName().split(",");
					if (aliasNames.length >= 3) {
						sm.setCode(6L);
						return sm;
					}
					if (Arrays.asList(aliasNames).contains(aliasName)) {
						sm.setCode(4L);
						return sm;
					}
				}
				if (mdInventoryExtend.getAliasName() != null && !mdInventoryExtend.getAliasName().equals("")) {
					mdInventoryExtend.setAliasName(mdInventoryExtend.getAliasName() + "," + aliasName);
				} else {
					mdInventoryExtend.setAliasName(aliasName);
				}
				this.updateObject(mdInventoryExtend);
				sm.setObj(mdInventoryExtend.getAliasName());
			}

		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage saveDeleteInventoryMaterielAliasName(Integer wmsMiId, String aliasName) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String aliasName1=aliasName;
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			Integer rbsId = null;
			Integer rbaId = null;
			Integer rbbId = null;
			String purchaseType = null;
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				rbaId = account.getOldId();
				purchaseType = "2";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = account.getOldId();
				purchaseType = "3";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				rbaId = Integer.parseInt(orgMap.get("one"));
				rbsId = Integer.parseInt(orgMap.get("tow"));
				rbbId = account.getOldId();
				purchaseType = "4";
			}
			Integer wzId = account.getOldId();
			MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
			mdInventoryExtend.setWmsMiId(wmsMiId);
			mdInventoryExtend = (MdInventoryExtend) this.getOne(mdInventoryExtend);
			if (mdInventoryExtend.getLinkWmsMiId() != null) {
				wmsMiId = mdInventoryExtend.getLinkWmsMiId();
			}
			MdMaterielInfo mdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(wmsMiId);
			if (mdMaterielInfo.getPurchaseType().equals(purchaseType) && Objects.equal(mdMaterielInfo.getWzId(), wzId)) {
				String aliasNames = mdMaterielInfo.getAliasName();

				Pattern p = Pattern.compile(".*,\\s*(.*)");
				Matcher m = p.matcher(aliasNames);
				if (m.find())
					if (aliasName.equals(m.group(1))) {
						aliasName = "," + aliasName;
					} else {
						aliasName = aliasName + ",";
					}
				aliasNames = aliasNames.replace(aliasName, "");
				mdMaterielInfo.setAliasName(aliasNames);
				if (mdMaterielInfo.getAliasName().equals(""))
					mdMaterielInfo.setAliasName(null);
				this.updateObject(mdMaterielInfo);
				srm.setObj(mdMaterielInfo.getAliasName());
			} else {
				String aliasNames = mdInventoryExtend.getAliasName();

				Pattern p = Pattern.compile(".*,\\s*(.*)");
				Matcher m = p.matcher(aliasNames);
				if (m.find())
					if (aliasName.equals(m.group(1))) {
						aliasName = "," + aliasName;
					} else {
						aliasName = aliasName + ",";
					}
				aliasNames = aliasNames.replace(aliasName, "");
				mdInventoryExtend.setAliasName(aliasNames);
				if (mdInventoryExtend.getAliasName().equals(""))
					mdInventoryExtend.setAliasName(null);
				this.updateObject(mdInventoryExtend);
				srm.setObj(mdInventoryExtend.getAliasName());
			}
		} catch (Exception e) {
			srm.setCode(0l);
			e.printStackTrace();
		}
		return srm;
	}

	//弹出页面数据
//	public PagerModel sevenOutMx(Integer mooId,Integer limit,Integer page) throws HSKException{
//		PagerModel pm=new PagerModel();
//		SysUserInfo sysUserInfo=this.GetOneSessionAccount();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			List<Map<String,Object>> mxList =mdOutOrderDao.sevenOutMx(mooId,limit,page);
//			List<Map<String,Object>> mxList1 =mdOutOrderDao.sevenOutMx(mooId,null,null);
//			Integer mxListCount1=mxList1.size();
//			pm.setItems(mxList);
//			pm.setRows(mxList);
//			pm.setTotal(mxListCount1);
//			pm.setTotalCount(mxList.size());
//		} catch (HSKDBException e) {
//			e.printStackTrace();
//		}
//		return  pm;
//	}

	}
