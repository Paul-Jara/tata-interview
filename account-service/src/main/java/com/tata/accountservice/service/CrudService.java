package com.tata.accountservice.service;

import java.util.List;

public interface CrudService<T, I> {
	
	void create(T object);
	
	void delete(I id);
	
	void update(T object);
	
	List<T> getAll();
}
