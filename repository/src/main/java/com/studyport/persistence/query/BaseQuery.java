package com.studyport.persistence.query;

import java.util.List;

public interface BaseQuery {

    String getQuery();

    List getParams();
}
