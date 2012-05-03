package com.googlecode.iforums.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.googlecode.iforums.dao.Mapper;

public abstract class BaseService<T> implements InitializingBean {
	
    private Mapper<T> mapper;

    @Override
    public void afterPropertiesSet() throws Exception {
    	setMapperHandlewired();
    }
    
    public abstract void setMapperHandlewired();

	/**
	 * 
	 * @param object
	 */
	public void insert(T object){
		mapper.insert(object);
	}
	
	/**
	 * 
	 * @param object
	 */
	public int update(T object){
		return mapper.update(object);
	}
	
	/**
	 * get object by id
	 * @param id
	 * @return
	 */
	public T getObjectById(int id){
		return mapper.getObjectById(id);
	}
	
	/**
	 * delete
	 * @param id
	 */
	public void removeObjectById(int id){
		mapper.removeObjectById(id);
	}
	
	/**
	 * get section by page
	 * @param page
	 * @param size
	 * @return
	 */
	public List<T> select(int page,int size){
		page = page<=0?1:page;
		int offset = (page-1)*size;

		return mapper.select(offset, size);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<T> selectAll(){
		return select(0,Integer.MAX_VALUE);
	}
	
	public Mapper<T> getMapper() {
		return mapper;
	}

	public void setMapper(Mapper<T> mapper) {
		this.mapper = mapper;
	}
}
