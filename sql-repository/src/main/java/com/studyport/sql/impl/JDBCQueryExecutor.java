package com.studyport.sql.impl;

import com.studyport.persistence.query.RowMapper;
import com.studyport.sql.QueryExecutor;
import com.studyport.sql.query.SqlRowMapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class JDBCQueryExecutor implements QueryExecutor {

    private static final Logger logger = LoggerFactory.getLogger(JDBCQueryExecutor.class);

    @Getter(AccessLevel.PRIVATE)
    private final DataSource dataSource;

    @Override
    public Object executeInsertQuery(String query, List params) throws SQLException{
        return execute(query, params, statement -> {
           int res =statement.executeUpdate();
           logger.debug("Inserted {} records", res);
           ResultSet generatedKeyResultSet = statement.getGeneratedKeys();

           return generatedKeyResultSet.next()? generatedKeyResultSet.getObject(1) : null;
        });
    }

    @Override
    public int executeUpdateQuery(String query, List params) throws SQLException{
        return execute(query, params, PreparedStatement::executeUpdate);
    }

    @Override
    public <R> List<R> executeQuery(final String query, final List params, final RowMapper<ResultSet, R> rowMapper) throws SQLException{
        return execute(query, params, preparedStatement -> {
            List<R> result = new ArrayList<>();
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    result.add(((SqlRowMapper<R>)rowMapper).mapTo(resultSet));
                }
            }
            return result;
        });
    }

    private <R> R execute(final String query, final List params, final StatementExecutor<R> executeFun) throws SQLException{
        try(Connection connection = getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(query, 1)){
                int index = 0;
                if(params != null){
                    for(Object obj : params){
                        statement.setObject(++index, obj);
                    }
                }
                R result = executeFun.execute(statement);
                return result;
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }


    private interface StatementExecutor< R>{
        R execute(PreparedStatement t) throws SQLException;
    }

}
