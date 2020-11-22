package com.hsk.xframe.api.utils.freeMarker;

/**
 * mysql数据库对象类型
 * 
 * @author xun
 *@version v1.0
 */
public enum MySqlDataType {

	mysql_int, mysql_integer, mysql_varchar, mysql_char, mysql_double, mysql_longtext, mysql_datetime, mysql_decimal, mysql_time, mysql_smallint, mysql_timestamp, mysql_bigint, mysql_text, mysql_blob, mysql_bit,mysql_tinyint;

	public static MySqlDataType getDataType(String animal) {
		return valueOf("mysql_" + animal.toLowerCase());
	}

}
