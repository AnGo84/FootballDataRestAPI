package ua.footballdata.restservice;

import java.util.List;

public interface RestService<T> {
	T findById(long id);
	List<T> findAllData();
}
