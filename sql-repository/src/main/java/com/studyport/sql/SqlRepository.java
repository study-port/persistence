package com.studyport.sql;

import com.studyport.persistence.CUDRepository;
import com.studyport.persistence.FinderRepository;
import com.studyport.persistence.exception.RepositoryException;
import com.studyport.persistence.query.FinderQuery;
import com.studyport.sql.query.SqlQuery;

import java.sql.ResultSet;
import java.util.List;

public interface SqlRepository extends CUDRepository<SqlQuery>, FinderRepository<ResultSet> {

    @Override
    Object create(SqlQuery query) throws RepositoryException;

    @Override
    int update(SqlQuery query) throws RepositoryException;

    @Override
    int delete(SqlQuery query) throws RepositoryException;

    @Override
    <R, T extends FinderQuery<R, ResultSet>> List<R> find(T query) throws RepositoryException;
}
