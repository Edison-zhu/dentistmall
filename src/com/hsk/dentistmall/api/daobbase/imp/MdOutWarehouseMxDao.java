package com.hsk.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_out_warehouse_mx表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
@Component
public class  MdOutWarehouseMxDao extends SupperDao implements IMdOutWarehouseMxDao {	

	/**
	 * 根据md_out_warehouse_mx表主键查找MdOutWarehouseMx对象记录
	 * 
	 * @param  WowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @return MdOutWarehouseMx md_out_warehouse_mx表记录
	 * @throws HSKDBException
	 */	
	public MdOutWarehouseMx findMdOutWarehouseMxById(Integer WowMxId) throws HSKDBException{
				MdOutWarehouseMx  att_MdOutWarehouseMx=new MdOutWarehouseMx();				
				if(WowMxId!=null){
					att_MdOutWarehouseMx.setWowMxId(WowMxId);	
				    Object obj=	this.getOne(att_MdOutWarehouseMx);
					if(obj!=null){
						att_MdOutWarehouseMx=(MdOutWarehouseMx) obj;
					}
				}
				return  att_MdOutWarehouseMx;
	}
	 /**
	  * 根据md_out_warehouse_mx表主键删除MdOutWarehouseMx对象记录
	  * @param  WowMxId  Integer类型(md_out_warehouse_mx表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdOutWarehouseMxById(Integer WowMxId) throws HSKDBException{ 
		 MdOutWarehouseMx  att_MdOutWarehouseMx=new MdOutWarehouseMx();	
		  if(WowMxId!=null){
					  att_MdOutWarehouseMx.setWowMxId(WowMxId);
				  	  Object obj=	this.getOne(att_MdOutWarehouseMx);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_out_warehouse_mx表主键修改MdOutWarehouseMx对象记录
     * @param  WowMxId  Integer类型(md_out_warehouse_mx表主键)
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return MdOutWarehouseMx  修改后的MdOutWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOutWarehouseMx updateMdOutWarehouseMxById(Integer WowMxId,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException{
		  if(WowMxId!=null){
					att_MdOutWarehouseMx.setWowMxId(WowMxId);
				   	Object obj=	this.getOne(att_MdOutWarehouseMx);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdOutWarehouseMx;
	}
	
	/**
	 * 新增md_out_warehouse_mx表 记录
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdOutWarehouseMx(MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException{
		 return this.newObject(att_MdOutWarehouseMx);
	} 
		
	/**
	 * 新增或修改md_out_warehouse_mx表记录 ,如果md_out_warehouse_mx表主键MdOutWarehouseMx.WowMxId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
	 * @throws HSKDBException
	 */
	public  MdOutWarehouseMx saveOrUpdateMdOutWarehouseMx(MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdOutWarehouseMx);
		  return att_MdOutWarehouseMx;
	}
	
	/**
	 *根据MdOutWarehouseMx对象作为对(md_out_warehouse_mx表进行查询，获取列表对象
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return List<MdOutWarehouseMx>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdOutWarehouseMx> getListByMdOutWarehouseMx(MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException{
		String Hql=this.getHql( att_MdOutWarehouseMx); 
		List<MdOutWarehouseMx> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdOutWarehouseMx对象作为对(md_out_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return List<MdOutWarehouseMx>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutWarehouseMx(MdOutWarehouseMx att_MdOutWarehouseMx)
			throws HSKDBException {
		String Hql=this.getHql(att_MdOutWarehouseMx);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdOutWarehouseMx对象获取Hql字符串
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
	 *  @return hql字符串
	 */
	private String getHql(MdOutWarehouseMx att_MdOutWarehouseMx) {
		StringBuffer sbuffer = new StringBuffer(" from  MdOutWarehouseMx  where  1=1  ");
		String likeStr = att_MdOutWarehouseMx.getTab_like();
		String orderStr = att_MdOutWarehouseMx.getTab_order();

		//*****************无关字段处理begin*****************
		//处理in条件 出库明细信息表id(wowMxId)
		if (att_MdOutWarehouseMx.getWowMxId_str() != null &&
				!"".equals(att_MdOutWarehouseMx.getWowMxId_str().trim())) {
			String intStr = att_MdOutWarehouseMx.getWowMxId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  wowMxId=" + did + "   ");
					} else {
						sbuffer.append("  wowMxId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 出库单信息表id(wowId)
		if (att_MdOutWarehouseMx.getWowId_str() != null &&
				!"".equals(att_MdOutWarehouseMx.getWowId_str().trim())) {
			String intStr = att_MdOutWarehouseMx.getWowId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  wowId=" + did + "   ");
					} else {
						sbuffer.append("  wowId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 物料信息表id(wmsMiId)
		if (att_MdOutWarehouseMx.getWmsMiId_str() != null &&
				!"".equals(att_MdOutWarehouseMx.getWmsMiId_str().trim())) {
			String intStr = att_MdOutWarehouseMx.getWmsMiId_str().trim();
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
		//时间类型开始条件处理  修改时间(editDate)
		if (att_MdOutWarehouseMx.getEditDate_start() != null) {
			sbuffer.append(" and  editDate<='" + att_MdOutWarehouseMx.getEditDate_start() + "'");
		}
		//时间类型结束条件处理 修改时间(editDate)
		if (att_MdOutWarehouseMx.getEditDate_end() != null) {
			sbuffer.append(" and  editDate>'" + att_MdOutWarehouseMx.getEditDate_end() + "'");
		}
		//时间类型开始条件处理  创建时间(createDate)
		if (att_MdOutWarehouseMx.getCreateDate_start() != null) {
			sbuffer.append(" and  createDate<='" + att_MdOutWarehouseMx.getCreateDate_start() + "'");
		}
		//时间类型结束条件处理 创建时间(createDate)
		if (att_MdOutWarehouseMx.getCreateDate_end() != null) {
			sbuffer.append(" and  createDate>'" + att_MdOutWarehouseMx.getCreateDate_end() + "'");
		}
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//出库明细信息表id(wowMxId)
		if (att_MdOutWarehouseMx.getWowMxId() != null) {
			sbuffer.append(" and wowMxId=" + att_MdOutWarehouseMx.getWowMxId());
		}
		//出库单信息表id(wowId)
		if (att_MdOutWarehouseMx.getWowId() != null) {
			sbuffer.append(" and wowId=" + att_MdOutWarehouseMx.getWowId());
		}
		//物料信息表id(wmsMiId)
		if (att_MdOutWarehouseMx.getWmsMiId() != null) {
			sbuffer.append(" and wmsMiId=" + att_MdOutWarehouseMx.getWmsMiId());
		}
		//规格表id(mmfId)
		if (att_MdOutWarehouseMx.getMmfId() != null) {
			sbuffer.append(" and mmfId=" + att_MdOutWarehouseMx.getMmfId());
		}
		//单据编码(outCode)
		if (att_MdOutWarehouseMx.getOutCode() != null &&
				!"".equals(att_MdOutWarehouseMx.getOutCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("outCode") != -1) {
				sbuffer.append(" and outCode  like '%" + att_MdOutWarehouseMx.getOutCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and outCode   ='" + att_MdOutWarehouseMx.getOutCode() + "'");
			}
		}
		//物料编码(matCode)
		if (att_MdOutWarehouseMx.getMatCode() != null &&
				!"".equals(att_MdOutWarehouseMx.getMatCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matCode") != -1) {
				sbuffer.append(" and matCode  like '%" + att_MdOutWarehouseMx.getMatCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and matCode   ='" + att_MdOutWarehouseMx.getMatCode() + "'");
			}
		}
		//物料名称(matName)
		if (att_MdOutWarehouseMx.getMatName() != null &&
				!"".equals(att_MdOutWarehouseMx.getMatName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("matName") != -1) {
				sbuffer.append(" and matName  like '%" + att_MdOutWarehouseMx.getMatName() + "%'");
			} else {
				sbuffer.append(" and matName   ='" + att_MdOutWarehouseMx.getMatName() + "'");
			}
		}
		//基本单位(basicUnit)
		if (att_MdOutWarehouseMx.getBasicUnit() != null &&
				!"".equals(att_MdOutWarehouseMx.getBasicUnit().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("basicUnit") != -1) {
				sbuffer.append(" and basicUnit  like '%" + att_MdOutWarehouseMx.getBasicUnit() + "%'");
			} else {
				sbuffer.append(" and basicUnit   ='" + att_MdOutWarehouseMx.getBasicUnit() + "'");
			}
		}
		//批次属性(itemKeyId)
		if (att_MdOutWarehouseMx.getItemKeyId() != null &&
				!"".equals(att_MdOutWarehouseMx.getItemKeyId().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("itemKeyId") != -1) {
				sbuffer.append(" and itemKeyId  like '%" + att_MdOutWarehouseMx.getItemKeyId() + "%'");
			} else {
				sbuffer.append(" and itemKeyId   ='" + att_MdOutWarehouseMx.getItemKeyId() + "'");
			}
		}
		//录入方式(inputMode)
		if (att_MdOutWarehouseMx.getInputMode() != null &&
				!"".equals(att_MdOutWarehouseMx.getInputMode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("inputMode") != -1) {
				sbuffer.append(" and inputMode  like '%" + att_MdOutWarehouseMx.getInputMode() + "%'");
			} else {
				sbuffer.append(" and inputMode   ='" + att_MdOutWarehouseMx.getInputMode() + "'");
			}
		}
		//行号(lineNo)
		if (att_MdOutWarehouseMx.getLineNo() != null &&
				!"".equals(att_MdOutWarehouseMx.getLineNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("lineNo") != -1) {
				sbuffer.append(" and lineNo  like '%" + att_MdOutWarehouseMx.getLineNo() + "%'");
			} else {
				sbuffer.append(" and lineNo   ='" + att_MdOutWarehouseMx.getLineNo() + "'");
			}
		}
		//收货单号(soi)
		if (att_MdOutWarehouseMx.getSoi() != null &&
				!"".equals(att_MdOutWarehouseMx.getSoi().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("soi") != -1) {
				sbuffer.append(" and soi  like '%" + att_MdOutWarehouseMx.getSoi() + "%'");
			} else {
				sbuffer.append(" and soi   ='" + att_MdOutWarehouseMx.getSoi() + "'");
			}
		}
		//供应商(supplier)
		if (att_MdOutWarehouseMx.getSupplier() != null &&
				!"".equals(att_MdOutWarehouseMx.getSupplier().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("supplier") != -1) {
				sbuffer.append(" and supplier  like '%" + att_MdOutWarehouseMx.getSupplier() + "%'");
			} else {
				sbuffer.append(" and supplier   ='" + att_MdOutWarehouseMx.getSupplier() + "'");
			}
		}
		//扩展属性1(extendPropc1)
		if (att_MdOutWarehouseMx.getExtendPropc1() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc1().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc1") != -1) {
				sbuffer.append(" and extendPropc1  like '%" + att_MdOutWarehouseMx.getExtendPropc1() + "%'");
			} else {
				sbuffer.append(" and extendPropc1   ='" + att_MdOutWarehouseMx.getExtendPropc1() + "'");
			}
		}
		//扩展属性1(extendPropc2)
		if (att_MdOutWarehouseMx.getExtendPropc2() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc2().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc2") != -1) {
				sbuffer.append(" and extendPropc2  like '%" + att_MdOutWarehouseMx.getExtendPropc2() + "%'");
			} else {
				sbuffer.append(" and extendPropc2   ='" + att_MdOutWarehouseMx.getExtendPropc2() + "'");
			}
		}
		//扩展属性1(extendPropc3)
		if (att_MdOutWarehouseMx.getExtendPropc3() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc3().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc3") != -1) {
				sbuffer.append(" and extendPropc3  like '%" + att_MdOutWarehouseMx.getExtendPropc3() + "%'");
			} else {
				sbuffer.append(" and extendPropc3   ='" + att_MdOutWarehouseMx.getExtendPropc3() + "'");
			}
		}
		//扩展属性1(extendPropc4)
		if (att_MdOutWarehouseMx.getExtendPropc4() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc4().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc4") != -1) {
				sbuffer.append(" and extendPropc4  like '%" + att_MdOutWarehouseMx.getExtendPropc4() + "%'");
			} else {
				sbuffer.append(" and extendPropc4   ='" + att_MdOutWarehouseMx.getExtendPropc4() + "'");
			}
		}
		//扩展属性1(extendPropc5)
		if (att_MdOutWarehouseMx.getExtendPropc5() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc5().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc5") != -1) {
				sbuffer.append(" and extendPropc5  like '%" + att_MdOutWarehouseMx.getExtendPropc5() + "%'");
			} else {
				sbuffer.append(" and extendPropc5   ='" + att_MdOutWarehouseMx.getExtendPropc5() + "'");
			}
		}
		//扩展属性1(extendPropc6)
		if (att_MdOutWarehouseMx.getExtendPropc6() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc6().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc6") != -1) {
				sbuffer.append(" and extendPropc6  like '%" + att_MdOutWarehouseMx.getExtendPropc6() + "%'");
			} else {
				sbuffer.append(" and extendPropc6   ='" + att_MdOutWarehouseMx.getExtendPropc6() + "'");
			}
		}
		//扩展属性1(extendPropc7)
		if (att_MdOutWarehouseMx.getExtendPropc7() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc7().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc7") != -1) {
				sbuffer.append(" and extendPropc7  like '%" + att_MdOutWarehouseMx.getExtendPropc7() + "%'");
			} else {
				sbuffer.append(" and extendPropc7   ='" + att_MdOutWarehouseMx.getExtendPropc7() + "'");
			}
		}
		//扩展属性1(extendPropc8)
		if (att_MdOutWarehouseMx.getExtendPropc8() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc8().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc8") != -1) {
				sbuffer.append(" and extendPropc8  like '%" + att_MdOutWarehouseMx.getExtendPropc8() + "%'");
			} else {
				sbuffer.append(" and extendPropc8   ='" + att_MdOutWarehouseMx.getExtendPropc8() + "'");
			}
		}
		//扩展属性1(extendPropc9)
		if (att_MdOutWarehouseMx.getExtendPropc9() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc9().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc9") != -1) {
				sbuffer.append(" and extendPropc9  like '%" + att_MdOutWarehouseMx.getExtendPropc9() + "%'");
			} else {
				sbuffer.append(" and extendPropc9   ='" + att_MdOutWarehouseMx.getExtendPropc9() + "'");
			}
		}
		//扩展属性1(extendPropc10)
		if (att_MdOutWarehouseMx.getExtendPropc10() != null &&
				!"".equals(att_MdOutWarehouseMx.getExtendPropc10().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("extendPropc10") != -1) {
				sbuffer.append(" and extendPropc10  like '%" + att_MdOutWarehouseMx.getExtendPropc10() + "%'");
			} else {
				sbuffer.append(" and extendPropc10   ='" + att_MdOutWarehouseMx.getExtendPropc10() + "'");
			}
		}
		//备注(remarks)
		if (att_MdOutWarehouseMx.getRemarks() != null &&
				!"".equals(att_MdOutWarehouseMx.getRemarks().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("remarks") != -1) {
				sbuffer.append(" and remarks  like '%" + att_MdOutWarehouseMx.getRemarks() + "%'");
			} else {
				sbuffer.append(" and remarks   ='" + att_MdOutWarehouseMx.getRemarks() + "'");
			}
		}
		//修改时间(editDate)
		if (att_MdOutWarehouseMx.getEditDate() != null) {
			sbuffer.append(" and  editDate='" + att_MdOutWarehouseMx.getEditDate() + "'");
		}
		//修改人(editRen)
		if (att_MdOutWarehouseMx.getEditRen() != null &&
				!"".equals(att_MdOutWarehouseMx.getEditRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
				sbuffer.append(" and editRen  like '%" + att_MdOutWarehouseMx.getEditRen() + "%'");
			} else {
				sbuffer.append(" and editRen   ='" + att_MdOutWarehouseMx.getEditRen() + "'");
			}
		}
		//创建时间(createDate)
		if (att_MdOutWarehouseMx.getCreateDate() != null) {
			sbuffer.append(" and  createDate='" + att_MdOutWarehouseMx.getCreateDate() + "'");
		}
		//创建人(createRen)
		if (att_MdOutWarehouseMx.getCreateRen() != null &&
				!"".equals(att_MdOutWarehouseMx.getCreateRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
				sbuffer.append(" and createRen  like '%" + att_MdOutWarehouseMx.getCreateRen() + "%'");
			} else {
				sbuffer.append(" and createRen   ='" + att_MdOutWarehouseMx.getCreateRen() + "'");
			}
		}

		if (att_MdOutWarehouseMx.getSearchMatName() != null && !att_MdOutWarehouseMx.getSearchMatName().equals(""))
			sbuffer.append(" and matName like '%" + att_MdOutWarehouseMx.getSearchMatName() + "%'");
		if (att_MdOutWarehouseMx.getSearchMmfName() != null && !att_MdOutWarehouseMx.getSearchMmfName().equals(""))
			sbuffer.append(" and norm like '%" + att_MdOutWarehouseMx.getSearchMmfName() + "%'");

		//*****************数据库字段处理end***************
		if (orderStr != null && !"".equals(orderStr.trim())) {
			sbuffer.append(" order by " + orderStr);
		}
						 /*
						 else {
							  sbuffer.append( " order by  WowMxId   desc " );
					      }
					      */

		return sbuffer.toString();
	}
	@Override
	public PagerModel getPagerMdOutOrderMxByMmfId(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException {
		String sql = "SELECT a.customer_name,a.user_name,DATE_FORMAT(a.finsh_date,'%Y-%m-%d %H:%i:%s') as finsh_date,a.company_type,"
					+"b.base_number,b.mat_name,b.norm,a.supplier_name FROM md_out_warehouse a,md_out_warehouse_mx b WHERE a.wow_id=b.wow_id";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRbaId() != null)
			sql += " and rba_id="+att_MdOutWarehouse.getRbaId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRbbId() != null)
			sql += " and rbb_id="+att_MdOutWarehouse.getRbbId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRbsId() != null)
			sql += " and rbs_id="+att_MdOutWarehouse.getRbsId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getPurchaseType() != null && !"".equals(att_MdOutWarehouse.getPurchaseType().trim()))
			sql += " and purchase_type="+att_MdOutWarehouse.getPurchaseType().trim();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getCompanyType() != null && !"".equals(att_MdOutWarehouse.getCompanyType().trim()))
			sql += " and company_type="+att_MdOutWarehouse.getCompanyType().trim();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getCustomerName() != null && !"".equals(att_MdOutWarehouse.getCustomerName().trim()))
			sql += " and customer_name like '%"+att_MdOutWarehouse.getCustomerName().trim()+"%'";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getUserName() != null && !"".equals(att_MdOutWarehouse.getUserName().trim()))
			sql += " and user_name like '%"+att_MdOutWarehouse.getUserName().trim()+"%'";
		if(att_MdOutWarehouseMx != null && att_MdOutWarehouseMx.getMmfId() != null)
			sql += " and mmf_id="+att_MdOutWarehouseMx.getMmfId();
		if(att_MdOutWarehouseMx != null && att_MdOutWarehouseMx.getMmfId_str() != null && !"".equals(att_MdOutWarehouseMx.getMmfId_str().trim()))
			sql += " and mmf_id in ("+att_MdOutWarehouseMx.getMmfId_str().trim()+")";
		sql += " order by finsh_date desc";
		return this.getJdbcDao().findByPage(sql);
	}
	@Override
	public List<Map<Object, Object>> getMxListByOut(
			MdOutWarehouse att_MdOutWarehouse,
			MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "SELECT a.*,b.*,case when f.mmt_name is not null then"
				+" CONCAT(f.mmt_name,'/',e.mmt_name,'/',d.mmt_name) else c.label_info end AS 'type_name'"
				+" FROM md_out_warehouse a,md_out_warehouse_mx b ,md_materiel_info c "
				+" LEFT JOIN md_materiel_type d ON c.md_wms_mi_id=d.mmt_id"
				+" LEFT JOIN md_materiel_type e ON c.mat_type2=e.mmt_id"
				+" LEFT JOIN md_materiel_type f ON c.mat_type1=f.mmt_id"
			+" WHERE a.wow_id=b.wow_id AND b.wms_mi_id=c.wms_mi_id";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRbaId() != null)
			sql += " and a.rba_id="+att_MdOutWarehouse.getRbaId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRbbId() != null)
			sql += " and a.rbb_id="+att_MdOutWarehouse.getRbbId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRbsId() != null)
			sql += " and a.rbs_id="+att_MdOutWarehouse.getRbsId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getPurchaseType() != null && !"".equals(att_MdOutWarehouse.getPurchaseType().trim()))
			sql += " and a.purchase_type="+att_MdOutWarehouse.getPurchaseType().trim();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getCompanyType() != null && !"".equals(att_MdOutWarehouse.getCompanyType().trim()))
			sql += " and a.company_type="+att_MdOutWarehouse.getCompanyType().trim();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getWowCode() != null && !"".equals(att_MdOutWarehouse.getWowCode().trim()))
			sql += " and a.wow_code like '%"+att_MdOutWarehouse.getWowCode().trim()+"%'";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getRelatedBill1() != null && !"".equals(att_MdOutWarehouse.getRelatedBill1().trim()))
			sql += " and a.RELATED_BILL1 like '%"+att_MdOutWarehouse.getRelatedBill1().trim()+"%'";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getCustomerName() != null && !"".equals(att_MdOutWarehouse.getCustomerName().trim()))
			sql += " and a.customer_name like '%"+att_MdOutWarehouse.getCustomerName().trim()+"%'";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getUserName() != null && !"".equals(att_MdOutWarehouse.getUserName().trim()))
			sql += " and a.user_name like '%"+att_MdOutWarehouse.getUserName().trim()+"%'";
		if(att_MdOutWarehouseMx != null && att_MdOutWarehouseMx.getMmfId() != null)
			sql += " and b.mmf_id="+att_MdOutWarehouseMx.getMmfId();
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getFinshDate_start()!=null)
			sql += " and a.FINSH_DATE >='"+sdf.format(att_MdOutWarehouse.getFinshDate_start())+" 00:00:00'";
		if(att_MdOutWarehouse != null && att_MdOutWarehouse.getFinshDate_end()!=null)
			sql += " and a.FINSH_DATE <='"+sdf.format(att_MdOutWarehouse.getFinshDate_end())+" 23:59:59'";
		sql += " and a.state='1'";
		sql += " order by finsh_date";
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<MdOutWarehouseMx> getListByMdOutWarehouseMxByMooId(Integer mooId) throws HSKDBException {
		String hql = "from MdOutWarehouseMx where 1=1";
		if (mooId != null) {
			hql += " and wowId in (select wowId from MdOutWarehouse where relatedBill1 = (select mooCode from MdOutOrder where mooId = " + mooId + "))";
		}
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Map<String, Object> getMissingNumber(MdOutWarehouseMx mdEnterWarehouseMx) throws HSKDBException {
		String sql = "select (number1 - number2) as alNumber, (number6 - number7) as salNumber from md_out_order where moo_code = (select RELATED_BILL1 from md_out_warehouse where wow_id = " + mdEnterWarehouseMx.getWowId() + ")";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty())
			return null;
		return list.get(0);
	}
}