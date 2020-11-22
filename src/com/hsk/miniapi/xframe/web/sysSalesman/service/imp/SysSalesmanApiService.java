package com.hsk.miniapi.xframe.web.sysSalesman.service.imp;

import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysRoleInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysSalesmanApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.miniapi.xframe.api.daobbase.imp.SysSalesmanApiDao;
import com.hsk.xframe.api.persistence.*;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CommonUtil;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.sysSalesman.service.ISysSalesmanApiService;
import com.hsk.miniapi.xframe.web.sysUser.service.ISysUserApiService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.sun.tools.xjc.outline.PackageOutline;
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
public class SysSalesmanApiService extends DSTApiService implements ISysSalesmanApiService {
    @Autowired
    ISysSalesmanApiDao sysSalesmanDao;
    @Autowired
    protected IorgApiDao orgDao;
    @Autowired
    private ISysUserInfoApiDao sysUserInfoDao;
    @Autowired
    private ISysRoleInfoApiDao sysRoleInfoDao;

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
            salesManEntity = sysSalesmanDao.getObject(salesManEntity);
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
            if (account.getUserType() != null && account.getUserType() == 6){
                sysSalesManEntity.setVerifyState(1);
                sysSalesManEntity.setState(2);
                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
                sysSalesManEntity1.setSalesAccount(account.getAccount());
                sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity1);
                sysSalesManEntity.setLeaderId(sysSalesManEntity1.getSalesmanId());
            }
            sysSalesmanDao.saveSalesman(sysSalesManEntity);

            String code = CreateCodeUtil.getNo("USE");
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(sysSalesManEntity.getSalesAccount());
            sysUserInfo.setUserName(sysSalesManEntity.getSalesName());
            sysUserInfo.setPassword(sysSalesManEntity.getSalesPassword());
            sysUserInfo.setUserType(3);
            sysUserInfo.setUserCode(code);
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
                    sysUserInfo.setState(1);
                    sysUserInfo.setPassword("123456");
                    if (account.getUserType() != null && account.getUserType() == 6){
                        sysUserInfo.setState(2);
                    }
                    this.newObject(sysUserInfo);
                }
                String roles = "2225";
                if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 2){
                    roles = "2226";
                } else if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
                    roles = "2227";
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
            if (account.getUserType() != null && account.getUserType() == 6){
                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
                sysSalesManEntity1.setSalesAccount(account.getAccount());
                sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity1);
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
                sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity1);
                sysSalesManEntity.setSalesmanId(sysSalesManEntity1.getSalesmanId());
            }
            pm = sysSalesmanDao.getPageModelInfo(sysSalesManEntity);
            if (pm == null){
                pm = new PagerModel();
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                List<Map<String, Object>> list = pm.getItems();
                for (Map<String, Object> map : list){
                    Object date = map.get("create_date");
                    if (date != null){
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
            if (account.getUserType() != null && account.getUserType() == 6){
                SysSalesManEntity sysSalesManEntity1 = new SysSalesManEntity();
                sysSalesManEntity1.setSalesAccount(account.getAccount());
                sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity1);
                sysSalesManEntity.setLeaderId(sysSalesManEntity1.getSalesmanId());
            }
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
                String city = sysSalesManEntity.getCity();
                String province = sysSalesManEntity.getProvince();
                String area = sysSalesManEntity.getArea();
                sysSalesManEntity.setSalesArea(province + "/" + city + "/" + area);
                sysSalesManEntity.setCreateDate(new Date());
                sysSalesManEntity.setCreateRen(account.getUserName());
                sysSalesManEntity.setSalesCompany(account.getOrgName());
                sysSalesManEntity.setOrgGxId(account.getOrgGxId());
                sysSalesmanDao.saveSalesman(sysSalesManEntity);
                return sm;
            }

            SysSalesManEntity salesMan = new SysSalesManEntity();
            salesMan .setSalesAccount(sysSalesManEntity.getSalesAccount());
            salesMan = sysSalesmanDao.getObject((salesMan));

            String accoutName = sysSalesManEntity.getSalesAccount();
            if (salesMan != null && accoutName.equals(salesMan.getSalesAccount())) {
                SysSalesManEntity salesManEntity = new SysSalesManEntity();
                salesManEntity.setSalesAccount(accoutName);
                salesManEntity = sysSalesmanDao.getObject(salesManEntity);
                if (salesManEntity != null) {
                    sm.setCode(2l);
                    sm.setMeg("账号重复，请重新输入！");
                    return sm;
                }
            }
            if(salesMan == null){
                salesMan = new SysSalesManEntity();
            }

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
                if (verifyState == 3){
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
                if(sysUserInfo != null) {
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
            Integer countLeaded = sysSalesmanDao.getPageModelInfoLeadedCount(sysSalesManEntity, 1);
            Integer countCompany = sysSalesmanDao.getPageModelInfoCompanyCount(sysSalesManEntity, 2);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("agentCount", countLeaded);
            map.put("otherCount", countCompany);
            sm.setObj(map);
        } catch (Exception e){
            sm.setCode(2l);
            e.printStackTrace();
        }
        return sm;
    }

    //创建表格
    public SysRetrunMessage exportSalesMan(SysSalesManEntity sysSalesManEntity) throws HSKException{
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
            Integer Leaded = sysSalesmanDao.getPageModelInfoLeadedCount(sysSalesManEntity, 1);
            Integer Company = sysSalesmanDao.getPageModelInfoCompanyCount(sysSalesManEntity, 2);
            dataMap.put("Leaded",Leaded);
            dataMap.put("Company",Company);
            List<Map<String, Object>> list = sysSalesmanDao.exportSalesMan(sysSalesManEntity);
           List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
           int index = 0;
//            Double countLack=0.0;
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index",index+"");
                    Object salesName1=map.get("sales_name");
                    String salesName=String.valueOf(salesName1);
                    reMap.put("salesName", salesName!="null" ? salesName:"");

                    Object agentCompany1=map.get("agent_company");
                    String agentCompany=String.valueOf(agentCompany1);
                    reMap.put("agentCompany", agentCompany!="null" ? agentCompany:"");
//
                    Object rbaName1=map.get("rba_name");
                    String rbaName=String.valueOf(rbaName1);
                    reMap.put("rbaName", rbaName!="null" ? rbaName:"");

                    Object createDate1=map.get("create_date");
                    String createDate=String.valueOf(createDate1);
                    SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date2=sdf2.parse(createDate);
                    reMap.put("createDate", sdf.format(date2));
                    Object state1=map.get("state");
                    String state=String.valueOf(state1);
                    Integer intState=Integer.valueOf(state);
                    //reMap.put("state",state);
                    if (intState==1){
                        reMap.put("state", "正常");
                    }else if(intState==2){
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
            String l =sd.format(date1);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "YWDL"+l+ ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "YWDL"+l+ ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "YWDL"+l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  srm;
    }

    @Override
    public SysRetrunMessage updateDelivery(String account, String delivery, String salesAddress) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysSalesManEntity salesManEntity = new SysSalesManEntity();
            salesManEntity.setSalesAccount(account);
            salesManEntity = sysSalesmanDao.getObject(salesManEntity);
            if (salesManEntity == null) {
                sm.setCode(2L);
                sm.setMeg("网络错误，请重新登录");
                return sm;
            }

            if (delivery == null || delivery.equals("")) {
                sm.setCode(2L);
                sm.setMeg("地址或者区域必填");
                return sm;
            }

            String[] deliveryList = delivery.split("/");

            if (deliveryList.length < 3) {
                sm.setCode(2L);
                sm.setMeg("地址或者区域必填");
                return sm;
            }
            if (deliveryList[0] == null || deliveryList[1] == null || deliveryList[2] == null) {
                sm.setCode(2L);
                sm.setMeg("地址或者区域必填");
                return sm;
            }
            if (deliveryList[0].equals("") || deliveryList[1].equals("") || deliveryList[2].equals("")) {
                sm.setCode(2L);
                sm.setMeg("地址或者区域必填");
                return sm;
            }
            salesManEntity.setSalesArea(delivery);
            salesManEntity.setSalesAddress(salesAddress);

            this.updateObject(salesManEntity);
        } catch (Exception e) {
            sm.setCode(2l);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getDelivery(String account) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysSalesManEntity salesManEntity = new SysSalesManEntity();
            salesManEntity.setSalesAccount(account);
            salesManEntity = sysSalesmanDao.getObject(salesManEntity);
            if (salesManEntity == null) {
                sm.setCode(2L);
                sm.setMeg("网络错误，请重新登录");
                return sm;
            }
            sm.setObj(salesManEntity);
        } catch (Exception e) {
            sm.setCode(2l);
            e.printStackTrace();
        }
        return sm;
    }
}
