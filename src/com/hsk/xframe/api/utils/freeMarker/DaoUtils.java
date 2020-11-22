package com.hsk.xframe.api.utils.freeMarker;

import com.mockrunner.jdbc.JDBCTestCaseAdapter;
import org.apache.openjpa.jdbc.kernel.JDBCSeq;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/19 17:02
 */
public class DaoUtils {

    public static List CallProcedure(JdbcTemplate JdbcTemplate, final String procedureName) {
        List list = (List)JdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                CallableStatement cs = connection.prepareCall(procedureName);
                return cs;
            }
        }, new CallableStatementCallback() {
            @Override
            public Object doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
                List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                callableStatement.execute();
                ResultSet rs = callableStatement.getResultSet();
                ResultSetMetaData metaData = rs.getMetaData();
                Integer count = metaData.getColumnCount();
                Map<String, Object> map;
                while (rs.next()) {
                    map = new HashMap<String, Object>();
                    for (int idx = 1; idx <= count; idx ++) {
                        map.put(metaData.getColumnName(idx), rs.getString(idx));
                    }
                    list1.add(map);
                }
                return list1;
            }
        });
        return list;
    }

}
