package org.itstep.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void save(T model);
    List<T> findAll();
    Optional<T> findById();
    void update(T model);
    void delete(T model);
    void delete(int id);
}
