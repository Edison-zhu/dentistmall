package com.hsk.miniapi.xframe.web.sysLogin.service.impl;

import com.hsk.miniapi.SessionUtil;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserLoginLogApiDao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.hsk.miniapi.xframe.jwt.TokenUtil;
import com.hsk.supper.until.json.JsonUtil;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysFeedbackEntity;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdCompanyGroupApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdDentalClinicApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdDentistHospitalApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdSupplierApiDao;
import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysFileInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.miniapi.xframe.web.sysLogin.service.ISysLoginApiService;
import sun.security.x509.SubjectAlternativeNameExtension;

/**
 * @author 张曙
 */
@Service
public class SysLoginApiService extends DSTApiService implements ISysLoginApiService {
    @Value("${appId}")
    String appId;
    @Value("${secret}")
    String secret;
    @Value("${salesAppId}")
    String salesAppId;
    @Value("${salesSecret}")
    String salesSecret;
    @Autowired
    private ISysUserInfoApiDao sysUserInfoDao;
    @Autowired
    protected IMdCompanyGroupApiDao mdCompanyGroupDao;
    @Autowired
    protected IMdDentistHospitalApiDao mdDentistHospitalDao;
    @Autowired
    protected IMdDentalClinicApiDao mdDentalClinicDao;
    @Autowired
    protected IMdSupplierApiDao mdSupplierDao;
    @Autowired
    ISysUserLoginLogApiDao sysUserLoginLogDao;
    @Autowired
    ISysSalesmanDao salesmanDao;

    //测试Ip存入数据库
    @Autowired
    protected ISysFileInfoApiDao sysFileInfoDao;

    @Override
    public SysRetrunMessage sysLogin(String userName, String password)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo sysUserInfo = sysUserInfoDao.userLogin(userName);
            if (sysUserInfo == null || !sysUserInfo.getState().equals(1)) {
                srm.setCode(0l);
                srm.setMeg("该用户不存在!");
                return srm;
            }
            if (!password.equals(sysUserInfo.getPassword())) {
                srm.setCode(0l);
                srm.setMeg("密码错误!");
                return srm;
            }
            if (sysUserInfo.getOrganizaType().equals("100")) {
                MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(sysUserInfo.getOldId());
                if (!mdSupplier.getState().equals("1")) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
            } else if (sysUserInfo.getOrganizaType().equals("20001")) {
                MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(sysUserInfo.getOldId());
                if (mdCompanyGroup == null || !mdCompanyGroup.getState().equals(1)) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
            } else if (sysUserInfo.getOrganizaType().equals("20002")) {
                MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(sysUserInfo.getOldId());
                if (mdDentistHospital == null || !mdDentistHospital.getState().equals(1)) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
            } else if (sysUserInfo.getOrganizaType().equals("20003")) {
                MdDentalClinic mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(sysUserInfo.getOldId());
                if (mdDentalClinic == null || !mdDentalClinic.getState().equals(1)) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
            }

            if (sysUserInfo.getOrganizaType().equals("2002") || sysUserInfo.getOrganizaType().equals("2003") || sysUserInfo.getOrganizaType().equals("2004")) {
                if (sysUserInfo.getUserRole().contains("2"))
                    this.SetShoppingSessionAccount(sysUserInfo);
                else
                    this.SetShoppingSessionAccount(null);
            } else
                this.SetShoppingSessionAccount(null);
            this.SetSessionAccount(sysUserInfo);
            srm.setMeg("登录成功!");

            /**
             *获取用户ip 并保存
             */
            //start
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            //FileWriter writer;
	       /* try {
	            writer = new FileWriter("D:\\tomcat\\apache-tomcat-7.0.63\\ip.txt",true);
	            Date date=new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            writer.write("登录时间"+sdf.format(date)+"\t"+"用户ip地址:"+ip+"\r\n");
	            writer.flush();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            }*/
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 2, ip);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("查询失败!");
        }
        return srm;
    }

    @Override
    public SysRetrunMessage shoppingLogin(String userName, String password)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo sysUserInfo = sysUserInfoDao.userLogin(userName);

            if (sysUserInfo == null || !sysUserInfo.getState().equals(1)) {
                srm.setCode(0l);
                srm.setMeg("该用户不存在!");
                return srm;
            }
            if (sysUserInfo.getUserType() != null && sysUserInfo.getUserType() == 6) {
                srm.setCode(0l);
                srm.setMeg("该用户不存在!");
                return srm;
            }
            if (!sysUserInfo.getOrganizaType().equals("2002") && sysUserInfo.getOrganizaType().equals("2003") && sysUserInfo.getOrganizaType().equals("2004")) {
                srm.setCode(0l);
                srm.setMeg("该用户不存在!");
                return srm;
            }
            if (!sysUserInfo.getUserRole().contains("2")) {
                srm.setCode(0l);
                srm.setMeg("该用户不存在!");
                return srm;
            }
            if (!password.equals(sysUserInfo.getPassword())) {
                srm.setCode(0l);
                srm.setMeg("密码错误!");
                return srm;
            }
            Integer rbaId = 0;
            String rbaName = "";
            if (sysUserInfo.getOrganizaType().equals("20001")) {
                MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(sysUserInfo.getOldId());

                if (mdCompanyGroup == null || !mdCompanyGroup.getState().equals(1)) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
                rbaId = mdCompanyGroup.getRbaId();
                rbaName = mdCompanyGroup.getRbaName();
            } else if (sysUserInfo.getOrganizaType().equals("20002")) {
                MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(sysUserInfo.getOldId());
                if (mdDentistHospital == null || !mdDentistHospital.getState().equals(1)) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
                rbaId = mdDentistHospital.getRbaId();
            } else if (sysUserInfo.getOrganizaType().equals("20003")) {
                MdDentalClinic mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(sysUserInfo.getOldId());
                if (mdDentalClinic == null || !mdDentalClinic.getState().equals(1)) {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
                rbaId = mdDentalClinic.getRbaId();
            }
            if (rbaId != 0 && rbaName.equals("")) {
                MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(rbaId);
                if (mdCompanyGroup != null) {
                    rbaName = mdCompanyGroup.getRbaName();
                }
            }
            sysUserInfo.setCompanyName(rbaName);
            sysUserInfo.setNeedSecurity(1);
            sysUserInfoDao.updateUserNeedSecurity(sysUserInfo.getNeedSecurity(), sysUserInfo.getSuiId());
            Map<String, Object> map = new HashMap<String, Object>();
            String token = TokenUtil.addToken(sysUserInfo);

            map.put("token", token);
            SysUserInfo noPassUser = sysUserInfo;
            noPassUser.setPassword("");
            map.put("account", noPassUser);

            String sessionId = SessionUtil.getSession(sysUserInfo.getAccount());
            if (sessionId != null && !sessionId.equals("")) {
                SessionUtil.remove(sysUserInfo.getAccount());
                SessionUtil.removeL(sessionId, sysUserInfo.getAccount());
            }
            String oldSessionId = request.getHeader("sessionId");
            if (oldSessionId != null && !oldSessionId.equals("")) {
                String accountName = SessionUtil.getLocalSessionId(oldSessionId);
                if (accountName != null && !accountName.equals("")) {
                    SessionUtil.remove(accountName);
                    SessionUtil.removeL(oldSessionId, accountName);
                }
            }


            SessionUtil.setSession(sysUserInfo.getAccount(), request.getSession().getId());
            SessionUtil.setLink(request.getSession().getId(), sysUserInfo.getAccount());
            request.getSession().setMaxInactiveInterval(10);
            map.put("sessionId", request.getSession().getId());
            srm.setObj(map);
            srm.setMeg("登录成功!");
            /**
             *获取用户ip 并保存
             */
            //start
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 1, ip);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("查询失败!");
        }
        return srm;
    }

    @Override
    public SysRetrunMessage salesManLogin(String userName, String password) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo sysUserInfo =null;
            boolean result=userName.matches("[0-9]+");
            if (result == true) {
                Integer phoneCount=sysUserInfoDao.PhoneNumberCount(userName);
                if (phoneCount==0){
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }else if (phoneCount>1){
                    srm.setCode(6l);
                    srm.setMeg("该手机号拥有多个账户");
                    return srm;
                }else {
                    sysUserInfo=sysUserInfoDao.userLoginPhoneNumber(userName);
                }

            }else {
                sysUserInfo=sysUserInfoDao.userLogin(userName);
            }
            if (sysUserInfo == null || !sysUserInfo.getState().equals(1)) {
                srm.setCode(0l);
                srm.setMeg("该用户不存在!");
                return srm;
            }
            if (sysUserInfo.getUserType() != null) {
                if (sysUserInfo.getUserType() != 6){
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
            }else {
                if (sysUserInfo.getUserType()==null&&sysUserInfo.getOrgGxId()==1){
                }else {
                    srm.setCode(0l);
                    srm.setMeg("该用户不存在!");
                    return srm;
                }
            }
            SysSalesManEntity salesManEntity = new SysSalesManEntity();
            salesManEntity.setSalesAccount(sysUserInfo.getAccount());
            salesManEntity = salesmanDao.getSalesManInfo(salesManEntity);
            if (sysUserInfo.getUserType()==null&&sysUserInfo.getOrgGxId()==1){
            }else {
                if (salesManEntity == null){
                    srm.setCode(0L);
                    srm.setMeg("该业务员不存在");
                    return srm;
                }
            }

            if (!password.equals(sysUserInfo.getPassword())) {
                srm.setCode(0l);
                srm.setMeg("密码错误!");
                return srm;
            }

//            sysUserInfo.setCompanyName(rbaName);
            Map<String, Object> map = new HashMap<String, Object>();
            String token = TokenUtil.addToken(sysUserInfo);

            map.put("token", token);
            SysUserInfo noPassUser = sysUserInfo;
            noPassUser.setPassword("");
            map.put("account", noPassUser);

            String sessionId = SessionUtil.getSession(sysUserInfo.getAccount());
            if (sessionId != null && !sessionId.equals("")) {
                SessionUtil.remove(sysUserInfo.getAccount());
                SessionUtil.removeL(sessionId, sysUserInfo.getAccount());
            }
            String oldSessionId = request.getHeader("sessionId");
            if (oldSessionId != null && !oldSessionId.equals("")) {
                String accountName = SessionUtil.getLocalSessionId(oldSessionId);
                if (accountName != null && !accountName.equals("")) {
                    SessionUtil.remove(accountName);
                    SessionUtil.removeL(oldSessionId, accountName);
                }
            }

            SessionUtil.setSession(sysUserInfo.getAccount(), request.getSession().getId());
            SessionUtil.setLink(request.getSession().getId(), sysUserInfo.getAccount());
            request.getSession().setMaxInactiveInterval(10);
            map.put("sessionId", request.getSession().getId());
            srm.setObj(map);
            srm.setMeg("登录成功!");
            /**
             *获取用户ip 并保存
             */
            //start
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 1, ip);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("查询失败!");
        }
        return srm;
    }

    @Override
    public SysRetrunMessage getCheckLogin() {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            String token = request.getHeader("token");
            if (token == null || token.equals("") || !token.startsWith("Breazer ")) {
                sm.setCode(0l);
                return sm;
            }
            token = token.substring("Breazer ".length());
            Map<String, Object> tokenData = TokenUtil.TOKEN_MAP.get(token);
            if (tokenData == null) {
                sm.setCode(0l);
                return sm;
            }
            SysUserInfo account = (SysUserInfo) tokenData.get("account");

            Date date = (Date) tokenData.get("createTime");

            SysUserInfo sysUserInfo = this.getApiSessionAccount();
            if (!account.getAccount().equals(sysUserInfo.getAccount())) {
                sm.setCode(0l);
                return sm;
            }

            String session = SessionUtil.getSession(sysUserInfo.getAccount());
            if (session == null && !session.equals("")) {
                sm.setCode(0l);
                return sm;
            }
            String oldSessionId = request.getHeader("sessionId");
            if (oldSessionId != null && !oldSessionId.equals("")) {
                String accountName = SessionUtil.getLocalSessionId(oldSessionId);
                if (accountName != null && !accountName.equals("") && !accountName.equals(sysUserInfo.getAccount())) {
                    sm.setCode(0l);
                    return sm;
                }
            }

        } catch (HSKException e) {
            sm.setCode(0l);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage ReceptionCancellation()
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1);

        try {
            String sessionId = request.getHeader("sessionId");
            String accountName = SessionUtil.getLocalAccount(sessionId);
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(accountName);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);

            SessionUtil.remove(sysUserInfo.getAccount());
            if (sessionId != null && !sessionId.equals("")) {
                SessionUtil.removeL(sessionId, sysUserInfo.getAccount());
            }

            String token = request.getHeader("token");
            if (token != null && !token.trim().equals("")) {
                token = token.substring("Breazer ".length());
                if (TokenUtil.TOKEN_MAP.containsKey(token)) {//如果存在，更新登录时间
                    TokenUtil.delToken(token);
                }
            }
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
//		SysUserInfo account = this.GetOneSessionAccount();
//		this.SetSessionAccount(null);
//		this.SetShoppingSessionAccount(null);
//		if(CommonUtil.USER_SESSION_MAP.containsKey(account.getSuiId()) && CommonUtil.USER_SESSION_MAP.get(account.getSuiId()) != null){
//    		HttpSession session = CommonUtil.USER_SESSION_MAP.get(account.getSuiId());
//    		session.invalidate();
//    		CommonUtil.USER_SESSION_MAP.remove(account.getSuiId());
//    	}
        return srm;
    }

    @Override
    public void addSysUserLoginShoppingLog(Integer suiId, String ip) throws HSKException {
        try {
            sysUserLoginLogDao.addSysUserLoginLog(suiId, 1, ip);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSysUserLoginSysLog(Integer suiId, String ip) throws HSKException {
        try {
            sysUserLoginLogDao.addSysUserLoginLog(suiId, 2, ip);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SysRetrunMessage weChatLogin(String code) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            HttpClient client = new DefaultHttpClient();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=JSCODE&grant_type=authorization_code";
            url = url.replace("JSCODE", code);
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            String h = EntityUtils.toString(response.getEntity());
            Map<String, Object> map = JsonUtil.getMap4Json(h);
            String sessionKey = map.get("session_key").toString();
            String openId = map.get("openid").toString();
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setThirdParty(openId);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (sysUserInfo == null) {
                sm.setCode(2L); // 此微信未绑定
                sm.setObj(map);
                return sm;
            }
            if (sysUserInfo == null || !sysUserInfo.getState().equals(1)) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }
            if (sysUserInfo.getUserType() != null && sysUserInfo.getUserType() == 6) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }
            if (!sysUserInfo.getOrganizaType().equals("2002") && sysUserInfo.getOrganizaType().equals("2003") && sysUserInfo.getOrganizaType().equals("2004")) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }
            if (!sysUserInfo.getUserRole().contains("2")) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }

            Integer rbaId = 0;
            String rbaName = "";
            if (sysUserInfo.getOrganizaType().equals("20001")) {
                MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(sysUserInfo.getOldId());

                if (mdCompanyGroup == null || !mdCompanyGroup.getState().equals(1)) {
                    sm.setCode(0l);
                    sm.setMeg("该用户不存在!");
                    return sm;
                }
                rbaId = mdCompanyGroup.getRbaId();
                rbaName = mdCompanyGroup.getRbaName();
            } else if (sysUserInfo.getOrganizaType().equals("20002")) {
                MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(sysUserInfo.getOldId());
                if (mdDentistHospital == null || !mdDentistHospital.getState().equals(1)) {
                    sm.setCode(0l);
                    sm.setMeg("该用户不存在!");
                    return sm;
                }
                rbaId = mdDentistHospital.getRbaId();
            } else if (sysUserInfo.getOrganizaType().equals("20003")) {
                MdDentalClinic mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(sysUserInfo.getOldId());
                if (mdDentalClinic == null || !mdDentalClinic.getState().equals(1)) {
                    sm.setCode(0l);
                    sm.setMeg("该用户不存在!");
                    return sm;
                }
                rbaId = mdDentalClinic.getRbaId();
            }
            if (rbaId != 0 && rbaName.equals("")) {
                MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(rbaId);
                if (mdCompanyGroup != null) {
                    rbaName = mdCompanyGroup.getRbaName();
                }
            }
            sysUserInfo.setCompanyName(rbaName);
            String token = TokenUtil.addToken(sysUserInfo);

            map.put("token", token);
            SysUserInfo noPassUser = sysUserInfo;
            noPassUser.setPassword("");
            map.put("account", noPassUser);

            String sessionId = SessionUtil.getSession(sysUserInfo.getAccount());
            if (sessionId != null && !sessionId.equals("")) {
                SessionUtil.remove(sysUserInfo.getAccount());
                SessionUtil.removeL(sessionId, sysUserInfo.getAccount());
            }
            String oldSessionId = request.getHeader("sessionId");
            if (oldSessionId != null && !oldSessionId.equals("")) {
                String accountName = SessionUtil.getLocalSessionId(oldSessionId);
                if (accountName != null && !accountName.equals("")) {
                    SessionUtil.remove(accountName);
                    SessionUtil.removeL(oldSessionId, accountName);
                }
            }

            SessionUtil.setSession(sysUserInfo.getAccount(), request.getSession().getId());
            SessionUtil.setLink(request.getSession().getId(), sysUserInfo.getAccount());
            request.getSession().setMaxInactiveInterval(10);
            map.put("sessionId", request.getSession().getId());
            sm.setObj(map);
            sm.setMeg("登录成功!");
            /**
             *获取用户ip 并保存
             */
            //start
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 1, ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage weChatSalesManLogin(String code) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            HttpClient client = new DefaultHttpClient();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + salesAppId + "&secret=" + salesSecret + "&js_code=JSCODE&grant_type=authorization_code";
            url = url.replace("JSCODE", code);
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            String h = EntityUtils.toString(response.getEntity());
            Map<String, Object> map = JsonUtil.getMap4Json(h);
            String sessionKey = map.get("session_key").toString();
            String openId = map.get("openid").toString();
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setThirdParty(openId);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (sysUserInfo == null) {
                sm.setCode(2L); // 此微信未绑定
                sm.setObj(map);
                return sm;
            }
            if (sysUserInfo == null || !sysUserInfo.getState().equals(1)) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }
            if (sysUserInfo.getUserType() != null && sysUserInfo.getUserType() != 6) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }
            SysSalesManEntity salesManEntity = new SysSalesManEntity();
            salesManEntity.setSalesAccount(sysUserInfo.getAccount());
            salesManEntity = salesmanDao.getSalesManInfo(salesManEntity);
            if (salesManEntity == null) {
                sm.setCode(0l);
                sm.setMeg("该用户不存在!");
                return sm;
            }

            String token = TokenUtil.addToken(sysUserInfo);

            map.put("token", token);
            SysUserInfo noPassUser = sysUserInfo;
            noPassUser.setPassword("");
            map.put("account", noPassUser);

            String sessionId = SessionUtil.getSession(sysUserInfo.getAccount());
            if (sessionId != null && !sessionId.equals("")) {
                SessionUtil.remove(sysUserInfo.getAccount());
                SessionUtil.removeL(sessionId, sysUserInfo.getAccount());
            }
            String oldSessionId = request.getHeader("sessionId");
            if (oldSessionId != null && !oldSessionId.equals("")) {
                String accountName = SessionUtil.getLocalSessionId(oldSessionId);
                if (accountName != null && !accountName.equals("")) {
                    SessionUtil.remove(accountName);
                    SessionUtil.removeL(oldSessionId, accountName);
                }
            }

            SessionUtil.setSession(sysUserInfo.getAccount(), request.getSession().getId());
            SessionUtil.setLink(request.getSession().getId(), sysUserInfo.getAccount());
            request.getSession().setMaxInactiveInterval(10);
            map.put("sessionId", request.getSession().getId());
            sm.setObj(map);
            sm.setMeg("登录成功!");
            /**
             *获取用户ip 并保存
             */
            //start
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 1, ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateThirdParty(String sessionKey) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo sysUserInfo = this.getApiSessionAccount();
            if (sysUserInfo == null) {
                sm.setCode(2L); //未登录
                return sm;
            }
            sysUserInfo.setThirdParty(sessionKey);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (sysUserInfo.getUserType()!=null&&sysUserInfo.getUserType()==6){
                if (this.getApiSessionSalesMan()==null){
                    sm.setCode(5L);
                    sm.setMeg("用户不存在");
                    return sm;
                }
            }
            if (sysUserInfo == null) {
                sm.setCode(3L); // 已解除
                return sm;
            }
            sysUserInfo.setThirdParty("");
            this.updateObject(sysUserInfo);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateBindThirdParty(String sessionKey) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo sysUserInfo = this.getApiSessionAccount();
            if (sysUserInfo == null) {
                sm.setCode(2L); //未登录
                return sm;
            }
            if (sysUserInfo.getUserType()!=null&&sysUserInfo.getUserType()==6){
                if (this.getApiSessionSalesMan()==null){
                    sm.setCode(5L);
                    sm.setMeg("用户不存在");
                    return sm;
                }
            }
            SysUserInfo sysUserInfo1 = new SysUserInfo();
            sysUserInfo1.setSuiId(sysUserInfo.getSuiId());
            sysUserInfo1 = (SysUserInfo) this.getOne(sysUserInfo1);
            if (sysUserInfo1.getThirdParty() != null && !sysUserInfo1.getThirdParty().equals("")) {
                sm.setCode(3L); // 已经绑定过
                return sm;
            }
            sysUserInfo1.setThirdParty(sessionKey);
            this.updateObject(sysUserInfo);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage findPassword(String phoneNumber, String account) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysUserInfo sysUserInfo = new SysUserInfo();
            if ((phoneNumber==null||phoneNumber.equals(""))||(account==null||account.equals(""))){
                sm.setCode(2L);
                return sm;
            }
            sysUserInfo.setPhoneNumber(phoneNumber);
            sysUserInfo.setAccount(account);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);

            if (sysUserInfo == null) {
                sm.setCode(0l);
            } else {
                if (sysUserInfo.getUserType()!=null&&sysUserInfo.getUserType()==6){
                    String accountName = sysUserInfo.getAccount();
                    SysSalesManEntity salesManEntity = new SysSalesManEntity();
                    salesManEntity.setSalesAccount(accountName);
                    salesManEntity = sysSalesmanDao.getSalesManInfo(salesManEntity);
                    if (salesManEntity==null){
                        sm.setCode(0L);
                        return sm;
                    }
                }
                sm.setObj(sysUserInfo.getAccount());
            }
        } catch (Exception e) {
            sm.setCode(-1l);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveResetPassword(String account, String password, String passwordAgain) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        if (!password.equals(passwordAgain)) {
            sm.setCode(2l);
            return sm;
        }
        try {
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(account);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (sysUserInfo.getUserType()!=null&&sysUserInfo.getUserType()==6){
                String accountName = sysUserInfo.getAccount();
                SysSalesManEntity salesManEntity = new SysSalesManEntity();
                salesManEntity.setSalesAccount(accountName);
                salesManEntity = sysSalesmanDao.getSalesManInfo(salesManEntity);
                if (salesManEntity==null){
                    sm.setCode(0L);
                    return sm;
                }
            }
            sysUserInfo.setPassword(password);
            this.updateObject(sysUserInfo);
        } catch (Exception e) {
            sm.setCode(-1l);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage savePass(String account, String phoneNumber, String password) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            String sessionId = request.getHeader("sessionId");
            String accountName = SessionUtil.getLocalAccount(sessionId);
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(accountName);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (sysUserInfo.getUserType()!=null&&sysUserInfo.getUserType()==6){
                if (this.getApiSessionSalesMan()==null){
                    sm.setCode(5L);
                    sm.setMeg("用户不存在");
                    return sm;
                }
            }
            if (!sysUserInfo.getAccount().equals(account)) {
                sm.setCode(2l);
                sm.setMeg("账号名与登录账号不匹配！");
                return sm;
            }
            if (!sysUserInfo.getPhoneNumber().equals(phoneNumber)) {
                sm.setCode(3l);
                sm.setMeg("号码与登录账号号码不匹配！");
                return sm;
            }
            sysUserInfo.setPassword(password);
            this.updateObject(sysUserInfo);
        } catch (Exception e) {
            sm.setCode(0l);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage savePhone(String account, String password, String phoneNumber) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            String sessionId = request.getHeader("sessionId");
            String accountName = SessionUtil.getLocalAccount(sessionId);
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setAccount(accountName);
            sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
            if (sysUserInfo.getUserType()!=null&&sysUserInfo.getUserType()==6){
                if (this.getApiSessionSalesMan()==null){
                    sm.setCode(5L);
                    sm.setMeg("用户不存在");
                    return sm;
                }
            }
            if (sysUserInfo == null) {
                sm.setCode(4L);
                sm.setMeg("已下线");
                return sm;
            }
            if (!sysUserInfo.getAccount().equals(account)) {
                sm.setCode(2l);
                sm.setMeg("账号名与登录账号不匹配！");
                return sm;
            }
            if (!sysUserInfo.getPassword().equals(password)) {
                sm.setCode(3l);
                sm.setMeg("密码与登录账号密码不匹配！");
                return sm;
            }
            sysUserInfo.setPhoneNumber(phoneNumber);
            this.updateObject(sysUserInfo);
            sm.setObj(sysUserInfo.getPhoneNumber());
        } catch (Exception e) {
            sm.setCode(0l);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveFeedBack(SysFeedbackEntity sysFeedbackEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysUserInfo sysUserInfo = this.getApiSessionAccount();
            if (sysUserInfo == null) {
                sm.setMeg("已下线，请重新登录");
                sm.setCode(0l);
                return sm;
            }
//            SysFeedbackEntity feedbackEntity = new SysFeedbackEntity();
            sysFeedbackEntity.setCreateDate(new Date());
            sysFeedbackEntity.setCreateRen(sysUserInfo.getAccount());
//            feedbackEntity.setFbValue(feedBackValue);
            sysFeedbackEntity.setSuiId(sysUserInfo.getSuiId());
            sysUserInfoDao.saveFeedBack(sysFeedbackEntity, sysUserInfo);
        } catch (Exception e) {
            sm.setCode(0l);
            e.printStackTrace();
        }
        return sm;
    }
}
