package net.iforums.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.iforums.dao.BaseORMDao;
import net.jforum.dao.ApiDAO;

@Repository
public class ApiDaoImpl extends BaseORMDao<Object> implements ApiDAO {
	public ApiDaoImpl(){
		setNamespace("Api");
	}
	public boolean isValid(String apiKey) {
		Map<String,Object> parameterObject = new HashMap<String,Object>();
		parameterObject.put("key", apiKey);
		return queryForEntry("isValid", parameterObject)!=null;
	}

}
