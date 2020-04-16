package com.studyport.sql.test;

import com.studyport.sql.query.SqlRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements SqlRowMapper<Person> {

    @Override
    public Person mapTo(ResultSet row) throws SQLException {
        Person person = new Person();
        person.setId(row.getLong("ID"));
        person.setFirstName(row.getString("FIRST_NAME"));
        person.setLastName(row.getString("LAST_NAME"));
        person.setAge(row.getInt("AGE"));
        return person;
    }
}
