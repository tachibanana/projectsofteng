package com.app.dao;

import java.util.List;

public interface UserDAO<T> {
	
	public void addUser(T user);
	public void updateUser(T user);
	public List<T> getListOfUser();
	public T getUserById(int userId);
	public void removeUser(T user);

}
