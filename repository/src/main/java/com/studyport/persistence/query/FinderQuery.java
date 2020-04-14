package com.studyport.persistence.query;

public interface FinderQuery<R, T> extends BaseQuery {

    RowMapper<T, R> getRowMapper();

}
