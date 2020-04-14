package com.studyport.sql.query;

import com.studyport.persistence.query.FinderQuery;
import com.studyport.persistence.query.RowMapper;
import lombok.ToString;

import java.sql.ResultSet;
import java.util.List;


@ToString
public class SqlFinderQuery<R> extends SqlQuery implements FinderQuery<R, ResultSet> {

    private RowMapper<ResultSet, R> mapper;

    SqlFinderQuery(String query) {
        super(query);
    }

    SqlFinderQuery(String query, List params) {
        super(query, params);
    }

    SqlFinderQuery(String query, List params, RowMapper<ResultSet, R> mapper){
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
