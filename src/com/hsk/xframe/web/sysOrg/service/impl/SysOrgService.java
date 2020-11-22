package com.hsk.xframe.web.sysOrg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.daobbase.IMdCompanyGroupDao;
import com.hsk.dentistmall.api.daobbase.IMdDentalClinicDao;
import com.hsk.dentistmall.api.daobbase.IMdDentistHospitalDao;
import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysCompany;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.web.sysOrg.service.ISysOrgService;

@Service
public class SysOrgService extends DSTService implements ISysOrgService {
    @Autowired
    private IMdCompanyGroupDao companyGroupDao;
    @Autowired
    private IMdDentistHospitalDao dentistHospitalDao;
    @Autowired
    private IMdDentalClinicDao dentalClinicDao;
    @Autowired
	private ISysSalesmanDao salesmanDao;

    @Override
    public List<Map<String, Object>> getOrgTree(Integer id) throws HSKException {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        SysOrgGx sysOrgGx = new SysOrgGx();
        try {
            sysOrgGx.setMxxOrgGxId(id);
            List<SysOrgGx> list = this.getList(sysOrgGx);
            if (list != null && list.size() > 0) {
                for (SysOrgGx org : list) {
                    Map<String, Object> treeMap = new HashMap<String, Object>();
                    treeMap.put("id", org.getOrgGxId());
                    treeMap.put("name", org.getNodeName());
                    treeMap.put("pId", org.getMxxOrgGxId());
                    if (org.getOrganizaType().toString().equals("1")) {//是根节点并且有子节点
                        treeMap.put("isParent", true);
                    } else
                        treeMap.put("isParent", false);
                    treeList.add(treeMap);
                }
            }
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return treeList;
    }

    @Override
    public PagerModel getSysCompanyPager(SysCompany sysCompany) throws HSKException {
        PagerModel model = new PagerModel();
        try {
            model = this.getPagerModel(sysCompany);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public SysRetrunMessage saveSysCompany(SysCompany sysCompany)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            if (sysCompany.getWzId() != null)
                this.updateObject(sysCompany);
            else {
                sysCompany.setState("1");
                this.newObject(sysCompany);
            }
            srm.setMeg("操作成功!");
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
        }
        return srm;
    }

    @Override
    public SysCompany findSysCompany(Integer wzId, Integer orgGxId) throws HSKException {
        SysCompany sysCompany = new SysCompany();
        try {
            if (wzId != null) {
                sysCompany.setWzId(wzId);
                sysCompany = (SysCompany) this.getOne(sysCompany);
            } else {
                sysCompany.setOrgGxId(orgGxId);
                sysCompany.setEducation(CreateCodeUtil.getSysCompanyNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysCompany;
    }

    @Override
    public SysRetrunMessage updateSysCompanyState(Integer wzId, String state) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysCompany sysCompany = new SysCompany(wzId);
            sysCompany = (SysCompany) this.getOne(sysCompany);
            sysCompany.setState(state);
            this.updateObject(sysCompany);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    public SysRetrunMessage findSysOrgGx(Integer oldId, String organizaType)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOldId(oldId);
            sysOrgGx.setOrganizaType(organizaType);
            sysOrgGx = (SysOrgGx) this.getOne(sysOrgGx);
            srm.setObj(sysOrgGx);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
        }
        return srm;
    }


    public List<TreeNode> getUserOrgTree(Integer id) throws HSKException {
        List<TreeNode> list_bakc = new ArrayList<TreeNode>();
        SysUserInfo sui = GetOneSessionAccount();
        if (id == null) {
            if (sui == null || sui.getOrgGxId() == null
                    || sui.getOrgGxId() == 1) {
                id = 1;
            } else {
                id = sui.getOrgGxId();
            }
            TreeNode root = new TreeNode();

            SysOrgGx sysOrgGxroot = new SysOrgGx();
            sysOrgGxroot.setOrgGxId(id);
            Object sobj = this.getOne(sysOrgGxroot);
            root.setId(id.toString());
            if (sobj != null) {
                sysOrgGxroot = (SysOrgGx) sobj;
                root.setName(sysOrgGxroot.getNodeName());
                root.setOpen(true);
                root.setIsParent("true");
            } else {
                root.setName("管理平台");
                root.setOpen(true);
                root.setIsParent("true");
            }
            root.setAttributes(sysOrgGxroot);
            list_bakc.add(root);
        }
        SysOrgGx sysOrgGx = new SysOrgGx();
        try {
			String salesManId = "";
			if (sui.getUserType() != null && sui.getUserType() == 6) {
				SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesAccount(sui.getAccount());
				sysSalesManEntity = salesmanDao.getObject(sysSalesManEntity);
				salesManId = sysSalesManEntity.getSalesmanId().toString();
			}
            sysOrgGx.setMxxOrgGxId(id);
            List<SysOrgGx> list = this.getList(sysOrgGx);
            if (list != null && list.size() > 0) {
                Integer oldId;
                Object obj = null;
				MdCompanyGroup mdCompanyGroup;
				MdDentistHospital mdDentistHospital;
				MdDentalClinic mdDentalClinic;
                for (SysOrgGx org : list) {
                    if (sui.getUserType() != null && sui.getUserType() == 6 && org.getOrgGxId() > 6 && org.getMxxOrgGxId() <= 6) {
                        if (org.getOrgGxId() == 2 || org.getMxxOrgGxId() == 2)
                            continue;
                        oldId = org.getOldId();
                        switch (org.getMxxOrgGxId()) {
                            case 4: //集团
								obj = companyGroupDao.findMdCompanyGroupById(oldId);
								if (obj == null){
									continue;
								}
								mdCompanyGroup = (MdCompanyGroup) obj;
								if (mdCompanyGroup.getSalesmanIds() == null || mdCompanyGroup.getSalesmanIds().indexOf(salesManId) < 0) {
									continue;
								}
                                break;
                            case 5: //医院
								obj = dentistHospitalDao.findMdDentistHospitalById(oldId);
								if (obj == null){
									continue;
								}
								mdDentistHospital = (MdDentistHospital) obj;
								if (mdDentistHospital.getSalesmanIds() == null || mdDentistHospital.getSalesmanIds().indexOf(salesManId) < 0) {
									continue;
								}
                                break;
                            case 6: //门诊
								obj = dentalClinicDao.findMdDentalClinicById(oldId);
								if (obj == null){
									continue;
								}
								mdDentalClinic = (MdDentalClinic) obj;
								if (mdDentalClinic.getSalesmanIds() == null || mdDentalClinic.getSalesmanIds().indexOf(salesManId) < 0) {
									continue;
								}
                                break;
                        }
                    }
                    TreeNode row = new TreeNode();
                    row.setId(org.getOrgGxId().toString());
                    row.setName(org.getNodeName());
                    row.setpId(org.getMxxOrgGxId().toString());
                    row.setAttributes(org);
                    Integer cot = org.getMxxOrgcount();
                    if (cot != null && cot > 0) {
                        row.setIsParent("true");
                    } else {
                        row.setIsParent("false");
                    }
                    list_bakc.add(row);
                }
            }
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return list_bakc;
    }


}
