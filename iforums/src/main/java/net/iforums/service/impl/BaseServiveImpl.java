package net.iforums.service.impl;

import javax.annotation.Resource;

import net.iforums.cache.CacheEngine;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jboss.logging.Logger;

public class BaseServiveImpl {
	static Logger logger = Logger.getLogger(BaseServiveImpl.class);
	
	@Resource(name="ehCacheEngine")
	protected CacheEngine cache;
	
	protected static Configuration config;
	
	static {
		try {
			config = new PropertiesConfiguration("configration.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			logger.fatal("配置文件加载不到", e);
		};
		System.out.println(config);
	}
	
	public static void main(String[] args) {
		
	}
}
