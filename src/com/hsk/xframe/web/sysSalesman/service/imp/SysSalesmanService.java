package com.hsk.xframe.web.sysSalesman.service.imp;

import com.hsk.dentistmall.api.daobbase.IMdCompanyGroupDao;
import com.hsk.dentistmall.api.daobbase.IMdDentalClinicDao;
import com.hsk.dentistmall.api.daobbase.IMdDentistHospitalDao;
import com.hsk.dentistmall.api.daobbase.IMdOrderInfoDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysRoleInfoDao;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.daobbase.ISysUserInfoDao;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.daobbase.imp.SysSalesmanDao;
import com.hsk.xframe.api.persistence.*;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CommonUtil;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.web.sysSalesman.service.ISysSalesmanService;
import com.hsk.xframe.web.sysUser.service.ISysUserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.sun.tools.xjc.outline.PackageOutline;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: yangfeng
 * time: 2020/1/3 15:09
 */
@Service
public class SysSalesmanService extends DSTService implements ISysSalesmanService {
    @Autowired
    ISysSalesmanDao sysSalesmanDao;
    @Autowired
    protected IorgDao orgDao;
    @Autowired
    private ISysUserInfoDao sysUserInfoDao;
    @Autowired
    private ISysRoleInfoDao sysRoleInfoDao;
    @Autowired
    protected IMdCompanyGroupDao mdCompanyGroupDao;
    @Autowired
    protected IMdDentistHospitalDao mdDentistHospitalDao;
    @Autowired
    protected IMdDentalClinicDao mdDentalClinicDao;
    @Autowired
    protected IMdOrderInfoDao mdOrderInfoDao;

    @Override
    public SysRetrunMessage saveSalesman(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            //查看当前组织是否存在集团、医院
//            SysOrgGx sysOrgGx = new SysOrgGx();
//            sysOrgGx.setOrgGxId(account.getOrgGxId());
//            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//                sysSalesManEntity.setRbaId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                sysSalesManEntity.setRbsId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                if (orgMap.containsKey("tow")) {//如果存在上级医院
//                    sysSalesManEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
//                }
//                sysSalesManEntity.setRbbId(account.getOldId());
//            }
            String salesAccount = sysSalesManEntity.getSalesAccount();
            SysSalesManEntity salesManEntity = new SysSalesManEntity();
            salesManEntity.setSalesAccount(salesAccount);
            salesManEntity = sysSalesmanDao.getSalesManInfo(salesManEntity);
            if (salesManEntity != null) {
                sm.setCode(2l);
                sm.setMeg("账号重复，请重新输入！");
                return sm;
            }
            String city = sysSalesManEntity.getCity();
            String province = sysSalesManEntity.getProvince();
            String area = sysSalesManEntity.getArea();
            sysSalesManEntity.setSalesArea(province + "/" + city + "/" + area);
            sysSalesManEntity.setCreateDate(new Date());
            sysSalesManEntity.setCreateRen(account.getUserName());
            sysSalesManEntity.setSalesCompany(account.getOrgName());
            sysSalesManEntity.setOrgGxId(account.getOrgGxId());
            sysSalesManEntity.setSalesPassword("123456");
            if (account.getUserType() != null && account.getUserType() == 6) {
                sysSalesManEntity.setVerifyState(1);
                sysSalesManEntity.setState(2);
                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
                sysSalesManEntity1.setSalesAccount(account.getAccount());
                sysSalesManEntity1 = sysSalesmanDao.getSalesManInfo(sysSalesManEntity1);
                sysSalesManEntity.setLeaderId(sysSalesManEntity1.getSalesmanId());
            }
            sysSalesmanDao.saveSalesman(sysSalesManEntity);

            String code = CreateCodeUtil.getNo("USE");
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(sysSalesManEntity.getSalesAccount());
            sysUserInfo.setUserName(sysSalesManEntity.getSalesName());
            sysUserInfo.setPassword(sysSalesManEntity.getSalesPassword());
            sysUserInfo.setPhoneNumber(sysSalesManEntity.getSalesPhone());
            sysUserInfo.setUserType(6);
            sysUserInfo.setUserCode(code);
            sysUserInfo.setUserRole(sysSalesManEntity.getSalesType().toString());
//            SysUserInfo account = this.GetOneSessionAccount();

            if (sysUserInfo.getOrgGxId() == null && sysUserInfo.getOrganizaType() != null && sysUserInfo.getOldId() != null) {
                SysOrgGx sog = new SysOrgGx();
                sog.setOrganizaType(sysUserInfo.getOrganizaType());
                sog.setOldId(sysUserInfo.getOldId());
                Object obj = this.getOne(sog);
                if (obj != null) {
                    sog = (SysOrgGx) obj;
                    sysUserInfo.setOrgGxId(sog.getOrgGxId());
                }
            }

            if (account != null && account.getSuiId() != null) {
                if (sysUserInfo.getSuiId() != null)
                    this.updateObject(sysUserInfo);
                else {

                    SysUserInfo pui = new SysUserInfo();
                    pui.setAccount(sysUserInfo.getAccount());
                    SysUserInfo obj = this.sysUserInfoDao
                            .getSysUserInfoByName(pui);
                    if (obj != null) {
                        sm.setCode(0l);
                        sm.setMeg(sysUserInfo.getAccount() + "以存在，请重新输入！！");
                        return sm;
                    }
                    sysUserInfo.setOrgGxId(1);
                    sysUserInfo.setState(sysSalesManEntity.getState());
                    sysUserInfo.setPassword("123456");
                    if (account.getUserType() != null && account.getUserType() == 6) {
                        sysUserInfo.setState(2);
                    }
                    this.newObject(sysUserInfo);
                }
                String roles = CommonUtil.YWY_ROLE.toString();
                if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 2) {
                    roles = CommonUtil.DLS_ROLE.toString();
                } else if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
                    roles = CommonUtil.SUPPLIER_DLS_ROLE.toString();
                }
                this.saveUserRole(sysUserInfo.getSuiId(), roles);

            }
        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(2l);
            throw new HSKException(e);
        }
        return sm;
    }

    private SysRetrunMessage saveUserRole(Integer suiId, String sroleIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            //查看该用户原来的角色
            SysGxRoleUser sysGxRoleUser = new SysGxRoleUser();
            sysGxRoleUser.setSuiId(suiId);
            List<SysGxRoleUser> oldRoleList = this.getList(sysGxRoleUser);
            String newRoleIds = "";
            String delRoleIds = "";
            List<Integer> newRoleIdArray = new ArrayList<Integer>();
            if (sroleIds != null && !sroleIds.trim().equals("")) {
                String[] sroleIdArray = sroleIds.split(",");
                //查看新增的岗位
                for (String sroleIdStr : sroleIdArray) {
                    Integer sroleId = Integer.parseInt(sroleIdStr);
                    boolean isFind = false;
                    if (oldRoleList != null && oldRoleList.size() > 0) {
                        for (SysGxRoleUser oldRole : oldRoleList) {
                            if (sroleId.equals(oldRole.getSroleId())) {
                                isFind = true;
                                break;
                            }
                        }
                    }
                    if (!isFind) {
                        newRoleIds += sroleId + ",";
                        newRoleIdArray.add(sroleId);
                    }
                }
                //查看删除岗位
                if (oldRoleList != null && oldRoleList.size() > 0) {
                    for (SysGxRoleUser oldRole : oldRoleList) {
                        boolean isFind = false;
                        for (String sroleIdStr : sroleIdArray) {
                            Integer sroleId = Integer.parseInt(sroleIdStr);
                            if (sroleId.equals(oldRole.getSroleId())) {
                                isFind = true;
                                break;
                            }
                        }
                        if (!isFind)
                            delRoleIds += oldRole.getSroleId() + ",";
                    }
                }
            } else {//删除所有岗位
                if (oldRoleList != null && oldRoleList.size() > 0) {
                    for (SysGxRoleUser oldRole : oldRoleList)
                        delRoleIds += oldRole.getSroleId() + ",";
                }
            }
            //新增用户岗位信息
            if (newRoleIds != null && !newRoleIds.trim().equals("")) {
                //保存用户改为信息
                for (Integer roleId : newRoleIdArray) {
                    SysGxRoleUser roleUser = new SysGxRoleUser();
                    roleUser.setSroleId(roleId);
                    roleUser.setSuiId(suiId);
                    this.newObject(roleUser);
                }
                newRoleIds = newRoleIds.substring(0, newRoleIds.length() - 1);
                //保存用户菜单信息
                List<SysGxRoleMenu> sysGxRoleMenuList = sysRoleInfoDao.getSysGxRoleMenuBySroleIds(newRoleIds);
                if (sysGxRoleMenuList != null && sysGxRoleMenuList.size() > 0) {
                    for (SysGxRoleMenu sysGxRoleMenu : sysGxRoleMenuList) {
                        SysGxUserMenu sysGxUserMenu = new SysGxUserMenu();
                        sysGxUserMenu.setSuiId(suiId);
                        sysGxUserMenu.setSroleId(sysGxRoleMenu.getSroleId());
                        sysGxUserMenu.setSmenuId(sysGxRoleMenu.getSmenuId());
                        sysGxUserMenu.setType(1);
                        this.newObject(sysGxUserMenu);
                    }
                }
                //保存用户岗位信息
                List<SysGxRoleControl> sysGxRoleControlList = sysRoleInfoDao.getSysGxRoleControlBySroleIds(newRoleIds);
                if (sysGxRoleControlList != null && sysGxRoleControlList.size() > 0) {
                    for (SysGxRoleControl sysGxRoleControl : sysGxRoleControlList) {
                        SysGxControlUser sysGxControlUser = new SysGxControlUser();
                        sysGxControlUser.setSuiId(suiId);
                        sysGxControlUser.setSroleId(sysGxRoleControl.getSroleId());
                        sysGxControlUser.setSconId(sysGxRoleControl.getSconId());
                        sysGxControlUser.setIfOperate(sysGxRoleControl.getIfOperate());
                        sysGxControlUser.setPropertiesSet(sysGxRoleControl.getPropertiesSet());
                        sysGxControlUser.setType(1);
                        this.newObject(sysGxControlUser);
                    }
                }
                //保存用户物料信息
                //保存用户库区信息
            }
            //删除原有的岗位信息
            if (delRoleIds != null && !delRoleIds.trim().equals("")) {
                delRoleIds = delRoleIds.substring(0, delRoleIds.length() - 1);
                sysUserInfoDao.delUserRoleByUsertAndRoles(suiId, delRoleIds);
                sysUserInfoDao.delUserMenus(suiId, null, null, delRoleIds);
                sysUserInfoDao.delUserControls(suiId, null, null, delRoleIds);
                //删除用户物料信息
                //删除用户库区信息
            }
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
        }
        return srm;
    }

    @Override
    public PagerModel getPagerModelObject(SysSalesManEntity sysSalesManEntity) throws HSKException {
        PagerModel pm = new PagerModel();
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            //查看当前组织是否存在集团、医院
//            SysOrgGx sysOrgGx = new SysOrgGx();
//            sysOrgGx.setOrgGxId(account.getOrgGxId());
//            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//                sysSalesManEntity.setRbaId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                sysSalesManEntity.setRbsId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                if (orgMap.containsKey("tow")) {//如果存在上级医院
//                    sysSalesManEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
//                }
//                sysSalesManEntity.setRbbId(account.getOldId());
//            }
            String salesProvince = "";
            String salesCity = "";
            String salesArea = "";
            if (sysSalesManEntity.getProvince() != null && !sysSalesManEntity.getProvince().equals("")) {
                salesProvince = sysSalesManEntity.getProvince();
            }
            if (sysSalesManEntity.getCity() != null && !sysSalesManEntity.getCity().equals("")) {
                salesCity = sysSalesManEntity.getCity();
            }
            if (sysSalesManEntity.getArea() != null && !sysSalesManEntity.getArea().equals("")) {
                salesArea = sysSalesManEntity.getArea();
            }
            String area = salesProvince + "/" + salesCity + "/" + salesArea + "";
            if (!area.equals("//")) {
                sysSalesManEntity.setSalesArea(area);
            }
            if (account.getUserType() != null && account.getUserType() == 6) {
                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
                sysSalesManEntity1.setSalesAccount(account.getAccount());
                sysSalesManEntity1 = sysSalesmanDao.getSalesManInfo(sysSalesManEntity1);
                sysSalesManEntity.setLeaderId(sysSalesManEntity1.getSalesmanId());
            }
            pm = sysSalesmanDao.getPagerModelObject(sysSalesManEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPageModelInfo(SysSalesManEntity sysSalesManEntity) throws HSKException {
        PagerModel pm = new PagerModel();
        try {
//            SysUserInfo account = this.GetOneSessionAccount();
//            if (account.getUserType() != null && account.getUserType() == 6){
//                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
//                sysSalesManEntity1.setSalesAccount(account.getAccount());
//                sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity1);
//                sysSalesManEntity.setLeaderId(sysSalesManEntity1.getSalesmanId());
//            }
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            if (sysSalesManEntity.getSalesmanId() == -1 && sysUserInfo.getUserType() != null && sysUserInfo.getUserType() == 6) {
                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
                sysSalesManEntity1.setSalesAccount(sysUserInfo.getAccount());
                sysSalesManEntity1 = sysSalesmanDao.getSalesManInfo(sysSalesManEntity1);
                sysSalesManEntity.setSalesmanId(sysSalesManEntity1.getSalesmanId());
                sysSalesManEntity.setSalesType(sysSalesManEntity1.getSalesType());
            }
            pm = sysSalesmanDao.getPageModelInfo(sysSalesManEntity);
            if (pm == null) {
                pm = new PagerModel();
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                List<Map<String, Object>> list = pm.getItems();
                for (Map<String, Object> map : list) {
                    Object date = map.get("create_date");
                    if (date != null) {
                        map.put("create_date", format.format(date));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysSalesManEntity getObject(Integer salesmanId) throws HSKException {
        SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            //查看当前组织是否存在集团、医院
//            SysOrgGx sysOrgGx = new SysOrgGx();
//            sysOrgGx.setOrgGxId(account.getOrgGxId());
//            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//                sysSalesManEntity.setRbaId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                sysSalesManEntity.setRbsId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                if (orgMap.containsKey("tow")) {//如果存在上级医院
//                    sysSalesManEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
//                }
//                sysSalesManEntity.setRbbId(account.getOldId());
//            }
            sysSalesManEntity.setSalesmanId(salesmanId);
            sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
            String allArea = sysSalesManEntity.getSalesArea();
            String[] areaArray = allArea.split("/");
            if (areaArray.length >= 1) {
                sysSalesManEntity.setProvince(areaArray[0]);
            }
            if (areaArray.length >= 2) {
                sysSalesManEntity.setCity(areaArray[1]);
            }
            if (areaArray.length >= 3) {
                sysSalesManEntity.setArea(areaArray[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysSalesManEntity;
    }

    @Override
    public PagerModel getSalesManViewPagerModel(SysSalesManEntity sysSalesManEntity) throws HSKException {
        PagerModel pm = new PagerModel();
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            //查看当前组织是否存在集团、医院
//            SysOrgGx sysOrgGx = new SysOrgGx();
//            sysOrgGx.setOrgGxId(account.getOrgGxId());
//            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//                sysSalesManEntity.setRbaId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                sysSalesManEntity.setRbsId(account.getOldId());
//            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
//                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    sysSalesManEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//                }
//                if (orgMap.containsKey("tow")) {//如果存在上级医院
//                    sysSalesManEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
//                }
//                sysSalesManEntity.setRbbId(account.getOldId());
//            }
            String salesProvince = "";
            String salesCity = "";
            String salesArea = "";
            if (sysSalesManEntity.getProvince() != null && !sysSalesManEntity.getProvince().equals("")) {
                salesProvince = sysSalesManEntity.getProvince();
            }
            if (sysSalesManEntity.getCity() != null && !sysSalesManEntity.getCity().equals("")) {
                salesCity = sysSalesManEntity.getCity();
            }
            if (sysSalesManEntity.getArea() != null && !sysSalesManEntity.getArea().equals("")) {
                salesArea = sysSalesManEntity.getArea();
            }
            String area = salesProvince + "/" + salesCity + "/" + salesArea + "";
            if (!area.equals("//")) {
                sysSalesManEntity.setSalesArea(area);
            }
//            if (account.getUserType() != null && account.getUserType() == 6){
//                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
//                sysSalesManEntity1.setSalesAccount(account.getAccount());
//                sysSalesManEntity1 = sysSalesmanDao.getSalesManInfo(sysSalesManEntity1);
//                sysSalesManEntity.setLeaderId(sysSalesManEntity1.getSalesmanId());
//            }
            pm = sysSalesmanDao.getPagerModelObject(sysSalesManEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage updateSysSalesmanBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        try {
            if (sysSalesManEntity.getSalesmanId() == null) {
//                String city = sysSalesManEntity.getCity();
//                String province = sysSalesManEntity.getProvince();
//                String area = sysSalesManEntity.getArea();
//                sysSalesManEntity.setSalesArea(province + "/" + city + "/" + area);
//                sysSalesManEntity.setCreateDate(new Date());
//                sysSalesManEntity.setCreateRen(account.getUserName());
//                sysSalesManEntity.setSalesCompany(account.getOrgName());
//                sysSalesManEntity.setOrgGxId(account.getOrgGxId());
//                sysSalesmanDao.saveSalesman(sysSalesManEntity);
                sm.setCode(3l);
                sm.setMeg("账号存在问题");
                return sm;
            }

            SysSalesManEntity salesMan = new SysSalesManEntity();
            salesMan.setSalesAccount(sysSalesManEntity.getSalesAccount());
            salesMan = sysSalesmanDao.getSalesManInfo((salesMan));

            String accoutName = sysSalesManEntity.getSalesAccount();
            if (salesMan != null && !salesMan.getSalesmanId().equals(sysSalesManEntity.getSalesmanId())) {
                SysSalesManEntity salesManEntity = new SysSalesManEntity();
                salesManEntity.setSalesAccount(accoutName);
                salesManEntity = sysSalesmanDao.getSalesManInfo(salesManEntity);
                if (salesManEntity != null) {
                    sm.setCode(2l);
                    sm.setMeg("账号重复，请重新输入！");
                    return sm;
                }
            }

            salesMan = new SysSalesManEntity();
            salesMan.setSalesmanId(sysSalesManEntity.getSalesmanId());
            salesMan = sysSalesmanDao.getObject(salesMan);
            String oldAccountName = salesMan.getSalesAccount();

            salesMan.setEditDate(new Date());
            salesMan.setEditRen(account.getUserName());
            String city = sysSalesManEntity.getCity();
            String province = sysSalesManEntity.getProvince();
            String area = sysSalesManEntity.getArea();
            sysSalesManEntity.setSalesArea(province + "/" + city + "/" + area);
            salesMan.setSalesAccount(sysSalesManEntity.getSalesAccount());
            salesMan.setSalesName(sysSalesManEntity.getSalesName());
            salesMan.setSalesWechat(sysSalesManEntity.getSalesWechat());
            salesMan.setState(sysSalesManEntity.getState());
            salesMan.setSalesPhone(sysSalesManEntity.getSalesPhone());
            salesMan.setSalesAddress(sysSalesManEntity.getSalesAddress());
            salesMan.setDescribes(sysSalesManEntity.getDescribes());
            salesMan.setAgentCompany(sysSalesManEntity.getAgentCompany());
            salesMan.setLeaderId(sysSalesManEntity.getLeaderId());
            salesMan.setLeaderName(sysSalesManEntity.getLeaderName());
            salesMan.setSalesType(sysSalesManEntity.getSalesType());
            sysSalesmanDao.updateSysSalesmanBySalesId(salesMan);


            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(oldAccountName);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            sysUserInfo.setAccount(salesMan.getSalesAccount());
            sysUserInfo.setPassword(salesMan.getSalesPassword());
            sysUserInfo.setUserName(salesMan.getSalesName());
            sysUserInfo.setUserRole(sysSalesManEntity.getSalesType().toString());
            sysUserInfo.setPhoneNumber(sysSalesManEntity.getSalesPhone());
            if (account.getUserType() != null && account.getUserType() == 6 && (salesMan.getVerifyState() != null && salesMan.getVerifyState() != 3)) {
                sysUserInfo.setState(2);
            } else {
                sysUserInfo.setState(salesMan.getState());
            }
            this.updateObject(sysUserInfo);

            String roles = "";
            if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 2) {
                roles = CommonUtil.DLS_ROLE.toString();
            } else if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 1) {
                roles = CommonUtil.YWY_ROLE.toString();
            } else if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
                roles = CommonUtil.SUPPLIER_DLS_ROLE.toString();
            }
            this.saveUserRole(sysUserInfo.getSuiId(), roles);

            String saleName = salesMan.getSalesName();
            MdCompanyGroup mdCompanyGroup = new MdCompanyGroup();
            mdCompanyGroup.setSalesmanIds(salesMan.getSalesmanId().toString());
            List<MdCompanyGroup> listCom = mdCompanyGroupDao.getListByMdCompanyGroup(mdCompanyGroup);
            for (MdCompanyGroup item : listCom) {
                if (!item.getSalesName().equals(saleName)) {
                    item.setSalesName(saleName);
                    mdCompanyGroupDao.updateMdCompanyGroupById(item.getRbaId(), item);
                }
            }

            MdDentistHospital mdDentistHospital = new MdDentistHospital();
            mdDentistHospital.setSalesmanIds(salesMan.getSalesmanId().toString());
            List<MdDentistHospital> listDen = mdDentistHospitalDao.getListByMdDentistHospital(mdDentistHospital);
            for (MdDentistHospital item : listDen) {
                if (!item.getSalesName().equals(saleName)) {
                    item.setSalesName(saleName);
                    mdDentistHospitalDao.updateMdDentistHospitalById(item.getRbsId(), item);
                }
            }

            MdDentalClinic mdDentalClinic = new MdDentalClinic();
            mdDentalClinic.setSalesmanIds(salesMan.getSalesmanId().toString());
            List<MdDentalClinic> listCli = mdDentalClinicDao.getListByMdDentalClinic(mdDentalClinic);
            for (MdDentalClinic item : listCli) {
                if (!item.getSalesName().equals(saleName)) {
                    item.setSalesName(saleName);
                    mdDentalClinicDao.updateMdDentalClinicById(item.getRbbId(), item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(2l);
            throw new HSKException(e);
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateSysSalesmanStateBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        try {
            if (sysSalesManEntity.getSalesmanId() == null) {
                sm.setCode(2l);
                return sm;
            }

            SysSalesManEntity salesMan = new SysSalesManEntity();
            salesMan.setSalesmanId(sysSalesManEntity.getSalesmanId());
            salesMan = sysSalesmanDao.getObject(salesMan);
            String oldAccountName = salesMan.getSalesAccount();

            salesMan.setEditDate(new Date());
            salesMan.setEditRen(account.getUserName());
            salesMan.setState(sysSalesManEntity.getState());
            sysSalesmanDao.updateSysSalesmanBySalesId(salesMan);

            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(oldAccountName);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (account.getUserType() != null && account.getUserType() == 6 && (salesMan.getVerifyState() == null || salesMan.getVerifyState() != 3)) {
                sysUserInfo.setState(2);
            } else {
                sysUserInfo.setState(salesMan.getState());
            }
            this.updateObject(sysUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(2l);
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteObject(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            sysSalesmanDao.deleteObject(sysSalesManEntity);
        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(2l);
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteAllObject(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            sysSalesmanDao.deleteAllObject(sysSalesManEntity);
        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(2l);
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
            sysSalesManEntity.setSalesmanId(id);
            sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
            if (sysSalesManEntity != null) {
                sysSalesManEntity.setVerifyState(verifyState);
                Integer state = 2;
                if (verifyState == 3) {
                    state = 1;
                }
                sysSalesManEntity.setState(state);
                if (verifyRemark != null && !verifyRemark.equals("")) {
                    sysSalesManEntity.setVerifyRemark(verifyRemark);
                }
                sysSalesmanDao.updateSysSalesmanBySalesId(sysSalesManEntity);

                SysUserInfo sysUserInfo = new SysUserInfo();
                sysUserInfo.setAccount(sysSalesManEntity.getSalesAccount());
                sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
                if (sysUserInfo != null) {
                    sysUserInfo.setState(state);
                    this.updateObject(sysUserInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(2l);
//			throw new HSKException(e);
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getPageModelInfoCount(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            if (sysSalesManEntity.getSalesmanId() == null) {
                SysUserInfo sysUserInfo = this.GetOneSessionAccount();
                sysSalesManEntity.setSalesAccount(sysUserInfo.getAccount());
                sysSalesManEntity = sysSalesmanDao.getSalesManInfo(sysSalesManEntity);
                if (sysSalesManEntity == null || sysUserInfo.getUserType() != 6) {
                    sm.setCode(2l);
                    return sm;
                }
            }

            Integer countLeaded = sysSalesmanDao.getPageModelInfoLeadedCount(sysSalesManEntity, 1);
            Integer countCompany = sysSalesmanDao.getPageModelInfoCompanyCount(sysSalesManEntity, 2);
            if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
                Integer supplierCount = sysSalesmanDao.getPagerModelInfoSupplierCount(sysSalesManEntity, 3);
                countLeaded += supplierCount;
            }
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("agentCount", countLeaded);
            map.put("otherCount", countCompany);
            sm.setObj(map);
        } catch (Exception e) {
            sm.setCode(2l);
            e.printStackTrace();
        }
        return sm;
    }

    //创建表格
    public SysRetrunMessage exportSalesMan(SysSalesManEntity sysSalesManEntity, String salesmanIds, String states1) throws HSKException {
        SysUserInfo account = this.GetOneSessionAccount();
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate = new Date();
        SysUserInfo SysUserInfo = this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName", SysUserInfo.getUserName());
        Date date = new Date();
        dataMap.put("newDate", sdf.format(date));
        dataMap.put("nodeName", SysUserInfo.getOrgGxId_Str());
        try {
            Integer SalesmanId1 = null;
            if (states1 != null && states1.equals("1") && account.getUserType() != null && account.getUserType() == 6) {
                sysSalesManEntity = new SysSalesManEntity();
                sysSalesManEntity.setSalesAccount(account.getAccount());
                SysSalesManEntity sysSalesManEntity2 = sysSalesmanDao.getSalesManInfo(sysSalesManEntity);
                SalesmanId1 = sysSalesManEntity2.getSalesmanId();
                sysSalesManEntity.setSalesmanId(SalesmanId1);
            }

            //判断显示多少条数
            if (salesmanIds != null && !"".equals(salesmanIds.trim())) {
                dataMap.put("huiZong", "");
                dataMap.put("Leaded", "");
                dataMap.put("Company", "");
            } else if (sysSalesManEntity.getSalesmanId() != null && sysSalesManEntity.getSalesmanId() != 0) {
                Integer Leaded = sysSalesmanDao.getPageModelInfoLeadedCount(sysSalesManEntity, 1);
                Integer Company = sysSalesmanDao.getPageModelInfoCompanyCount(sysSalesManEntity, 2);

                dataMap.put("huiZong", "汇总");
                dataMap.put("Leaded", Leaded + "家");
                dataMap.put("Company", Company + "家");
            } else {
                dataMap.put("huiZong", "");
                dataMap.put("Leaded", "");
                dataMap.put("Company", "");
            }
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();

            List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
            List<Map<String, String>> reList2 = new ArrayList<Map<String, String>>();

            if (salesmanIds != null && !"".equals(salesmanIds.trim())) {

                int index = 0;
                sysSalesManEntity.setSalesmanId(0);
                SysSalesManEntity sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
                sysSalesManEntity1.setSalesmanId(0);
                String salesName = sysSalesManEntity1.getSalesName();
                list = sysSalesmanDao.exportSalesMan(sysSalesManEntity);
                String st1 = "";
                if (list != null && list.size() > 0) {
//                        int index2=0;
                    for (Map<String, Object> map : list) {
                        index++;
                        Map<String, String> reMap = new HashMap<String, String>();
                        reMap.put("index", index + "");
                        if (!salesName.equals(st1)) {
                            reMap.put("salesName", salesName != "null" ? salesName : "");
                        } else {
                            reMap.put("salesName", "");
                        }
                        st1 = salesName;
                        Object agentCompany1 = map.get("agent_company");
                        String agentCompany = String.valueOf(agentCompany1);
                        reMap.put("agentCompany", agentCompany != "null" ? agentCompany : "");
                        Object rbaName1 = map.get("rba_name");
                        String rbaName = String.valueOf(rbaName1);
                        reMap.put("rbaName", rbaName != "null" ? rbaName : "");
                        Object createDate1 = map.get("create_date");
                        String createDate = String.valueOf(createDate1);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date2 = sdf2.parse(createDate);
                        reMap.put("createDate", sdf.format(date2));
                        Object state1 = map.get("state");
                        String state = String.valueOf(state1);
                        Integer intState = Integer.valueOf(state);
                        if (intState == 1) {
                            reMap.put("state", "正常");
                        } else if (intState == 2) {
                            reMap.put("state", "异常");
                        }
                        reList.add(reMap);
                    }

                } else {

                }
            } else {
                list = sysSalesmanDao.exportSalesMan(sysSalesManEntity);
                SysSalesManEntity sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
                sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
                String salesName = sysSalesManEntity1.getSalesName();

                int index = 0;
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        index++;
                        Map<String, String> reMap = new HashMap<String, String>();
                        reMap.put("index", index + "");
                        if (index >= 2) {
                            reMap.put("salesName", " ");
                        } else {
                            reMap.put("salesName", salesName != "null" ? salesName : "");
                        }

                        Object agentCompany1 = map.get("agent_company");
                        String agentCompany = String.valueOf(agentCompany1);
                        reMap.put("agentCompany", agentCompany != "null" ? agentCompany : "");
//
                        Object rbaName1 = map.get("rba_name");
                        String rbaName = String.valueOf(rbaName1);
                        reMap.put("rbaName", rbaName != "null" ? rbaName : "");

                        Object createDate1 = map.get("create_date");
                        String createDate = String.valueOf(createDate1);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date2 = sdf2.parse(createDate);
                        reMap.put("createDate", sdf.format(date2));
                        Object state1 = map.get("state");
                        String state = String.valueOf(state1);
                        Integer intState = Integer.valueOf(state);
                        //reMap.put("state",state);
                        if (intState == 1) {
                            reMap.put("state", "正常");
                        } else if (intState == 2) {
                            reMap.put("state", "异常");
                        }
                        reList.add(reMap);
                    }
                }
            }
//            dataMap.put("countLack",countLack);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSalesMan.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
            String l = sd.format(date1);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "YWDL" + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "YWDL" + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "YWDL" + l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    //管理员导出查看关联下的导出全部
    public SysRetrunMessage exportSalesManAdmin(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysUserInfo account = this.GetOneSessionAccount();
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate = new Date();
        SysUserInfo SysUserInfo = this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName", SysUserInfo.getUserName());
        Date date = new Date();
        dataMap.put("newDate", sdf.format(date));
        dataMap.put("nodeName", SysUserInfo.getOrgGxId_Str());
        try {
            Integer SalesmanId1 = null;
            //判断显示多少条
            Integer Leaded = sysSalesmanDao.getPageModelInfoLeadedCount(sysSalesManEntity, 1);
            Integer Company = sysSalesmanDao.getPageModelInfoCompanyCount(sysSalesManEntity, 2);
            dataMap.put("huiZong", "汇总");
            dataMap.put("Leaded", Leaded + "家");
            dataMap.put("Company", Company + "家");
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            list = sysSalesmanDao.exportSalesManAdmin(sysSalesManEntity);
            SysSalesManEntity sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
            sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
            String salesName = sysSalesManEntity1.getSalesName();
            int index = 0;
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index", index + "");
                    if (index >= 2) {
                        reMap.put("salesName", " ");
                    } else {
                        reMap.put("salesName", salesName != "null" ? salesName : "");
                    }

                    Object agentCompany1 = map.get("agent_company");
                    String agentCompany = String.valueOf(agentCompany1);
                    reMap.put("agentCompany", agentCompany != "null" ? agentCompany : "");
//
//                        Object rbaName1 = map.get("rba_name");
//                        String rbaName = String.valueOf(rbaName1);
//                        reMap.put("rbaName", rbaName != "null" ? rbaName : "");
                    Object rbaName1 = map.get("rba_name");
                    String rbaName = String.valueOf(rbaName1);

                    Object rbsName1 = map.get("rbs_name");
                    String rbsName = String.valueOf(rbsName1);

                    Object rbbName1 = map.get("rbb_name");
                    String rbbName = String.valueOf(rbbName1);
                    if (rbaName != "null") {
                        reMap.put("rbaName", rbaName);
                    } else if (rbsName != "null") {
                        reMap.put("rbaName", rbsName);
                    } else if (rbbName != "null") {
                        reMap.put("rbaName", rbbName);
                    } else {
                        reMap.put("rbaName", "");
                    }
                    Object createDate1 = map.get("create_date");
                    String createDate = String.valueOf(createDate1);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date2 = sdf2.parse(createDate);
                    reMap.put("createDate", sdf.format(date2));
                    Object state1 = map.get("state");
                    String state = String.valueOf(state1);
                    Integer intState = Integer.valueOf(state);
                    //reMap.put("state",state);
                    if (intState == 1) {
                        reMap.put("state", "正常");
                    } else if (intState == 2) {
                        reMap.put("state", "异常");
                    }
                    reList.add(reMap);
                }
            }
//            dataMap.put("countLack",countLack);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSalesMan.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
            String l = sd.format(date1);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "YWDL" + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "YWDL" + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "YWDL" + l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    public SysRetrunMessage exportSalesManAll(SysSalesManEntity sysSalesManEntity) throws HSKException {

        SysUserInfo account = this.GetOneSessionAccount();
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate = new Date();
        SysUserInfo SysUserInfo = this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName", SysUserInfo.getUserName());
        Date date = new Date();
        dataMap.put("newDate", sdf.format(date));
        dataMap.put("nodeName", SysUserInfo.getOrgGxId_Str());

        try {
            List<Map<String, Integer>> listAccount = sysSalesmanDao.selectSalesManId1(account.getOrgGxId());
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int AgentCompanyCount = 0;
            int rbaNameCount = 0;
            if (listAccount != null && listAccount.size() > 0) {
                for (Map<String, Integer> map1 : listAccount) {
                    Integer salesmanIdAcc = map1.get("salesman_id");
                    sysSalesManEntity.setSalesmanId(salesmanIdAcc);
                    Integer SalesmanId1 = null;
                    dataMap.put("huiZong", "汇总");
                    dataMap.put("Leaded", "");
                    dataMap.put("Company", "");
                    list = sysSalesmanDao.exportSalesManAdmin(sysSalesManEntity);
                    SysSalesManEntity sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
                    sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
                    String salesName = sysSalesManEntity1.getSalesName();
                    int index = 0;
//            int AgentCompanyCount=0;
//            int rbaNameCount=0;
                    if (list != null && list.size() > 0) {
                        for (Map<String, Object> map : list) {
                            index++;
                            Map<String, String> reMap = new HashMap<String, String>();
                            reMap.put("index", index + "");
                            if (index >= 2) {
                                reMap.put("salesName", " ");
                            } else {
                                reMap.put("salesName", salesName != "null" ? salesName : "");
                            }

                            Object agentCompany1 = map.get("agent_company");
                            String agentCompany = String.valueOf(agentCompany1);
                            reMap.put("agentCompany", agentCompany != "null" ? agentCompany : "");
//
//                    Object rbaName1 = map.get("rba_name");
//                    String rbaName = String.valueOf(rbaName1);
//                    reMap.put("rbaName", rbaName != "null" ? rbaName : "");
                            Object rbaName1 = map.get("rba_name");
                            String rbaName = String.valueOf(rbaName1);

                            Object rbsName1 = map.get("rbs_name");
                            String rbsName = String.valueOf(rbsName1);

                            Object rbbName1 = map.get("rbb_name");
                            String rbbName = String.valueOf(rbbName1);
                            if (rbaName != "null") {
                                reMap.put("rbaName", rbaName);
                            } else if (rbsName != "null") {
                                reMap.put("rbaName", rbsName);
                            } else if (rbbName != "null") {
                                reMap.put("rbaName", rbbName);
                            } else {
                                reMap.put("rbaName", "");
                            }

                            if (agentCompany != "null") {
                                AgentCompanyCount += 1;
                            } else {
                                AgentCompanyCount += 0;
                            }
                            if (rbaName != "null" || rbsName != "null" || rbbName != "null") {
                                rbaNameCount += 1;
                            } else {
                                rbaNameCount += 0;
                            }

                            Object createDate1 = map.get("create_date");
                            String createDate = String.valueOf(createDate1);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date2 = sdf2.parse(createDate);
                            reMap.put("createDate", sdf.format(date2));
                            Object state1 = map.get("state");
                            String state = String.valueOf(state1);
                            Integer intState = Integer.valueOf(state);
                            //reMap.put("state",state);
                            if (intState == 1) {
                                reMap.put("state", "正常");
                            } else if (intState == 2) {
                                reMap.put("state", "异常");
                            }
                            reList.add(reMap);
                        }
                    }
                }
            }
            if (AgentCompanyCount != 0 && rbaNameCount != 0) {
                dataMap.put("huiZong", "汇总");
                dataMap.put("Leaded", AgentCompanyCount + "家");
                dataMap.put("Company", rbaNameCount + "家");
            }
//            dataMap.put("countLack",countLack);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSalesMan.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
            String l = sd.format(date1);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "YWDL" + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "YWDL" + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "YWDL" + l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    public SysRetrunMessage exportSalesManAgent(SysSalesManEntity sysSalesManEntity) throws HSKException {
        SysUserInfo account = this.GetOneSessionAccount();
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate = new Date();
        SysUserInfo SysUserInfo = this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName", SysUserInfo.getUserName());
        Date date = new Date();
        dataMap.put("newDate", sdf.format(date));
        dataMap.put("nodeName", SysUserInfo.getOrgGxId_Str());
        try {
            SysSalesManEntity sysSalesManEntity3 = new SysSalesManEntity();
            sysSalesManEntity3.setSalesAccount(account.getAccount());
            SysSalesManEntity sysSalesManEntity2 = sysSalesmanDao.getObject(sysSalesManEntity3);
            List<Map<String, Integer>> listAccount = sysSalesmanDao.selectSalesManId(sysSalesManEntity2.getSalesmanId());
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int AgentCompanyCount = 0;
            int rbaNameCount = 0;
            if (listAccount != null && listAccount.size() > 0) {
                for (Map<String, Integer> map1 : listAccount) {
                    Integer salesmanIdAcc = map1.get("salesman_id");
                    sysSalesManEntity.setSalesmanId(salesmanIdAcc);
                    Integer SalesmanId1 = null;
                    dataMap.put("huiZong", "汇总");
                    dataMap.put("Leaded", "");
                    dataMap.put("Company", "");
                    //sysSalesManEntity.setSalesAccount(account.getAccount());
                    list = sysSalesmanDao.exportSalesManAdmin(sysSalesManEntity);
                    SysSalesManEntity sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
                    sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
                    String salesName = sysSalesManEntity1.getSalesName();
                    String agentCompany = sysSalesManEntity1.getAgentCompany();
                    int index = 0;
                    if (list != null && list.size() > 0) {
                        for (Map<String, Object> map : list) {
                            index++;
                            Map<String, String> reMap = new HashMap<String, String>();
                            reMap.put("index", index + "");
                            if (index >= 2) {
                                reMap.put("salesName", " ");
                            } else {
                                reMap.put("salesName", salesName != "null" ? salesName : "");
                            }
//                            Object agentCompany1 = map.get("agent_company");
//                            String agentCompany = String.valueOf(agentCompany1);
                            reMap.put("agentCompany", agentCompany != "null" ? agentCompany : "");
//
                            Object rbaName1 = map.get("rba_name");
                            String rbaName = String.valueOf(rbaName1);

                            Object rbsName1 = map.get("rbs_name");
                            String rbsName = String.valueOf(rbsName1);

                            Object rbbName1 = map.get("rbb_name");
                            String rbbName = String.valueOf(rbbName1);
                            if (rbaName != "null") {
                                reMap.put("rbaName", rbaName);
                            } else if (rbsName != "null") {
                                reMap.put("rbaName", rbsName);
                            } else if (rbbName != "null") {
                                reMap.put("rbaName", rbbName);
                            } else {
                                reMap.put("rbaName", "");
                            }
                            Object createDate1 = map.get("create_date");
                            String createDate = String.valueOf(createDate1);
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date2 = sdf2.parse(createDate);
                            reMap.put("createDate", sdf.format(date2));
                            Object state1 = map.get("state");
                            String state = String.valueOf(state1);
                            Integer intState = Integer.valueOf(state);


                            if (agentCompany != "null") {
                                AgentCompanyCount += 1;
                            } else {
                                AgentCompanyCount += 0;
                            }
                            if (rbaName != "null" || rbsName != "null" || rbbName != "null") {
                                rbaNameCount += 1;
                            } else {
                                rbaNameCount += 0;
                            }
                            if (intState == 1) {
                                reMap.put("state", "正常");
                            } else if (intState == 2) {
                                reMap.put("state", "异常");
                            }
                            reList.add(reMap);
                        }
                    }
                }
            }
            if (AgentCompanyCount != 0 && rbaNameCount != 0) {
                dataMap.put("huiZong", "汇总");
                dataMap.put("Leaded", AgentCompanyCount + "家");
                dataMap.put("Company", rbaNameCount + "家");
            }
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSalesMan.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
            String l = sd.format(date1);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "YWDL" + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "YWDL" + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "YWDL" + l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    @Override
    public PagerModel getSalesManLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1,
                                            String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen, Integer selectValue,
                                            String dateInput7, String dateInput8, Integer moiId, Integer limit, Integer page) throws HSKException {
        PagerModel pm = new PagerModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<Map<String, Object>> mxList = sysSalesmanDao.getSalesManLoanMxList(orderCodeGJ, dateInput1, dateInput2, selectValue1, moneyFw1, moneyFw2, gongYingS, caiGou, jiGouMenZhen, selectValue, dateInput7, dateInput8, moiId, limit, page);
            for (Map<String, Object> map : mxList) {
                Object PlaceOrderTime = map.get("place_order_time");
                map.put("place_order_time", sdf.format(PlaceOrderTime));


                Object paydate = map.get("pay_date");
                if (paydate != null && !paydate.equals("")) {
                    map.put("pay_date", sdf.format(paydate));
                } else {
                    map.put("pay_date", "");
                }
            }
            List<Map<String, Object>> mxList1 = sysSalesmanDao.getSalesManLoanMxList(orderCodeGJ, dateInput1, dateInput2, selectValue1, moneyFw1, moneyFw2, gongYingS, caiGou, jiGouMenZhen, selectValue, dateInput7, dateInput8, moiId, null, null);
            Integer mxListCount1 = mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());

        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage saveLoan(String checkStr, String loanMoney) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        if (checkStr == null || checkStr.equals("")) {
            srm.setCode(2L);
            return srm;
        }
        SysUserInfo sysUserInfo = this.GetOneSessionAccount();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String check[] = checkStr.split(",");

            MdOrderInfo att_mdOrderInfo = null;
            for (String moiIds : check) {
                Integer moiId = Integer.valueOf(moiIds);
                att_mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);

                if (att_mdOrderInfo.getSettlement() != 1) {
                    srm.setCode(2L); //未结算不允许放款
                    throw new HSKException();
                }
                if (att_mdOrderInfo.getLoanState() == 1) {
                    srm.setCode(3L); //不允许重复放款
                    throw new HSKException();
                }

                att_mdOrderInfo.setLoanState(1);
                att_mdOrderInfo.setLoanMoney(att_mdOrderInfo.getActualMoney());
                String setSettlementMoneyStr = String.valueOf(att_mdOrderInfo.getActualMoney());
                String SettlementLogStr = sysUserInfo.getUserName() + "/" + sdf.format(new Date()) + "\t\t" + "放款￥" + setSettlementMoneyStr;
                att_mdOrderInfo.setLoanLog(SettlementLogStr);

                mdOrderInfoDao.saveOrUpdateMdOrderInfo(att_mdOrderInfo);
            }
            srm.setObj(att_mdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage getTotalMoney() throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, String> SeMap = new HashMap<String, String>();
        SysUserInfo sysUserInfo = this.GetOneSessionAccount();

        try {
            Integer total = sysSalesmanDao.totalAll();
            String total1 = String.valueOf(total);
            SeMap.put("total", total1);

            Double money1 = sysSalesmanDao.totalLanMoney(2);
            String money11 = String.valueOf(money1);
            SeMap.put("money1", money11);

            Double money2 = sysSalesmanDao.totalLanMoney(1);
            String money21 = String.valueOf(money2);
            SeMap.put("money2", money21);
            srm.setObj(SeMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }

    @Override
    public PagerModel getSalesManSupplier(Integer salesmanId) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdSupplier>());
        try {
            pm = sysSalesmanDao.getPagerModelSupplier(salesmanId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }
}
