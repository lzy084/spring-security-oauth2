package com.dd.cloud.core.base.mapper;

import java.util.List;

public interface IBaseMapper<T> {
     T get(Long id);

     List<T> findAllList();

     int insert(T entity);

     int insertBatch(List<T> list);

     int update(T entity);

     int delete(Long id);
}
