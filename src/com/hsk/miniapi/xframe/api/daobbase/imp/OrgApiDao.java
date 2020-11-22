package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.xframe.api.persistence.SysOrgGx;


@Component
public class OrgApiDao extends SupperDao implements IorgApiDao {

 
	public int saveOrgInfo(SysOrgGx sysOrgGx) throws HSKDBException {
		String types = sysOrgGx.getOrganizaType();
		if (types == null || "".equals(types.trim())) {
			throw new HSKDBException("未设置组织类型");
		}
		  String oldIdPath="1;";
		  String oldCodePath="root;";
		  String orgGxIdPath="1;";
		
		if (sysOrgGx.getMxxOrgGxId() == null) {
			if (sysOrgGx.getOrganizaType().indexOf("100") >= 0)
				// 平台供应商
				sysOrgGx.setMxxOrgGxId(2);
			oldIdPath="1;2;";
			oldCodePath="root;gys;";
			orgGxIdPath="1;2;";
			if (sysOrgGx.getOrganizaType().indexOf("200") >= 0)
				// 平台组织
				sysOrgGx.setMxxOrgGxId(3);
				oldIdPath="1;3;";
				oldCodePath="root;org;";
				orgGxIdPath="1;3;";
		}else {
			SysOrgGx root=new SysOrgGx();
			root.setOrgGxId(sysOrgGx.getMxxOrgGxId());
			List<SysOrgGx> list_obj=this.getHibernateDao().find(root);
			if(list_obj==null||list_obj.size()<=0){ 
				oldIdPath="1;";
				oldCodePath="root;";
				orgGxIdPath="1;";
				sysOrgGx.setMxxOrgGxId(1);
			}else {
				root=list_obj.get(0);
				oldIdPath=root.getOldIdPath();
				oldCodePath=root.getOldCodePath();
				orgGxIdPath=root.getOrgGxIdPath();
			}
		}
		int id= (Integer) this.getHibernateDao().save(sysOrgGx);
		sysOrgGx.setOldIdPath(oldIdPath+sysOrgGx.getOldId()+";");
		sysOrgGx.setOldCodePath(oldCodePath+sysOrgGx.getOldCode()+";");
		sysOrgGx.setOrgGxIdPath(orgGxIdPath+id+";");
		this.updateObject(sysOrgGx);
		return id;
	}

	 
	public String getOrgStr(SysOrgGx sysOrgGx) throws HSKDBException {
		String  ls_back=null;
		if(sysOrgGx.getOrgGxId()!=null){
			SysOrgGx sog=new SysOrgGx();
			 sog.setOrgGxId(sysOrgGx.getOrgGxId());
			Object objorg= this.getOne(sog);
			if(objorg!=null){
				sog=(SysOrgGx) objorg;
				String hql ="  from sysOrgGx a where  a.orgGxId!="+sysOrgGx.getOrgGxId()+" and a.oldIdPath like '%"+sog.getOrgGxIdPath()+"%'";
				String  orgType=sysOrgGx.getOrganizaType();
				if(orgType!=null&&!"".equals(orgType.trim())){
					    hql+= " and  a.organizaType in("+orgType+")";  
				}
			  List list_obj=	this.getHibernateDao().findColByHqlQuery(hql);
			  if(list_obj!=null&&list_obj.size()>0){
				  ls_back="";
				  for(Object did:list_obj){
					  SysOrgGx  row=(SysOrgGx)did;
					  ls_back+=row.getOrgGxId()+","; 
				  }
				  ls_back=ls_back.substring(0,ls_back.length()-1);
			  }
			} 
		}
		return ls_back;
	}


	 
	public String getOldIdStr(SysOrgGx sysOrgGx) throws HSKDBException {
		String  ls_back=null;
		if(sysOrgGx.getOrgGxId()!=null){
			SysOrgGx sog=new SysOrgGx();
			 sog.setOrgGxId(sysOrgGx.getOrgGxId());
			Object objorg= this.getOne(sog);
			if(objorg!=null){
				sog=(SysOrgGx) objorg;
				String hql =" from com.hsk.xframe.api.persistence.SysOrgGx a" 						 
						+" where  a.orgGxId!="+sysOrgGx.getOrgGxId()+" and  a.orgGxIdPath like '%"+sog.getOrgGxIdPath()+"%'";
				String  orgType=sysOrgGx.getOrganizaType();
				if(orgType!=null&&!"".equals(orgType.trim())){
					    hql+= " and  a.organizaType in("+orgType+")";  
				}				 
			  List list_obj= 	this.getHibernateDao().findColByHqlQuery(hql);
			  if(list_obj!=null&&list_obj.size()>0){
				  ls_back="";
				  for(Object did:list_obj){
					  SysOrgGx  row=(SysOrgGx)did;
					  ls_back+=row.getOldId()+","; 
				  }
				  ls_back=ls_back.substring(0,ls_back.length()-1);
			  }
			} 
		}
		return ls_back;
	}


	@Override
	public Map<String, String> getOldThreeMap(SysOrgGx sysOrgGx)throws HSKDBException {
		Map<String, String> reMap = new HashMap<String,String>();
		if(sysOrgGx.getOrgGxId()!=null){
			SysOrgGx sog=new SysOrgGx();
			sog.setOrgGxId(sysOrgGx.getOrgGxId());
			Object objorg= this.getOne(sog);
			if(objorg!=null){
				sog=(SysOrgGx) objorg;
				String oldPath = sog.getOldIdPath();
				oldPath =oldPath.substring(0, oldPath.length()-1);
				String[] oldArray = oldPath.split(";");
				if(sog.getOrganizaType().equals("20001")){
					reMap.put("one", oldArray[3]);
				}else if(sog.getOrganizaType().equals("20002")){
					SysOrgGx oneSog = new SysOrgGx();
					oneSog.setOrgGxId(sog.getMxxOrgGxId());
					oneSog = (SysOrgGx) this.getOne(oneSog);
					if(oneSog.getOrganizaType().equals("20001"))
						reMap.put("one", oneSog.getOldId()+"");
					reMap.put("tow", sog.getOldId()+"");
				}else if(sog.getOrganizaType().equals("20003")){
					SysOrgGx twoSog = new SysOrgGx();
					twoSog.setOrgGxId(sog.getMxxOrgGxId());
					twoSog = (SysOrgGx) this.getOne(twoSog);
					if(twoSog.getOrganizaType().equals("20002")){
						reMap.put("tow", twoSog.getOldId()+"");
						SysOrgGx oneSog = new SysOrgGx();
						oneSog.setOrgGxId(twoSog.getMxxOrgGxId());
						oneSog = (SysOrgGx) this.getOne(oneSog);
						if(oneSog.getOrganizaType().equals("20001"))
							reMap.put("one", oneSog.getOldId()+"");
					}
					reMap.put("three", sog.getOldId()+"");
				}
			} 
		}
		return reMap;
	}


	@Override
	public String getNameStr(SysOrgGx sysOrgGx) throws HSKDBException {
		String reStr = "";
		if(sysOrgGx.getOrgGxId()!=null){
			SysOrgGx sog=new SysOrgGx();
			sog.setOrgGxId(sysOrgGx.getOrgGxId());
			Object objorg= this.getOne(sog);
			if(objorg!=null){
				sog=(SysOrgGx) objorg;
				String orgGxIdPath = sog.getOrgGxIdPath();
				orgGxIdPath = orgGxIdPath.substring(1, orgGxIdPath.length()-1);
				orgGxIdPath = orgGxIdPath.replaceAll(";", ",");
				String hql = "from SysOrgGx where orgGxId in ("+orgGxIdPath+")";
				List<SysOrgGx> orgList = this.getHibernateTemplate().find(hql);
				if(orgList != null && orgList.size() > 0){
					for(SysOrgGx obj : orgList){
						if(obj.getOrganizaType().equals("20001") && obj.getOrgGxId() != 4)
							reStr +=obj.getNodeName();
						else if(obj.getOrganizaType().equals("20002") && obj.getOrgGxId() != 5)
							reStr +=obj.getNodeName();
						else if(obj.getOrganizaType().equals("20003") && obj.getOrgGxId() != 6)
							reStr +=obj.getNodeName();
						
					}
				}
			}
		}
		return reStr;
	}


	@Override
	public String getOldIdStrByParent(SysOrgGx sysOrgGx) throws HSKDBException {
		String  ls_back=null;
		if(sysOrgGx.getOrgGxId()!=null){
			SysOrgGx sog=new SysOrgGx();
			 sog.setOrgGxId(sysOrgGx.getOrgGxId());
			Object objorg= this.getOne(sog);
			if(objorg!=null){
				sog=(SysOrgGx) objorg;
				String hql =" from com.hsk.xframe.api.persistence.SysOrgGx a" 						 
						+" where  a.mxxOrgGxId="+sysOrgGx.getOrgGxId();
				String  orgType=sysOrgGx.getOrganizaType();
				if(orgType!=null&&!"".equals(orgType.trim())){
					    hql+= " and  a.organizaType in("+orgType+")";  
				}				 
			  List list_obj= 	this.getHibernateDao().findColByHqlQuery(hql);
			  if(list_obj!=null&&list_obj.size()>0){
				  ls_back="";
				  for(Object did:list_obj){
					  SysOrgGx  row=(SysOrgGx)did;
					  ls_back+=row.getOldId()+","; 
				  }
				  ls_back=ls_back.substring(0,ls_back.length()-1);
			  }
			} 
		}
		return ls_back;
	}

}
