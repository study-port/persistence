package com.studyport.sql.query;

import com.studyport.persistence.query.BaseQuery;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class SqlQuery implements BaseQuery {

    private final String query;
    private List params;

    public static SqlQuery of(String query){
        return new SqlQuery(query);
    }

    public static SqlQuery of(String query, List params){
        return new SqlQuery(query, params);
    }

    SqlQuery(String query) {
        this.query = query;
    }

    SqlQuery(String query, List params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return this.query;
    }

    public List getParams() {
        return this.params;
    }

    public SqlQuery addParam(Object val){
        if(this.params == null){
            this.params = new ArrayList();
        }
        this.params.add(val);
        return this;
    }
}
