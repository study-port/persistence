package com.studyport.sql;

import com.studyport.persistence.query.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryExecutor {

    Object executeInsertQuery(String query, List params) throws SQLException;

    int executeUpdateQuery(String query, List params) throws SQLException;

    <R> List<R> executeQuery(final String query, final List params, final RowMapper<ResultSet, R> rowMapper) throws SQLException;
}
