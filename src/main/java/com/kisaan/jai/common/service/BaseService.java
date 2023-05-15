package com.kisaan.jai.common.service;

public interface BaseService<T, ID> {

	T save(T t);
	
	T update(ID id,T t);
	
	void delete(ID id);
}
