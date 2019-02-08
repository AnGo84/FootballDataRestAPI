package ua.footballdata.service;

import java.util.List;

public interface AppService<T> {
	T findById(long id);
	List<T> findAllData();
}
