package net.iforums.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.iforums.utils.DbUtils;
import net.iforums.utils.preferences.ConfigKeys;
import net.iforums.utils.preferences.SystemGlobals;
import net.sf.cglib.beans.BeanMap;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 如果要添加新的方法，切忌catch异常，并返回一个缺省值
 * @version 3:22:18 PM Oct 14, 2010
 * @param <T>
 */

@SuppressWarnings("unchecked")
public class BaseORMDao<T> extends SqlMapClientDaoSupport {
	
	@Resource
	public void setSqlMapClientForAutowire(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	private SqlMapClientTemplate sqlMapClientTemplate = this.getSqlMapClientTemplate();
	private String namespace;

	/**
	 * @return the namespace
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @param namespace
	 *            the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * To get the full statement name with namespace.
	 * 
	 * @param statementName
	 * @return
	 */
	private String getFullStatementName(String statementName) {
		return namespace + "." + statementName;
	}
	
	protected Integer queryForInteger(String statementName) throws DataAccessException {
		try {
			return (Integer)sqlMapClientTemplate.queryForObject(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return -1;
		}
	}
	protected Integer queryForInteger(String statementName,Object parameterObject) throws DataAccessException {
	    try {
	        return (Integer)sqlMapClientTemplate.queryForObject(getFullStatementName(statementName),parameterObject);
	    } catch (Exception e) {
	        logger.error("database error.", e);
	        return -1;
	    }
	}
	
	protected Long queryForLong(String statementName) throws DataAccessException {
		try {
			return (Long)sqlMapClientTemplate.queryForObject(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return -1L;
		}
	}
	
	protected  Map<String, Object>  queryForMap(String statementName) throws DataAccessException {
		try {
			return (Map<String, Object>) sqlMapClientTemplate.queryForObject(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return new HashMap<String, Object>();
		}
	}	

	/**
	 * Query for object.
	 * 
	 * @param statementName
	 * @return
	 */
	protected Object queryForObject(String statementName) throws DataAccessException {
		try {
			return sqlMapClientTemplate.queryForObject(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return null;
		}
	}

	/**
	 * Query for object.
	 * 
	 * @param statementName
	 * @return
	 */
	protected T queryForEntry(String statementName) throws DataAccessException {
	    try {
	    	return (T)sqlMapClientTemplate.queryForObject(getFullStatementName(statementName));
	    } catch (Exception e) {
			logger.error("database error.", e);
			return null;
		}
	}
	/**
	 * Query for object.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 * @throws DataAccessException
	 */
	protected Object queryForObject(String statementName, Object parameterObject) throws DataAccessException {
		try {
			return sqlMapClientTemplate.queryForObject(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
			return null;
		}
	}
	
	/**
	 * Query for object.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 * @throws DataAccessException
	 */
    protected T queryForEntry(String statementName, Object parameterObject) throws DataAccessException {
		try {
			return (T)sqlMapClientTemplate.queryForObject(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
			return null;
		}
	}

	/**
	 * Query for list.
	 * 
	 * @param statementName
	 * @return
	 * @throws DataAccessException
	 */
	protected List queryForList(String statementName) throws DataAccessException {
		try {
			return sqlMapClientTemplate.queryForList(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return new ArrayList();
		}
	}

	/**
	 * Query for list.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 * @throws DataAccessException
	 */
	protected List<Map<String,Object>> queryForList(String statementName, Object parameterObject) throws DataAccessException {
		try {
			return sqlMapClientTemplate.queryForList(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
			return new ArrayList<Map<String,Object>>();
		}
	}
	
	/**
	 * Query for list.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 * @throws DataAccessException
	 */
    protected List<T> queryForEntryList(String statementName, Object parameterObject) throws DataAccessException {
		try {
			return (List<T>)sqlMapClientTemplate.queryForList(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
			return new ArrayList<T>();
		}
	}
	
	/**
	 * Query for list.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 * @throws DataAccessException
	 */
	protected List<T> queryForEntryList(String statementName){
		try {
			return (List<T>)sqlMapClientTemplate.queryForList(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return new ArrayList<T>();
		}
	}

	/**
	 * Query for map.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param keyProperty
	 * @return
	 * @throws DataAccessException
	 */
	protected Map queryForMap(String statementName, Object parameterObject, String keyProperty) {
		try {
			return sqlMapClientTemplate.queryForMap(getFullStatementName(statementName), parameterObject, keyProperty);
		} catch (Exception e) {
			logger.error("database error.", e);
			return new HashMap();
		}
	}

	/**
	 * Insert action.
	 * 
	 * @param statementName
	 */
	protected void insert(String statementName) {
		try {
			sqlMapClientTemplate.insert(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
		}
	}

	/**
	 * Insert action with parameter.
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	protected void insert(String statementName, Object parameterObject) {
		try {
			sqlMapClientTemplate.insert(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
		}
	}

	/**
	 * Update action.
	 * 
	 * @param statementName
	 * @return
	 */
	protected int update(String statementName) {
		try {
			return (Integer) sqlMapClientTemplate.update(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return -1;
		}
	}

	/**
	 * Update action with parameter.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	protected int update(String statementName, Object parameterObject) {
		try {
			return (Integer) sqlMapClientTemplate.update(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
			return -1;
		}
	}

	/**
	 * Delete action.
	 * 
	 * @param statementName
	 * @return
	 */
	protected int delete(String statementName) {
		try {
			return (Integer) sqlMapClientTemplate.delete(getFullStatementName(statementName));
		} catch (Exception e) {
			logger.error("database error.", e);
			return -1;
		}
	}

	/**
	 * Delete action with parameter.
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	protected int delete(String statementName, Object parameterObject) {
		try {
			return (Integer) sqlMapClientTemplate.delete(getFullStatementName(statementName), parameterObject);
		} catch (Exception e) {
			logger.error("database error.", e);
			return -1;
		}
	}
	
	protected static long getAutoId(){
	    try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
	    return System.currentTimeMillis();
	}
	
    public void deleteObjectById(long id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        this.delete("deleteObjectById", params);
    }
    public void deleteObjectById(int id) {
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("id",id);
    	this.delete("deleteObjectById", params);
    }
    public void deleteObjectById(String id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        this.delete("deleteObjectById", params);
    }
    public T getObjectById(long id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        return queryForEntry("getObjectById", params);
    }
    public T getObjectById(int id) {
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("id",id);
    	return queryForEntry("getObjectById", params);
    }
    
    public int getTotal() {
        Integer total = (Integer)queryForObject("getTotal");
        if(total==null){
            return 0;
        }
        return total;
    }
    
    public List<T> select(int page, int size) {
        Map<String,Object> params = new HashMap<String,Object>(2);
        if(page<=0)page=1;
        params.put("startId",(page-1)*size);
        params.put("endId",page*size);
        return queryForEntryList("select",params);
    }
    
    public List<T> selectMarked(int page, int size) {
        Map<String,Object> params = new HashMap<String,Object>(2);
        if(page<=0)page=1;
        params.put("startId",(page-1)*size+1);
        params.put("endId",page*size);
        return queryForEntryList("selectMarked",params);
    }
    
    public long insert(T obj) {
        Map<String,Object> params = BeanMap.create(obj);
        
        long id = 0l;
        Object idObj = params.get("id");
        if(idObj==null){
        	id = getAutoId();
            params.put("id", getAutoId());
        }else{
        	return (Long)idObj;
        }
        
        logger.info(params);
        
        insert("insert",params);
        return id;
    }

    public void update(T obj) {
        Map<String,Object> params = BeanMap.create(obj);
        logger.info(params);
        update("update",params);    
    }
    
    public <K> Map<K,T> queryForEntryMap(String keyProperty,Class<K> keyClass) {
        Map<String,Object> params = new HashMap<String,Object>(2);
        logger.info(params);
        return queryForMap("queryForEntryMap", params, keyProperty);
    }
    public Map<Long,T> queryForEntryMap(String keyProperty) {
        return queryForEntryMap(keyProperty,Long.class);
    }
    
    public void markObjectById(long id,int mark){
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("id", id);
        params.put("mark", mark);
        update("markObjectById",params);
    }
    
    
    private String autoGeneratedKeysQuery;

	protected boolean supportAutoGeneratedKeys()
	{
		return SystemGlobals.getBoolValue(ConfigKeys.DATABASE_AUTO_KEYS);
	}

	/**
	 * {@link #setSupportAutoGeneratedKey(boolean)} is set to <code>false</code>
	 * 
	 * @param query The query to execute to retreive the last generated key
	 */
	protected void setAutoGeneratedKeysQuery(String query)
	{
		this.autoGeneratedKeysQuery = query;
	}

	protected String getAutoGeneratedKeysQuery()
	{
		return this.autoGeneratedKeysQuery;
	}

	protected String getErrorMessage()
	{
		return "Could not obtain the latest generated key. This error may be associated"
			+ " to some invalid database driver operation or server failure."
			+ " Please check the database configurations and code logic.";
	}

	protected PreparedStatement getStatementForAutoKeys(String queryName, Connection conn) throws SQLException
	{
		PreparedStatement p = null;
		
		if (this.supportAutoGeneratedKeys()) {
			p = conn.prepareStatement(SystemGlobals.getSql(queryName), Statement.RETURN_GENERATED_KEYS);
		}
		else {
			p = conn.prepareStatement(SystemGlobals.getSql(queryName));
		}

		return p;
	}

	protected int executeAutoKeysQuery(PreparedStatement p, Connection conn) throws SQLException
	{
		int id = -1;
		p.executeUpdate();

		ResultSet rs = null;
		
		try {
			if (this.supportAutoGeneratedKeys()) {
				rs = p.getGeneratedKeys();
				
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
			else {
				p = conn.prepareStatement(this.getAutoGeneratedKeysQuery());
				rs = p.executeQuery();

				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		}
		finally {
			DbUtils.close(rs);
		}

		if (id == -1) {
			throw new SQLException(this.getErrorMessage());
		}

		return id;
	}
}
