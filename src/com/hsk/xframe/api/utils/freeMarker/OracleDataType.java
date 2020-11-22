package com.hsk.xframe.api.utils.freeMarker;
/**
 * oracle数据库对象类型
 * 
 * @author xun
 *@version v1.0
 */
public enum OracleDataType {

	oracle_int, oracle_integer,oracle_number, oracle_varchar, oracle_varchar2, oracle_char, oracle_double, oracle_timestamp, oracle_date,  oracle_blob, oracle_long;
	
	public static OracleDataType getDataType(String animal) {
		int leg=animal.indexOf("(");
		if(leg>=0){
			animal=animal.substring(0, leg);
		}
		return valueOf("oracle_" + animal.toLowerCase());
	}
}
