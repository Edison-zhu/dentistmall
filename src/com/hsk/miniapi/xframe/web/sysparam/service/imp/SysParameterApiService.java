package com.hsk.miniapi.xframe.web.sysparam.service.imp;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysControlParamApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysParameterApiDao;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysControlParam;
import com.hsk.xframe.api.persistence.SysParameter;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.sysparam.service.ISysParameterApiService;

@Service
public class SysParameterApiService extends DSTApiService implements ISysParameterApiService {
	@Autowired
	private ISysParameterApiDao sysParameterDao;
	@Autowired
	private ISysControlParamApiDao sysControlParamDao;
	
	public SysRetrunMessage getOneSysParameter() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			List<SysParameter> list = sysParameterDao.getSysParameterList(null);
			srm.setObj(list);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("查询失败!");
		}
		return srm;
	}
	
	public PagerModel getSysParameterPager(SysParameter sysParameter)
			throws HSKException {
		PagerModel model = new PagerModel();
		try {
			model = this.getPagerModel(sysParameter);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public SysRetrunMessage saveOneSysParameter(SysParameter sysParameter,String controlIds,String paramNames,String paramTypes,String paramOrders,String paramNodes) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			if(sysParameter.getSparId() != null){
				this.updateObject(sysParameter);
				//删除参数信息
				SysControlParam sysControlParam = new SysControlParam();
				sysControlParam.setSuiId(sysParameter.getSparId());
				sysControlParam.setParamSource("1");
				sysControlParamDao.delSysControlParamList(sysControlParam);
			}
			else{
				this.newObject(sysParameter);
			}
			if(controlIds != null && !controlIds.trim().equals("")){
				String[] controlIdArray = controlIds.split(",");
				String[] paraNameArray=paramNames.split(",");
				String[] paramTypeArray=paramTypes.split(",");
				String[] paramOrderArray=paramOrders.split(",");
				String[] paramNodeArray=paramNodes.split(",");
				for(int i=0;i < controlIdArray.length;i++){
					SysControlParam controlParam = new SysControlParam();
					controlParam.setControlId(controlIdArray[i]);
					controlParam.setParamName(paraNameArray[i]);
					controlParam.setParamType(paramTypeArray[i]);
					controlParam.setParamOrder(Integer.parseInt(paramOrderArray[i]));
					controlParam.setParamNode(paramNodeArray[i]);
					controlParam.setSuiId(sysParameter.getSparId());
					controlParam.setParamSource("1");
					this.newObject(controlParam);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	public SysRetrunMessage saveSysParameter(SysParameter sysParameter) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			if(sysParameter.getSparId() != null)
				this.updateObject(sysParameter);
			else{
				this.newObject(sysParameter);
			}
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	public SysParameter findSysParameter(Integer sparId,Integer sysSparId) throws HSKException {
		SysParameter sysParameter = new SysParameter();
		try {
			if(sparId != null){
				sysParameter.setSparId(sparId);
				sysParameter = (SysParameter) this.getOne(sysParameter);
			}else{
				sysParameter.setParamCode(CreateCodeUtil.getSysParameterNo());
				sysParameter.setParamOrderNumber(sysParameterDao.getMaxOrderByParentId(sysSparId)+1);
				sysParameter.setSysSparId(sysSparId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysParameter;
	}

	public SysRetrunMessage delSysParameter(Integer sparId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			//检查是否可以被删除
			SysParameter checkSysParameter = new SysParameter();
			checkSysParameter.setSysSparId(sparId);
			List<SysParameter> checkSysParameterList = this.getList(checkSysParameter);
			if(checkSysParameterList != null && checkSysParameterList.size() > 0){
				srm.setCode(0L);
				srm.setMeg("该大类参数下有小类参数不允许删除！");
				return srm;
			}
			SysParameter sysParameter = new SysParameter(sparId);
			sysParameter = (SysParameter) this.getOne(sysParameter);
			this.deleteObjects(sysParameter);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	
	public List<SysParameter> getParameterListByParentCode(String parentCode)
			throws HSKException {
		List<SysParameter> list = new ArrayList<SysParameter>();
		try {
			SysParameter sysParameter = new SysParameter();
			sysParameter.setParamCode(parentCode);
			sysParameter = (SysParameter) this.getOne(sysParameter);
			SysParameter sysParameter2 = new SysParameter();
			sysParameter2.setSysSparId(sysParameter.getSparId());
			list = this.getList(sysParameter2);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return list;
	}

	public SysParameter findSysParameterByCode(String  code)
			throws HSKException {
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParamCode(code);
	   Object obj=	this.getOne(sysParameter);
		 if(obj!=null){
			 sysParameter=(SysParameter)obj;
			 return sysParameter;
		 }
	   return null;
	}
	
	public  SysRetrunMessage   getTreeNodeListByParentCode(String parentCode)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		List<TreeNode> list_back = new ArrayList<TreeNode>();
		if (parentCode == null) {
			srm.setCode(0l);
			srm.setMeg("parentCode为空!");
			return srm;
		}
		try {
			List<SysParameter> list = new ArrayList<SysParameter>();
			SysParameter sysParameter = new SysParameter();
			if ("system".equals(parentCode)) {
				sysParameter.setRelevantId(1);
				sysParameter.setIfLoad(1);
				list = this.getList(sysParameter);
			} else {
				sysParameter.setParamCode(parentCode);
				sysParameter = (SysParameter) this.getOne(sysParameter);
				SysParameter sysParameter2 = new SysParameter();
				sysParameter2.setSysSparId(sysParameter.getSparId());
				list = this.getList(sysParameter2);
			}
			for (SysParameter did : list) {
				TreeNode tn = new TreeNode();
				tn.setId(did.getParamValue());
				tn.setName(did.getParamName());
				tn.setText(did.getParamName());
				tn.setAttributes(did);
				tn.setType(did.getParamCode());
				tn.setDatatype("02");

				if ("system".equals(parentCode)) {
					SysRetrunMessage srm01= this
							.getTreeNodeListByParentCode(did.getParamCode());
					if("1".equals(  srm01.getCode().toString()) ) {
						List<TreeNode> list_children=(List<TreeNode>)srm01.getObj();
						if(list_children!=null&&list_children.size()>0){
							tn.setChildren(list_children);
							}
					}
					tn.setDatatype("01");
				}
				list_back.add(tn);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		srm.setObj(list_back);
		return srm;
	}

	 
	public SysRetrunMessage getNewCode(String prefix) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		srm.setObj(CreateCodeUtil.getNo(prefix));
		return srm;
	}

}
