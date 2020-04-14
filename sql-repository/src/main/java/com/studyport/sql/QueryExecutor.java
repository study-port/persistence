package com.studyport.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface QueryExecutor {

    Object executeInsertQuery(String query, List params) throws SQLException;

    int executeUpdateQuery(String query, List params) throws SQLException;

    ResultSet executeQuery(String query, List params) throws SQLException;
}
