package net.iforums.dao.impl;

import net.iforums.beans.Config;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.ConfigDao;

import org.springframework.stereotype.Repository;

@Repository
public class ConfigDaoImpl extends BaseORMDao<Config> implements ConfigDao{
	
	public ConfigDaoImpl(){
		this.setNamespace(Config.class.getSimpleName());
	}

	/**
	 * @see net.iforums.dao.ConfigDao#selectByName(java.lang.String)
	 */
	public Config selectByName(String name)
	{
		return null;
	}
}
