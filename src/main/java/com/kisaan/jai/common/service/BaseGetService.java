package com.kisaan.jai.common.service;

import java.util.List;

public interface BaseGetService<T, ID> {

    T findById(ID id);

    List<T> findAll();
}
