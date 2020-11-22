package com.hsk.miniapi.xframe.api.utils.freeMarker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateCodeUtil {
	/**
	 * 获取系统编号
	 * @return
	 */
	public static String getSysInfoNo(){
		return "SYS"+getNo();
	}
	/**
	 * 获取角色编号
	 * @return
	 */
	public static String getSysRoleNo(){
		return "ROL"+getNo();
	}
	/**
	 * 
	 * @return
	 */
	public static String getSysMenuNo(){
		return "MEN"+getNo();
	}
	public static String getSysParameterNo(){
		return "PAR"+getNo();
	}
	public static String getSysUserNo(){
		return "USE"+getNo();
	}
	public static String getSysPowerControlNo(){
		return "COM"+getNo();
	}
	public static String getSysControlPlanNo(){
		return "COP"+getNo();
	}
	public static String getSysControlPlanParamNo(){
		return "CPP"+getNo();
	}
	public static String getSysColumnsInfoNo(){
		return "COI"+getNo();
	}
	public static String getSysPlanFaceNo(){
		return "PLF"+getNo();
	}
	public static String getSysFaceplanParamNo(){
		return "FPP"+getNo();
	}
	public static String getSysPlanInfoNo(){
		return "SPI"+getNo();
	}
	public static String getSysPlanParamDefiniNo(){
		return "PPD"+getNo();
	}
	public static String getSysControlTemplateNo(){
		return "PCT"+getNo();
	}
	public static String getSysControlTemplateParamNo(){
		return "CTP"+getNo();
	}
	
	public static String getSysWebsitTempletNo(){
		return "SWT"+getNo();
	}
	public static String getMxxzWebsitTempletNo(){
		return "MWT"+getNo();
	}
	public static String getSysTempletInfoNo(){
		return "STI"+getNo();
	}
	public static String getSysSiteColumnsNo(){
		return "SSC"+getNo();
	}
	
	public static String getFieldNo() {
		return "FIE"+getNo();
	}
	public static String getTableNo() {
		return "TAB"+getNo();
	}

	
	
	
	private static String getNo(){
        String datetime=new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()); 
        String[] str1=datetime.split(" ");
        String[] str2=str1[0].split("-");
        String[] str3=str1[1].split(":");
        String no=str2[0]+str2[1]+str2[2]+str3[0]+str3[1]+str3[2]+sn();
        return no;
        
    }
	private static String  sn() {
        Random random = new Random();
        int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
        int b = 0;
        while (true) {
            b = random.nextInt(999);
            if (b < 100) {
                continue;
            } else
                break;
        }
        return b+"";
    }
	
	public static String getSysCompanyNo() {
		return "CMP"+getNo();
	}
	public static String getSysControlInfoNo() {
		return "CNT"+getNo();
	}
	public static String getSysClassNo() {
		return "CLA"+getNo();
	}
	public static String getSysClassColumnsNo() {
		return "COL"+getNo();
	}
	public static String getNo(String prefix) { 
		return prefix+getNo();
	}

	
	
}
