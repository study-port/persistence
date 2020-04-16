package com.studyport.sql.test;

public class PersonQuery {

    public static final String PERSON_INSERT = "INSERT INTO PERSON (FIRST_NAME, LAST_NAME, AGE) VALUES (?,?,?)";

    public static final String FIND_PERSON_BY_ID = "SELECT * FROM PERSON WHERE ID = ?";

    public static final String PERSON_AGE_UPDATE = "UPDATE PERSON SET AGE = ? WHERE ID = ?";

    public static final String PERSON_DELETE = "DELETE FROM PERSON WHERE ID = ?";

}
