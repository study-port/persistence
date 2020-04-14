package com.studyport.sql.impl;

import com.studyport.sql.QueryExecutor;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
public class JDBCQueryExecutor implements QueryExecutor {

    @Getter(AccessLevel.PRIVATE)
    private final DataSource dataSource;

    @Override
    public Object executeInsertQuery(String query, List params) throws SQLException{
        return execute(query, params, statement -> {
           statement.executeUpdate();
           return statement.getGeneratedKeys().getObject(1);
        });
    }

    @Override
    public int executeUpdateQuery(String query, List params) throws SQLException{
        return execute(query, params, PreparedStatement::executeUpdate);
    }

    @Override
    public ResultSet executeQuery(String query, List params) throws SQLException{
        return execute(query, params, PreparedStatement::executeQuery);
    }

    private <R> R execute(String query, List params, StatementExecutor<R> executeFun) throws SQLException{
        try(Connection connection = getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                int index = 0;
                if(params != null){
                    for(Object obj : params){
                        statement.setObject(++index, obj);
                    }
                }
                return executeFun.execute(statement);
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
