package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.SystemContext;
import com.hsk.miniapi.xframe.api.daobbase.ITablesInfoApiDao;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;

/**
 * 表对象业务处理类
 * 
 * @author xun
 * @version v1.0
 * 
 */

@Component
public class TablesInfoApiDao extends SupperDao implements ITablesInfoApiDao {

	public FMPojoInfo queryTableInfo(String tableName, String UserName) {
		FMPojoInfo lo_pojo = new FMPojoInfo();
		String sql = "";
		if (SystemContext.DB_Type.equals("mysql")) {
			// mysql表字段处理方法
			sql = " SELECT a.TABLE_NAME, a.TABLE_COMMENT FROM information_schema.TABLES  a WHERE  a.TABLE_NAME='"
					+ tableName
					+ "' AND a.table_schema='"
					+ UserName
					+ "' AND a.table_type='BASE TABLE' ";

			
		}
		if (SystemContext.DB_Type.equals("oracle")) {
			// oracle表字段处理方法
			sql="select   Table_Name ,comments as TABLE_COMMENT from user_tab_comments  where Table_Name='"+tableName+"'";
		}
		lo_pojo = this.getJdbcDao().getJdbcTemplate()
				.queryForObject(sql, new RowMapper<FMPojoInfo>() {
					public FMPojoInfo mapRow(ResultSet rs, int index)
							throws SQLException {
						FMPojoInfo bean = new FMPojoInfo();
						bean.setTableName(rs.getString("TABLE_NAME"));
						bean.setCommentText(rs.getString("TABLE_COMMENT"));
						return bean;
					}
				});
		lo_pojo.setPkName(this.getPkName(tableName, UserName));
		return lo_pojo;
	}

	public List<FMPojoInfo> queryListTableInfo(String UserName) {
		List<FMPojoInfo> lo_pojo = new ArrayList<FMPojoInfo>();
		String sql = "";
		if (SystemContext.DB_Type.equals("mysql")) {
			// mysql表字段处理方法
			sql = " SELECT a.TABLE_NAME, a.TABLE_COMMENT FROM information_schema.TABLES  a WHERE    a.table_schema='"
					+ UserName + "' AND a.table_type='BASE TABLE' ";
		
		}
		if (SystemContext.DB_Type.equals("oracle")) {
			// oracle表字段处理方法
			sql="select   Table_Name ,comments as TABLE_COMMENT from user_tab_comments  "; 
		}
		
		lo_pojo = this.getJdbcDao().getJdbcTemplate()
				.query(sql, new RowMapper<FMPojoInfo>() {
					public FMPojoInfo mapRow(ResultSet rs, int index)
							throws SQLException {
						
						FMPojoInfo bean = new FMPojoInfo();
						bean.setTableName(rs.getString("TABLE_NAME"));
						bean.setCommentText(rs.getString("TABLE_COMMENT")); 
						return bean;
						
					}
				});	
		if(lo_pojo!=null&&lo_pojo.size()>0){
			for(FMPojoInfo did:lo_pojo){
				did.setPkName(this.getPkName(did.getTableName(), UserName));
			}
		}
		return lo_pojo;
	}
	
	
	
	public String getPkName(String tableName, String UserName){
		String ls_back=null;
		String sql=" SELECT  k.column_name  as  PKNAME FROM information_schema.table_constraints t JOIN information_schema.key_column_usage k  "+
 " USING (constraint_name,table_schema,table_name) WHERE t.constraint_type='PRIMARY KEY'  AND t.table_schema='"+UserName+"'  AND t.table_name='"+tableName+"'";
		ls_back = this.getJdbcDao().getJdbcTemplate()
				.queryForObject(sql, new RowMapper<String>() {
					public String mapRow(ResultSet rs, int index)
							throws SQLException {
						String bean = rs.getString("PKNAME");
						return bean;
					}
				});
		
		
		return ls_back;
	}

}
