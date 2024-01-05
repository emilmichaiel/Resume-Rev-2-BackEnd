package com.emilmi.resume.common;

import java.util.Map;

public interface EmilMiGenericService<T> {
    Map<String, Object> findAll(Integer page, Integer size, String sort);

    T findById(String id);

    T add(T t);

    T update(T t, String id);

    void delete(String id);
}
