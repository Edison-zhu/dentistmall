package com.hsk.miniapi.xframe.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.utils.freeMarker.RandomCodeUtils;
/**
 * token管理类
 * @author Administrator
 *
 */
public class TokenUtil {
	/**
	 * token列表
	 */
	public static Map<String,Map<String,Object>> TOKEN_MAP = new HashMap<String,Map<String,Object>>();
	/**
	 * 增加token信息
	 * @param userType 用户类型：1后台管理员；2医院管理员；3医生；4：代理商；5业务员
	 * @param sysUserInfo
	 * @param userObject
	 */
	public static String addToken(SysUserInfo sysUserInfo){
		//检测该用户之前是否已经登录
		String oldToken="";
		for (Map.Entry<String,Map<String,Object>> entry : TOKEN_MAP.entrySet()) { 
			Map<String,Object> tokenMap=entry.getValue();
			if(tokenMap != null){
				SysUserInfo userAccount = (SysUserInfo) tokenMap.get("account");
				if(userAccount.getSuiId().equals(sysUserInfo.getSuiId())){
					oldToken=entry.getKey();
					break;
				}
			}
		}
		//如果已经登录删除之前的token信息
		if(oldToken !=null && oldToken!="")
			TOKEN_MAP.remove(oldToken);
		Map<String,Object> tokenMap = new HashMap<String,Object>();
		tokenMap.put("createTime", (new Date()));//登录时间
		tokenMap.put("account", sysUserInfo);//登录账号信息
		String token = RandomCodeUtils.getRandomString(32);
		TOKEN_MAP.put(token, tokenMap);
		return token;
	}
	/**
	 * 根据token获取信息
	 * @param token token信息
	 * @return
	 */
	public static Map<String,Object> getTokenInfo(String token){
		if(TOKEN_MAP.containsKey(token))
			return TOKEN_MAP.get(token);
		else
			return null;
	}
	/**
	 * 删除token信息
	 * @param token 
	 */
	public static void delToken(String token){
		if(TOKEN_MAP.containsKey(token))
			TOKEN_MAP.remove(token);
	}

}
