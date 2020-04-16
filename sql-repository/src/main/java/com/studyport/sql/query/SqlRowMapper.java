package com.studyport.sql.query;

import com.studyport.persistence.query.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlRowMapper<R> extends RowMapper<ResultSet, R> {
    @Override
    R mapTo(ResultSet row) throws SQLException;
}
