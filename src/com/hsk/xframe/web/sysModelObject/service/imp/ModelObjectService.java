package com.hsk.xframe.web.sysModelObject.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dao.IsupperDao;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.service.imp.SupperService;
import com.hsk.xframe.api.persistence.SysModelObject;
import com.hsk.xframe.web.sysModelObject.service.IModelObjectService;

@Service
public class ModelObjectService extends SupperService implements
		IModelObjectService {
	@Autowired
	private IsupperDao supperDao;
	
	public SysRetrunMessage initRootModel(String sysCode, String proName)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		try {
			// 第一级
			SysModelObject root_smo = new SysModelObject();
			root_smo.setSmoName(proName);
			root_smo.setSmoComment(proName);
			root_smo.setSysCode(sysCode);
			root_smo.setSmoType("1");
			Integer id = supperDao.newObject(root_smo);
			// 第二级
			String[] array_str = new String[] { "界面定义", "选择区域定义", "数据表格定义",
					"通用查询定义", "表单定义" };
			String[] array_twoIdPath = new String[5];
			String[] array_twoNamePath = new String[5];
			Integer[] array_two = new Integer[5];
			int i = 0;
			for (String did : array_str) {
				SysModelObject two_smo = new SysModelObject();
				String SMO_NAME = did;
				two_smo.setSmoName(SMO_NAME);
				two_smo.setSmoComment(SMO_NAME + "文件夹");
				two_smo.setSysCode(sysCode);
				two_smo.setSmoType("2");
				two_smo.setSysSmoId(id);
				two_smo.setSmoNamePath(proName + "\\" + SMO_NAME);
				Integer two_id = supperDao.newObject(two_smo);
				two_smo.setSmoIdPath(id + "\\" + two_id);
				array_twoNamePath[i] = two_smo.getSmoNamePath();
				array_twoIdPath[i] = two_smo.getSmoIdPath();
				array_two[i] = two_id;
				i++;
				supperDao.updateObject(two_smo);
			}
			// 第三级
			array_str = new String[] { "单表界面模型", "主子表界面模型" };
			String parent_idPath = array_twoIdPath[0];
			String parent_namePath = array_twoNamePath[0];
			Integer parent_id = array_two[0];
			for (String did : array_str) {
				SysModelObject throw_smo = new SysModelObject();
				throw_smo.setSmoName(did);
				throw_smo.setSmoComment(did + "文件夹");
				throw_smo.setSysCode(sysCode);
				throw_smo.setSmoType("2");
				throw_smo.setSysSmoId(parent_id);
				throw_smo.setSmoNamePath(parent_namePath + "\\" + did);
				Integer this_id = supperDao.newObject(throw_smo);
				throw_smo.setSmoIdPath(parent_idPath + "\\" + this_id);
				supperDao.updateObject(throw_smo);
			}
		} catch (Exception e) {
//			e.printStackTrace();
//			srm.setCode(0l);
//			srm.setMeg(e.getMessage());
			throw new HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage removeRootModel(String sysCode) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		SysModelObject smo = new SysModelObject();
		smo.setSysCode(sysCode);
		try {
			List list = supperDao.getList(smo);
			if (list != null && list.size() > 0) {
				this.getHibernateDao().deleteAll(list);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}
		return srm;
	}

	@Override
	public SysRetrunMessage updateRootModel(String sysCode, String proName)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		SysModelObject smo = new SysModelObject();
		smo.setSysCode(sysCode);
		smo.setSmoName(proName);
		try {
			Object obj = this.getOne(smo);
			if (obj != null) {
				smo = (SysModelObject) obj;
				smo.setSmoName(proName);
				supperDao.updateObject(smo);
			}
		} catch (Exception e) {
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}

}
