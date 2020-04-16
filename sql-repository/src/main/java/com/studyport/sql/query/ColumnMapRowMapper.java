package com.studyport.sql.query;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Default ColumnMapRowMapper to return Row as Map&lt;String,Object&gt;
 */
public class ColumnMapRowMapper implements SqlRowMapper<Map<String, Object>> {

    private Set<String> columns;

    @Override
    public Map<String, Object> mapTo(ResultSet row) throws SQLException {
        Set<String> columnNames = getColumns(row);
        Map<String, Object> result = new HashMap<>();
        for(String columnName : columnNames){
            result.put(columnName, row.getObject(columnName));
        }
        return result;
    }


    private Set<String> getColumns(final ResultSet resultSet) throws SQLException{

        if(columns == null || columns.isEmpty()){
            columns = new HashSet<>();
            ResultSetMetaData metaData= resultSet.getMetaData();
            int n = metaData.getColumnCount();
            for(int index = 1; index <= n; index++){
                columns.add(metaData.getColumnName(index));
            }
        }

        return columns;
    }

}
