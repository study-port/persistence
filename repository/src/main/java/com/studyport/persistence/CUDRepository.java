package com.studyport.persistence;

import com.studyport.persistence.exception.RepositoryException;
import com.studyport.persistence.query.BaseQuery;

public interface CUDRepository<T extends BaseQuery> {

    Object create(T query) throws RepositoryException;

    int update(T query) throws RepositoryException;

    int delete(T query) throws RepositoryException;

}
