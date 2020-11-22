package com.hsk.xframe.api.daobbase.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.SystemContext;
import com.hsk.xframe.api.daobbase.IFieldInfoDao;
import com.hsk.xframe.api.dto.freeMarker.FMFieldInfo;
import com.hsk.xframe.api.utils.freeMarker.FreeMarkerUtil;
 
@Component
public class FieldInfoDao extends SupperDao implements IFieldInfoDao {

	@Override
	public List<FMFieldInfo> queryListFieldInfo(String tableName,
			String UserName) {
		List<FMFieldInfo> lo_field = new ArrayList<FMFieldInfo>();
		String sql = "";
		if (SystemContext.DB_Type.equals("mysql")) {
			// mysql表字段处理方法
			sql = " SELECT  a.TABLE_NAME,a.COLUMN_NAME,a.COLUMN_COMMENT,a.DATA_TYPE,a.CHARACTER_MAXIMUM_LENGTH,a.NUMERIC_PRECISION,a.NUMERIC_SCALE,a.IS_NULLABLE,a.COLUMN_KEY ,a.ORDINAL_POSITION "+
                     " FROM  information_schema.COLUMNS a  WHERE  a.table_name = '"
					+ tableName
					+ "'   AND  a.table_schema = '"
					+ UserName
					+ "'  order by  a.ORDINAL_POSITION  ";
			 
			lo_field = this.getJdbcDao().getJdbcTemplate()
					.query(sql, new RowMapper<FMFieldInfo>() {
						public FMFieldInfo mapRow(ResultSet rs, int index)
								throws SQLException {
 
							
							FMFieldInfo bean = new FMFieldInfo();
							bean.setFieldCode(rs.getString("COLUMN_NAME"));
							bean.setFieldName(rs.getString("COLUMN_NAME"));
							bean.setFieldComment(rs.getString("COLUMN_COMMENT"));
							
							
							String dataType=rs
									.getString("DATA_TYPE");
							bean.setFieldType(FreeMarkerUtil.transformType(dataType));
						 
						 
							  if(bean.getFieldType().equals( "String")){
									bean.setDataLength( rs.getInt("CHARACTER_MAXIMUM_LENGTH") );
									bean.setDataPrecision( 0);
								}else  if(bean.getFieldType().equals( "Integer")){
									bean.setDataLength( rs.getInt("NUMERIC_PRECISION") );
									bean.setDataPrecision(0 );
								} else  if(bean.getFieldType().equals( "Double")){
									bean.setDataLength( rs.getInt("NUMERIC_PRECISION") );
									bean.setDataPrecision(rs.getInt("NUMERIC_SCALE") );
								}  
						  
							  bean.setIsNullable("YES".equals( rs.getString("IS_NULLABLE"))?"1":"2");
								bean.setColumnKey("PRI".equals( rs.getString("COLUMN_KEY"))?"1":"2");
						 
							return bean;
						}
					});
		
		}
		if (SystemContext.DB_Type.equals("oracle")) {
			// oracle表字段处理方法
			sql="select  " +
					"a.TABLE_NAME,  a.COLUMN_NAME,   a.DATA_TYPE, a.COLUMN_ID,a.CHAR_LENGTH,a.NULLABLE,a.DATA_LENGTH,a.DATA_PRECISION,a.DATA_SCALE,"+
				"(select comments from user_col_comments  where Table_Name= a.Table_Name and COLUMN_NAME=a.COLUMN_NAME ) as COLUMN_COMMENT, "+
				"( select  decode(count(*),1,'Y','N') from   user_cons_columns   "+
					"  where     constraint_name   =   (select   constraint_name   from   user_constraints   "+
					 "             where   Table_Name= a.Table_Name and COLUMN_NAME=a.COLUMN_NAME       "   +      
					"               and   constraint_type   ='P') "+
					" ) as  COLUMN_KEY "+
				"	 from user_tab_columns a "+
				"	  where a.Table_Name='"+tableName+"' order by  a.COLUMN_ID";
			
			
			lo_field = this.getJdbcDao().getJdbcTemplate()
					.query(sql, new RowMapper<FMFieldInfo>() {
						public FMFieldInfo mapRow(ResultSet rs, int index)
								throws SQLException {
							FMFieldInfo bean = new FMFieldInfo();
							bean.setFieldCode(rs.getString("COLUMN_NAME"));
							bean.setFieldComment(rs.getString("COLUMN_COMMENT")); 
							bean.setFieldName(rs.getString("COLUMN_NAME"));
							String dataType=rs
									.getString("DATA_TYPE");
							 if(dataType.equals("NUMBER")){
								 int DATA_SCALE= rs.getInt("DATA_SCALE");
								 if(DATA_SCALE>0){
									 dataType="double";
								 }
							 } 
							bean.setFieldType(FreeMarkerUtil.transformType(dataType));
							
							if(bean.getFieldType().equals( "String")){
								bean.setDataLength( rs.getInt("DATA_LENGTH") );
								bean.setDataPrecision( 0);
							}else  if(bean.getFieldType().equals( "Integer")){
								bean.setDataLength( rs.getInt("CHAR_LENGTH") );
								bean.setDataPrecision(0 );
							} else  if(bean.getFieldType().equals( "Double")){
								bean.setDataLength( rs.getInt("DATA_PRECISION") );
								bean.setDataPrecision(rs.getInt("DATA_SCALE") );
							} 
							
							bean.setIsNullable("Y".equals( rs.getString("NULLABLE"))?"1":"2");
							bean.setColumnKey( "Y".equals( rs.getString("COLUMN_KEY"))?"1":"2");
							
							return bean;
						}
					});
			
			
		}
		
		
		
		return lo_field;
	}

}
