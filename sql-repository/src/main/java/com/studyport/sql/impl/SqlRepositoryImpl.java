package com.studyport.sql.impl;

import com.studyport.persistence.exception.RepositoryException;
import com.studyport.persistence.query.FinderQuery;
import com.studyport.sql.QueryExecutor;
import com.studyport.sql.SqlRepository;
import com.studyport.sql.query.SqlQuery;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class SqlRepositoryImpl implements SqlRepository {

    private final QueryExecutor queryExecutor;

    @Override
    public Object create(SqlQuery query) throws RepositoryException {
        try {
            return queryExecutor.executeInsertQuery(query.getQuery(), query.getParams());
        } catch (SQLException e) {
            throw new RepositoryException(e, "DATABASE_ERROR", "Error in inserting data");
        }catch (Exception e){
            throw new RepositoryException(e, "DATABASE_ERROR", e.getMessage());
        }
    }

    @Override
    public int update(SqlQuery query) throws RepositoryException{
        try {
            return queryExecutor.executeUpdateQuery(query.getQuery(), query.getParams());
        } catch (SQLException e) {
            throw new RepositoryException(e, "DATABASE_ERROR", "Error in updating data");
        }catch (Exception e){
            throw new RepositoryException(e, "DATABASE_ERROR", e.getMessage());
        }
    }

    @Override
    public int delete(SqlQuery query) throws RepositoryException{
        try {
            return queryExecutor.executeUpdateQuery(query.getQuery(), query.getParams());
        } catch (SQLException e) {
            throw new RepositoryException(e, "DATABASE_ERROR", "Error in deleting data");
        }catch (Exception e){
            throw new RepositoryException(e, "DATABASE_ERROR", e.getMessage());
        }
    }

    @Override
    public <R, T extends FinderQuery<R, ResultSet>> List<R> find(T query) throws RepositoryException{
        try{
            return this.queryExecutor.executeQuery(query.getQuery(), query.getParams(), query.getRowMapper());
        }catch (SQLException e){
            throw new RepositoryException(e, "DATABASE_ERROR", "Error in fetching data : "+ query.toString());
        }catch (Exception e){
            throw new RepositoryException(e, "DATABASE_ERROR", e.getMessage());
        }
    }
}
