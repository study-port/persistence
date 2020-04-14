package com.studyport.persistence;

import com.studyport.persistence.exception.RepositoryException;
import com.studyport.persistence.query.BaseQuery;
import com.studyport.persistence.query.FinderQuery;

import java.util.List;

public interface FinderRepository<C> {

    <R, T extends FinderQuery<R, C>> List<R> find(T query) throws RepositoryException;
}
