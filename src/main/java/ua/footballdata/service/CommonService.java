package ua.footballdata.service;


import java.util.List;



public interface CommonService<T> {
	
	T findById(long id);
	
	T findByName(String login);
	
	void save(T object);
	
	void update(T object);
	
	void deleteById(long id);

	List<T> findAll();
	
	//void deleteAllUsers();
	
	boolean isExist(T object);
	
}
