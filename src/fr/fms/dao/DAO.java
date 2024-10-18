package fr.fms.dao;

import java.util.ArrayList;

public interface DAO<T> {
	public void create(T obj);
	public T read(int id);
	public boolean update(T obj);
	public boolean delete(T obj);
	public ArrayList<T> readAll();
}
