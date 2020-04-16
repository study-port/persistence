package com.studyport.sql.test;

import com.studyport.persistence.exception.RepositoryException;
import com.studyport.sql.SqlRepository;
import com.studyport.sql.impl.JDBCQueryExecutor;
import com.studyport.sql.impl.SqlRepositoryImpl;
import com.studyport.sql.query.SqlFinderQuery;
import com.studyport.sql.query.SqlQuery;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestSqlRepository {
    private static JdbcDataSource dataSource;

    @BeforeAll
    static void init(){
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;INIT=create schema if not exists testdb\\;runscript from 'classpath:init.sql'");
    }

    @Test
    public void testCreate() throws RepositoryException {
        SqlRepository repository = getSqlRepository();
        Object id = repository.create(SqlQuery.of(PersonQuery.PERSON_INSERT).addParam("Amit").addParam("Kumar").addParam(28));
        Assertions.assertEquals(3L, id);
    }

    @Test
    public void testUpdate() throws RepositoryException{
        SqlRepository repository = getSqlRepository();
        int recordsUpdateCount = repository.update(SqlQuery.of(PersonQuery.PERSON_AGE_UPDATE).addParam(30).addParam(1));
        Assertions.assertEquals(1, recordsUpdateCount);
    }

    @Test
    public void testDelete() throws RepositoryException{
        SqlRepository repository = getSqlRepository();
        int deletedCount = repository.delete(SqlQuery.of(PersonQuery.PERSON_DELETE).addParam(1));
        Assertions.assertEquals(1, deletedCount);
    }

    @Test
    public void testFind() throws RepositoryException{
        SqlRepository repository = getSqlRepository();
        SqlFinderQuery<Person> finderQuery = SqlFinderQuery.of(PersonQuery.FIND_PERSON_BY_ID, new PersonMapper()).addParam(1);

        List<Person> personList = repository.find(finderQuery);

        Person expected = new Person();
        expected.setId(1);
        expected.setFirstName("Bob");
        expected.setLastName("T");
        expected.setAge(24);

        Assertions.assertTrue(!personList.isEmpty());
        Assertions.assertEquals(expected, personList.get(0));
    }

    private SqlRepository getSqlRepository(){
        return new SqlRepositoryImpl(new JDBCQueryExecutor(dataSource));
    }

}
