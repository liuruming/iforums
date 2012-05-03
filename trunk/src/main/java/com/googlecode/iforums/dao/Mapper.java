package com.googlecode.iforums.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Mapper<T> {
	/**
	 * 
	 * @param object
	 */
	public void insert(@Param("obj") T object);
	
	/**
	 * 
	 * @param object
	 */
	public int update(@Param("obj") T object);
	
	/**
	 * get object by id
	 * @param id
	 * @return
	 */
	public T getObjectById(@Param("id") int id);
	
	/**
	 * delete
	 * @param id
	 */
	public void removeObjectById(@Param("id") long id);
	/**
	 * delete
	 * @param id
	 */
	public void removeObjectById(@Param("id") String id);
	
	/**
	 * get section by page
	 * @param page
	 * @param size
	 * @return
	 */
	public List<T> select(@Param("offset") int offset, @Param("size") int size);

    public int getTotal();

}
