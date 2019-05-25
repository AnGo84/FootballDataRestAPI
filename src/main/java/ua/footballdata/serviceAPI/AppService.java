package ua.footballdata.serviceAPI;

import java.util.List;

public interface AppService<T> {
	T findById(long id);
	List<T> findAllData();
}
