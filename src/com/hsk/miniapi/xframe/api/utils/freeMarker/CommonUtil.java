package com.hsk.miniapi.xframe.api.utils.freeMarker;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class CommonUtil {
	
	public static Map<Integer,HttpSession> USER_SESSION_MAP = new HashMap<Integer,HttpSession>();
	
	public static Integer GYS_ADMIN_ROLE=2214;//供应商管理员角色
	public static Integer GYS_ROLE=2215;//供应商普通角色
	
	public static Integer JT_ADMIN_ROLE=2217;//集团管理员角色
	public static Integer YY_ADMIN_ROLE=2218;//医院管理员角色
	public static Integer MZ_ADMIN_ROLE=2219;//门诊管理员角色
	public static Integer CGY_ROLE=2220;//采购员角色
	public static Integer CK_ADMIN_ROLE=2221;//仓库管理员角色
	public static Integer LLY_ROLE=2222;//领料员角色
}
