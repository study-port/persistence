package com.studyport.sql.test;


import com.studyport.persistence.query.RowMapper;
import com.studyport.sql.QueryExecutor;
import com.studyport.sql.impl.JDBCQueryExecutor;
import com.studyport.sql.query.SqlQuery;
import com.studyport.sql.query.SqlRowMapper;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class TestJDBCQueryExecutor {

    private static JdbcDataSource dataSource;

    @BeforeAll
    static void init(){
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;INIT=create schema if not exists testdb\\;runscript from 'classpath:init.sql'");
    }

    @Test
    public void testExecuteInsertQuery() throws SQLException {
        JDBCQueryExecutor jdbcQueryExecutor = new JDBCQueryExecutor(dataSource);
        Object id = jdbcQueryExecutor.executeInsertQuery(PersonQuery.PERSON_INSERT, Arrays.asList("Anil", "K", 39));
        Assertions.assertEquals(3L, id);
    }

    @Test
    public void testExecuteQuery() throws SQLException{
        JDBCQueryExecutor jdbcQueryExecutor = new JDBCQueryExecutor(dataSource);
        List<String> result = jdbcQueryExecutor.executeQuery(PersonQuery.FIND_PERSON_BY_ID, Arrays.asList(1), (SqlRowMapper<String>) row -> row.getString("FIRST_NAME"));
        Assertions.assertEquals("BOB", result.get(0).toUpperCase());
    }

    @Test
    public void testExecuteUpdate() throws SQLException{
        JDBCQueryExecutor jdbcQueryExecutor = new JDBCQueryExecutor(dataSource);
        int updateRecordCount = jdbcQueryExecutor.executeUpdateQuery(PersonQuery.PERSON_AGE_UPDATE, Arrays.asList(30, 1));
        Assertions.assertEquals(1, updateRecordCount);
    }

    @Test
    public void testExecuteDelete() throws SQLException{
        JDBCQueryExecutor jdbcQueryExecutor = new JDBCQueryExecutor(dataSource);
        int deleteRecordCount = jdbcQueryExecutor.executeUpdateQuery(PersonQuery.PERSON_DELETE, Arrays.asList(1));
        Assertions.assertEquals(1, deleteRecordCount);
    }

}
