package com.kisaan.jai.common.service;

import java.util.List;

public interface SearchService<T, ID> {

    List<T> search(String value);
}
