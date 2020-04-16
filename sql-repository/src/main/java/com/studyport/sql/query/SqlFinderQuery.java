package com.studyport.sql.query;

import com.studyport.persistence.query.FinderQuery;
import com.studyport.persistence.query.RowMapper;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@ToString
public class SqlFinderQuery<R> extends SqlQuery implements FinderQuery<R, ResultSet> {

    private final SqlRowMapper<R> mapper;

    public static SqlFinderQuery<Map<String, Object>> of(String query){
        return new SqlFinderQuery<Map<String, Object>>(query, new ColumnMapRowMapper());
    }

    public static <R> SqlFinderQuery<R> of(String query, SqlRowMapper<R> rowMapper){
        return new SqlFinderQuery<R>(query, rowMapper);
    }

    SqlFinderQuery(String query, SqlRowMapper<R> mapper) {
        this(query, null, mapper);
    }


    SqlFinderQuery(String query, List params, SqlRowMapper<R> mapper){
        super(query, params);
        this.mapper = mapper;
    }

    @Override
    public RowMapper<ResultSet, R> getRowMapper() {
        return this.mapper;
    }

    @Override
    public SqlFinderQuery<R> addParam(Object val) {
        super.addParam(val);
        return this;
    }
}
