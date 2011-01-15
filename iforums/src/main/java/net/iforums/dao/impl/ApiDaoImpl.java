package net.iforums.dao.impl;

import java.util.HashMap;
import java.util.Map;

import net.iforums.dao.ApiDao;
import net.iforums.dao.BaseORMDao;

import org.springframework.stereotype.Repository;

@Repository
public class ApiDaoImpl extends BaseORMDao<Object> implements ApiDao {
	public ApiDaoImpl(){
		setNamespace("Api");
	}
	public boolean isValid(String apiKey) {
		Map<String,Object> parameterObject = new HashMap<String,Object>();
		parameterObject.put("key", apiKey);
		return queryForEntry("isValid", parameterObject)!=null;
	}

}
