package com.studyport.persistence.query;

public interface RowMapper<T, R> {

    R mapTo(T row);

}
