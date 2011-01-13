package net.iforums.dao;

import java.util.List;
import java.util.Map;

/**
 * 普通Dao的基类
 * @author zhurx
 *
 * @param <T>
 */
public interface BaseDao<T> {
    /**
     * 插入
     * @param obj
     */
    public void insert(T obj);
    
    /**
     * 更新
     * @param obj
     */
    public void update(T obj);
    
    /**
     * 查询
     * @param page
     * @param size
     * @return
     */
    public List<T> select(int page,int size);
    
    /**
     * 查询
     * @param page
     * @param size
     * @return
     */
    public List<T> selectMarked(int page,int size);
    
    /**
     * 
     * @param id
     * @return
     */
    public T getObjectById(long id);
    
    /**
     * 
     * @param id
     */
    public void deleteObjectById(long id);
    
    public void deleteObjectById(String id);
    
    /**
     * 
     * @return
     */
    public int getTotal();
    
    /**
     * 
     * @param <K>
     * @param keyProperty
     * @param keyClass
     * @return
     */
    public <K> Map<K,T> queryForEntryMap(String keyProperty,Class<K> keyClass);
    
    /**
     * 
     * @param keyProperty
     * @return
     */
    public Map<Long,T> queryForEntryMap(String keyProperty);
    
    /**
     * 
     * @param id
     * @param mark
     */
    public void markObjectById(long id,int mark);
}
